package common;
import common.ArrMethod;

public class Tool{

	static boolean printFlag = true;

	static public int [] randArr(int length,int seed){
		return ArrMethod.getRandArr(seed,length);
	}

  static public int max(int a,int b){ 
    return a>b ? a : b ;
  }

  static public int min(int a,int b){ 
    return a<b ? a : b ;
  }

  static public int [][] range2(int len1,int len2,int val){
    int arr[][] = new int [len1][],i=0,j=0;
    for( i=0;i<len1;i++ ){
      arr[i] = new int [len2];
      for(j=0;j<len2;j++){
        arr[i][j] = val;
      }
    }
    return arr;
  }

	// default value is 0
  static public int [][] range2(int len1,int len2){
		return range2(len1,len2,0);
	}

  static public int [] range(int len,int val){
    int arr[]=new int [len],i=0;
    for(i=0;i<len;i++){
      arr[i]=val;
    }
    return arr;
  }

	// default value is 0
  static public int [] range(int len){
		return range(len,0);
	}

  static public void print( String s ){ 
		if( printFlag ){
			System.out.print( s );
		}
  }

  static public void printRange(int arr[]){
    for(int i:arr){
			print( i+" ");
    }
		print("\n");
  }

  static public void printRange2(int arr[][]){
    for(int i[] :arr){
      printRange(i);
    }
  }
}
