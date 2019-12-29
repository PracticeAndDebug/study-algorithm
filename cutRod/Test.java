package cutRod;
public class Test{
	public boolean run(){
		case4();
		return true;
	}
	public boolean case1(){
		Cut c0 = new Cut();
		int priceArr [] = { 0,1, 5, 8, 9,10,17,17,20,24,30};

		int res  [] = new int [11];
		res[1 ] = 1;
		res[2 ] = 5;
		res[3 ] = 8;
		res[4 ] = 10;
		res[5 ] = 13;
		res[6 ] = 17;
		res[7 ] = 18;
		res[8 ] = 22;
		res[9 ] = 25;
		res[10] = 30;
		for( int i=1;i<11;i++){
			if(c0.cutRod(priceArr,i) != res[i]){
				return false;
			}
		}
		return true;
	}
	public boolean case2(){
		Cut c0 = new Cut();
		int priceArr [] = { 0,1, 5, 8, 9,10,17,17,20,24,30};
		for( int i=1;i<21;i++){
			System.out.println(i+" "+c0.cutRod(priceArr,i));
		}
		return true;
	}
	public boolean case3(){
		Cut c0 = new Cut();
		int priceArr [] = { 0,1, 5, 8, 9,10,17,17,20,24,30};
		int r1=0,r2=0;
		for( int i=1;i<151;i++){
			// System.out.println(i+" "+c0.memoCutRod(priceArr,i));
			// System.out.println(i+" "+c0.bottomUpCutRod(priceArr,i));
			r1 = c0.memoCutRod(priceArr,i);
			r2 = c0.bottomUpCutRod(priceArr,i);
			//alert c0.memoCutRod(price,i)
			assert r1 == r2 : "differ";
		}
		return true;
	}
	public boolean case4(){
		Cut c0 = new Cut();
		int priceArr [] = { 0,1, 5, 8, 9,10,17,17,20,24,30};
		c0.extendedBottomUpCutRod(priceArr,9);
		return true;	
	}
}
