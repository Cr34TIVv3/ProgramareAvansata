import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SaveCommand implements Command{
    List<String> tokens;

    public SaveCommand(String command) {
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
        FileOutputStream file = new FileOutputStream("file.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(catalog);
        out.close();
        file.close();
    }

}
