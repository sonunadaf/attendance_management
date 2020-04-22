package com.proj.syncbyte.service.login;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.dao.user.IUserDao;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.entity.UserTypeEntity;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private IUserDao userDao;
	private static final Logger log = Logger.getLogger(LoginServiceImpl.class);

	public LoginServiceImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public UserDTO authenticate(String empCode, String password)
			throws InvalidDataException, HibernateException {
		UserEntity entity = userDao.getUserByEmpCode(empCode);
		if (entity == null || !entity.getPassword().equals(password)) {
			throw new InvalidDataException("Invalid user credentials.");
		}
		UserDTO response = convertDomainToDto(entity);
		return response;
	}

	private UserDTO convertDomainToDto(UserEntity entity) {
		UserDTO responseDTO = new UserDTO();
		BeanUtils.copyProperties(entity, responseDTO);
		UserTypeEntity userType = entity.getTypeEntity();
		if (userType != null) {
			responseDTO.setUserTypeRole(userType.getCode());
		}
		return responseDTO;
	}

}
