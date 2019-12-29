package redBlackTree;

import redBlackTree.RedBlackTree;
import redBlackTree.RedBlackNode;
import common.ArrMethod;

public class RedBlackTreeTest{
	int seed = 1;
	int length = 100;
  int repeat = 100;

  public boolean run(){
		insertTest();
    int flag = 0;
    for(int i=1;i< repeat ;i++){
      seed = i;
      // System.out.println(deleteTest()+" i:"+i);
      deleteTest();
    }
		return true;
	}

	public boolean insertTest(){
	  int randArr [] = ArrMethod.getRandArr( seed, length );
		RedBlackTree rbt = new RedBlackTree();
		insertArr( rbt, randArr );
		ArrMethod.compare( randArr, rbt.getArr() );
		return true;
	}
	
	public RedBlackNode [] insertArr( RedBlackTree tree , int arr [] ){
		RedBlackNode rbtArr [] = new RedBlackNode [ length ];
		for( int i=0;i<length;i++){
			rbtArr[i] = tree.insert( arr[i] );
		}
		return rbtArr;
	}

	public boolean deleteTest(){
	  int randArr1 [] = ArrMethod.getRandArr( seed, length );
	  int randArr2 [] = ArrMethod.getRandArr( seed+1, length );

		RedBlackTree rbt = new RedBlackTree();

		insertArr( rbt, randArr1);
		insertArr( rbt, randArr2);

    // ArrMethod.printArr(randArr1, "randArr1");
    // ArrMethod.printArr(randArr2, "randArr2");

    try{
		  for( int i=0;i<randArr2.length;i++){
		  	rbt.delete( randArr2[i] );
        featureTest2( rbt );
      }
		  ArrMethod.compare( randArr1 , rbt.getArr() );
    }
    catch( AssertionError e){
      System.out.println( "seed:"+seed+" "+e.toString() );
    }
    return true;
	}
	
  public boolean featureTest2( RedBlackTree rbt){
		// features of a red black tree
		String step0 = "every node must be black or red.";
		String step1 = "root must be black.";
		String step2 = "leave must be black.";
		String step3 = "red node should have black children.";
		String step4 = "every node should have same black height to all its' leaves";

		
		// get all nodes in the tree
		RedBlackNode rbtArr [] = rbt.traverse();
		// step 0
		for( RedBlackNode node : rbtArr ){
			assert ( node.color == "black" || node.color == "red") : step0;
		}
		// step 1
		assert rbt.root.color == "black" : step1;
		// step 2
		assert rbt.nil.color == "black" : step2;
		// step 3
		for( RedBlackNode node : rbtArr ){
			if( node.color == "red" ){
				assert ( node.left.color== "black" || node.right.color == "black") : step3;
			}
		}
		// step 4
		blackHeightTest( step4 );
		return true;
  }

	public boolean featureTest(){
		// features of a red black tree
		String step0 = "every node must be black or red.";
		String step1 = "root must be black.";
		String step2 = "leave must be black.";
		String step3 = "red node should have black children.";
		String step4 = "every node should have same black height to all its' leaves";

		
		// constructor a red black tree, and insert random integers
		RedBlackTree rbt = new RedBlackTree();
	  int randArr [] = ArrMethod.getRandArr( seed, length );
		insertArr( rbt, randArr );
		// get all nodes in the tree
		RedBlackNode rbtArr [] = rbt.traverse();
		// step 0
		for( RedBlackNode node : rbtArr ){
			assert ( node.color == "black" || node.color == "red") : step0;
		}
		// step 1
		assert rbt.root.color == "black" : step1;
		// step 2
		assert rbt.nil.color == "black" : step2;
		// step 3
		for( RedBlackNode node : rbtArr ){
			if( node.color == "red" ){
				assert ( node.left.color== "black" || node.right.color == "black") : step3;
			}
		}
		// step 4
		blackHeightTest( step4 );
		return true;
	}

	public boolean blackHeightTest( String assertMessage ){
		// define another node class with height 

		RedBlackTree rbt = new RedBlackTree();
		int counter = 0;
	  int randArr [] = ArrMethod.getRandArr( seed, length );
		int backupArr [] = null;
		RedBlackNode pointer = null;
    int defaultKey = -10;

		insertArr( rbt , randArr );
		RedBlackNode rbtArr [] = rbt.traverse();

		// put all key values in backupArr
		backupArr = rbt.getArr();
		// set key of all nodes to defaultKey
		for( RedBlackNode node : rbtArr ){
			node.key = defaultKey;
		}
		// check black height
		for( RedBlackNode node : rbtArr ){
			if( node.left == rbt.nil || node.right == rbt.nil ){// find a bottom node
				counter = 0;
				pointer = node;
				while( pointer != rbt.nil ){
					if( pointer.key == defaultKey ){
						pointer.key = counter;
            break;
					}
					else{
						assert pointer.key == counter : assertMessage;
					}
					if( pointer.color == "black"){
						counter++;
					}
					pointer = pointer.top;
				}
			}
		}
		// set key values to initial value
		for( int i = 0;i<length;i++){
			rbtArr[i].key = backupArr[i];
		}
		return true;
	}
}
