/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Student;

/**
 *
 * @author hoang
 */
public class AttendanceController extends BaseRequiredAuthenticationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account ac) throws ServletException, IOException {
        AttendanceDBContext aDB = new AttendanceDBContext();
        ArrayList<Attendance> listA = aDB.getListAttendance(0);
        for (Attendance a : listA) {
            aDB.updateAttendance(a.getStudent().getId(),req.getParameter("isPresent_"+a.getStudent().getId()));
        }
        //resp.getWriter().println(listA.size());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account ac) throws ServletException, IOException {
        AttendanceDBContext aDB = new AttendanceDBContext();
        ArrayList<Attendance> listA = aDB.getListAttendance(0);
        
        StudentDBContext sDB = new StudentDBContext();
        
        for (Attendance a : listA) {
            Student s = sDB.getStudentByID(a.getStudent().getId());
            a.setStudent(s);
        }
        req.setAttribute("listA", listA);
        req.getRequestDispatcher("../lecturer/attendance.jsp").forward(req, resp);
        //resp.getWriter().println(listStudent.size());
    }
    
}
