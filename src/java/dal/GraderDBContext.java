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
import model.Assessment;
import model.Grade;

/**
 *
 * @author hoang
 */
public class GraderDBContext extends DBcontext {

    public ArrayList<Grade> getListGrade(int tid, String subid, int sid) {
        ArrayList<Grade> listG = new ArrayList<>();

        String sql = "SELECT a.asname, a.[weight], g.score, a.comment FROM Student s\n"
                + "INNER JOIN Grade g ON g.sid = s.ID\n"
                + "INNER JOIN Assessment a ON g.asid = a.asid\n"
                + "INNER JOIN [Subject] sub ON g.subid = sub.ID\n"
                + "INNER JOIN Term t ON t.TermID = sub.termid\n"
                + "WHERE t.TermID = ? AND s.ID = ? AND sub.ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            stm.setInt(2, sid);
            stm.setString(3, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Assessment a = new Assessment();
                a.setName(rs.getString("asname"));
                a.setWeight(rs.getInt("weight"));
                a.setComment(rs.getString("comment"));
                Grade g = new Grade();
                g.setAss(a);
                g.setScore(rs.getFloat("score"));
                listG.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listG;
    }
}
