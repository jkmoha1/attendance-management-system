package Array2;

public class foreachloopsearchelement {

	public static void main(String[] args) {

		int a[]= {100,30,10,50,45,74,200,30,30};
		int search_element=30;
		
		boolean result=false;
	
		for(int x:a) {
			if(x==search_element)
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
