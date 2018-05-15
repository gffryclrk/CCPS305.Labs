public class Student{
	private int stdnum;
	private String name;
	private double sgpa;

	public Student(int stdnum, String name, double sgpa){
		this.stdnum = stdnum;
		this.name = name;
		this.sgpa = sgpa;
	}

	@Override
	public String toString(){
		return stdnum + " " + name + " " + sgpa;
	}
}