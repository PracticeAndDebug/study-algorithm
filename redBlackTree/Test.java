package redBlackTree;

import java.util.Random;
import redBlackTree.Node;
import redBlackTree.Method;


public class Test {
  public boolean run(){
		printArr( getRandArr( 1,100,5), "getRandArr seed ") ;
		printArr( getRandArr( 100,5), "getRandArr no seed ") ;
    return true;
  }

  public int getRand ( int max ){
    Random rand = new Random( );
		return rand.nextInt( max );
  }

  public int [] getRandArr ( int max , int length ){
    int arr [] = new int [ length ];
		for( int i = 0; i < length; i++){
			arr[ i ] = getRand( max );
		}
		return arr;
  }

  public int [] getRandArr( int seed, int max , int length ){
    int arr [] = new int [ length ];
    Random rand = new Random( seed );
		for( int i = 0; i < length; i++){
			arr[ i ] = rand.nextInt( max );
		}
		return arr;
  }

  public boolean printArr( int arr[] , String header ){
    for( int i = 0; i < arr.length; i++){
      System.out.println( header + " ,arr[" + i + "]:" + arr[i] );
    }
    return true;
  }

}
