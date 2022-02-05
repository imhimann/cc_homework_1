package cc_homework_1;
import java.util.Scanner;

public class cc_clean_code {

	public static void main(String[] args) {
		int numberOfCustomers = 0;  // use Intention-Revealing Names
		
		try (Scanner n = new Scanner(System.in)){
			while(numberOfCustomers < 1 || numberOfCustomers > 30) {
				System.out.println("How many of you are there? ");
				numberOfCustomers = n.nextInt();
				
				if(numberOfCustomers > 30) {
					System.out.println("Too many people. Max: 30p");
				} else{
					System.out.println("Invalid amount.");
				}
			}
			displayAllSeats(cinemaSeats());
			bookSeats(numberOfCustomers, cinemaSeats());
		}

		
		
	}

	public static char[][] cinemaSeats() {
		char[][] total_seats = new char[3][10];
		
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 10; column++) {
				total_seats[row][column] = '-';
			}
		}
		return total_seats;
	}
	
	public static void displayAllSeats(char[][] cinemaSeats) {
		
		System.out.println("\t  1   2   3   4   5   6   7   8   9  10\n");
		char row = 'A';

		for(int i = 0; i < 3; i++){
		      System.out.print(row + "\t| ");
		      
		      for(int j = 0; j < 10; j++){
		       System.out.print( cinemaSeats[i][j] + " | "); 
		      }
		      
		      if( i == 0 )
			        System.out.println("  Sheep");
			      else if( i == 1)
			        System.out.println("  Hot");
			      else if (i == 2)
			        System.out.println("  Econ");
		      row++;
		    }
		System.out.println("\n\t\t\t\t '-' – free seats\n\t\t\t\t 'X' – taken");
	}

	public static void bookSeats(int numberOfCustomers, char[][] cinemaSeats) {
		try(Scanner n = new Scanner(System.in)){
			int row = 0, column = 0, counter = 1;
			String ABC = "ABC" , prompt = "Z0";
			
			while(counter <= numberOfCustomers){
				System.out.println("Person " + counter +" / " + numberOfCustomers + " :");
				System.out.println("Choose your seats (for e.g A2 / B4 / C10)");
				
				prompt = n.nextLine();
				String uppercased_Prompt = prompt.toUpperCase();
				row = ABC.indexOf(uppercased_Prompt.charAt(0));
				column = Integer.parseInt(uppercased_Prompt.substring(1))-1;
				
				if(cinemaSeats[row][column]=='X') {
					System.out.println("Seat is taken. Please input again.");
				} else {
					cinemaSeats[row][column] = 'X';
					counter++;
					displayAllSeats(cinemaSeats);
				}
			}
		}
		printReceiptSeats(cinemaSeats);
	}
	
	public static void printReceiptSeats(char[][] cinemaSeats) {
		
		final double PRICE_SHEEP = 3.00;
		final double PRICE_HOT = 14.00;
		final double PRICE_ECONOMY = 7.00;
		
		int countSheep = 0, countHot = 0, countEconomy = 0;
		double sumPrice_Sheep = 0.0, sumPrice_Hot = 0.0, sumPrice_Economy = 0;
		double sumPrice_All;
									
		for(int column = 0; column < 10; column++) {
			if(cinemaSeats[0][column] == 'X') {
				sumPrice_Sheep += PRICE_SHEEP;
				countSheep++;
			} else if(cinemaSeats[1][column] == 'X') {
				sumPrice_Hot += PRICE_HOT;
				countHot++;
			}else if(cinemaSeats[2][column] == 'X') {
				sumPrice_Economy += PRICE_ECONOMY;
				countEconomy++;
			}
		}
		sumPrice_All = sumPrice_Sheep + sumPrice_Hot + sumPrice_Economy;
		
	    System.out.println("-Seats-\t\t-Amt-\t-Price $-\t-Total $-");
	    System.out.printf("Sheep\t\t  %d\t  3.00\t\t   %.2f\n",countSheep,sumPrice_Sheep);
	    System.out.printf("Hot\t\t  %d\t 14.00\t\t   %.2f\n",countHot,sumPrice_Hot);
	    System.out.printf("Economy\t\t  %d\t  7.00\t\t   %.2f\n",countEconomy,sumPrice_Economy);
	    System.out.printf("Total\t\t \t  \t\t   %.2f\n",sumPrice_All);
	}
}
