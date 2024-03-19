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
import model.Student;

/**
 *
 * @author hoang
 */
public class StudentDBContext extends DBcontext {

    public void insert(Student s) {
        String sql = "INSERT INTO Student (Name, Gender, Date, Email, Phone, PersonalID, SpecialtyID)\n"
                + "VALUES \n"
                + "(?,?,?, ?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            stm.setBoolean(2, s.isGender());
            stm.setDate(3, s.getDate());
            stm.setString(4, s.getEmail());
            stm.setString(5, s.getPhone());
            stm.setString(6, s.getPersonalID());
            stm.setInt(7, s.getSpecialty().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getLatestID() {
        String sql = "SELECT TOP 1 * FROM Student\n"
                + "ORDER BY ID DESC";
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

    public Student getStudentIDByUserName(String username) {
        Student s = new Student();
        String sql = "SELECT ID FROM Student WHERE Email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public ArrayList<Student> search(int id) {
        ArrayList<Student> listS = new ArrayList<>();
        String sql = "SELECT ID, [Name] FROM Student\n"
                + "WHERE ID LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Student s = new Student();
                s.setId(rs.getInt("ID"));
                s.setName(rs.getString("Name"));
                listS.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listS;
    }
    
    public Student getStudent (int id){
        Student s = new Student();
        String sql ="SELECT * FROM Student WHERE ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                s.setId(rs.getInt("ID"));
                s.setName(rs.getString("Name"));
                s.setGender(rs.getBoolean("Gender"));
                s.setDate(rs.getDate("Date"));
                s.setEmail(rs.getString("Email"));
                s.setPhone(rs.getString("Phone"));
                s.setPersonalID(rs.getString("PersonalID"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
