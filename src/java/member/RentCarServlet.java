/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import bean.car;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import jdbc.JdbcUtility;

/**
 *
 * @author Arsalan
 */
@WebServlet(name = "RentCarServlet", urlPatterns = {"/RentCarServlet"})
public class RentCarServlet extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementSelectCarByAvailability();
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
            PreparedStatement ps = jdbcUtility.getPsSelectCarByAvailability();
            ps.setBoolean(1, true);
            //execute the statement
            ResultSet rs = ps.executeQuery();
            
            ArrayList<car> availcarlist = new ArrayList<car>(); 

            /*request.getRequestDispatcher("header.jsp").include(request, response);
            out.println("<h1>CURRENT BOOKINGS</h1>");
            out.println("<div style=\"padding: 25px\">");
            out.println("<table class=\"table table-striped\">");
            out.println("<thead style=\"font-weight: bold;\">");
            out.println("    <tr>");
            out.println("      <td style=\"border:1px solid black;\">ID</th>");
            out.println("      <td style=\"border:1px solid black;\">Name</th>");
            out.println("      <td style=\"border:1px solid black;\">Model</th>");
            out.println("      <td style=\"border:1px solid black;\">Color</th>");
            out.println("      <td style=\"border:1px solid black;\">Year</th>");
            out.println("      <td style=\"border:1px solid black;\">Price Per Day</th>");
            out.println("      <td style=\"border:1px solid black;\">Operations</th>");
            out.println("    </tr>");
            out.println("  </thead>");
            out.println("  <tbody>");*/

            while (rs.next()) {

                car _car = new car();
                _car.setId(rs.getInt("id"));
                _car.setName(rs.getString("name"));
                _car.setModel(rs.getString("model"));
                _car.setColor(rs.getString("color"));
                _car.setYear(rs.getInt("year"));
                _car.setPrice(rs.getDouble("price"));
                _car.setAvailability(rs.getBoolean("availability"));
                availcarlist.add(_car);
                /*int id = rs.getInt("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                String color = rs.getString("color");
                String year = rs.getString("year");
                String price = rs.getString("price");

                out.println("    <tr>");
                out.println("      <td style=\"border:1px solid black;\">" + id + "</td>");
                out.println("      <td style=\"border:1px solid black;\">" + name + "</td>");
                out.println("      <td style=\"border:1px solid black;\">" + model + "</td>");
                out.println("      <td style=\"border:1px solid black;\">" + color + "</td>");
                out.println("      <td style=\"border:1px solid black;\">" + year + "</td>");
                out.println("      <td style=\"border:1px solid black;\">" + price + "</td>");
                out.println("      <td style=\"border:1px solid black;\">"
                         + "<a href='RentCarDay.jsp?carId=" + id + "'>Rent Car</a>"
                        + "<br />"
                );
                out.println("    </tr>");*/
            }
            
            request.setAttribute("availcarlist", availcarlist);
            getServletConfig().getServletContext().getRequestDispatcher("/rentcar.jsp").forward(request,response);
                
            /*out.println("  </tbody>");
            out.println("</table>");
            out.println("</div>");
            
            request.getRequestDispatcher("footer.jsp").include(request, response);*/           
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
