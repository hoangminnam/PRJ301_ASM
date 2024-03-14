/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import dal.TermDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Student;
import model.Subject;
import model.Term;

/**
 *
 * @author hoang
 */
public class AttendanceReportController extends AuthenticationAndAuthenrizationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String termid = req.getParameter("termid");
        String subid = req.getParameter("subid");
        
        TermDBContext tDB = new TermDBContext();
        ArrayList<Term> listT = tDB.getListTerm();
        
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudentIDByUserName(account.getUsername());
        
        ArrayList<Subject> listSubject;
        Term t;
        if(termid == null || termid == "") {
            t = tDB.getTermCurrent();
            listSubject = tDB.listSubject(t.getTermID(), s.getId());
        } else {
            t = tDB.getTermByID(Integer.parseInt(termid));
            listSubject = tDB.listSubject(t.getTermID(), s.getId());
        }
        
        AttendanceDBContext aDB = new AttendanceDBContext();
        
        ArrayList<Attendance> listA;
        if(subid == null){
            subid = listSubject.get(0).getSubjectID();
            listA = aDB.getListAttendanceReport(s.getId(), listSubject.get(0).getSubjectID());
        } else{
            listA = aDB.getListAttendanceReport(s.getId(), subid);
        }
        
        req.setAttribute("t", t);
        req.setAttribute("subid", subid);
        req.setAttribute("listSubject", listSubject);
        req.setAttribute("listT", listT);
        req.setAttribute("listA", listA);
        //resp.getWriter().print(listA.size());
        req.getRequestDispatcher("attendanceReport.jsp").forward(req, resp);
    }
}
