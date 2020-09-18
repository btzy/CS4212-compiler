class Main {

Void main(){
    println (fib(10));
}

}
class Fib {
Int fib(Int x) {
if (x == 1 || x == 2) { return 1; }
else {
return fib(x-2) + fib(x-1);
}
}
}