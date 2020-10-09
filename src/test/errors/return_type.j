class Main {

Void main(Int i, Int a, Int b,Int d){
    i = a;
}
}
class Dummy {
    Int x;
    Void e() { x = x; return;} // ok
    Int f() { x = x; return;} // error
    Int g() { x = x; return "123";} // error
    Void h() { x = x; return "123";} // error
}