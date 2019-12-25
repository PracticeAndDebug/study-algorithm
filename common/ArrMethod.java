package common;

import java.util.Random;
import java.util.Arrays;

public class ArrMethod{

	static int randMax = 1000;

  public static int [] getRandArr( int seed , int length){
    int arr [] = new int [ length ];
    Random rand = new Random( seed );
    for( int i = 0; i < length; i++){
      arr[ i ] = rand.nextInt( randMax );
    }
    return arr;
  }

  public static boolean compare( int arr1[],int arr2[]){
    if( arr1 == null && arr2 == null ){
      return true;
    }
    assert (arr1.length == arr2.length): "length differ:"+arr1.length+" : "+arr2.length;
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    for( int i=0;i<arr1.length;i++){
      assert (arr1[i]==arr2[i]) : ( "compare["+i+"]:  "+arr1[i]+" : "+arr2[i] );
    }
    return true;
  }

	public static void printArr( int arr [] , String header){
		for( int i=0; i<arr.length; i++){
			System.out.println( header + " , arr[ "+i+" ]: "+arr[i] );
		}
	}

  public static void printArr( int arr [] ){
    printArr( arr , "");
	}

}
