/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package getDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author hoang
 */
public class GetDateTime {
        public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the day of the week of the given date
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Calculate the number of days to subtract to get to Monday
        int daysToSubtract = (dayOfWeek - Calendar.MONDAY + 7) % 7;

        // Subtract the days to get to the first day of the week (Monday)
        calendar.add(Calendar.DAY_OF_YEAR, -daysToSubtract);

        // Return the resulting date
        return calendar.getTime();
    }
        
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        } else {
            return null;
        }
    }
    
    public static java.util.Date convertToUtilDate(java.sql.Date sqlDate) {
        // Convert java.sql.Date to java.util.Date
        return new java.util.Date(sqlDate.getTime());
    }
    
    public static ArrayList<java.sql.Date> getBetweenDate(java.util.Date fromDate, java.util.Date toDate) {
        ArrayList<java.sql.Date> dateList = new ArrayList<>();

        // Calendar to manipulate dates
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);

        // Iterate from 'from' date to 'to' date
        while (!calendar.getTime().after(toDate)) {
            dateList.add(new java.sql.Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.DATE, 1); // Move to the next day
        }
        return dateList;
    }
}
