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
import model.Feature;

/**
 *
 * @author sonnt
 */
public abstract class AuthenticationAndAuthenrizationController extends HttpServlet {

    private Account getAuthenticatedAccount(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        if (account == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String username = null;
                String password = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }

                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }

                    if (username != null && password != null) {
                        break;
                    }
                }

                if (username != null && password != null) {
                    AccountDBcontext db = new AccountDBcontext();
                    account = db.getAccount(username, password);
                    req.getSession().setAttribute("account", account);
                }
            }
        }
        return account;
    }

    private boolean isAuthorization(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        boolean isAuthorizated = false;
        if (account == null) {
            return false;
        }
        String url = req.getServletPath();
        for (Feature f : account.getFeatures()) {
            if (f.getUrl().equals(url)) {
                isAuthorizated = true;
                break;
            }
        }

        return isAuthorizated;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null && isAuthorization(req)) {
            doPost(req, resp, account);
        } else {
            HttpSession session = req.getSession();
            boolean isLoggedIn = (session != null && session.getAttribute("account") != null);
            if (isLoggedIn) {
                resp.getWriter().print("Access Denied!!");
            } else {
                resp.sendRedirect("../login");
            }

        }

    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null && isAuthorization(req)) {
            doGet(req, resp, account);
        } else {
            HttpSession session = req.getSession();
            boolean isLoggedIn = (session != null && session.getAttribute("account") != null);
            if (isLoggedIn) {
                resp.getWriter().print("Access Denied!!");
            } else {
                resp.sendRedirect("../login");
            }

        }
    }

}
