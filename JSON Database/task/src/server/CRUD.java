package server;

public interface CRUD {
    String create(String key, String value);

    String read(String key);

    String update(String key, String value);

    String delete(String key);
}