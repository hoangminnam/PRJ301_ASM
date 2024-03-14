/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.Date;


/**
 *
 * @author hoang
 */
public class Attendance {
    private int aid;
    private Session se;
    private Student student;
    private boolean isPresent;
    private String description;
    private Date datetime;
    
    public Attendance() {
    }

    public Attendance(int aid, Session se, Student student, boolean isPresent, String description, Date datetime) {
        this.aid = aid;
        this.se = se;
        this.student = student;
        this.isPresent = isPresent;
        this.description = description;
        this.datetime = datetime;
    }

    public Session getSe() {
        return se;
    }

    public void setSe(Session se) {
        this.se = se;
    }
    
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
