package model.entities;

import model.exception.DomainException;

public class Account {
	
	private Integer number;
	private String holder;
	private Double balance;
	private Double withdrawLimit;
	
	public Account () {
	}

	public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
		if (number < 0) {
			throw new DomainException("Invalid number account!");
		}
		if (balance < 0.0) {
			throw new DomainException("Invalid initial balance!");
		}
		if (withdrawLimit < 0.0) {
			throw new DomainException("Invalid withdraw limit!");
		}
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	public Integer getNumber() {
		return number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}

	public Double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(Double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	public void deposit(Double amount) {
		balance += amount;
	}
	
	public void withdraw(Double amount) {
		if (amount > withdrawLimit) {
			throw new DomainException("The amount exceeds withdraw limit!");
		}
		if (balance < amount) {
			throw new DomainException("Not enough balance!");
		}
		if (amount < 0.0) {
			throw new DomainException("Invalid value!");
		}
		balance -= amount;
	}
	
	@Override
	public String toString() {
		return "New balance: " + String.format("%.2f", balance);
	}

}
