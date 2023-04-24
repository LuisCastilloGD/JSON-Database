package server.request;

import com.google.gson.Gson;
import server.command.Command;
import server.command.DeleteCommand;
import server.command.GetCommand;
import server.command.SetCommand;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    private final static String RESPONSE = "response";
    private final static String REASON = "reason";
    private final static String VALUE = "value";
    private final static String OK_RESPONSE = "OK";
    private final static String ERROR_RESPONSE = "ERROR";
    private final static String MISSING_KEY_RESPONSE = "No such key";

    public static String wrapResponse(Command command) {
        final Map<String, Object> parameters = new HashMap<>();
        if (command instanceof GetCommand) {
            if (((GetCommand) command).getResponseException().equals(ResponseException.NO_EXCEPTION)) {
                if (((GetCommand) command).getValue().isJsonNull())
                    return new Gson().toJson(getErrorResponse());
                parameters.put(VALUE, ((GetCommand) command).getValue());
                parameters.put(RESPONSE, OK_RESPONSE);
            } else if (((GetCommand) command).getResponseException().equals(ResponseException.KEY_MISSING_EXCEPTION)) {
                parameters.put(RESPONSE, ERROR_RESPONSE);
                parameters.put(REASON, MISSING_KEY_RESPONSE);
            }
        } else if (command instanceof SetCommand) {
            command.ge
        if (((SetCommand) command).getResponseException().equals(ResponseException.NO_EXCEPTION)) {
                parameters.put(RESPONSE, OK_RESPONSE);
            } else {
                parameters.put(RESPONSE, ERROR_RESPONSE);
            }
        } else if (command instanceof DeleteCommand) {
            if (((DeleteCommand) command).getResponseException().equals(ResponseException.NO_EXCEPTION)) {
                parameters.put(RESPONSE, OK_RESPONSE);
            } else {
                parameters.put(RESPONSE, ERROR_RESPONSE);
                parameters.put(REASON, MISSING_KEY_RESPONSE);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(parameters);
    }

    public static String getOkResponse() {
        return "{\"" + RESPONSE + "\" : \"" + OK_RESPONSE + "\"}";
    }

    public static String getErrorResponse() {
        return "{\"" + RESPONSE + "\" : \"" + ERROR_RESPONSE + "\"}";
    }
}
