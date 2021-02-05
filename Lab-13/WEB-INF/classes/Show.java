import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Show extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {  

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        out.println("<h3>The following data has been found in the session: </h3>");
        
        int i = 1;
        String name = "";
        while ((name = (String)session.getAttribute("ItemName" + i)) != null) {
           out.println("ItemName" + i + " " + name + "<br>");
           i++ ;
           //session.invalidate();
        }

        out.close();

    }

}