.text
.global main
main:
stmfd sp!,{a1,fp,lr}
sub sp,sp,#100
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L2
mov a1,#1
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L0:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L0
mov fp,a4
.L1:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L1
eor fp,fp,a4
.L2:
str fp,[sp,#92]
ldr a2,[sp,#92]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L5
mov a1,#5
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L3:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L3
mov fp,a4
.L4:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L4
eor fp,fp,a4
.L5:
str fp,[sp,#88]
ldr a2,[sp,#88]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L8
mov a1,#7
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L6:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L6
mov fp,a4
.L7:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L7
eor fp,fp,a4
.L8:
str fp,[sp,#84]
ldr a2,[sp,#84]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L11
mov a1,#8
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L9:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L9
mov fp,a4
.L10:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L10
eor fp,fp,a4
.L11:
str fp,[sp,#80]
ldr a2,[sp,#80]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L14
mov a1,#9
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L12:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L12
mov fp,a4
.L13:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L13
eor fp,fp,a4
.L14:
str fp,[sp,#76]
ldr a2,[sp,#76]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L17
mov a1,#1
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L15:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L15
mov fp,a4
.L16:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L16
eor fp,fp,a4
.L17:
str fp,[sp,#72]
ldr a2,[sp,#72]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L20
mov a1,#5
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L18:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L18
mov fp,a4
.L19:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L19
eor fp,fp,a4
.L20:
str fp,[sp,#68]
ldr a2,[sp,#68]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L23
mov a1,#7
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L21:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L21
mov fp,a4
.L22:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L22
eor fp,fp,a4
.L23:
str fp,[sp,#64]
ldr a2,[sp,#64]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L26
mov a1,#8
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L24:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L24
mov fp,a4
.L25:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L25
eor fp,fp,a4
.L26:
str fp,[sp,#60]
ldr a2,[sp,#60]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L29
mov a1,#9
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L27:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L27
mov fp,a4
.L28:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L28
eor fp,fp,a4
.L29:
str fp,[sp,#56]
ldr a2,[sp,#56]
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#5
rsb fp,fp,#0
str fp,[sp,#52]
mov a2,#2
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L32
ldr a1,[sp,#52]
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L30:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L30
mov fp,a4
.L31:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L31
eor fp,fp,a4
.L32:
str fp,[sp,#48]
ldr a2,[sp,#48]
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#2
rsb fp,fp,#0
str fp,[sp,#44]
ldr a2,[sp,#44]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L35
mov a1,#5
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L33:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L33
mov fp,a4
.L34:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L34
eor fp,fp,a4
.L35:
str fp,[sp,#40]
ldr a2,[sp,#40]
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#5
rsb fp,fp,#0
str fp,[sp,#36]
mov fp,#2
rsb fp,fp,#0
str fp,[sp,#32]
ldr a2,[sp,#32]
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L38
ldr a1,[sp,#36]
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L36:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L36
mov fp,a4
.L37:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L37
eor fp,fp,a4
.L38:
str fp,[sp,#28]
ldr a2,[sp,#28]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L41
mov a1,#1
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L39:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L39
mov fp,a4
.L40:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L40
eor fp,fp,a4
.L41:
str fp,[sp,#24]
ldr a2,[sp,#24]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L44
mov a1,#10
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L42:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L42
mov fp,a4
.L43:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L43
eor fp,fp,a4
.L44:
str fp,[sp,#20]
ldr a2,[sp,#20]
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#1
rsb fp,fp,#0
str fp,[sp,#16]
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L47
ldr a1,[sp,#16]
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L45:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L45
mov fp,a4
.L46:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L46
eor fp,fp,a4
.L47:
str fp,[sp,#12]
ldr a2,[sp,#12]
ldr a1,=.LZ0
bl printf(PLT)
mov fp,#10
rsb fp,fp,#0
str fp,[sp,#8]
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L50
ldr a1,[sp,#8]
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L48:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L48
mov fp,a4
.L49:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L49
eor fp,fp,a4
.L50:
str fp,[sp,#4]
ldr a2,[sp,#4]
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#0
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq fp,#0
beq .L53
mov a1,#0
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L51:
cmp a1,a2,LSL#1
movcs a2,a2,LSL#1
movcs a3,a3,LSL#1
bcs .L51
mov fp,a4
.L52:
cmp a1,a2
subcs a1,a1,a2
addcs fp,fp,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L52
eor fp,fp,a4
.L53:
str fp,[sp,#0]
ldr a2,[sp,#0]
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
