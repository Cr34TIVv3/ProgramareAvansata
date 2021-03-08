public class Student implements Comparable<Student> {
    private String name;
    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private static int label = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
        this.name = "S" + label++;
        this.grade = (int) (Math.random()*1000) % 10 + 1;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
}
