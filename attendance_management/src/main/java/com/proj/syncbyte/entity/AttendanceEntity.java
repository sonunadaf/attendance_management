package com.proj.syncbyte.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "attendance")
public class AttendanceEntity {
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	private int id;
	@Column(name = "status")
	private String status;
	@Column(name = "attendance_date")
	private Date attendanceDate;
	@Column(name = "checked_in_time")
	private Date checkInTime;
	@Column(name = "checked_in_with")
	private String checkInType;
	@Column(name = "checked_out_with")
	private String checkOutType;
	@Column(name = "checked_out_time")
	private Date checkoOutTime;
	@Column(name = "in_finger_print_url")
	private String inFingerPrintUrl;
	@Column(name = "out_finger_print_url")
	private String outFingerPrintUrl;
	@Column(name = "attendance_process")
	private String attendanceProcess;
	@Column(name = "record_status")
	private String recordStatus;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

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

	public String getAttendanceProcess() {
		return attendanceProcess;
	}

	public void setAttendanceProcess(String attendanceProcess) {
		this.attendanceProcess = attendanceProcess;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
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

	public String getInFingerPrintUrl() {
		return inFingerPrintUrl;
	}

	public void setInFingerPrintUrl(String inFingerPrintUrl) {
		this.inFingerPrintUrl = inFingerPrintUrl;
	}

	public String getOutFingerPrintUrl() {
		return outFingerPrintUrl;
	}

	public void setOutFingerPrintUrl(String outFingerPrintUrl) {
		this.outFingerPrintUrl = outFingerPrintUrl;
	}

}
