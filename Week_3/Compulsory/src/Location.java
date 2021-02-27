import java.util.HashMap;

public class Location {
    protected String name;

    protected HashMap<String, Integer> map;

    public Location() {
        map = new HashMap<String, Integer>();
    }

    protected void addToMap(String name, int time) {
        map.put(name, time);
    }
    protected int getTimeByKey(String key) {
        return map.get(key);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
