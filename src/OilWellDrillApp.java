import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class OilWellDrillApp {
	
	static int drillingAttempt = 0;
	static int barrelsOfOilCollected = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to the oil well drilling game!");
		System.out.println("Game Rules: ");
		System.out.println("You have 10 chances of guess. You have $100, each time of drilling costs you $10.");
		System.out.println("Board X coordinate: 0 - 4,  Board Y coordinate: 0 - 4, Depth: 0 - 9");
		System.out.println("Game ends when you have collected all oils or you have used all the funds.");
		
		String[][] map = new String[5][5];
		
		setMap(map);
		
		displayMap(map);
		
		//randomly generating the coordinates and depth of oil wells
		OilWell[] oilWellArray = new OilWell[2];
		
		for (int i = 0; i < 2; i++) {
			int xCor = (int) Math.floor(Math.random() * 5);
			int yCor = (int) Math.floor(Math.random() * 5);
			int depth = (int) Math.floor(Math.random() * 10);
			int oilAmount = (int) Math.floor(Math.random() * 10000);
			oilWellArray[i] = new OilWell(xCor, yCor, depth, oilAmount);
			//System.out.println(oilWellArray[i].x + " " + oilWellArray[i].y + " " + oilWellArray[i].depth + " " + oilWellArray[i].reserves);
			
		}
		
		
		int totalOilReserves = oilWellArray[0].reserves + oilWellArray[1].reserves;
		
		Investor richman = new Investor("Richman", 100, 2, 0, 0, 0);
		
		while (!(richman.getFund() <= 0 || barrelsOfOilCollected == totalOilReserves)) {
			drillingAttempt += 1;
			richman.getDrillMethod();
			evaluateResult(richman.xd, richman.yd, richman.dd, oilWellArray, map);
			Scanner inputDevice = new Scanner(System.in);
			System.out.print("Input any keys to continue, Q to quit>>");
			String input = inputDevice.nextLine();
			if (!input.equals("Q")) {
				map[richman.yd][richman.xd] = (String.valueOf(richman.dd));
				displayMap(map);
				continue;
			} else {
				break;
			}		
			
		}
		
		if (richman.getFund() <= 0) {
			System.out.println("Sorry you don't have enough fund.");
		}
		
		if (barrelsOfOilCollected == totalOilReserves) {
			System.out.println("You have collected all oil!");
		}
		
		displaySummary(richman);

	}
	
	private static void displaySummary(Investor richman) {
		// TODO Auto-generated method stub
		System.out.printf("Hi %s, here is the summary of your drilling: \n", richman.name);
		System.out.printf("Attempted: %d times \n", drillingAttempt);
		System.out.println("Fund spent: " + richman.fundSpent);
		System.out.println("Oil collected: " + barrelsOfOilCollected + " barrels");
		
	}

	private static void setMap(String[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map.length; x++) {
				map[y][x] = "-";
			}
		}
		
	}

	public static void displayMap(String[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map.length; x++) {
				System.out.print(map[y][x] + " ");
			} System.out.println();
		}
	}
	
	public static void evaluateResult(int xd, int yd, int dd, OilWell[] oilWellArray, String[][] map) {
		
		int closestDistance = oilWellArray[0].drillDistance(xd, yd, dd);
		boolean isFound = false;
		
		for(int i = 0; i < oilWellArray.length; i++) {
			if(xd == oilWellArray[i].x && yd == oilWellArray[i].y && oilWellArray[i].drillDistance(xd, yd, dd) == 0) {
				
				if (map[yd][xd].equals(Integer.toString(dd))) {
					System.out.println("All reserves in this spot are extracted!");
					isFound = true;
					break;
				}
				
					System.out.println("***************");
					System.out.printf("Big congrats!! You just got %d barrels of oil! Do you want to try again?\n", oilWellArray[i].reserves);
					System.out.println("***************");
					
					barrelsOfOilCollected += oilWellArray[i].reserves;
					isFound = true;
					break;
			} 	else {
	
				closestDistance = Math.min(closestDistance, oilWellArray[i].drillDistance(xd, yd, dd));
			}
		}
		
		
		if (isFound == false) {
			if (map[yd][xd].equals(Integer.toString(dd))) {
				System.out.println("You have tried this spot at this depth already!");
			}
			System.out.printf("You missed the well, the closest distance is %d, do you want to try again?\n", closestDistance);
		}
	 
		
		
		
	}
	
	
}
	