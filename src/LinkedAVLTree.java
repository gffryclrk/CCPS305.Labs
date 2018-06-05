public class LinkedAVLTree<T extends Comparable<T>> extends LinkedBinarySearchTree<T>{

  public class AVLTNode<T extends Comparable<T>> extends BSTNode<T>{
	
  	private int height;
    //	private AVLTNode<T> left, right;
    //	private T data;
  	
  	public AVLTNode(T element) {
  		super(element);
  	}
  	public AVLTNode() {
  		this(null);
  	}
    private int getHeight(){
      return this.height;
    }

    private void setHeight(int h){
      this.height = h;
    }




  }


  public LinkedAVLTree(){
    super();
  }
  public LinkedAVLTree(T element){
    root = new AVLTNode<T>(element);
  }
  
  
  public LinkedAVLTree(T element, LinkedAVLTree<T> left, LinkedAVLTree<T> right){
    root = new AVLTNode(element);
    // if(left != null) root.setLeft(left.root); 
    // if(right != null) root.setRight(right.root);
    root.setLeft(left.root); 
    root.setRight(right.root);

  }
  
  public LinkedAVLTree<T> getLeft(){
    LinkedAVLTree<T> result = new LinkedAVLTree<T>();
    result.root = root.getLeft();
//    if(root != null) result.root = root.getLeft();
//    else root = new AVLTNode<T>();
    return result;
  }

  public LinkedAVLTree<T> getRight(){
    LinkedAVLTree<T> result = new LinkedAVLTree<T>();
//    if(root != null) result.root = root.getRight();
    result.root = root.getRight();
    return result;
  }

  // public void setLeft(LinkedAVLTree<T> newLeft){
	 //    root = newLeft.root;
  // }
  
  public void setRoot(BTNode<T> root){
    this.root = root;
  }
  
  public int balanceFactor(){
	  return getLeft().height() - getRight().height();
	  
  }

  public void leftRotation(){
    LinkedBinaryTree<T> newRoot = leftRotation(this);
    this.root = newRoot.root;
  }

  public void rightRotation(){
    LinkedBinaryTree<T> newRoot = rightRotation(this);
    this.root = newRoot.root;
  }

  private LinkedBinaryTree<T> leftRotation(LinkedBinaryTree<T> oldParent){

    LinkedAVLTree<T> newLeft = new LinkedAVLTree(oldParent.getRootElement());
    if(oldParent.getLeft().root != null) newLeft.root.setLeft(oldParent.getLeft().root.left);

    LinkedAVLTree<T> newParent = new LinkedAVLTree<T>(oldParent.getRight().getRootElement());
    newParent.root.setRight(oldParent.getRight().root.right);
    newParent.root.setLeft(newLeft.root);

    return newParent;

  }

  

  private LinkedBinaryTree<T> rightRotation(LinkedBinaryTree<T> oldParent){
    LinkedAVLTree<T> newRight = new LinkedAVLTree<T>(oldParent.getRootElement());
    if(oldParent.getRight().root != null) newRight.root.setRight(oldParent.getRight().root.right);

    LinkedAVLTree<T> newParent = new LinkedAVLTree<T>(oldParent.getLeft().getRootElement());
    newParent.root.setLeft(oldParent.getLeft().root.left);
    newParent.root.setRight(newRight.root);

    return newParent;

  }

  private void checkBalance(){
    if(this.balanceFactor() < -1) findLeft(this, this.root);
    else if(this.balanceFactor() > 1) findRight(this, this.root);
  }

  private void findLeft(LinkedAVLTree<T> tree, BTNode<T> replace) {
	  if(tree.balanceFactor() < -1 && tree.getRight().balanceFactor() < -1) findLeft(tree.getRight(), replace.right);
//	  if(tree.balanceFactor() < -1 && tree.getRight().balanceFactor() > -1) findLeft(tree.getRight().getLeft(), replace.right);
    // if(tree.balanceFactor() < -1 && tree.getRight().balanceFactor() >= -1) tree.leftRotation(); 
	  if(tree.balanceFactor() < -1 && tree.getRight().balanceFactor() > tree.balanceFactor()) {
	      this.setRoot(leftRotation(tree).root);

    }
  }

  private void findRight(LinkedAVLTree<T> tree, BTNode<T> replace){
    if(tree.balanceFactor() > 1 && tree.getLeft().balanceFactor() > 1) findRight(tree.getLeft(), replace.left);
    else if(tree.balanceFactor() > 1 && tree.getLeft().balanceFactor() < 1) findRight(tree.getLeft().getRight(), replace.left);
    else if(tree.balanceFactor() > 1 && tree.getLeft().balanceFactor() <= 1){
      
//      LinkedAVLTree<T> newTree = tree.rightRotation();
      // this.remove(newTree.getRootElement());
      // this.add(newTree.root);
//      root.find(newTree.getRootElement()) = newTree.root;
    // this.root.left.right = rightRotation(tree).root;
      // replace.setRight(rightRotation(tree).root);
      // tree.setRoot(rightRotation(tree).root);
      
      if(this.height() > 2) replace.setRight(rightRotation(tree).root);
      else{
        this.setRoot(rightRotation(tree).root);
      }

    } 
  }


  @Override
  public void add(T item){
    super.add(item);
    checkBalance();
  }

}
