package ZZZZ;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Contact {
	private String name;
	private String email;
	private String phoneNum;
	
	public Contact(String name,String email,String phoneNum) {
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
	}
	
	public String getname() {
		return name;
	}
	
	public String getemail() {
		return email;
	}
	public String getphoneNum() {
		return phoneNum;
	}
	
	public String toString(){
		return name + "," + email + "," + phoneNum;
	}
	
	public static List<Contact> loadContacts(String csvFile){
	 List<Contact>contacts = new ArrayList<>();
	 try {
		 List<String>lines = Files.readAllLines(Path.of(csvFile));
		 for(String line : lines) {
			 String[]data = line.split(",");
			 if(data.length == 3) {
				 String name = data[0];
				 String email = data[1];
				 String phoneNum = data[2];
				 Contact contact = new Contact(name,email,phoneNum);
				 contacts.add(contact);
			 }
		 }
	 }catch(IOException e) {
		 e.printStackTrace();
	 }
	 return contacts;
   }
	
	public static void saveContacts(String csvFile, List<Contact>contacts) {
		try {
			List<String>lines = new ArrayList<>();
			for(Contact contact : contacts) {
				lines.add(contact.toString());
			}
			Files.write(Path.of(csvFile), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addContact(List<Contact>contacts) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("名前を入力してください：");
		String name = scanner.nextLine();
		System.out.print("メールアドレスを入力してください：");
		String email = scanner.nextLine();
		System.out.print("電話番号を入力してください：");
		String phone = scanner.nextLine();

		Contact contact = new Contact(name, email, phone);	
		contacts.add(contact);
		
	}
	
	public static void deleteContact(List<Contact>contacts, List<Integer>indices ) {
		if(contacts.isEmpty()) {
			System.out.println("連絡先がみつかりません。");
			return;
		}
		System.out.println("削除する連絡先の番号を入力してください：");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] indicesArray = input.split(" ");
		
		List<Integer> indicesToDelete = new ArrayList<>();
		for(String indexString : indicesArray) {
			try {
				int index = Integer.parseInt(indexString);
				if(index >= 0 && index < contacts.size()) {
					indicesToDelete.add(index);
				}else {
					System.out.println("無効な連絡先番号です：" + index);
				}
			}catch(NumberFormatException e){
				System.out.println("無効な入力です：" + indexString);
			}
		}
		if(!indicesToDelete.isEmpty()) {
			indicesToDelete.sort(Collections.reverseOrder());
			
			System.out.println("以下の連絡先を削除しました：");
			for(int index : indicesToDelete) {
				Contact deletedContact = contacts.remove(index);
				System.out.println(deletedContact);
			}
		}else {
			System.out.println("削除する連絡先が指定されていません。");
		}
		scanner.close();
	}
	public static void showContacts(List<Contact>contacts) {
		if(contacts.isEmpty()) {
			System.out.println("連絡先が見つかりません。");
		}else {
			System.out.println("連絡先：");
			for(int i = 0;i < contacts.size();i++) {
				Contact contact = contacts.get(i);
				System.out.println(i + " ," + contact.toString());
			}
		}
	}
}