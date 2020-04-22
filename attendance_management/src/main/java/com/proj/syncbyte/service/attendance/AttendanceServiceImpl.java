package com.proj.syncbyte.service.attendance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.attendance.dto.AttendanceResponseDTO;
import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.dao.attendance.ISaveOrUpdateAttendance;
import com.proj.syncbyte.dao.getattendance.IGetAttendanceDao;
import com.proj.syncbyte.dao.user.IUserDao;
import com.proj.syncbyte.entity.AttendanceEntity;
import com.proj.syncbyte.entity.AttendanceStatus;
import com.proj.syncbyte.entity.RecordStstus;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.service.user.IUsersService;
import com.proj.syncbyte.utility.DateUtility;
import com.proj.syncbyte.utility.ExcelGenerator;
import com.proj.syncbyte.utility.FileUtility;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private ISaveOrUpdateAttendance saveOrUpdateAttendance;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IGetAttendanceDao attendanceDao;
	@Autowired
	private IUsersService usersService;
	private static final Logger log = Logger.getLogger(AttendanceServiceImpl.class);

	public AttendanceServiceImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public List<AttendanceResponseDTO> addAttendance(String empCode) throws InvalidDataException, ParseException {
		UserEntity userEntity = getUser(empCode);
		AttendanceEntity attendanceEntity = new AttendanceEntity();
		attendanceEntity.setAttendanceDate(DateUtility.getDate(new Date()));
		attendanceEntity.setCheckInTime(DateUtility.getDate(new Date()));
		attendanceEntity.setStatus(AttendanceStatus.PRESENT.toString());
		attendanceEntity.setRecordStatus(RecordStstus.ACTIVE.toString());
		attendanceEntity.setAttendanceProcess("LOGGED_IN");
		attendanceEntity.setUserEntity(userEntity);
		attendanceEntity.setCheckInType("LOGGED_IN");
		saveOrUpdateAttendance.save(attendanceEntity);
		return getAttendanceList(empCode);
	}

	public List<AttendanceResponseDTO> getAttendanceList(String empCode) throws InvalidDataException {

		UserEntity entity = getUser(empCode);
		Set<AttendanceEntity> entities = entity.getAttendanceEntities();
		List<AttendanceResponseDTO> list = new ArrayList<AttendanceResponseDTO>();
		for (AttendanceEntity attendanceEntity : entities) {
			if (attendanceEntity.getRecordStatus().equals(RecordStstus.ACTIVE.toString())) {
				AttendanceResponseDTO dto = convertDomainToDto(attendanceEntity);
				list.add(dto);
			}
		}
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	private AttendanceResponseDTO convertDomainToDto(AttendanceEntity attendanceEntity) {
		AttendanceResponseDTO dto = new AttendanceResponseDTO();
		BeanUtils.copyProperties(attendanceEntity, dto);
		return dto;
	}

	private UserEntity getUser(String empCode) throws InvalidDataException {
		UserEntity userEntity = userDao.getUserByEmpCode(empCode);
		if (userEntity == null) {
			throw new InvalidDataException("Invalid employee code.");
		}
		return userEntity;
	}

	public Map<String, String> getAttandanceStatus(String empCode) throws InvalidDataException {
		try {
			Map<String, String> map = new HashMap<String, String>();
			UserEntity entity = getUser(empCode);
			Set<AttendanceEntity> entities = entity.getAttendanceEntities();
			for (AttendanceEntity attendanceEntity : entities) {
				if (DateUtility.compareTwoDate(attendanceEntity.getAttendanceDate(), new Date())
						&& attendanceEntity.getCheckoOutTime() == null
						&& attendanceEntity.getRecordStatus().equals(RecordStstus.ACTIVE.toString())) {
					map.put("actions", "updateAttendance.do");
					map.put("message", "Checked Out : ");
					map.put("empCode", empCode);
					map.put("id", "" + attendanceEntity.getId());
					map.put("fileActions", "outWithFingerPrint.do");
					map.put("hidden", "");
					return map;
				} else if (DateUtility.compareTwoDate(attendanceEntity.getAttendanceDate(), new Date())
						&& attendanceEntity.getCheckoOutTime() != null
						&& attendanceEntity.getRecordStatus().equals(RecordStstus.ACTIVE.toString())) {
					map.put("actions", "attendance.do");
					map.put("message", "Checked In : ");
					map.put("empCode", empCode);
					map.put("hidden", "none");
					return map;
				}
			}
			map.put("actions", "attendance.do");
			map.put("message", "Checked In : ");
			map.put("empCode", empCode);
			map.put("fileActions", "inWithFingerPrint.do");
			map.put("hidden", "");
			return map;
		} catch (InvalidDataException e) {
			throw e;
		}
	}

	public List<AttendanceResponseDTO> updateAttendance(int id, String empCode)
			throws InvalidDataException, ParseException {
		try {
			AttendanceEntity attendanceEntity = attendanceDao.getAttendanceEntityById(id);
			if (attendanceEntity == null) {
				throw new InvalidDataException("Invalid Attendance Id.");
			}
			attendanceEntity.setCheckoOutTime(DateUtility.getDate(new Date()));
			attendanceEntity.setCheckOutType("LOGGED_IN");
			saveOrUpdateAttendance.save(attendanceEntity);
			UserEntity entity = getUser(empCode);
			Set<AttendanceEntity> entities = entity.getAttendanceEntities();
			List<AttendanceResponseDTO> list = new ArrayList<AttendanceResponseDTO>();
			for (AttendanceEntity attendance : entities) {
				AttendanceResponseDTO dto = convertDomainToDto(attendance);
				list.add(dto);
			}
			return list;
		} catch (InvalidDataException e) {
			throw e;
		}
	}

	public void addAttendance(String empCode, MultipartFile file) throws InvalidDataException, ParseException {
		try {
			UserEntity userEntity = getUser(empCode);
			String filePath = FileUtility.saveFile(file);
			AttendanceEntity attendanceEntity = new AttendanceEntity();
			attendanceEntity.setAttendanceDate(DateUtility.getDate(new Date()));
			attendanceEntity.setCheckInTime(DateUtility.getDate(new Date()));
			attendanceEntity.setStatus(AttendanceStatus.PRESENT.toString());
			attendanceEntity.setRecordStatus(RecordStstus.ACTIVE.toString());
			attendanceEntity.setUserEntity(userEntity);
			attendanceEntity.setCheckInType("FINGER_PRINT");
			attendanceEntity.setInFingerPrintUrl(filePath);
			saveOrUpdateAttendance.save(attendanceEntity);
		} catch (InvalidDataException e) {
			throw e;
		}

	}

	public void updateAttendance(String empCode, MultipartFile file, int id)
			throws ParseException, InvalidDataException {
		try {
			AttendanceEntity attendanceEntity = attendanceDao.getAttendanceEntityById(id);
			if (attendanceEntity == null) {
				throw new InvalidDataException("Invalid Attendance Id.");
			}
			String filePath = FileUtility.saveFile(file);
			attendanceEntity.setCheckoOutTime(DateUtility.getDate(new Date()));
			attendanceEntity.setCheckOutType("FINGER_PRINT");
			attendanceEntity.setOutFingerPrintUrl(filePath);
			saveOrUpdateAttendance.save(attendanceEntity);
			UserEntity entity = getUser(empCode);
			Set<AttendanceEntity> entities = entity.getAttendanceEntities();
			List<AttendanceResponseDTO> list = new ArrayList<AttendanceResponseDTO>();
			for (AttendanceEntity attendance : entities) {
				AttendanceResponseDTO dto = convertDomainToDto(attendance);
				list.add(dto);
			}

		} catch (InvalidDataException e) {
			throw e;
		}
	}

	public String generateAttendanceReport(String empCode) throws InvalidDataException {
		try {
			UserDTO user = usersService.getUserByEmpCode(empCode);
			List<AttendanceResponseDTO> list = getAttendanceList(empCode);
			String path = ExcelGenerator.generateExcel(list, user);
			return path;
		} catch (InvalidDataException e) {
			throw e;
		}
	}

}
