.text
.global main
main:
stmfd sp!,{v1,lr}
mov a1,#4
mov a2,#1
bl calloc(PLT)
mov v1,a1
ldr fp,=.LC0
str fp,[v1,#0]
mov a2,#5
mov a1,v1
bl $Test$f$$$Test$Int
add a3,a1,#4
ldr a2,[a1,#0]
ldr a1,=.LZ0
bl printf(PLT)
ldr fp,=.LC1
str fp,[v1,#0]
mov a2,#5
mov a1,v1
bl $Test$f$$$Test$Int
add a3,a1,#4
ldr a2,[a1,#0]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#4
mov a2,#1
bl calloc(PLT)
mov v1,a1
ldr fp,=.LC2
str fp,[v1,#0]
mov a2,#3
mov a1,v1
bl $Test$f$$$Test$Int
add a3,a1,#4
ldr a2,[a1,#0]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
ldmfd sp!,{v1,pc}
ldmfd sp!,{v1,pc}
$Test$f$$$Test$Int:
stmfd sp!,{v1,v2,v3,lr}
mov v2,a1
mov v1,a2
mov lr,#0
cmp v1,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L0
mov lr,#1
sub a2,v1,lr
mov a1,v2
bl $Test$f$$$Test$Int
mov v6,a1
ldr a2,=.LC3
ldr a1,[v6,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov v3,a1
add a1,a1,#4
add a2,v6,#4
ldr a3,[v6,#0]
cmp a3,#0
beq .L2
.L1:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L1
.L2:
ldr fp,=.LC3
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L4
.L3:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L3
.L4:
mov a2,v1
mov a1,v2
bl $Test$g$$$Test$Int
mov v6,a1
ldr a1,[v3,#0]
ldr a2,[v6,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
add a2,v3,#4
ldr a3,[v3,#0]
cmp a3,#0
beq .L6
.L5:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L5
.L6:
add a2,v6,#4
ldr a3,[v6,#0]
cmp a3,#0
beq .L8
.L7:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L7
.L8:
mov a1,lr
ldmfd sp!,{v1,v2,v3,pc}
b .L9
.L0:
ldr a1,=.LC4
ldmfd sp!,{v1,v2,v3,pc}
.L9:
ldmfd sp!,{v1,v2,v3,pc}
$Test$g$$$Test$Int:
stmfd sp!,{v1,lr}
mov v1,a1
mov lr,#0
cmp a2,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L10
mov lr,#1
sub a2,a2,lr
mov a1,v1
bl $Test$g$$$Test$Int
ldr a2,[v1,#0]
mov v6,a1
mov v7,a2
ldr a1,[v6,#0]
ldr a2,[v7,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
add a2,v6,#4
ldr a3,[v6,#0]
cmp a3,#0
beq .L12
.L11:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L11
.L12:
add a2,v7,#4
ldr a3,[v7,#0]
cmp a3,#0
beq .L14
.L13:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L13
.L14:
mov a1,lr
ldmfd sp!,{v1,pc}
b .L15
.L10:
ldr a1,=.LC4
ldmfd sp!,{v1,pc}
.L15:
ldmfd sp!,{v1,pc}
.data
.align 2
.LC0:
.ascii "\001\000\000\000a"
.align 2
.LC1:
.ascii "\002\000\000\000bb"
.align 2
.LC2:
.ascii "\001\000\000\000c"
.align 2
.LC3:
.ascii "\001\000\000\000 "
.align 2
.LC4:
.ascii "\000\000\000\000"
.LZ0:
.asciz "%.*s\012\000"
