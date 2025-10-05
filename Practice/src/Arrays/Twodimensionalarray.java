package Arrays;

/*
1.Declare an array
2.Add values into array
3.Find size of an array
4.Read singke value from an array
5.Read multiple values from an array
*/

public class Twodimensionalarray {

	public static void main(String[] args) {
		
		//Declare an array
		//Approach 1
		int a[][]=new int [3][2];
		
		a[0][0]=100;
		a[0][1]=200;
		
		a[1][0]=300;
		a[1][1]=400;
		
		a[2][0]=500;
		a[2][1]=600;
		
		//Approach 2
		int b[][]= {{100,200},{300,400},{500,600}};
		
		//Find size of an array
		System.out.println("length of rows :"+a.length);
		System.out.println("length of columns :"+a[0].length);
		
		//read single value from an array
		System.out.println(a[2][1]);
		
		//Read multiple rows and columns
		
		for (int a)
		
	}

}
