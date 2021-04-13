package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EmployeeDAO implements DAO<Employee>{
    final private Connection connection;
    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getByField(String field, String value) throws Exception {
        String query;
        PreparedStatement statement;
        if(field == null) {
            query = "SELECT id, name, surname, job_title FROM employees";
            statement = connection.prepareStatement(query);
        }
        else {
            query = "SELECT id, name, surname, job_title FROM employees WHERE " + field + " = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
        }

        List<Employee> employees = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id           = parseInt(resultSet.getString("id"));
            String name      = resultSet.getString("name");
            String surname   = resultSet.getString("surname");
            String jobTitle  = resultSet.getString("job_title");

            Employee Employee = new Employee(id,name, surname, jobTitle);
            employees.add(Employee);
        }
        statement.close();
        resultSet.close();
        return employees;
    }

    public List<Employee> getAll() throws Exception {
        return this.getByField(null, null);
    }

    public void add(Employee record) throws Exception {
        PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO employees VALUES(?, ?, ?, ?)");

        statement.setInt(1, record.getId());
        statement.setString(2,record.getName());
        statement.setString(3,record.getSurname());
        statement.setString(4,record.getJob());

        statement.executeQuery();
        statement.close();
    }

    public List<Employee> getEmployeesByMovie    (Movie movie) throws Exception {
        String query = "SELECT e.id, e.name, e.surname, e.job_title FROM employees e\n" +
                "    join employees_association on e.id=id_employee\n" +
                "        join movies m on id_movie= " + movie.getId() + "\n" +
                "            WHERE m.title = ? AND m.release_date = ? AND m.duration = ? AND m.score = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,movie.getTitle());
        statement.setString(2,movie.getReleaseDate());
        statement.setInt(3,movie.getDuration());
        statement.setFloat(4,movie.getScore());

        List<Employee> employees = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id              = parseInt(resultSet.getString("id"));
            String name         = resultSet.getString("name");
            String surname      = resultSet.getString("surname");
            String jobTitle     = resultSet.getString("job_title");

            Employee employee = new Employee(id,name, surname, jobTitle);
            employees.add(employee);
        }
        statement.close();
        resultSet.close();
        return employees;
    }
}
