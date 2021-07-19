/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import bean.UserProfile;
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
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementSelectUserByLogin();
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
                //out.println("<h1>Edit Profile Servlet</h1>");
                
                /*PreparedStatement pscd = jdbcUtility.getPsSelectUserByLogin();
                pscd.setString(1, userProfile.getLogin());
                ResultSet rscd = pscd.executeQuery();*/
                
                ArrayList<UserProfile> editprofile = new ArrayList<UserProfile>();
                editprofile.add(userProfile);
                
                request.setAttribute("editprofile", editprofile);
                getServletConfig().getServletContext().getRequestDispatcher("/editprofile.jsp").forward(request,response);
                
                /*while(rscd.next()){
                    UserProfile up = new UserProfile();
                    up.setId(rscd.getInt("id"));
                    up.setLogin(rscd.getString("login"));
                    up.setName("fullname");
                    
                    out.println("<form method=\"post\" action=\"UpdateProfileServlet\">");
                    out.println("<div>");
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>Full name : ");
                    out.println("       <input name=\"fullname\" type=\"text\" value=" + rscd.getString("fullname") + " required/>");
                    out.println("   </label></div>");  
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>User Login:");
                    out.println("       <input name=\"login\" type=\"text\" value=" + rscd.getString("login") + " required/>");
                    out.println("   </label></div>");
                    out.println("<div style=\"text-align: left; padding: 15px\">   <label>");
                    out.println("       <input name=\"id\" type=\"hidden\" value=" + rscd.getInt("id") + " >");
                    out.println("   </label></div>");
                    out.println("<div style=\"text-align: left; padding-left: 15px\">   <label>");
                    out.println("       <input type=\"submit\" name=\"Submit\" class=\"btn btn-info\" value=\"UPDATE\" />");
                    out.println("   </label></div>");
                    out.println("</div>");
                    out.println("</form>");
                }*/
                
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
