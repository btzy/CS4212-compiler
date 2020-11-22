.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#204
mov lr,#100
str lr,[sp,#200]
mov lr,#10
str lr,[sp,#196]
ldr fp,[sp,#200]
mov lr,#99
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movgt fp,#1
movle fp,#0
cmp fp,#0
bne .L2
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
movgt fp,#1
movle fp,#0
cmp fp,#0
bne .L4
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
ldr fp,[sp,#200]
mov lr,#99
cmp fp,lr
movlt fp,#1
movge fp,#0
cmp fp,#0
bne .L6
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movlt fp,#1
movge fp,#0
cmp fp,#0
bne .L8
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
movlt fp,#1
movge fp,#0
cmp fp,#0
bne .L10
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
ldr fp,[sp,#200]
mov lr,#99
cmp fp,lr
movge fp,#1
movlt fp,#0
cmp fp,#0
bne .L12
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movge fp,#1
movlt fp,#0
cmp fp,#0
bne .L14
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
movge fp,#1
movlt fp,#0
cmp fp,#0
bne .L16
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
ldr fp,[sp,#200]
mov lr,#99
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L18
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L20
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L22
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
ldr fp,[sp,#200]
ldr lr,[sp,#196]
cmp fp,lr
movgt fp,#1
movle fp,#0
cmp fp,#0
bne .L24
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
ldr fp,[sp,#200]
ldr lr,[sp,#196]
cmp fp,lr
movlt fp,#1
movge fp,#0
cmp fp,#0
bne .L26
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
ldr fp,[sp,#200]
ldr lr,[sp,#196]
cmp fp,lr
movge fp,#1
movlt fp,#0
cmp fp,#0
bne .L28
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
ldr fp,[sp,#200]
ldr lr,[sp,#196]
cmp fp,lr
movle fp,#1
movgt fp,#0
cmp fp,#0
bne .L30
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
ldr fp,[sp,#200]
mov lr,#99
cmp fp,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L32
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L34
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
moveq fp,#1
movne fp,#0
cmp fp,#0
bne .L36
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
ldr fp,[sp,#200]
mov lr,#99
cmp fp,lr
movne fp,#1
moveq fp,#0
cmp fp,#0
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
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne fp,#1
moveq fp,#0
cmp fp,#0
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
ldr fp,[sp,#200]
mov lr,#101
cmp fp,lr
movne fp,#1
moveq fp,#0
cmp fp,#0
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
ldr fp,[sp,#200]
ldr lr,[sp,#196]
add lr,fp,lr
str lr,[sp,#192]
ldr a2,[sp,#192]
ldr a1,=.LZ1
bl printf(PLT)
mov a2,#321
ldr a1,=.LZ1
bl printf(PLT)
ldr fp,[sp,#200]
mov lr,#10
add lr,fp,lr
str lr,[sp,#188]
ldr a2,[sp,#188]
ldr a1,=.LZ1
bl printf(PLT)
mov fp,#12
ldr lr,[sp,#200]
add lr,fp,lr
str lr,[sp,#184]
ldr a2,[sp,#184]
ldr a1,=.LZ1
bl printf(PLT)
mov fp,#99
ldr lr,[sp,#200]
cmp fp,lr
movlt fp,#1
movge fp,#0
cmp fp,#0
bne .L44
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
ldr lr,[sp,#200]
cmp fp,lr
movlt lr,#1
movge lr,#0
strb lr,[sp,#180]
ldrb fp,[sp,#180]
eor fp,fp,#1
cmp fp,#0
bne .L46
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
str lr,[sp,#176]
ldr a3,[sp,#176]
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#1
mov lr,#2
add lr,fp,lr
str lr,[sp,#172]
ldr fp,[sp,#172]
mov lr,#3
add lr,fp,lr
str lr,[sp,#168]
ldr fp,[sp,#168]
mov lr,#4
add lr,fp,lr
str lr,[sp,#164]
ldr fp,[sp,#164]
mov lr,#5
add lr,fp,lr
str lr,[sp,#160]
ldr a2,[sp,#160]
ldr a1,=.LZ1
bl printf(PLT)
ldr fp,[sp,#200]
ldr lr,[sp,#196]
sub lr,fp,lr
str lr,[sp,#156]
ldr a2,[sp,#156]
ldr a1,=.LZ1
bl printf(PLT)
ldr fp,[sp,#196]
ldr lr,[sp,#200]
sub lr,fp,lr
str lr,[sp,#152]
ldr a2,[sp,#152]
ldr a1,=.LZ1
bl printf(PLT)
ldr fp,[sp,#196]
ldr lr,[sp,#200]
mul lr,fp,lr
str lr,[sp,#148]
ldr a2,[sp,#148]
ldr a1,=.LZ1
bl printf(PLT)
ldr a1,[sp,#200]
ldr a2,[sp,#196]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L54
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
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
str lr,[sp,#144]
ldr a2,[sp,#144]
ldr a1,=.LZ1
bl printf(PLT)
ldr a1,[sp,#196]
ldr a2,[sp,#200]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L57
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
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
str lr,[sp,#140]
ldr a2,[sp,#140]
ldr a1,=.LZ1
bl printf(PLT)
ldr lr,[sp,#196]
rsb lr,lr,#0
str lr,[sp,#136]
ldr a2,[sp,#136]
ldr a1,=.LZ1
bl printf(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#132]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#128]
ldrb fp,[sp,#132]
ldrb lr,[sp,#128]
and lr,fp,lr
strb lr,[sp,#124]
ldrb a1,[sp,#124]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#120]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#116]
ldrb fp,[sp,#120]
ldrb lr,[sp,#116]
and lr,fp,lr
strb lr,[sp,#112]
ldrb a1,[sp,#112]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#108]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#104]
ldrb fp,[sp,#108]
ldrb lr,[sp,#104]
and lr,fp,lr
strb lr,[sp,#100]
ldrb a1,[sp,#100]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#96]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#92]
ldrb fp,[sp,#96]
ldrb lr,[sp,#92]
and lr,fp,lr
strb lr,[sp,#88]
ldrb a1,[sp,#88]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#84]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#80]
ldrb fp,[sp,#84]
ldrb lr,[sp,#80]
orr lr,fp,lr
strb lr,[sp,#76]
ldrb a1,[sp,#76]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#72]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#68]
ldrb fp,[sp,#72]
ldrb lr,[sp,#68]
orr lr,fp,lr
strb lr,[sp,#64]
ldrb a1,[sp,#64]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#60]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#56]
ldrb fp,[sp,#60]
ldrb lr,[sp,#56]
orr lr,fp,lr
strb lr,[sp,#52]
ldrb a1,[sp,#52]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#48]
ldr fp,[sp,#196]
mov lr,#10
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#44]
ldrb fp,[sp,#48]
ldrb lr,[sp,#44]
orr lr,fp,lr
strb lr,[sp,#40]
ldrb a1,[sp,#40]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#36]
ldrb a1,[sp,#36]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
moveq lr,#1
movne lr,#0
strb lr,[sp,#32]
ldrb lr,[sp,#32]
eor lr,lr,#1
strb lr,[sp,#28]
ldrb a1,[sp,#28]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#24]
ldrb a1,[sp,#24]
cmp a1,#0
ldrne a1,=.LZ2
ldreq a1,=.LZ3
bl puts(PLT)
ldr fp,[sp,#200]
mov lr,#100
cmp fp,lr
movne lr,#1
moveq lr,#0
strb lr,[sp,#20]
ldrb lr,[sp,#20]
eor lr,lr,#1
strb lr,[sp,#16]
ldrb a1,[sp,#16]
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
str lr,[sp,#12]
ldr a1,[sp,#12]
ldr a2,=.LC13
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
str lr,[sp,#8]
ldr a1,[sp,#8]
ldr a2,=.LC14
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
str lr,[sp,#4]
ldr a3,[sp,#4]
ldr a2,[a3],#4
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#208
ldmfd sp!,{fp,pc}
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
.asciz "%.*s\012"
.LZ1:
.asciz "%d\012"
.LZ2:
.asciz "true"
.LZ3:
.asciz "false"
