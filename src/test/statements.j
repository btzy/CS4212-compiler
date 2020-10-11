class MainC {

    Void main (Int a, String b, Bool c){
        Int x;
        String y;
        Bool z;
        Cl cl;
        readln(x);
        readln(y);
        readln(z);
        readln(a);
        readln(b);
        readln(c);
        println(x);
        println(y);
        println(z);
        println(a);
        println(b);
        println(c);
        if (x==a) {
            x=a;a=x;
            y=b;b=y;
            z=c;c=z;
        } else {
            while (z) {
                z = x<a;
            }
            cl = new Cl();
            cl.inner = cl;
            cl.field = 123;
            cl.f();
            return;
        }
        main(x,y,z);
    }
}

class Cl{
    Cl inner;
    Int field;
    Int f() {
        inner.field = inner.f();
        this.field = inner.field;
        return field;
    }
}