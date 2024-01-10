package entities;

public class Student {
	
	public String name;
	public double grade1;
	public double grade2;
	public double grade3;
	
	public double getFinalGrade() {
		return grade1 + grade2 + grade3;
	}
	
	public boolean result() {
		if (getFinalGrade() < 60.0) {
			return false; // REPROVOU
		} else {
			return true; // PASSOU
		} 
	}
	
	public double missingPoints() {
		return 60 - getFinalGrade();
	}
	
	public String toString() {
		return "NOTA FINAL = " + String.format("%.2f", getFinalGrade());
	}
	
}
