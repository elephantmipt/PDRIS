package edu.phystech.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static authorization.AuthorizationManager.verifyPassword;
import static authorization.AuthorizationManager.userExists;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write = resp.getWriter();
        ServletContext servletContext = getServletConfig().getServletContext();

        String user = req.getParameter("userName");
        String pass = req.getParameter("userPass");
        if(user == null || pass == null){
            RequestDispatcher rd = servletContext.getRequestDispatcher("/formLogin.html");
            rd.include(req, resp);
            return;
        }
        if (!userExists(user)) {
            write.println(String.format(
                    "<h1>Username %s doesn't exist. Please, sign up to continue</h1>", user
            ));
            RequestDispatcher rd = servletContext.getRequestDispatcher("/formLogin.html");
            rd.include(req, resp);
        }
        else {
            if (verifyPassword(user, pass)) {
                RequestDispatcher rd = servletContext.getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
            } else {
                write.println("<h1>Wrong password</h1>");
                RequestDispatcher rd = servletContext.getRequestDispatcher("/formLogin.html");
                rd.include(req, resp);
            }
        }
    }
}
