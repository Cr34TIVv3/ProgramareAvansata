import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListCommand implements Command{
    List<String> tokens;

    public ListCommand(String command) {
        tokens = new ArrayList<>();
        this.makeTokens(command);
    }

    private void makeTokens(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        boolean firstToken = true;
        while( tokenizer.hasMoreTokens() ) {
            if( firstToken ) {
                firstToken = false;
                tokenizer.nextToken();
            }
            else {
                tokens.add(tokenizer.nextToken());
            }
        }
    }
    public void doSomething(Catalog catalog) {
        catalog.list();
    }
}
