package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GenreDAO implements DAO<Genre>{
    final private Connection connection;
    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Genre> getByField(String field, String value) throws Exception {
        String query;
        PreparedStatement statement;

        if(field == null) {
            query = "SELECT id, type FROM genres";
            statement = connection.prepareStatement(query);
        }
        else {
            query = "SELECT id, type FROM genres WHERE " + field + " = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
        }


        List<Genre> genres = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id      = parseInt(resultSet.getString("id"));
            String type = resultSet.getString("title");

            Genre Genre = new Genre(id,type);
            genres.add(Genre);
        }
        statement.close();
        resultSet.close();
        return genres;
    }

    public List<Genre> getAll() throws Exception {
        return this.getByField(null, null);
    }

    public void add(Genre record) throws Exception {
        PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO movies VALUES(?, ?)");

        statement.setInt(1, record.getId());
        statement.setString(2,record.getType());

        statement.executeQuery();

        statement.close();
    }

    public List<Genre> getGenresByMovie (Movie movie) throws Exception {
        String query = "SELECT g.id, g.type from genres g\n" +
                "    join genres_association a on g.id=a.id_genre \n" +
                "        join movies m on a.id_movie= " + movie.getId() + "\n" +
                "            WHERE m.title = ? AND m.release_date = ? AND m.duration = ? AND m.score = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,movie.getTitle());
        statement.setString(2,movie.getReleaseDate());
        statement.setInt(3,movie.getDuration());
        statement.setFloat(4,movie.getScore());

        List<Genre> genres = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int id             = parseInt(resultSet.getString("id"));
            String type        = resultSet.getString("type");

            Genre genre = new Genre(id,type);
            genres.add(genre);
        }
        statement.close();
        resultSet.close();
        return genres;
    }
}
