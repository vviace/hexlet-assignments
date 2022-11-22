package exercise.connections;

public interface Connection {
    // BEGIN
    void connect();
    String getName();
    void disconnect();
    void write(String content);
    // END
}
