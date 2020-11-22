.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#20
mov a1,#4
mov a2,#1
bl calloc(PLT)
str a1,[sp,#16]
ldr fp,=.LC0
ldr lr,[sp,#16]
str fp,[lr,#0]
mov a2,#5
ldr a1,[sp,#16]
bl $Test$f$$$Test$Int
str a1,[sp,#12]
ldr a3,[sp,#12]
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
ldr fp,=.LC1
ldr lr,[sp,#16]
str fp,[lr,#0]
mov a2,#5
ldr a1,[sp,#16]
bl $Test$f$$$Test$Int
str a1,[sp,#8]
ldr a3,[sp,#8]
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#4
mov a2,#1
bl calloc(PLT)
str a1,[sp,#16]
ldr fp,=.LC2
ldr lr,[sp,#16]
str fp,[lr,#0]
mov a2,#3
ldr a1,[sp,#16]
bl $Test$f$$$Test$Int
str a1,[sp,#4]
ldr a3,[sp,#4]
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#24
ldmfd sp!,{fp,pc}
$Test$f$$$Test$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#24
ldr fp,[sp,#28]
mov lr,#0
cmp fp,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L0
ldr fp,[sp,#28]
mov lr,#1
sub lr,fp,lr
str lr,[sp,#20]
ldr a2,[sp,#20]
ldr a1,[sp,#24]
bl $Test$f$$$Test$Int
str a1,[sp,#16]
ldr a1,[sp,#16]
ldr a2,=.LC3
ldr a1,[a1,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
ldr fp,[sp,#16]
add a2,fp,#4
ldr a3,[fp,#0]
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
str lr,[sp,#12]
ldr a2,[sp,#28]
ldr a1,[sp,#24]
bl $Test$g$$$Test$Int
str a1,[sp,#8]
ldr a1,[sp,#12]
ldr a2,[sp,#8]
ldr a1,[a1,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
ldr fp,[sp,#12]
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L6
.L5:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L5
.L6:
ldr fp,[sp,#8]
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L8
.L7:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L7
.L8:
str lr,[sp,#4]
ldr a1,[sp,#4]
add sp,sp,#32
ldmfd sp!,{fp,pc}
b .L9
.L0:
ldr a1,=.LC4
add sp,sp,#32
ldmfd sp!,{fp,pc}
.L9:
$Test$g$$$Test$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#16
ldr fp,[sp,#20]
mov lr,#0
cmp fp,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L10
ldr fp,[sp,#20]
mov lr,#1
sub lr,fp,lr
str lr,[sp,#12]
ldr a2,[sp,#12]
ldr a1,[sp,#16]
bl $Test$g$$$Test$Int
str a1,[sp,#8]
ldr lr,[sp,#16]
ldr lr,[lr,#0]
str lr,[sp,#4]
ldr a1,[sp,#8]
ldr a2,[sp,#4]
ldr a1,[a1,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
ldr fp,[sp,#8]
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L12
.L11:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L11
.L12:
ldr fp,[sp,#4]
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L14
.L13:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L13
.L14:
str lr,[sp,#0]
ldr a1,[sp,#0]
add sp,sp,#24
ldmfd sp!,{fp,pc}
b .L15
.L10:
ldr a1,=.LC4
add sp,sp,#24
ldmfd sp!,{fp,pc}
.L15:
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
.asciz "%.*s\012"
