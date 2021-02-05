import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.sql.*;

public class Process extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{

	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String name = request.getParameter("name");

    String msg = "Message From Server : ";
    request.setAttribute("key" , msg);
    //RequestDispatcher rd = request.getRequestDispatcher("/Error");

    Connection con = null ;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/store";

        con = DriverManager.getConnection(url,"root","root"); 

        Statement st = con.createStatement();

        ResultSet result = st.executeQuery("select * from items where itemname = '"+item+"' ");

        if (!result.next()) {
        	RequestDispatcher rd = request.getRequestDispatcher("Error");
        	rd.forward(request , response);
        }else{
        	HttpSession session = request.getSession();

        	if (!exists(name, session))
        	{
        		if (session.getAttribute("count") == null) {
	        		session.setAttribute("count" , 1);
	        	}

	        	session.setAttribute("ItemName" + session.getAttribute("count"), name);
	        	session.setAttribute("count", (int)session.getAttribute("count") + 1);
        	}

        	
        	RequestDispatcher rd = request.getRequestDispatcher("Main");
        	rd.forward(request , response);
        }

        con.close();

    }catch(Exception e){
        System.err.println(e);
    }

    out.close();


	}

	private boolean exists(String name, HttpSession sess) {
		int i = 1;
        String temp = "";
        while ((temp = (String)sess.getAttribute("ItemName" + i)) != null) {
           if (temp.equals(name))
           	return true;
           i++ ;
        }

        return false;
	}
}
