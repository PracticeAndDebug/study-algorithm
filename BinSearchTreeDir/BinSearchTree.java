package BinSearchTreeDir;
import BinTreeDir.BinTree;
public class BinSearchTree{
  public BinTree root;
  public int length;
  public int maxLength;

  public BinSearchTree(){
    root = null;
    length = 0;
    maxLength = 1000;
  }
  
  public void insert( int inputKey ){ 
    length++;	  
    if( root == null ){
      root = new BinTree( inputKey );
    }	  
    else{
      BinTree upper = null;
      BinTree lower = root;
      BinTree inputTree = new BinTree( inputKey );
      // search for the place to insert
      while( lower != null){
        upper = lower;
	if( inputKey > lower.key ){
	  lower = lower.right;
	}
	else{
	  lower = lower.left;
	}
      }
      inputTree.top = upper;
      if( inputKey > upper.key ){
        upper.right = inputTree;
      }
      else{
        upper.left = inputTree;
      }
    }
  }

  public BinTree delete( int inputKey ){ 
    BinTree node = search( inputKey );
    if( node == null){
      return null;
    }
    length --;
    if( node.noChild() ){
      if( node == root ){
        root = null;
      }
      else if( node == node.top.left ){
        node.top.left = null;
      }
      else{
        node.top.right = null;
      }
    }
    else if( node.leftChildOnly() ){
      if( node == root ){
        root = root.left;
	root.top = null;
      }
      else if( node == node.top.left ){
        node.top.left = node.left;
	node.left.top = node.top;
      }
      else{
        node.top.right = node.left;
	node.left.top = node.top;
      }
    }
    // case: node has right child
    else{
      BinTree succNode = successor( node );
      boolean nodeIsRoot = ( node == root );
      boolean rightSucc = ( node.right == succNode );
      
      if     ( ( nodeIsRoot == true  ) && ( rightSucc == true  ) ) {
        if( root.left != null ){
	  succNode.left = root.left;
	  root.left.top = succNode;
	}	      
	root = succNode;
      }
      else if( ( nodeIsRoot == false ) && ( rightSucc == true  ) ) {
        if( node.left != null ){
	  succNode.left = node.left;
	  node.left.top = succNode;
	  node.left = null;
	  node.right = null;
	}

	if( node == node.top.left ){
	  node.top.left = succNode;
	  succNode.top = node.top;
	  node.top = null;
	}
	else if( node == node.top.right ){
	  node.top.right = succNode;
	  succNode.top = node.top;
	  node.top = null;
	}
      }
      else if( ( nodeIsRoot == true  ) && ( rightSucc == false ) ) {
        if( succNode.right != null ){
	  succNode.top.left = succNode.right;
	  succNode.right.top = succNode.top;
	  succNode.right = null;
	  succNode.top = null;
	}
	else{
	  succNode.top.left = null;
	  succNode.top = null;
	}
	if( node.left != null ){
	  node.left.top = succNode;
	  succNode.left = node.left;
	  node.left = null;
	}
	if( node.right != null){
	  node.right.top = succNode;
	  succNode.right = node.right;
	  node.right = null;
	}
	root = succNode;
      }
      else if( ( nodeIsRoot == false ) && ( rightSucc == false ) ) {
        if( succNode.right != null ){
	  succNode.top.left = succNode.right;
	  succNode.right.top = succNode.top;
	  succNode.right = null;
	}
	else{
	  succNode.top.left = null;
	}
	if( node.left != null ){
	  node.left.top = succNode;
	  succNode.left = node.left;
	  node.left = null;
	}
	if( node.right != null){
	  node.right.top = succNode;
	  succNode.right = node.right;
	  node.right = null;
	}
	if( node == node.top.left ){
	  node.top.left = succNode;
	}
	else{
	  node.top.right = succNode;
	}
	succNode.top = node.top;
	node.top = null;
      }
    }
    return node;
  }

  public BinTree search( int inputKey ){
    if( root == null ){
      return null;
    }
    BinTree node = root;
    while( node != null && node.key != inputKey ){
      if( inputKey > node.key ){
        node = node.right;
      }
      else{
        node = node.left;
      }
    }
    return node;
  }

  public BinTree max( BinTree node ){
    BinTree result = node;
    if(result == null){
      return null;
    }
    while( result.right != null ){
      result = result.right;
    }
    return result;
  }

  public BinTree min( BinTree node ){
    BinTree result = node;
    if(result == null){
      return null;
    }
    while( result.left != null ){
      result = result.left;
    }
    return result;
  }

  public int [] midTraverse(){
    int arr [] = new int [ length ];
    if( root == null ){
      return arr;
    }
    BinTree node = min( root );
    for( int i = 0; i < length; i++){
      arr[i] = node.key;
      node = successor( node );
    }
    return arr;
  }

  public BinTree successor( BinTree node ){
    if( node.right != null){
      return min( node.right );	      
    }
    else{
      if( node.top != null && node == node.top.left ){
        return node.top;	      
      }
      else if( node.top != null && node == node.top.right ){
        BinTree result = node;	      
	while( result.top != null && result == result.top.right ){
	  result = result.top;
	}
        return result.top;
      }      
      else{
        return null;
      }
    }
  }

  public BinTree predecessor( BinTree node ){
    if( node.left != null ){
      return max(node.left);
    }
    if( node.top != null && node == node.top.right ){
      return node.top;
    }
    if( node.top != null && node == node.top.left ){
      BinTree result = node;
      while( result.top != null && result.top.left == result ){
        result = result.top;
      }
      return result.top ;
    }
    return null;
  }
}
