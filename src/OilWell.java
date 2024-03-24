
public class OilWell {
	
	int x;
	int y;
	int depth;
	int reserves;
	
	OilWell(int _x, int _y, int _depth, int _reserves) {
		
		x = _x;
		y = _y;
		depth = _depth;
		reserves = _reserves;
		
	}
	
	public int drillDistance(int xd, int yd, int dd) {
		return Math.abs(x - xd) + Math.abs(y - yd) + Math.abs(depth - dd);
	}

}
