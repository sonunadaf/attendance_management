package com.proj.syncbyte.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.proj.syncbyte.controller.user.dto.UserDTO;

public class SessionUtility {

	public static UserDTO loggedInUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDTO responseDTO = (UserDTO) session.getAttribute("dto");
		if (responseDTO == null) {
			return null;
		}
		return responseDTO;
	}

}
