package Array2;

public class SearchingElementinArray {

	public static void main(String[] args) {

		int a[]= {100,21,60,50,40,45,30,45};
		int search_element=62;
		
		boolean status=false;	//false:not found and true: found
		
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==search_element)
			{
				System.out.println("Element found");
				status=true;
				break;
			}
		}
		if(status==false) {	
		System.out.println("Element not found");
		}
	}
	

}
