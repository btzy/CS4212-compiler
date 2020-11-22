.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#36
mov lr,#1
str lr,[sp,#32]
mov lr,#2
str lr,[sp,#28]
mov lr,#3
str lr,[sp,#24]
mov lr,#4
str lr,[sp,#20]
mov a1,#8
mov a2,#1
bl calloc(PLT)
str a1,[sp,#8]
ldr a3,[sp,#28]
ldr a2,[sp,#32]
ldr a1,[sp,#8]
bl $Compute$addSquares$$$Compute$Int$Int
str a1,[sp,#4]
ldr a2,[sp,#24]
ldr a1,[sp,#8]
bl $Compute$square$$$Compute$Int
str a1,[sp,#0]
ldr fp,[sp,#4]
ldr lr,[sp,#0]
add lr,fp,lr
str lr,[sp,#16]
ldr a2,[sp,#20]
ldr a1,[sp,#8]
bl $Compute$square$$$Compute$Int
str a1,[sp,#12]
ldr fp,[sp,#12]
ldr lr,[sp,#16]
cmp fp,lr
movgt fp,#1
movle fp,#0
cmp fp,#0
bne .L0
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L1
.L0:
ldr a3,=.LC1
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L1:
mov a1,#0
add sp,sp,#40
ldmfd sp!,{fp,pc}
$Compute$square$$$Compute$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#8
ldr fp,[sp,#12]
ldr lr,[sp,#12]
mul lr,fp,lr
str lr,[sp,#4]
ldr a1,[sp,#4]
add sp,sp,#16
ldmfd sp!,{fp,pc}
$Compute$add$$$Compute$Int$Int:
stmfd sp!,{a1,a2,a3,fp,lr}
sub sp,sp,#4
ldr fp,[sp,#8]
ldr lr,[sp,#12]
add lr,fp,lr
str lr,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#16
ldmfd sp!,{fp,pc}
$Compute$addSquares$$$Compute$Int$Int:
stmfd sp!,{a1,a2,a3,fp,lr}
sub sp,sp,#20
ldr lr,[sp,#20]
ldrb lr,[lr,#0]
strb lr,[sp,#16]
ldrb fp,[sp,#16]
cmp fp,#0
bne .L2
mov fp,#1
ldr lr,[sp,#20]
strb fp,[lr,#0]
ldr a2,[sp,#24]
ldr a1,[sp,#20]
bl $Compute$square$$$Compute$Int
str a1,[sp,#12]
ldr a2,[sp,#28]
ldr a1,[sp,#20]
bl $Compute$square$$$Compute$Int
str a1,[sp,#8]
ldr a3,[sp,#8]
ldr a2,[sp,#12]
ldr a1,[sp,#20]
bl $Compute$add$$$Compute$Int$Int
str a1,[sp,#4]
ldr a1,[sp,#4]
add sp,sp,#32
ldmfd sp!,{fp,pc}
b .L3
.L2:
ldr lr,[sp,#20]
ldr lr,[lr,#4]
str lr,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#32
ldmfd sp!,{fp,pc}
.L3:
.data
.align 2
.LC0:
.ascii "\047\000\000\000Square of d smaller than sum of squares"
.align 2
.LC1:
.ascii "\046\000\000\000Square of d larger than sum of squares"
.LZ0:
.asciz "%.*s\012"
