<%@ page import="com.transaction.TransactionResult" %>
<html>
    <head>
        <title>Transaction Result</title>
    </head>
    <body>
    <% 
        if (request.getAttribute("TransactionResult") != null) {
        	TransactionResult result = (TransactionResult) request.getAttribute("TransactionResult");
    %>
  
    <h1>Transaction Result</h1>
    <div>Relative balance for the period is: <%= result.getRelativeBalance()%></div>
    <div>Number of transactions included is: <%= result.getTransactionCount()%></div>
         
    <% 
        } else { 
    %>
 
    <h1>No transaction record found.</h1>
          
    <% } %>   
    </body>
</html>