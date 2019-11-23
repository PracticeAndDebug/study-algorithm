package BinSearchTreeDir;
import BinTreeDir.BinTree;
class BinSearchTree{
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
     //System.out.println("\n====================start to delete =====================");
    BinTree node = search( inputKey );
     // //System.out.println("Bst delete inputKey: "+ inputKey );
    if( node == null){
	      //System.out.println("Bst delete 0");
      return null;
    }
    length --;
    if( node.noChild() ){
	      //System.out.println("Bst delete 1");
      if( node == root ){
        root = null;
      }
      else if( node == node.top.left ){
        node.top.left = null;
      }
      else{
        node.top.right = null;
      }
      printMidTraverse("Bst delete 1");
    }
    else if( node.leftChildOnly() ){
	      //System.out.println("Bst delete 3");
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
      printMidTraverse("Bst delete 3");
    }
    // case: node has right child
    else{
      BinTree succNode = successor( node );
      boolean nodeIsRoot = ( node == root );
      boolean rightSucc = ( node.right == succNode );
      
       //System.out.println("Bst delete 4");
       //System.out.println("Bst delete root.key     : " + root.key );
       //System.out.println("Bst delete node.key     : " + node.key );
       //System.out.println("Bst delete succNode.key : " + succNode.key );

      if     ( ( nodeIsRoot == true  ) && ( rightSucc == true  ) ) {
	 //System.out.println("Bst delete 4.1");
        if( root.left != null ){
	  succNode.left = root.left;
	  root.left.top = succNode;
	}	      
	root = succNode;
	printMidTraverse("Bst delete 4.1");
      }
      else if( ( nodeIsRoot == false ) && ( rightSucc == true  ) ) {
	      //System.out.println("Bst delete 4.2");
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
	printMidTraverse("Bst delete 4.2");
      }
      else if( ( nodeIsRoot == true  ) && ( rightSucc == false ) ) {
	 //System.out.println("Bst delete 4.3");
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
	printMidTraverse("Bst delete 4.3");
      }
      else if( ( nodeIsRoot == false ) && ( rightSucc == false ) ) {
	 //System.out.println("Bst delete 4.4");
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
	printMidTraverse("Bst delete 4.4");
      }
    }
     //System.out.println("==================== stop delete =====================\n");
    return node;
  }

  public void printMidTraverse( String header){
    int length = 40;
    int count = 1;
    BinTree node = min( root );
     //System.out.println( header );
    while( node != null ){
      String msg  = ( " ,count: " + count + " ,key:"+node.key );
      String msg2 = ( " ,left: "+( node.left == null ? null : node.left.key) );
      String msg3 = ( " ,right :"+( node.right == null ? null : node.right.key) );
      String msg4 = ( " ,top :"+( node.top == null ? null : node.top.key) );
      //  //System.out.println( msg + msg2 + msg3 + msg4 );
      node = successor( node );
      count++;
      if( count >= length ){
         //System.out.println("******* too long");
	break;
      }
    }
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

  /*
  public BinTree update( int inputOldKey, int inputNewKey ){ 
     // //System.out.println( "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" );
     // //System.out.println( "update inputOldKey:"+inputOldKey );
    BinTree node = search( inputOldKey );
     // //System.out.println( "update node.key:"+( node == null ? null : node.key ) );
    if( node != null ){
      node.key = inputNewKey;
    }
    return node;
  }
  */

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
