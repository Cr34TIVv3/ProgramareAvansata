package sample.repository;

import sample.entities.Movie;

import javax.persistence.*;
import java.util.List;

public class MovieRepository implements RepositoryInterface<Movie, Integer>{
    final private EntityManagerFactory factory;

    public MovieRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Movie object) {

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(object);
            transaction.commit();
        }
        catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        manager.close();
    }

    @Override
    public Movie findObjectById(Integer identifier) {
        EntityManager manager = factory.createEntityManager();
        String query = "SELECT * FROM movies WHERE id = ?";
        Query query1 = manager.createNativeQuery(query, Movie.class);
        query1.setParameter(1,identifier);

        Movie output = (Movie) query1.getSingleResult();
        manager.close();
        return output;
    }

    @Override
    public Movie findObjectByName(String name) {
        EntityManager manager = factory.createEntityManager();
        Movie movie = null;

        movie = (Movie) manager.createNamedQuery("findByName")
                .setParameter("movie_title", name)
                .getSingleResult();

        manager.close();
        return movie;
    }

    @Override
    public List<Movie> getAllObjects() {
        EntityManager manager = factory.createEntityManager();
        String query = "SELECT * FROM movies";
        Query query1 = manager.createNativeQuery(query, Movie.class);

        List<Movie> output = query1.getResultList();
        return output;
    }
}
