package jtp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JTPOutputStream {

    private ObjectOutputStream output;

    public JTPOutputStream(Socket socket) throws IOException {
        super();
        this.output = new ObjectOutputStream(socket.getOutputStream());
    }

    public void put(JTPMessage message) throws IOException {
        output.writeObject(message);
        output.flush();
    }
}

