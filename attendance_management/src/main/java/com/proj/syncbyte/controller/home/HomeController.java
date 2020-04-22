package com.proj.syncbyte.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.syncbyte.controller.user.dto.UserDTO;
import com.proj.syncbyte.utility.SessionUtility;

@Controller
@RequestMapping("*.do")
public class HomeController {

	private static final Logger log = Logger.getLogger(HomeController.class);

	public HomeController() {
		log.info("Created: " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		UserDTO dto = SessionUtility.loggedInUser(request);
		if (dto == null)
			return "LogIn";
		model.addAttribute("dto", dto);
		return "UserHome";
	}

}
