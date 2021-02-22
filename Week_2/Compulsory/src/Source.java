import java.util.Objects;

public class Source{
    public enum SourceType {
        WAREHOUSE, FACTORY
    }

    private String name;
    private int supply;
    private SourceType type;

    private static int count = 1;

    public Source(SourceType type, int supply) {
        this.name = "S" + count++;
        this.type = type;
        this.supply = supply;
    }
    public Source(String name, SourceType type, int supply) {
        this.name = name;
        this.type = type;
        this.supply = supply;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getSupply() {
        return supply;
    }
    public void setSupply(int supply) {
        this.supply = supply;
    }

    public SourceType getType() {
        return type;
    }
    public void setType(SourceType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", supply=" + supply +
                ", type=" + type +
                '}';
    }

}
