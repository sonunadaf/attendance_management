package com.proj.syncbyte.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.attendance.dto.AttendanceResponseDTO;
import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.dao.ISaveOrUpdateUserDao;
import com.proj.syncbyte.dao.user.IUserDao;
import com.proj.syncbyte.entity.RecordStstus;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.service.attendance.IAttendanceService;

@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private ISaveOrUpdateUserDao saveUserDao;

	private static final Logger log = Logger.getLogger(UsersServiceImpl.class);

	public UsersServiceImpl() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	public UserDTO getUserByEmpCode(String empCode) {
		UserEntity entity = userDao.getUserByEmpCode(empCode);
		UserDTO responseDTO = convertDomainToDto(entity);
		return responseDTO;
	}

	private UserDTO convertDomainToDto(UserEntity entity) {
		UserDTO responseDTO = new UserDTO();
		BeanUtils.copyProperties(entity, responseDTO);
		return responseDTO;
	}

	public List<UserDTO> getUserList() throws HibernateException {
		List<UserEntity> users = userDao.getAllUser();
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserEntity userEntity : users) {
			if (userEntity.getRecordStatus().equals("ACTIVE") && userEntity.getTypeEntity() == null) {
				UserDTO dto = convertDomainToDto(userEntity);
				list.add(dto);
			}

		}
		return list;
	}

	public void deleteUser(String empCode) throws HibernateException {
		UserEntity entity = userDao.getUserByEmpCode(empCode);
		entity.setRecordStatus(RecordStstus.INACTIVE.toString());
		saveUserDao.saveUser(entity);
		// userDao.deleteUser(empCode);
	}

	
}
