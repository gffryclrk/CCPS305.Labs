/*
* AVL Tree implementation by Geoffrey Clark
* Code heavily based on available Java implementation from Rosetta Code:
* https://rosettacode.org/wiki/AVL_tree#More_elaborate_version_2
* As well as implementation from Java Foundations, 2nd Edition, 
* chapters 16 & 17: Trees & Binary Search Trees.
* I also found the method for the sideways tree printout
* in Building Java Programs: A back to Basics Approach (3rd Ed.)
*/
public class AVLTree<T extends Comparable<T>>{
	
	private AVLNode<T> root;

	private class AVLNode<T extends Comparable<T>> {
		T data;
		int height;
		AVLNode<T> left, right, parent;

		private AVLNode(T data){
			this.data = data;
			this.height = 0;
		}

		private AVLNode(T data, AVLNode<T> parent){
			this.data = data;
			this.parent = parent;
			if(parent != null) this.height = parent.height - 1;
			else this.height = 0;
		}

		private T getData(){
			return this.data;
		}

		private AVLNode<T> add(T item){
			AVLNode<T> n = null;

			if(item.compareTo(data) < 0){
				if(left == null){
					n = new AVLNode<T>(item, this);
					left = n;
					return n;
				} 
				else n = left.add(item);
			}else{
				if(right == null){
					n = new AVLNode<T>(item, this);
					right = n;
					return n;
				}
				else n = right.add(item);
			}
			return n;
		}

		private AVLNode<T> find(T target){
			AVLNode<T> result = null;

			if(target.compareTo(data) == 0) result = this;
			else{
				if(target.compareTo(data) < 0){
					if(left != null) result = left.find(target);
				}else{
					if(right != null) result = right.find(target);
				}
			}

			return result;
		}

		@Override
		public String toString(){
			return data.toString();
		}

	}

	public AVLTree(){

	}

	public AVLTree(T data){
		this.root = new AVLNode<T>(data);
	}

	public void add(T data){
		if(root == null) root = new AVLNode<T>(data);
		else rebalance(root.add(data));


	}
	
	private void delete(T data){
		delete(root.find(data));
	}

	private void delete(AVLNode<T> n){
		if(n.left == null && n.right == null){
			if(n.parent == null) root = null;
			else{
				AVLNode<T> parent = n.parent;
				if(parent.left == n) parent.left = null;
				else parent.right = null;
				rebalance(parent);
			}
			return;
		}

		if(n.left != null){
			AVLNode<T> child = n.left;
			while (child.right != null) child = child.right;
			n.data = child.data;
			delete(child);
		}else{
			AVLNode<T> child = n.right;
			while(child.left != null) child = child.left;
			n.data = child.data;
			delete(child);
		}
	}

	private int height(AVLNode<T> n){
		if(n == null) return -1;
		return n.height;
	}

	private void reheight(AVLNode<T> n){
		if(n != null){
			n.height = 1 + Math.max(height(n.left), height(n.right));
		}
	}

	private void rebalance(AVLNode<T> n){
		reheight(n);

		if((height(n.left) - height(n.right)) == -2){
			if(height(n.right.right) < height(n.right.left)){
				n.right = rightRotation(n.right);
				n = leftRotation(n);
			}else{
				n = leftRotation(n);
			}
		}else if((height(n.left) - height(n.right)) == 2){
			if(height(n.left.left) < height(n.left.right)){
				n.left = leftRotation(n.left);
				n = rightRotation(n);							
			}else{				
				n = rightRotation(n);
			}
		}
		if(n.parent != null){
			rebalance(n.parent);
		}else{
			root = n;
		}
		
	}

	private AVLNode<T> leftRotation(AVLNode<T> n){
		AVLNode<T> o = n.right;
		o.parent = n.parent;
		n.right = o.left;

		if(n.right != null) n.right.parent = n;

		o.left = n;
		n.parent = o;

		if(o.parent != null){
			if(o.parent.right == n){
				o.parent.right = o;
			}else{
				o.parent.left = o;
			}
		}

		reheight(n);
		reheight(o);

		return o;

	}

	private AVLNode<T> rightRotation(AVLNode<T> n){
		AVLNode<T> o = n.left;
		o.parent = n.parent;
		n.left = o.right;

		if(n.left != null) n.left.parent = n;

		o.right = n;
		n.parent = o;

		if(o.parent != null){
			if(o.parent.right == n){
				o.parent.right = o;
			}else{
				o.parent.left = o;
			}
		}

		reheight(n);
		reheight(o);
		
		return o;
	}


	public void printInorder() {
		System.out.print("Inorder traversal: ");
		printInorder(root);
		System.out.println();
	}

	private void printInorder(AVLNode<T> n) {
        if (n != null) {
            printInorder(n.left);
            System.out.printf("%s ", n.data);
            printInorder(n.right);
        }
    }

	public void printSideways(){
		System.out.println("Sideways tree representation: \n");
		printSideways(root, 0);
	}

	public void printSideways(AVLNode<T> root, int level){
		if (root != null){
			printSideways(root.right, level +1);
			for (int i = 0; i < level; i+=1) {
				System.out.print("     ");
			}
			System.out.println(root.getData());
			printSideways(root.left, level +1);
		}
	}

	public int size(){
		return size(root);
	}
	private int size(AVLNode<T> n){
		if(n == null) return 0;
		return size(n.left) + size(n.right) + 1;
	}

	public void print(){
		/*
		* This function should begin by printing a small header containing the size of the tree. 
		* Then it should perform an InOrder  traversal of the tree, printing out each element as it traverses 
		* the tree.
		*/

		System.out.println("Tree size: " + size());
		printInorder();
	}


	public static void main(String[] args) {
		// int[] numbers = {20, 45, 90, 70, 10, 40, 35, 30, 99, 60, 50, 80};

		AVLTree<Integer> avl = new AVLTree<Integer>();
		avl.add(20);
		avl.add(45);
		avl.add(90);
		
		avl.printInorder();
		avl.printSideways();

		avl.add(70);
		avl.add(10);
		avl.add(40);
		avl.add(35);
		avl.add(30);

		avl.printInorder();
		avl.printSideways();

		avl.add(99);
		avl.printSideways();

		avl.add(60);
		avl.printSideways();
		
		avl.add(50);
		avl.printSideways();

		avl.add(80);

		avl.printInorder();
		avl.printSideways();
		
		avl.delete(10);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(30);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(40);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(35);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(70);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(90);
		avl.printInorder();
		avl.printSideways();
		
		avl.delete(99);
		avl.printInorder();
		avl.printSideways();

		avl.print();
	}

}