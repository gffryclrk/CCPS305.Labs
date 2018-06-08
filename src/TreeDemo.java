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
				null,
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

		
	}


}