<%-- 
    Document   : login.jsp
    Created on : Jan 6, 2021, 12:27:46 AM
    Author     : Arsalan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"
        ></script>
    </head>
    <%@include file="header.jsp" %>
    <body>
        <br>
        <br>
        <div class="card">
        <div class="card-body">
        <h3 class="card-title">Login Form</h3>    
        <br>
        <form  method="post" action="loginServlet">
            
            <div class="form-outline mb-4" >
                <input type="text" placeholder="Login" name="login" >
            </div>
            
            <div  class="form-outline mb-4">
                <input type="password" placeholder="Password" name="password">
            </div>
            
            <div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </div>
            <br>
            <br>
        </form>
        </div>
    </div>
    </body>
    <%@include file="footer.jsp" %>
</html>

