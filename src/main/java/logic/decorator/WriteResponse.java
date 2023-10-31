package logic.decorator;

import logic.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteResponse extends Decorator{
    public WriteResponse(Task task) {
        super(task);
    }

    @Override
    public void result(HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        response.getWriter().print(task.execute(request, response));
    }
}
