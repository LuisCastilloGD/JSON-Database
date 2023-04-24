package server.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import server.database.DataBase;
import server.KeyMissingException;
import server.request.ResponseException;

public class SetCommand implements Command {

    private final JsonElement key;
    private final JsonElement value;
    private ResponseException responseException;

    public SetCommand(JsonElement key, JsonElement value) {
        this.key = key;
        this.value = value;
        this.responseException = ResponseException.NO_EXCEPTION;
    }

    public ResponseException getResponseException() {
        return responseException;
    }

    private void setResponseException(ResponseException responseException) {
        this.responseException = responseException;
    }

    @Override
    public void execute() {
        try {
            DataBase.getInstance().setCellByKey(this.key, this.value);
        } catch (KeyMissingException e) {
            setResponseException(ResponseException.KEY_MISSING_EXCEPTION);
        } catch (JsonSyntaxException e) {
            setResponseException(ResponseException.JSON_FORMAT_EXCEPTION);
        }

    }
}
