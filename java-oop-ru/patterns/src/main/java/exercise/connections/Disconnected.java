package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{
    private final TcpConnection connection;

    public Disconnected(TcpConnection connectionTcp) {
        this.connection = connectionTcp;
    }

    @Override
    public void connect() {
        TcpConnection connection = this.connection;
        connection.setConnectionState(new Connected(connection));
    }

    @Override
    public String getName() {
        return "disconnected";
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String content) {
        System.out.println("Error! Try to write to disconnected connection");
    }
}
// END
