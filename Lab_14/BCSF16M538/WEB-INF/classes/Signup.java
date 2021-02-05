import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Signup extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user_type");
        int user_type = user.equals("student") ? 1 : 2;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone_no = request.getParameter("phone_no");
        String dob = null;
        HttpSession sess = request.getSession();
        int semester = 0;
        if (user_type == 1) {
            dob = request.getParameter("dob");
            semester = Integer.parseInt(request.getParameter("semester"));
        }
        UserProfile User = new UserProfile(name, phone_no, email, password, dob, semester);
        UserDAO db = new UserDAO();
        if (db.insertUser(User, user_type)) {
            sess.setAttribute("email", email);
            sess.setAttribute("user_type", user_type);
            if (user_type == 1)
                response.sendRedirect("MainStudent");
            else if (user_type == 2)
                response.sendRedirect("MainTeacher");
        } else {
            response.sendRedirect("Error?msg=Error Inserting User");
        }
    }
}
