package jtp;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

public class JTPSocket {

    private Socket socket;
    private JTPOutputStream out;
    private JTPInputStream in;

    public JTPSocket(Socket socket) throws IOException {
        out = new JTPOutputStream(socket);
        in = new JTPInputStream(socket);
    }


    public JTPOutputStream getOut() {
        return out;
    }

    public JTPInputStream getIn() {
        return in;
    }
}
