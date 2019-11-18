// ~~~
//

class Bin_search_tree extends Bin_tree{
  Bin_search_tree left = null;
  Bin_search_tree right= null;
  Bin_search_tree top = null;

  Bin_search_tree c_bst_arr [] = null;
  Bin_search_tree c_root = null;

  int c_traverse_array [] = null;
  int c_traverse_index = 0;
  int real_length = 0;

  public void array_to_tree( int arr [][] ){// array input
    // input array shoule be checked... ... to be added	 
         //System.out.println("bst 0:"+this.value);
    int length = arr.length;
    int width = arr[0].length;
    int i=0;
    int left = -1;
    int right = -1;
    c_bst_arr = new Bin_search_tree [ length ];
         //System.out.println("bst 1:"+this.value);
    for( i = 0; i < length; i++ ) {
      c_bst_arr[i] = new Bin_search_tree();
    }
    for( i = 0; i < length; i++ ) {
      // dirty code below
      left= arr[i][1];
      right= arr[i][2];
         //System.out.println("=====bst 2:"+this.value);
      value = arr[i][3];
         //System.out.println("bst 3:"+this.value);
      if( left != -1 ){
        c_bst_arr[i].left = c_bst_arr[ left ];
	c_bst_arr[left].top = c_bst_arr[i];
      }
      if( right!= -1 ){
        c_bst_arr[i].right = c_bst_arr[ right ];
	c_bst_arr[right].top = c_bst_arr[i];
      }
      c_bst_arr[i].value = value;
      //  //System.out.println( "arr_to_tree:"+c_bst_arr[i].value );
    }
    c_root = null;
    real_length = c_bst_arr.length;
  } 

  public Bin_search_tree get_root(){
    if(c_root != null ){
      return c_root;
    }
    int i=0;
    for(i=0;i<c_bst_arr.length;i++){
      if(c_bst_arr[i].top == null){
        c_root = c_bst_arr[i];
	return c_root;
      }
    }
    return c_root;
  }

  public int [] mid_traverse () { // use recursion
    get_root();
    c_traverse_array = new int [ real_length ];
    c_traverse_index = 0;
    mid_traverse_part( c_root );
    return c_traverse_array;
  }

  public void mid_traverse_part( Bin_search_tree bst ){
    if( bst.left != null ){
       mid_traverse_part( bst.left );
    }
    c_traverse_array[ c_traverse_index ] = bst.value;
    c_traverse_index++;
    if( bst.right != null ){
      mid_traverse_part( bst.right );
    }
  }

  public int [] mid_traverse_2 () { // no use recuision
	return null;
    
  }

  public Bin_search_tree max (){
    Bin_search_tree result = this;
    while( (result != null) && (result.right != null) ){
      result = result.right;
    }
    return result;
  }

  public Bin_search_tree min (){
    Bin_search_tree result = this;
    while( (result != null) && (result.left != null) ){
      result = result.left ;
    }
    return result;
  }
    
  public Bin_search_tree successor(){
    if(this.right != null ){
      return this.right.min();
    }	  
    Bin_search_tree current_node = this;
    Bin_search_tree upper_node = this.top;
    while( (upper_node != null) && ( current_node != upper_node.left ) ) { 
      current_node = upper_node;
      upper_node = upper_node.top;
    }
    return upper_node;
  }

  public Bin_search_tree predecessor(){
    if(this.left!= null ){
      return this.left.max();
    }	  
    Bin_search_tree current_node = this;
    Bin_search_tree upper_node = this.top;
    while( (upper_node != null) && ( current_node != upper_node.right) ) { 
      current_node = upper_node;
      upper_node = upper_node.top;
    }
    return upper_node;
  }

  public void insert ( int num ){
    real_length++;
    Bin_search_tree upper = null , lower = this.get_root();
    // String print_message = (lower!=null) ? (lower.value+" ^_^!") : "lower is null" ;
     //  //System.out.println( print_message );
    Bin_search_tree next_node = new Bin_search_tree ();
    next_node.value = num;
    while( lower != null ){
      upper = lower;	    
      //  //System.out.println("lower:"+lower.value);
      if( lower.value > num ){
        lower = lower.left;
      }
      else{
        lower = lower.right;
      }
    }
    if( upper == null ){
      this.value = num;
    }
    else if( upper.value > num ){
      upper.left = next_node;
      next_node.top = upper;
    }
    else{
      upper.right = next_node;
      next_node.top = upper;
    }
    //  //System.out.println( "fuck upper after insert:"+upper.value);
    //  //System.out.println( "fuck upper.son after insert:"+( upper.value>num ? (" ;left."+upper.left.value) : (" ;right."+upper.right.value )) );
  }

  public boolean delete( Bin_search_tree node ){
    return true;
  }

  public Bin_search_tree search ( int num ){
    return null;
  }
}
