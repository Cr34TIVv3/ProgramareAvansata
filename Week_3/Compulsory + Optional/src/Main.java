import java.time.LocalTime;

public class Main {
    // locatii redenumite
    public static void main(String[] args) {
        Location v1, v2, v3, v4, v5, v6;
        v1 = new Hotel("Hotel",8);
        v2 = new Museum("Museum A",5,LocalTime.of(9,30),LocalTime.of(16, 0));
        v3 = new Museum("Museum B",10,LocalTime.of(10,0),LocalTime.of(20,0));
        v4 = new Church("Church A",LocalTime.of(8,0),LocalTime.of(15,30));
        v5 = new Church("Church B",LocalTime.of(9,30),LocalTime.of(15,45));
        v6 = new Restaurant("Restaurnat", 9, LocalTime.of(10,30), LocalTime.of(16,0));

        v1.addToMap(v2, 10);
        v1.addToMap(v3, 50);

        v2.addToMap(v3, 20);
        v2.addToMap(v4, 20);
        v2.addToMap(v5, 10);

        v3.addToMap(v2, 20);
        v3.addToMap(v4, 20);

        v4.addToMap(v5, 30);
        v4.addToMap(v6, 10);

        v5.addToMap(v6, 20);
        v5.addToMap(v4, 30);

        City city = new City("Narnia", 10);
        city.addLocation(v1);
        city.addLocation(v2);
        city.addLocation(v3);
        city.addLocation(v4);
        city.addLocation(v5);
        city.addLocation(v6);

        city.displayLocations();

        TravelPlan plan = new TravelPlan(city);
        plan.addLocation(v1);
        plan.addLocation(v2);
        plan.addLocation(v3);
        plan.addLocation(v4);
        plan.addLocation(v5);
        plan.addLocation(v6);

        plan.shortestPath(v2, v6);

    }
}
