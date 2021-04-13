import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class Game {
    private Board board;
    private List<Player> players;
    private ArrayBlockingQueue<Player> playerQueue;

    public Game(Board board) {
        this.board = board;
        players = new LinkedList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        playerQueue = new ArrayBlockingQueue<>(players.size());
        Runnable[] runnable = new Runnable[players.size()];
        int count = 0;
        for(var p : players) {
            playerQueue.add(p);
            runnable[count++] = () -> {

                Player player = p;
                while(board.hasTokens()) {
                    if(player.equals(playerQueue.peek())) {
                        Token token = board.grabRandomToken();
                        System.out.println(player.getName() + " took " + token.value);
                        playerQueue.remove(player);
                        playerQueue.add(player);
                    }
                }
            };
        }
        for(int i=0;i<count;i++) {
            new Thread(runnable[i]).start();
        }
    }
}
