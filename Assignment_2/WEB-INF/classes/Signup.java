import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Signup extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String firstName = request.getParameter("name_1");
    String lastName = request.getParameter("name_2");
    String email = request.getParameter("Email");
    String pass = request.getParameter("pass");
    String cpass = request.getParameter("cpass");

    Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/javadb";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement st = con.createStatement();



       ResultSet result = st.executeQuery("select email from user where email = '"+email+"' ");

        if(result.next()){
            response.sendRedirect("signup.html?error=error!" );
        }else{
            int rs = st.executeUpdate("insert into user (fname , lname , email , pass ) values('"+firstName+"' , '"+lastName+"' , '"+email+"' , '"+pass+"' )");
            response.sendRedirect("signin.html");
                        
        }

        con.close();

    }catch(Exception e){

        System.err.println(e);

    }


    out.close(); 

	
	}

    /*String site = new String("localhost:8080/Assignment_2/signin.html");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location" , site); 
*/
   //response.sendRedirect("/signin.html");  

    /*RequestDispatcher rd = request.getRequestDispatcher("signin.html");
    rd.forward(request, response);*/

    /*ServletContext sc = getServletContext();
    sc.getRequestDispatcher("/signin.html").forward(request, response);*/

}