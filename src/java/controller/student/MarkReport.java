/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.GraderDBContext;
import dal.StudentDBContext;
import dal.TermDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Grade;
import model.Student;
import model.Subject;
import model.Term;

/**
 *
 * @author hoang
 */
public class MarkReport extends AuthenticationAndAuthenrizationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        if (termid == null || termid == "") {
            t = tDB.getTermCurrent();
            listSubject = tDB.listSubject(t.getTermID(), s.getId());
        } else {
            t = tDB.getTermByID(Integer.parseInt(termid));
            listSubject = tDB.listSubject(t.getTermID(), s.getId());
        }
        
        GraderDBContext gDB = new GraderDBContext();
        ArrayList<Grade> listG;
        if(subid == null && !listSubject.isEmpty()){
            subid = listSubject.get(0).getSubjectID();
            listG = gDB.getListGrade(t.getTermID(), subid, s.getId());
        } else if(subid == null && listSubject == null){
            listG = null;
        } else {
            listG = gDB.getListGrade(t.getTermID(), subid, s.getId());
        }
        
        req.setAttribute("t", t);
        req.setAttribute("subid", subid);
        req.setAttribute("listSubject", listSubject);
        req.setAttribute("listT", listT);
        req.setAttribute("listG", listG);
        req.getRequestDispatcher("markreport.jsp").forward(req, resp);
    }
}
