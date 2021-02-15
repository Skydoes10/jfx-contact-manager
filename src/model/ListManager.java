package model;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public void importContacts(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line = br.readLine();
	    while(line!=null){
	      String[] parts = line.split(";");
	      addContact(parts[0],parts[1]);
	      line = br.readLine();
	    }
	    br.close();
	}
	
	public void exportContacts(String fileName) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(fileName);

	    for(int i=0;i<contacts.size();i++){
	      addContact myContact = contacts.get(i);
	      pw.println(myContact.getName()+";"+myContact.getEmail());
	    }

	    pw.close();
	}
}
