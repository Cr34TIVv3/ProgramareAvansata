import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadCommand implements Command{
    List<String> tokens;

    public LoadCommand(String command) {
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

    public void doSomething(Catalog catalog) throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream("file.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        catalog.makeCopy((Catalog) in.readObject());
        in.close();
        file.close();
    }
}
