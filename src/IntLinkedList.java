/* This class is based on the example LinkedIntList implementation
 * from chapter 16 of 'Building Java Programs' by Stuart Reges & Marty Stepp (3rd ed.)
 * However, I have made some changes, including:
 * 1) Count is stored as a field (as opposed to counting on demand with a loop in the size method)
 * 2) remove() uses the nodeAt() method
 * 3) I nested the ListNode class
 */

public class IntLinkedList{
	private ListNode front; 
	private int count = 0;
	
	private class ListNode{
		public int data;
		public ListNode next;
		
		public ListNode(){
			this(0, null);
		}
		
		public ListNode(int data){
			this(data, null);
		}
		
		public ListNode(int data, ListNode next){
			this.data = data;
			this.next = next;
		}
	}
	
	public IntLinkedList(){
		front = null;
		// count = 0;
	}
	
	public void add(int value){
		if(front == null){
			front = new ListNode(value);
		}else{
			ListNode current = nodeAt(count-1);
			current.next = new ListNode(value);
		}
		count+=1;
	}
	
	public void add(int index, int value){
		if(index == 0){
			front = new ListNode(value, front);
		}else{
			if(index > count) index = count; 
			ListNode current = nodeAt(index - 1);
			current.next = new ListNode(value, current.next);
		}
		count+=1;
	}
	
	public void addSorted(int value){
		if(front == null || front.data >= value){
			front = new ListNode(value, front);
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data < value){
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
		}
	}
	
	public void remove(int index) {
		if(index == 0) front = front.next;
		else {
			ListNode current = nodeAt(index - 1);
			current.next = current.next.next;
		}
		count-=1;
	}
	
	private ListNode nodeAt(int index){
		ListNode current = front;
		for(int i = 0; i < index; i++){
			current = current.next;
		}
		return current;
	}
	
	public int indexOf(int value){
		int index = 0;
		ListNode current = front;
		while(current != null){
			if(current.data == value){
				return index;
			}
			index+=1;
			current = current.next;
		}
		return -1;
	}
	
	public boolean contains(int value) {
		ListNode current = front;
		while(current.next != null) {
			if(value == current.data) return true;
			current = current.next;
		}
		return false;
	}
	
	public int get(int index){
		return nodeAt(index).data;
	}
	
	public void clear() {
		front = null;
		count = 0;
	}
	
	public boolean isEmpty() {
		if(count > 0) return false;
		else return true;
	}
	
	public int size(){
		return count;
	}
	
	public String toString(){
		if(front == null){
			return "[]";
		}else{
			String result = "[" + front.data;
			ListNode current = front.next;
			while (current != null){
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}
	
	public static void main(String[] args) {
		IntLinkedList ill = new IntLinkedList();
		System.out.println("Is empty?: " + ill.isEmpty());
		System.out.println("Size: " + ill.size());
		ill.add(17);
		ill.add(42);
		ill.add(2, 88);
		System.out.println(ill.toString());
		ill.addSorted(6);
		System.out.println(ill.toString());
		ill.addSorted(0);
		System.out.println(ill.toString());
		System.out.println(ill.get(2));
		ill.remove(2);
		System.out.println(ill.toString());
		System.out.println("Is empty?: " + ill.isEmpty());
		System.out.println("Contains 42?: " + ill.contains(42));
		System.out.println("Contains 99?: " + ill.contains(99));
		System.out.println("Index of 42: " + ill.indexOf(42));
		System.out.println("Contents before clear: " + ill.toString());
		ill.clear();
		System.out.println("Contents after clear: " + ill.toString());
		System.out.println("Size: " + ill.size());
		
	}
}