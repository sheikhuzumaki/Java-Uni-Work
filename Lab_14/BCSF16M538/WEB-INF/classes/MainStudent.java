import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MainStudent extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession sess = request.getSession();
        String email = (String) sess.getAttribute("email");
        if (email == null && sess.getAttribute("user_type") == null) {
            response.sendRedirect("Error?msg=You must Signin to continue");
            return;
        }
        String user_type = Integer.toString((Integer) sess.getAttribute("user_type"));

        if (!user_type.equals("1")) {
            response.sendRedirect("Error?msg=You cannot access this");
            return;
        }
        UserDAO db = new UserDAO();
        UserProfile user = db.getUserProfile(email);
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' href='index.css' />");
        out.println("</head>");
        out.println("<body>");
        // out.println("<h1>Student</h1>");
        out.println("<div class='user-detail'>" + user + "<div>");

        out.println("<h2>Course</h2>");
        out.println("<ul>");
        ArrayList<Course> courses = db.getCoursesInSemester(user.getSemester());
        if (courses.size() == 0)
            out.println("<h4>No Courses Yet</h4>");
        for (Course course : courses) {
            out.println("<li>" + course.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("<form action='logout' style='margin-top:20px;'><button type='submit'>LogOut</form>");
        out.println("<body></html>");
    }
}
