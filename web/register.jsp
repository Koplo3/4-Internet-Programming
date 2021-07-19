<%-- 
    Document   : register
    Created on : Jan 5, 2021, 8:13:15 PM
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
        <h3 class="card-title">Registration Form</h3>    
        <br>

        <form method="post" action="RegistrationServlet">
                
                <div class="form-outline mb-4" >
                    <label>Full name:
                        <input name="fname" type="text" palaceholder="Full name"  required/>
                    </label>
                </div>
            
                <div class="form-outline mb-4" >
                    <label>Login Id:&nbsp;&nbsp;&nbsp;
                        <input name="login" type="text" palaceholder="loginId" required/>
                    </label>
                </div>

                <div class="form-outline mb-4" >
                    <label>Password:
                        <input name="password" type="password" required/>
                    </label>
                </div>

                <div>
                    <label>
                        <input type="submit" name="Submit" class="btn btn-primary" value="Submit" />
                    </label> 
                </div>
            
        </form>
        </div>
        </div>
    </body>
</html>
<%@include file="footer.jsp" %>
