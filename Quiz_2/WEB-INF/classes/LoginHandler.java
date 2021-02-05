import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class LoginHandler extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String [][] LogIn = {
    	{"marcin", "testpaswrd"},
    	{"Robert", "Delgro1234"},
    	{"James", "Gosling"},
    };

    String user = request.getParameter("usr");
    String pass = request.getParameter("pass");

    for(int i = 0 ; i < 3 ; i++ ){
    	if(LogIn[i][0] == user && LogIn[i][1] == pass){
    		out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Welcome</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<script>alert('Log In')</script>");
		    out.println("</body>");
		    out.println("</html>");
    	}else{
    		out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Fail</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<script>alert('Not Log In')</script>");
		    out.println("</body>");
		    out.println("</html>");
    	}
    }
	
	}
}


