package com.proj.syncbyte.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "employee_code")
	private String empCode;
	private String password;
	@Column(name = "DOB")
	private Date dateOfBirth;
	@Column(name = "finger_print_path")
	private String fingerPrintPath;
	@Column(name = "user_profile_pict")
	private String userProfilePath;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "record_status")
	private String recordStatus;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UserTypeEntity typeEntity;
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<AttendanceEntity> attendanceEntities;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFingerPrintPath() {
		return fingerPrintPath;
	}

	public void setFingerPrintPath(String fingerPrintPath) {
		this.fingerPrintPath = fingerPrintPath;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public UserTypeEntity getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(UserTypeEntity typeEntity) {
		this.typeEntity = typeEntity;
	}

	public String getUserProfilePath() {
		return userProfilePath;
	}

	public void setUserProfilePath(String userProfilePath) {
		this.userProfilePath = userProfilePath;
	}

	public Set<AttendanceEntity> getAttendanceEntities() {
		return attendanceEntities;
	}

	public void setAttendanceEntities(Set<AttendanceEntity> attendanceEntities) {
		this.attendanceEntities = attendanceEntities;
	}

}
