package server.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import server.database.DataBase;
import server.KeyMissingException;
import server.request.ResponseException;

public class GetCommand implements Command {

    private final JsonElement key;
    private JsonElement value;


    private ResponseException responseException;

    public GetCommand(JsonElement key) {
        this.key = key;
        this.responseException = ResponseException.NO_EXCEPTION;
    }

    public JsonElement getValue() {
        return this.value;
    }

    private void setValue(JsonElement value) {
        this.value = value;
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
            setValue(DataBase.getInstance().getCellByKey(this.key));
        } catch (KeyMissingException e) {
            setResponseException(ResponseException.KEY_MISSING_EXCEPTION);
        } catch (JsonSyntaxException e) {
            setResponseException(ResponseException.JSON_FORMAT_EXCEPTION);
        }

    }

}
