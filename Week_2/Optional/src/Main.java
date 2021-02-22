import java.util.List;

public class Main {
    public static void main(String[] args) {

        Source[] sources = new Source[3];
        sources[0] = new Factory(10);
        sources[1] = new Warehouse(35);
        sources[2] = new Warehouse(25);

        Destination[] destinations = new Destination[3];
        destinations[0] = new Destination(20);
        destinations[1] = new Destination(25);
        destinations[2] = new Destination(25);

        int[][] costs = {
                { 2, 3, 1 },
                { 5, 4, 8 },
                { 5, 6, 8 }
        };

        try{
            Problem p = new Problem(sources, destinations, costs);
            p.printTheInstance();
            p.solveUsingLeastCostMethod();
        }
        catch (ArithmeticException e){
            System.out.println(e.toString());
        }
    }
}
