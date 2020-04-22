package com.proj.syncbyte.dao.getattendance;

import java.util.List;

import com.proj.syncbyte.entity.AttendanceEntity;

public interface IGetAttendanceDao {

	public List<AttendanceEntity> getAllAttandanceList(String empCode);

	public AttendanceEntity getAttendanceEntityById(int id);

}
