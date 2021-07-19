<%-- 
    Document   : InsertCar
    Created on : Feb 1, 2021, 2:30:41 PM
    Author     : Karimul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
      
        <title>Insert Car</title>
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
        <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"
        ></script>

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="js/ie-emulation-modes-warning.js"></script>
    </head>
    
    
    
    <body background="../img/bg.png">

        <div class="page-header" style="padding-top: 35px; padding-bottom: 20px;">
            <h1 style="text-align: center">Add Another Car</h1>
        </div>
        <div style="margin: auto; width: 50%; padding: 10px; text-align: center;">
            <form method="post" action="../AddCarServlet">
            <div class="form-outline mb-4" >
                
                <input name="name" type="text" placeholder="Enter Car Name" required/>
            
            </div>
            <div class="form-outline mb-4" >
            
                <input name="model" type="text" placeholder="Enter Car Model" required/>
            
                </div>
            <div class="form-outline mb-4" >
            
                <input name="color" type="text" placeholder="Enter Car Color" required/>
            
                </div>
            <div class="form-outline mb-4" >
            
                <input name="year" type="text" placeholder="Enter Car Year" required/>
            
                </div>
            <div class="form-outline mb-4" >
            
                <input name="price" type="text" placeholder="Service Charges Per Day"  required/>
            
                </div>
            <div class="form-outline mb-4" > 
            <button type="submit" name="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
        </div>
        
        <br>
        <br>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="js/ie10-viewport-bug-workaround.js"></script>
    </body>
    
</html>
<%@include file="../footer.jsp" %>
