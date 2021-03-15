import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReportOpenCommand implements Command{
    List<String> tokens;

    public ReportOpenCommand(String command) {
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

    public void doSomething(Catalog catalog) throws IOException {
        File file = new File("output.html");

        if( !file.exists() )
            throw new MyException("Report file does not exits...");


        Desktop desktop = Desktop.getDesktop();

        desktop.open(file);
    }
}
