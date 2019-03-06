package common.business.service;

import common.solutions.utils.sequencegenerator.SequenceGenerator;

/**
 * Created by eliza on 26.02.19.
 */
public interface BaseService {

    void deleteByID(Integer id);

    void printAll();

    void setSequenceGenerator(SequenceGenerator sequenceGenerator);
}

