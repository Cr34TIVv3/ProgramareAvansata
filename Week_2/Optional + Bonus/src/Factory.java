public class Factory extends Source{
    public Factory(int supply) {
        this.name = "S" + count++;
        this.supply = supply;
    }
    public Factory(String name, int supply) {
        this.name = name;
        this.supply = supply;
    }


    public void printType() {
        System.out.println("This is a factory!");
    }
}
