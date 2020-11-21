#!/bin/bash
if [[ -z "${GEM5_DIR}" ]]; then
    GEM5_DIR="$HOME/Documents/gem5"
else
    GEM5_DIR="${GEM5_DIR}"
fi
java -cp cup/java-cup-11b-runtime.jar:. Compiler -i test/$1.j > test/$1.s
arm-linux-gnueabi-gcc -march=armv7-a test/$1.s --static -o runner_file
if [ -f test/$1.in ]; then
    $GEM5_DIR/build/ARM/gem5.opt $GEM5_DIR/configs/example/se.py -c ./runner_file --input $PWD/test/$1.in --output $PWD/test/$1.out
else
    $GEM5_DIR/build/ARM/gem5.opt $GEM5_DIR/configs/example/se.py -c ./runner_file --output $PWD/test/$1.out
fi
rm runner_file
java -cp cup/java-cup-11b-runtime.jar:. Compiler -i test/$1.j -O > test/$1-opt.s
