package edu.mum.coffee.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping("/user")
public class OrderController {
	
	@Autowired
	public PersonService personService;
	@Autowired
	public OrderService orderService;

	@GetMapping({ "/createOrder" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createOrder");
		Order order = new Order();
		order.setOrderDate(new Date());
		modelAndView.addObject(order);
		return modelAndView;
	}

	@RequestMapping("/saveOrder")
	public String saveProductPage(@ModelAttribute(value = "order") Order order) {
		System.out.println(order);
		orderService.save(order);
		return "redirect:/user";
	}
	@RequestMapping("/profile")
	public String profilePage(Map<String,Object> map,HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				System.out.println("Username:"
				+ securityContextImpl.getAuthentication().getName());
				String name = securityContextImpl.getAuthentication().getName();
		List<Person> findByEmail = personService.findByEmail(name);
		map.put("person", findByEmail.get(0));
		return "profile";
	}
	@ModelAttribute
	public void getProduct(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if(id != null){
			map.put("person",personService.findById(Long.valueOf(id)));
			System.out.println("111111");
		}
		System.out.println("2222222222");
	}
	@RequestMapping("/updateProfile")
	public String updatePersonAndSavePage(Person person) {
		System.out.println("333333");
		personService.savePerson(person);
		System.out.println(person);
		return "redirect:/user";
	}
}
