package storage.initiator;

import java.util.List;

/**
 * Created by eliza on 27.03.19.
 */
public class ThreadInitiator <T> {

    private String inputFile;
    private List<T> outputList;

    public ThreadInitiator(String inputFile) {
        this.inputFile = inputFile;
    }


}
