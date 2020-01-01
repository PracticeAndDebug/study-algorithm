package longestCommonSequence;
import longestCommonSequence.Lcs;
import common.StrMethod;
import java.util.Random;
import java.util.Date;
public class Test{
	int seed = 1;
	int strLength = 10;
	int repeat = 1000;


	public boolean run(){
		return fixedInput() & randInput();
	}

	public boolean fixedInput(){
		Lcs l = new Lcs();
		assert l.compare1("qqqaaa","bbbaaa" ).equals("aaa") : "no" ;
		assert l.compare1("aqabqc","bbbabc" ).equals("abc") : "no" ;
		assert l.compare1("qaqaqa","bababa" ).equals("aaa") : "no" ;
		assert l.compare1("qaqbqc","zazbzcz").equals("abc") : "no" ;
		assert l.compare1(""      ,"zazbzc" ).equals(""   ) : "no" ;
		assert l.compare1("aaa"   ,"bb"     ).equals(""   ) : "no" ;
		assert l.compare1("aba"   ,"bb"     ).equals("b"  ) : "no" ;
		return true;
	}

	public boolean randInput(){
		StrMethod stm = new StrMethod();
		Lcs lcs = new Lcs();
		String r1="",r2="";

		for( int i=0;i< this.repeat;i++){
			String s1 = stm.getRandStr( strLength ,2*i);
			String s2 = stm.getRandStr( strLength ,2*i+1);
			
			

			r1 = lcs.compare1(s1,s2);


			r2 = lcs.compare2(s1,s2);


			assert lcs.compare1(s1,s2).equals( lcs.compare2(s1,s2)) : "no";
	 	}

		return true;
	}


	public void print(String s){ System.out.print(s);}

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
