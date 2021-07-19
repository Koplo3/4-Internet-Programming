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
import bean.UserProfile;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Arsalan
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            //get the prepared statement that is needed
            PreparedStatement ps = jdbcUtility.getPsSelectUserByLogin();

            //execute the statement
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            //generate UserProfile bean
            UserProfile userProfile = null;
            while (rs.next()) {

                int recordId = rs.getInt("id");
                String dbPasswordHash = rs.getString("password");
                String salt = rs.getString("salt");
                String fullname = rs.getString("fullname");
                String photo = rs.getString("photo");
//                String addedDate = rs.getString("addeddate");
                String usertype = rs.getString("usertype");


                //creating user profile object
                userProfile = new UserProfile();
                
                userProfile.setId(recordId);
                userProfile.setLogin(login);
                userProfile.setPassword(dbPasswordHash);
                userProfile.setSalt(salt);
                userProfile.setName(fullname);
                userProfile.setPhoto(photo);
//                userProfile.setAddedDate(addedDate);
                userProfile.setUserType(usertype);
            }

            if (userProfile != null) {

                //generate password hash from the password login-form
                String hash = DigestUtils.sha512Hex(password + userProfile.getSalt());

                //validate hash
                if (hash.equals(userProfile.getPassword())) {

                    //login and password is correct
                    //generate session
                    HttpSession session = request.getSession();

                    //add profileBean to session
                    userProfile.setPassword("");
                    userProfile.setSalt("");
                    session.setAttribute("userprofile", userProfile);
                    session.setAttribute("userid", userProfile.getId());
                    session.setAttribute("fullname", userProfile.getName());
                    session.setAttribute("usertype", userProfile.getUserType());
                    session.setAttribute("profilepic", userProfile.getPhoto());

                    out.println("<script>");
                    out.println("    alert('Login Successful!');");
                    //The following location is to be changed according to the project Home page 
                    out.println("    window.location = '/CRS/home.jsp'");
                    out.println("</script>");

                } else {

                    //login correct but password is incorrect
                    out.println("<script>");
                    out.println("    alert('Login/Password incorrect');");
                    out.println("    window.location = '/CRS/login.jsp'");
                    out.println("</script>");
                }

            } else {
                //user with that login not exist
                out.println("<script>");
                out.println("    alert('Login/Password incorrect');");
                out.println("    window.location = '/CSR/login.jsp'");
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
            out.println("    alert('Select user by login failed');");
            out.println("</script>");
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Select user by login failed');");
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
