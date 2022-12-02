package jtp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class JTPInputStream {

    private ObjectInputStream input;

    JTPInputStream(Socket socket) throws IOException {
        super();
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    public JTPMessage get() throws IOException, ClassNotFoundException {
        return (JTPMessage) input.readObject();
    }

}
