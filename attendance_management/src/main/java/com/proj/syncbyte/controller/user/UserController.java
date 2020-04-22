package com.proj.syncbyte.controller.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.entity.UserEntity;
import com.proj.syncbyte.service.user.IUsersService;
import com.proj.syncbyte.utility.SessionUtility;

@Controller
@RequestMapping("*.do")
public class UserController {
	@Autowired
	private IUsersService usersService;
	private static final Logger log = Logger.getLogger(UserController.class);

	public UserController() {
		log.info("Created: " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "getAllEmployee.do", method = RequestMethod.GET)
	public String getAllEmployees(HttpServletRequest request, Model model) {
		try {
			UserDTO dto = SessionUtility.loggedInUser(request);
			if (dto == null || !dto.getUserTypeRole().equals("ADMIN")) {
				model.addAttribute("error", "Un-Authorized user");
				return "LogIn";
			}
			List<UserDTO> list = usersService.getUserList();
			model.addAttribute("list", list);
			return "EmplyeesPage";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "EmplyeesPage";
		}

	}

	@RequestMapping(value = "deleteEmp.do", method = RequestMethod.GET)
	public String deleteUser(@RequestParam String empCode, HttpServletRequest request, Model model) {
		try {
			UserDTO dto = SessionUtility.loggedInUser(request);
			if (dto == null || !dto.getUserTypeRole().equals("ADMIN")) {
				model.addAttribute("error", "Un-Authorized user");
				return "LogIn";
			}
			usersService.deleteUser(empCode);
			List<UserDTO> list = usersService.getUserList();
			model.addAttribute("list", list);
			return "EmplyeesPage";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "EmplyeesPage";
		}

	}

}
