package com.proj.syncbyte.dao.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.service.attendance.IAttendanceService;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IAttendanceService attendanceService;
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public UserEntity getUserByEmpCode(String empcode) {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from UserEntity where empCode=:cd";
			Query query = session.createQuery(sql);
			query.setParameter("cd", empcode);
			UserEntity entity = (UserEntity) query.uniqueResult();
			return entity;
		} catch (HibernateException e) {
			throw new HibernateException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public UserEntity getUserById(int id) {
		Session session = sessionFactory.openSession();
		try {
			UserEntity entity = session.get(UserEntity.class, id);
			return entity;
		} catch (HibernateException e) {
			throw new HibernateException("Error while fetch the data.");
		} finally {
			session.close();
		}
	}

	public List<UserEntity> getAllUser() {
		String sql = "from UserEntity where recordStatus=:rs order by id desc";
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(sql);
			query.setParameter("rs", "ACTIVE");
			List<UserEntity> users = query.list();
			return users;
		} catch (HibernateException e) {
			throw new HibernateException("Error while fetch the data.");
		} finally {
			session.close();
		}
	}

	public void deleteUser(String empCode) {
		String sql = "delete from UserEntity where empCode=:emp";
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(sql);
			query.setParameter("emp", empCode);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new HibernateException("Error while fetch the data.");
		} finally {
			session.close();
		}

	}
}
