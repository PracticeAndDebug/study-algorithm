import java.io.*;
import java.util.Arrays;
import java.util.Random;
// import java.io.FileReader;

class Bin_search_tree_test extends Bin_search_tree{
  int bin_tree_length = 15;
  int bin_tree_width  = 4;
  String dir_name = "./texts";
  String file_name_arr[] = null;
  String file_name = "";
  String file_data = "";

  boolean c_print_flag = false;
  boolean show_false_message = true;

  private int file_int_arr[][] = new int [ bin_tree_length ][ bin_tree_width ];

  File dir_pointer = null;
  FileReader file_pointer = null;

  int i = 0;
  int j = 0;
  int character = 0;

  public static void main(String args[]) {
    Bin_search_tree_test bt = new Bin_search_tree_test( );
    bt.check_it_out();
  }

  private void check_it_out(){
	   //System.out.println( "1:"+this.value);
    // String file_name = "./texts/bin_search_tree_data__0";
    boolean flag = true;
    this.read_dir_file_names();
	   //System.out.println( "2:"+this.value);
    int seed_count = 1;
    for( String file_name : file_name_arr ){
      seed_count++;
      this.read_single_file( "./texts/"+file_name );
      this.file_data_to_arr();
      this.file_name = file_name;
      this.array_to_tree( this.file_int_arr );
      //  //System.out.println( file_name+":"+this.get_root().value );
      //  //System.out.println( file_name+":"+this.c_bst_arr[7].value );
      flag = this.max_test();
      flag = flag && this.min_test();
      flag = flag && this.successor_test();
      flag = flag && this.predecessor_test();
      flag = flag && this.insert_test( seed_count );
      flag = flag && this.delete_test( seed_count );
      if( flag == false){
	break;
      }
    }
     System.out.println("finally:"+flag);
  }

  // String dir_name = "./texts";
  // String file_name_arr[] = null;
  private void read_dir_file_names( ) {
    File target_dir = new File( dir_name );
    //  //System.out.println( target_dir.exists() );
    // if( target_dir.exists() ){
    file_name_arr = target_dir.list();
    // for( String file_name  : file_name_arr ) {  //System.out.println( file_name ); }
  }

  private boolean max_test(){
    Bin_search_tree bst = this.get_root();
    //  //System.out.println( "    root:"+bst.value);
    int max = bst.max().value;
    // int item [] = null;
    //  //System.out.println( "    max:"+max);
    for( int item [] : file_int_arr ){
	    // //System.out.println( "    max_test:"+item[3] );
      if( max < item[3] ){
        if( show_false_message ){
           //System.out.println( file_name +":"+" ;max:"+max+" ;item:"+item[3]);
        }	      
        return false;
      }
    }
    return true;
  }

  private boolean min_test(){
    int min = this.get_root().min().value;
    for( int item [] : file_int_arr ){
      if( min > item[3] ){
        if( show_false_message ){
	   //System.out.println( file_name +":"+" ;min:"+min+" ;item:"+item[3]);
	}	      
        return false;
      }
    }    
    return true;
  }

  private boolean successor_test(){
    int arr[]=get_value_arr_in_order(true);	  
    Bin_search_tree node = c_bst_arr[0];
    for( int i=0;i<file_int_arr.length;i++){
      if( node.value != arr[i] ){
        if( show_false_message){
	   //System.out.println(file_name+" : "+"successor : "+" ;ok:"+arr[i]+" ;bad:"+node.value);
	}	      
        return false;
      }
      node = node.successor();
    }
    return true;
  }

  private boolean predecessor_test(){
    int arr[]=get_value_arr_in_order(false);	  
    Bin_search_tree node = c_bst_arr[ file_int_arr.length -1 ];
    for( int i=0;i<file_int_arr.length;i++){
      if( node.value != arr[i] ){
        if( show_false_message){
	   //System.out.println(file_name+" : "+"predecessor: "+" ;ok:"+arr[i]+" ;bad:"+node.value);
	}	      
        return false;
      }
      node = node.predecessor();
    }
    return true;
  }

