/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.GraderDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Grade;
import model.Student;
import model.Subject;

/**
 *
 * @author hoang
 */
public class GradeTranscriptController extends AuthenticationAndAuthenrizationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudentIDByUserName(account.getUsername());
        GraderDBContext gDB = new GraderDBContext();
        ArrayList<Subject> listSub = gDB.getListSubjects(s.getId());
        ArrayList<Grade> listG = new ArrayList<>();
        for (Subject sub : listSub) {
            if (sub.isIsActive() && !sub.isIsStudying()) {
                ArrayList<Grade> listTemp = gDB.getListGrade(sub.getT().getTermID(), sub.getSubjectID(), s.getId());
                double score = 0;
                Subject su = new Subject();
                su.setSubjectID(sub.getSubjectID());
                Grade g = new Grade();
                g.setSub(sub);
                for (Grade grade : listTemp) {
                    score = (double) (score + (grade.getScore() * grade.getAss().getWeight()) / 100);
                }
                g.setScore(score);
                listG.add(g);
            }
        }
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudentIDByUserName(account.getUsername());
        GraderDBContext gDB = new GraderDBContext();
        ArrayList<Subject> listSub = gDB.getListSubjects(s.getId());
        ArrayList<Grade> listG = new ArrayList<>();
        for (Subject sub : listSub) {
            if (sub.isIsActive() && !sub.isIsStudying()) {
                ArrayList<Grade> listTemp = gDB.getListGrade(sub.getT().getTermID(), sub.getSubjectID(), s.getId());
                double score = 0;
                Subject su = new Subject();
                su.setSubjectID(sub.getSubjectID());
                Grade g = new Grade();
                g.setSub(sub);
                for (Grade grade : listTemp) {
                    score = (double) (score + (grade.getScore() * grade.getAss().getWeight()) / 100);
                }
                g.setScore(score);
                listG.add(g);
            }
        }
//        for (Grade grade : listG) {
//            resp.getWriter().println(grade.getSub().getSubjectID());
//        }

        req.setAttribute("listG", listG);
        req.setAttribute("listSub", listSub);

        req.getRequestDispatcher("GradeTranscript.jsp").forward(req, resp);
    }
}
