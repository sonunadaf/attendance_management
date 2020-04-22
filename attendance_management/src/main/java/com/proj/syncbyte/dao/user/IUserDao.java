package com.proj.syncbyte.dao.user;

import java.util.List;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.entity.UserEntity;

public interface IUserDao {

	public UserEntity getUserByEmpCode(String empCode);

	public UserEntity getUserById(int id);

	public List<UserEntity> getAllUser();

	public void deleteUser(String empCode);

}
