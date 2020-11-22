class Main {

  Void main() {
    List l;
    Node n;
    l = new List();
    l.init();
    n = l.insertFirst(3);
    n = n.insert(4);
    n = n.insert(6);
    l.print();
    println(l.size()+10);
    n = l.item(1);
    println(n.val);
    println(n.next.val);
    n = l.item(2);
    println(n.val);
  }
}

class List {
  Node head;
  
  Void init() {
    head = new Node();
    head.last = true;
  }
  
  Int size() {
    Int x;
    Node curr;
    x = 0;
    curr = head;
    while (!curr.last) {
      x = x + 1;
      curr = curr.next;
    }
    return x;
  }
  
  Node item(Int idx) {
    Node curr;
    curr = head;
    while (idx > 0) {
      curr = curr.next;
      idx = idx - 1;
    }
    return curr;
  }
  
  Node insertFirst(Int val) {
    Node tmp;
    tmp = new Node();
    tmp.next = head;
    tmp.val = val;
    tmp.last = false;
    head = tmp;
    return tmp;
  }
  
  Void print() {
    Node curr;
    curr = head;
    println("Linked list content:");
    while (!curr.last) {
      println(curr.val);
      curr = curr.next;
    }
  }
}

class Node {
  Node next;
  Int val;
  Bool last;
  
  Node insert(Int val) {
    Node tmp;
    tmp = new Node();
    tmp.next = next;
    tmp.val = val;
    tmp.last = false;
    next = tmp;
    return tmp;
  }
}
