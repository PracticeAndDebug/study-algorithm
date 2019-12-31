package longestCommonSequence;

public class Lcs{
	public String compare(String s1,String s2){
		return digui(s1,s2,"");
	}

	public String digui(String s1,String s2,String res){

		String last1 = lastString( s1 );
		String last2 = lastString( s2 );
		if( s1.isEmpty() || s2.isEmpty() ){
			return "";
		}

		if(s1.length() == 1){ 
			 //Sys tem.out.println( "1  s1:"+s1+" s2: "+s2+" res: "+res);
			return singleCharStr(s1,s2)+res; 
		}
		if(s2.length() == 1){ 
			 //Sys tem.out.println( "2  s1:"+s1+" s2: "+s2+" res: "+res);
			return singleCharStr(s2,s1)+res; 
		}
		if( last1.equals( last2 ) ){ 
			res = last1+res;
			 //Sys tem.out.println( "3  s1:"+s1+" s2: "+s2+" res: "+res);
			return digui( cutTail(s1),cutTail(s2), res );
		}

		String a = digui( cutTail(s1) , s2 , res );
		String b = digui( s1 , cutTail(s2) , res );
		res = longerString(a,b);
		 //Sys tem.out.println( "4  s1:"+s1+" s2: "+s2+" res: "+res);
		return res ;
	}

	// length of s1 must be 1
	private String singleCharStr(String s1,String s2){
		return (s1.length() == 1 && s2.contains( s1 )) ? s1 : "";
	}

	// return last char of a string
	private char lastChar(String s){
		if( s.isEmpty() ){ return '\0';}
		int len = s.length();
		return s.charAt(len-1);
	}

	// transform last char of a string to string and return it
	private String lastString(String s){
		String r = String.valueOf( lastChar(s));
		return r;
	}

	private String cutTail(String s){
		int len = s.length();
		if( s.length() == 0 ){ return "";}
		return s.substring(0,len-1);
	}
	private String longerString(String s1,String s2){
		return s1.length()>s2.length() ? s1 : s2;
	}
}
