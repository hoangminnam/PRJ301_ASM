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

/**
 *
 * @author hoang
 */
public class GroupDBContext extends DBcontext{
//    public ArrayList<Group> getGroup(){
//        ArrayList<Group> listG = new ArrayList<>();
//        String sql = "SELECT * FROM [Group]";
//        try {
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {                
//                Group g = new Group();
//                g.setID(rs.getInt("ID"));
//                g.setName(rs.getString("Name"));
//                g.setSubjectID(rs.getString("SubjectID"));
//                g.setPIC(rs.getInt("PIC"));
//                listG.add(g);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listG;
//    }
}
