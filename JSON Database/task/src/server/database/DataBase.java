package server.database;

import com.google.gson.*;
import server.KeyMissingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class DataBase {
    private final static DataBase INSTANCE = new DataBase();
    private final ReadAndWriteLockController readAndWriteLockController;
    private JsonObject dataBase;
    private final String PATH_TO_FILE = "./src/server/data/data.json";

    public JsonElement getCellByKey(JsonElement key) throws KeyMissingException {
        readAndWriteLockController.getReadLock().lock();
        if (key.isJsonPrimitive()) {
            if (dataBase.has(key.getAsString())) {
                readAndWriteLockController.getReadLock().unlock();
                return dataBase.get(key.getAsString());
            }
        } else if (key.isJsonArray()) {
            readAndWriteLockController.getReadLock().unlock();
            return findElement(key.getAsJsonArray());
        }
        throw new KeyMissingException();
    }

    public synchronized void setCellByKey(JsonElement key, JsonElement value) throws KeyMissingException {
        readAndWriteLockController.getWriteLock().lock();
        if (key.isJsonPrimitive()) {
            dataBase.add(key.getAsString(), value);
            updateDataBaseFile();
            readAndWriteLockController.getWriteLock().unlock();
        } else if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String keyToAdd = keys.remove(keys.size() - 1).getAsString();
            addElement(keys).getAsJsonObject().add(keyToAdd, value);
            updateDataBaseFile();
            readAndWriteLockController.getWriteLock().unlock();
        } else {
            readAndWriteLockController.getWriteLock().unlock();
            throw new KeyMissingException();
        }

    }


    public synchronized void deleteCellByKey(JsonElement key) throws KeyMissingException {
        readAndWriteLockController.getWriteLock().lock();
        if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String deleteThis = keys.remove(keys.size() - 1).getAsString();
            findElement(keys).getAsJsonObject().remove(deleteThis);
            updateDataBaseFile();
            readAndWriteLockController.getWriteLock().unlock();
        } else if (dataBase.has(key.getAsString())) {
            dataBase.remove(key.getAsString());
            updateDataBaseFile();
            readAndWriteLockController.getWriteLock().unlock();
        } else {
            readAndWriteLockController.getWriteLock().unlock();
            throw new KeyMissingException();
        }
    }

    public DataBase() {
        dataBase = new JsonObject();
        try {
            File file = new File(PATH_TO_FILE);
            if (file.exists()) {
                System.out.println("Found File");
                String dbFile = new String(Files.readAllBytes(file.toPath()));
                System.out.println(dbFile);
                dataBase = new Gson().fromJson(dbFile, JsonObject.class);
                System.out.println(dataBase.toString());
            } else {
                System.out.println("Didn't find file");
                Files.createFile(file.toPath());
                dataBase = new JsonObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in Data Base File");
        } finally {
            readAndWriteLockController = ReadAndWriteLockController.getInstance();
        }

    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    private JsonElement addElement(JsonArray keys) {
        JsonElement tmp = dataBase;
        for (JsonElement key : keys) {
            if (!tmp.getAsJsonObject().has(key.getAsString())) {
                tmp.getAsJsonObject().add(key.getAsString(), new JsonObject());
            }
            tmp = tmp.getAsJsonObject().get(key.getAsString());
        }
        return tmp;
    }

    private JsonElement findElement(JsonArray keys) throws KeyMissingException {
        JsonElement tmp = dataBase;
        for (JsonElement key : keys) {
            if (!key.isJsonPrimitive() || !tmp.getAsJsonObject().has(key.getAsString())) {
                throw new KeyMissingException();
            }
            tmp = tmp.getAsJsonObject().get(key.getAsString());
        }
        return tmp;
    }

    private void updateDataBaseFile() {
        System.out.println(dataBase);
        try (FileWriter fileWriter = new FileWriter(PATH_TO_FILE)) {
            fileWriter.write(dataBase.toString());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating Data Base File");
        }
    }

}
