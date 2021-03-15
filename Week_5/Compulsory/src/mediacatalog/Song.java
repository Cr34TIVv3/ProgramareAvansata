package mediacatalog;

public class Song implements Item, java.io.Serializable {
    private String name;
    private String path;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void describe() {
        System.out.println(this.toString());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
