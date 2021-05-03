import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class User implements Serializable {
    private String name;
    private String messages;
    final private List<String> friends;
    private boolean onlineStatus;

    public User(String name) {
        this.name = name;
        friends = new ArrayList<>();
        messages = " ";
    }

    public String getName() {
        return name;
    }

    public ListIterator<String> getIterator() {
        return friends.listIterator();
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean online) {
        onlineStatus = online;
    }

    public void addFriend(String username) {
        if (!friends.contains(username)) {
            friends.add(username);
        }
    }

    public void addMessage(String message) {
        messages += message + "; ";
    }

    public String getMessages() {
        return messages;
    }

    public void setMessageEmpty() {
        messages = " ";
    }
}
