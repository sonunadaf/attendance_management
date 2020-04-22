package com.proj.syncbyte.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.syncbyte.entity.UserEntity;

@Repository
public class SaveOrUpdateDao implements ISaveOrUpdateUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(SaveOrUpdateDao.class);

	public SaveOrUpdateDao() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public void saveUser(UserEntity entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new HibernateException(e.getMessage());
		} finally {
			session.close();
		}
	}

}
