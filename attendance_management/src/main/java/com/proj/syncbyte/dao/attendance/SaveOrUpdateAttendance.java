package com.proj.syncbyte.dao.attendance;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.syncbyte.entity.AttendanceEntity;

@Repository
public class SaveOrUpdateAttendance implements ISaveOrUpdateAttendance {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(SaveOrUpdateAttendance.class);

	public SaveOrUpdateAttendance() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public void save(AttendanceEntity attendanceEntity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(attendanceEntity);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new HibernateException(e.getMessage());
		} finally {
			session.close();
		}
	}
}
