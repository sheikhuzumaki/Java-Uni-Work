<!doctype html>
<html lang="en">
  <head>
  <title>Session Page</title>
  </head>
  <body>
    <%@ page language="java" import="java.io.* , javax.servlet.http.* , javax.servlet.* , java.sql.* , java.util.*" %>
    <h1>Sessions</h1>
    <%
           String name = "";
           String name=(String)session.getAttribute("sessname"); 
           out.println("Item Name" +  name );
    %>
  </body>
</html>