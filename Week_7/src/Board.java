import java.util.ArrayList;
import java.util.List;

public class Board {
    private int tokenCount;
    private List<Token> tokenList;

    public Board(int tokenCount) {
        this.tokenCount = tokenCount;
        tokenList = new ArrayList<>();
    }

    public Token grabRandomToken() {
        if(tokenList.size() != 0) {
            int index = (int)(Math.random()*100)%tokenList.size();
            Token token = tokenList.get(index);
            tokenList.remove(token);
            return token;
        }
        return null;
    }

    public void addToken(Token token) {
        if(tokenList.size() < tokenCount)
            tokenList.add(token);
    }

    public boolean hasTokens() {
        if (tokenList.size() > 0)
            return true;
        return false;
    }
}
