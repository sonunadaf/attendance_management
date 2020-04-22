package com.proj.syncbyte.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.register.dto.RequestDTO;
import com.proj.syncbyte.dao.user.IUserDao;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.utility.FileUtility;

@Service
public class UserValidation {

	@Autowired
	private IUserDao dao;

	public UserValidation() {
		// TODO Auto-generated constructor stub
	}

	public void validate(RequestDTO requestDTO) throws InvalidDataException {

		if (requestDTO.getName() == null || requestDTO.getName() == "" || requestDTO.getName().length() == 0) {
			throw new InvalidDataException("Name is required field.");
		}
		if (requestDTO.getEmpCode() == null || requestDTO.getEmpCode() == "" || requestDTO.getEmpCode().length() == 0) {
			throw new InvalidDataException("Employee code is required field.");
		}
		if (requestDTO.getDateOfBirth() == null) {
			throw new InvalidDataException("D.O.B is required field.");
		}
		if (requestDTO.getPassword() == null || requestDTO.getPassword() == ""
				|| requestDTO.getPassword().length() == 0) {
			throw new InvalidDataException("Password is required field.");
		}
		if (requestDTO.getFile() == null || !FileUtility.isImageFile(requestDTO.getFile())) {
			throw new InvalidDataException("Finger-print format image is required.");
		}
		UserEntity user = dao.getUserByEmpCode(requestDTO.getEmpCode());
		if (user != null) {
			throw new InvalidDataException("User already exist.");
		}
	}
}
