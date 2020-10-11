class MainC {

    Void main (){
        A a;
        B b;
        C c;
        a = new A();
        b = new B();
        c = new C();
        a.f(123);
        a.g(456);
        b.f(789);
        b.g("a");
        c.setB(b);
        c.getB().f(345);
        main();
    }
}

class A {
    Int f(Int x) {
        g(x);
        (new B()).f(x);
        (new MainC()).main();
        return (new B()).g(null);
    }
    Int g(Int x) {
        return 1;
    }
}

class B{
    Int f(Int x) {
        g("test");
        return (new A()).f(x);
    }
    Int g(String s) {
        return 1;
    }
}

class C{
    B b;
    Void setB(B b) {
        this.b=b;
    }
    B getB() {
        return b;
    }
}
