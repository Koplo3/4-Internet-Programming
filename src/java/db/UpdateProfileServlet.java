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
import javax.servlet.http.HttpSession;
import jdbc.JdbcUtility;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

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
        jdbcUtility.prepareSQLStatementUpdateUserByID();
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
        
        String fullname = request.getParameter("fullname");
        String login = request.getParameter("login");
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {                    
            PreparedStatement ps = jdbcUtility.getPsUpdateUserByID();
            
            ps.setString(1, fullname);
            ps.setString(2, login);
            ps.setInt(3, id);
            
            int updateStatus = 0;
            updateStatus = ps.executeUpdate();
            //update current profile pic
            HttpSession session = request.getSession();
            session.setAttribute("fullname", fullname);
            
            PrintWriter out = response.getWriter();
            
            out.println(fullname);
            out.println(login);
            out.println(id);
            out.println(updateStatus);
            
            if (updateStatus == 1) {
                out.println("<script>");
                out.println("    alert('User profile update success');");
                out.println("    window.location = 'home.jsp'");
                out.println("</script>");
            }
        }
	catch (SQLException ex)
	{
            while (ex != null) {
                System.out.println ("SQLState: " + ex.getSQLState ());
                System.out.println ("Message:  " + ex.getMessage ());
		System.out.println ("Vendor:   " + ex.getErrorCode ());
                ex = ex.getNextException ();
		System.out.println ("");
            }
            
            PrintWriter out = response.getWriter();
            
            out.println("<script>");
            out.println("    alert('User profile update failed');");
            out.println("    window.location = 'home.jsp'");
            out.println("</script>");            
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
            
            PrintWriter out = response.getWriter();
            
            out.println("<script>");
            out.println("    alert('User profile update failed');");
            out.println("    window.location = 'home.jsp'");
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
