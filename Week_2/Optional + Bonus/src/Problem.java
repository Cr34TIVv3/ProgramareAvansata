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

    public void solveUsingVogelApproximationMethod() {
        this.solution = new Solution();

        Source[] proxySources = Arrays.copyOf(sources, sources.length);
        Destination[] proxyDestinations = Arrays.copyOf(destinations, destinations.length);
        int[][] matrix = new int[costs.length][costs[0].length];

        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> columns = new ArrayList<Integer>();

        while ( rows.size() != proxySources.length && columns.size() != proxyDestinations.length ) {
            int[] penaltyRow = new int[proxyDestinations.length];
            int[] penaltyColumn = new int[proxySources.length];

            int firstTwo = 2;
            int firstValue = 0, secondValue = 0;
            for (int i = 0; i < proxySources.length; i++) {
                if (!rows.contains(i)) {
                    for (int j = 0; j < proxyDestinations.length; j++) {
                        if (!columns.contains(j)) {
                            if (firstTwo == 2) {
                                firstValue = costs[i][j];
                                firstTwo--;
                            } else if (firstTwo == 1) {
                                secondValue = costs[i][j];
                                firstTwo--;
                            } else if (firstTwo == 0) {
                                if (costs[i][j] < firstValue || costs[i][j] < secondValue) {
                                    if (firstValue < secondValue) secondValue = costs[i][j];
                                    else firstValue = costs[i][j];
                                }
                            }
                        }
                    }
                    penaltyColumn[i] = Math.abs(firstValue - secondValue);
                    firstTwo = 2;
                }
            }

            firstTwo = 2;

            for (int j = 0; j < proxySources.length; j++) {
                if (!columns.contains(j)) {
                    for (int i = 0; i < proxyDestinations.length; i++) {
                        if (!rows.contains(i)) {
                            if (firstTwo == 2) {
                                firstValue = costs[i][j];
                                firstTwo--;
                            } else if (firstTwo == 1) {
                                secondValue = costs[i][j];
                                firstTwo--;
                            } else if (firstTwo == 0) {
                                if (costs[i][j] < firstValue || costs[i][j] < secondValue) {
                                    if (firstValue < secondValue) secondValue = costs[i][j];
                                    else firstValue = costs[i][j];
                                }
                            }
                        }

                    }
                    penaltyRow[j] = Math.abs(firstValue - secondValue);
                    firstTwo = 2;
                }
            }

            int maxI = 0;
            for(int j=0;j<proxyDestinations.length;j++) {
                if(penaltyRow[j] > penaltyRow[maxI]) maxI = j;
            }

            int maxJ = 0;
            for(int i=0;i<proxySources.length;i++) {
                if(penaltyColumn[i] > penaltyColumn[maxJ]) maxJ = i;
            }

            if( penaltyRow[maxI] > penaltyColumn[maxJ] )
            {
                int indexJ = maxI;

                int finalIndexI = 0;
                int finalIndexJ = 0;
                int value = 100000;
                for(int i=0;i<proxySources.length;i++) {
                    if(!rows.contains(i)) {
                        if(costs[i][indexJ] < value) {
                            value = costs[i][indexJ];
                            finalIndexI = i;
                            finalIndexJ = indexJ;
                        }
                        else if( costs[i][indexJ] == value ) {
                            if( Math.min(proxySources[i].getSupply(),proxyDestinations[indexJ].getDemand()) > Math.min(proxySources[finalIndexI].getSupply(),proxyDestinations[finalIndexJ].getDemand()) ) {
                                value = costs[i][indexJ];
                                finalIndexI = i;
                                finalIndexJ = indexJ;
                            }
                        }
                    }
                }

                int supply = proxySources[finalIndexI].getSupply();
                int demand = proxyDestinations[finalIndexJ].getDemand();


                this.solution.add(sources[finalIndexI], destinations[finalIndexJ], Math.min(supply,demand), costs[finalIndexI][finalIndexJ]);

                if ( supply < demand ) {
                    proxySources[finalIndexI].setSupply(0);
                    proxyDestinations[finalIndexJ].setDemand(demand - supply);
                    rows.add(finalIndexI);
                }
                else if( supply > demand ) {
                    proxySources[finalIndexI].setSupply(supply-demand);
                    proxyDestinations[finalIndexJ].setDemand(0);
                    columns.add(finalIndexJ);
                }
                else {
                    proxySources[finalIndexI].setSupply(0);
                    proxyDestinations[finalIndexJ].setDemand(0);
                    rows.add(finalIndexI);
                    columns.add(finalIndexJ);
                }
            }
            else {
                int indexI = maxJ;

                int finalIndexI = 0;
                int finalIndexJ = 0;
                int value = 100000;
                for(int j=0;j<proxyDestinations.length;j++) {
                    if(!columns.contains(j)) {
                        if(costs[indexI][j] < value) {
                            value = costs[indexI][j];
                            finalIndexI = indexI;
                            finalIndexJ = j;
                        }
                        else if( costs[indexI][j] == value ) {
                            if( Math.min(proxySources[indexI].getSupply(),proxyDestinations[j].getDemand()) > Math.min(proxySources[finalIndexI].getSupply(),proxyDestinations[finalIndexJ].getDemand()) ) {
                                value = costs[indexI][j];
                                finalIndexI = indexI;
                                finalIndexJ = j;
                            }
                        }
                    }
                }

                int supply = proxySources[finalIndexI].getSupply();
                int demand = proxyDestinations[finalIndexJ].getDemand();


                this.solution.add(sources[finalIndexI], destinations[finalIndexJ], Math.min(supply,demand), costs[finalIndexI][finalIndexJ]);

                if ( supply < demand ) {
                    proxySources[finalIndexI].setSupply(0);
                    proxyDestinations[finalIndexJ].setDemand(demand - supply);
                    rows.add(finalIndexI);
                }
                else if( supply > demand ) {
                    proxySources[finalIndexI].setSupply(supply-demand);
                    proxyDestinations[finalIndexJ].setDemand(0);
                    columns.add(finalIndexJ);
                }
                else {
                    proxySources[finalIndexI].setSupply(0);
                    proxyDestinations[finalIndexJ].setDemand(0);
                    rows.add(finalIndexI);
                    columns.add(finalIndexJ);
                }
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
