package longestCommonSequence;

public class Lcs{
	boolean printFlag = false; 

  public String compare1(String s1,String s2){
		return digui(s1,s2,"");
  }

  public String compare2(String s1,String s2){
    s1 = "a"+s1;s2="b"+s2;
    char charArr1 [] = s1.toCharArray();
    char charArr2 [] = s2.toCharArray();
    int arr[][] = range2(s1.length(),s2.length(),0);
    int directionArr[][] = range2(s1.length(),s2.length(),0);
    int i=0,i1=0, i2=0, n=0,m=0, direction = 0, resLen=0;
    String result = "";

    for( i1=1;i1<s1.length();i1++){
      for(i2=1;i2<s2.length();i2++){
        if( charArr1[ i1 ] == charArr2[ i2 ]){
          n = arr[i1-1][i2-1]+1;
          m = 1;
        }
        else if( arr[i1-1][i2] > arr[i1][i2-1]){
          n = arr[i1-1][i2];
          m = 2;
        }
        else{
          n = arr[i1][i2-1];
          m = 3;
        }
        arr[i1][i2]= n;
        directionArr[i1][i2] = m;
      }
    }

    printRange2( arr );
    printRange2( directionArr );

    i1 = s1.length() -1;
    i2 = s2.length() -1;
    direction = directionArr[ i1 ][ i2 ];
    resLen = arr[i1][i2];

    // for(i=0;i< resLen ;i++){
    while( result.length() < resLen ){
      switch( direction ){
        case 1:
          result = charArr1[i1] + result;
          i1 = i1 -1;
          i2 = i2 -1;
          break;
        case 2:
          i1 = i1 -1;
          break;
        case 3:
          i2 = i2 -1;
          break;
      }
      print( "i:"+i+" direction:"+direction+" i1:"+i1+" i2:"+i2+" res:"+result);
      direction = directionArr[i1][i2];
    }
    return result;
}

  public int max(int a,int b){ 
    return a>b ? a : b ;
  }

  public int [][] range2(int len1,int len2,int val){
    int arr[][] = new int [len1][],i=0,j=0;
    for( i=0;i<len1;i++ ){
      arr[i] = new int [len2];
      for(j=0;j<len2;j++){
        arr[i][j] = val;
      }
    }
    return arr;
  }
  public int [] range(int len,int val){
    int arr[]=new int [len],i=0;
    for(i=0;i<len;i++){
      arr[i]=val;
    }
    return arr;
  }

  public void print( String s ){ 
		if( printFlag ){
			System.out.println( s );
		}
  }

  public void printRange(int arr[]){
    for(int i:arr){
			print( i+" ");
    }
		print("\n");
  }

  public void printRange2(int arr[][]){
    for(int i[] :arr){
      printRange(i);
    }
  }

  public String digui(String s1,String s2,String res){

    String last1 = lastString( s1 );
    String last2 = lastString( s2 );
    if( s1.isEmpty() || s2.isEmpty() ){
      return "";
    }

    if(s1.length() == 1){ 
       //Sys tem.out.println( "1  s1:"+s1+" s2: "+s2+" res: "+res);
      return singleCharStr(s1,s2)+res; 
    }
    if(s2.length() == 1){ 
       //Sys tem.out.println( "2  s1:"+s1+" s2: "+s2+" res: "+res);
      return singleCharStr(s2,s1)+res; 
    }
    if( last1.equals( last2 ) ){ 
      res = last1+res;
       //Sys tem.out.println( "3  s1:"+s1+" s2: "+s2+" res: "+res);
      return digui( cutTail(s1),cutTail(s2), res );
    }

    String a = digui( cutTail(s1) , s2 , res );
    String b = digui( s1 , cutTail(s2) , res );
    res = longerString(a,b);
     //Sys tem.out.println( "4  s1:"+s1+" s2: "+s2+" res: "+res);
    return res ;
  }

  // length of s1 must be 1
  private String singleCharStr(String s1,String s2){
    return (s1.length() == 1 && s2.contains( s1 )) ? s1 : "";
  }

  // return last char of a string
  private char lastChar(String s){
    if( s.isEmpty() ){ return '\0';}
    int len = s.length();
    return s.charAt(len-1);
  }

  // transform last char of a string to string and return it
  private String lastString(String s){
    String r = String.valueOf( lastChar(s));
    return r;
  }

  private String cutTail(String s){
    int len = s.length();
    if( s.length() == 0 ){ return "";}
    return s.substring(0,len-1);
  }
  private String longerString(String s1,String s2){
    return s1.length()>s2.length() ? s1 : s2;
  }
}
