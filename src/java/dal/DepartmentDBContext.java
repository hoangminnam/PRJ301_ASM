/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

/**
 *
 * @author hoang
 */
public class DepartmentDBContext extends DBcontext{
    public ArrayList<Department> getDepartment() {
        ArrayList<Department> dept = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Department";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Department d= new Department();
                d.setId(rs.getInt("ID"));
                d.setName(rs.getString("Name"));
                dept.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dept;
    }
}
