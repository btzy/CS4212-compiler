.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#36
mov a1,#4
mov a2,#1
bl calloc(PLT)
str a1,[sp,#32]
ldr a1,[sp,#32]
bl $List$init$$$List
mov a2,#3
ldr a1,[sp,#32]
bl $List$insertFirst$$$List$Int
str a1,[sp,#28]
mov a2,#4
ldr a1,[sp,#28]
bl $Node$insert$$$Node$Int
str a1,[sp,#28]
mov a2,#6
ldr a1,[sp,#28]
bl $Node$insert$$$Node$Int
str a1,[sp,#28]
ldr a1,[sp,#32]
bl $List$print$$$List
ldr a1,[sp,#32]
bl $List$size$$$List
str a1,[sp,#24]
ldr fp,[sp,#24]
mov lr,#10
add lr,fp,lr
str lr,[sp,#20]
ldr a2,[sp,#20]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
ldr a1,[sp,#32]
bl $List$item$$$List$Int
str a1,[sp,#28]
ldr lr,[sp,#28]
ldr lr,[lr,#4]
str lr,[sp,#16]
ldr a2,[sp,#16]
ldr a1,=.LZ0
bl printf(PLT)
ldr lr,[sp,#28]
ldr lr,[lr,#0]
str lr,[sp,#12]
ldr lr,[sp,#12]
ldr lr,[lr,#4]
str lr,[sp,#8]
ldr a2,[sp,#8]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
ldr a1,[sp,#32]
bl $List$item$$$List$Int
str a1,[sp,#28]
ldr lr,[sp,#28]
ldr lr,[lr,#4]
str lr,[sp,#4]
ldr a2,[sp,#4]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#40
ldmfd sp!,{fp,pc}
$List$init$$$List:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#4
mov a1,#12
mov a2,#1
bl calloc(PLT)
ldr fp,[sp,#4]
str a1,[fp,#0]
ldr lr,[sp,#4]
ldr lr,[lr,#0]
str lr,[sp,#0]
mov fp,#1
ldr lr,[sp,#0]
strb fp,[lr,#8]
add sp,sp,#8
ldmfd sp!,{fp,pc}
$List$size$$$List:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#12
mov lr,#0
str lr,[sp,#8]
ldr lr,[sp,#12]
ldr lr,[lr,#0]
str lr,[sp,#4]
.L0:
ldr lr,[sp,#4]
ldrb lr,[lr,#8]
strb lr,[sp,#0]
ldrb fp,[sp,#0]
eor fp,fp,#1
cmp fp,#0
bne .L1
b .L2
.L1:
ldr fp,[sp,#8]
mov lr,#1
add lr,fp,lr
str lr,[sp,#8]
ldr lr,[sp,#4]
ldr lr,[lr,#0]
str lr,[sp,#4]
b .L0
.L2:
ldr a1,[sp,#8]
add sp,sp,#16
ldmfd sp!,{fp,pc}
$List$item$$$List$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#8
ldr lr,[sp,#8]
ldr lr,[lr,#0]
str lr,[sp,#4]
.L3:
ldr fp,[sp,#12]
mov lr,#0
cmp fp,lr
movgt fp,#1
movle fp,#0
cmp fp,#0
bne .L4
b .L5
.L4:
ldr lr,[sp,#4]
ldr lr,[lr,#0]
str lr,[sp,#4]
ldr fp,[sp,#12]
mov lr,#1
sub lr,fp,lr
str lr,[sp,#12]
b .L3
.L5:
ldr a1,[sp,#4]
add sp,sp,#16
ldmfd sp!,{fp,pc}
$List$insertFirst$$$List$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#8
mov a1,#12
mov a2,#1
bl calloc(PLT)
str a1,[sp,#4]
ldr fp,[sp,#8]
ldr fp,[fp,#0]
ldr lr,[sp,#4]
str fp,[lr,#0]
ldr fp,[sp,#12]
ldr lr,[sp,#4]
str fp,[lr,#4]
mov fp,#0
ldr lr,[sp,#4]
strb fp,[lr,#8]
ldr fp,[sp,#4]
ldr lr,[sp,#8]
str fp,[lr,#0]
ldr a1,[sp,#4]
add sp,sp,#16
ldmfd sp!,{fp,pc}
$List$print$$$List:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#12
ldr lr,[sp,#12]
ldr lr,[lr,#0]
str lr,[sp,#8]
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ1
bl printf(PLT)
.L6:
ldr lr,[sp,#8]
ldrb lr,[lr,#8]
strb lr,[sp,#4]
ldrb fp,[sp,#4]
eor fp,fp,#1
cmp fp,#0
bne .L7
b .L8
.L7:
ldr lr,[sp,#8]
ldr lr,[lr,#4]
str lr,[sp,#0]
ldr a2,[sp,#0]
ldr a1,=.LZ0
bl printf(PLT)
ldr lr,[sp,#8]
ldr lr,[lr,#0]
str lr,[sp,#8]
b .L6
.L8:
add sp,sp,#16
ldmfd sp!,{fp,pc}
$Node$insert$$$Node$Int:
stmfd sp!,{a1,a2,fp,lr}
sub sp,sp,#8
mov a1,#12
mov a2,#1
bl calloc(PLT)
str a1,[sp,#4]
ldr fp,[sp,#8]
ldr fp,[fp,#0]
ldr lr,[sp,#4]
str fp,[lr,#0]
ldr fp,[sp,#12]
ldr lr,[sp,#4]
str fp,[lr,#4]
mov fp,#0
ldr lr,[sp,#4]
strb fp,[lr,#8]
ldr fp,[sp,#4]
ldr lr,[sp,#8]
str fp,[lr,#0]
ldr a1,[sp,#4]
add sp,sp,#16
ldmfd sp!,{fp,pc}
.data
.align 2
.LC0:
.ascii "\024\000\000\000Linked list content:"
.LZ0:
.asciz "%d\012"
.LZ1:
.asciz "%.*s\012"
