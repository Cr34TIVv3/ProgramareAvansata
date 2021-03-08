import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    private City city;
    private Location[] locations;

    public TravelPlan(City city) {
        this.city = city;
        locations = new Location[0];
    }

    public void addLocation(Location location) {
        Location[] proxyLocations = new Location[this.locations.length+1];
        int i=0;
        for(i=0;i<locations.length;i++) {
            proxyLocations[i] = this.locations[i];
        }
        proxyLocations[i]=location;
        this.locations = proxyLocations;
    }

    private Map.Entry<Location, Integer> getEntryFromMap(Map<Location, Integer> map, Location location) {
        for(Map.Entry<Location, Integer> entry : map.entrySet()) {
            if ( location.equals(entry.getKey()) ) {
                return entry;
            }
        }
        return null;
    }

    public void dijkstra(Location start, List<Location> doNotVisitAgain, Map<Location, Location> preceding, Map<Location, Integer> currentCost) {
        if( doNotVisitAgain.size() == preceding.size()-1 ) {
            return;
        }

        boolean ok = true;
        Map.Entry<Location, Integer> lowestEntry = null;

        for(Map.Entry<Location, Integer> entry : currentCost.entrySet()) {
            if(ok) {
                lowestEntry = entry;
                ok = false;
            }
            else {
                break;
            }
        }

        for(Map.Entry<Location, Integer> entry : currentCost.entrySet()) {
            if(entry.getValue() < lowestEntry.getValue() && !doNotVisitAgain.contains(entry.getKey())) {
                lowestEntry = entry;
            }
        }

        Map<Location, Integer> mapForCosts = lowestEntry.getKey().getMap();

        for(Map.Entry<Location, Integer> entry : mapForCosts.entrySet()) {
            Map.Entry<Location, Integer> entryMatchCurrentCost = getEntryFromMap(currentCost, entry.getKey());
            if(entryMatchCurrentCost != null) {
                if( lowestEntry.getValue() +  entry.getValue() < entryMatchCurrentCost.getValue()) {
                    currentCost.put(entryMatchCurrentCost.getKey(), lowestEntry.getValue() +  entry.getValue());
                    preceding.put(entryMatchCurrentCost.getKey(),lowestEntry.getKey());
                }
            }

        }
        doNotVisitAgain.add(lowestEntry.getKey());

        dijkstra(start, doNotVisitAgain, preceding, currentCost);

    }

    private void printThePath(Location locationFinish, Map<Location, Location> preceding) {
        for( Map.Entry<Location, Location> entry : preceding.entrySet() ) {
            if( entry.getKey().equals(locationFinish) ) {
                printThePath(entry.getValue(), preceding);
                System.out.print(" - " + entry.getKey().getName());
            }
        }
    }


    public void shortestPath(Location locationStart, Location locationFinish) {
        List<Location> doNotVisitAgain = new ArrayList<Location>();
        Map<Location, Location> preceding = new HashMap<Location, Location>();
        Map<Location, Integer> currentCost = new HashMap<Location, Integer>();

        List<Location> proxyLocationFromCity = city.getLocationList();

        //currentCost should be completed
        for( Location location : proxyLocationFromCity ) {
            if(!location.equals(locationStart)) {
                currentCost.put(location, 10_000);
            }

        }

        Map<Location, Integer> proxyMapFromLocationStart = locationStart.getMap();
        for( Map.Entry<Location, Integer> entry : proxyMapFromLocationStart.entrySet()) {
            preceding.put(entry.getKey(),locationStart);
            currentCost.put(entry.getKey(), entry.getValue());
        }



        this.dijkstra(locationStart, doNotVisitAgain, preceding, currentCost);
        System.out.println("Cost = " + currentCost.get(locationFinish));
        System.out.print(locationStart.getName());
        this.printThePath(locationFinish, preceding);
    }
}
