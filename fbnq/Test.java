package fbnq;
import fbnq.Fbnq;
public class Test{
	public boolean run(){
		case0();
		return true;
	}
	public boolean case0(){
		Fbnq f = new Fbnq();
		System.out.println( f.count2( 30));
		System.out.println( f.count1( 30 ));
		return true;
	}
	public boolean case1(){
		Fbnq f = new Fbnq();
		for( int i=1;i<100;i++){
			// System.out.println( i + " : " + f.count2( i ));
			assert f.count2(i) == f.count1(i);
		}
		return true;
	}
}
