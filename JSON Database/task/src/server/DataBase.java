package server;

import java.util.HashMap;

public class DataBase {
    private final static DataBase INSTANCE = new DataBase();
    public static DataBase getInstance() {
        return INSTANCE;
    }
    private static HashMap<String, String> dataBase;
    public HashMap<String, String> getDataBase() {
        return (HashMap<String, String>) dataBase.clone();
    }

    public void setDataBase(HashMap<String, String> dataBase) {
        this.dataBase = dataBase;
    }

    public String getCellByKey(String key) {
        try {
            if (!this.getDataBase().get(key).equals("")) {
                return this.getDataBase().get(key);
            } else {
                return "ERROR";
            }
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public String setCellByKey(String key, String value) {
        try {
            this.dataBase.put(key, value);
            return "OK";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public String deleteCellByKey(String key) {
        try {
            if (this.dataBase.get(key) == null)
                return "ERROR";
            this.dataBase.remove(key);
            return "OK";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    DataBase() {
        this.dataBase = new HashMap<>();
    }
}
