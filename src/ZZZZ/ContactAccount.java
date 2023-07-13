package ZZZZ;

import java.util.List;

public class ContactAccount {
	private String csvFile;
	private List<Contact> contacts;
	
	public ContactAccount(String csvFile) {
		this.csvFile = csvFile;
		this.contacts = Contact.loadContacts(csvFile);
	}
	
	private void saveContacts() {
		Contact.saveContacts(csvFile,  contacts);
	}
	public List<Contact>getContacts(){
		return contacts;
	}
	public void addContact(List<Contact>contacts) {
			Contact.addContact(contacts);
			saveContacts();
	}
	
	public void deleteContacts(List<Integer>indices) {
		Contact.deleteContact(contacts,indices);
		saveContacts();
	}
	public void showContacts() {
		System.out.println("名前\tメールアドレス\t電話番号");
		Contact.showContacts(contacts);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContactAccount account = new ContactAccount("contact.csv");
		
		account.addContact(account.getContacts());
		account.showContacts();
		
		account.deleteContacts(null);
		account.showContacts();
		

	}


}