package logic.singletone;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

public class BlokingQueueWrapper {

    private static final BlockingQueue<Future<String>> QUEUE = new ArrayBlockingQueue<>(500);

    public static BlockingQueue<Future<String>> getInstance(){
        return QUEUE;
    }
}
