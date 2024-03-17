/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoang
 */
public class Subject {

    private String subjectID;
    private String Name;
    private int creadit;
    private Term t;
    private int semester;
    private String prerequisite;
    private boolean isActive;
    private boolean isStudying;
    private Specialty sp;

    public Subject() {
    }

    public Subject(String subjectID, String Name, int creadit, Term t, int semester, String prerequisite, boolean isActive, boolean isStudying, Specialty sp) {
        this.subjectID = subjectID;
        this.Name = Name;
        this.creadit = creadit;
        this.t = t;
        this.semester = semester;
        this.prerequisite = prerequisite;
        this.isActive = isActive;
        this.isStudying = isStudying;
        this.sp = sp;
    }

    public Term getT() {
        return t;
    }

    public void setT(Term t) {
        this.t = t;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsStudying() {
        return isStudying;
    }

    public void setIsStudying(boolean isStudying) {
        this.isStudying = isStudying;
    }

    public Specialty getSp() {
        return sp;
    }

    public void setSp(Specialty sp) {
        this.sp = sp;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getCreadit() {
        return creadit;
    }

    public void setCreadit(int creadit) {
        this.creadit = creadit;
    }
}
