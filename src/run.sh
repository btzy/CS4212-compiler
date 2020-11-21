#!/bin/bash
if [[ -z "${GEM5_DIR}" ]]; then
    GEM5_DIR="$HOME/Documents/gem5"
else
    GEM5_DIR="${GEM5_DIR}"
fi
java -cp cup/java-cup-11b-runtime.jar:. Compiler -i test/$1.j > runner_file.s
arm-linux-gnueabi-gcc -march=armv7-a runner_file.s --static -o runner_file
if [ -f test/$1.in ]; then
    $GEM5_DIR/build/ARM/gem5.opt -r --stdout-file /dev/null $GEM5_DIR/configs/example/se.py -c ./runner_file --input $PWD/test/$1.in --output $PWD/runner_output.txt
else
    $GEM5_DIR/build/ARM/gem5.opt -r --stdout-file /dev/null $GEM5_DIR/configs/example/se.py -c ./runner_file --output $PWD/runner_output.txt
fi
cat $PWD/runner_output.txt
rm runner_file.s
rm runner_file
rm $PWD/runner_output.txt
