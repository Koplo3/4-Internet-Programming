/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JdbcUtility;

/**
 *
 * @author arnob
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementInsertCurrentBooking();
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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            String username = session.getAttribute("fullname").toString();
            int carId = Integer.parseInt(request.getParameter("carId"));
            int days = Integer.parseInt(request.getParameter("days"));

            try {
                PreparedStatement ps = jdbcUtility.getPsInsertCurrentBooking();
                ps.setInt(1, userId);
                ps.setString(2, username);
                ps.setInt(3, carId);
                ps.setInt(4, days);

                int insertStatus = 0;
                insertStatus = ps.executeUpdate();

//              PrintWriter out = response.getWriter();
                out.println(insertStatus);

                if (insertStatus == 1) {
                    out.println("<script>");
                    out.println("    alert('Booking success');");
                    out.println("    window.location = 'UserBookings'");
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

//                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("    alert('Booking failed g');");
//                out.println("    window.location = '/myapp/register.html'");
                out.println("</script>");
            } catch (java.lang.Exception ex) {
                ex.printStackTrace();

//                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("    alert('Booking failed t');");
                out.println("    window.location = '/myapp/register.html'");
                out.println("</script>");
            }
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
