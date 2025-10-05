package Assignment;

public class smallestnumberifelse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=20,b=30,c=40,d=15;
		
		if(a<b&&a<c&&a<d)
		{
			System.out.println(a);
		}
		else if(b<c&&b<d&&b<a)
		{
			System.out.println(b);
		}
		else if(c<d&&c<a&&c<b)
		{
			System.out.println(c);
		}
		else 
		{
		System.out.println(d);	
		}
	}

}
