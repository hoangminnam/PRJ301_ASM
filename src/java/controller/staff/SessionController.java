/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.staff;

import dal.GroupDBContext;
import dal.LecturerDBContext;
import dal.RoomDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Group;
import model.Lecturer;
import model.Room;
import model.TimeSlot;

/**
 *
 * @author hoang
 */
public class SessionController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        GroupDBContext gDB = new GroupDBContext();
//        ArrayList<Group> listg = gDB.getGroup();
//        
//        LecturerDBContext lDB = new LecturerDBContext();
//        ArrayList<Lecturer> listL = lDB.getLecturer();
//        
//        RoomDBContext rDB = new RoomDBContext();
//        ArrayList<Room> listR = rDB.getRoom();
//        
//        TimeSlotDBContext tsDB  = new TimeSlotDBContext();
//        ArrayList<TimeSlot> listTimeSlot = tsDB.getListTimeSlot();
//        
//        req.setAttribute("listG", listg);
//        req.setAttribute("listL", listL);
//        req.setAttribute("listR", listR);
//        req.setAttribute("listTimeSlot", listTimeSlot);
//        req.getRequestDispatcher("insertSession.jsp").forward(req, resp);
       //resp.getWriter().println(listg.get(0).getID());
    }

}
