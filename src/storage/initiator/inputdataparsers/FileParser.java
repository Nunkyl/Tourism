package storage.initiator.inputdataparsers;

/**
 * Created by eliza on 22.03.19.
 */
public interface FileParser<T> {
    T parseFile(String file) throws Exception;
}
