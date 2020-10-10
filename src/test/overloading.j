// Test for method overloading

class A {

    Void main (){
        F f;
        f = new F();
        f.func();
        f.func(1);
        f.func("a");
        f.func(1,"a");
        f.func("a",1);
        f.func(null); // coerces to string
        f.func(1,null);
        f.gg(new A());
        f.gg(new B());
    }
}

class F {
    Int func() { return 1; }
    Int func(Int i) { return 2; }
    Int func(String s) { return 3; }
    Int func(Int i, String s) { return 4; }
    Int func(String s, Int i) { return 5; }
    Int gg(A a) { return 6; }
    Int gg(B b) { return 7; }
}


class B{}