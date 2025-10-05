package Day1;

public class logicaloperators {

	public static void main(String[] args) {
		
		//3.Logical operators  &&  ||   !
				//returns boolean value - true/false
				// works between two boolean values
				
				boolean x=true;
				boolean y=false;
				
				System.out.println(x&&y);
				System.out.println(x||y);
				System.out.println(!x);
				System.out.println();

				boolean b1=20>10;
				boolean b2=20<10;
				
				System.out.println(b1&&b2);
				System.out.println(b1||b2);
				System.out.println(!b1);
				System.out.println(!b2);
				
				
				
				/*
				 // case-1
				int a=10;
				//a=a+1;
				//System.out.println(a);
				
				a++;
				System.out.println(a);
				*/
				
				/*
				//case-2 - Post increment
				
				int a=10;
				int res=a++;
				System.out.println(res);  // answer will be 10.
				System.out.println(a);  // answer will be 11.
				
				// in this execution first assignment is happening and then increment is happened.
				*/
				
				/*
				//case -3 pre increment
				
				int a=10;
				int res=++a;
				System.out.println(res); // answer will be 11
				System.out.println(a);  // answer will be 11
				
				// in this execution first increment will happen and then assignment will happen.
				*/
				
				//Decrement
				
				/*int a=10;
			
				int res=a--;
				System.out.println(res); //output is 10
				*/
				int a=10;
				int res=--a;
				System.out.println(res); // out put is 9
	}

}
