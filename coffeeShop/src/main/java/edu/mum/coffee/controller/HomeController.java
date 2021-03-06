package edu.mum.coffee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
public class HomeController {
	
	@GetMapping({"/", "/home"})
	public String homePage() {
		return "home";
	}
	@GetMapping({ "/index"})
	public String indexPage() {
		return "index";
	}
	

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	@RequestMapping("/user")
	public String toUserPage(){
		return "userPage";
	}
	@RequestMapping("/admin")
	public String toAdminPage(){
		return "adminPage";
	}
	
	@RequestMapping("/login")
	public String toLoginPage(){
		return "login";
	}
	
	@Autowired
	public PersonService personService;
	@GetMapping({ "/regist" })
	public ModelAndView toRegistPage() {
		ModelAndView modelAndView = new ModelAndView("regist");
		Person person = new Person();
		modelAndView.addObject(person);
		return modelAndView;
	}

	@RequestMapping("/savePerson")
	public String saveProductPage(@ModelAttribute(value = "person") Person person) {
		System.out.println(person);
		personService.savePerson(person);
		return "redirect:/login";
	}
	
}
