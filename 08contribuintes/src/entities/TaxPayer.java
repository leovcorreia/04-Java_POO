package entities;

public class TaxPayer {
	
	private Double salaryIncome;
	private Double servicesIncome;
	private Double capitalIncome;
	private Double healthSpending;
	private Double educationSpending;
	
	public TaxPayer() {
	}

	public TaxPayer(Double salaryIncome, Double servicesIncome, Double capitalIncome, Double healthSpending,
			Double educationSpending) {
		this.salaryIncome = salaryIncome;
		this.servicesIncome = servicesIncome;
		this.capitalIncome = capitalIncome;
		this.healthSpending = healthSpending;
		this.educationSpending = educationSpending;
	}

	public Double getSalaryIncome() {
		return salaryIncome;
	}

	public void setSalaryIncome(Double salaryIncome) {
		this.salaryIncome = salaryIncome;
	}

	public Double getServicesIncome() {
		return servicesIncome;
	}

	public void setServicesIncome(Double servicesIncome) {
		this.servicesIncome = servicesIncome;
	}

	public Double getCapitalIncome() {
		return capitalIncome;
	}

	public void setCapitalIncome(Double capitalIncome) {
		this.capitalIncome = capitalIncome;
	}

	public Double getHealthSpending() {
		return healthSpending;
	}

	public void setHealthSpending(Double healthSpending) {
		this.healthSpending = healthSpending;
	}

	public Double getEducationSpending() {
		return educationSpending;
	}

	public void setEducationSpending(Double educationSpending) {
		this.educationSpending = educationSpending;
	}
	
	public Double salaryTax() {
		Double monthSalary = salaryIncome / 12.0;
		if (monthSalary < 3000.0) {
			return 0.0;
		} else if (monthSalary < 5000.0) {
			return salaryIncome * 0.1;
		} else {
			return salaryIncome * 0.2;
		}
	}
	
	public Double servicesTax() {
		if (servicesIncome > 0.0) {
			return servicesIncome * 0.15;
		} else {
			return 0.0;
		}
	}
	
	public Double capitalTax() {
		if (capitalIncome > 0.0) {
			return capitalIncome * 0.2;
		} else {
			return 0.0;
		}
	}
	
	public Double grossTax() {
		return salaryTax() + servicesTax() + capitalTax();
	}
	
	public Double taxRebate() {
		Double custos = healthSpending + educationSpending;
		if (custos < grossTax() * 0.3) {
			return custos;
		} else {
			return grossTax() * 0.3;
		}
	}
	
	public Double netTax() {
		return grossTax() - taxRebate();
	}

}
