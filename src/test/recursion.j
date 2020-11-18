class Main {

Void main(){
  Test x;
  x = new Test();
  x.str = "a";
  println(x.f(5));
  x.str = "bb";
  println(x.f(5));
  x = new Test();
  x.str = "c";
  println(x.f(3));
}
}

class Test {
  String str;
  String f(Int count) {
    if (count == 0) { return null; }
    else {
      return f(count-1) + " " + g(count);
    }
  }
  String g(Int count) {
    if (count == 0) { return null; }
    else {
      return g(count-1) + str;
    }
  }
}
