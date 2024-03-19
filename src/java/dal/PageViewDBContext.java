/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
public class PageViewDBContext extends DBcontext {

    public int getViews() {
        int views = 0;
        String sql = "SELECT * FROM [Views]";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("views");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageViewDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return views;
    }

    public void updateViews(int views) {
        String sql = "UPDATE [Views]\n"
                + "   SET [views] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, views);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PageViewDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
