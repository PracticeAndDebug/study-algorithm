package fbnq;
public class Fbnq{
	public int count1(int n){
		if( n <= 2){ return 1;}
		return count1(n-1)+count1(n-2);
	}
	public int count2(int n){
		int a=0,b=1,c=0;
		for( int i=1;i<n;i++){
			c=b;
			b=a+b;
			a=c;
		}
		return b;
	}
}
