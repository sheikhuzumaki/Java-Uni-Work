import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Signin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession sess = request.getSession();
        UserDAO db = new UserDAO();
        if (db.authenticate(email, password)) {
            sess.setAttribute("email", email);
            int user_type = db.getUserType(email);
            sess.setAttribute("user_type", user_type);
            if (user_type == 1)
                response.sendRedirect("MainStudent");
            else if (user_type == 2)
                response.sendRedirect("MainTeacher");
            else if (user_type == 0)
                response.sendRedirect("MainAdmin");
        } else {
            response.sendRedirect("Error?msg=Incorrect User name or password");
        }
    }
}
