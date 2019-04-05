package storage.initiator;

import storage.initiator.inputdataparsers.FileParser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 27.03.19.
 */
public class ThreadInitiator  <T> implements Runnable {

    private String inputFile;
    private FileParser<List<T>> parser;
    private List<T> outputList;

    public ThreadInitiator(String inputFile, FileParser<List<T>> parser) {
        this.inputFile = inputFile;
        this.parser = parser;
    }

    @Override
    public void run() {
        try{
            outputList = parser.parseFile(inputFile);
        } catch (Exception e){
            System.out.println("Problems with parsing files!");
            e.printStackTrace();
        }

    }

    public void readDataFromFileInSeparateThread(){

        Thread thread = new Thread(this);
        thread.start();
    }

    /*
    public List<T> getOutputList(){
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }*/

}
