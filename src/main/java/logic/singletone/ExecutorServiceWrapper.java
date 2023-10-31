package logic.singletone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWrapper {

    private static final int NUM_THREADS = Math.max(Runtime.getRuntime().availableProcessors() - 1, 1);
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(NUM_THREADS);

    public static ExecutorService getInstance(){
        return EXECUTOR;
    }

}
