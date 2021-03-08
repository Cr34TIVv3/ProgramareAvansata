import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student())
                .toArray(Student[]::new);


        var studentList = new LinkedList<Student>();



        // Collections.addAll(studentList, students);

        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList, Comparator.comparing(Student::getName));


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
        problem.solveRankingAdmissionProblem();
        problem.solveTheProblem();

        Faker faker = new Faker();
        Student stud = new Student();
        stud.setName(faker.name().firstName());

    }
}
