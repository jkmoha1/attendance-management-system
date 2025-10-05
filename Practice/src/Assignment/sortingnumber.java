package Assignment;

import java.util.Arrays;

public class sortingnumber {

	public static void main(String[] args) {

		int a[]= {100,40,30,54,12,50,70};
		
		System.out.println("Before sorting.....");
		System.out.println(Arrays.toString(a));
		
		Arrays.sort(a);
		System.out.println("After sorting......");
		System.out.println(Arrays.toString(a));
		
	}

}
