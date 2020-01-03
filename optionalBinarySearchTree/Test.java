package optionalBinarySearchTree;
import common.ArrMethod;
import optionalBinarySearchTree.Obst;

public class Test{
	public boolean run(){
		int p[] = { 0,15,10, 5,10,20};
		int q[] = { 5,10, 5, 5, 5,10};
		System.out.println( recurse(p,q));
		assert recurse(p,q)==275:"no";
		return true;
	}

	public int runRecurse(int seed,int length){
		ArrMethod am = new ArrMethod();
		int p[] = am.getRandArr( seed , length );
		int q[] = am.getRandArr( seed , length );
		return recurse(p,q);
	}

	public int recurse(int p[],int q[]){
		return part(p,q,1,p.length-1);
	}

	public int part(int p[],int q[],int i,int j){
		int result = 99999;
		if( j==(i-1)){ return q[j];}
		for( int idx = i;idx<=j; idx++){
			result = min( result,part(p,q,i,idx-1)+part(p,q,idx+1,j)+addArr(p,i,j)+addArr(q,i-1,j));
		}
		return result;
	}

	public int addArr( int arr[],int start,int end){
		int result = 0,idx=0;
		for( idx=start;idx<=end;idx++){
			result=arr[idx]+result;
		}
		return result;
	}

	public int min(int a,int b){ return a>b?b:a;}
}
