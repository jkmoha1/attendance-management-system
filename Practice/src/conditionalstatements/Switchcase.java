package conditionalstatements;

public class Switchcase {

	public static void main(String[] args) {
		/* 
		Syntax for switch case

		switch(variable)
		{
		case value1:statemnents;
		case value2:statements;
		case value3:statements;
		default:statements;
		}*/
		
		int weekno=15;
		
		switch(weekno)
		{
		case 1:System.out.println("Sunday");break;
		case 2:System.out.println("Monday");break;
		case 3:System.out.println("Teusday");break;
		case 4:System.out.println("Wednesday");break;
		case 5:System.out.println("Thursday");break;
		case 6:System.out.println("Friday");break;
		case 7:System.out.println("Saturday");break;
		default:System.out.println("Invalid number");	
		
		//if break command is not used then upcoming statement without checking the case.
		
		}
		
	}

}
