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
import model.Account;
import model.Feature;
import model.Group;
import model.GroupAccess;

/**
 *
 * @author hoang
 */
public class AccountDBcontext extends DBcontext{
    public Account getAccount(String username, String password){
       
        String sql = "SELECT a.[username], a.[password], g_ac.g_acid, g_ac.g_acName, f.fid, f.[url]\n"
                + "FROM Account a\n"
                + "INNER JOIN GroupAccount ga ON a.username = ga.username\n"
                + "INNER JOIN GroupAccess g_ac ON ga.g_acid = g_ac.g_acid\n"
                + "INNER JOIN GroupFeature gf ON g_ac.g_acid = gf.gid\n"
                + "INNER JOIN Feature f ON f.fid = gf.fid\n"
                + "WHERE a.[username] = ? AND a.[password] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account a = null;
            GroupAccess g_ac = null;
            while (rs.next()) {                
               if(a == null) {
                a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
               }
               int g_acid = rs.getInt("g_acid");
               if(g_acid != 0 && g_ac == null){
                   g_ac = new GroupAccess();
                   g_ac.setG_acid(rs.getInt("g_acid"));
                   g_ac.setG_acname(rs.getString("g_acName"));
                   a.setG_ac(g_ac);
               }
               int fid = rs.getInt("fid");
               if(fid != 0){
                   Feature f = new Feature();
                   f.setId(fid);
                   f.setUrl(rs.getString("url"));
                   a.getFeatures().add(f);
               }
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
