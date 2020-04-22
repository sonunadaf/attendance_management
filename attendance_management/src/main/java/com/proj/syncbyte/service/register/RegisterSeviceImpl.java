package com.proj.syncbyte.service.register;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.register.dto.RequestDTO;
import com.proj.syncbyte.dao.ISaveOrUpdateUserDao;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.utility.FileUtility;
import com.proj.syncbyte.utility.Status;
import com.proj.syncbyte.validation.UserValidation;

@Service
public class RegisterSeviceImpl implements IRegisterSevice {

	@Autowired
	private UserValidation validation;
	@Autowired
	private ISaveOrUpdateUserDao saveOrUpdateDao;
	private static final Logger log = Logger.getLogger(RegisterSeviceImpl.class);

	public RegisterSeviceImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
		System.out.println("Created");
	}

	public void registerUser(RequestDTO requestDTO) throws InvalidDataException, HibernateException {
		try {
			validation.validate(requestDTO);
			String saveFilePath = FileUtility.saveFile(requestDTO.getFile());
			UserEntity entity = convertDtoToEntity(requestDTO);
			entity.setFingerPrintPath(saveFilePath);
			saveOrUpdateDao.saveUser(entity);
		} catch (InvalidDataException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		}

	}

	public UserEntity convertDtoToEntity(RequestDTO requestDTO) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(requestDTO, entity);
		entity.setRecordStatus(Status.ACTIVE.toString());
		return entity;

	}

}
