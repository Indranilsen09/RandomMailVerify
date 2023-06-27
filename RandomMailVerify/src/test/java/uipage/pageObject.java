package uipage;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageObject 
{
	
	
	static String generateMail() 
	{
		
		String email ="";
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		String currentDate = LocalDate.now().toString().replace("-","");
		String domain = "@gmail.com";
		
		while(email.length()<8) 
		{
			int i = (int) (Math.random()*26);
			email += alphabets.substring(i,i+1);
			
		}
		email += currentDate.substring(2,currentDate.length());
		email += domain;
		
		return email;
		
	}
	
	

}
