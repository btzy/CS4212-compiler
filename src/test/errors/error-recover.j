class Main {

Void main(){
    Int x; Bool y; String   z;Void v; A a;
    a = new A();
    readln(x);
    println (123+456);
    if (y) { z = "test"; } else {z="";x=1000;}
    while(y) {}
    while(y) {z="a";}
    while(y) {z="a";y = false;}
    z = z + z +  + "123";
    z = z + z + "123";
    z = z + z + "123"123;
    y=true;
    a.field1 = z;
    a.field2 = 123+456;
    y = (true || false) && !true || !false;
    y=1<1||1>1||1<=1||1>=1||1==1||1!=1;
    x = 1000;
    x =-1000   ;
    x = 1000.123;
    x=x*x+x/x-x;
    x=a.field2;
    x = new A().field2;
    a=null;
    f3(x,z  ,  y);
}

}
class A {
String field1; Int field2;
Void f1(Int x) { x = x;}
Void f2(Int x, String y) { this.field2 = x; return;;}
Int f3(Int x, String y, Bool z) { x = x; return x;}
Int corrupt(Int x String y) { x = x; return x;}
}