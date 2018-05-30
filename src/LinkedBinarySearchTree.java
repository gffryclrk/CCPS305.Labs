// From Java Foundations 2nd Ed

public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> {
	
	public LinkedBinarySearchTree(){
		super();
	}

	public LinkedBinarySearchTree(T element){
		root = new BSTNode<T>(element);
	}

	public void add (T item){
		if(root == null){
			root = new BSTNode<T>(item);
		}else{
			((BSTNode)root).add(item);
		}
	} 

	public T remove (T target){
		BSTNode<T> node = null;

		if(root != null){
			node = ((BSTNode)root).find(target);
		}

		if(node == null){
			// throw exception?
			// The textbook uses their own "ElementNotFoundException" but
			// I don't believe this is part of the standard library.
		}

		root = ((BSTNode)root).remove(target);

		return node.getElement();

	}
}