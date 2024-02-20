package entities;

public class Account {
	
	private int number;
	public String holderName;
	public double balance;
	
	public Account(int number, String holderName) {
		this.number = number;
		this.holderName = holderName;
	}

	public Account(int number, String holderName, double initialDeposit) {
		this.number = number;
		this.holderName = holderName;
		deposit(initialDeposit);
	}
	
	public int getNumber() {
		return number;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		this.balance -= amount + 5.00;
	}
	
	public String toString() {
		return "Account " + number + ", Holder: " + holderName +
				", Balance: " + String.format("%.2f", balance);
	}
	
}
