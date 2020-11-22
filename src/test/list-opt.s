.text
.global main
main:
stmfd sp!,{v1,v2,lr}
sub sp,sp,#4
mov a1,#4
mov a2,#1
bl calloc(PLT)
mov v2,a1
mov a1,v2
bl $List$init$$$List
mov a2,#3
mov a1,v2
bl $List$insertFirst$$$List$Int
mov v1,a1
mov a2,#4
mov a1,v1
bl $Node$insert$$$Node$Int
mov v1,a1
mov a2,#6
mov a1,v1
bl $Node$insert$$$Node$Int
mov v1,a1
mov a1,v2
bl $List$print$$$List
mov a1,v2
bl $List$size$$$List
add a2,a1,#10
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
mov a1,v2
bl $List$item$$$List$Int
mov v1,a1
ldr a2,[v1,#4]
ldr a1,=.LZ0
bl printf(PLT)
ldr a1,[v1,#0]
ldr a2,[a1,#4]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a1,v2
bl $List$item$$$List$Int
mov v1,a1
ldr a2,[v1,#4]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#4
ldmfd sp!,{v1,v2,pc}
$List$init$$$List:
stmfd sp!,{v1,lr}
mov v1,a1
mov a1,#12
mov a2,#1
bl calloc(PLT)
str a1,[v1,#0]
ldr a1,[v1,#0]
mov fp,#1
strb fp,[a1,#8]
ldmfd sp!,{v1,pc}
$List$size$$$List:
stmfd sp!,{lr}
sub sp,sp,#4
mov a2,#0
ldr a3,[a1,#0]
.L0:
ldrb a1,[a3,#8]
cmp a1,#0
beq .L1
b .L2
.L1:
add a2,a2,#1
ldr a3,[a3,#0]
b .L0
.L2:
mov a1,a2
add sp,sp,#4
ldmfd sp!,{pc}
$List$item$$$List$Int:
stmfd sp!,{lr}
sub sp,sp,#4
ldr a1,[a1,#0]
.L3:
cmp a2,#0
bgt .L4
b .L5
.L4:
ldr a1,[a1,#0]
sub a2,a2,#1
b .L3
.L5:
add sp,sp,#4
ldmfd sp!,{pc}
$List$insertFirst$$$List$Int:
stmfd sp!,{v1,v2,lr}
sub sp,sp,#4
mov v2,a1
mov v1,a2
mov a1,#12
mov a2,#1
bl calloc(PLT)
ldr fp,[v2,#0]
str fp,[a1,#0]
str v1,[a1,#4]
mov fp,#0
strb fp,[a1,#8]
str a1,[v2,#0]
add sp,sp,#4
ldmfd sp!,{v1,v2,pc}
$List$print$$$List:
stmfd sp!,{v1,lr}
ldr v1,[a1,#0]
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ1
bl printf(PLT)
.L6:
ldrb a1,[v1,#8]
cmp a1,#0
beq .L7
b .L8
.L7:
ldr a2,[v1,#4]
ldr a1,=.LZ0
bl printf(PLT)
ldr v1,[v1,#0]
b .L6
.L8:
ldmfd sp!,{v1,pc}
$Node$insert$$$Node$Int:
stmfd sp!,{v1,v2,lr}
sub sp,sp,#4
mov v2,a1
mov v1,a2
mov a1,#12
mov a2,#1
bl calloc(PLT)
ldr fp,[v2,#0]
str fp,[a1,#0]
str v1,[a1,#4]
mov fp,#0
strb fp,[a1,#8]
str a1,[v2,#0]
add sp,sp,#4
ldmfd sp!,{v1,v2,pc}
.data
.align 2
.LC0:
.ascii "\024\000\000\000Linked list content:"
.LZ0:
.asciz "%d\012"
.LZ1:
.asciz "%.*s\012"
