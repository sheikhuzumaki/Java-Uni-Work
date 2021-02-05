<!doctype html>
<html lang="en">
  <head>
    <title>Main</title>
  </head>
   <body>
   <h1>Searched Item</h1>
   <%@ page language="java" import="java.io.* , javax.servlet.http.* , javax.servlet.* , java.sql.* , java.util.*" %>
     <% 
     Connection con = null ;
     String item = request.getParameter("item");
     try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/store";
        con = DriverManager.getConnection(url,"root","root"); 
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery("select * from items where itemname = '"+item+"' ");
        %>
        <table><tr><th>Item Name</th><th>Description</th><th>Price</th></tr>
        <%
        while (result.next()) {
                out.println("<tr><td>" + result.getString("itemname")+ "</td><td>" + result.getString("description")+ "</td><td>" + result.getInt("price")+ "</td></tr>");
            }
          %>
        </table>
        <%
        }catch(Exception e){
            System.err.println(e);
        }
     %>
  </body>
</html>