package cutRod;
public class Cut{
	public int cutRod( int p[],int n){
		if( n==0){return 0;}
		int res=-1;
		for( int i=1;i<= min(n,p.length-1) ;i++){
			res=max(res , p[i]+cutRod(p,n-i));
		}
		return res;
	}
	public int max(int a,int b){
		return a>b ? a:b;
	}
	public int min(int a,int b){
		return a<b ? a:b;
	}

	public int memoCutRod(int priceArr[],int n){
		int resArr [] = new int [n+1];
		for( int i=0;i<resArr.length;i++){
			resArr[i]=-1;
		}
		return memoCutRodAux( priceArr, n ,resArr );
	}

	public int memoCutRodAux(int priceArr[],int n,int resArr[]){
		if( resArr[n] > 0){
			return resArr[n];
		}
		if( n==0){return 0;}
		int res=-1;
		for( int i=1;i<= min(n,priceArr.length-1) ;i++){
			res=max(res , priceArr[i]+memoCutRodAux(priceArr,n-i,resArr));
		}
		resArr[n] = res;
		return res;
	}

	public int bottomUpCutRod( int priceArr[],int n){
		int maxPriceArr [] = new int [n+1];
		int i=0,j=0,result=-1;
		maxPriceArr[0] = 0;
		for( i=1;i<=min(n,priceArr.length-1);i++){
			result = -1;
			for( j=1;j<=i;j++){
				result = max( result , priceArr[j]+maxPriceArr[i-j] );
			}
			maxPriceArr[i] = result;
		}
		if(n>priceArr.length-1){
			int a = n / (priceArr.length-1);
			int b = n % (priceArr.length-1);
			return a*maxPriceArr[ priceArr.length-1 ] + maxPriceArr[b]; 
		}
		return maxPriceArr[n];
	}
	public void extendedBottomUpCutRod(int priceArr[],int n){
		int maxPriceArr[] = new int [n+1];
		int howToCut[] = new int [n+1];
		int i=0,j=0,result=-1;
		maxPriceArr[0] = 0;
		for( i=1;i<=n;i++){
			result = -1;
			for( j=1;j<=i;j++){
				if( priceArr[j]+maxPriceArr[i-j] > result ){
					result = priceArr[j]+maxPriceArr[i-j] ;
					howToCut[i] = j;
				}
			}
			maxPriceArr[i] = result;
		}
		System.out.println( "max price:"+maxPriceArr[n] );
		int idx = n;
		while( idx > 0 ){
			System.out.print( howToCut[idx]+ ":" );
			idx = idx - howToCut[idx];
		}
	}
}
