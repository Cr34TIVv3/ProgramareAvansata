import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Student, School> matching;

    public Solution() {
        matching = new HashMap<>();
    }

    public void addMatch(Student student, School school) {
        matching.put(student, school);
    }

    public void printSolution() {
        for( var entry : matching.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
