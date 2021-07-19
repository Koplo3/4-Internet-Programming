<%-- 
    Document   : rentcar
    Created on : Jan 30, 2021, 10:11:35 AM
    Author     : DELL
--%>
<%@ page import="bean.UserProfile" %>
<%@ page import="bean.car" %>
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
        <title>Rent Car Page</title>
    </head>
    <%@include file="header.jsp" %>
    <body>
        <div>
        <div id="carouselExampleIndicators" class="carousel slide" data-mdb-ride="carousel">
  <ol class="carousel-indicators">
    <li
      data-mdb-target="#carouselExampleIndicators"
      data-mdb-slide-to="0"
      class="active"
    ></li>
    <li data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="1"></li>
    <li data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img
        src="https://www.perodua.com.my/assets/images/banner_Bezza_desktop.jpg"
        class="d-block w-100"
        alt="..."
      />
    </div>
    <div class="carousel-item">
      <img
        src="https://www.perodua.com.my/assets/images/myvi-inner-page-banner.png"
        class="d-block w-100"
        alt="..."
      />
    </div>
    <div class="carousel-item">
      <img
        src="https://www.perodua.com.my/assets/images/banner_alza_desktop.jpg"
        class="d-block w-100"
        alt="..."
      />
    </div>
  </div>
  <a
    class="carousel-control-prev"
    href="#carouselExampleIndicators"
    role="button"
    data-mdb-slide="prev"
  >
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </a>
  <a
    class="carousel-control-next"
    href="#carouselExampleIndicators"
    role="button"
    data-mdb-slide="next"
  >
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </a>
</div>
</div>  
        <br>
        <br>
        <div>
        <h3>Available Cars For Booking</h3>
        <div style="padding-bottom: 50px; padding-left: 50px; padding-right: 50px">
            <table class="table table-striped">
                <thead style="font-weight: bold;">
                    <tr>
                        <td style="border:1px solid black;">ID</td>
                        <td style="border:1px solid black;">Name</td>
                        <td style="border:1px solid black;">Model</td>
                        <td style="border:1px solid black;">Color</td>
                        <td style="border:1px solid black;">Year</td>
                        <td style="border:1px solid black;">Price Per Day</td>
                        <td style="border:1px solid black;">Operations</td>
                    </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<car> availcarlist = (ArrayList<car>) request.getAttribute("availcarlist");
                    int ic = 0;
                    for(car _car:availcarlist){
                %>
                    <tr>
                        <td style="border:1px solid black;"><%=ic+1%></td>
                        <td style="border:1px solid black;"><%=_car.getName()%></td>
                        <td style="border:1px solid black;"><%=_car.getModel()%></td>
                        <td style="border:1px solid black;"><%=_car.getColor()%></td>
                        <td style="border:1px solid black;"><%=_car.getYear()%></td>
                        <td style="border:1px solid black;"><%=_car.getPrice()%></td>
                        <td style="border:1px solid black;">
                            <a href='RentCarDay.jsp?carId=<%=_car.getId()%>'<button class="btn btn-primary" type="button">Rent Car</a></td>
                        <br />
                    </tr>
                <%  ic++;
                    } %>
                </tbody>
            </table>
        </div>
      </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
