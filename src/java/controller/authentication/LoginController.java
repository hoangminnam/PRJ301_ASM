/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.AccountDBcontext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

/**
 *
 * @author hoang
 */
public class LoginController extends HttpServlet {

    private void websiteRedirection(Account a, HttpServletResponse resp) throws ServletException, IOException {
        if (a.getG_ac().getG_acid() == 1) {
            resp.sendRedirect("/student/HomeStudent.jsp");
        } else if (a.getG_ac().getG_acid() == 2) {
            resp.sendRedirect("lecturer/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AccountDBcontext acDB = new AccountDBcontext();
        Account a = acDB.getAccount(username, password);

        if (a != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", a);

            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
            c_user.setMaxAge(60 * 60);
            c_pass.setMaxAge(60 * 60);
            resp.addCookie(c_pass);
            resp.addCookie(c_user);
            websiteRedirection(a, resp);
        } else {
            resp.getWriter().println("Access Denied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //If the user is still in a session, go to the home page
        if (req.getSession().getAttribute("account") != null) {
            Account a = (Account) req.getSession().getAttribute("account");
            websiteRedirection(a, resp);
        } else {
            req.getRequestDispatcher("login/login.jsp").forward(req, resp);
        }
    }
}
