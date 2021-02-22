import java.util.Arrays;

public class Problem {

    private Source[] sources;
    private Destination[] destinations;
    private int[][] costs;

    public Problem(Source[] sources, Destination[] destinations, int[][] costs) {
        if (costs.length != sources.length) {
            throw new ArithmeticException("Bad height");
        } else if (costs[0].length != destinations.length) {
            throw new ArithmeticException("Bad width");
        } else {
            this.costs = costs;
            this.setSources(sources);
            this.setDestinations(destinations);
        }
    }

    private void printSources() {
        for (Source s : sources) {
            System.out.println(s.toString());
        }
    }

    private void printDestinations() {
        for (Destination d : destinations) {
            System.out.println(d.toString());
        }
    }

    private void printCosts() {
        System.out.println(Arrays.deepToString(costs));
    }

    public void printTheInstance() {
        System.out.println("The instance is: ");
        this.printSources();
        System.out.print("\n");
        this.printDestinations();
        System.out.print("\n");
        this.printCosts();
    }

    public Source[] getSources() {
        return sources;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }

    public Destination[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }

}
