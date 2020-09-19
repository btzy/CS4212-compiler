for f in test/*.j; do
    name=$(echo "${f%.*}")
    printf "Testing $name ... "
    java -cp cup/java-cup-11b-runtime.jar:. Compiler -i "$name.j" > test_tmp_out
    cmp --silent test_tmp_out "$name.parse" && printf "OK\n" || printf "Failed\n"
done
rm test_tmp_out
