package logic;

import logic.decorator.AddInExecution;
import logic.decorator.Decorator;
import logic.decorator.WriteResponse;
import logic.singletone.BlokingQueueWrapper;
import logic.task.FastTask;
import logic.task.GetCompletedTasks;
import logic.task.HeavyTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskHandler {
        //TreeMap
        // независимый регистр
    // планировщик (выполнение задач)
    private final static Map<String, Decorator> TASKS = new HashMap<>();

    // надо думать как заполнять в динамике
    // xml config - под вопросом
    static {
        TASKS.put("GET/test/heavy-task", new AddInExecution( new HeavyTask()) );
        TASKS.put("GET/test/fast-task", new AddInExecution ( new FastTask()) );
        TASKS.put("GET/test/take-all-completed-tasks", new WriteResponse( new GetCompletedTasks()) );
    }

    public static void get(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        Optional<Decorator> task = Optional.of(getCurrentTask(request));
        if(task.isPresent()) {
            task.get().result(request, response);
        }
    }

    private static Decorator getCurrentTask(HttpServletRequest request){
        return TASKS.get(getMethodLink(request));
    }

    private static String getMethodLink(HttpServletRequest request){
        return request.getMethod() + request.getRequestURI();
    }

}
