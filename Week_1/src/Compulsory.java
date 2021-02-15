public class Compulsory {
    //function that computes the digit sum
    public static int digitSum(int num) {
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String args[]) {

        //step 1
        System.out.println("Hello world!");

        //step 2
        //bibliography https://www.geeksforgeeks.org/arrays-in-java/
        String[] languages = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        //step 3
        int n = (int) (Math.random() * 1_000_000);

        //step 4
        //bibliography
        //  https://www.baeldung.com/java-binary-numbers
        //  https://www.javamex.com/tutorials/conversion/decimal_hexadecimal.shtml
        n = n * 3;
        int result = ( n + 0b10101 + 0xFF)*6;

        //step 5
        while(result >= 10){
            result = digitSum(result);
        }

        //step 6
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
