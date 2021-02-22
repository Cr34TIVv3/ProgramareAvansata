import java.util.Objects;

public class Destination{

    private String name;
    private int demand;

    private static int count = 1;

    public Destination(int demand) {
        this.setName("D" + count++);
        this.setDemand(demand);
    }
    public Destination(String name, int demand) {
        this.setName(name);
        this.setDemand(demand);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDemand() {
        return demand;
    }
    public void setDemand(int demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", demand=" + demand +
                '}';
    }
}
