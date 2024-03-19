/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.student;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.Student;

/**
 *
 * @author hoang
 */
public class StudentDetailController extends AuthenticationAndAuthenrizationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String id = req.getParameter("id");
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudent(Integer.parseInt(id));
        req.setAttribute("s", s);
        req.getRequestDispatcher("/student/StudentDetail.jsp").forward(req, resp);
    }
}
