import java.util.Objects;

public class Destination extends Object{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return demand == that.demand && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, demand);
    }
}
