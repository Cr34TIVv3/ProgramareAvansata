import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PlayCommand implements Command{
    List<String> tokens;

    public PlayCommand(String command) {
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

    public void doSomething(Catalog catalog) throws IOException{
        List<Item> itemList = catalog.getItemList();
        String name = tokens.get(0);

        for(var entry : itemList) {
            if ( entry.getName().equals(name)) {
                File file = new File(entry.getPath());

                if( !file.exists() )
                    throw new MyException("This file does not exists" + entry.getName());


                Desktop desktop = Desktop.getDesktop();

                desktop.open(file);
            }
        }
    }

}
