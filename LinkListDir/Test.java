package LinkListDir;
import java.util.Random;
import java.util.Arrays;
import LinkListDir.LinkList;
public class Test {

  public int randMax = 100;

  public void checkItOut(){
    boolean flag = this.insertTest();
    flag = flag && this.searchTest();
    flag = flag && this.deleteTest();
    System.out.println( flag );
  }

  public boolean insertTest(){
    LinkList linkList = new LinkList();
    int randArr [] = getRandArr(1,100);// need seed and length as inputs
    insertIntegers( linkList , randArr );
    return compare2ArrsNoOrder( linkList.getArr(), randArr );
  }

  public boolean searchTest(){
    LinkList linkList = new LinkList();	  
    LinkListNode node = null;
    for(int i=0;i<100;i++){
      linkList.insert( i );
      node = linkList.search(i);
      if( (node==null) || (node.key != i)){
        return false;
      }
    }	  
    for( int i=100;i<200;i++){
      node = linkList.search(i);
      if( node!=null ){
        return false;
      }
    }
    return true;
  }

  public boolean deleteTest(){
    LinkList linkList = new LinkList();
    int randArr1 [] = getRandArr(1,1000);// need seed and length as inputs
    int randArr2 [] = getRandArr(2,1000);// need seed and length as inputs
    // int randArr3 [] = getRandArr(3,10);// need seed and length as inputs
    int randArr3 [] = { 102,103,104,105,106,107,108,109,110};
    insertIntegers( linkList , randArr1 );
    insertIntegers( linkList , randArr2 );
    deleteIntegers( linkList , randArr2 );
    deleteIntegers( linkList , randArr3 );
    if ( compare2ArrsNoOrder( linkList.getArr(), randArr1 ) == false ){
      assert false:"first compare wrong";
      return false;
    }
    deleteIntegers( linkList , randArr1 );
    assert (linkList.getArr().length == 0):"second compare wrong";
    return (linkList.getArr().length == 0);
  }

  public void deleteIntegers(LinkList linkList,int arr[]){
    for(int i=0;i<arr.length;i++){
      linkList.delete(arr[i]);
    } 
  }

  public void insertIntegers(LinkList linkList, int arr[]){
    for(int i=0;i<arr.length;i++){
      linkList.insert(arr[i]);
    } 
  }

  public boolean compare2ArrsNoOrder( int arr1[],int arr2[]){
    if( arr1 == null && arr2 == null ){
      return true;
    }
    assert (arr1.length == arr2.length): "length differ:"+arr1.length+" : "+arr2.length;
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    for( int i=0;i<arr1.length;i++){
      assert (arr1[i]==arr2[i]) : ( "compare:"+i+" , "+arr1[i]+" : "+arr2[i] );
    }
    return true;
  }

  public int [] getRandArr( int seed , int length){
    int arr [] = new int [ length ];
    Random rand = new Random( seed );
    for( int i = 0; i < length; i++){
      arr[ i ] = rand.nextInt( this.randMax );
    }
    return arr;
  }
}
