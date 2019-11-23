package BinTreeDir;
public class BinTree{
  public BinTree left;
  public BinTree right;
  public BinTree top;
  public int key;

  public BinTree ( int inputKey){
    key = inputKey;
  }

  public boolean leftChildOnly(){
    return ( left != null && right == null );
  }

  public boolean rightChildOnly(){
    return ( right != null && left == null );
  }

  public boolean noChild(){
    return ( right == null && left == null);
  }

  public boolean singleChild(){
    return ( leftChildOnly() != rightChildOnly() );
  }
  /*
  public int getKey (){
    return key;
  }

  public BinTree getLeft(){
    return left;
  }

  public BinTree getRight(){
    return right;
  }

  public BinTree getTop(){
    return top;
  }
  */
}
