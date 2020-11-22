.text
.global main
main:
stmfd sp!,{lr}
sub sp,sp,#4
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L0
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L3
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L6
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L9
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L12
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L15
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L18
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L21
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L24
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L27
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
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#5
rsb a1,a1,#0
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L30
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
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2
rsb a1,a1,#0
mov a2,#5
mov a4,a1,ASR#31
add a3,a1,a1,ASR#31
eors a1,a3,a1,ASR#31
moveq lr,#0
beq .L35
eor a4,a4,a2,ASR#31
add a3,a2,a2,ASR#31
eor a2,a3,a2,ASR#31
mov a3,#1
.L33:
cmp a1,a2,LSR#1
movls a1,a1,LSL#1
movls a3,a3,LSL#1
bls .L33
mov lr,a4
.L34:
cmp a2,a1
subcs a2,a2,a1
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a1,a1,LSR#1
bne .L34
eor lr,lr,a4
.L35:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a2,#5
rsb a2,a2,#0
mov a1,#2
rsb a1,a1,#0
mov a4,a1,ASR#31
add a3,a1,a1,ASR#31
eors a1,a3,a1,ASR#31
moveq lr,#0
beq .L38
eor a4,a4,a2,ASR#31
add a3,a2,a2,ASR#31
eor a2,a3,a2,ASR#31
mov a3,#1
.L36:
cmp a1,a2,LSR#1
movls a1,a1,LSL#1
movls a3,a3,LSL#1
bls .L36
mov lr,a4
.L37:
cmp a2,a1
subcs a2,a2,a1
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a1,a1,LSR#1
bne .L37
eor lr,lr,a4
.L38:
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L39
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L42
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
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#1
rsb a1,a1,#0
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L45
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
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#10
rsb a1,a1,#0
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L48
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
mov a2,lr
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
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L51
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
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L56
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L54:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L54
mov lr,a4
.L55:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L55
eor lr,lr,a4
.L56:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
mov a2,#65535
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L59
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L57:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L57
mov lr,a4
.L58:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L58
eor lr,lr,a4
.L59:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
mov a2,#65536
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L62
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L60:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L60
mov lr,a4
.L61:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L61
eor lr,lr,a4
.L62:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
mov a2,#2147483647
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L65
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L63:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L63
mov lr,a4
.L64:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L64
eor lr,lr,a4
.L65:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
sub a1,a1,#1
mov a2,#2147483647
mov a4,a1,ASR#31
add a3,a1,a1,ASR#31
eors a1,a3,a1,ASR#31
moveq lr,#0
beq .L68
eor a4,a4,a2,ASR#31
add a3,a2,a2,ASR#31
eor a2,a3,a2,ASR#31
mov a3,#1
.L66:
cmp a1,a2,LSR#1
movls a1,a1,LSL#1
movls a3,a3,LSL#1
bls .L66
mov lr,a4
.L67:
cmp a2,a1
subcs a2,a2,a1
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a1,a1,LSR#1
bne .L67
eor lr,lr,a4
.L68:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L71
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L69:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L69
mov lr,a4
.L70:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L70
eor lr,lr,a4
.L71:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a2,#65535
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L74
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L72:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L72
mov lr,a4
.L73:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L73
eor lr,lr,a4
.L74:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a2,#65536
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L77
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L75:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L75
mov lr,a4
.L76:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L76
eor lr,lr,a4
.L77:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a2,#2147483647
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L80
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L78:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L78
mov lr,a4
.L79:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L79
eor lr,lr,a4
.L80:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a2,#2147483647
rsb a2,a2,#0
sub a1,a1,#1
mov a4,a1,ASR#31
add a3,a1,a1,ASR#31
eors a1,a3,a1,ASR#31
moveq lr,#0
beq .L83
eor a4,a4,a2,ASR#31
add a3,a2,a2,ASR#31
eor a2,a3,a2,ASR#31
mov a3,#1
.L81:
cmp a1,a2,LSR#1
movls a1,a1,LSL#1
movls a3,a3,LSL#1
bls .L81
mov lr,a4
.L82:
cmp a2,a1
subcs a2,a2,a1
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a1,a1,LSR#1
bne .L82
eor lr,lr,a4
.L83:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
sub a1,a1,#1
mov a2,#1
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L86
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L84:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L84
mov lr,a4
.L85:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L85
eor lr,lr,a4
.L86:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
sub a1,a1,#1
mov a2,#65535
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L89
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L87:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L87
mov lr,a4
.L88:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L88
eor lr,lr,a4
.L89:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
sub a1,a1,#1
mov a2,#65536
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L92
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L90:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L90
mov lr,a4
.L91:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L91
eor lr,lr,a4
.L92:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
sub a1,a1,#1
mov a2,#2147483647
mov a4,a2,ASR#31
add a3,a2,a2,ASR#31
eors a2,a3,a2,ASR#31
moveq lr,#0
beq .L95
eor a4,a4,a1,ASR#31
add a3,a1,a1,ASR#31
eor a1,a3,a1,ASR#31
mov a3,#1
.L93:
cmp a2,a1,LSR#1
movls a2,a2,LSL#1
movls a3,a3,LSL#1
bls .L93
mov lr,a4
.L94:
cmp a1,a2
subcs a1,a1,a2
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a2,a2,LSR#1
bne .L94
eor lr,lr,a4
.L95:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#2147483647
rsb a1,a1,#0
mov a3,#2147483647
rsb a3,a3,#0
sub a2,a1,#1
sub a1,a3,#1
mov a4,a1,ASR#31
add a3,a1,a1,ASR#31
eors a1,a3,a1,ASR#31
moveq lr,#0
beq .L98
eor a4,a4,a2,ASR#31
add a3,a2,a2,ASR#31
eor a2,a3,a2,ASR#31
mov a3,#1
.L96:
cmp a1,a2,LSR#1
movls a1,a1,LSL#1
movls a3,a3,LSL#1
bls .L96
mov lr,a4
.L97:
cmp a2,a1
subcs a2,a2,a1
addcs lr,lr,a3
movs a3,a3,LSR#1
movne a1,a1,LSR#1
bne .L97
eor lr,lr,a4
.L98:
mov a2,lr
ldr a1,=.LZ0
bl printf(PLT)
mov a1,#0
add sp,sp,#4
ldmfd sp!,{pc}
add sp,sp,#4
ldmfd sp!,{pc}
.data
.LZ0:
.asciz "%d\012\000"
