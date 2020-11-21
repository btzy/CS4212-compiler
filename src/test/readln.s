.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#28
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
streq a1,[sp,#24]
beq .L0
ldr a1,[sp,#-4]
bl atoi(PLT)
str a1,[sp,#24]
.L0:
ldr fp,[sp,#24]
mov lr,#123
add lr,fp,lr
str lr,[sp,#12]
ldr a2,[sp,#12]
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
str a1,[sp,#20]
ldr a3,[sp,#20]
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
beq .L5
ldr a1,[sp,#-4]
ldrb a1,[a1,#0]
cmp a1,#116
moveq a1,#1
beq .L5
cmp a1,#49
moveq a1,#1
movne a1,#0
.L5:
strb a1,[sp,#16]
ldrb lr,[sp,#16]
eor lr,lr,#1
strb lr,[sp,#8]
ldrb a1,[sp,#8]
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
beq .L6
ldr a1,[sp,#-4]
ldrb a1,[a1,#0]
cmp a1,#116
moveq a1,#1
beq .L6
cmp a1,#49
moveq a1,#1
movne a1,#0
.L6:
strb a1,[sp,#16]
ldrb lr,[sp,#16]
eor lr,lr,#1
strb lr,[sp,#4]
ldrb a1,[sp,#4]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
mov a1,#0
add sp,sp,#32
ldmfd sp!,{fp,pc}
add sp,sp,#32
ldmfd sp!,{fp,pc}
.LN0:
.word stdin
.data
.align 2
.LC0:
.ascii "\000\000\000\000"
.LZ0:
.asciz "%d\012\000"
.LZ1:
.asciz "%.*s\012\000"
.LZ2:
.asciz "true"
.LZ3:
.asciz "false"
