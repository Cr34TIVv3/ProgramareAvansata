import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> stringList;
    private String finalResult;
    int sum;

    public Solution() {
        stringList = new ArrayList<String>();
        sum = 0;
    }

    /**
     * This method will add a string into the list
     * @param s The source
     * @param d The destination
     * @param units Units of a product
     * @param cost Cost of transport
     */
    public void add(Source s, Destination d, int units, int cost) {
        String output = s.getName() + "->" + d.getName() + ": " + units + " units * cost " + cost + " = " + units*cost;
        sum += units*cost;

        stringList.add(output);
        finalResult = "Total cost: " + sum;
    }

    public void showSolution() {
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println(finalResult);
    }
}
