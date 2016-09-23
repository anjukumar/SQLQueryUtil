import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Password {
	public static void main(String[] args) throws UnsupportedEncodingException 
	{
		Scanner sc = new Scanner(System.in);
		Query q = new Query();
		
		System.out.println("Enter Username : ");
		String username = sc.nextLine();
		System.out.println("Enter password : ");
		String password = sc.nextLine();
		
		if(username.equals("ora1") && password.equals("ora1"))
			try {
				q.query();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			System.out.println("Password or Username Invalid");
	}
}