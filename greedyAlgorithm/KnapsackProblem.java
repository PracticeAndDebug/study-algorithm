package greedyAlgorithm;
public class KnapsackProblem{
  public int solve(int price[],int weight[],int maxWeight){
		return recursive( price,weight,50,-1);
	}

	public int recursive(int price[],int weight[],int maxWeight,int idx){
		int i= idx+1;
		int nextWeight = idx < 0 ? maxWeight : maxWeight - weight[idx];
		int result = 0;
		if( maxWeight < 0 ){ return 0;}
		for( ; i < price.length; i++){
			result = max( result, recursive ( price,weight, nextWeight ,i));
			System.out.println("i:"+i+" result:"+result);
		}
		if( idx < 0 ){	return result;}
		return result+ price[idx];
	}

	int max( int a,int b){ return a>b ? a : b ; }

	public boolean test(){
		int weight[] = {60,20,30};
		int price[] = {10,100,120};
		int maxWeight = 50;
		int result = 220;
		int solution = solve( price,weight,maxWeight);
		assert solution == result : "want:"+result+" get:"+solution;
		return true;
	}
}
