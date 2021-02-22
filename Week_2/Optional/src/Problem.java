import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {
    //mandatory parameters for solving the problem
    private Source[] sources;
    private Destination[] destinations;
    private int[][] costs;
    private Solution solution;

    /**
     *
     * @param sources Array of sources
     * @param destinations Array of destinations
     * @param costs Matrix of costs - it might throw an ArithmeticException if it doesn't respect the format
     */
    public Problem(Source[] sources, Destination[] destinations, int[][] costs) {
        if ( costs.length != sources.length ) {
            throw new ArithmeticException("Bad height");
        }
        else if ( costs[0].length != destinations.length ) {
            throw new ArithmeticException("Bad width");
        }
        else {
            this.costs = costs;
            this.setSources(sources);
            this.setDestinations(destinations);
        }
    }

    private Source[] deleteElementFromSources(Source[] array, int index) {
        Source[] proxy = new Source[array.length - 1];
        int k = 0;
        for ( int i = 0; i < array.length; i++ ) {
            if ( i != index ) {
                proxy[k++] = array[i];
            }
        }
        return proxy;
    }
    private void filterForSources(Source[] s) {
        for( int i = 0; i < s.length - 1; i++) {
            for ( int j = i + 1; j < s.length; j++) {
                if ( s[i].equals(s[j])  ) {
                    s = this.deleteElementFromSources(s, j);
                    j--;
                }
            }
        }
    }

    private Destination[] deleteElementFromDestinations(Destination[] array, int index) {
        Destination[] proxy = new Destination[array.length - 1];
        int k = 0;
        for ( int i = 0; i < array.length; i++ ) {
            if ( i != index ) {
                proxy[k++] = array[i];
            }
        }
        return proxy;
    }
    private void filterForDestinations(Destination[] s) {
        for( int i = 0; i < s.length - 1; i++) {
            for ( int j = i + 1; j < s.length; j++) {
                if ( s[i].equals(s[j])  ) {
                    s = this.deleteElementFromDestinations(s, j);
                    j--;
                }
            }
        }
    }

    private void printSources() {
        for ( Source s: sources  ) {
            System.out.println(s.toString());
        }
    }
    private void printDestinations() {
        for ( Destination d: destinations  ) {
            System.out.println(d.toString());
        }
    }
    private void printCosts() {
        System.out.println(Arrays.deepToString(costs));
    }


    private boolean sourcesAreDone(Source[] sources) {
        for ( Source s : sources ) {
            if ( s.getSupply() != 0 ) return false;
        }
        return true;
    }

    private boolean destinationsAreDone(Destination[] destinations) {
        for ( Destination d : destinations ) {
            if ( d.getDemand() != 0 ) return false;
        }
        return true;
    }

    /**
     * This method will solve the problem by using Least Cost Method
     * It will make some proxy data structures to store the instance
     * It weill use List to mark which index should not be visited in the cost matrix
     * This solution will choose the lowest cell in the matrix step by step in order to modify the data structures, updating the solution
     */
    public void solveUsingLeastCostMethod() {

        this.solution = new Solution();

        Source[] proxySources = Arrays.copyOf(sources, sources.length);
        Destination[] proxyDestinations = Arrays.copyOf(destinations, destinations.length);
        int[][] matrix = new int[costs.length][costs[0].length];

        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> columns = new ArrayList<Integer>();
        while ( rows.size() != proxySources.length && columns.size() != proxyDestinations.length ) {
            //getting minimum cell (if there is a tie, we choose where is the maximum quantity)
            int indexI,indexJ, value;
            indexI = 0;
            indexJ = 0;
            value = 0;
            boolean firstElement = true;
            for(int i=0;i<proxySources.length;i++) {
                if( !rows.contains(i) ) {
                    for(int j=0;j<proxyDestinations.length;j++) {
                        if( !columns.contains(j) ) {
                            if ( firstElement ) {
                                indexI = i;
                                indexJ = j;
                                value = costs[i][j];
                                firstElement = false;
                            }
                            if( costs[i][j] < value ) {
                                indexI = i;
                                indexJ = j;
                                value = costs[i][j];
                            }
                            else if( costs[i][j] == value ) {
                                if( Math.min(proxySources[i].getSupply(),proxyDestinations[j].getDemand()) > Math.min(proxySources[indexI].getSupply(),proxyDestinations[indexJ].getDemand()) ) {
                                    indexI = i;
                                    indexJ = j;
                                    value = costs[i][j];
                                }
                            }
                        }
                    }
                }
            }
            int supply = proxySources[indexI].getSupply();
            int demand = proxyDestinations[indexJ].getDemand();


            this.solution.add(sources[indexI], destinations[indexJ], Math.min(supply,demand), costs[indexI][indexJ]);

            if ( supply < demand ) {
                proxySources[indexI].setSupply(0);
                proxyDestinations[indexJ].setDemand(demand - supply);
                rows.add(indexI);
            }
            else if( supply > demand ) {
                proxySources[indexI].setSupply(supply-demand);
                proxyDestinations[indexJ].setDemand(0);
                columns.add(indexJ);
            }
            else {
                proxySources[indexI].setSupply(0);
                proxyDestinations[indexJ].setDemand(0);
                rows.add(indexI);
                columns.add(indexJ);
            }
        }
        this.solution.showSolution();


    }

    /**
     * It will print the instance in a specific format
     */
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
        this.filterForSources(sources);
        this.sources = Arrays.copyOf(sources, sources.length);
    }

    public Destination[] getDestinations() {
        return destinations;
    }
    public void setDestinations(Destination[] destinations) {
        this.filterForDestinations(destinations);
        this.destinations = Arrays.copyOf(destinations, destinations.length);
    }

}
