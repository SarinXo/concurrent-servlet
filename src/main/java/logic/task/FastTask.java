package logic.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FastTask implements Task{
    @Override
    public String execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        return String.format("FastTask with id thread "
                            + Thread.currentThread().getName()
                            + " was successfully done!");
    }
}
