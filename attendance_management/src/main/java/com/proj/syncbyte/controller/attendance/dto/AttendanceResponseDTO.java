package com.proj.syncbyte.controller.attendance.dto;

import java.util.Date;

public class AttendanceResponseDTO implements Comparable<AttendanceResponseDTO> {

	private int id;
	private String status;
	private Date attendanceDate;
	private Date checkInTime;
	private Date checkoOutTime;
	private String checkInType;
	private String checkOutType;
	private String fingerPrintUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckoOutTime() {
		return checkoOutTime;
	}

	public void setCheckoOutTime(Date checkoOutTime) {
		this.checkoOutTime = checkoOutTime;
	}

	public String getFingerPrintUrl() {
		return fingerPrintUrl;
	}

	public void setFingerPrintUrl(String fingerPrintUrl) {
		this.fingerPrintUrl = fingerPrintUrl;
	}

	public String getCheckInType() {
		return checkInType;
	}

	public void setCheckInType(String checkInType) {
		this.checkInType = checkInType;
	}

	public String getCheckOutType() {
		return checkOutType;
	}

	public void setCheckOutType(String checkOutType) {
		this.checkOutType = checkOutType;
	}

	public int compareTo(AttendanceResponseDTO at) {
		return getAttendanceDate().compareTo(at.getAttendanceDate());
	}

}
