package jtp;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class JTPClient {
    private JTPSocket client;

    private String token = "";

    public String getToken() {
        return token;
    }

    private HashMap<String, JTPFunction> inputEndPoints = new HashMap<>();

    public void start() throws IOException {
        client = new JTPSocket(new Socket("localhost", 8080));
        try {
            var stream = client.getOut();
            stream.put(JTPMessage.createMessage("registration", "", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            var stream = client.getIn();
            var message = stream.get();
            token = message.getBody();
            connectThreads(client);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connectThreads(JTPSocket socket) {
        new Thread(() -> {
            try {
                var stream = socket.getIn();
                while (true) {
                    var message = stream.get();
                    var value = inputEndPoints.get(message.getPath());
                    if (value != null) {
                        value.proceed(message);
                    } else {
                        System.out.println("End Point not registered " + message.getPath());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void registerInputEndPoint(String path, JTPFunction function) {
        inputEndPoints.put(path, function);
    }

    public void sendMessage(JTPMessage message) {
        new Thread(() -> {
            try {
                message.setToken(token);
                client.getOut().put(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}

