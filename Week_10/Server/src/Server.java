import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance = null;
    private int port = 0;

    public static boolean keepRunning;
    public static ServerSocket serverSocket;

    private Server(int port) {
        this.port = port;
        keepRunning = true;
    }

    public static Server getInstance(int port) {
        if (instance == null) {
            instance = new Server(port);
        }
        return instance;
    }

    public void run() throws IOException {
        try {
            System.out.println("Waiting for clients..");
            serverSocket = new ServerSocket(port);

            initializeState();

            while (keepRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client arrived..");
                new ClientRequestThread(clientSocket).start();
            }
        }
        catch (Exception e) {
            System.out.println("Server closed..");
        }
    }

    public static UsersState deserialization() {
        UsersState object1 = null;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("file.ser");

            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (UsersState)in.readObject();

            in.close();
            file.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return object1;
    }

    public static void serialization(UsersState state) {

        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("file.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(state);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    private static void initializeState() {
        UsersState state = Server.deserialization();
        if (state == null) {
            ClientRequestThread.state = new UsersState();
        }
        else {
            System.out.println("Initialized from serialization !!!");
            state.showUsers();
            ClientRequestThread.state = state;
        }
    }
}
