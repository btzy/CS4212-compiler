class Main {

    Void main(){
        println("abc");
        println("\123"); // dec literal for '{' (jLite spec differs from Java)
        println("\032"); // dec literal for <space> (jLite spec differs from Java)
        println("\x20"); // hex literal for <space>
        println("\x48"); // hex literal for 'H'
        println("abc\123\032\x20\x48");
        println("abc\123\032456\x20\x48def");
        println("abc\123\032456\x20\x48def" + " " + "abc\123\032456\x20\x48def");
        println("\\");
        println("\n");
        println("\t");
    }

}