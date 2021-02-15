
public class Optional {

    public static void createRandomGraph(int [][]matrix, int n) {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if ( j > i )
                {
                    matrix[i][j] = ( (int) ( Math.random() * 100 ) ) % 2;
                }
                else if( i == j)
                {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = matrix[j][i];
                }
            }
        }
    }

    public static void displayMatrix(int [][]matrix, int n) {
        String solidBlock          = "\u2588";
        String top_left_corner     = "\u250C";
        String top_right_corner    = "\u2510";
        String bottom_left_corner  = "\u2514";
        String bottom_right_corner = "\u2518";

        String top_pipe   = "\u2574";
        String bottom_pipe= "\u2574";
        String left_pipe  = "\u23B8";
        String right_pipe = "\u23B9";


        System.out.print(solidBlock);

        for( int i=0; i<n; i++ ) {
            System.out.print(" " + i);
        }
        System.out.print(" \n");

        for( int i=0; i<n; i++ ) {
            if ( i == 0 ) {
                System.out.print(" ");
                System.out.print(top_left_corner);

                for ( int k=0; k<n+n-1; k++) {
                    System.out.print(top_pipe);
                }
                System.out.print(top_right_corner + '\n');
            }
            else if ( i == n-1 ) {
                System.out.print(" ");
                System.out.print(left_pipe);

                for ( int k=0; k<n+n-1; k++) {
                    System.out.print(" ");
                }
                System.out.print(right_pipe + '\n');



                System.out.print(i);
                for ( int j=0; j<n;  j++) {
                    if( j == 0 ) {
                        System.out.print(left_pipe + matrix[i][j]);
                    }
                    else {
                        System.out.print(" " + matrix[i][j]);
                    }
                }
                System.out.print(right_pipe + '\n');


                System.out.print(" ");
                System.out.print(bottom_left_corner);

                for ( int k=0; k<n+n-1; k++) {
                    System.out.print(bottom_pipe);
                }
                System.out.print(bottom_right_corner + '\n');

                return;
            }
            else {
                System.out.print(" ");
                System.out.print(left_pipe);

                for ( int k=0; k<n+n-1; k++) {
                    System.out.print(" ");
                }
                System.out.print(right_pipe + '\n');
            }


            System.out.print(i);
            for ( int j=0; j<n;  j++) {
                if( j == 0 ) {
                    System.out.print(left_pipe + matrix[i][j]);
                }
                else {
                    System.out.print(" " + matrix[i][j]);
                }
            }
            System.out.print(right_pipe + '\n');
        }
    }

    static int label = 1;

    public static void dfsComponents(int i, int[] visited, int[][] matrix, int n) {
        visited[i] = label;
        for (int j=0; j<n; j++ ) {
            if(matrix[i][j] == 1 && visited[j] == 0) {
                dfsComponents(j, visited, matrix, n);
            }
        }
    }

    public static void displayComponents(int [][]matrix, int n) {
        int []visited = new int[n];

        for( int i=0; i<n; i++) {
            visited[i] = 0;
        }

        for( int i=0; i<n; i++) {
            if(visited[i] == 0) {
                dfsComponents(i, visited, matrix, n);
                label++;
            }
        }

        if(label != 2){
            System.out.println("Graph is not connected!");
        }
        else {
            System.out.println("Graph is connected!");
            displaySP(matrix, n);
            return;
        }

        for( int i=0; i<n; i++) {
            System.out.print(visited[i] + " ");
        }

    }

    public static void DFS_sp(int i, int[] visited, int[][] matrix, int n, int[][] SPmatrix) {
        visited[i] = 1;
        for (int j=0; j<n; j++ ) {
            if(matrix[i][j] == 1 && visited[j] == 0) {
                SPmatrix[i][j] = SPmatrix[j][i] = 1;
                DFS_sp(j, visited, matrix, n, SPmatrix);
            }
        }
    }

    public static void displaySP(int [][]matrix, int n) {
        int [][]SPmatrix = new int[n][n];
        int []visited = new int[n];
        DFS_sp(0, visited, matrix, n, SPmatrix);
        displayMatrix(SPmatrix, n);
    }


    public static void main(String[] args) {
        //bibliography https://www.javatpoint.com/java-string-to-int

        long startTime = System.nanoTime();

        if ( args.length < 1 ) {
            System.out.println("One argument needed!");
            System.exit(1);
        }
        else{
            int n;
            try{
                n = Integer.parseInt(args[0]);
                if( n % 2 != 1 ) {
                    System.out.println("The argument must be odd!");
                    System.exit(1);
                }
                else {
                    int [][]matrix = new int[n][n];
                    createRandomGraph(matrix, n);
                    displayMatrix(matrix, n);
                    displayComponents(matrix, n);
                }
            }
            catch (NumberFormatException x)
            {
                System.out.printf("Invalid");
            }
        }

        long stopTime = System.nanoTime();

        System.out.println("Timing = " + (stopTime - startTime) );

    }
}
