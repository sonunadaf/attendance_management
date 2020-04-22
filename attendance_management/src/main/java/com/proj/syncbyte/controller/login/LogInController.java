package com.proj.syncbyte.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.syncbyte.Exception.InvalidDataException;
import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.service.login.ILoginService;

@Controller
@RequestMapping("*.do")
public class LogInController {

	@Autowired
	private ILoginService loginService;
	private static final Logger log = Logger.getLogger(LogInController.class);

	public LogInController() {
		log.info("Created: " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String authenticate(HttpServletRequest request) {
//		if (SessionUtility.isLoggedIn(request) != null) {
//			return "HomePage";
//		}
		return "LogIn";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String authenticate(@RequestParam String empCode, @RequestParam String password, HttpServletRequest request,
			Model model) {
		try {

			UserDTO responseDTO = loginService.authenticate(empCode, password);
			HttpSession session = request.getSession();
			model.addAttribute("dto", responseDTO);
			session.setAttribute("dto", responseDTO);
			session.setMaxInactiveInterval(300);
			return "UserHome";
		} catch (InvalidDataException e) {
			log.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("password", password);
			return "LogIn";
		} catch (HibernateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("empCode", empCode);
			model.addAttribute("password", password);
			return "LogIn";
		}

	}

	@RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
	public String authenticate(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("dto");
		if (dto != null) {
			session.removeAttribute("dto");
		}
		return "LogIn";

	}
}
