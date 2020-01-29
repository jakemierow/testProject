package edu.wctc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ListServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");


            String absPath = getServletContext().getRealPath("/") + "../../db";

            conn = DriverManager.getConnection("jdbc:derby:" + absPath, "jake", "jake");

            stmt = conn.createStatement();

            rset = stmt.executeQuery("select name, age, speciesname from pet");


            StringBuilder sb = new StringBuilder("<html><body>");
            sb.append("<ui>");
            while (rset.next()) {
                sb.append("<li");
                String name = rset.getString("speciesname");
                int age = rset.getInt("age");
                String species = rset.getString(3);
                sb.append(name + "," + age + "," + "," + species);
                sb.append("<li>");
            }
            sb.append("</ul");
            sb.append("</body></html>");

            response.setContentType("text/html");

            response.getWriter().print(sb.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


