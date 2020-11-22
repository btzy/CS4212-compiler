.text
.global main
main:
stmfd sp!,{v1,v2,lr}
sub sp,sp,#4
mov v2,#100
mov v1,#10
cmp v2,#99
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
cmp v2,#100
bgt .L2
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L3
.L2:
ldr a3,=.LC1
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L3:
cmp v2,#101
bgt .L4
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L5
.L4:
ldr a3,=.LC1
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L5:
cmp v2,#99
blt .L6
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L7
.L6:
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L7:
cmp v2,#100
blt .L8
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L9
.L8:
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L9:
cmp v2,#101
blt .L10
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L11
.L10:
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L11:
cmp v2,#99
bge .L12
ldr a3,=.LC4
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L13
.L12:
ldr a3,=.LC5
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L13:
cmp v2,#100
bge .L14
ldr a3,=.LC4
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L15
.L14:
ldr a3,=.LC5
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L15:
cmp v2,#101
bge .L16
ldr a3,=.LC4
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L17
.L16:
ldr a3,=.LC5
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L17:
cmp v2,#99
ble .L18
ldr a3,=.LC6
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L19
.L18:
ldr a3,=.LC7
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L19:
cmp v2,#100
ble .L20
ldr a3,=.LC6
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L21
.L20:
ldr a3,=.LC7
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L21:
cmp v2,#101
ble .L22
ldr a3,=.LC6
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L23
.L22:
ldr a3,=.LC7
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L23:
cmp v2,v1
bgt .L24
ldr a3,=.LC0
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L25
.L24:
ldr a3,=.LC1
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L25:
cmp v2,v1
blt .L26
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L27
.L26:
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L27:
cmp v2,v1
bge .L28
ldr a3,=.LC4
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L29
.L28:
ldr a3,=.LC5
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L29:
cmp v2,v1
ble .L30
ldr a3,=.LC6
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L31
.L30:
ldr a3,=.LC7
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L31:
cmp v2,#99
beq .L32
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L33
.L32:
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L33:
cmp v2,#100
beq .L34
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L35
.L34:
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L35:
cmp v2,#101
beq .L36
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L37
.L36:
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L37:
cmp v2,#99
bne .L38
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L39
.L38:
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L39:
cmp v2,#100
bne .L40
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L41
.L40:
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L41:
cmp v2,#101
bne .L42
ldr a3,=.LC9
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L43
.L42:
ldr a3,=.LC8
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L43:
add a2,v2,v1
ldr a1,=.LZ1
bl printf(PLT)
mov a2,#321
ldr a1,=.LZ1
bl printf(PLT)
add a2,v2,#10
ldr a1,=.LZ1
bl printf(PLT)
mov fp,#12
add a2,fp,v2
ldr a1,=.LZ1
bl printf(PLT)
mov fp,#99
cmp fp,v2
blt .L44
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L45
.L44:
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L45:
mov fp,#99
cmp fp,v2
movlt a1,#1
movge a1,#0
cmp a1,#0
beq .L46
ldr a3,=.LC3
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
b .L47
.L46:
ldr a3,=.LC2
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
.L47:
ldr a1,=.LC10
ldr a2,=.LC11
ldr a1,[a1,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
ldr fp,=.LC10
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L49
.L48:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L48
.L49:
ldr fp,=.LC11
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L51
.L50:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L50
.L51:
mov a3,lr
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#1
add a1,fp,#2
add a1,a1,#3
add a1,a1,#4
add a2,a1,#5
ldr a1,=.LZ1
bl printf(PLT)
sub a2,v2,v1
ldr a1,=.LZ1
bl printf(PLT)
sub a2,v1,v2
ldr a1,=.LZ1
bl printf(PLT)
mul a2,v1,v2
ldr a1,=.LZ1
bl printf(PLT)
mov a4,v1,ASR#31
add a3,v1,v1,ASR#31
eors a2,a3,v1,ASR#31
moveq lr,#0
beq .L54
eor a4,a4,v2,ASR#31
add a3,v2,v2,ASR#31
eor a1,a3,v2,ASR#31
mov a3,#1
.L52:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L52
mov lr,a4
.L53:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L53
eor lr,lr,a4
.L54:
mov a2,lr
ldr a1,=.LZ1
bl printf(PLT)
mov a4,v2,ASR#31
add a3,v2,v2,ASR#31
eors a2,a3,v2,ASR#31
moveq lr,#0
beq .L57
eor a4,a4,v1,ASR#31
add a3,v1,v1,ASR#31
eor a1,a3,v1,ASR#31
mov a3,#1
.L55:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L55
mov lr,a4
.L56:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L56
eor lr,lr,a4
.L57:
mov a2,lr
ldr a1,=.LZ1
bl printf(PLT)
rsb a2,v1,#0
ldr a1,=.LZ1
bl printf(PLT)
cmp v2,#100
moveq a2,#1
movne a2,#0
cmp v1,#10
moveq a1,#1
movne a1,#0
and a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a2,#1
moveq a2,#0
cmp v1,#10
moveq a1,#1
movne a1,#0
and a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
moveq a2,#1
movne a2,#0
cmp v1,#10
movne a1,#1
moveq a1,#0
and a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a2,#1
moveq a2,#0
cmp v1,#10
movne a1,#1
moveq a1,#0
and a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
moveq a2,#1
movne a2,#0
cmp v1,#10
moveq a1,#1
movne a1,#0
orr a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a2,#1
moveq a2,#0
cmp v1,#10
moveq a1,#1
movne a1,#0
orr a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
moveq a2,#1
movne a2,#0
cmp v1,#10
movne a1,#1
moveq a1,#0
orr a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a2,#1
moveq a2,#0
cmp v1,#10
movne a1,#1
moveq a1,#0
orr a1,a2,a1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
moveq a1,#1
movne a1,#0
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
moveq a1,#1
movne a1,#0
eor a1,a1,#1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a1,#1
moveq a1,#0
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
cmp v2,#100
movne a1,#1
moveq a1,#0
eor a1,a1,#1
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr a1,=.LC12
ldr a2,=.LC13
ldr a1,[a1,#0]
ldr a2,[a2,#0]
add fp,a1,a2
add a1,fp,#4
bl malloc(PLT)
str fp,[a1,#0]
mov lr,a1
add a1,a1,#4
ldr fp,=.LC12
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L59
.L58:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L58
.L59:
ldr fp,=.LC13
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L61
.L60:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L60
.L61:
mov a1,lr
mov v6,a1
ldr a2,=.LC13
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
beq .L63
.L62:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L62
.L63:
ldr fp,=.LC13
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L65
.L64:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L64
.L65:
mov a1,lr
mov v6,a1
ldr a2,=.LC14
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
beq .L67
.L66:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L66
.L67:
ldr fp,=.LC14
add a2,fp,#4
ldr a3,[fp,#0]
cmp a3,#0
beq .L69
.L68:
ldrb a4,[a2],#1
strb a4,[a1],#1
subs a3,a3,#1
bne .L68
.L69:
mov a3,lr
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#4
ldmfd sp!,{v1,v2,pc}
.data
.align 2
.LC0:
.ascii "\012\000\000\000not bigger"
.align 2
.LC1:
.ascii "\006\000\000\000bigger"
.align 2
.LC2:
.ascii "\013\000\000\000not smaller"
.align 2
.LC3:
.ascii "\007\000\000\000smaller"
.align 2
.LC4:
.ascii "\023\000\000\000not bigger or equal"
.align 2
.LC5:
.ascii "\017\000\000\000bigger or equal"
.align 2
.LC6:
.ascii "\024\000\000\000not smaller or equal"
.align 2
.LC7:
.ascii "\020\000\000\000smaller or equal"
.align 2
.LC8:
.ascii "\011\000\000\000not equal"
.align 2
.LC9:
.ascii "\005\000\000\000equal"
.align 2
.LC10:
.ascii "\003\000\000\000abc"
.align 2
.LC11:
.ascii "\007\000\000\000defghij"
.align 2
.LC12:
.ascii "\004\000\000\000test"
.align 2
.LC13:
.ascii "\000\000\000\000"
.align 2
.LC14:
.ascii "\002\000\000\000ab"
.LZ0:
.asciz "%.*s\012\000"
.LZ1:
.asciz "%d\012\000"
.LZ2:
.asciz "true"
.LZ3:
.asciz "false"
