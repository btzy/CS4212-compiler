#!/bin/bash
java -cp cup/java-cup-11b-runtime.jar:. Compiler -i test/$1.j > test/$1.s
arm-linux-gnueabi-gcc -march=armv7-a test/$1.s --static -o runner_file
$HOME/Documents/gem5/build/ARM/gem5.opt $HOME/Documents/gem5/configs/example/se.py -c ./runner_file --output $PWD/test/$1.out
rm runner_file
