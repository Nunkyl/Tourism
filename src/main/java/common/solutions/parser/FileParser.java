package common.solutions.parser;

/**
 * Created by eliza on 06.04.19.
 */
@FunctionalInterface
public interface FileParser<EXTRACTED_DATA> {

    EXTRACTED_DATA parseFile(String file) throws Exception;
}