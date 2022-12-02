package jtp;

import com.google.gson.Gson;

import java.io.Serializable;

public class JTPMessage implements Serializable {
    private static Gson gson = new Gson();
    private String path;
    private String token;
    private String body;

    public static JTPMessage createMessage(String path, Object data, String token) {
        var message = new JTPMessage();
        message.setBody(gson.toJson(data));
        message.setToken(token);
        message.setPath(path);
        return message;
    }

    public static JTPMessage createMessage(String path, Object data) {
        var message = new JTPMessage();
        message.setBody(gson.toJson(data));
        message.setPath(path);
        return message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public <T> T getDBody(Class<T> type) {
        return gson.fromJson(body, type);
    }

}
