package redBlackTree;

import redBlackTree.RedBlackNode;

/*
private varibles: root,nil,defaultKey
public methods: traverse,insert,delete,search;
private methods: max,min,successor,leftRotate,rightRotate,insertFixUp,deleteFixUp,
private methods: isLeftSon,isRightSon;
*/

public class RedBlackTree {
	public int defaultKey = -1;
	public int maxLength = 100000;
	public RedBlackNode root;
	public RedBlackNode nil = new RedBlackNode( defaultKey );

  // initialize;
	public RedBlackTree(){
		nil.left= nil;
		nil.right = nil;
		nil.top = nil;
		root = nil;
	}

	public boolean insert( RedBlackNode node ){
    if( root == nil ){
			root = node;
    }
    else{
      RedBlackNode upper = nil;
      RedBlackNode lower = root;
			int inputKey = node.key;
      // search for the place to insert
      while( lower != nil ){
        upper = lower;
        if( inputKey > lower.key ){
          lower = lower.right;
        }
        else{
          lower = lower.left;
        }
      }
      node.top = upper;
      if( inputKey > upper.key ){
        upper.right = node;
      }
      else{
        upper.left = node;
      }
    }
		node.color = "red";
		node.left = nil;
		node.right = nil;
		
		insertFixUp( node );
		return true;
  }


	public RedBlackNode insert ( int key ){
		RedBlackNode node = new RedBlackNode( key );
		node.top = nil;
		node.left = nil;
		node.right = nil;
		insert( node );
		return node ;
	}

	public void insertFixUp( RedBlackNode z ){
		RedBlackNode y = nil;
		while( z.top.color == "red" ){
      if( z.top == z.top.top.left ){
		  	y = z.top.top.right;
		  	if( y.color == "red"){
		  		z.top.color = "black";
		  		y.color = "black";
					z.top.top.color = "red";
					z = z.top.top;  		
		  	}
				else if( z == z.top.right ){
					z = z.top;
					leftRotate( z );
				}
				else{
				  z.top.color = "black";
				  z.top.top.color = "red";
				  rightRotate( z.top.top );
				}
		  }
      else if( z.top == z.top.top.right ){
		  	y = z.top.top.left;
		  	if( y.color == "red"){
		  		z.top.color = "black";
		  		y.color = "black";
					z.top.top.color = "red";
					z = z.top.top;  		
		  	}
				else if( z == z.top.left ){
					z = z.top;
					rightRotate( z );
				}
				else{
				  z.top.color = "black";
				  z.top.top.color = "red";
				  leftRotate( z.top.top );
				}
		  }
		  root.color = "black";
		}


  }

  // change x.top y.top x.right y.left
  public void leftRotate( RedBlackNode x ){
		RedBlackNode y = x.right;

		x.right = y.left;
		if( y.left != nil ){
		  y.left.top = x;
		}

		y.top = x.top;
		if( x.top == nil ){
			root = y;
		}
		else if( x == x.top.left ){
			x.top.left = y;
		}
		else {
			x.top.right = y;
		}

		y.left = x;
		x.top = y;// x.top = y;
  }

  public void rightRotate( RedBlackNode x ){
		RedBlackNode y = x.left;

		x.left = y.right;
		if( y.right != nil ){
		  y.right.top = x;
		}

		y.top = x.top;
		if( x.top == nil ){
			root = y;
		}
		else if( x == x.top.right ){
			x.top.right = y;
		}
		else {
			x.top.left = y;
		}

		y.right = x;
		x.top = y;// x.top = y;
  }

	public RedBlackNode search( int key ){
    RedBlackNode n = root;
    int counter = 0;
    while( n != nil ){
      counter++;
      assert counter < maxLength:"over search"+key;
      if( n.key > key ){
        n = n.left;
      }
      else if( n.key < key ){
        n = n.right;
      }
      else{
        return n;
      }
    }
    return nil;
  }

	public boolean transplant( RedBlackNode u, RedBlackNode v){
	  if( u.top == nil ){
	    root = v;
	  }
	  else if( u == u.top.left){
	    u.top.left = v;
	  }
	  else{
	    u.top.right = v;
	  }
	  if( v != nil ){
		  v.top = u.top;
	  }
    return true;
	};

	public boolean delete ( RedBlackNode node ){ 
    if( node.left == nil ){
      transplant( node, node.right );
    }
    else if( node.right == nil ){
      transplant( node, node.left );
    }
    else{
      RedBlackNode next = min( node.right );
      if( next.top != node ){
        transplant( next , next.right );
        next.right = node.right;
        next.right.top = next;
      }
      transplant( node,next);
      next.left = node.left;
      next.left.top = next;
    }
    return true;
  }

