/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.LecturerDBContext;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import getDate.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author hoang
 */
public class LessionToday extends AuthenticationAndAuthenrizationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        Date d = new Date();
        java.sql.Date date = DateTimeHelper.convertUtilDateToSqlDate(d);
        
        LecturerDBContext lDB = new LecturerDBContext();
        int lid = lDB.getLecturerIDByUsername(account.getUsername());
        SessionDBContext lessDB = new SessionDBContext();
        ArrayList<Session> lessions = lessDB.getListSession(lid, date, date);
        req.setAttribute("lessions", lessions);
        req.getRequestDispatcher("takeAttendanceToday.jsp").forward(req, resp);
        //resp.getWriter().print(lessions.size());
    }
}
