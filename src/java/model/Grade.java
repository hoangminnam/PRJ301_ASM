/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class Grade {
    private int gradeid;
    private int sid;
    private Assessment ass;
    private Subject sub;
    private double score;

    public Grade() {
    }

    public Grade(int gradeid, int sid, Assessment ass, Subject sub, double score) {
        this.gradeid = gradeid;
        this.sid = sid;
        this.ass = ass;
        this.sub = sub;
        this.score = score;
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Assessment getAss() {
        return ass;
    }

    public void setAss(Assessment ass) {
        this.ass = ass;
    }



    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
