package entities;

public class Student implements Comparable<Student> {
	
	private Integer code;
	
	public Student( ){
	}

	public Student(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public int compareTo(Student other) {
		return code.compareTo(other.getCode());
	}
	
	

}
