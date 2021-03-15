import java.io.IOException;

public interface Command {
    void doSomething(Catalog catalog) throws IOException, ClassNotFoundException;
}
