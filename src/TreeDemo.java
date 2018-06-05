import java.util.Iterator;
import java.util.Random;

public class TreeDemo {
	public static void main(String[] args) {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>(1,
			new LinkedBinaryTree<Integer>(2, 
				new LinkedBinaryTree<Integer>(4),
				new LinkedBinaryTree<Integer>(5)
			),
			new LinkedBinaryTree<Integer>(
				3,
//				new LinkedBinaryTree<Integer>(null),
				null, // Null pointer exception if you try to add null.
				// Instead you must add a tree whose root is null. 
				new LinkedBinaryTree<Integer>(6,
					new LinkedBinaryTree<Integer>(7),
					new LinkedBinaryTree<Integer>(8)
				)
			)
		);


		System.out.println("Binary Tree size: " + lbt.size());
		Iterator<Integer> ti = lbt.inorder();
		System.out.print("\ninorder: ");
		while(ti.hasNext()){
			System.out.print(" " + ti.next());
		}
		System.out.println();
		lbt.printSideways();
		
		LinkedBinarySearchTree<Integer> bst = new LinkedBinarySearchTree<>();
		bst.add(35);
		bst.add(36);
		bst.add(37);
		bst.add(1);
		bst.add(42);
		bst.add(99);
		bst.add(2);
		bst.add(34);
		bst.add(17);
		bst.add(17);

		/*
		* Below is handy for populating the tree with random integers.
		* Not ideal for testing removal of specific values.
		*

		Random rn = new Random();
		int MAX_NODES = 15;
		for (int i=0; i<MAX_NODES; i+=1) {
			bst.add(rn.nextInt(100)+1);
		}
		
		*/


		System.out.println("\n\n\nBinary Search Tree size: " + bst.size());
		Iterator<Integer> sti = bst.inorder();
		System.out.print("\ninorder: ");
		while(sti.hasNext()){
			System.out.print(" " + sti.next());
		}
		System.out.println();
		bst.printSideways();

		// Removals
		bst.remove(99); // leaf
		bst.remove(1);  // one child
		bst.remove(37); // two children

		System.out.println("\n\n\nBinary Search Tree size: " + bst.size());
		sti = bst.inorder();
		System.out.print("\ninorder: ");
		while(sti.hasNext()){
			System.out.print(" " + sti.next());
		}
		System.out.println();
		bst.printSideways();

		System.out.print("\n\n\n\n");
		System.out.println("AVL TREES!!!");

		LinkedAVLTree<Integer> avl = new LinkedAVLTree<>();
		int[] numbers = {20, 45, 90, 70, 10, 40, 35, 30, 99, 60, 50, 80};
//		int[] numbers = {20, 45, 90}; 
		for(int i = 0; i<3; i+=1){
			avl.add(numbers[i]);
			System.out.println(avl.balanceFactor());
		}
//		Integer i = 3;
//		avl.add(i);
		System.out.println("AVL Tree size: " + avl.size());
		System.out.println(avl);
		
		avl.printSideways();
		System.out.println("Balanace factor:" + avl.balanceFactor());
		System.out.println("Height:" + avl.height());
		
		System.out.print("\n\nLeft Rotation");
//		avl.leftRotation();
		
		avl.printSideways();
		System.out.println("Balanace factor:" + avl.balanceFactor());
		System.out.println("Height:" + avl.height());
		
		// The tree will remain balanced until 30 is added
		// Add 5 more numbers
		
		for(int i = 3; i < 8; i+=1) {
			avl.add(numbers[i]);
		}
		
//		avl.add(numbers[3]);
		
		avl.printSideways();
		System.out.println("Balanace factor:" + avl.balanceFactor());
		System.out.println("Height:" + avl.height());
		
		// add the rest
		
		for(int i = 8; i < numbers.length; i+=1) {
			avl.add(numbers[i]);
		}
		
		avl.printSideways();
		System.out.println("Balanace factor:" + avl.balanceFactor());
		System.out.println("Height:" + avl.height());
	}



}