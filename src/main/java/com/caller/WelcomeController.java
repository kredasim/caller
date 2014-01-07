package com.caller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caller.model.User;
import com.caller.service.CallService;
import com.caller.service.UserService;

@Controller
public class WelcomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CallService callService;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
 
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		model.addAttribute("verified", userService.isUserActivated(name));
		return "home";
 
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = "sendActivationCode")
	public String sendActivationCode() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    userService.sendActivationCode(name);
		return "redirect:welcome";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = "activate")
	public String activate(@RequestParam("activationNumber") String activationNumber) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    
		userService.activateUser(name, activationNumber);
		return "redirect:welcome";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = "makeCall")
	public String makeCall(@RequestParam("to") String toNumber, @RequestParam("text") String text) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    
		callService.makeCall(name, toNumber, text);
		return "redirect:welcome";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = "makeConferenceCall")
	public String makeConferenceCall(@RequestParam("numbers") String numbers, 
			@RequestParam("conferenceRoomName") String conferenceRoomName) {
		String[] splittedNumbers = numbers.split(",");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
		callService.makeConferenceCall(name, splittedNumbers, conferenceRoomName);
		return "redirect:welcome";
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";
 
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("user") User user) {
		userService.registerUser(user);
		return "login";
	}
	
	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}
}
