import java.util.Objects;

public abstract class Source{

    protected String name;
    protected int supply;

    protected static int count = 1;

    public abstract void printType();

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


    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", supply=" + supply +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return supply == source.supply && name.equals(source.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supply);
    }
}
