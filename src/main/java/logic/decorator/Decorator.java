package logic.decorator;

import logic.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public abstract class Decorator{
    protected Task task;

    public Decorator(Task task) {
        if(Objects.nonNull(task))
            this.task = task;
        else
            throw new NullPointerException("task can't be null");
    }

    public abstract void result(HttpServletRequest request,
                                HttpServletResponse response) throws Exception;
}