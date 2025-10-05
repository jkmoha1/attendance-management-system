package conditionalstatements;

public class findlargestnumber {
	public static void main(String[] args) {
		
		int a=40,b=20,c=30;
		
		if(a>b&&a>c)
		{
			System.out.println("largest number is :"+a);
		}
		else if(b>c&&  b>a)
		{
			System.out.println("largestnumber is :"+b);
		}
		else
		{
			System.out.println("largest number is :"+c);
		}
	}

}
