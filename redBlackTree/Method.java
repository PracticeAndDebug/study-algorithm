package redBlackTree;

import redBlackTree.Node;

public class Method {

  public boolean insert( Node root , Node node ){
		return true;
  } 

  public int [] traverse( Node root ){ // mid traverse
    return null;
  }

  public Node delete( Node root, Node node ){
    return null;
  }

  public Node search( Node root, int key ){
    return null;
  }

  public Node successor( Node root, Node node){
    return null;
  }
 
  public Node min ( Node root ){
    Node upper = null, lower = root;
    while( lower != null ){
      upper = lower;
      lower = lower.left;
    }
    return upper;
	}

  public Node max ( Node root ){
    Node upper = null, lower = root;
    while( lower != null ){
      upper = lower;
      lower = lower.right;
    }
    return upper;
  }

}
