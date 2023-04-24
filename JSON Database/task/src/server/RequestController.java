package server;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RequestController implements CRUD {
    DataBase dataBase;

    RequestController() {
        this.dataBase = DataBase.getInstance();
    }

    @Override
    public String create(String key, String value) {
        String response = this.dataBase.setCellByKey(key, value);
        return response;
    }

    @Override
    public String read(String key) {
        String value = dataBase.getCellByKey(key);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("response", "OK");
        parameters.put("value", value);

        if (value.equals("ERROR")) {
            parameters = new HashMap<>();
            parameters.put("response", value);
            parameters.put("reason", "No such key");
        }

        Gson gson = new Gson();
        String responseJson = gson.toJson(parameters);
        return responseJson;
    }

    @Override
    public String update(String key, String value) {
        String response = dataBase.setCellByKey(key, value);
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("response", response);
        Gson gson = new Gson();
        String responseJson = gson.toJson(parameters);
        return responseJson;
    }

    @Override
    public String delete(String key) {
        String response = dataBase.deleteCellByKey(key);
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("response", response);
        if (response.equals("ERROR"))
            parameters.put("reason", "No such key");
        Gson gson = new Gson();
        String responseJson = gson.toJson(parameters);
        return responseJson;
    }
}
