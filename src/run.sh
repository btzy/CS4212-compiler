#!/bin/bash
java -cp cup/java-cup-11b-runtime.jar:. Compiler -i test/$1.j > runner_file.s
arm-linux-gnueabi-gcc -march=armv7-a runner_file.s --static -o runner_file
$HOME/Documents/gem5/build/ARM/gem5.opt -r --stdout-file /dev/null $HOME/Documents/gem5/configs/example/se.py -c ./runner_file --output $PWD/runner_output.txt
cat $PWD/runner_output.txt
rm runner_file.s
rm runner_file
rm $PWD/runner_output.txt
