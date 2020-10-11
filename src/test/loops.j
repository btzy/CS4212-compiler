class MainC {

    Void main (){
        Loop1 l;
        Int arg;
        l = new Loop1();
        arg = 25;
        l.normal_loop(arg*3);
        l.call_loop(arg*2);
        l.call_loop_2(arg);
    }
}

class Loop1{
    Void normal_loop(Int x) {
        while (x > 0) { x = x-1;}
    }
    Int call_loop(Int x) {
        if (x>0) {return call_loop_2(x-1)*2;}
        else {return 1;}
    }
    Int call_loop_2(Int x) {
        if (x>0) {return call_loop(x-1)*3;}
        else {return 1;}
    }
}
