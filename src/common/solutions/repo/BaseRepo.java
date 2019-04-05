package common.solutions.repo;

import java.util.Collection;
import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public interface BaseRepo<T, ID> {

    void add(T entity);

    void add(Collection<T> entity);

    void update(T entity);

    T findByID(ID id);

    void deleteByID(ID ID);

    void printAll();

    List<T> findAll();
}
