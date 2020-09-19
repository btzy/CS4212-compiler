class Main {

Void main(){
    println((new Def()).f1(10));
    println(new Abc().f1(10)); // note: this differs from Java, since JLite grammar differs here
    println (((((new Abc().f1(10))))));
}

}
class Abc {
Int f1(Int x) {
return f2(x);
}
Int f2(Int x) {
return f3(x);
}
Int f3(Int x) {return f4(x);}
Int f4(Int x) {return f5(x);}
Int f5(Int x) {return f6(x);}
Int f6(Int x) {return f7(x);}
Int f7(Int x) {return f1(x-1);}
}
class Def {
Int f1(Int x) {
return f2(x);
}
Int f2(Int x) {
return f3(x);
}
Int f3(Int x) {return f4(x);}
Int f4(Int x) {return f5(x);}
Int f5(Int x) {return f6(x);}
Int f6(Int x) {return f7(x);}
Int f7(Int x) {return f1(x-1);}
}