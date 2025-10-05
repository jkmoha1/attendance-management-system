package Arrays;
/*
1.Declare an array
2.Add values into array
3.Find size of an array
4.Read singke value from an array
5.Read multiple values from an array
*/

public class Singledimensionalarray {

	public static void main(String[] args) {

		//Declaring array
		//Approach
		//This can be used if we know size of an Array.
		//No of indexes needs to be allotted prior
		int a[]=new int[5];
		
		//Adding values into array
		a[0]=100;
		a[1]=200;
		a[2]=300;
		a[3]=400;
		a[4]=500;
		
		//Approach-2
		//This can be used if we do not know the size of Array.
		//Indexes will be automatically alotted.
		int b[]= {100,200,300,400,500};
		
		//Find length of an array.
		
		System.out.println(a.length);
		System.out.println(b.length);
		
		//Read single value for an array
		System.out.println(a[4]); //here 4 is index.index is always n-1
		
		System.out.println(b[3]); //here 3 is index.
		
		//Read multiple values from array
		
		//Using for loop
		/*for (int i=0; i<=4; i++) 
//if we do not know size of array we can write i<=a.length-1 or i<a.length
		{
			System.out.println(a[i]);
		}
		*/
		//enhanced for loop or for each loop..
		
		for (int x:a) 
		{
			System.out.println(x);
		}
		
	}

}
