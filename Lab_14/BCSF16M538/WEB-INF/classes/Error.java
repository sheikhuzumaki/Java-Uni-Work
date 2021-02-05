import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Error extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String msg = request.getParameter("msg");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' href='index.css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2 class='error' style='display:block;font-size:20px'>" + msg + "</h2>");
        out.println("</body></html>");
    }
}
