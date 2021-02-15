public class Bonus {
    //bibliography https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
    static int label = 0;

    public static void printRandomTree(int curr_level, int max_nodes, int max_level) {
        String indent = "";
        for(int i=1;i<=curr_level*2;i++) {
            indent += " ";
        }

        System.out.println(indent + "+node" + label);
        label++;

        int nodes = (int) (Math.random() * 1_000) % (max_nodes+1);

        if( curr_level <= max_level )
        for(int i = 1; i <= nodes; i++) {
            printRandomTree(curr_level+1, max_nodes, max_level);
        }

    }

    public static void main(String[] args) {
        int maxNodes = 2;
        int maxLevel = 4;
        printRandomTree(1, maxNodes, maxLevel);
    }
}
