import freemarker.template.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class ReportCreateCommand implements Command{
    List<String> tokens;

    public ReportCreateCommand(String command) {
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
        Configuration config = new Configuration();

        config.setClassForTemplateLoading(ReportCreateCommand.class, "../resources");

//        config.setIncompatibleImprovements(new Version(2, 3, 20));
//        config.setDefaultEncoding("UTF-8");
//        config.setLocale(Locale.US);

        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<>();

        input.put("items",catalog.getItemList());

        Template template = config.getTemplate("template.ftl");

        Writer writer = new FileWriter(new File("./output.html"));
        try{
            template.process(input,writer);
        }
        catch (TemplateException exc) {
            System.out.println(exc.getMessage());
        }
        finally {
            writer.close();
        }
    }
}
