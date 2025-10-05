package Array2;

import java.util.Arrays;

public class Sortingelementsinarray {

	public static void main(String[] args) {

		 int a[]= {100,800,60,120,40,200,150};
		 
		 System.out.println("Before Sorting....");
		 
		 /*for(int value:a) {
			 System.out.println(value);
		 }*/
		 
		 //To Read all elements in single statement no need to write for loop as we have predefined class method called to string.
		 System.out.println(Arrays.toString(a));
		 //To sort array there is method Arrays.sort();
		 Arrays.sort(a);
		 System.out.println("After Sorting.....");
		 System.out.println(Arrays.toString(a));
	}

}
