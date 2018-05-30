import java.util.Iterator;

public class TreeDemo {
	public static void main(String[] args) {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>(1,
			new LinkedBinaryTree<Integer>(2, 
				new LinkedBinaryTree<Integer>(4),
				new LinkedBinaryTree<Integer>(5)
			),
			new LinkedBinaryTree<Integer>(
				3,
				new LinkedBinaryTree<Integer>(null),
				new LinkedBinaryTree<Integer>(6,
					new LinkedBinaryTree<Integer>(7),
					new LinkedBinaryTree<Integer>(8)
				)
			)
		);


		Iterator<Integer> ti = lbt.inorder();
		while(ti.hasNext()){
			System.out.println(ti.next());
		}
	}
}