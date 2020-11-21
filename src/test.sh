if [[ -z "${GEM5_DIR}" ]]; then
    GEM5_DIR="$HOME/Documents/gem5"
else
    GEM5_DIR="${GEM5_DIR}"
fi
for f in test/*.j; do
    name=$(echo "${f%.*}")
    printf "Testing $name ... "
    java -cp cup/java-cup-11b-runtime.jar:. Compiler -i "$name.j" > runner_file.s
    if cmp --silent runner_file.s "$name.s"; then
        printf "Assembly matches ... "
        arm-linux-gnueabi-gcc -march=armv7-a runner_file.s --static -o runner_file
        if [ -f test/$1.in ]; then
            $GEM5_DIR/build/ARM/gem5.opt -r --stdout-file /dev/null $GEM5_DIR/configs/example/se.py -c ./runner_file --input test/$1.in --output $PWD/runner_output.txt > /dev/null
        else
            $GEM5_DIR/build/ARM/gem5.opt -r --stdout-file /dev/null $GEM5_DIR/configs/example/se.py -c ./runner_file --output $PWD/runner_output.txt > /dev/null
        fi
        if cmp --silent $PWD/runner_output.txt "$name.out"; then
            printf "Program output matches\n"
        else
            printf "Program output differs from expected\n"
        fi
        rm $PWD/runner_output.txt
        rm runner_file
    else
    	printf "Assembly differs from expected\n"
    fi
    rm runner_file.s
done
