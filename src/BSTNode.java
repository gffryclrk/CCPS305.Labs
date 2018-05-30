// From Java Foundations 2nd Ed.

public class BSTNode<T extends Comparable<T>> extends BTNode<T>{
	
	public BSTNode(T element){
		super(element);
	}

	public void add (T item){
		if (item.compareTo(element) < 0){
			if(left == null){
				left = new BSTNode(item);
			}else{
				((BSTNode)left).add(item);
			}
		}else{
			if(right==null){
				right = new BSTNode(item);
			}else{
				((BSTNode)right).add(item);
			}
		}
	}

	public BSTNode<T> find(T target){
		BSTNode<T> result = null;

		if(target.compareTo(element) == 0) result = this;
		else{
			if(target.compareTo(element) < 0){
				if(left != null) result = ((BSTNode)left).find(target);
			}else{
				if(right != null) result = ((BSTNode)right).find(target);
			}
		}

		return result;
	}

	public BSTNode<T> remove(T target){
		BSTNode<T> result = this;

		if(target.compareTo(element) == 0){
			if(left == null && right == null)
				result = null;
			else if(left != null && right == null)
				result = (BSTNode)left;
			else if(left == null && right != null)
				result = (BSTNode)right;
			else{
				result = getSuccessor();
				result.left = left;
				result.right = right;
			}
		}else{
			if(target.compareTo(element) < 0){
				if(left != null)
					left = ((BSTNode)left).remove(target);
			}else{
				if (right != null){
					right = ((BSTNode)right).remove(target);
				}
			}
		}

		return result;
	}

	protected BSTNode<T> getSuccessor(){

		BSTNode<T> successor = (BSTNode)right;

		while (successor.getLeft() != null){
			successor = (BSTNode) successor.getLeft();
		}

		((BSTNode)right).remove(successor.getElement());

		return successor;
	}

}