package Day1;

public class Arithmeticoperators {

	public static void main(String[] args) {
	
	// 1.Arithmetic operations
	
			
			int a=20, b=10;
			
			System.out.println("sum of a and b is:"+(a+b));
			
			System.out.println("diff of a and b is:"+(a-b));
			
			System.out.println("multiplication of a and b is:"+(a*b));
			
			System.out.println("division of a and b is:"+(a/b)); // This will return the coefficient value
			
			System.out.println("Modulo division of a and b is:"+(a%b)); //This will return the reminder value

			
			// 2.Relational/comparision operators > >= < <= !=  ==
						// returns boolean value true/false
						
					
						
						System.out.println(a>b);
						System.out.println(a<b);
						System.out.println(a>=b);
						System.out.println(a<=b);
						System.out.println(a!=b);
						System.out.println(a==b);
						
						b=20;
						
						System.out.println(a>=b);
						System.out.println(a<=b);
						System.out.println(a!=b);
						System.out.println(a==b);

						boolean res=a>b;
						
						System.out.println(res);

	}
}
