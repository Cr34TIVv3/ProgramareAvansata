package sample.repository;

import java.util.List;

public interface RepositoryInterface <T,ID>{
    void create(T object);
    T findObjectById(ID identifier);
    T findObjectByName(String name);
    List<T> getAllObjects();
}
