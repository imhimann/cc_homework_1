package cc_homework_1;

import java.util.Scanner;

public class cc_bad_code {

	public static void main(String[] args){
		   
	    char[][] cineSeat = new char[3][10];
	    
	    for( int a = 0; a < 3; a++){
	      for( int b = 0; b < 10; b++){
	    	  cineSeat[a][b] = '-';
	      }
	    }
	    
	    try (Scanner n = new Scanner(System.in)) {
			System.out.println("How many of you are there?");
			int c = n.nextInt();
			
			if( c > 30) {
			  System.out.println("Too many people. Max: 30");
			  c = n.nextInt(); }
			
			display(cineSeat);
			
			for(int count = 1; count <= c; count++){
	
			  cineSeatChosen(cineSeat);
			  display(cineSeat);
			}
		}
	    price(cineSeat);
	    
	    
	    
	  }
	  
	// ------------------------------------------------------------------------------------------------------------------//
	  
	  //                                                                 ----Seat Picked
	  public static void cineSeatChosen(char[][] cS){
	   try (Scanner n = new Scanner(System.in)) {
		String letters = "ABC";
	   
		String seatPrompt = n.nextLine();
		String seatChose = seatPrompt.toUpperCase();                      // Uppercase letters
	   
		int row = letters.indexOf( seatChose.charAt(0) );
		int column = Integer.parseInt(seatChose.substring(1)) - 1; 
	   
		while(cS[row][column] == 'X'){                                      // Seat is taken
			System.out.println("Seat is taken. Please input again.");
	    
			seatPrompt = n.nextLine();
			seatChose = seatPrompt.toUpperCase();
	    
			row = letters.indexOf( seatChose.charAt(0) );
			column = Integer.parseInt(seatChose.substring(1)) - 1;
	    
	   }
	    
		cS[row][column] = 'X';
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	  }
	  
	  //                                                                  ----Price
	  public static void price(char[][] cS){
	   
	    final double PRICE_SHEEP = 3.00;
	    final double PRICE_HOT = 14.00;
	    final double PRICE_ECONOMY = 7.00;
	    
	    double totalSheep = 0.00, totalHot = 0.00, totalEcon = 0.00;
	    double total;
	    
	    int countSheep = 0, countHot = 0, countEcon = 0;
	    for(int i = 0; i < 10; i++){
	      if( cS[0][i] == 'X'){
	       totalSheep += PRICE_SHEEP;                             // total Price of Sheep seats that are taken
	       countSheep++;                                          // total no. of Sheep seats that are taken
	      }
	       if( cS[1][i] == 'X'){
	       totalHot += PRICE_HOT;                            // total Price of Hot seats that are taken
	       countHot++;                                          // total no. of Hot seats that are taken
	      }
	       if ( cS[2][i] == 'X'){
	       totalEcon += PRICE_ECONOMY;                            // total Price of Econ seats that are taken
	       countEcon++;                                          // total no. of Econ seats that are taken
	      }
	    }
	    
	    total = totalSheep + totalHot + totalEcon;
	    receiptSeats(totalSheep, totalHot, totalEcon, total, countSheep, countHot, countEcon);
	    
	    
	  }
	  
	  //                                                                      Display Seats
	  public static void display(char[][] cS){
	    char row = 'A';
	    
	    System.out.println("\t  1   2   3   4   5   6   7   8   9  10\n");
	    for(int i = 0; i < 3; i++){
	      System.out.print(row + "\t| ");
	      
	      for(int j = 0; j < 10; j++){
	       System.out.print( cS[i][j] + " | "); 
	      }
	      if( i == 0 )
	        System.out.print("  Sheep");
	      else if( i == 1)
	        System.out.print("  Hot");
	      else
	        System.out.print("  Econ");
	      System.out.println();
	      row++;
	    }
	    
	    System.out.println("\n\t\t\t\t '-' – free seats\n\t\t\t\t 'X' – taken");
	    
	  }
	  
	  //                                                                      Receipt
	  public static void receiptSeats(double totalSheep, double totalHot, double totalEcon, double total,
	                                 int countSheep, int countHot, int countEcon){
	    
	    System.out.println("\n–––––––––––––––––––––––––––– BILL ––––––––––––––––––––––––––––\n");
	    
	    System.out.println("-Seats-\t\t-Amt-\t-Price $-\t-Total $-");
	    System.out.printf("Sheep\t\t    %d\t  3.00\t  %.2f\n",countSheep,totalSheep);
	    System.out.printf("Hot\t\t    %d\t 14.00\t  %.2f\n",countHot,totalHot);
	    System.out.printf("Economy\t\t    %d\t  7.00\t  %.2f\n",countEcon,totalEcon);
	    System.out.printf("Total\t\t \t  \t  %.2f\n",total);
	    
	    System.out.println("\n––––––––––––––––––––––––––––// //––––––––––––––––––––––––––––––");
	    
	  }
	  
	}
