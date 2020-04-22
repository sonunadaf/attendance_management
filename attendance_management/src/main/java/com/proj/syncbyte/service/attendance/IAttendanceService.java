package com.proj.syncbyte.service.attendance;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.attendance.dto.AttendanceResponseDTO;

public interface IAttendanceService {

	public List<AttendanceResponseDTO> addAttendance(String empCode) throws InvalidDataException, ParseException;

	public List<AttendanceResponseDTO> getAttendanceList(String empCode) throws InvalidDataException;

	public Map<String, String> getAttandanceStatus(String empCode) throws InvalidDataException;

	public List<AttendanceResponseDTO> updateAttendance(int id, String empCode)
			throws InvalidDataException, ParseException;

	public void addAttendance(String empCode, MultipartFile file) throws InvalidDataException, ParseException;

	public void updateAttendance(String empCode, MultipartFile file, int id)
			throws ParseException, InvalidDataException;

	public String generateAttendanceReport(String empCode) throws InvalidDataException;

}
