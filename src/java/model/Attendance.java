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
public class Attendance {
    private int sesID;
    private Student student;
    private boolean isPresent;
    private String description;
    private Lecturer l;

    public Attendance() {
    }

    public Attendance(int sesID, Student student, boolean isPresent, String description, Lecturer l) {
        this.sesID = sesID;
        this.student = student;
        this.isPresent = isPresent;
        this.description = description;
        this.l = l;
    }
    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public int getSesID() {
        return sesID;
    }

    public void setSesID(int sesID) {
        this.sesID = sesID;
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

    public Lecturer getL() {
        return l;
    }

    public void setL(Lecturer l) {
        this.l = l;
    }
}
