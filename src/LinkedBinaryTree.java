/*
* This implimentation is based heavily on the LinkedBinaryTree Class
* found in Java Foundations 2nd Ed, Chapter 16 by Lewis, DePasquale & Chase
* However, I have made some changes. Notably I removed anything that
* was unique to the book's package: they tend to implement their own
* versions of things such as exceptions, data structures and iterators
* and then use them in later chapters. I made some changes to make
* the code work with standard library classes. I also made a change
* to one of the LinkedBinaryTree() constructors which I noted in place.
* 
*/
import java.util.*;

public class LinkedBinaryTree<T> {
	protected BTNode<T> root;

	public LinkedBinaryTree(){
		root = null;
	}
	public LinkedBinaryTree(T element){
		root = new BTNode<T>(element);
	}
	public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
		root = new BTNode<T>(element);
		/*
		* Trying to add null leaves throws a NullPointerException.
		* 
		* This is contrary to my intended behaviour to have
		* nodes with less than two leaves. 
		* root.setLeft(left.root); 
		* root.setRight(right.root);
		*/

		if(left != null) root.setLeft(left.root); 
		if(right != null) root.setRight(right.root);
	}
	public T getRootElement(){
		if(root == null){
			// throw exception? 
		}
		
		return root.getElement();
	}

	public LinkedBinaryTree<T> getLeft(){
		if(root == null){
			// throw exception?
		}

		LinkedBinaryTree<T> result = new LinkedBinaryTree<T>();
		result.root = root.getLeft();

		return result;
	}
	public LinkedBinaryTree<T> getRight(){
		if(root == null){
			// throw exception?
		}
		LinkedBinaryTree<T> result = new LinkedBinaryTree<T>();
		result.root = root.getRight();
		return result;
	}
	public Iterator<T> inorder(){
		Queue<T> q = new java.util.LinkedList<T>();

		if (root != null) root.inorder(q);

		return q.iterator();
	}

	public int size(){
		int result = 0;
		if (root != null){
			result = root.count();
		}
		return result;
	}

	
	/*
	* printSideways is from Reges & Stepp: Building Java Programs: A Back to Basics Approach
	* I thought it would be a useful visualization.
	*/

	public void printSideways(){
		System.out.println("Sideways tree representation: \n");
		printSideways(root, 0);
	}

	public void printSideways(BTNode<T> root, int level){
		if (root != null){
			printSideways(root.getRight(), level +1);
			for (int i = 0; i < level; i+=1) {
				System.out.print("     ");
			}
			System.out.println(root.getElement());
			printSideways(root.getLeft(), level +1);
		}
	}


}