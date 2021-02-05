<!DOCTYPE html>
<html>
<head>
	<title>Process</title>
</head>
<%@ page language="java" import="java.io.* , javax.servlet.http.* , javax.servlet.* , java.sql.* , java.util.*" %>
<body>
	<% 

	Connection con = null ;

	String item = request.getParameter("item");

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/store";
        con = DriverManager.getConnection(url,"root","root"); 
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery("select * from items where itemname = '"+item+"' ");
        if (!result.next()) {
        	RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
        	rd.forward(request , response);
        }else{
            session.setAttribute("sessname" , item); 
        	RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
        	rd.forward(request , response);
        }

        con.close();

    }catch(Exception e){
        System.err.println(e);
    }

	%>


</body>
</html>