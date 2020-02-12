package edu.wctc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private final String DRIVER_NAME = "jdbc:derby:";
    private final String DATABASE_PATH = "../../db";
    private final String SCHEMA = "jake";
    private final String PASSWORD = "jake";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            String searchTerm = request.getParameter("speciesName");

            // Load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Find the absolute path of the database folder
            String absPath = getServletContext().getRealPath("/") + DATABASE_PATH;

//            StringBuilder sql = new StringBuilder("SELECT pet.nm, pet.age, food.nm");
//            sql.append(" FROM pet_food, pet, food");
//            sql.append(" WHERE pet_food.pet_id = pet.pet_id");
//            sql.append(" AND pet_food.food_id = food.food_id");
//            sql.append(" AND pet.species_nm = ?");
//            sql.append(" ORDER BY pet.nm"); // Don't end SQL with semicolon!

            // Build the query as a String
            StringBuilder sql = new StringBuilder("select name, age, favoritetoy, weight, nickname ");
            sql.append("from pet ");
            sql.append("join pet_detail on (pet.petid = pet_detail.pet_id)");
            sql.append("where speciesname = ?"); // Don't end SQL with semicolon!

            // Create a connection
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, SCHEMA, PASSWORD);
            // Create a statement to execute SQL
            pstmt = conn.prepareStatement(sql.toString());
            // Fill the prepared statement params
            pstmt.setString(1, searchTerm);
            // Execute a SELECT query and get a result set
            rset = pstmt.executeQuery();

            // Create a StringBuilder for ease of appending strings
            StringBuilder output = new StringBuilder();

            // HTML to create a simple web page
            output.append("<html><head><link type='text/css' rel='stylesheet' href='css/style.css'></head>");
            output.append("<body>");

            // Start the table
            output.append("<table>");
            // Start a row
            output.append("<tr>");
            // Add the headers
            output.append("<th>Name</th><th>Age</th><th>Favorite Toy</th><th>Weight</th><th>Nickname</th>");
            // End the row
            output.append("</tr>");

            // Loop while the result set has more rows
            while (rset.next()) {
                // Start a row
                output.append("<tr>");
                // Get the first string (the pet name) from each record
                String petName = rset.getString(1);
                // Add a cell with the info
                output.append("<td>" + petName + "</td>");

                // Get the rest of the pet data and append likewise
                int age = rset.getInt(2);
                output.append("<td>" + age + "</td>");
                String favToy = rset.getString(3);
                output.append("<td>" + favToy + "</td>");
                int weight = rset.getInt(4);
                output.append("<td>" + weight + "</td>");
                String nickname = rset.getString(5);
                output.append("<td>" + nickname + "</td>");

                // End the row
                output.append("</tr>");
            }

            // Close all those opening tags
            output.append("</table></body></html>");

            // Send the HTML as the response
            response.setContentType("text/html");
            response.getWriter().print(output.toString());

        } catch (SQLException | ClassNotFoundException e) {
            // If there's an exception locating the driver, send IT as the response
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
