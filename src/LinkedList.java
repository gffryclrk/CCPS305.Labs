 /* I really didn't like the way the code I submitted for the previous lab
 * was structured (LinkedListStudent.java). I honestly thought that
 * it would be more difficult and time consuming to convert it to 
 * a doubly linked list than start this new project from scratch. As
 * you can see, I decided to scrap the whole thing and reformat the code.
 * 
 * This personal implementation is my version of a Doubly Linked List which
 * uses Java Generics to store any data type the user wants (similar to the 
 * standard library). I have decided to encapsulate the nodes within the Linked
 * List class which is the only thing that makes sense to me at this time.
 * 
 * For this implementation I have used two dummy nodes at the start and 
 * end of the list to simplify adding & removing. 
 * 
 * Further, to comply with the lab instructions, I have written a simple
 * Student class (Student.java) that can be used as the data type for this
 * implementation. 
 * 
*/
public class LinkedList<T>{

	private ListNode first;
	private ListNode last;
	private int size;

	private class ListNode{
		T data;
		ListNode next;
		ListNode prev;

		private ListNode(){

		}
		
		private ListNode(T data){
			this.data = data;
		}

		private ListNode(T data, ListNode next){
			this.data = data;
			this.next = next;
		}

		private ListNode(T data, ListNode next, ListNode prev){
			this.data = data;
			this.next = next;
			this.prev = prev;	
		}

		private ListNode(ListNode next){
			this.next = next;
		}

		@Override
		public String toString(){
			return data.toString();
		}

	}
	public LinkedList(){
		first = new ListNode(); //dummy node
		first.next = new ListNode(null, null, first); // last 
		last = first.next;
		this.size = 0;
	}
	
	// My default add method adds nodes to the end of the list
	public void add(T data){
		add(data, size);
	}
	
	public void add(T data, int index){
		ListNode current = nodeAt(index);
		// current.data = T;
		ListNode insert = new ListNode(data, current, current.prev);
		current.prev.next = insert;
		current.prev = insert;
		size += 1;

	}

	// Default remove removes students from end of list
	public void remove(){
		remove(size-1);
	}
	
	public void remove(int index){
		if(size == 0) return;
		
		ListNode current = nodeAt(index);
		current.prev.next = current.next;
		current.next.prev = current.prev;
		size -= 1;
	}

	public void clear(){
		first = null;
		last = null;
		size = 0;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	private ListNode nodeAt(int index){
		ListNode current = first.next; //skip dummy first node
		for (int i = 0; i < index; i+=1) {
			current = current.next;
		}
		return current;
	}

	@Override
	public String toString(){
		ListNode current = first.next;
		StringBuilder sb = new StringBuilder("");
		while(current != last){
			sb.append(current.toString() + "\n");
			current = current.next;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// Add Demo		
		LinkedList<Student> ll = new LinkedList<>();
		ll.add(new Student(1, "Ronny", 3.5)); // Ronny
		ll.add(new Student(2, "Jeremy", 3.33)); // Ronny Jeremy
		ll.add(new Student(3, "Susanne", 3.0), 1); // Ronny Susanne Jeremy
		ll.add(new Student(4, "Barbara", 4.0)); // Ronny Susanne Jeremy Barbara
		System.out.println(ll);
		
		// Remove Demo		
		LinkedList<Student> lls = new LinkedList<>();
		lls.remove(); // Remove from empty list
		lls.add(new Student(1, "Henry", 3.5));
		lls.remove(); // Remove with one Element
		lls.add(new Student(2, "Joanne", 2.33)); // Joanne
		lls.add(new Student(3, "Lisa", 4.33)); // Joanne Lisa
		lls.remove(0); // Lisa
		System.out.println(lls);
		System.out.println(lls.size()); // 1
		lls.clear();
		System.out.println("Size: " + lls.size() + " isEmpty: " + lls.isEmpty());

	}
}