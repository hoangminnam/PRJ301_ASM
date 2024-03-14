/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.lecturer;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.AttendanceDBContext;
import dal.LecturerDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author hoang
 */
public class AttendanceController extends AuthenticationAndAuthenrizationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account ac) throws ServletException, IOException {
        int seid = Integer.parseInt(req.getParameter("leid"));
                LecturerDBContext lDB = new LecturerDBContext();
        int lid = lDB.getLecturerIDByUsername(ac.getUsername());
        SessionDBContext db = new SessionDBContext();
        ArrayList<Student> students = db.getStudentsByLession(seid,lid);
                ArrayList<Attendance> atts = new ArrayList<>();
                Session lession = new Session();
        lession.setSeid(seid);
        for (Student student : students) {
            Attendance a = new Attendance();
            a.setSe(lession);
            a.setStudent(student);
            a.setDescription(req.getParameter("desription_" + student.getId()));
            a.setIsPresent(req.getParameter("isPresent_" + student.getId()).equals("yes"));
            atts.add(a);
        }
//        for (Attendance a : atts) {
//            resp.getWriter().println(a.getSesID() +" " +a.getStudent().getId() +
//                    " "+ a.getStudent().getName() +" "+ a.isIsPresent()+" " + a.getDescription());
//            resp.getWriter().println();
//        }
        
        AttendanceDBContext aDB = new AttendanceDBContext();
        aDB.takeAttendances(seid, atts);
        resp.sendRedirect("attendance?leid="+seid);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account ac) throws ServletException, IOException {
        int leid = Integer.parseInt(req.getParameter("leid"));
        AttendanceDBContext aDB = new AttendanceDBContext();
        LecturerDBContext lDB = new LecturerDBContext();
        int lid = lDB.getLecturerIDByUsername(ac.getUsername());
        
        ArrayList<Attendance> listA = aDB.getListAttendanceByLession(leid, lid);
        
        
        
        
        
//        AttendanceDBContext aDB = new AttendanceDBContext();
//        ArrayList<Attendance> listA = aDB.getListAttendance(0);
//        
//        StudentDBContext sDB = new StudentDBContext();
//        
//        for (Attendance a : listA) {
//            Student s = sDB.getStudentByID(a.getStudent().getId());
//            a.setStudent(s);
//        }

        req.setAttribute("lname", ac.getDisplayName());
        req.setAttribute("listA", listA);
        req.getRequestDispatcher("../lecturer/attendance.jsp").forward(req, resp);
//       resp.getWriter().println(listA.size());
    }
    
}
