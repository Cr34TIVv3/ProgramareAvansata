public class Main {
    public static void main(String[] args) {

//        Source[] sources = new Source[3];
//        sources[0] = new Source(Source.SourceType.FACTORY, 10 );
//        sources[1] = new Source(Source.SourceType.WAREHOUSE, 35 );
//        sources[2] = new Source(Source.SourceType.WAREHOUSE, 25 );
//
//        Destination[] destinations = new Destination[3];
//        destinations[0] = new Destination(20);
//        destinations[1] = new Destination(25);
//        destinations[2] = new Destination(25);
//
//        int[][] costs = {
//                { 2, 3, 1 },
//                { 5, 4, 8 },
//                { 5, 6, 8 }
//        };
//
//        try{
//            Problem problem = new Problem(sources, destinations, costs);
//            problem.printTheInstance();
//        }
//        catch (ArithmeticException e){
//            System.out.println(e.toString());
//        }
        System.out.println("+node0");
        int numberLevels = (int)Math.random()+2;
        int numberSons = (int)Math.random()+2;
        int Sons = numberSons;
        for(int i=1;i<=numberSons;i++)
        {
            System.out.println("  +node" + i);
            numberSons=(int)Math.random()+2;
            int k = i;
            for(int x=1;x<=numberSons;x++)
            {
                k=k+2;
                System.out.println("    -node" + k);
            }
        }


    }
}
