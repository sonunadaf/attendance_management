package com.proj.syncbyte.controller.attendance;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.attendance.dto.AttendanceResponseDTO;
import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.service.attendance.IAttendanceService;
import com.proj.syncbyte.utility.SessionUtility;

@Controller
@RequestMapping("/")
public class AttendanceController {

	@Autowired
	private IAttendanceService attendanceService;
	private static final Logger log = Logger.getLogger(AttendanceController.class);

	public AttendanceController() {
		log.info("Created: " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/attendance.do", method = RequestMethod.GET)
	public String getAttendance(HttpServletRequest servletRequest, Model model) {
		try {
			UserDTO dto = SessionUtility.loggedInUser(servletRequest);
			if (dto == null)
				return "LogIn";
			List<AttendanceResponseDTO> dtoList = attendanceService.getAttendanceList(dto.getEmpCode());
			Map<String, String> map = attendanceService.getAttandanceStatus(dto.getEmpCode());
			model.addAttribute("map", map);
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("list", dtoList);
			return "AttendancePage";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "AttendancePage";
		}

	}

	@RequestMapping(value = "/attendance.do", method = RequestMethod.POST)
	public String addAttendance(@RequestParam String empCode, HttpServletRequest servletRequest, Model model)
			throws InvalidDataException {
		try {
			UserDTO dto = SessionUtility.loggedInUser(servletRequest);
			if (dto == null)
				return "LogIn";
			List<AttendanceResponseDTO> dtoList = attendanceService.addAttendance(empCode);
			Map<String, String> map = attendanceService.getAttandanceStatus(empCode);
			model.addAttribute("map", map);
			model.addAttribute("empCode", empCode);
			model.addAttribute("list", dtoList);
			return "MarkAttendance";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (ParseException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		}
	}

	@RequestMapping(value = "/updateAttendance.do", method = RequestMethod.POST)
	public String updateAttendance(@RequestParam String empCode, @RequestParam int id,
			HttpServletRequest servletRequest, Model model) throws InvalidDataException {
		try {
			UserDTO dto = SessionUtility.loggedInUser(servletRequest);
			if (dto == null)
				return "LogIn";
			List<AttendanceResponseDTO> dtoList = attendanceService.updateAttendance(id, empCode);
			Map<String, String> map = attendanceService.getAttandanceStatus(empCode);
			model.addAttribute("map", map);
			model.addAttribute("empCode", empCode);
			model.addAttribute("list", dtoList);
			return "AttendancePage";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "AttendancePage";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "AttendancePage";
		} catch (ParseException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("error", e.getMessage());
			return "AttendancePage";
		}
	}

	@RequestMapping(value = "/markAttendance.do", method = RequestMethod.GET)
	public String markAttendance(HttpServletRequest servletRequest, Model model) {
		try {
			UserDTO dto = SessionUtility.loggedInUser(servletRequest);
			if (dto == null)
				return "LogIn";
			List<AttendanceResponseDTO> dtoList = attendanceService.getAttendanceList(dto.getEmpCode());
			Map<String, String> map = attendanceService.getAttandanceStatus(dto.getEmpCode());
			model.addAttribute("map", map);
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("list", dtoList);
			return "MarkAttendance";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		}
	}

	@RequestMapping(value = "/inWithFingerPrint.do", method = RequestMethod.POST)
	public String markAttendanceWithFinferPrint(@RequestParam MultipartFile file, HttpServletRequest servletRequest,
			Model model) throws InvalidDataException {
		UserDTO dto = SessionUtility.loggedInUser(servletRequest);
		try {

			if (dto == null)
				return "LogIn";
			attendanceService.addAttendance(dto.getEmpCode(), file);
			Map<String, String> map = attendanceService.getAttandanceStatus(dto.getEmpCode());
			model.addAttribute("map", map);
			model.addAttribute("empCode", dto.getEmpCode());
			return "MarkAttendance";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (ParseException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		}
	}

	@RequestMapping(value = "/outWithFingerPrint.do", method = RequestMethod.POST)
	public String outWithFingerPrint(@RequestParam MultipartFile file, @RequestParam int id,
			HttpServletRequest servletRequest, Model model) throws InvalidDataException {
		UserDTO dto = SessionUtility.loggedInUser(servletRequest);
		try {

			if (dto == null)
				return "LogIn";
			attendanceService.updateAttendance(dto.getEmpCode(), file, id);
			List<AttendanceResponseDTO> dtoList = attendanceService.getAttendanceList(dto.getEmpCode());
			Map<String, String> map = attendanceService.getAttandanceStatus(dto.getEmpCode());
			model.addAttribute("map", map);
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("list", dtoList);
			return "AttendancePage";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		} catch (ParseException e) {
			log.error(e.getMessage());
			model.addAttribute("empCode", dto.getEmpCode());
			model.addAttribute("error", e.getMessage());
			return "MarkAttendance";
		}
	}

	@RequestMapping(value = "getAttendance.do", method = RequestMethod.GET)
	public String getAttendanceList(@RequestParam String empCode, HttpServletRequest request, Model model) {
		try {
			UserDTO dto = SessionUtility.loggedInUser(request);
			if (dto == null) {
				return "LogIn";
			}
			List<AttendanceResponseDTO> dtoList = attendanceService.getAttendanceList(empCode);
			model.addAttribute("dto", dto);
			model.addAttribute("list", dtoList);
			return "AttendancePage";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "EmployeesPage";
		}
	}

	@RequestMapping(value = "downloadAttendanceReports.do", method = RequestMethod.GET)
	public void downloadReports(@RequestParam String empCode, HttpServletRequest request, Model model,
			HttpServletResponse response) {
		try {
			String path = attendanceService.generateAttendanceReport(empCode);
			UserDTO dto = SessionUtility.loggedInUser(request);
			if (dto == null || !dto.getUserTypeRole().equals("ADMIN")) {
				model.addAttribute("error", "Un-Authorized user");
			}

			File file = new File(path);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[(int) file.length()];
			int bytesRead = 0;
			while ((bytesRead = inStrem.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.flush();
			inStrem.close();
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

	}
}
