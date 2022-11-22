package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection connection;
    private String content;

    public Connected(TcpConnection connectionTcp) {
        this.connection = connectionTcp;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public String getName() {
        return "connected";
    }

    @Override
    public void disconnect() {
        TcpConnection connection = this.connection;
        connection.setConnectionState(new Disconnected(connection));
    }

    @Override
    public void write(String content) {
        connection.addToBuffer(content);
    }
}
// END
