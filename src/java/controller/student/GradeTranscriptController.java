/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.AuthenticationAndAuthenrizationController;
import dal.GraderDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import model.Account;
import model.Grade;
import model.Student;
import model.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hoang
 */
public class GradeTranscriptController extends AuthenticationAndAuthenrizationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudentIDByUserName(account.getUsername());
        GraderDBContext gDB = new GraderDBContext();
        ArrayList<Subject> listSub = gDB.getListSubjects(s.getId());

        try {
            XSSFWorkbook wordkbook = new XSSFWorkbook();
            XSSFSheet sheet = wordkbook.createSheet("StudentTranscript");
            XSSFRow row = null;
            Cell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("NO.");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("TERM");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("SEMESTER");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("SUBJECT CODE");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("PREREQUISITE SUBJECT");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("SUBJECT NAME");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("CREDIT");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("GRADE");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("STATUS");

            for (int i = 0; i < listSub.size(); i++) {
                row = sheet.createRow(i + 1);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listSub.get(i).getT().getTname());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(listSub.get(i).getSemester());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(listSub.get(i).getSubjectID());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(listSub.get(i).getPrerequisite());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(listSub.get(i).getName());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(listSub.get(i).getCreadit());

                if (!listSub.get(i).isIsActive()) {
                    cell = row.createCell(7, CellType.NUMERIC);
                    cell.setCellValue("");

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue("Not Started");
                } else if (listSub.get(i).isIsActive() && listSub.get(i).isIsStudying()) {
                    cell = row.createCell(7, CellType.NUMERIC);
                    cell.setCellValue("");

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue("Studying");
                } else {
                    ArrayList<Grade> listTemp = gDB.getListGrade(listSub.get(i).getT().getTermID(), listSub.get(i).getSubjectID(), s.getId());
                    double score = 0;
                    for (Grade grade : listTemp) {
                        score = (double) (score + (grade.getScore() * grade.getAss().getWeight()) / 100);
                    }
                    cell = row.createCell(7, CellType.NUMERIC);
                    cell.setCellValue(score);
                    if (score >= 4) {
                        cell = row.createCell(8, CellType.STRING);
                        cell.setCellValue("Passed");
                    } else {
                        cell = row.createCell(8, CellType.STRING);
                        cell.setCellValue(" Not Passed");
                    }

                }
            }

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wordkbook.write(outByteStream);

            byte[] outArray = outByteStream.toByteArray();
            resp.setContentType("application/ms-excel");
            resp.setContentLength(outArray.length);
            resp.setHeader("Expires:", "0"); // disables browser caching
            resp.setHeader("Content-Disposition", "attachment; filename=StudentTranscript_" + account.getDisplayName() + ".xlsx");

            OutputStream outStream = resp.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        StudentDBContext sDB = new StudentDBContext();
        Student s = sDB.getStudentIDByUserName(account.getUsername());
        GraderDBContext gDB = new GraderDBContext();
        ArrayList<Subject> listSub = gDB.getListSubjects(s.getId());
        ArrayList<Grade> listG = new ArrayList<>();
        for (Subject sub : listSub) {
            if (sub.isIsActive() && !sub.isIsStudying()) {
                ArrayList<Grade> listTemp = gDB.getListGrade(sub.getT().getTermID(), sub.getSubjectID(), s.getId());
                double score = 0;
                Subject su = new Subject();
                su.setSubjectID(sub.getSubjectID());
                Grade g = new Grade();
                g.setSub(sub);
                for (Grade grade : listTemp) {
                    score = (double) (score + (grade.getScore() * grade.getAss().getWeight()) / 100);
                }
                g.setScore(score);
                listG.add(g);
            }
        }
//        for (Grade grade : listG) {
//            resp.getWriter().println(grade.getSub().getSubjectID());
//        }

        req.setAttribute("listG", listG);
        req.setAttribute("listSub", listSub);

        req.getRequestDispatcher("GradeTranscript.jsp").forward(req, resp);
    }
}
