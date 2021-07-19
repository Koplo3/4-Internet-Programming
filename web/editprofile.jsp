<%-- 
    Document   : editprofile
    Created on : Jan 30, 2021, 9:33:48 AM
    Author     : DELL
--%>

<%@ page import="bean.UserProfile" %>
<%@ page import="java.util.ArrayList" %>

<%
    if (session.getAttribute("userprofile") == null) {
        out.println("<script>");
        out.println("    alert('Session invalid/terminated - user logout!');");
        out.println("    window.location = 'home.jsp'");
        out.println("</script>");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
          rel="stylesheet"
        />
        <!-- Google Fonts -->
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
        <!-- MDB -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css"
          rel="stylesheet"
        />
    </head>
    <%@include file="header.jsp" %>
    <body>
        <br>
        <br>
        <div class="card">
        <div class="card-body">
        <h3 class="card-title">Update Profile Picture</h3>    
        <br>
        <%
            ArrayList<UserProfile> editprofile = (ArrayList<UserProfile>) request.getAttribute("editprofile");
            for(UserProfile up: editprofile){
        %>
        <form method="post" action="UpdateProfileServlet">
                <div class="form-outline mb-4" >
                    <label>Full name : 
                    <input name="fullname" type="text" value="<%=up.getName()%>" required/>
                </label>
                </div>
                <div class="form-outline mb-4" >
                    <label>User Login:
                    <input name="login" type="text" value="<%=up.getLogin()%>" required/>
                </label>
                </div>
                <div class="form-outline mb-4" >
                    <label>
                    <input name="id" type="hidden" value="<%=up.getId()%>" >
                </label>
                <br>
                <input type="submit" name="Submit" class="btn btn-info" value="UPDATE" />
                </div>
                
        </form>
        <%  } %>
        </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
