/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;
import java.sql.*;

/**
 *
 * @author hoang
 */
public class Session {
    private int seid;
    private Group group;
    private Lecturer lecturer;
    private Room room;
    private TimeSlot timeSlot;
    private Date date;
    private boolean isTaken;

    public Session() {
    }

    public Session(int seid, Group group, Lecturer lecturer, Room room, TimeSlot timeSlot, Date date, boolean isTaken) {
        this.seid = seid;
        this.group = group;
        this.lecturer = lecturer;
        this.room = room;
        this.timeSlot = timeSlot;
        this.date = date;
        this.isTaken = isTaken;
    }

    public int getSeid() {
        return seid;
    }

    public void setSeid(int seid) {
        this.seid = seid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIsTaken() {
        return isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }
    
}
