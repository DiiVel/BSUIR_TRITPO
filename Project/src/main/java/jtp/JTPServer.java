package jtp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class JTPServer {

    private static int idBase = new Random().nextInt(10000);

    private ServerSocket serverSocket;

    private HashMap<String, JTPSocket> clients = new HashMap<>();

    private HashMap<String, JTPFunction> inputEndPoints = new HashMap<>();

    public JTPServer() throws IOException {
        serverSocket = new ServerSocket(8080);
    }

    private static String generateId() {
        return String.format("%x", ("" + (idBase++)).hashCode());
    }

    public void registerInputEndPoint(String path, JTPFunction function) {
        inputEndPoints.put(path, function);
    }

    public List<String> getTokens() {
        return new ArrayList<>(clients.keySet());
    }

    public void sendMessage(JTPMessage message) {
        new Thread(() -> {
            try {
                JTPSocket socket = clients.get(message.getToken());
                if (socket == null) {
                    System.out.println("No user for token: " + message.getToken());
                    return;
                }
                var stream = socket.getOut();
                stream.put(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void start() {
        Thread acceptingThread = new Thread(() -> {
            while (true) {
                try {
                    connect(new JTPSocket(serverSocket.accept()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        acceptingThread.start();
    }

    private void connectThreads(JTPSocket socket) {
        new Thread(() -> {
            try {
                var stream = socket.getIn();
                while (true) {
                    var message = stream.get();
                    System.out.println("message");
                    var value = inputEndPoints.get(message.getPath());
                    if (value != null) {
                        value.proceed(message);
                    } else {
                        System.out.println("End Point not registered " + message.getPath());
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void connect(JTPSocket client) {
        JTPMessage value = JTPMessage.createMessage("", "", "");
        try {
            var stream = client.getIn();
            value = stream.get();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (value.getPath().equals("token")) {
            clients.put(value.getDBody(String.class), client);
            connectThreads(client);
            return;
        }
        if (value.getPath().equals("registration")) {
            String token = generateId();
            clients.put(token, client);
            connectThreads(client);
            try {
                var stream = client.getOut();
                stream.put(JTPMessage.createMessage("token", token, token));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
