public class Main {
    public static void main(String[] args) throws Exception {

//        Server.serialization(new UsersState());

        Server server = Server.getInstance(7777);
        server.run();
    }
}
