/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author hoang
 */
public class Group {
    private int ID;
    private String name;
    private Subject sub;
    private int PIC;

    public Group() {
    }

    public Group(int ID, String name, Subject sub, int PIC) {
        this.ID = ID;
        this.name = name;
        this.sub = sub;
        this.PIC = PIC;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public int getPIC() {
        return PIC;
    }

    public void setPIC(int PIC) {
        this.PIC = PIC;
    }
}
