<%-- 
    Document   : editcar
    Created on : Jan 29, 2021, 10:49:59 PM
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
        <title>Edit Car</title>
    </head>
    <%@include file="header.jsp" %>
    <body>
        <div class="page-header" style="padding-top: 35px; padding-bottom: 20px;">
            <h3 style="text-align: center">Edit Car Details</h3>
        </div>
        <div style="margin: auto; width: 50%; padding: 10px; text-align: center; padding-bottom: 30px;">
        <form method="post" action="UpdateCarServlet">
            <%
                ArrayList<car> carbyid = (ArrayList<car>) request.getAttribute("carbyid");
                for(car _car:carbyid){
            %>
                <div class="form-outline mb-4" >
                    <input name="name" type="text" value="<%=_car.getName()%>" required/>
                </div>
                <div class="form-outline mb-4" >
                    <input name="model" type="text" value="<%=_car.getModel()%>" required/>
                </div>
                <div class="form-outline mb-4" >
                    <input name="color" type="text" value="<%=_car.getColor()%>" required/>
                </div>
                <div class="form-outline mb-4" >
                    <input name="year" type="text" value="<%=_car.getYear()%>" required/>
                </div>
                <div class="form-outline mb-4" >
                    <input name="price" type="text" value="<%=_car.getPrice()%>" required/>
                </div>
                <div class="form-outline mb-4" >
                    <label>Car availability:
                    <select name="availability">
                        <option selected>TRUE</option>
                        <option>FALSE</option>
                    </select>
                </label>
                </div>
               <div class="form-outline mb-4" >
                        <input name="id" type="hidden" value="<%=_car.getId()%>" >
                </div>
                <div class="form-outline mb-4" >
                    <input type="submit" name="Submit" class="btn btn-success" value="UPDATE" />
                </div>
            
            <% 
                }
            %>
        </form>
        </div>
    </body>
</html>
<%@include file="/footer.jsp" %>
