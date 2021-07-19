/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.UserProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "UserBookings", urlPatterns = {"/UserBookings"})
public class UserBookings extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementSelectCurrentBookingByID();
        jdbcUtility.prepareSQLStatementSelectCarByID();

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
            UserProfile userprofile = (UserProfile) session.getAttribute("userprofile");
            int userId = Integer.parseInt(session.getAttribute("userid").toString());

            if (userprofile != null) {
                PreparedStatement pscd = jdbcUtility.getPsSelectCurrentBookingByID();
                pscd.setInt(1, userId);

                ResultSet rscd = pscd.executeQuery();

                int iC = 0;

                request.getRequestDispatcher("header.jsp").include(request, response);
                out.println("<br>");
                out.println("<br>");
                out.println("<div style=\"text-allign: center\">");
                out.println("<h3>CURRENT BOOKINGS</h3>");
                out.println("</div>");
                
                if (rscd.next() == false) {
                    out.println("<div style=\"padding: 25px\">");
                    out.println("<h4>No bookings to show</h4>");
                    out.println("</div>");

                } else {
                    out.println("<div style=\"padding: 25px\">");
                    out.println("<table class=\"table table-striped\">"
                            + "<thead style=\"font-weight: bold;\">"
                            + "<tr>"
                            + "<td style=\"border:1px solid black;\">No</td>"
                            + "<td style=\"border:1px solid black;\">Car Name</td>"
                            + "<td style=\"border:1px solid black;\">Model</td>"
                            + "<td style=\"border:1px solid black;\">Days</td>"
                            + "<td style=\"border:1px solid black;\">Price</td>"
                            + "<td style=\"border:1px solid black;\">Status</td>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>");
                    do {
                        PreparedStatement psxy = jdbcUtility.getPsSelectCarByID();
                        int carId = rscd.getInt("carId");
                        int status = rscd.getInt("status");
                        psxy.setInt(1, carId);
                        ResultSet rsxy = psxy.executeQuery();
                        rsxy.next();

                        iC++;
                        out.println("<tr>");
                        out.println("   <td style=\"border:1px solid black;\">" + iC + "</td>");
                        out.println("   <td style=\"border:1px solid black;\">" + rsxy.getString("name") + "</td>");
                        out.println("   <td style=\"border:1px solid black;\">" + rsxy.getString("model") + "</td>");
                        out.println("   <td style=\"border:1px solid black;\">" + rscd.getString("days") + "</td>");
                        out.println("   <td style=\"border:1px solid black;\">" + rsxy.getDouble("price") + "</td>");

                        switch (status) {
                            case 0:
                                out.println("   <td style=\"border:1px solid black;\">Pending</td>");
                                break;
                            case 1:
                                out.println("   <td style=\"border:1px solid black;\">Approved</td>");
                                break;
                            default:
                                out.println("   <td style=\"border:1px solid black;\">Declined</td>");
                                break;
                        }
                        out.println("</tr>");
                    } while (rscd.next());

                    out.println("</tbody>"
                            + "</table>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("</div>");
                }

                request.getRequestDispatcher("footer.jsp").include(request, response);

            } else {
                out.println("<script>");
                out.println("    alert('Session invalid/terminated - user logout!');");
                out.println("    window.location = 'home.jsp'");
                out.println("</script>");
            }
        } catch (Exception ex) {
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
