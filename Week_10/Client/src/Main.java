public class Main {
    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1";
        int port = 7777;

        Client client = new Client(serverAddress, port);
        client.getStarted();
    }
}
