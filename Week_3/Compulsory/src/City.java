import java.util.List;

public class City extends Location implements Classifiable{
    private int rank;
    private List<Location> locationList;


    public City(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

}
