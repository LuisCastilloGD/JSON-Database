package server.request;

import com.google.gson.*;
import server.command.*;

public class RequestController {
    private final CommandExecutor commandExecutor;

    public RequestController() {
        this.commandExecutor = new CommandExecutor();
    }

    public String inputRequest(String request) {

        JsonObject jsonObject = JsonParser.parseString(request).getAsJsonObject();
        String typeRequest = jsonObject.get("type").getAsString();
        if (typeRequest.equals("exit"))
            return ResponseWrapper.getOkResponse();

        JsonElement key = jsonObject.get("key");

        switch (typeRequest) {
            case "set" -> {
                JsonElement value = jsonObject.get("value");
                SetCommand setCommand = new SetCommand(key, value);
                commandExecutor.executeCommand(setCommand);
                return ResponseWrapper.wrapResponse(setCommand);
            }
            case "get" -> {
                GetCommand getCommand = new GetCommand(key);
                commandExecutor.executeCommand(getCommand);
                return ResponseWrapper.wrapResponse(getCommand);
            }
            case "delete" -> {
                System.out.println("In Request Controller");
                DeleteCommand delCommand = new DeleteCommand(key);
                commandExecutor.executeCommand(delCommand);
                return ResponseWrapper.wrapResponse(delCommand);
            }
            default -> {
                return ResponseWrapper.getErrorResponse();
            }
        }
    }

}
