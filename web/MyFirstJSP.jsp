<%-- 
    Document   : MyFirstJSP
    Created on : 25/06/2014, 21:59:40
    Author     : jonasrla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
    if (request.getParameter("name") == null) {
        out.println("Please enter your name.");
    } else {
        out.println("Hello <b>"+request. getParameter("name")+"</b>!");
    }
%>
    </body>
</html>
