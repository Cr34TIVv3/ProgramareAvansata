import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    final private Socket serverSocket;
    final private PrintWriter out;
    final private BufferedReader in;


    public Client(String ipAddress, int port) throws Exception {
        serverSocket = new Socket(ipAddress, port);
        out = new PrintWriter(serverSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
    }

    void exitIfNotAvailable() throws Exception {
        try{
            if (in.read() == -1) {
                System.out.println("You have been disconnected..");
                serverSocket.close();
                in.close();
                out.close();
                System.exit(0);
            }
        }
        catch (SocketException e) {
            System.out.println("You have been disconnected..");
            serverSocket.close();
            in.close();
            out.close();
            System.exit(0);
        }

    }

    public void getStarted() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String command = scanner.nextLine();

                out.println(command);
                out.flush();

                if(command.equals("exit")) {
                    serverSocket.close();
                    in.close();
                    out.close();
                    System.exit(0);
                }

                String response = in.readLine();
                System.out.println(response);
            }
        }
        catch (SocketException e) {
            exitIfNotAvailable();
        }
    }
}
