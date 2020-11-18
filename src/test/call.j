class Main {

Void main(){
  println((new A()).f());
}
}

class A {

Int f() {
  return f(1);
}
Int f(Int a) {
  return f(a, 2);
}
Int f(Int a, Int b) {
  return f(a, b, 3);
}
Int f(Int a, Int b, Int c) {
  return f(a, b, c, 4);
}
Int f(Int a, Int b, Int c, Int d) {
  return f(a, b, c, d, 5);
}
Int f(Int a, Int b, Int c, Int d, Int e) {
  return f(a, b, c, d, e, 6);
}
Int f(Int a, Int b, Int c, Int d, Int e, Int f) {
  return f(a, b, c, d, e, f, 7);
}
Int f(Int a, Int b, Int c, Int d, Int e, Int f, Int g) {
  return f(a, b, c, d, e, f, g, 8);
}
Int f(Int a, Int b, Int c, Int d, Int e, Int f, Int g, Int h) {
  println(h);
  println(g);
  println(f);
  println(e);
  println(d);
  println(c);
  println(b);
  println(a);
  return 0;
}

}
