import java.util.Scanner;

public class Investor {

	String name;
	double fund;
	double drillCost;
	int xd;
	int yd;
	int dd;
	int fundSpent = 0;
	
	Investor(String _name, double _fund, double _drillCost, int _drillPositionX, int _drillPositionY, int _drillDepth) {
		
		name = _name;
		fund = _fund;
		drillCost = _drillCost;
		xd = _drillPositionX;
		yd = _drillPositionY;
		dd = _drillDepth;
		
	}
	
	public double getFund() {
		return fund;
	}
	
	
	public void getDrillMethod() {
		Scanner inputDevice = new Scanner(System.in);
		
		int drillDepth;
		int col;
		int row;
		
		
		do {
			System.out.printf("Hi %s, please let me know the x coordinate you want to drill>>", name);
			col = inputDevice.nextInt();	
		} while (col < 0 || col >= 5);

		do {
			System.out.printf("Hi %s, please let me know the y coordinate you want to drill>>", name);
			row = inputDevice.nextInt();
		} while (row < 0 || row >= 5);

		
		do {
			System.out.printf("Plese let me know the depth you want to drill, the available fund is $%.0f>>", fund);
			drillDepth = inputDevice.nextInt();	
		} while (drillDepth >= 10);

		
		xd = col;
		yd = row;
		dd = drillDepth;
		
		fund -= 10;
		fundSpent += 10;
		
		
		
	}

}
