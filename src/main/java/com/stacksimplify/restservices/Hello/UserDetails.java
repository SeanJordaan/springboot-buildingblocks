package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;

public class UserDetails {

	private String firstname;
	private String lastname;
	private String city;
	
	
	
	//Generated the Fields
	public UserDetails(String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	
	//Generated the Getters and setters 
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@GetMapping("/helloWorld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Sean", "Jordaan","JHB");
	}
	
}
