package entities;

public class Student {
	
	public String name;
	public double grade1;
	public double grade2;
	public double grade3;
	public double finalGrade;
	public String status;
	
	public double getFinalGrade() {
		return grade1 + grade2 + grade3;
	}
	
	public boolean result(double finalGrade) {
		if (finalGrade < 60.0) {
			return false; // REPROVOU
		} else {
			return true; // PASSOU
		} 
	}
	
	public double toPass(double finalGrade) {
		return 60 - finalGrade;
	}
	
	public String toString() {
		return "NOTA FINAL = " + String.format("%.2f", getFinalGrade()) + "\n";
	}
	

}
