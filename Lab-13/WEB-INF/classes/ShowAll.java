import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class ShowAll extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/store";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement st = con.createStatement();

        ResultSet result = st.executeQuery("select * from items ");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Item Info</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Item Info</h1><br>");
        out.println("<table border=1 width=50%><tr><th>Item Name</th><th>Description</th><th>Price</th></tr>");
        while (result.next()) {
            out.println("<tr><td>" + result.getString("itemname")+ "</td><td>" + result.getString("description")+ "</td><td>" + result.getInt("price")+ "</td></tr>");
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
