<%-- 
    Document   : nav
    Created on : Jan 19, 2021, 10:16:42 AM
    Author     : arnob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String name = (String) session.getAttribute("fullname");
    String type = (String) session.getAttribute("usertype");
%>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"
        ></script>
        <title>CRS</title>
        
    </head>
    
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e8e8e8;">
    <div class="container-fluid">
        
        <!-- brand -->
        
        <a class="navbar-brand" href="home.jsp"><h3><b>Car Rental System</b></h3></a>
    
    
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarRightAlignExample"
      aria-controls="navbarRightAlignExample"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <div class="collapse navbar-collapse" id="navbarRightAlignExample">
        <%
            if (session.getAttribute("userprofile") == null) {
                out.println("<ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">");
                out.println("<li class=\"nav-item active\">");
                out.println("<a class=\"nav-link\" href=\"login.jsp\">Login </a>");
                out.println("</li>");
                out.println("<li class=\"nav-item active\">");
                out.println("<a href='register.jsp'><button class=\"btn btn-primary\" type=\"button\">Register</button></a>");
                out.println("</li>");
                out.println("</ul>");
            } else {

                out.println("<ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">");
                out.println("<li class=\"nav-item active\">");
                if (type.equals("Member")) {
                    out.println("<a class=\"nav-link\" href='RentCarServlet'><b>Rent a Car</b></a>");
                    out.println("</li>");
                    out.println("<li class=\"nav-item active\">");
                    out.println("<a class=\"nav-link\" href='UserBookings'><b>Your Bookings</b></a>");
                    out.println("</li>");
                } else {
                    out.println("<a class=\"nav-link\" href='CarServlet'><b>Car List</b></a>");
                    out.println("</li>");
                    out.println("<li class=\"nav-item active\">");
                    out.println("<a class=\"nav-link\" href='ViewBookings'><b>Bookings List</b></a>");
                    out.println("</li>");
                }
                out.println("</ul>");

                out.println("<ul class=\"navbar-nav ml-auto\">");
                out.println("<li class=\"nav-item dropdown\">");
                out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-mdb-toggle=\"dropdown\" aria-expanded=\"false\">Hi, " + name + "</a>");
                out.println("<ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">");
                out.println("<li>");
                out.println("<a class=\"dropdown-item\" href=\"UpdateProfilePic.jsp\">Change Profile Pic</a>");
                out.println("</li>");
                out.println("<li>");
                out.println("<a class=\"dropdown-item\" href=\"EditProfileServlet\">Edit Profile Info</a>");
                out.println("</li>");
                out.println("</ul>");
                out.println("</li>");
                out.println("<li class=\"nav-item active\">");
                out.println("<a href='LogoutServlet'><button class=\"btn btn-danger\" type=\"button\">Logout</button></a>");
                out.println("</li>");
                out.println("</ul>");
            }
        %>
        

    </div>
   </div>
</nav>