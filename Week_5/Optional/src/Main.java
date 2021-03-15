import java.io.IOException;


/*

add  #Song#  <name> <path>
     #Image# <name> <path>
list
save
load
play <name>

report create

add Image Chuck C:\\source.Media\\Chuck.jpeg
add Song  File1 C:\\source.Media\\file1.mp3
list
save
add Song  File2 C:\\source.Media\\file2.mp3
load
list

 */

public class Main {
    public static void main(String[] args) {
        try {
            Shell shell = new Shell();
            shell.init();
        }
        catch (IOException | ClassNotFoundException | MyException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
