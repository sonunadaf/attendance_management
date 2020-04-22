package com.proj.syncbyte.utility;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.proj.syncbyte.controller.attendance.dto.AttendanceResponseDTO;
import com.proj.syncbyte.controller.user.dto.UserDTO;

public class ExcelGenerator {

	public static String generateExcel(List<AttendanceResponseDTO> list, UserDTO user) {
		String path = FileUtility.getExcelFilePath("reports.xlsx");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(user.getEmpCode() + "_Attendance_Report");
			sheet.setDefaultColumnWidth(list.size() + 2);

			// create header row
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("#");
			header.createCell(1).setCellValue("AttendanceDate");
			header.createCell(2).setCellValue("CheckInTime");
			header.createCell(3).setCellValue("CheckInType");
			header.createCell(4).setCellValue("CheckoOutTime");
			header.createCell(5).setCellValue("CheckOutType");

			// create data rows
			int rowCount = 1;
			int count = 1;
			for (AttendanceResponseDTO at : list) {
				HSSFRow aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(count++);
				aRow.createCell(1).setCellValue(at.getAttendanceDate().toString());
				aRow.createCell(2).setCellValue(at.getCheckInTime().toString());
				aRow.createCell(3).setCellValue(at.getCheckInType());
				aRow.createCell(4).setCellValue(at.getCheckoOutTime().toString());
				aRow.createCell(5).setCellValue(at.getCheckOutType());
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return path;
	}

}
