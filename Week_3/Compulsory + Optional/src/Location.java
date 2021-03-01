import com.sun.source.tree.ReturnTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Location {
    protected String name;

    protected Map<Location, Integer> map;

    public Location() {
        map = new HashMap<Location, Integer>();
    }

    public Map<Location, Integer>getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return name.equals(location.name) && map.equals(location.map);
    }


    protected void addToMap(Location location, int time) {
        map.put(location, time);
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
