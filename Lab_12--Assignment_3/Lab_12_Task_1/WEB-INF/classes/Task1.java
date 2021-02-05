import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;

public class Task1 extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{


	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    //String [] planguage = new String[3] ;
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    String gender = request.getParameter("gender");
    String age = request.getParameter("age");
    /*for (int i = 0 ; i < 3  ; i++  ) {
    	planguage[i] = request.getParameter("planguage[]");
    }*/
    String[] lang = request.getParameterValues("planguage[]");
    String inst = request.getParameter("inst");

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Info</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<label>Name : </label><span>" + name + "</span><br>");
    out.println("<label>Gender : </label><span>" + gender + "</span><br>");
    out.println("<label>Age : </label><span>" + age + "</span><br>");
    out.println("<label>Lanuguages : </label><span>" + String.join(", ", lang) + "</span><br>");
    out.println("<label>Instructions : </label><span>" + inst + "</span><br>");
    out.println("</body>");
    out.println("</html>");

    out.close();


	}
}