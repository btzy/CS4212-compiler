.text
.global main
main:
stmfd sp!,{v1,v2,v3,v4,v5,lr}
mov v4,#1
mov v3,#2
mov v5,#3
mov v2,#4
mov a1,#8
mov a2,#1
bl calloc(PLT)
mov v1,a1
mov a2,v4
mov a3,v3
mov a1,v1
bl $Compute$addSquares$$$Compute$Int$Int
mov v3,a1
mov a2,v5
mov a1,v1
bl $Compute$square$$$Compute$Int
add v3,v3,a1
mov a2,v2
mov a1,v1
bl $Compute$square$$$Compute$Int
cmp a1,v3
bgt .L0
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
ldmfd sp!,{v1,v2,v3,v4,v5,pc}
$Compute$square$$$Compute$Int:
stmfd sp!,{lr}
sub sp,sp,#4
mul a1,a2,a2
add sp,sp,#4
ldmfd sp!,{pc}
$Compute$add$$$Compute$Int$Int:
stmfd sp!,{lr}
sub sp,sp,#4
add a1,a2,a3
add sp,sp,#4
ldmfd sp!,{pc}
$Compute$addSquares$$$Compute$Int$Int:
stmfd sp!,{v1,v2,v3,lr}
mov v3,a1
mov v2,a3
ldrb a1,[v3,#0]
cmp a1,#0
bne .L2
mov fp,#1
strb fp,[v3,#0]
mov a1,v3
bl $Compute$square$$$Compute$Int
mov v1,a1
mov a2,v2
mov a1,v3
bl $Compute$square$$$Compute$Int
mov a3,a1
mov a1,v3
mov a2,v1
bl $Compute$add$$$Compute$Int$Int
ldmfd sp!,{v1,v2,v3,pc}
b .L3
.L2:
ldr a1,[v3,#4]
ldmfd sp!,{v1,v2,v3,pc}
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
