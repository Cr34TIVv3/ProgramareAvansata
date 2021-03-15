import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
    private final Catalog catalog;

    public Shell() {
        catalog = new Catalog();
    }

    public void init() throws  IOException, ClassNotFoundException{
        for (;;){
            BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
            System.out.print("|>>");
            String command = reader.readLine();

            this.commandParsing(command);
        }
    }

    private void commandParsing(String command) throws IOException, ClassNotFoundException{
        Command cmd;
        if(command.matches("add[ ]+(Image|Song)[ ]+[a-zA-Z0-9\\-\\_]+[ ]+[a-zA-Z0-9\\-\\_:\\\\.]+")) {
            cmd = new AddCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Item has been added.");
        }
        else if(command.matches("^(list)$")) {
            cmd = new ListCommand(command);
            cmd.doSomething(catalog);
        }
        else if(command.matches("^(save)$")) {
            cmd = new SaveCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Catalog saved!");
        }
        else if(command.matches("^(load)$")) {
            cmd = new LoadCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Catalog loaded!");
        }
        else if(command.matches("play[ ]+[a-zA-Z0-9\\-\\_]+")) {
            cmd = new PlayCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Item played..");
        }
        else if(command.matches("report[ ]+(create)[ ]*")) {
            cmd = new ReportCreateCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Report created..");
        }
        else if(command.matches("report[ ]+(open)[ ]*")) {
            cmd = new ReportCreateCommand(command);
            cmd.doSomething(catalog);
            System.out.println("Report created..");
        }
        else {
            throw new MyException("Error: command does not exists");
        }
    }
}
