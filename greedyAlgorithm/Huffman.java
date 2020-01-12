package greedyAlgorithm;
import java.util.LinkedList;
public class Huffman{

	public class Node{
		char letter;
		int freq;
		Node left;
		Node right;
		Node(int freq , char letter){
			this.freq = freq;
			this.letter = letter;
		}
		
		public String toString(){
			return ("freq:"+freq+" letter:"+letter);
		}
	}

	void p(String s){ 
		System.out.println( s );
	}

	Node solve(int freqArr[],char letterArr[]){
		LinkedList<Node> list = new LinkedList<Node>();
		Node node  = null;
		Node node1 = null;
		Node node2 = null;

		for( int i = 0 ; i < freqArr.length ; i++ ){
			node = new Node( freqArr[i],letterArr[i] );
			list.add( node );
		}

		for( int i = 0 ;i < ( freqArr.length - 1 ) ; i++ ){
			node1 = mini( list );
			node2 = mini( list );
			node = new Node( node1.freq + node2.freq , ' ');
			list.add( node );
		}

		return list.getFirst();
	}

	Node mini( LinkedList<Node> list ){
		Node node = list.get(0);

		for(int i=0;i<list.size();i++){
			if( list.get(i).freq < node.freq ){
				node = list.get(i);
			}
		}

		list.remove( node );
		return node;
	}

	Node [] getNodeArr(int freqArr[],char letterArr[]){
		// assert freqArr.length == letterArr.length
		Node nodeArr [] = new Node [ freqArr.length ];

		for( int i=0;i<freqArr.length;i++){
			nodeArr[i] = new Node( freqArr[i],letterArr[i] );
		}
		
		return nodeArr;
	}


	public boolean test(){
		int freqArr [] = {5,4,3,2,1};
		char letterArr [] = {'a','b','c','d','e'};
		// getNodeArr( freqArr,letterArr );
		solve( freqArr,letterArr);
		return true;
	}
}
