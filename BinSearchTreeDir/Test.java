package BinSearchTreeDir;
import LinkListDir.LinkList;
import BinSearchTreeDir.BinSearchTree;
import BinTreeDir.BinTree;
import java.util.Arrays;
public class Test{
  int seed;
  int testLength;
  LinkListDir.Test listTest;
  BinSearchTree tree;
  LinkList list ;

  public Test( int seed, int testLength){
    seed       = seed;
    testLength = testLength;
    initComponent();
  }	

  public Test( ){
    seed = 1;
    testLength = 1000;
    initComponent();
  }

  public boolean checkItOut(){
    insertTest();	  
    for( int i=0;i<10;i++){
      maxTest();
      minTest();
    }
    for( int i=0;i<20;i++){
      seed = i*10;	    
      deleteTest();
    }
    deleteTest();
    searchTest();
    // updateTest();
    midTraverseTest();
    successorTest();
    predecessorTest();
    return true;	  
  }

  private void insertTest(){ 
    initComponent();	  
    int randArr [] = insertRandArrToTree();
    assert listTest.compare2ArrsNoOrder( tree.midTraverse() , randArr ) : "insert fail";
  }

  private void deleteTest(){ 
    initComponent();
    int randArr [] = insertRandArrToTree();
    int bigRandArr[] = listTest.getRandArr( seed , testLength );
    String msg = "delete test wrong";

    for( int i = 0; i < testLength; i++){
      list.insert( randArr[i] );
    }

    for( int i = 0; i < testLength; i++){
      bigRandArr[ i ] = listTest.randMax + 10 + bigRandArr[i];
    }

    for( int i = 0; i < testLength; i++){
      tree.delete( randArr[i] );
      list.delete( randArr[i] );
      // treeIsBinSearchTree( tree );
      int [] resultArr = tree.midTraverse();
      assert listTest.compare2ArrsNoOrder( tree.midTraverse() , list.getArr() ) : msg;
    }

    for( int i = 0; i < testLength; i++){
      tree.delete( bigRandArr[i] );
      list.delete( bigRandArr[i] );
      assert listTest.compare2ArrsNoOrder( tree.midTraverse() , list.getArr() ) : msg;
    }

  }

  private void treeIsBinSearchTree( BinSearchTree inputTree ){
    int arr [] = inputTree.midTraverse();
    int arr2 [] = inputTree.midTraverse();
    Arrays.sort( arr );
    assert listTest.compare2ArrsNoOrder( arr , arr2 ) : "not BinSearchTree";
  }

  private void searchTest(){ 
    initComponent();	  
    for( int i = 0; i < testLength ; i++){
      tree.insert( i );
      assert tree.search( i ) != null : "search should be valid";
    }
    for( int i = testLength + 100 ; i < testLength + 200 ; i++){
      assert tree.search( i ) == null : "search should be null";
    }
  }

  private void maxTest(){
    initComponent();	  
    int randArr [] = insertRandArrToTree();
    int maxInt = randArr[0];
    for( int i = 0; i < randArr.length; i++){
      maxInt = ( randArr[i] > maxInt ? randArr[i] : maxInt );
    }
    assert maxInt == tree.max( tree.root ).key : "max test wrong";
  }

  private void minTest(){
    initComponent();	  
    int randArr [] = insertRandArrToTree();
    int minInt = randArr[0];
    for( int i = 0; i < randArr.length; i++){
      minInt = ( randArr[i] < minInt ? randArr[i] : minInt );
    }
    assert minInt == tree.min( tree.root ).key : "min test wrong";
  }

  private void midTraverseTest(){
    initComponent();	  
    int randArr [] = insertRandArrToTree();
    int travArr [] = tree.midTraverse();
    Arrays.sort( randArr );
    assert randArr.length == travArr.length : "midTraverse wrong:"+randArr.length+" , "+travArr.length;
    for( int i = 0; i < randArr.length; i++ ){
      assert randArr[i] == travArr[i] : "midTraverse wrong:"+randArr[i]+" , "+travArr[i];
    }
  }
  private void successorTest(){
    initComponent();
    int randArr [] = insertRandArrToTree();
    Arrays.sort( randArr );
    BinTree pointer = tree.min( tree.root );
    for( int i = 0; i < randArr.length; i++){
      assert pointer.key == randArr[i] : "successor test wrong:"+pointer.key+","+randArr[i];
      pointer = tree.successor( pointer );
    }
  }

  private void predecessorTest(){
    initComponent();
    int randArr [] = insertRandArrToTree();
    BinTree pointer = tree.max( tree.root );
    Arrays.sort( randArr );
    for( int i = 0; i < randArr.length; i++){
      assert pointer != null : "pointer is null";
      assert pointer.key == randArr[ randArr.length - 1 - i] : "predecessor test wrong:"+pointer.key+","+randArr[i];
      pointer = tree.predecessor( pointer );
    }
  }

  private void initComponent(){
    listTest   = new LinkListDir.Test();
    tree       = new BinSearchTree();	  
    list       = new LinkList();
  }

  private int [] insertRandArrToTree(){
    int randArr [] = listTest.getRandArr(seed,testLength);
    for( int i = 0; i < randArr.length; i++){
      tree.insert( randArr[i] );
    }
    return randArr;
  }
}
