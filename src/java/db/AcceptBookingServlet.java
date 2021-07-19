/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.JdbcUtility;

/**
 *
 * @author arnob
 */
@WebServlet(name = "AcceptBookingServlet", urlPatterns = {"/AcceptBookingServlet"})
public class AcceptBookingServlet extends HttpServlet {
    
    private JdbcUtility jdbcUtility;

    @Override
    //this method run once only for the servlet lifecycle
    //when the servlet loaded the first time in Glassfish/Tomcat
    public void init() throws ServletException {

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
        jdbcUtility.prepareSQLStatementUpdateCurrentBookingStatus();
        jdbcUtility.prepareSQLStatementUpdateCarAvailability();
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
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        int carId = Integer.parseInt(request.getParameter("carid"));
        
        try {
            PreparedStatement ps = jdbcUtility.getPsUpdateCurrentBookingStatus();

            ps.setInt(1, 1);
            ps.setInt(2, bookingId);

            int bookingStatus = 0;
            bookingStatus = ps.executeUpdate();
            
            ps = jdbcUtility.getPsUpdateCarAvailability();
            ps.setBoolean(1, false);
            ps.setInt(2, carId);
            
            int carAvailabilityUpdate = 0;
            carAvailabilityUpdate = ps.executeUpdate();
            
            PrintWriter out = response.getWriter();

            out.println(bookingStatus);

            if (bookingStatus == 1 && carAvailabilityUpdate == 1) {
                out.println("<script>");
                out.println("    alert('Booking accepted successfully');");
                out.println("    window.location = 'ViewBookings'");
                out.println("</script>");
            }
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
            out.println("    alert('Booking opertiaon failed');");
            out.println("    window.location = 'ViewBookings'");
            out.println("</script>");
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Booking operation failed');");
            out.println("    window.location = 'ViewBookings'");
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
