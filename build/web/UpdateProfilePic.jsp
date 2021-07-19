<%-- 
    Document   : UpdateProfilePic
    Created on : Jan 14, 2021, 12:27:47 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile Pic</title>
        <!-- Font Awesome -->
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
    
    <body>
        <br>
        <br>
        <div class="card">
        <div class="card-body">
        <h3 class="card-title">Update Profile Picture</h3>    
        <br>
        
        <%
            String sessionprofilepic = (String) session.getAttribute("profilepic");
        %>
        <% 
            if(session.getAttribute("userprofile")==null){
                out.println("<script>");
                out.println("    alert('Session invalid/terminated - user logout!');");
                out.println("    window.location = 'home.jsp'");
                out.println("</script>");
            }else{
                out.println("<p><img src=\"img/"  + sessionprofilepic + "\" style=\"width:300px;height:300px;\"><br></p>");
            }
        %>
        
            <form method="post" action="UploadProfilePicServlet" enctype="multipart/form-data">
                <br>
                <b>Choose a new picture: </b><input type="file" name="profilepic" size="60" /><br /><br />
                <input name="id" type="hidden" value="<%=session.getAttribute("userid")%>">
                <br /> <input type="submit" value="Upload" class="btn btn-success"/>
                <br>
                <br>
                <br>
            </form>
        </div>
        </div>
    </body>
</html>
<%@include file="footer.jsp" %>