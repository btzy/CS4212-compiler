.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#12
mov lr,#10
str lr,[sp,#8]
ldr a2,[sp,#8]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#1
str lr,[sp,#4]
mov lr,#0
str lr,[sp,#0]
.L0:
ldr fp,[sp,#4]
ldr lr,[sp,#8]
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L1
b .L2
.L1:
ldr fp,[sp,#0]
ldr lr,[sp,#4]
add lr,fp,lr
str lr,[sp,#0]
ldr fp,[sp,#4]
mov lr,#1
add lr,fp,lr
str lr,[sp,#4]
b .L0
.L2:
ldr a2,[sp,#0]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#16
ldmfd sp!,{fp,pc}
add sp,sp,#16
ldmfd sp!,{fp,pc}
.data
.LZ0:
.asciz "%d\012\000"
