// From Java Foundations 2nd Ed
import java.util.Queue;

public class BTNode<T> {
	protected T element;
	protected BTNode<T> left, right;

	public BTNode(T element){
		this.element = element;
		left = right = null;
	}

	public T getElement(){
		return element;
	}

	public void setElement(T element){
		this.element = element;
	}

	public BTNode<T> getLeft(){
		return left;
	}

	public void setLeft(BTNode<T> left){
		this.left = left;
	}

	public BTNode<T> getRight(){
		return right;
	}
	public void setRight(BTNode<T> right){
		this.right = right;
	}
	public BTNode<T> find(T target){
		BTNode<T> result = null;

		if(element.equals(target)){
			result = this;
		}else{
			if(left != null) result = left.find(target);
			if(result == null && right != null) result = right.find(target);
		}
		return result;

	}
	public int count(){
		int result = 1;

		if(left != null){
			result += left.count();
		}
		if(right != null){
			result += right.count();
		}

		return result;
	}

	public void inorder(Queue<T> iter){
		if (left != null) left.inorder(iter);

		iter.add(element);

		if (right != null) right.inorder(iter);
	}
}