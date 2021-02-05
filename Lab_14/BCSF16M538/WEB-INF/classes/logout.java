import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        sess.invalidate();
        response.sendRedirect("Signin.html");
    }
}
