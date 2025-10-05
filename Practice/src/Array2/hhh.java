package Array2;

public class hhh {

	public static void main(String[] args) {

		int a[]= {50,60,70,65,75,84,91};
		int search_result=45;
		
		boolean result=false;
		
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==search_result)
			{
				System.out.println("Element found");
				result=true;
				break;
			}
			
			}
		if (result==false) {
			System.out.println("Element not found");
		}
	}

}
