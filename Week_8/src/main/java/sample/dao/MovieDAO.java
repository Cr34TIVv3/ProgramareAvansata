package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class MovieDAO implements DAO<Movie>{

    final private Connection connection;
    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getByField(String field, String value) throws Exception {
        String query;
        PreparedStatement statement;

        if(field == null) {
            query = "SELECT id, title, release_date, duration, score FROM movies";
            statement = connection.prepareStatement(query);
        }
        else {
            query = "SELECT id, title, release_date, duration, score FROM movies WHERE " + field + " = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
        }


        List<Movie> movies = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id              = parseInt(resultSet.getString("id"));
            String title        = resultSet.getString("title");
            String releaseDate  = resultSet.getString("release_date");
            int duration        = parseInt(resultSet.getString("duration"));
            float score         = parseFloat(resultSet.getString("score"));

            Movie movie = new Movie(id,title,releaseDate,duration,score);
            movies.add(movie);
        }
        statement.close();
        resultSet.close();
        return movies;
    }

    public List<Movie> getAll() throws Exception {
        return this.getByField(null, null);
    }

    public void add(Movie record) throws Exception {
        PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO movies VALUES(?, ?, ?, ?, ?)");

        statement.setInt(1, record.getId());
        statement.setString(2,record.getTitle());
        statement.setString(3,record.getReleaseDate());
        statement.setInt(4,record.getDuration());
        statement.setFloat(5,record.getScore());

        statement.executeQuery();
        statement.close();
    }

    public List<Movie> getMoviesByGenre (Genre genre) throws Exception {
        String query = "SELECT m.id, m.title, m.release_date, m.duration, m.score from movies m \n" +
                "    join genres_association a on m.id=a.id_movie\n" +
                "        join genres g on a.id_genre=" + genre.getId() + " \n" +
                "            WHERE g.type = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,genre.getType());

        List<Movie> movies = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id              = parseInt(resultSet.getString("id"));
            String title        = resultSet.getString("title");
            String releaseDate  = resultSet.getString("release_date");
            int duration        = parseInt(resultSet.getString("duration"));
            float score         = parseFloat(resultSet.getString("score"));

            Movie movie = new Movie(id,title,releaseDate,duration,score);
            movies.add(movie);
        }
        statement.close();
        resultSet.close();
        return movies;
    }

    public List<Movie> getMoviesByEmployee (Employee employee) throws Exception {
        String query = "SELECT m.id, m.title, m.release_date, m.duration, m.score from movies m\n" +
                "    join employees_association on m.id=id_movie\n" +
                "        join employees on id_employee= " + employee.getId() + "\n" +
                "            WHERE (name = ? AND surname = ? AND job_title = ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,employee.getName());
        statement.setString(2,employee.getSurname());
        statement.setString(3,employee.getJob());

        List<Movie> movies = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id              = parseInt(resultSet.getString("id"));
            String title        = resultSet.getString("title");
            String releaseDate  = resultSet.getString("release_date");
            int duration        = parseInt(resultSet.getString("duration"));
            float score         = parseFloat(resultSet.getString("score"));

            Movie movie = new Movie(id,title,releaseDate,duration,score);
            movies.add(movie);
        }
        statement.close();
        resultSet.close();
        return movies;
    }
}
