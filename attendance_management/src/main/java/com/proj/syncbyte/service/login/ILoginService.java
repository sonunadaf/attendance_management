package com.proj.syncbyte.service.login;

import org.hibernate.HibernateException;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.user.dto.UserDTO;

public interface ILoginService {

	public UserDTO authenticate(String empCode, String password) throws InvalidDataException, HibernateException;

}
