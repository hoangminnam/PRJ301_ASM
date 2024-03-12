/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.lecturer;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import getDate.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
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
public class TimatableController extends AuthenticationAndAuthenrizationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String raw_from = req.getParameter("dateFrom");
        String raw_to = req.getParameter("dateTo");
        java.sql.Date from = null;
        java.sql.Date to = null;
        
        Date today = new Date();
        if(raw_from ==null)
        {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
        }
        
        if(raw_to ==null)
        {
            to =DateTimeHelper.convertUtilDateToSqlDate(
                    DateTimeHelper.addDaysToDate(DateTimeHelper.getWeekStart(today),6));
        }
        else
        {
            to = java.sql.Date.valueOf(raw_to);
        }
        
        ArrayList<java.sql.Date> dates = DateTimeHelper.getListBetween(
                DateTimeHelper.convertSqlDateToUtilDate(from), 
                DateTimeHelper.convertSqlDateToUtilDate(to));
        
        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.getListTimeSlot();
        
        SessionDBContext lessDB = new SessionDBContext();
        ArrayList<Session> lessions = lessDB.getListSession(0, from, to);
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("lessions", lessions);
        req.getRequestDispatcher("timetable.jsp").forward(req, resp);
    }
}
