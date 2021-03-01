import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class City extends Location implements Classifiable{
    private int rank;
    private List<Location> locationList;


    public City(String name, int rank) {
        locationList = new ArrayList<Location>();
        this.name = name;
        this.rank = rank;
    }

    public void addLocation(Location location) {
        locationList.add(location);
    }

    public void displayLocations() {
        List<Location> proxyList = locationList;
        Location[] locations = new Location[locationList.size()];

        int size = 0;
        for(Location place : locationList) {
            if( place instanceof Visitable && !(place instanceof Payable) ) {
                locations[size++] = place;
            }
        }

        for(int i=0;i<size-1;i++) {
            for(int j=i+1;j<size;j++) {
                LocalTime[] timeByIndexI = new LocalTime[2];
                LocalTime[] timeByIndexJ = new LocalTime[2];
                Location auxiliar;

                timeByIndexI = ((Visitable) locations[i]).getHoursAvailability();
                timeByIndexJ = ((Visitable) locations[j]).getHoursAvailability();

                if(timeByIndexI[0].isAfter(timeByIndexJ[0]) ) {
                    auxiliar = locations[i];
                    locations[i] = locations[j];
                    locations[j] = auxiliar;
                }
            }
        }
        for(int i=0;i<size;i++) {
            proxyList.add(locations[i]);
            System.out.println(locations[i].name + " having the starting our=" + ((Visitable) locations[i]).getHoursAvailability()[0]);
        }
        locationList = proxyList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

}
