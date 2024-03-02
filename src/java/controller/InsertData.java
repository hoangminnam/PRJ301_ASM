/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.LecturerDBContext;
import dal.StudentDBContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import model.Lecturer;
import model.Specialty;
import model.Student;

/**
 *
 * @author hoang
 */
public class InsertData {

    private static final String[] provinceCodes = {
        "001", "002", "004", "006", "008", "010", "011", "012", "014", "015", "017", "019",
        "020", "022", "024", "025", "026", "027", "030", "031", "033", "034", "035", "036",
        "037", "038", "040", "042", "044", "045", "046", "048", "049", "051", "052", "054",
        "056", "058", "060", "062", "064", "066", "067", "068", "070", "072", "074", "075",
        "077", "079", "080", "082", "083", "084", "086", "087", "089", "091", "092", "093",
        "094", "095", "096"
    };
    private static final String[] FIRST_NAMES = {"Linh", "Trang", "Hoa", "An", "Huy", "Nam", "Thang", "Minh", "Thi", "Tuan", "Quynh", "Hong", "Van", "Phuong", "Binh", "Son", "Duc", "Giang", "Cuong", "Tam"};
    private static final String[] MIDDLE_NAMES = {"Thi", "Van", "Minh", "Hoang", "Nhu", "Duc", "Van", "Ngoc", "Huu", "Thanh", "Nhan", "Hai", "Thinh", "Hoai", "Phuc"};
    private static final String[] LAST_NAMES = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Huynh", "Phan", "Vu", "Vo", "Dang", "Bui", "Do", "Ho", "Ngo", "Duong", "Ly", "Dao", "Dinh", "An", "Duong"};

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String middleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return lastName + " " + middleName + " " + firstName;
    }

    private static String generatePersonalID(boolean isMale) {
        Random random = new Random();
        String provinceCode = provinceCodes[random.nextInt(provinceCodes.length)];
        String genderCode = isMale ? "2" : "3";
        String yearOfBirth = "03";
        String randomDigits = String.format("%06d", random.nextInt(1000000));
        return  provinceCode + genderCode + yearOfBirth +randomDigits;
    }

        private static final String[] CODES = {"101", "102", "103", "104", "105", "106", "201", "202", "203", "204", "205", "301", "302", "401", "501"};
    
    public static String generateRandomCode() {
        Random random = new Random();
        int index = random.nextInt(CODES.length);
        return CODES[index];
    }
    
    public static void main(String[] args) {
        insertLecturer();
    }
    
    public void insertStudent() {
        Random random = new Random();
        ArrayList<Student> listStudent = new ArrayList<>();
        StudentDBContext stDB = new StudentDBContext();
        int id = stDB.getLatestID();
        
        for (int i = 0; i < 10; i++) {
            id++;
            Student s = new Student();
            s.setId(id);
            s.setName(generateRandomName());
            s.setGender(random.nextBoolean());
            s.setDate(Date.valueOf("2003-01-01"));
            String[] name = s.getName().split("\\s");
            String email = name[2] + name[0].toLowerCase().charAt(0) + name[1].toLowerCase().charAt(0) + Integer.toString(id) + "@fpt.edu.vn";
            s.setEmail(email);
            String phone = "0" + Integer.toString(random.nextInt(1000000000));
            s.setPhone(phone);
            s.setPersonalID(generatePersonalID(s.isGender()));
            Specialty specialty = new Specialty();
            specialty.setId(Integer.parseInt(generateRandomCode()));
            s.setSpecialty(specialty);
            stDB.insert(s);
        }
    }
    
    public static void insertLecturer(){
        Random random = new Random();
         LecturerDBContext lDB = new LecturerDBContext();
         int id = 1;
         for (int i = 0; i < 3; i++) {
            id++;
            Lecturer l = new Lecturer();
            l.setId(200000+id);
            l.setName(generateRandomName());
            l.setGender(random.nextBoolean());
            l.setDate(Date.valueOf("1990-01-01"));
            String[] name = l.getName().split("\\s");
            String email = name[2] + name[0].toLowerCase().charAt(0) + name[1].toLowerCase().charAt(0) + Integer.toString(id) + "@fe.edu.vn";
            l.setEmail(email);
            String phone = "0" + Integer.toString(random.nextInt(1000000000));
            l.setPhone(phone);
            l.setPersonalID(generatePersonalID(l.isGender()));
            lDB.insert(l);
        }
    }
}
