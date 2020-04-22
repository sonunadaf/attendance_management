package com.proj.syncbyte.dao.getattendance;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.syncbyte.entity.AttendanceEntity;

@Repository
public class GetAttendanceDaoImpl implements IGetAttendanceDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(GetAttendanceDaoImpl.class);

	public GetAttendanceDaoImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public List<AttendanceEntity> getAllAttandanceList(String empCode) {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from AttendanceEntity a inner join UserEntity ue on ue.where empCode=:code order by id desc";
			Query query = session.createQuery(sql);
			query.setParameter("code", empCode);
			List<AttendanceEntity> attendanceList = query.list();
			return attendanceList;
		} catch (HibernateException e) {
			throw new HibernateException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public AttendanceEntity getAttendanceEntityById(int id) {
		Session session = sessionFactory.openSession();
		try {
			AttendanceEntity attendanceEntity = session.get(AttendanceEntity.class, id);
			return attendanceEntity;
		} catch (HibernateException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
