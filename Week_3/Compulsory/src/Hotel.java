public class Hotel extends Location implements Classifiable{
    private int rank;

    public Hotel(String name, int rank) {
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
