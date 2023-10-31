package logic.decorator;

import logic.singletone.BlokingQueueWrapper;
import logic.singletone.ExecutorServiceWrapper;
import logic.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AddInExecution extends Decorator{

    private final static BlockingQueue<Future<String>> QUEUE = BlokingQueueWrapper.getInstance();
    private static final ExecutorService EXECUTOR = ExecutorServiceWrapper.getInstance();

    public AddInExecution(Task task) {
        super(task);
    }

    @Override
    public void result(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        QUEUE.put(
                EXECUTOR.submit(() -> {
                    try {
                        return task.execute(request, response);
                    } catch (Exception e) {
                        response.getWriter().println("error 0_o try later");
                        throw new RuntimeException(e);
                    }
                })
        );
    }
}
