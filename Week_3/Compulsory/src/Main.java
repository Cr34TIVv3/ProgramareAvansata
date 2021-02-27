public class Main {
    public static void main(String[] args) {
        Location v1, v2, v3, v4, v5, v6;
        v1 = new Hotel("Hotel",8);
        v2 = new Museum("Museum A",5,9,16);
        v3 = new Museum("Museum B",10,10,20);
        v4 = new Church("Church A",8,15);
        v5 = new Church("Church B",9,15);
        v6 = new Restaurant("Restaurnat", 9, 10, 16);

        v1.addToMap(v2.getName(), 10);
        v1.addToMap(v3.getName(), 50);

        v2.addToMap(v3.getName(), 20);
        v2.addToMap(v4.getName(), 20);
        v2.addToMap(v5.getName(), 10);

        v3.addToMap(v2.getName(), 20);
        v3.addToMap(v4.getName(), 20);

        v4.addToMap(v5.getName(), 30);
        v4.addToMap(v6.getName(), 10);

        v5.addToMap(v6.getName(), 20);
        v5.addToMap(v4.getName(), 30);


    }
}
