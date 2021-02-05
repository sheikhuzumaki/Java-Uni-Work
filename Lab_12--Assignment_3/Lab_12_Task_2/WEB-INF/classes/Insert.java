import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Insert extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();


    String cname = request.getParameter("cname");
    String model = request.getParameter("model");
    String price_string = request.getParameter("price");
    int price = Integer.parseInt(price_string);

    Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/mobileinfo";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement st = con.createStatement();

        int rs = st.executeUpdate("insert into mobileoutlet (companyName , model , price ) values('"+cname+"' , '"+model+"' , '"+price+"')");
        response.sendRedirect("search.html");

        con.close();

    }catch(Exception e){
        System.err.println(e);
    }

    out.close();

	}
}
