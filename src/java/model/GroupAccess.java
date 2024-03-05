/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author hoang
 */
public class GroupAccess {
    private int g_acid;
    private String g_acname;

    public GroupAccess() {
    }

    public GroupAccess(int g_acid, String g_acname) {
        this.g_acid = g_acid;
        this.g_acname = g_acname;
    }

    public int getG_acid() {
        return g_acid;
    }

    public void setG_acid(int g_acid) {
        this.g_acid = g_acid;
    }

    public String getG_acname() {
        return g_acname;
    }

    public void setG_acname(String g_acname) {
        this.g_acname = g_acname;
    }
}
