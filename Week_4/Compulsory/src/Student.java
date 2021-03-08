public class Student implements Comparable<Student> {
    private String name;

    private static int label = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
        this.name = "S" + label++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
}
