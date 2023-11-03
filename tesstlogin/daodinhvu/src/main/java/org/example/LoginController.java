package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().println("Email: "+request.getParameter("email"));
//        response.getWriter().println("Password: "+request.getParameter("pass"));
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        User user = UserService.getInstance().checkLogin(email, pass);
        if(user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("auth", user);
            response.sendRedirect("index.jsp");
//        }
//        if ("quay@gmail.com".equals(email) && "123".equals(pass)) {
//            response.getWriter().println("Login Success!");
        } else {
            request.setAttribute("error","You have entered wrong username/email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}