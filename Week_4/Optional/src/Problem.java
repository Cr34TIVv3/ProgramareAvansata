import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Problem {
    private List<Student> studentList;
    private List<School> schoolList;
    private Map<Student, List<School>> studentListMap;
    private Map<School, List<Student>> schoolListMap;

    public Problem(List<Student> studentList, List<School> schoolList, Map<Student, List<School>> studentListMap, Map<School, List<Student>> schoolListMap) {
        this.studentList = studentList;
        this.schoolList = schoolList;
        this.studentListMap = studentListMap;
        this.schoolListMap = schoolListMap;
    }
    //
    public void displayInstance() {
        System.out.println();
        for(School s : schoolList) {
            System.out.println(s);
            studentList.stream()
                    .filter(std -> studentListMap.get(std).contains(s))
                    .forEach(System.out::println);
            System.out.println();
        }

        System.out.println();
        for(Student s : studentList) {
            System.out.println(s);
            schoolList.stream()
                    .filter(sch -> schoolListMap.get(sch).contains(s))
                    .forEach(System.out::println);
            System.out.println();
        }
    }


    private List<School> getSchoolsMatch(Student theStudent, Map<School, List<Student>> proxySchoolListMap) {
        int priorityMax = 100;

        List<School> output = new ArrayList<>();

        for(var x : proxySchoolListMap.entrySet()) {
            int priority = 0;
            for(var y : x.getValue()) {
                if(y.equals(theStudent) && x.getKey().getCapacity() > 0) {
                    if ( priority == priorityMax ) {
                        priorityMax = priority;
                        output.add(x.getKey());
                    }
                    else if( priority < priorityMax ) {
                        priorityMax = priority;
                        output.clear();
                        output.add(x.getKey());
                    }
                    break;
                }
                priority++;
            }
        }
        return output;
    }

    private void decrementCapacitySchool(School theSchool, Map<School, List<Student>> proxySchoolListMap) {
        for( var x : proxySchoolListMap.entrySet()) {
            if ( x.getKey().equals(theSchool) ) {
                x.getKey().decrementCapacity();
            }
        }
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Solution solveRankingAdmissionProblem() {
        var solution = new Solution();
        var proxyStudentList = new ArrayList<Student>(studentList);
        var proxySchoolList = new ArrayList<School>(schoolList);

        var it = proxyStudentList.iterator();

        for( var school : proxySchoolList) {
            while(school.getCapacity() > 0) {
                if(!it.hasNext()) {
                    solution.printSolution();
                    return solution;
                }
                Student stud = it.next();
                solution.addMatch(stud, school);
                school.decrementCapacity();
            }
        }
        return solution;
    }

    public void solveTheProblem() {
        var solution = new Solution();
        var proxyStudentListMap = new HashMap<Student, List<School>>(studentListMap);
        var proxySchoolListMap = new HashMap<School, List<Student>>(schoolListMap);

        for(var stud : studentListMap.entrySet()) {
            Student theStudent = stud.getKey();
            School theSchool = null;
            List<School> maybeMatchSchool = getSchoolsMatch(theStudent, proxySchoolListMap);

            for(var school : stud.getValue()) {
                if ( maybeMatchSchool.contains(school) ) {
                    theSchool = school;
                    break;
                }
            }
            solution.addMatch(theStudent, theSchool);
            decrementCapacitySchool(theSchool, proxySchoolListMap);
            solution.addMatch(theStudent, theSchool);
        }
        solution.printSolution();
    }
}
