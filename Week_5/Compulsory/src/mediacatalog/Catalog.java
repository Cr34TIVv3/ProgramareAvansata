package mediacatalog;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Catalog implements java.io.Serializable{

    List<Item> itemList;

    public Catalog () {
        itemList = new ArrayList<>();
    }

    public void add(Item entry) {
        itemList.add(entry);
    }


    public void list() {
        for(var item : itemList) {
            item.describe();
        }
    }

    private void makeCopy(Catalog original) {
        this.itemList = original.itemList;
    }

    public void play(String name) throws IOException, MyException{
        //streams
        for(var entry : itemList) {
            if ( entry.getName().equals(name)) {
                File file = new File(entry.getPath());

                if( !file.exists() )
                    throw new MyException("This file does not exists" + entry.getName());


                Desktop desktop = Desktop.getDesktop();

                desktop.open(file);
                break;
            }
        }
    }

    public void save() throws IOException, ClassNotFoundException{
        FileOutputStream file = new FileOutputStream("file.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.close();
        file.close();
    }


    public void load() throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream("file.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        this.makeCopy((Catalog) in.readObject());
        in.close();
        file.close();
    }
}
