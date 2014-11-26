package ru.natalia.services.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {
	public String mailTo;
	public String mailFrom;
	public  Mail()
	{
		mailTo = null;
		mailFrom = null;
	}
	/*public boolean equalsMailsTo(String mail)
	{
		
		
	}*/
	public void readMailData(String path) throws FileNotFoundException
	{
		Pattern pattern;
		Matcher match;
		pattern = Pattern.compile("^\\s+To:$");
		Scanner in= new Scanner(new File(path));
		String mailData=null;
		while(in.hasNextLine())
			mailData+=in.nextLine();
		in.close();
		match = pattern.matcher(mailData);
	}
}
