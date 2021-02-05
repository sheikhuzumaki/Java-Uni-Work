import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Add_Course extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession sess = request.getSession();
        String email = (String) sess.getAttribute("email");
        String user_type = Integer.toString((Integer) sess.getAttribute("user_type"));

        if (email == null) {
            response.sendRedirect("Error?msg=You must Signin to continue");
            return;
        }
        if (!user_type.equals("0")) {
            response.sendRedirect("Error?msg=You cannot access this");
            return;
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.print(
                "<form method='POST'><input name='name' placeholder='Name'><input name='semester' placeholder='Semester'><input type='submit'></from>");
        out.println("<body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int semester = Integer.parseInt(request.getParameter("semester"));
        UserDAO db = new UserDAO();
        if (db.insertCousrse(name, semester)) {
            out.println("Course Added");
        } else {
            response.sendRedirect("Error?msg=Error Adding Course");
        }
    }
}
