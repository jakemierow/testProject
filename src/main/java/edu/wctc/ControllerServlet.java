package edu.wctc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String desiredPage = request.getParameter("Page");
        switch(desiredPage)
        {
            case "2":
                request.getRequestDispatcher("two.jsp").forward(request, response);
                break;
            case "3":
                request.getRequestDispatcher("three.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("notfound.jsp").forward(request, response);
        }

    }


}
