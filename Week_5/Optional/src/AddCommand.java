import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddCommand implements Command {
    List<String> tokens;

    public AddCommand(String command) {
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
        Item item;
        int countExpectedParams = 3;
        boolean isImage = true;
        String name = null;
        String path = null;

        for(var token : tokens) {
            if ( countExpectedParams == 3) {
                if(token.equals("Image")) {
                    isImage = true;
                }
                else if(token.equals("Song")) {
                    isImage = false;
                }
                countExpectedParams--;
            }
            else if( countExpectedParams == 2) {
                name = token;
                countExpectedParams--;
            }
            else if( countExpectedParams == 1){
                path = token;
                countExpectedParams--;
            }
            else {
                break;
            }
        }
        if(isImage) {
            item = new Image(name, path);
        }
        else {
            item = new Song(name, path);
        }

        catalog.add(item);
    }
}
