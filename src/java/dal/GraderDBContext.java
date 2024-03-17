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
import model.Subject;
import model.Term;

/**
 *
 * @author hoang
 */
public class GraderDBContext extends DBcontext {

    public ArrayList<Subject> getListSubjects(int sid) {
        ArrayList<Subject> listSub = new ArrayList<>();
        String sql = "SELECT sub.termid, sub.semester, t.tname, sub.ID, sub.prerequisite, sub.[Name], sub.Credit, sub.isActive, sub.isStudying\n"
                + "FROM [Subject] sub\n"
                + "INNER JOIN Specialty sp ON sp.ID = sub.SpecialtyID\n"
                + "INNER JOIN Student s ON s.SpecialtyID = sp.ID\n"
                + "LEFT JOIN Term t ON sub.termid = t.TermID\n"
                + "WHERE s.ID = ? \n"
                + "ORDER BY ISNULL(sub.semester, 999999);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setTname(rs.getString("tname"));
                t.setTermID(rs.getInt("termid"));
                Subject sub = new Subject();
                sub.setT(t);
                sub.setSemester(rs.getInt("semester"));
                sub.setSubjectID(rs.getString("ID"));
                sub.setPrerequisite(rs.getString("prerequisite"));
                sub.setName(rs.getString("Name"));
                sub.setCreadit(rs.getInt("Credit"));
                sub.setIsActive(rs.getBoolean("isActive"));
                sub.setIsStudying(rs.getBoolean("isStudying"));
                listSub.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSub;
    }

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
                //g.setScore(rs.getFloat("score"));

                if (rs.getObject("score") != null) {
                    g.setScore(rs.getFloat("score"));
                }
                listG.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listG;
    }
}
