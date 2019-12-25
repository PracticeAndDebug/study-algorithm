package redBlackTree;

// left,right,top, int key,String color
// no default value
public class RedBlackNode{
	RedBlackNode left ;
	RedBlackNode right ;
	RedBlackNode top ;
	int key;
	String color;

	public RedBlackNode( int key ){
		this.key = key;
		color = "black";
	}
}
