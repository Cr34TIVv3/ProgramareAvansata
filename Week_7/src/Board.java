import java.util.List;

public class Board {
    private int tokenCount;
    private List<Token> tokenList;

    public Board(int tokenCount) {
        this.tokenCount = tokenCount;
    }

    public void addToken(Token token) {
        if(tokenList.size() < tokenCount)
            tokenList.add(token);
    }
}
