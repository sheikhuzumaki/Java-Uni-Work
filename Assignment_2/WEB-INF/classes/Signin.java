import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Signin extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();


    String email = request.getParameter("Email");
    String pass = request.getParameter("pass");

     Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/javadb";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement ft = con.createStatement();

        ResultSet rm = ft.executeQuery("select * from user where email = '"+email+"' and pass = '"+pass+"'  ");


        if (rm.next()) {
            
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Log In</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p> Log In Succefully " + rm.getString("fname") + "  " + rm.getString("lname") + " </p>");
    out.println("</body>");
    out.println("</html>");

        }else{

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Not Log In</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p> Invalid User Name or Password</p>");
    out.println("</body>");
    out.println("</html>");

        }

        con.close();

    }catch(Exception e){

        System.err.println(e);

    }


    out.close(); 

    
    }


	
	}


