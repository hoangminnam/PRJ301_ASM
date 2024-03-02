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
import model.TimeSlot;

/**
 *
 * @author hoang
 */
public class TimeSlotDBContext extends DBcontext{
    public ArrayList<TimeSlot> getListTimeSlot(){
        ArrayList<TimeSlot> listTS = new ArrayList<>();
        String sql = "SELECT*FROM TimeSlot";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                TimeSlot  ts = new TimeSlot();
                ts.setId(rs.getInt("TID"));
                ts.setTime("Time");
                listTS.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTS;
    }
}
