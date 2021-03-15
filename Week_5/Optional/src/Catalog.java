import java.util.ArrayList;
import java.util.List;

public class Catalog implements java.io.Serializable{
    List<Item> itemList;

    public Catalog () {
        itemList = new ArrayList<>();
    }

    public void add(Item entry) {
        itemList.add(entry);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void makeCopy(Catalog original) {
        this.itemList = original.itemList;
    }

    public void list() {
        for(var item : itemList) {
            item.describe();
        }
    }
}
