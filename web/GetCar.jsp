<%-- 
    Document   : GetCar
    Created on : Jan 15, 2021, 1:46:50 AM
    Author     : Arsalan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="bean.UserProfile" %>
<%@ page import="bean.car" %>
<%@ page import="java.util.ArrayList" %>


<c:if test="${sessionScope.memberprofile == null}">
    <% response.sendRedirect(request.getContextPath() + "/terminate.jsp"); %>
</c:if>


<%--<jsp:useBean id="memberprofile" class="bean.UserProfile" scope="session" />--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MEMBER REQUEST CAR PAGE</title>
        
    </head>
    <body>
       
        <!-- here it should show all the cars  -->
        
        <h2>GET CAR PAGE </h2>
    </body>
</html>
