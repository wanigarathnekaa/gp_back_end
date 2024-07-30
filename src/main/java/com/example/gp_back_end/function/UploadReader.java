package com.example.gp_back_end.function;

import com.example.gp_back_end.model.UploadLecturerModel;
import com.example.gp_back_end.model.UploadStudentModel;
import com.example.gp_back_end.user.Role;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadReader {

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
            model.setRole(getRoleFromCellValue(getCellValue((XSSFCell) row.getCell(5)))); // Assuming the role is in the 6th column

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
            model.setLecturerId(getCellValue((XSSFCell) row.getCell(0)));
            model.setName(getCellValue((XSSFCell) row.getCell(1)));
            model.setEmail(getCellValue((XSSFCell) row.getCell(2)));
            model.setNic(getCellValue((XSSFCell) row.getCell(3)));

            models.add(model);
        }
        workbook.close();
        return models;
    }
}
