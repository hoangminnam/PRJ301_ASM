/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Student;

/**
 *
 * @author hoang
 */
public class AttendanceDBContext extends DBcontext{
    //get list attendance with lecturerID in table session
    public ArrayList<Attendance> getListAttendance(int sessID){
        ArrayList<Attendance> listA = new ArrayList<>();
        String sql = "SELECT a.SesID, a.StudentID, a.isPresent, a.[Description] FROM Attendance a\n"
                + "INNER JOIN [Session] s\n"
                + "ON a.SesID = s.ID\n"
                + "WHERE s.LID = 200005"; // replace sessID
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Attendance a = new Attendance();
                a.setSesID(rs.getInt("SesID"));
                Student s = new Student();
                s.setId(rs.getInt("StudentID"));
                a.setStudent(s);
                a.setIsPresent(rs.getBoolean("isPresent"));
                a.setDescription(rs.getString("Description"));
                listA.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listA;
    }
    
    public void updateAttendance(int id, String isPresent){
        String sql = "UPDATE [dbo].[Attendance]\n"
                + "   SET [isPresent] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Datetime] = GETDATE()\n"
                + " WHERE StudentID = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, isPresent);
            stm.setString(2, null);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
