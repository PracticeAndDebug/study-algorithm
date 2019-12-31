package longestCommonSequence;
import longestCommonSequence.Lcs;
import common.StrMethod;
import java.util.Random;
public class Test{
	int seed = 1;
	int strLength = 9;
	int repeat = 20;


	public boolean run(){
		return fixedInput();
	}

	public boolean fixedInput(){
		Lcs l = new Lcs();
		assert l.compare("qqqaaa","bbbaaa" ).equals("aaa") : "no" ;
		assert l.compare("aqabqc","bbbabc" ).equals("abc") : "no" ;
		assert l.compare("qaqaqa","bababa" ).equals("aaa") : "no" ;
		assert l.compare("qaqbqc","zazbzcz").equals("abc") : "no" ;
		assert l.compare(""      ,"zazbzc" ).equals(""   ) : "no" ;
		assert l.compare("aaa"   ,"bb"     ).equals(""   ) : "no" ;
		assert l.compare("aba"   ,"bb"     ).equals("b"  ) : "no" ;
		return true;
	}

	// this one is wrong
	/*
	public boolean randInput(){
		Random r=new Random(seed);	
		StrMethod stm = new StrMethod();
		Lcs l = new Lcs();
		StringBuilder source1 = new StringBuilder(stm.getRandStr( strLength ));
		StringBuilder source2 = new StringBuilder(stm.getRandStr( strLength ));
		int i=0;
		char mark1 = '~';
		char mark2 = '=';
		String result = "";

		for( i=0;i< strLength ;i++){
			switch( r.nextInt(4)){
				case 0: 
				result = result + source1.charAt(i);
				break;
				
				case 1: 
				source1.setCharAt(i,mark1);
				break;

				case 2: 
				source2.setCharAt(i,mark2);
				break;

				case 3: 
				source1.setCharAt(i,mark1);
				source2.setCharAt(i,mark2);
				break;
			}
		}
		String underTest = l.compare( source1.toString(),source2.toString() );

		System.out.println( "source1 : " + source1 );
		System.out.println( "source2 : " + source2 );
		System.out.println( "result: " + result );
		System.out.println( "underTest: " + underTest);
		
		return underTest.equals(result);
	}
	*/
}
