<%-- 
    Document   : RentCar
    Created on : Jan 19, 2021, 5:38:54 PM
    Author     : arnob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carId = request.getParameter("carId");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Rent Car</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="js/ie-emulation-modes-warning.js"></script>


    </head>

    <%@include file="header.jsp" %>
    <body background="img/bg.png">
        <br>
        <br>
        <div class="page-header">
            <h1 style="text-align: center">Information Collection Form</h1>
        </div>
        <form method="post" action='BookingServlet' style="width: 500px; margin: auto"  >
            <label>Please enter the number of days you want the car:
                <input name="days" type="text" class="form-control" required/>
                <input type="hidden" name="carId" value="<% out.print(carId); %>"/>
            </label>
            <br>
            <br>
            <button type="submit" name="submit" class="btn btn-primary">Submit</button>
        </form>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="js/ie10-viewport-bug-workaround.js"></script>
        
    </body>
    
</html>
