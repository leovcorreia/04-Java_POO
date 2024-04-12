package entities;

public class Product {
	
	private String name;
	private double purchase;
	private double sale;
	private double profit;
	private double profitPercentage;
	
	public Product(String name, double purchase, double sale, double profit, double profitPercentage) {
		this.name = name;
		this.purchase = purchase;
		this.sale = sale;
		this.profit = profit;
		this.profitPercentage = profitPercentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPurchase() {
		return purchase;
	}

	public void setPurchase(double purchase) {
		this.purchase = purchase;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public double getProfit() {
		return profit;
	}
	
	public double getProfitPercentage() {
		return profitPercentage;
	}
	
}
