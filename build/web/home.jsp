<%-- 
    Document   : home
    Created on : Jan 7, 2021, 2:46:44 AM
    Author     : Arsalan
--%>
<html>
    <head>
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
        <style>
            .hero-image {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("https://mk0cfstest0sltokv8ce.kinstacdn.com/wp-content/uploads/2019/09/Cool-Cars-Feat1.jpg");
            height: 80%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
          }
          .hero-text {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
          }
        </style>
        
    </head>

<!--<h1>Car Rental System</h1>-->
<%@include file="header.jsp" %>
<%
    //String sessionname = (String) session.getAttribute("fullname");
    //String sessiontype = (String) session.getAttribute("usertype");
    //String sessionprofilepic = (String) session.getAttribute("profilepic");
%>
<%
//    if (session.getAttribute("userprofile") != null) {
//        out.println("<p><img src=\"img/" + sessionprofilepic + "\"><br></p>");
//        out.println("<p><a href='UpdateProfilePic.jsp'>Change Profile pic</a><br>");
//        out.println("<a href='EditProfileServlet'>Edit Profile</a></p>");
//    }
%>
<body>
<div class="hero-image">
  <div class="hero-text">
    <h1>CAR RENTAL SYSTEM</h1>
    <h3> GET 15% OFF YOUR FIRST RENTAL</h3>
  </div>
</div>
<br>
<br>
<h1>Welcome to Car Rental System!</h1>
<br>
<br>
<div class="card">
  <div class="card-body">
    <h5 class="card-title">What Do We Do?</h5>
    <p class="card-text">
      A car rental, hire car, or car hire agency is a company that rent automobiles for short period of time, <br> 
    generally ranging from a few hours to a few weeks. it is often organized with numerous local branches(which allow a user to <br>
    return a vehicle to a different locations), and primarily located near airports or busy city areas and often complemented <br>
    by a Website allowing online reservations. Car rental agencies primarily serves people who require a temporary vehicle. <br>
    for example those who do not own their own car, travelers who are out of town. Alongside the basic rental of a vehicle, car <br>
    car rental agencies also offer extra products such as insurance and GPS.
    </p>
    <br>
    <br>
  </div>
</div>

</body>
</html>
<%@include file="footer.jsp" %>