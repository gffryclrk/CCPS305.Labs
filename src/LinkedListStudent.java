/* I made some changes to the provided code (LNode class):
 * Firstly, I nested it within the LinkedListStudent class
 * I changed the fields to begin with lowercase characters: stdname, stdnum
 * (as not to be confused with classes, as per Java convention)
 * I also changed Public ListNode(int stdnum..) to public LNode
 * as I was under the impression that this was designed to be a constructor
 * for the LNode class.
 * Speaking of which, in the constructor I also added the 'this' keyword
 * so that the constructor parameters assigned the corresponding object
 * instance fields.
 * 
 */
import java.util.Scanner;

public class LinkedListStudent{
	public LNode first;
	private int size = 0;

	public class LNode{
		int stdnum;
		String stdname;
		double gpa;
		LNode next;

		// LNode constructor
		public LNode(int stdnum, String sname, double sgpa, LNode snext){
			this.stdnum = stdnum;
			this.stdname = sname;
			this.gpa = sgpa; 
			this.next = snext;

		}

		public String toString(){
			return "{" + stdnum + "," + stdname + "," + gpa + "}";
		}

	}

	// Add student to end of list
	public void add(int stdnum, String sname, double sgpa){
		if(first == null) first = new LNode(stdnum, sname, sgpa, null); //end of list has null as next?
		else{
			LNode current = first;
			while(current.next != null) {
				current = current.next;
			}
			current.next = new LNode(stdnum, sname, sgpa, null); 
		}
		size+=1;

	}
	// remove from end of list
	public void remove(){
		if(first == null || first.next == null) {
			first = null;
			return;
		}; //null & 1 node lists

		LNode current = first;
		while(current.next.next != null){
			current = current.next;
		}
		current.next = null;
		size-=1;

	}

	public String toString(){
		if(size == 0) return "[]";
		else{
			StringBuilder sb = new StringBuilder("[" + first);
			LNode current = first.next;
			while(current != null){
				sb.append("," + current);
				current = current.next;
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public int size(){
		return size;
	}

	public static void main(String[] args){
		/* Demo: */
		
		LinkedListStudent lss = new LinkedListStudent();
		lss.remove(); //remove from empty list
		lss.add(1, "Harry", 3.5);
		lss.remove(); //remove from list with one
		lss.add(2, "George", 4.0);
		lss.add(3, "Tommy", 2.33);
		System.out.println(lss.toString());
		lss.remove(); //remove from list with two
		System.out.println(lss);
		
		
		// Keyboard input example
		LinkedListStudent lss_kbin = new LinkedListStudent();
		int NUM_OF_STUDENTS = 3;
		Scanner keyboard = new Scanner(System.in);
		
		// Q1: User inputs three students
		System.out.println("Please add 3 students to the linked list: ");
		for (int i = 0; i<NUM_OF_STUDENTS; i+=1) {
				System.out.println("Name of Student " + (i+1) + ":");
				String s = keyboard.next();
				
				System.out.println("GPA of Student " + (i+1) + ":");
				Double d = keyboard.nextDouble();
				
				lss_kbin.add(i, s, d);
				
		}
		keyboard.close();
		// Q2: Print all students in list
		System.out.println("Thank you. Current students in list:\n");
		System.out.println(lss_kbin);
		// Q3: Removing last student
		System.out.println("Removing last student");
		lss_kbin.remove();
		// Q4: Print Size of List
		System.out.println("Size of list: " + lss_kbin.size());
		System.out.println("Current list:\n" + lss_kbin);
	}

}