public class School implements Comparable<School>{
    private String name;
    private int capacity;

    private static int label = 0;

    public School(int capacity) {
        this.name = "H" + label++;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void decrementCapacity() {
        this.capacity--;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(School o) {
        return name.compareTo(o.name);
    }
}
