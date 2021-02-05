import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Error extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {        
    
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String key = (String)request.getAttribute("key");

    String name = request.getParameter("name");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error 404</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> " + key +"</h1>");
            out.println("<p> No Information for Item :  " + name + " </p>");
            out.println("</body>");
            out.println("</html>");
    

    }
}
