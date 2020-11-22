.text
.global main
main:
stmfd sp!,{lr}
sub sp,sp,#4
mov a1,#0
str a1,[sp,#-4]!
mov a1,sp
mov a2,#0
str a2,[sp,#-4]!
mov a2,sp
ldr a3,.LN0
ldr a3,[a3,#0]
bl getline(PLT)
add sp,sp,#8
cmn a1,#1
moveq a3,#0
beq .L0
ldr a1,[sp,#-4]
mov a2,#0
mov a3,#10
bl strtol(PLT)
mov a3,a1
.L0:
mov a1,#1
mov a2,#0
.L1:
cmp a1,a3
ble .L2
b .L3
.L2:
add a2,a2,a1
add a1,a1,#1
b .L1
.L3:
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#4
ldmfd sp!,{pc}
.LN0:
.word stdin
.data
.align 2
.LC0:
.ascii "\000\000\000\000"
.LZ0:
.asciz "%d\012"
