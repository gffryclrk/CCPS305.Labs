import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class LabThree{
	
	public static class Exam{
		private int grade;
		private String name;

		public Exam(String name, int grade){
			this.grade = grade;
			this.name = name;
		}

		@Override
		public String toString(){
			return name + " " + grade;
		}
	}
	public static void main(String[] args) {
		
		System.out.println("Stack example");

		Stack<Exam> se = new Stack<>();
		Stack<Exam> sb = new Stack<>(); // For backing up our stack
		
		se.add(new Exam("Yeilding", 87));
		se.add(new Exam("White", 84));
		se.add(new Exam("Todd", 52));
		se.add(new Exam("Tashev", 95));

		System.out.println(se); // In order that they were added

		while(!se.isEmpty()){
			Exam next = se.pop();
			System.out.println(next);
			sb.add(next);
		}

		System.out.println(se); // Original Stack is now empty
		System.out.println(sb); // Backup exists in reverse order

		System.out.println("\nQueue example");
		Queue<Exam> qe = new LinkedList<Exam>();

		qe.add(new Exam("Yeilding", 87));
		qe.add(new Exam("White", 84));
		qe.add(new Exam("Todd", 52));
		qe.add(new Exam("Tashev", 95));
		qe.add(new Exam("Vargas", 100));

		System.out.println(qe); // Queue's built in toString() prints in same order as added

		while(!qe.isEmpty()){
			Exam next = qe.remove();
			if(next.grade != 100) System.out.println(next);
			// I chose not to back up the Queue but to do so would be analagous to how it was done for 
			// the stack. 
		}
		
		System.out.println(qe); // Empty queue
	}
}