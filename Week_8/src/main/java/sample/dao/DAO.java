package sample.dao;

import java.rmi.server.ExportException;
import java.util.List;

public interface DAO<T> {

    List<T> getByField(String field, String value) throws Exception;
    List<T> getAll() throws Exception;
    void add(T record) throws Exception;
}
