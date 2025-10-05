package Array2;

import java.util.Arrays;

public class sortingstrings {

	public static void main(String[] args) {

		String s[]= {"scott","john","david","sohail"};
		
		System.out.println("Before sorting...."+Arrays.toString(s));
		
		Arrays.sort(s);
		
		System.out.println("After Sorting...."+Arrays.toString(s));
	}

}
