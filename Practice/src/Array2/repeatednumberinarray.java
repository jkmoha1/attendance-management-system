 package Array2;

public class repeatednumberinarray {

	public static void main(String[] args) {

		int a[]= {45,10,54,45,10,10,84,96,54,74,84};
		int search_duplicate=10;
		int count=0;
		
		for(int value:a) 
		{
			if(value==search_duplicate)
			{
				count++;
			}
		}
		System.out.println(count);
	}

}
