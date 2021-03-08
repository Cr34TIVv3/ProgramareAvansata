import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class myTest {

    private Problem getInstance() {
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student())
                .toArray(Student[]::new);


        var studentList = new LinkedList<Student>();

        studentList.addAll(Arrays.asList(students));

        //--------------------------------------------------

        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> { if ( i == 0) return new School(1); else return new School(2);} )
                .toArray(School[]::new);

        var schoolList = new TreeSet<School>();
        schoolList.addAll(Arrays.asList(schools));

        //--------------------------------------------------

        var stdPrefMap = new TreeMap<Student, List<School>>();
        stdPrefMap.put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[2], Arrays.asList(schools[0], schools[1]));
        stdPrefMap.put(students[3], Arrays.asList(schools[0], schools[2]));

        var schPrefMap = new HashMap<School, List<Student>>();
        schPrefMap.put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
        schPrefMap.put(schools[1], Arrays.asList(students[0], students[2], students[1]));
        schPrefMap.put(schools[2], Arrays.asList(students[0], students[1], students[3]));

        Problem problem = new Problem(studentList, new ArrayList<>(schoolList), stdPrefMap, schPrefMap);

        return problem;
    }

    private Solution getExpectedResult(List<Student> studList, List<School> schList) {
        var solution = new Solution();
        var iterator = studList.iterator();
        for(var school : schList) {
            while(school.getCapacity() > 0) {
                if(!iterator.hasNext()) {
                    return solution;
                }
                solution.addMatch(iterator.next(), school);
                school.decrementCapacity();
            }
        }
        return solution;
    }

    @Test
    public void test1() { //it should pass
        Problem problem1 = getInstance();
        var expectedResults = getExpectedResult(problem1.getStudentList(), problem1.getSchoolList());

        assertEquals(problem1.solveRankingAdmissionProblem(), expectedResults);
    }

    @Test
    public void test2() { //it should fail
        Problem problem2 = getInstance();
        var expectedResults = getExpectedResult(problem2.getStudentList(), problem2.getSchoolList());

        assertEquals(problem2.solveRankingAdmissionProblem(), expectedResults);
    }
}