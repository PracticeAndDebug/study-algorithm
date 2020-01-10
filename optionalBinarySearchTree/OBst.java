package optionalBinarySearchTree;
import common.ArrMethod;
import common.Tool;

public class OBst{
	static public int run(int p[],int q[]){
		// let e[1~n+1,0~n] be new table	
		int n=p.length-1;
		int e[][] = Tool.range2(n+2,n+1,-1);
		// let w[0~n,0~n] be new table
		int w[][] = Tool.range2(n+1,n+1,-1);
		// let root[0~n,0~n] be new table
		int root[][] = Tool.range2(n+1,n+1,-1);

		int i=0,j=0,r=0,x=0,y=0;
		// x is n+1-i
		// y is max(i)

		for( x = 0; x <= n; x = x+1 ){//repeat n+1 txmes
			for( y = n+1-x ; y >= 1 ; y = y-1 ){//repeat n+2-x txmes
				i = y;
				j = x + y - 1;
				getE(p,q,e,w,i,j);
			}
		}
		return e[1][n];
	}

	static public boolean getW(int p[] ,int q[],int w[][],int i,int j){
		int r=0,result=q[i-1];
		for(r=i;r<=j;r++){
			result=result+q[r]+p[r];
		}
		w[i][j] = result;
		return true;
	}

	static public boolean getE(int p[],int q[],int e[][],int w[][],int i,int j){
		int r=0,result=0;
		if( j == (i-1) ){
			result = q[j];
		}
		else{
			getW(p,q,w,i,j);
			result = e[i][j-1] + e[i+1][j] + w[i][j];
			for(r=i;r<=j;r++){
				result = Tool.min( result , e[i][r-1]+e[r+1][j]+w[i][j]);
			}
		}
		e[i][j] = result;
		return true;
	}
}
