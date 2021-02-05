import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MainAdmin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession sess = request.getSession();
        String email = (String) sess.getAttribute("email");
        if (email == null && sess.getAttribute("user_type") == null) {
            response.sendRedirect("Error?msg=You must Signin to continue");
            return;
        }
        String user_type = Integer.toString((Integer) sess.getAttribute("user_type"));
        if (!user_type.equals("0")) {
            response.sendRedirect("Error?msg=You cannot access this");
            return;
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='Add_Course'><button type='submit'>Add Course</button></form>");
        out.println("<form action='AssignCourse'><button type='submit'>Assign Course</button></form>");
        out.println("<form action='logout' style='margin-top:20px;'><button type='submit'>LogOut</form>");
        out.println("<body></html>");
    }
}
