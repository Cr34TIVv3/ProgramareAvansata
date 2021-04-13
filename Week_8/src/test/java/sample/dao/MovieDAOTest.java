package sample.dao;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieDAOTest {

    @Test
    void getByField() throws Exception{
        DataBaseConnection connection = DataBaseConnection.getInstance();
        MovieDAO implementation = new MovieDAO(connection.getConnection());

        List<Movie> output = implementation.getByField("id","1");

        assertEquals("asd", output.get(0).getTitle());
    }
}