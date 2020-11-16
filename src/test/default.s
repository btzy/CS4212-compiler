.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#36
mov fp,#1
str fp,[sp,#32]
mov fp,#2
str fp,[sp,#28]
mov fp,#3
str fp,[sp,#24]
mov fp,#4
str fp,[sp,#20]
mov a1,#8
bl malloc(PLT)
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
add fp,fp,lr
str fp,[sp,#16]
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
add sp,sp,#40
ldmfd sp!,{fp,pc}
$Compute$square$$$Compute$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#8
ldr fp,[sp,#8]
ldr lr,[sp,#8]
mul fp,fp,lr
str fp,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#16
ldmfd sp!,{fp,pc}
add sp,sp,#16
ldmfd sp!,{fp,pc}
$Compute$add$$$Compute$Int$Int:
stmfd sp!,{a1,a2,a3,fp,lr}
sub sp,sp,#4
ldr fp,[sp,#8]
ldr lr,[sp,#12]
add fp,fp,lr
str fp,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#16
ldmfd sp!,{fp,pc}
add sp,sp,#16
ldmfd sp!,{fp,pc}
$Compute$addSquares$$$Compute$Int$Int:
stmfd sp!,{a1,a2,a3,fp,lr}
sub sp,sp,#20
ldr fp,[sp,#20]
ldrb fp,[fp,#0]
str fp,[sp,#16]
ldrb fp,[sp,#16]
cmp fp,#0
bne .L2
mov fp,#1
ldr lr,[sp,#20]
str fp,[lr,#0]
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
ldr fp,[sp,#20]
ldr fp,[fp,#4]
str fp,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#32
ldmfd sp!,{fp,pc}
.L3:
add sp,sp,#32
ldmfd sp!,{fp,pc}
.data
.LC0:
.ascii "\047\000\000\000Square of d smaller than sum of squares"
.LC1:
.ascii "\046\000\000\000Square of d larger than sum of squares"
.LZ0:
.asciz "%.*s\012\000"
