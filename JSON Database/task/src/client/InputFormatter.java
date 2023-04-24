package client;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class InputFormatter {
    private static final String PATH_TO_FILE = "src/client/data/";

    public static List<String> getMessages(Args args) {
        Gson gson = new Gson();
        if (args.getInputFileName() != null)
            return inputInFile(args);
        if (args.getTypeRequest().equals("exit")) {
            return Collections.singletonList(gson.toJson(Map.of("type", "exit")));
        } else {
            return Collections.singletonList(gson.toJson(getParameters(args)));
        }
    }

    private static List<String> inputInFile(Args args) {
        if (args.getInputFileName() != null) {
            try (BufferedReader inputFile = new BufferedReader(new FileReader(PATH_TO_FILE + args.getInputFileName()))) {
                List<String> msgJson = new ArrayList<>();
                String line;
                while ((line = inputFile.readLine()) != null) {
                    msgJson.add(line);
                }
                return msgJson;
            } catch (Exception e) {
                System.out.println("Error in input File");
            }
        }
        return null;
    }

    private static Map<String, String> getParameters(Args args) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("type", args.getTypeRequest());
        parameters.put("key", args.getKey());
        if (args.getTypeRequest().equals("set"))
            parameters.put("value", args.getValue());
        return parameters;
    }
}
