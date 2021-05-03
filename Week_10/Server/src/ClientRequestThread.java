import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class ClientRequestThread extends Thread{
    final private Socket clientSocket;
    final private PrintWriter out;
    final private BufferedReader in;

    final private int label;
    private static int counter = 0;




    public static UsersState state;

    private boolean keepRunning;

    public ClientRequestThread(Socket clientSocket) throws Exception{
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        keepRunning = true;

        label = counter;
        counter++;
    }



    public void run() {
        try {
            while (keepRunning) {
                clientSocket.setSoTimeout(60*1000);
                String message = in.readLine();
                System.out.println(label + " : " + message);

                handleMessage(message);
            }
        }
        catch (SocketTimeoutException e) {
            out.println("You have been disconnected..");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //    C:\Users\Rafa\Desktop\repos\ProgramareAvansata\Week_10\Client\out\artifacts\Client_jar\Client.jar
    /*
        register name
        login name
        friend name1, name2 ...
        send message
        read
        stop
        exit
     */
    private void handleMessage(String message) throws IOException {
        List<String> tokens = splitIntoTokens(message);

        String keyword = tokens.get(0);

        switch (keyword) {
            case "register":
                tokens.remove(0);
                handleRegistration(tokens);
                break;
            case "login":
                tokens.remove(0);
                handleLogin(tokens);
                break;
            case "friend":
                tokens.remove(0);
                handleFriends(tokens);
                break;
            case "send":
                tokens.remove(0);
                handleSending(tokens);
                break;
            case "read":
                tokens.remove(0);
                handleReading(tokens);
                break;
            case "exit":
                tokens.remove(0);
                handleExit(tokens);
                break;
            case "stop":
                tokens.remove(0);
                handleStop(tokens);
                break;
        }
    }

    private void handleRegistration(List<String> tokens) {
        String username = tokens.get(0);
        if (!state.hasUsername(username)) {
            User newUser = new User(username);
            state.addUser(newUser);

            state.showUsers();

            String response = "User " + username + " was added successfully..";
            System.out.println(response);
            out.println(response);
            out.flush();
        }
    }

    private void handleLogin(List<String> tokens) {
        String username = tokens.get(0);
        if (state.hasUsername(username)) {

            state.setOnline(username);
            name = username;

            String response = "User " + username + " is now online..";
            System.out.println(response);
            out.println(response);
            out.flush();
        }
    }

    private String name;

    private void handleFriends(List<String> tokens) {
        if (state.getOnlineStatus(name)) {
            User user = state.getByUsername(name);
            for (var token : tokens) {
                user.addFriend(token);
            }
        }
        out.println("Friend list updated..");
        out.flush();
    }

    private void handleSending(List<String> tokens) {
        User user = state.getByUsername(name);
        String message = tokens.get(0);

        var iterator = user.getIterator();

        while (iterator.hasNext()) {
            String friend = iterator.next();

            User userFriend = state.getByUsername(friend);
            if (userFriend != null) {
                userFriend.addMessage(name + ": " + message);
            }
        }
        System.out.println("Message sent ..");
        out.println("Message sent ..");
        out.flush();
    }

    private void handleReading(List<String> tokens) {
        User thisUser = state.getByUsername(name);
        out.println(thisUser.getMessages());
        thisUser.setMessageEmpty();
        out.flush();
    }

    private void handleExit(List<String> tokens) {
        User thisUser = state.getByUsername(name);
        thisUser.setOnlineStatus(false);

        state.printUserState();

        keepRunning = false;
    }

    private void handleStop(List<String> tokens) throws IOException {
        if (!Server.keepRunning) {
            out.println("Server will be shutdown asap ...(command from other player)");
            out.flush();
            return;
        }

//        Server.serverSocket.close();

        out.println("Server will be shutdown asap ...");
        out.flush();

        new Thread(()-> {

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (state.checkIfAllUsersAreDisconnected()) {
                    Server.serialization(state);
                    System.exit(0);
                }
            }
        }).start();

    }

    private List<String> splitIntoTokens(String content) {
        StringTokenizer tokenizer = new StringTokenizer(content);
        List<String> list = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;
    }
}
