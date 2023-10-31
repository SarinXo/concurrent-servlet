package logic.task;

import logic.singletone.BlokingQueueWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

public class GetCompletedTasks implements Task {

    private final static BlockingQueue<Future<String>> QUEUE = BlokingQueueWrapper.getInstance();
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        StringBuilder stringBuilder = new StringBuilder();
        var iterator = QUEUE.iterator();

        while (iterator.hasNext()) {
            Future<String> entry = iterator.next();
            if (entry.isDone()) {
                stringBuilder.append(entry.get()).append("\n");
                iterator.remove();
            }
        }
        return stringBuilder.append("end").toString();
    }
}
