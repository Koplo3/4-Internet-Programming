/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.UserProfile;
import bean.car;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import jdbc.JdbcUtility;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CarServlet", urlPatterns = {"/CarServlet"})
public class CarServlet extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementSelectAllCar();
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
        try {
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            UserProfile userprofile = (UserProfile) session.getAttribute("userprofile");
            if (userprofile != null) {
                /*request.getRequestDispatcher("header.jsp").include(request, response);
                out.println("<h1>AVAILABLE CARS </h1>");
                out.println("<div style=\"padding: 25px\">");
                out.println("<table class=\"table table-striped\">"
                        + "<thead style=\"font-weight: bold;\">"
                        + "<tr>"
                        + "<td style=\"border:1px solid black;\">No</td>"
                        + "<td style=\"border:1px solid black;\">Name</td>"
                        + "<td style=\"border:1px solid black;\">Model</td>"
                        + "<td style=\"border:1px solid black;\">Color</td>"
                        + "<td style=\"border:1px solid black;\">Year</td>"
                        + "<td style=\"border:1px solid black;\">Price</td>"
                        + "<td style=\"border:1px solid black;\">Availability</td>"
                        + "<td colspan=\"2\" style=\"border:1px solid black; text-align: center\">Operation</td>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>");*/

                PreparedStatement pscd = jdbcUtility.getPsSelectAllCar();
                ResultSet rscd = pscd.executeQuery();
                //int iC = 0;
                ArrayList<car> carlist = new ArrayList<car>();
                while (rscd.next()) {
                    car _car = new car();
                    _car.setId(rscd.getInt("id"));
                    _car.setName(rscd.getString("name"));
                    _car.setModel(rscd.getString("model"));
                    _car.setColor(rscd.getString("color"));
                    _car.setYear(rscd.getInt("year"));
                    _car.setPrice(rscd.getDouble("price"));
                    _car.setAvailability(rscd.getBoolean("availability"));
                    carlist.add(_car);
                    /*boolean availability = rscd.getBoolean("availability");

                    iC++;
                    out.println("<tr>");
                    out.println("   <td style=\"border:1px solid black;\">" + iC + "</td>");
                    out.println("   <td style=\"border:1px solid black;\">" + rscd.getString("name") + "</td>");
                    out.println("   <td style=\"border:1px solid black;\">" + rscd.getString("model") + "</td>");
                    out.println("   <td style=\"border:1px solid black;\">" + rscd.getString("color") + "</td>");
                    out.println("   <td style=\"border:1px solid black;\">" + rscd.getInt("year") + "</td>");
                    out.println("   <td style=\"border:1px solid black;\">" + rscd.getDouble("price") + "</td>");

                    if (availability) {
                        out.println("   <td style=\"border:1px solid black;\">Yes</td>");
                    } else {
                        out.println("   <td style=\"border:1px solid black;\">No</td>");
                    }

                    out.println("   <td style=\"border:1px solid black;\"><a href=\"EditCarServlet?id=" + rscd.getInt("id") + "\">Edit</a></td>");
                    out.println("   <td style=\"border:1px solid black;\"><a href=\"DelCarServlet?id=" + rscd.getInt("id") + "\">Delete</a></td>");
                    out.println("</tr>");*/
                }
                request.setAttribute("carlist", carlist);
                getServletConfig().getServletContext().getRequestDispatcher("/car.jsp").forward(request,response);
                //session.setAttribute("carlist", carlist);
                /*out.println("<script>");
                out.println("   alert(' " + carlist.size() +" ')");
                out.println("   window.location = 'car.jsp'");
                out.println("</script>");*/
                /*out.println("</tbody>"
                        + "</table>");
                out.println("</div>");

                out.println("<p>To add another car Go to <a href='/CRS/admin/InsertCar.html'>Insert Car</a></p>");

                request.getRequestDispatcher("footer.jsp").include(request, response);*/
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
