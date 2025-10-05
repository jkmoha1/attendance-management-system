package Day1;

public class Assignmentoperators {

	public static void main(String[] args) {
		
    //Assignment operators are =  +=  -=  *=  /=  %=
		
		int a=10;
		a+=5;       //a=a+5;
		System.out.println(a);
		
		int b=10;
		
		b-=5; 		//b==b-5
		System.out.println(b);
		
		int c=10;
		
		c*=2;        //c=c*2;
		System.out.println(c);
		
		int d=10;
		
		d/=2;		//d=d/2;
		System.out.println(d);
		
		int e=10;
		e%=2;		//e=e%2;
		System.out.println(e);
		
		//Conditional/ternary operator
		
		//syntax is variable=expression? result1:result2;
		
		int g=200,h=100;
		int x=(g>h)? g:h;
		System.out.println(x); //if expression is true then g value is assigned to x and if it is false then b value will be returned as result
		
		int y=(1==2)?200:100;
		System.out.println(y); //result will be 100 as it is false.
		
		// if expression is true first vale will be assigned if false second value will be assigned
		
		int person_age=30;
		String res=(person_age>18)? "Eligible": "Not eligible";
		System.out.println(res);
		
		int j=50,k=70;
		
		j+=20;
		System.out.println("J is:"+ j);
		k-=20;
		System.out.println("K is:"+ k);
		
		System.out.println(("J is:"+ j)+(" and ")+("K is:"+ k));
	
	}

}
