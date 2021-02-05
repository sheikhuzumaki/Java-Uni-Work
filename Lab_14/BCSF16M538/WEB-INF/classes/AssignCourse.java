import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AssignCourse extends HttpServlet {
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
        UserDAO db = new UserDAO();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.print("<form method='POST'>");
        out.print("<select name='course'>");
        ArrayList<Course> courses = db.getCourses();
        for (Course c : courses) {
            out.print("<option value='" + c.getCourseId() + "'>" + c.getName() + "</option>");
        }
        out.println("</select><br>");
        ArrayList<UserProfile> teachers = db.getTeachers();
        out.print("<select name='email'>");
        for (UserProfile u : teachers) {
            out.print("<option value='" + u.getEmail() + "'>" + u.getName() + "</option>");
        }
        out.println("</select>");
        out.println("<input type='submit'></form>");
        out.println("<body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int course_id = Integer.parseInt(request.getParameter("course"));
        String email = request.getParameter("email");
        UserDAO db = new UserDAO();
        if (db.assignCourse(course_id, email)) {
            out.println("Course Assigned");
        } else {
            response.sendRedirect("Error?msg=Error Assigning Course");
        }
    }
}
