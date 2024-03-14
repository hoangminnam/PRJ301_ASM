/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Room;
import model.Session;
import model.Student;
import model.Subject;
import model.TimeSlot;


/**
 *
 * @author hoang
 */
public class SessionDBContext extends DBcontext{
    
    public ArrayList<Student> getStudentsByLession(int seid, int lid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT S.ID, S.[Name] FROM Student s\n"
                    + "INNER JOIN Enrollments e ON s.ID = e.sid\n"
                    + "INNER JOIN [Group] g ON e.gid = g.ID\n"
                    + "INNER JOIN [Session] se ON sE.GID = g.ID\n"
                    + "WHERE se.ID = ? AND se.LID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, seid);
            stm.setInt(2, lid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt("ID"));
                s.setName(rs.getString("Name"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    
    public ArrayList<Session> getListSession(int lid, Date from, Date to){
        ArrayList<Session> listSE = new  ArrayList<>();
        String sql = "SELECT se.ID, se.isTaken, g.[Name], g.SubjectID,sub.[Name] AS subname, r.ID AS rid, t.TID, t.[time], se.[Date]\n"
                + "FROM [Session] se\n"
                + "INNER JOIN [Group] g ON se.GID = g.ID\n"
                + "INNER JOIN Lecturer l ON se.LID = l.ID\n"
                + "INNER JOIN Room r ON se.RoomID = R.ID\n"
                + "INNER JOIN TimeSlot t ON t.TID = se.TID\n"
                + "INNER JOIN Subject sub ON sub.ID = g.SubjectID\n"
                + "WHERE se.LID = ? AND se.[Date] >= ? AND se.[Date] <= ? "
                + " ORDER BY se.TID";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Session s = new Session();
                s.setSeid(rs.getInt("ID"));
                TimeSlot ts = new  TimeSlot();
                ts.setId(rs.getInt("TID"));
                ts.setTime(rs.getString("time"));
                s.setTimeSlot(ts);
                
                Subject sub = new Subject();
                Group g = new Group();
                sub.setSubjectID(rs.getString("SubjectID"));
                sub.setName(rs.getString("subname"));
                g.setSub(sub);
                g.setName(rs.getString("Name"));
                s.setGroup(g);
                
                Room r = new Room();
                r.setId(rs.getString("rid"));
                s.setRoom(r);
                s.setIsTaken(rs.getBoolean("isTaken"));
                s.setDate(rs.getDate("Date"));
                listSE.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSE;
    }
}
