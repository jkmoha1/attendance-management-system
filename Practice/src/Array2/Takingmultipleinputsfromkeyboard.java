package Array2;

import java.util.Scanner;

public class Takingmultipleinputsfromkeyboard {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		
		/*System.out.println("Enter first number:");
		int num1=sc.nextInt();
		
		System.out.println("Enter second numbe:r");
		int num2=sc.nextInt();
		
		System.out.println("Enter third number:");
		int num3=sc.nextInt();
		
		System.out.println("Total is:"+(num1+num2+num3));
		*/
		
		System.out.println("Enter name:");
		String name=sc.next();	
		System.out.println("Your name is :"+name);
		
		System.out.println("Enter your age:");
		int age=sc.nextInt();
		System.out.println("Your age is :"+age);
		
		System.out.println("Enter unknown value");
		Object value=sc.next();
		System.out.println(value);




	}

}
