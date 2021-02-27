public class Warehouse extends Source{
    public Warehouse(int supply) {
        this.name = "S" + count++;
        this.supply = supply;
    }
    public Warehouse(String name, int supply) {
        this.name = name;
        this.supply = supply;
    }


    public void printType() {
        System.out.println("This is a warehouse!");
    }
}
