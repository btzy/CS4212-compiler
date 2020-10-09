class Main {

Void main(Int i, Int a, Int b,Int d){
    (new A()).f(null);
    (new A()).f(123);
    (new A()).g();
}
}

class A {
    Int
        g   ;
    Void f(Bool b) { b = b; }
    Void f(String s) { s = s; }
    Void f(String s1, String s2) { s1 = s2; }
    Void f(A a) { a=a;}
    Void f(Main s) {s=s;}
}