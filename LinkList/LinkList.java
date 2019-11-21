class LinkList{
  LinkListNode head;
  int length;

  public LinkList (){
    head = null;
    length = 0;
  }

  public LinkListNode insert( int inputKey ){
    length++;	  
    if( head == null ){
      head = new LinkListNode( inputKey );
      return head;
    }
    LinkListNode node = new LinkListNode( inputKey );
    node.next = head;
    head.prev = node;
    head = node;
    return node;
  }

  public void delete( int inputKey ){
    LinkListNode node = search( inputKey);
    if( node != null ){
      if( length == 1){
        head = null;
      }	   
      else if( head == node){
        head.next.prev = null;
	head = head.next;
      }
      else{
        node.prev.next = node.next;
	if(node.next != null){
	  node.next.prev = node.prev;
	}
      }
      length = length-1;	    
    }
  }

  public LinkListNode search( int inputKey ){
    LinkListNode node = head;
    while( ( node != null )&& node.key != inputKey ){
      node = node.next;
    }    
    return node;
  }

  public int [] getArr(){
    int arr [] = new int [length];
    LinkListNode pointer = head;
    for( int i=0;i<length;i++){
      arr[i] = pointer.key;
      pointer = pointer.next;
    }
    return arr;
  }
}
