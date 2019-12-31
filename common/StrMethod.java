package common;
import java.util.Random;

public class StrMethod{
	public String getRandStr (int length,int seed){
		Random r = new Random( seed );
		String s = "";
		for( int i=0;i<length;i++){
			s = s + (char)( r.nextInt(26) + (int)('a'));
		}
		return s;
	}
	public String getRandStr( int length){
		return getRandStr( length,1);
	}
}
