package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private final String ip;
    private final int port;
    private final List<String> buffer = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ip = ipAddress;
        this.port = port;
        this.connection = new Disconnected(this);
    }
    public void addToBuffer(String content) {
        buffer.add(content);
    }
    public void setConnectionState(Connection connectionState) {
        this.connection = connectionState;
    }

    public void connect() {
        this.connection.connect();
    }


    public String getCurrentState() {
        return connection.getName();
    }


    public void disconnect() {
        this.connection.disconnect();
    }

    public void write(String content) {
        connection.write(content);
    }
}
// END
