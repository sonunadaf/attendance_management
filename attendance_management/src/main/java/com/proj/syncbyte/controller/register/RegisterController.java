package com.proj.syncbyte.controller.register;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.register.dto.RequestDTO;
import com.proj.syncbyte.service.register.IRegisterSevice;

@Controller
@RequestMapping("*.do")
public class RegisterController {

	@Autowired
	private IRegisterSevice registerSevice;
	private static final Logger log = Logger.getLogger(RegisterController.class);

	public RegisterController() {
		log.info("Created : " + this.getClass().getSimpleName());
	}

	@InitBinder
	public void InitBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String registerUser() {
		return "HomePage";

	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerUser(RequestDTO requestDTO, HttpServletRequest request, Model model) {
		try {
			registerSevice.registerUser(requestDTO);
			model.addAttribute("status", "User Register Successfully.");
			return "LogIn";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			model.addAttribute("requestObj", requestDTO);
			log.error(e.getMessage());
			return "HomePage";
		} catch (HibernateException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			model.addAttribute("requestObj", requestDTO);
			log.error(e.getMessage());
			return "HomePage";
		}

	}

}
