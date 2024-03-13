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
import model.Session;
import model.Student;

/**
 *
 * @author hoang
 */
public class AttendanceDBContext extends DBcontext {

    public ArrayList<Attendance> getListAttendanceByLession(int leid, int lid) {
        ArrayList<Attendance> listA = new ArrayList<>();

        String sql = "SELECT a.ID AS aid, g.[Name] AS gname ,s.ID, s.[Name] AS sname, a.isPresent, a.[Description], a.[Datetime]\n"
                + "FROM [Session] se \n"
                + "INNER JOIN [Group] g ON se.GID = g.ID\n"
                + "INNER JOIN Enrollments e  ON e.gid = g.ID\n"
                + "INNER JOIN Student s ON s.ID = e.sid\n"
                + "LEFT JOIN Attendance a ON se.ID = a.SesID\n"
                + "WHERE se.ID = ? AND se.LID = ? AND a.StudentID = s.ID ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            stm.setInt(2, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student s = new Student();
                Session se = new Session();
                s.setId(rs.getInt("ID"));
                s.setName(rs.getString("sname"));
                a.setStudent(s);
                se.setSeid(leid);
                a.setSesID(leid);

                a.setAid(rs.getInt("aid"));
                if (a.getAid() != 0) {
                    a.setDescription(rs.getString("Description"));
                    a.setIsPresent(rs.getBoolean("isPresent"));
                    a.setDatetime(rs.getTimestamp("Datetime"));
                }
                listA.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listA;
    }

    public void takeAttendances(int leid, ArrayList<Attendance> atts) {
        try {
            connection.setAutoCommit(false);
            String sql_remove_atts = "DELETE FROM [Attendance]\n" +
"      WHERE SesID = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, leid);
            stm_remove_atts.executeUpdate();

            for (Attendance att : atts) {
                String sql_insert_att = "INSERT INTO [Attendance]\n"
                        + "           ([SesID]\n"
                        + "           ,[StudentID]\n"
                        + "           ,[isPresent]\n"
                        + "           ,[Description]\n"
                        + "           ,[Datetime])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, leid);
                stm_insert_att.setInt(2, att.getStudent().getId());
                stm_insert_att.setBoolean(3, att.isIsPresent());
                stm_insert_att.setString(4, att.getDescription());

                stm_insert_att.executeUpdate();
            }

            String sql_update_lession = "UPDATE [Session] SET [isTaken] = 1 WHERE ID =?";
            PreparedStatement stm_update_lession = connection.prepareStatement(sql_update_lession);
            stm_update_lession.setInt(1, leid);
            stm_update_lession.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
