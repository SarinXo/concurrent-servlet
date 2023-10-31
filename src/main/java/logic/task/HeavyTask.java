package logic.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class HeavyTask implements Task{

    @Override
    public String execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        TimeUnit.SECONDS.sleep(10);
        return String.format("HeavyTask with id thread "
                                + Thread.currentThread().getName()
                                + " was successfully done!");
    }

}
