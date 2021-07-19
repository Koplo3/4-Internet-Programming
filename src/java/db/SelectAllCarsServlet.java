/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import jdbc.JdbcUtility;

/**
 *
 * @author Arsalan
 */
@WebServlet(name = "SelectAllCarsServlet", urlPatterns = {"/SelectAllCarsServlet"})
public class SelectAllCarsServlet extends HttpServlet {

    private JdbcUtility jdbcUtility;

    @Override
    //this method run once only for the servlet lifecycle
    //when the servlet loaded the first time in Glassfish/Tomcat
    public void init() throws ServletException {

        System.out.println("SelectAllCars: Execute init method");

        String driver = "com.mysql.jdbc.Driver";

        String dbName = "scsj3303";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JdbcUtility(driver,
                url,
                userName,
                password);

        jdbcUtility.jdbcConnect();

        //prepare the statement once only
        //for the entire servlet lifecycle
        jdbcUtility.prepareSQLStatementSelectAllCars();
    }

    @Override
    //this method run once only for the servlet lifecycle
    //when the servlet unloaded in application server (Glassfish/Tomcat)
    //or when the application server shutdown
    public void destroy() {
        jdbcUtility.jdbcConClose();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //get the prepared statement that is needed
            PreparedStatement ps = jdbcUtility.getPsSelectAllCars();
            //execute the statement
            ResultSet rs = ps.executeQuery();

            //generate output for browser
            //use variable out to send table output to browser
            //NO NEED TO INSERT A CAR HERE BECAUSE THIS IS FOR THE USERR AND USER SEES TEH AVAILABLE CARS
//            out.println("<p><a href='/myapp/InsertCar.html'>Insert a Car</a></p>");
            out.println("<table border='1'>");
            out.println("  <thead>");
            out.println("    <tr>");
            out.println("      <th>Name</th>");
            out.println("      <th>Model</th>");
            out.println("      <th>Color</th>");
            out.println("      <th>Year</th>");
            out.println("      <th>Price</th>");
            out.println("      <th>Operations</th>");
            out.println("    </tr>");
            out.println("  </thead>");
            out.println("  <tbody>");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                String color = rs.getString("color");
                String year = rs.getString("year");
                String price = rs.getString("price");

                out.println("    <tr>");
                out.println("      <td>" + name + "</td>");
                out.println("      <td>" + model + "</td>");
                out.println("      <td>" + color + "</td>");
                out.println("      <td>" + year + "</td>");
                out.println("      <td>" + price + "</td>");

                out.println("      <td>"
                        //Here we might change it to RENT CAR servlet and just go with Rent cause it is
                        //for the user and user can only rent a car not update
                        + "<a href='UpdateCarServlet?id=" + id + "'>Update</a>"
                        + "<br />"
                        + "<a href='DeleteCarServlet?id=" + id + "'>Delete</a>"
                        + "</td>");

                out.println("    </tr>");
            }

            out.println("  </tbody>");
            out.println("</table>");
            out.println("<br>");
            out.println("<p>Go to <a href='home.jsp'>Home</a></p>");
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message:  " + ex.getMessage());
                System.out.println("Vendor:   " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Select Car failed');");
            out.println("</script>");
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Select Car failed');");
            out.println("</script>");
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
