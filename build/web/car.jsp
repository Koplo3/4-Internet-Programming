<%-- 
    Document   : Car.jsp
    Created on : Jan 29, 2021, 8:40:53 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>


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

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car</title>
    </head>
    <body>
        <div style="padding-top: 30px;">
            <h3>AVAILABLE CARS</h3>
        </div>
        <div style="padding: 25px">
        <table class="table table-striped">
            <thead style="font-weight: bold;">
                <tr>
                    <td style="border:1px solid black;">No</td>
                    <td style="border:1px solid black;">Name</td>
                    <td style="border:1px solid black;">Model</td>
                    <td style="border:1px solid black;">Color</td>
                    <td style="border:1px solid black;">Year</td>
                    <td style="border:1px solid black;">Price</td>
                    <td style="border:1px solid black;">Availability</td>
                    <td colspan="2" style="border:1px solid black; text-align: center">Operation</td>
                </tr>
            </thead>
            <tbody>
            <%
                
                ArrayList<car> carlist = (ArrayList<car>) request.getAttribute("carlist");
                int ic = 0;
                
                for(car _car:carlist){
            %>
                <tr>
                    <td style="border:1px solid black;"><%=ic%></td>
                    <td style="border:1px solid black;"><%=_car.getName()%></td>
                    <td style="border:1px solid black;"><%=_car.getModel()%></td>
                    <td style="border:1px solid black;"><%=_car.getColor()%></td>
                    <td style="border:1px solid black;"><%=_car.getYear()%></td>
                    <td style="border:1px solid black;"><%=_car.getPrice()%></td>

                <%if(_car.getAvailability()){%>
                    <td style="border:1px solid black;">Yes</td>
                <%} else {%>
                    <td style="border:1px solid black;">No</td>
                <%}%>
                <td style="border:1px solid black;"><a href="EditCarServlet?id=<%=_car.getId()%>">Edit</a></td>
                <td style="border:1px solid black;"><a href="DelCarServlet?id=<%=_car.getId()%>"u>Delete</a></td>
                </tr>
            <%  ic++;
                }%>
            </tbody>
        </table>
        </div>
            <div>
                <p>To add another car Go to&nbsp;&nbsp;&nbsp;<a href='/CRS/admin/InsertCar.jsp'><button class="btn btn-success" type="button">Insert Car</button></a></p>
            </div>  
            <br>
            <br>
    </body>
    <%@include file="footer.jsp" %>
</html>
