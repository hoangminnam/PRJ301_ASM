/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.TimeSlot;


/**
 *
 * @author hoang
 */
public class SessionDBContext extends DBcontext{
    public void insert(){
        
    }
    
    public ArrayList<Session> getListSession(int lid, Date from, Date to){
        ArrayList<Session> listSE = new  ArrayList<>();
        String sql = "SELECT g.[Name], g.SubjectID, r.ID, t.TID, t.[time], se.[Date] FROM [Session] se\n"
                + "INNER JOIN [Group] g ON se.GID = g.ID\n"
                + "INNER JOIN Lecturer l ON se.LID = l.ID\n"
                + "INNER JOIN Room r ON se.RoomID = R.ID\n"
                + "INNER JOIN TimeSlot t ON t.TID = se.TID\n"
                + "WHERE se.LID = '200005' AND se.[Date] > ? AND se.[Date] < ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Session s = new Session();
                TimeSlot ts = new  TimeSlot();
                ts.setId(rs.getInt("TID"));
                s.setTimeSlot(ts);
                s.setDate(rs.getDate("Date"));
                listSE.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSE;
    }
}
