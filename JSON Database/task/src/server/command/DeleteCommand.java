package server.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import server.database.DataBase;
import server.KeyMissingException;
import server.request.ResponseException;

public class DeleteCommand implements Command{

    private final JsonElement key;
    private ResponseException responseException;

    public DeleteCommand(JsonElement key){
        this.key = key;
        this.responseException = ResponseException.NO_EXCEPTION;
    }

    public ResponseException getResponseException() {
        return responseException;
    }
    private void setResponseException(ResponseException responseException){
        this.responseException = responseException;
    }

    @Override
    public void execute() {
        try {
            DataBase.getInstance().deleteCellByKey(this.key);
        }catch (KeyMissingException e){
            setResponseException(ResponseException.KEY_MISSING_EXCEPTION);
        }catch (JsonSyntaxException e){
            setResponseException(ResponseException.JSON_FORMAT_EXCEPTION);
        }

    }
}
