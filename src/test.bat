@echo off
for %%f in (test/*.j) do (
    echo|set /p="Testing %%~nf ... "
    java -cp cup/java-cup-11b-runtime.jar;. Compiler -i "test/%%~nf.j" > test_tmp_out
    fc test_tmp_out "test/%%~nf.parse" > nul 2> nul
    if errorlevel 1 (echo Failed) else (echo OK)
)
del test_tmp_out