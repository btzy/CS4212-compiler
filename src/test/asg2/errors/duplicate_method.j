class Main {

Void main(Int i, Int a, Int b,Int d){
    i = a;
}
}
class Dummy {
    Int x;
    Void f() { x = x;}
    Void f(String y) { x = y;}
    Void f(String more_arg) { more_arg = x;}
}