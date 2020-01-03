package optionalBinarySearchTree;
import common.ArrMethod;
import optionalBinarySearchTree.Obst;

public class Test{
	// assertion string 
	String assertStr1 = "fixed chcek recurse wrong";
	String assertStr2 = "fixed check obst wrong";
	String assertStr3 = "rand check wrong";

	// fixed check args
	int p[] = { 0,15,10, 5,10,20};
	int q[] = { 5,10, 5, 5, 5,10};
	int fixedResult = 275;

	// random check args
	int repeat = 10;
	int strLen = 8;
	
	public boolean run(){
		int res = 0;
		res = recurse(p,q);
		assert res == fixedResult : assertStr1 +"res:"+fixedResult+" ?:"+ res ;
		res = Obst.run(p,q);
		assert res == fixedResult : assertStr2+"res:"+fixedResult+" ?:"+ res ;
		for( int i=0;i< repeat ;i++){
			compareCheck(i, strLen );
		}
		return true;
	}

	public boolean compareCheck(int seed,int length){
		ArrMethod am = new ArrMethod();
		int p[] = am.getRandArr( seed , length );
		int q[] = am.getRandArr( seed , length );
		assert recurse(p,q) == Obst.run(p,q): assertStr3;
		//return recurse(p,q);
		return true;
	}

	public int recurse(int p[],int q[]){
		return recurse(p,q,1,p.length-1);
	}

	public int recurse(int p[],int q[],int i,int j){
		int result = 99999;
		if( j==(i-1)){ return q[j];}
		for( int idx = i;idx<=j; idx++){
			result = min( 
				result,
				recurse(p,q,i,idx-1)+
				recurse(p,q,idx+1,j)+
				addArr(p,i,j)+
				addArr(q,i-1,j)
			);
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
