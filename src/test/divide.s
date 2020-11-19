.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#100
mov a1,#1
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L2
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L0:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L0
mov lr,a4
.L1:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L1
eor lr,lr,a4
.L2:
str lr,[sp,#96]
ldr a2,[sp,#96]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#5
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L5
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L3:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L3
mov lr,a4
.L4:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L4
eor lr,lr,a4
.L5:
str lr,[sp,#92]
ldr a2,[sp,#92]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#7
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L8
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L6:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L6
mov lr,a4
.L7:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L7
eor lr,lr,a4
.L8:
str lr,[sp,#88]
ldr a2,[sp,#88]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#8
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L11
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L9:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L9
mov lr,a4
.L10:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L10
eor lr,lr,a4
.L11:
str lr,[sp,#84]
ldr a2,[sp,#84]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#9
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L14
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L12:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L12
mov lr,a4
.L13:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L13
eor lr,lr,a4
.L14:
str lr,[sp,#80]
ldr a2,[sp,#80]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#1
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L17
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L15:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L15
mov lr,a4
.L16:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L16
eor lr,lr,a4
.L17:
str lr,[sp,#76]
ldr a2,[sp,#76]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#5
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L20
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L18:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L18
mov lr,a4
.L19:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L19
eor lr,lr,a4
.L20:
str lr,[sp,#72]
ldr a2,[sp,#72]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#7
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L23
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L21:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L21
mov lr,a4
.L22:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L22
eor lr,lr,a4
.L23:
str lr,[sp,#68]
ldr a2,[sp,#68]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#8
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L26
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L24:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L24
mov lr,a4
.L25:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L25
eor lr,lr,a4
.L26:
str lr,[sp,#64]
ldr a2,[sp,#64]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#9
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L29
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L27:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L27
mov lr,a4
.L28:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L28
eor lr,lr,a4
.L29:
str lr,[sp,#60]
ldr a2,[sp,#60]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#5
rsb lr,lr,#0
str lr,[sp,#56]
ldr a1,[sp,#56]
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L32
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L30:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L30
mov lr,a4
.L31:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L31
eor lr,lr,a4
.L32:
str lr,[sp,#52]
ldr a2,[sp,#52]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#2
rsb lr,lr,#0
str lr,[sp,#48]
mov a1,#5
ldr a2,[sp,#48]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L35
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L33:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L33
mov lr,a4
.L34:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L34
eor lr,lr,a4
.L35:
str lr,[sp,#44]
ldr a2,[sp,#44]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#5
rsb lr,lr,#0
str lr,[sp,#40]
mov lr,#2
rsb lr,lr,#0
str lr,[sp,#36]
ldr a1,[sp,#40]
ldr a2,[sp,#36]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L38
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L36:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L36
mov lr,a4
.L37:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L37
eor lr,lr,a4
.L38:
str lr,[sp,#32]
ldr a2,[sp,#32]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#1
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L41
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L39:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L39
mov lr,a4
.L40:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L40
eor lr,lr,a4
.L41:
str lr,[sp,#28]
ldr a2,[sp,#28]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#10
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L44
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L42:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L42
mov lr,a4
.L43:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L43
eor lr,lr,a4
.L44:
str lr,[sp,#24]
ldr a2,[sp,#24]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#1
rsb lr,lr,#0
str lr,[sp,#20]
ldr a1,[sp,#20]
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L47
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L45:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L45
mov lr,a4
.L46:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L46
eor lr,lr,a4
.L47:
str lr,[sp,#16]
ldr a2,[sp,#16]
ldr a1,=.LZ0
bl printf(PLT)
mov lr,#10
rsb lr,lr,#0
str lr,[sp,#12]
ldr a1,[sp,#12]
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L50
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L48:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L48
mov lr,a4
.L49:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L49
eor lr,lr,a4
.L50:
str lr,[sp,#8]
ldr a2,[sp,#8]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L53
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L51:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L51
mov lr,a4
.L52:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L52
eor lr,lr,a4
.L53:
str lr,[sp,#4]
ldr a2,[sp,#4]
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#104
ldmfd sp!,{fp,pc}
add sp,sp,#104
ldmfd sp!,{fp,pc}
.data
.LZ0:
.asciz "%d\012\000"
