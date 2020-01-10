package greedyAlgorithm;

public class ActivitySelection{

	public int solve(int s[],int f[]){
		// return 1+recursiveActivitySelector(s,f,0,s.length-1);
		return greedyActivitySelector(s,f);
	}

	int greedyActivitySelector(int s[],int f[]){
		int res = 1,idx = 1, ft = f[0] ;
		while( idx < s.length ){
			if( s[ idx ] >= ft ){
				res++;
				ft = f[ idx ] ;
				// System.out.println( "idx:"+idx+" ft:"+ft);
			}
			idx++;
		}
		return res;
	}

	int recursiveActivitySelector(int s[],int f[],int k,int n){
		int m = k+1;
		while( m<=n && s[m] < f[k] ){
			m=m+1;
		}
		if( m<=n){
			return 1+recursiveActivitySelector(s,f,m,n);
		}
		// else if( m == n){
		// 	System.out.println( m );
		// 	return 1;
		// }
		else{
			return 0;
		}
	}

	public boolean test(){
		int s[] ={ 1, 3, 0, 5, 3, 5, 6, 8, 8, 2,12};
		int f[] ={ 4, 5, 6, 7, 9, 9,10,11,12,14,16};
		int result = 4;
		int solution = solve(s,f);
		assert( solution == result ): ("should be:"+result+" get:"+solution);

		int s2[] = { 1, 3, 5, 9, 9, 9,12};
		int f2[] = { 3, 5, 7,10,11,12,13};
		result = 5;
		solution = solve(s2,f2);
		assert( solution == result ): ("should be:"+result+" get:"+solution);

		return true;
	}

}
