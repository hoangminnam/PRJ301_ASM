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
import model.Subject;
import model.Term;

/**
 *
 * @author hoang
 */
public class TermDBContext extends DBcontext {

    public ArrayList<Subject> listSubject(int termid, int sid) {
        ArrayList<Subject> listSub = new ArrayList<>();
        String sql = "SELECT sub.[Name], sub.ID FROM Student s\n"
                + "INNER JOIN Enrollments e ON e.sid = s.ID\n"
                + "INNER JOIN [Group] g ON g.ID = e.gid\n"
                + "INNER JOIN [Subject] sub ON g.SubjectID = sub.ID\n"
                + "INNER JOIN Term t ON sub.termid = t.TermID\n"
                + "WHERE t.TermID = ? AND s.ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, termid);
            stm.setInt(2, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Subject s = new Subject();
                s.setName(rs.getString("Name"));
                s.setSubjectID(rs.getString("ID"));
                listSub.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSub;
    }

    public Term getTermByID(int tid){
        Term t = new Term();
        String sql = "SELECT tname FROM Term WHERE TermID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                t.setTname(rs.getString("tname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setTermID(tid);
        return t;
    }
    
    public Term getTermCurrent() {
        Term t = new Term();

        String sql = "SELECT TOP 1 * FROM Term ORDER BY  TermID DESC";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t.setTermID(rs.getInt("TermID"));
                t.setTname(rs.getString("tname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public ArrayList<Term> getListTerm() {
        ArrayList<Term> listT = new ArrayList<>();
        String sql = "SELECT * FROM Term";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setTermID(rs.getInt("TermID"));
                t.setTname(rs.getString("tname"));
                listT.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listT;
    }
}
