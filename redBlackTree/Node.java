package redBlackTree;

public class Node {
  public Node left;
  public Node right;
  public Node top;
  public boolean isRed;
  public int key = -1;

  public Node( int key ){
    this.key = key;
  }
}
