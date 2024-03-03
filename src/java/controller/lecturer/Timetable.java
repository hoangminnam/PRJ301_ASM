/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.lecturer;

import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import getDate.DateTimeHelper;
import getDate.GetDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author hoang
 */
public class Timetable extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String raw_dateFrom = req.getParameter("dateFrom");
        String raw_dateTo = req.getParameter("dateTo");
        
        java.sql.Date from = null;
        java.sql.Date to = null;
        
        Date current = new Date();
        
        if(raw_dateFrom == null){
            from = GetDateTime.convertUtilDateToSqlDate(GetDateTime.convertUtilDateToSqlDate(current));
        } else {
            from = java.sql.Date.valueOf(raw_dateFrom);
        }
        
        if (raw_dateTo == null) {
            to = GetDateTime.convertUtilDateToSqlDate(GetDateTime.convertUtilDateToSqlDate(current));
        } else {
            to = java.sql.Date.valueOf(raw_dateTo);
        }
        
        ArrayList<java.sql.Date> dates = GetDateTime.getBetweenDate(GetDateTime.convertToUtilDate(from), GetDateTime.convertToUtilDate(to));
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
