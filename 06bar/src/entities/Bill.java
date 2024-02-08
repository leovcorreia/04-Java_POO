package entities;

public class Bill {
	
	public char gender;
	public int beer, barbecue, softDrink;

	public double feeding() {
		return (beer * 5) + (barbecue * 7) + (softDrink * 3);
	}
	
	public double cover() {
		if (feeding() <= 30.0) {
			return 4.0;
		} else {
			return 0.0;
		}
	}
	
	public double ticket() {
		if ((gender == 'F') || (gender == 'f')) {
			return 8.0;
		} else {
			return 10.0;
		} 
	}
	
	public double total() {
		return feeding() + cover() + ticket();
	}

}
