/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import bean.UserProfile;
import bean.car;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JdbcUtility;

/**
 *
 * @author DELL
 */
@WebServlet(name = "EditCarServlet", urlPatterns = {"/EditCarServlet"})
public class EditCarServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        //get user profile bean from session
        UserProfile userProfile = (UserProfile)session.getAttribute("userprofile");
        
        try {
            
            PrintWriter out = response.getWriter();
            
            if (userProfile != null)
            {
                //request.getRequestDispatcher("header.jsp").include(request, response);
                //out.println("<h1>Edit Car Servlet</h1>");
                
                PreparedStatement pscd = jdbcUtility.getPsSelectCarByID();
                pscd.setString(1, request.getParameter("id"));
                ResultSet rscd = pscd.executeQuery();
                
                ArrayList<car> carbyid = new ArrayList<car>();
                while(rscd.next()){
                    car _car = new car();
                    _car.setId(rscd.getInt("id"));
                    _car.setName(rscd.getString("name"));
                    _car.setModel(rscd.getString("model"));
                    _car.setColor(rscd.getString("color"));
                    _car.setYear(rscd.getInt("year"));
                    _car.setPrice(rscd.getDouble("price"));
                    _car.setAvailability(rscd.getBoolean("availability"));
                    carbyid.add(_car);
                    
                    /*out.println("<form method=\"post\" action=\"UpdateCarServlet\">");
                    out.println("<div>");
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car name : ");
                    out.println("       <input name=\"name\" type=\"text\" value=" + rscd.getString("name") + " required/>");
                    out.println("   </label></div>");  
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car model:");
                    out.println("       <input name=\"model\" type=\"text\" value=" + rscd.getString("model") + " required/>");
                    out.println("   </label></div>"); 
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car color:");
                    out.println("       <input name=\"color\" type=\"text\" value=" + rscd.getString("color") + " required/>");
                    out.println("   </label></div>"); 
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car year:");
                    out.println("       <input name=\"year\" type=\"text\" value=" + rscd.getInt("year") + " required/>");
                    out.println("   </label></div>"); 
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car price:");
                    out.println("       <input name=\"price\" type=\"text\" value=" + rscd.getDouble("price") + " required/>");
                    out.println("   </label></div>");
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Car availability: \n" +
                                "       <select name=\"availability\">\n" +
                                "           <option selected>TRUE</option>>\n" +
                                "           <option>FALSE</option>>\n" +
                                "       </select>\n" +
                                "   </label></div>");
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>");
                    out.println("       <input name=\"id\" type=\"hidden\" value=" + rscd.getInt("id") + " >");
                    out.println("   </label></div>");
                    out.println("<div style=\"text-align: left; padding-left: 15px\">   <label>");
                    out.println("       <input type=\"submit\" name=\"Submit\" class=\"btn btn-info\" value=\"UPDATE\" />");
                    out.println("   </label></div>");
                    out.println("</div>");
                    out.println("</form>");*/
                }
                request.setAttribute("carbyid", carbyid);
                getServletConfig().getServletContext().getRequestDispatcher("/editcar.jsp").forward(request,response);
                //request.getRequestDispatcher("footer.jsp").include(request, response);
            }
            else {
                out.println("<script>");
                out.println("    alert('Session invalid/terminated - user logout!');");
                out.println("    window.location = 'home.jsp'");
                out.println("</script>");
            }            
        } catch (Exception ex) {}
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
