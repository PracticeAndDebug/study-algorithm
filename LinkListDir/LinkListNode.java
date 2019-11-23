package LinkListDir;
class LinkListNode {
  LinkListNode prev;
  LinkListNode next;
  int key;

  public LinkListNode( int inputKey) {
      key = inputKey ;
      prev = null;
      next = null;
  }
}
