package entities;

public class Account {
	
	private int number;
	public String holderName;
	public double balance;
	
	public Account(int number, String holderName, double balance) { // Construtor
		super();
		this.number = number;
		this.holderName = holderName;
		this.balance = balance;
	}
	
	public String toString() {
		return "Account " + number + ", Holder: " + holderName +
				", Balance: " + String.format("%.2f", balance);
	}
	
	

}
