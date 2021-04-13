package sample.dao;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String jobTitle;

    public Employee(int id, String name, String surname, String jobTitle) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return jobTitle;
    }

    public void setJob(String job) {
        this.jobTitle = job;
    }
}
