package server;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;

public class InputController {

    RequestController requestController;

    InputController() {
        this.requestController = new RequestController();

    }

    @Deprecated
    protected void inputMenu() {
        final Scanner scanner = new Scanner(System.in);
        boolean active = true;
        while (active) {
            final String input = scanner.next();
            switch (input) {
                case "set" -> requestController.update(scanner.next(), scanner.nextLine());
                case "get" -> requestController.read(scanner.next());
                case "delete" -> requestController.delete(scanner.next());
                case "exit" -> active = false;
                default -> {
                    System.out.println("ERROR\n");
                }
            }
        }
    }

    protected String inputRequest(String request) {

        JsonObject jsonObject = new JsonParser().parse(request).getAsJsonObject();
        String typeRequest = jsonObject.get("type").getAsString();

        if (typeRequest.equals("exit"))
            return "{\"response\" : \"OK\"}";

        String key = jsonObject.get("key").getAsString();

        switch (typeRequest) {
            case "set" -> {
                String value = jsonObject.get("value").getAsString();
                return requestController.update(key, value);
            }
            case "get" -> {
                return requestController.read(key);
            }
            case "delete" -> {

                return requestController.delete(key);
            }
            default -> {
                return "ERROR";
            }
        }


    }


}
