package model;

public class addContact {
	private String name;
	private String email;
	
	public addContact(String n, String e) {
		name = n;
		email = e;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
}
