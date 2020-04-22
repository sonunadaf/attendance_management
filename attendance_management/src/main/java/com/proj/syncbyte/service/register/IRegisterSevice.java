package com.proj.syncbyte.service.register;

import org.hibernate.HibernateException;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.register.dto.RequestDTO;

public interface IRegisterSevice {

	public void registerUser(RequestDTO requestDTO) throws InvalidDataException, HibernateException;

}
