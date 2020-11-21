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
moveq a1,#0
beq .L0
ldr a1,[sp,#-4]
bl atoi(PLT)
.L0:
add a2,a1,#123
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
str a1,[sp,#-4]!
mov a1,sp
mov a2,#0
str a2,[sp,#-4]!
mov a2,sp
ldr a3,.LN0
ldr a3,[a3,#0]
bl getline(PLT)
cmn a1,#1
ldreq a1,=.LC0
beq .L1
mov fp,a1
add a1,a1,#4
bl malloc(PLT)
subs a3,fp,#1
ldr a4,[sp,#4]
movlt a3,#0
blt .L2
ldrb a2,[a4,a3]
cmp a2,#10
addne a3,a3,#1
.L2:
str a3,[a1,#0]
add a2,a1,#4
cmp a3,#0
beq .L4
.L3:
ldrb fp,[a4],#1
strb fp,[a2],#1
subs a3,a3,#1
bne .L3
.L4:
.L1:
add sp,sp,#8
mov v6,a1
ldr a2,=.LC1
ldr a1,[v6,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
add a2,v6,#4
ldr a3,[v6,#0]
cmp a3,#0
beq .L6
.L5:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L5
.L6:
ldr fp,=.LC1
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
mov a3,lr
ldr a2,[a3],#4
ldr a1,=.LZ1
bl printf(PLT)
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
beq .L9
ldr a1,[sp,#-4]
ldrb a1,[a1,#0]
cmp a1,#116
moveq a1,#1
beq .L9
cmp a1,#49
moveq a1,#1
movne a1,#0
.L9:
eor a1,a1,#1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
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
beq .L10
ldr a1,[sp,#-4]
ldrb a1,[a1,#0]
cmp a1,#116
moveq a1,#1
beq .L10
cmp a1,#49
moveq a1,#1
movne a1,#0
.L10:
eor a1,a1,#1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
mov a1,#0
add sp,sp,#4
ldmfd sp!,{pc}
add sp,sp,#4
ldmfd sp!,{pc}
.LN0:
.word stdin
.data
.align 2
.LC0:
.ascii "\000\000\000\000"
.align 2
.LC1:
.ascii "\001\000\000\000a"
.LZ0:
.asciz "%d\012\000"
.LZ1:
.asciz "%.*s\012\000"
.LZ2:
.asciz "true"
.LZ3:
.asciz "false"