  public boolean delete ( int key ){
    RedBlackNode node = search( key );
    return (node==nil) ? false : rbDelete( node );
  }

  public boolean rbDelete( RedBlackNode z){
    RedBlackNode x=nil,y=nil;
    y=z;
    String yOriginalColor = y.color;
    if(z.left==nil){
      x=z.right;
      transplant(z,z.right);
    }
    else if(z.right==nil){
      x=z.left;
      transplant(z,z.left);
    }
    else{
      y=min(z.right);
      yOriginalColor=y.color;
      x=y.right;
      if(y.top ==z){
        x.top=y;
      }
      else{
        transplant(y,y.right);
        y.right=z.right;
        y.right.top = y;
      }
      transplant(z,y);
      y.left=z.left;
      y.left.top=y;
      y.color=z.color;
    }
    int fixFlag = 0;
    if(yOriginalColor=="black"){
      rbDeleteFixUp(x);
    }
    return true;
  }

  public boolean rbDeleteFixUp(RedBlackNode x){
    RedBlackNode w=nil;
    while(x!=root && x.color=="black" && x!= nil){
      if( isLeftSon(x)){
        w=x.top.right;
        if(w.color=="red"){
          w.color="black";
          x.top.color="red";
          leftRotate(x.top);
          w=x.top.right;
        }
        else if(w.left.color=="black" && w.right.color=="black"){
          w.color="red";
          x=x.top;
        }
        else if(w.right.color=="black"){
          w.left.color="black";
          w.color="red";
          rightRotate(w);
          w=x.top.right;
        }
        else{
          w.color=x.top.color;
          x.top.color="black";
          w.right.color="black";
          leftRotate(x.top);
          x=root;
        }
      }
      else if( isRightSon(x)){
        w=x.top.left;
        if(w.color=="red"){
          w.color="black";
          x.top.color="red";
          rightRotate(x.top);
          w=x.top.left;
        }
        else if(w.right.color=="black" && w.left.color=="black"){
          w.color="red";
          x=x.top;
        }
        else if(w.left.color=="black"){
          w.right.color="black";
          w.color="red";
          leftRotate(w);
          w=x.top.left;
        }
        else{
          w.color=x.top.color;
          x.top.color="black";
          w.left.color="black";
          rightRotate(x.top);
          x=root;
        }
      }
    }
    x.color="black";
    nil.color="black";
    return true;
  }

  public RedBlackNode [] traverse(){
		// when this tree is a empty tree
		if( root == nil ){
			return new RedBlackNode[0];
		}
		
		// not empty tree
		int length = 1;
		RedBlackNode head = new RedBlackNode( defaultKey );
		RedBlackNode tail = head;
		RedBlackNode pointer = min( root );
		head.right = pointer;

		while( true ){
			pointer = successor( tail.right );
			if( pointer == nil ){
				break;
			}
			length++;
			// head = new RedBlackNode( defaultKey );
			tail.left = new RedBlackNode( defaultKey );
			tail.left.right = pointer;
			tail = tail.left;

			// max length check
			assert length<=maxLength : "max length exceed";
		}
		
		RedBlackNode rbtArray [] = new RedBlackNode[ length ];
		pointer = head;
		for( int i=0;i<length;i++){
			rbtArray[ i ] = pointer.right;
			pointer = pointer.left;
		}
		return rbtArray;
	} 

	public int [] getArr(){
		RedBlackNode rbtArr [] = traverse();
		int arr [] = new int [ rbtArr.length ];
		for( int i=0;i<rbtArr.length;i++){
			arr[ i ] = rbtArr[ i ].key;
		}
		return arr;
	}

  private RedBlackNode max( RedBlackNode node ){
    int counter=0;
		while( node.right != nil ){
      counter++;
      assert counter<maxLength:"max counter exceed";
			node = node.right;
		}
		return node;
	} 

  private RedBlackNode min( RedBlackNode node ){
    int counter = 0;
		while( node.left != nil ){
      counter++;
      assert counter<maxLength:"min counter exceed";
			node = node.left;
		}
		return node;
  } 

  private RedBlackNode successor( RedBlackNode node ){
	  if( node.right != nil ){
			return min( node.right );
		}	
		if( isLeftSon( node )){
			return node.top;
		}
		while( isRightSon( node )){
			node = node.top;
		}
		return node.top;
	} 

	private boolean isLeftSon( RedBlackNode node ){
		return node != nil && node.top != nil && node == node.top.left;
	}

	private boolean isRightSon( RedBlackNode node ){
		return node != nil && node.top != nil && node == node.top.right;
	}

}
