public class AVLTree<T extends Comparable<T>>{
	
	private AVLNode<T> root;

	private class AVLNode<T extends Comparable<T>> {
		T data;
		int height;
		// int height, balance;
		AVLNode<T> left, right, parent;

		private AVLNode(T data){
			this.data = data;
			this.height = 0;
		}
		private AVLNode(T data, AVLNode<T> parent){
			this.data = data;
			this.parent = parent;
			// this.height = parent.height + 1;
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
					// left = new AVLNode<T>(item, this);
					// rebalance(left);
					left = n;
					return n;
				} 
				else n = left.add(item);
			}else{
				if(right == null){
					// right = new AVLNode<T>(item, this);
					// rebalance(right);
					n = new AVLNode<T>(item, this);
					right = n;
					return n;
				}
				else n = right.add(item);
			}
			return n;
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
			// if(n.left != null && height(n.left.left) >= height(n.left.right)){
			// 	n = leftRotation(n);				
			// }else{
			// 	n.left = leftRotation(n.left);
			// 	n = rightRotation(n);
			// }
			// if(n.left != null && height(n.left.left) < height(n.left.right)){

			// if(n.right != null && height(n.right.right) < height(n.right.left)){
			if(height(n.right.right) < height(n.right.left)){
				n.right = rightRotation(n.right);
				n = leftRotation(n);
				// n.left = leftRotation(n.left);
				// n = rightRotation(n);

			}else{
				n = leftRotation(n);
			}
		}else if((height(n.left) - height(n.right)) == 2){
			// if(n.right != null && height(n.right.right) >= height(n.right.left)){
			// 	n = rightRotation(n);
			// }else{
			// 	n.right = rightRotation(n.right);
			// 	n = leftRotation(n);
			// }
			// if(n.right != null && height(n.right.right) < height(n.right.left)){

			// if(n.left != null && height(n.left.left) < height(n.left.right)){
			if(height(n.left.left) < height(n.left.right)){
				n.left = leftRotation(n.left);
				n = rightRotation(n);							
				// n.right = rightRotation(n.right);
				// n = leftRotation(n);
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
		printInorder(root);
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
	}

}