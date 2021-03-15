import mediacatalog.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            Catalog catalog = new Catalog();
            catalog.add(new Song("She past away","C:\\Users\\rafae\\Desktop\\FII_2_SEM2\\PA\\Week_5\\Compulsory\\src\\Media\\She Past Away - Kasvetli Kutlama.mp3"));
            catalog.add(new Image("Chuck","C:\\Users\\rafae\\Desktop\\FII_2_SEM2\\PA\\Week_5\\Compulsory\\src\\Media\\Chuck.jpeg"));
            catalog.play("Chuck");
            catalog.save();
            catalog.list();
            TimeUnit.SECONDS.sleep(2);
            catalog.add(new Song("Du Hast","C:\\Users\\rafae\\Desktop\\FII_2_SEM2\\PA\\Week_5\\Compulsory\\src\\Media\\Rammstein - Du Hast (Official Video).mp3"));
            catalog.play("Du Hast");
            TimeUnit.SECONDS.sleep(5);
            catalog.load();
            catalog.list();
            //catalog.play("Du Hast");
        }
        catch (MyException | InterruptedException | IOException | ClassNotFoundException exception) {
            System.out.println(exception);
        }
    }
}
