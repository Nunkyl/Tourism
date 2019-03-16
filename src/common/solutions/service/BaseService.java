package common.solutions.service;

import common.solutions.sequencegenerator.SequenceGenerator;

/**
 * Created by eliza on 26.02.19.
 */
public interface BaseService <T, ID> {

    void add(T entity);

    void update(T entity);

    T findByID(ID id);

    void delete(T entity);

    void deleteByID(ID id);

    void printAll();

    //void setSequenceGenerator(SequenceGenerator sequenceGenerator);
}

