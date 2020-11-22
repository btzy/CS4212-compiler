.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#12
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
moveq a1,#0
streq a1,[sp,#8]
beq .L0
ldr a1,[sp,#-4]
mov a2,#0
mov a3,#10
bl strtol(PLT)
str a1,[sp,#8]
.L0:
mov lr,#1
str lr,[sp,#4]
mov lr,#0
str lr,[sp,#0]
.L1:
ldr fp,[sp,#4]
ldr lr,[sp,#8]
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L2
b .L3
.L2:
ldr fp,[sp,#0]
ldr lr,[sp,#4]
add lr,fp,lr
str lr,[sp,#0]
ldr fp,[sp,#4]
mov lr,#1
add lr,fp,lr
str lr,[sp,#4]
b .L1
.L3:
ldr a2,[sp,#0]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#16
ldmfd sp!,{fp,pc}
.LN0:
.word stdin
.data
.align 2
.LC0:
.ascii "\000\000\000\000"
.LZ0:
.asciz "%d\012"
