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
public class Account {
    private String username;
    private String password;
    private String displayName;
    private ArrayList<Feature> features = new ArrayList<>();
    private GroupAccess g_ac;

    public Account() {
    }

    public Account(String username, String password, String displayName, GroupAccess g_ac) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.g_ac = g_ac;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public GroupAccess getG_ac() {
        return g_ac;
    }

    public void setG_ac(GroupAccess g_ac) {
        this.g_ac = g_ac;
    }
}
