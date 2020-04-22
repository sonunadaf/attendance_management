package com.proj.syncbyte.service.user;

import java.util.List;

import org.hibernate.HibernateException;

import com.proj.syncbyte.controller.user.dto.UserDTO;

public interface IUsersService {

	public UserDTO getUserByEmpCode(String empCode);

	public List<UserDTO> getUserList() throws HibernateException;

	public void deleteUser(String empCode) throws HibernateException;

}
