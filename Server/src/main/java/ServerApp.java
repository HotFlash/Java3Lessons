import server.MyServer;

public class ServerApp {

    private static final int PORT = 8190;

    public static void main(String[] args) {
        new MyServer().start(PORT);
    }
}
