package com.example.gp_back_end.function;

import com.example.gp_back_end.model.FormModel;
import com.example.gp_back_end.model.CourseModel;
import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.repository.StudentLoginRepository;
import com.example.gp_back_end.user.Role;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class UploadReader {

    @Autowired
    private StudentLoginRepository studentLoginRepository;

    private String getCellValue(XSSFCell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unsupported Cell Type";
        }
    }

    private Role getRoleFromCellValue(String cellValue) {
        try {
            return Role.valueOf(cellValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle the case where the role is not valid
            // For example, you could return a default role or throw an exception
            return null; // or throw new RuntimeException("Invalid role: " + cellValue);
        }
    }

    public List<UploadStudentModel> readStudentExcel(MultipartFile file) throws IOException {
        List<UploadStudentModel> models = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Set<String> regNumberSet = new HashSet<>();
        int flag = 0;

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip the header row
            }
            String regNumber = getCellValue((XSSFCell) row.getCell(0));
            Optional<UploadStudentModel> existingStudent = studentLoginRepository.findByRegNumber(regNumber);
            if (regNumberSet.contains(regNumber)) {
                // Duplicate in the file, handle accordingly
                System.out.println("Duplicate registration number in file: " + regNumber);
                flag = 1;
            }else if (existingStudent.isPresent()) {
                // Student already exists, handle accordingly
                System.out.println("Student already exists in database: " + regNumber);
                flag = 1;
            }
            regNumberSet.add(regNumber);
        }

        if(flag == 1) {
           return models;
        }

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            UploadStudentModel model = new UploadStudentModel();
            model.setRegNumber(getCellValue((XSSFCell) row.getCell(0)));
            model.setName(getCellValue((XSSFCell) row.getCell(1)));
            model.setIndexNumber(getCellValue((XSSFCell) row.getCell(2)));
            model.setEmail(getCellValue((XSSFCell) row.getCell(3)));
            model.setNic(getCellValue((XSSFCell) row.getCell(4)));
            model.setPassword(getCellValue((XSSFCell) row.getCell(4)));
            model.setRole(getRoleFromCellValue(getCellValue((XSSFCell) row.getCell(5)))); // Assuming the role is in the 6th column
            model.setRoleName("Student");
            model.setSemester(Integer.parseInt(getCellValue((XSSFCell) row.getCell(6))));
            model.setYear(Integer.parseInt(getCellValue((XSSFCell) row.getCell(7))));

            models.add(model);
        }
        workbook.close();
        return models;
    }

    public List<UploadLecturerModel> readLecturerExcel(MultipartFile file) throws IOException {
        List<UploadLecturerModel> models = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            UploadLecturerModel model = new UploadLecturerModel();
            model.setRegNumber(getCellValue((XSSFCell) row.getCell(0)));
            model.setName(getCellValue((XSSFCell) row.getCell(1)));
            model.setEmail(getCellValue((XSSFCell) row.getCell(2)));
            model.setNic(getCellValue((XSSFCell) row.getCell(3)));
            model.setPassword(getCellValue((XSSFCell) row.getCell(3)));
            model.setRole(Role.LECTURER);
            model.setRoleName("Lecturer");
            String coursesCell = getCellValue((XSSFCell) row.getCell(4));
            if (coursesCell != null && !coursesCell.isEmpty()) {
                List<String> courses = Arrays.asList(coursesCell.split(",\\s*"));
                model.setCourse(courses);
            } else {
                model.setCourse(Collections.emptyList());
            }

            models.add(model);
        }
        workbook.close();
        return models;
    }


    private String generateShareURL() {
        // Logic to generate a share URL
        return String.valueOf(System.currentTimeMillis());
    }

    public List<FormModel> formBulkUpload(MultipartFile file, String content) throws IOException {
        List<FormModel> models = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            FormModel model = new FormModel();
            model.setUserId(("REG123456"));
            model.setName(getCellValue((XSSFCell) row.getCell(0)));
            model.setDescription(getCellValue((XSSFCell) row.getCell(1)));
            model.setCourse(getCellValue((XSSFCell) row.getCell(2)));
            model.setCreatedAt(LocalDateTime.now());
            model.setTemplate(false);
            model.setContent(content);
            model.setPublished(false);
            model.setVisits(0);
            model.setSubmissions(0);
            model.setShareURL(generateShareURL());
            model.setVisits(0);

            models.add(model);
        }
        workbook.close();
        return models;
    }
  
    public List<CourseModel> readCourseExcel(MultipartFile file) throws IOException {
        List<CourseModel> models = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            CourseModel model = new CourseModel();
            model.setCourseCode(getCellValue((XSSFCell) row.getCell(0)));
            model.setCourseName(getCellValue((XSSFCell) row.getCell(1)));
            model.setCredit(getCellValue((XSSFCell) row.getCell(2)));
            model.setYear(getCellValue((XSSFCell) row.getCell(3)));
            model.setSemester(getCellValue((XSSFCell) row.getCell(4)));

            models.add(model);
        }
        workbook.close();
        return models;
    }
}
