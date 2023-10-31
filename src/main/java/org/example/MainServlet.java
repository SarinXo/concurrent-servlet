package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static logic.TaskHandler.get;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response){
        try {
            get(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}