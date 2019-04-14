package common.solutions.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by eliza on 26.02.19.
 */
public interface BaseService <T, ID> {

    T add(T entity);

    void add(Collection<T> entity);

    void update(T entity);

    T findByID(ID id);

    void delete(T entity);

    void deleteByID(ID id);

    void printAll();

    List<T> findAll();
}

