public class LinkedAVLTree<T extends Comparable<T>> extends LinkedBinaryTree{

  public class AVLTNode extends BSTNode{
    private int height;

    private int getHeight(){
      return this.height;
    }
    private void setHeight(int h){
      this.height = h;
    }

  }

  public int height(AVLTNode node){
    
  }

}
