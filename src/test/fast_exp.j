// Test for method overloading

class A {

    Void main (){
        F f;
        f = new F();
        println(f.pow(2, 20));
        println(f.pow(3, 15));
        println(f.pow(4, 10));
    }
}

class F {
    Int pow(Int base, Int exp) {
        Int half;
        Int res;
        Int ans;
        if (exp==0) {
            return 1;
        } else {
            half = exp/2;
            res = pow(base,half);
            res = res * res;
            if (half*2<exp){ // exp was odd
                ans = res * base;
            }
            else {
                ans = res;
            }
            return ans;
        }
    }
}