  private int [] get_value_arr_in_order( boolean rise_order ){
    int arr [] = new int [ file_int_arr.length ];
    int i = 0;
    int length = file_int_arr.length;
    int mid_int = 0;
    for( i=0;i<file_int_arr.length;i++){
      arr[i]=file_int_arr[i][3];	   
    }
    Arrays.sort(arr);
    if( rise_order == false ){
      for( i=0; i< (length/2) ;i++){
        mid_int = arr[i];	     
        arr[i] = arr[length-i-1];
	arr[length-i-1] = mid_int;
      }
    }
    return arr;
  }

  private void read_single_file( String single_file_name ){
    try{
      file_pointer = new FileReader( single_file_name );
      file_data = "";
      while( true ){
        character = file_pointer.read();
	if( character == -1 ){
	  break;
	}
	file_data = file_data + (char)character;
      }
    }
    catch(IOException e){
       //System.out.println( e.toString() );
    }
  }

  private boolean mid_traverse_test(){
    int arr [] = new int [ file_int_arr.length ];
    int i = 0;
    Bin_search_tree bst_pointer = this.get_root();
    for( i=0;i<file_int_arr.length;i++){
      arr[i]=file_int_arr[i][3];	   
    }
    Arrays.sort(arr);
    int arr_2 [] = this.mid_traverse();
    for( i=0;i<arr.length;i++){
      if( arr[i] != arr_2[i] ){
        return false;
      }
    }
    return true;
  }

  private boolean insert_test( int seed ){
    int length = file_int_arr.length ;
    int insert_count = length / 2;
    int full_arr [] = new int [ length + insert_count ];
    int k=0;
    Random r = new Random( seed );
    int insert_rand_int = 0;
    for( k=0;k< length;k++){ // copy arr
      full_arr[k] = file_int_arr[k][3];
    }
    for( k=0;k<insert_count;k++){ // insert many random integers into tree
      insert_rand_int = r.nextInt(1000);	    
	//     //System.out.println( "random_integers: "+insert_rand_int );
      full_arr[ length + k ] = insert_rand_int;
      this.insert( insert_rand_int );//!!! insert here
    }
    Arrays.sort( full_arr );
    int arr_after_insert[] = this.mid_traverse();
    for( k=0;k<(insert_count+length);k++){ // compare 
      if( arr_after_insert[k] != full_arr[k] ){
        if( show_false_message ){
	   //System.out.println(file_name+" ;false_int:"+arr_after_insert[k]+" ;true_int:"+full_arr[k]);
	}	      
        return false;
      }
    }
    return true;
  }

  private boolean delete_test( int seed ){
	  /*
    // generate random
    Random r = new Random( seed );
    int length = file_int_arr.length;
    int start = r.nextInt( length/4 );	    
    int end = start_index + length/2;
    
    // generate correct int arr
    // [0,start-1] [start,end] [end+1,length-1]
    int k = 0;
    int correct_arr [] = new int [ start + length - end - 1 ];
    while( k < length){
      correct_arr [k] = file_int_arr[k][3];
      k = (k==(start-1)) ? (end+1) : k;
    } 

    // start to delete 
    Bin_search_tree node_to_delete = null;
    for( k = start; k<= end; k++){
      node_to_delete = c_bst_arr[ k ];
      this.get_root().delete( node_to_delete );
    }

    // check by comparing 
     //System.out.println(this.value);
    */
    return true;

  }

  private int get_random_int (int seed ) {
    Random r = new Random( seed );
    return r.nextInt(1000);
  }

    /*
    if( c_print_flag == true ){
      for(int item: arr){
         //System.out.println("mid_traverse_test:item:"+item);
      }
      while(true){
	if( bst_pointer == null){
	  break;
	}
         //System.out.println( "Bin_search_tree_test:mid_traverse_test:"+bst_pointer.getClass().getName() );
        bst_pointer = bst_pointer.left;		
      }
      for(int item: arr_2){
         //System.out.println("mid_traverse:item:"+item);
      }
    }
    */

  private void file_data_to_arr(){
    String _str_arr [] = file_data.split("\n");
    String _str_arr_2 [] = null;
    for( i=0; i< _str_arr.length; i++){
      _str_arr_2 = _str_arr[i].split(" ");
      for( j=0;j< _str_arr_2.length ;j++){
        file_int_arr[i][j] = Integer.valueOf( _str_arr_2[j] );
      }
    }
  }
}
