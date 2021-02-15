package model;

import java.util.List;
import java.util.ArrayList;

public class ListManager {
	private List<addContact> contacts;
	
	public ListManager() {
		contacts = new ArrayList<>();
	}
	
	public void addContact(String n, String e) {
		contacts.add(new addContact(n, e));
	}
	
	public List<addContact> getContacts(){
		return contacts;
	}
}
