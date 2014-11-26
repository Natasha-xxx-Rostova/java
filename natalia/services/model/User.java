package ru.natalia.services.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	public String name;
	public String email;
	public String id;
	public String password;
	
	public User(String Name,String Email, String Id, String Password){
	 this.name=Name;
	 this.email=Email;
	 this.id=Id;
	 this.password=Password;
	}
	public User()
	{
		this.name=null;
		 this.email=null;
		 this.id=null;
		 this.password=null;
	}
	public void readUserData( String path)
	{
		List<String> userData = new ArrayList<String>();
		try {
			Scanner in= new Scanner(new File(path));
			while(in.hasNextLine())
				userData.add(in.nextLine());
			in.close();
			email= userData.get(0);
			password = userData.get(1);
			name = userData.get(2);
			id = userData.get(3);
			//return user;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return null;
	}

}
