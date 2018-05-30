// LinkedBinaryTree from Java Foundations 2nd Ed, Chapter 16

import java.util.Iterator;
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
		root.setLeft(left.root);
		root.setRight(right.root);
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
}