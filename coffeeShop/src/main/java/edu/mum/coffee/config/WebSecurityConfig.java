package edu.mum.coffee.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;
import edu.mum.coffee.service.PersonService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user/**").access("hasRole('USER')").antMatchers("/admin/**")
				.access("hasRole('ADMIN')").antMatchers("/", "/home", "/index").permitAll().anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
//				.failureUrl("/login?error")
				.permitAll()
				.successHandler(customSuccessHandler)
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").permitAll();
	}

	@Autowired
	PersonService personService;
//	PersonRepository personRepository;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		PersonService personService = new PersonService();
		List<Person> findAllPersons = personService.findAllPersons();
		for (Person person : findAllPersons) {
			String email = person.getEmail();
			System.out.println("emaillllllllllll:"+email);
//			if (email != null)
//				auth.inMemoryAuthentication().withUser(email).password("123").roles("USER");
//			else {
//				System.out.println("this user does not exist");
//			}
		}
//		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user@gmail.com").password("123").roles("USER");
	}
}