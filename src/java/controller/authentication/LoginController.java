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
public class LoginController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        AccountDBcontext acDB = new AccountDBcontext();
        Account a = acDB.getAccount(username, password);
        
        if(a != null){
            HttpSession session = req.getSession();
            session.setAttribute("account", a);
            
            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
            c_user.setMaxAge(60*60);
            c_pass.setMaxAge(60*60);
            resp.addCookie(c_pass);
            resp.addCookie(c_user);
            
            resp.getWriter().println("Login Successfull!");
            resp.getWriter().println(req.getServletPath());
        } else {
            resp.getWriter().println("Access Denied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login/login.jsp").forward(req, resp);
    }
}
