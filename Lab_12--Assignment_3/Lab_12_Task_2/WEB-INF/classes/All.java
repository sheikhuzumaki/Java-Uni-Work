import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class All extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/mobileinfo";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement st = con.createStatement();

        ResultSet result = st.executeQuery("select * from mobileoutlet ");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Searched Records</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Searched Records</h1><br>");
        out.println("<table border=1 width=50%><tr><th>Company Name</th><th>Model</th><th>Price</th></tr>");
        while (result.next()) {
            out.println("<tr><td>" + result.getString("companyName")+ "</td><td>" + result.getString("model")+ "</td><td>" + result.getInt("price")+ "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        con.close();

    }catch(Exception e){
        System.err.println(e);
    }

    out.close();

	}
}
