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
import model.Lecturer;

/**
 *
 * @author hoang
 */
public class LecturerDBContext extends DBcontext{
    public int getLecturerIDByUsername(String username) {
        int lid = 0;
        String sql = "SELECT ID FROM Lecturer\n"
                + "WHERE Email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                lid = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lid;
    }
    
    public ArrayList<Lecturer> getLecturer(){
        ArrayList<Lecturer> listL = new ArrayList<>();
        String sql ="SELECT*FROM Lecturer";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("ID"));
                l.setName(rs.getString("Name"));
                l.setGender(rs.getBoolean("Gender"));
                l.setDate(rs.getDate("Date"));
                l.setEmail(rs.getString("Email"));
                l.setPhone(rs.getString("Phone"));
                l.setPersonalID(rs.getString("PersonalID"));
                listL.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listL;
    }
    

    public void insert(Lecturer l) {
        String sql = "INSERT INTO Lecturer (ID, Name, Gender, Date, Email, Phone, PersonalID)\n"
                + "VALUES \n"
                + "(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, l.getId());
            stm.setString(2, l.getName());
            stm.setBoolean(3, l.isGender());
            stm.setDate(4, l.getDate());
            stm.setString(5, l.getEmail());
            stm.setString(6, l.getPhone());
            stm.setString(7, l.getPersonalID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getLatestID() {
        String sql = "SELECT TOP 1 * FROM Lecturer ORDER BY ID DESC";
        int id = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
