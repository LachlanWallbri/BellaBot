int64_t sub_9f0()
{
    /* jump -> nullptr */
}

int64_t _JString2Char()
{
    /* tailcall */
    return _JString2Char();
}

int64_t __cxa_finalize(int64_t arg1)
{
    /* tailcall */
    return __cxa_finalize(arg1);
}

int64_t cfsetispeed(struct termios* arg1, speed_t arg2)
{
    /* tailcall */
    return cfsetispeed(arg1, arg2);
}

int64_t cfmakeraw(struct termios* arg1)
{
    /* tailcall */
    return cfmakeraw(arg1);
}

int64_t __memcpy_chk(int64_t arg1, int64_t arg2, size_t arg3, size_t arg4)
{
    /* tailcall */
    return __memcpy_chk(arg1, arg2, arg3, arg4);
}

void __stack_chk_fail() __noreturn
{
    /* tailcall */
    return __stack_chk_fail();
}

int64_t __android_log_print()
{
    /* tailcall */
    return __android_log_print();
}

int64_t __open_2(char* arg1, int32_t arg2)
{
    /* tailcall */
    return __open_2(arg1, arg2);
}

int64_t tcflush(int32_t arg1, int32_t arg2)
{
    /* tailcall */
    return tcflush(arg1, arg2);
}

int64_t cfsetospeed(struct termios* arg1, speed_t arg2)
{
    /* tailcall */
    return cfsetospeed(arg1, arg2);
}

int64_t tcgetattr(int32_t arg1, struct termios* arg2)
{
    /* tailcall */
    return tcgetattr(arg1, arg2);
}

int64_t close(int32_t arg1)
{
    /* tailcall */
    return close(arg1);
}

int64_t malloc(size_t arg1)
{
    /* tailcall */
    return malloc(arg1);
}

int64_t memcpy(int64_t arg1, int64_t arg2, size_t arg3)
{
    /* tailcall */
    return memcpy(arg1, arg2, arg3);
}

int64_t tcsetattr(int32_t arg1, int32_t arg2, struct termios* arg3)
{
    /* tailcall */
    return tcsetattr(arg1, arg2, arg3);
}

int64_t _start()
{
    /* tailcall */
    return __cxa_finalize(&data_2d68);
}

int64_t sub_b1c() __pure
{
    return;
}

int64_t j_sub_b1c()
{
    /* tailcall */
    return sub_b1c();
}

int64_t sub_b28(int64_t arg1)
{
    /* jump -> arg1 */
}

void* _JString2Char(int64_t* arg1, int64_t arg2)
{
    void* var_58 = nullptr;
    int64_t x0_1 = *(*arg1 + 0x30)(arg1, "java/lang/String", "getBytes", "(Ljava/lang/String;)[B");
    int64_t x0_3 = *(*arg1 + 0x538)(arg1, "GB2312");
    int64_t x0_7 = *(*arg1 + 0x110)(arg1, arg2, *(*arg1 + 0x108)(arg1, x0_1, "getBytes", "(Ljava/lang/String;)[B"), x0_3);
    int32_t x0_9 = *(*arg1 + 0x558)(arg1, x0_7);
    int64_t x0_11 = *(*arg1 + 0x5c0)(arg1, x0_7, 0);
    int32_t x12_1;
    if (x0_9 <= 0)
    {
        x12_1 = 1;
    }
    else
    {
        x12_1 = 0;
    }
    if ((x12_1 & 1) == 0)
    {
        var_58 = malloc((x0_9 + 1));
        int64_t x12_2 = x0_9;
        if ((1 & 1) != 0)
        {
            memcpy(var_58, x0_11, x12_2);
            void* var_18_2 = var_58;
        }
        else
        {
            int64_t var_18_1 = __memcpy_chk(var_58, x0_11, x12_2, -1);
        }
        *(var_58 + x0_9) = 0;
    }
    *(*arg1 + 0x600)(arg1, x0_7, x0_11, 0);  {  // {"__cxa_atexit"}}
    return var_58;
}

int64_t Java_com_pudutech_serialport_library_SerialPort_open(int64_t* arg1, int64_t arg2, int64_t arg3, int32_t arg4, int32_t arg5)
{
    void tpidr_el0;
    int64_t x8_1 = *(_ReadStatusReg(tpidr_el0) + 0x28);
    int64_t var_88 = arg2;
    speed_t x0_1 = sub_10c8(arg4);
    int64_t var_78;
    if (x0_1 == 0xffffffff)
    {
        __android_log_print(6, data_3000, "Invalid baudrate");
        var_78 = 0;
    }
    else
    {
        int32_t var_e4_1 = 3;
        int32_t var_e8_1 = __android_log_print(3, data_3000, "path = %s, baudrate = %d, flags …", _JString2Char(arg1, arg3), arg4, arg5);
        void var_54;
        char* x0_6 = *(*arg1 + 0x548)(arg1, arg3, &var_54);
        int64_t var_68_1 = -1;
        int32_t var_ec_1 = __android_log_print(3, data_3000, "Opening serial port %s with flag…", x0_6, (arg5 | 2));
        int32_t x0_9 = __open_2(x0_6, (arg5 | 2));
        int32_t var_f0_1 = __android_log_print(3, data_3000, "open() fd = %d", x0_9);
        *(*arg1 + 0x550)(arg1, arg3, x0_6);
        if (x0_9 == 0xffffffff)
        {
            __android_log_print(6, data_3000, "Cannot open port");
            var_78 = 0;
        }
        else
        {
            int32_t var_f4_1 = __android_log_print(3, data_3000, "Configuring serial port");
            void var_50;
            if (tcgetattr(x0_9, &var_50) != 0)
            {
                int32_t var_f8_1 = __android_log_print(6, data_3000, "tcgetattr() failed");
                close(x0_9);
                var_78 = 0;
            }
            else
            {
                void* var_100_1 = &var_50;
                cfmakeraw(&var_50);
                int32_t var_104_1 = cfsetispeed(var_100_1, x0_1);
                int32_t var_108_1 = cfsetospeed(var_100_1, x0_1);
                if (tcsetattr(x0_9, 0, var_100_1) == 0)
                {
                    int64_t x0_27 = *(*arg1 + 0x30)(arg1, "java/io/FileDescriptor");
                    int64_t x0_29 = *(*arg1 + 0x108)(arg1, x0_27, "<init>", "()V");
                    int64_t x0_31 = *(*arg1 + 0x2f0)(arg1, x0_27, "descriptor", "I");
                    int64_t x0_33 = *(*arg1 + 0xe0)(arg1, x0_27, x0_29);
                    *(*arg1 + 0x368)(arg1, x0_33, x0_31, x0_9);
                    var_78 = x0_33;
                }
                else
                {
                    int32_t var_10c_1 = __android_log_print(6, data_3000, "tcsetattr() failed");
                    close(x0_9);
                    var_78 = 0;
                }
            }
        }
    }
    if (*(_ReadStatusReg(tpidr_el0) + 0x28) != x8_1)
    {
        __stack_chk_fail();
        /* no return */
    }
    return var_78;
}

uint64_t sub_10c8(int32_t arg1)
{
    void tpidr_el0;
    int64_t x8_1 = *(_ReadStatusReg(tpidr_el0) + 0x28);
    int32_t var_1c;
    switch (arg1)
    {
        case 0:
        {
            var_1c = 0;
            break;
        }
        case 0x32:
        {
            var_1c = 1;
            break;
        }
        case 0x4b:
        {
            var_1c = 2;
            break;
        }
        case 0x6e:
        {
            var_1c = 3;
            break;
        }
        case 0x86:
        {
            var_1c = 4;
            break;
        }
        case 0x96:
        {
            var_1c = 5;
            break;
        }
        case 0xc8:
        {
            var_1c = 6;
            break;
        }
        case 0x12c:
        {
            var_1c = 7;
            break;
        }
        case 0x258:
        {
            var_1c = 8;
            break;
        }
        case 0x4b0:
        {
            var_1c = 9;
            break;
        }
        case 0x708:  {  // {"ispeed"}}
        {
            var_1c = 0xa;
            break;
        }
        case 0x960:
        {
            var_1c = 0xb;
            break;
        }
        case 0x12c0:
        {
            var_1c = 0xc;
            break;
        }
        case 0x2580:
        {
            var_1c = 0xd;
            break;
        }
        case 0x4b00:
        {
            var_1c = 0xe;
            break;
        }
        case 0x9600:
        {
            var_1c = 0xf;
            break;
        }
        case 0xe100:
        {
            var_1c = 0x1001;
            break;
        }
        case 0x1c200:
        {
            var_1c = 0x1002;
            break;
        }
        case 0x38400:
        {
            var_1c = 0x1003;
            break;
        }
        case 0x70800:
        {
            var_1c = 0x1004;
            break;
        }
        case 0x7a120:
        {
            var_1c = 0x1005;
            break;
        }
        case 0x8ca00:
        {
            var_1c = 0x1006;
            break;
        }
        case 0xe1000:
        {
            var_1c = 0x1007;
            break;
        }
        case 0xf4240:
        {
            var_1c = 0x1008;
            break;
        }
        case 0x119400:
        {
            var_1c = 0x1009;
            break;
        }
        case 0x16e360:
        {
            var_1c = 0x100a;
            break;
        }
        case 0x1e8480:
        {
            var_1c = 0x100b;
            break;
        }
        case 0x2625a0:
        {
            var_1c = 0x100c;
            break;
        }
        case 0x2dc6c0:
        {
            var_1c = 0x100d;
            break;
        }
        case 0x3567e0:
        {
            var_1c = 0x100e;
            break;
        }
        case 0x3d0900:
        {
            var_1c = 0x100f;
            break;
        }
        default:
            var_1c = 0xffffffff;
    }
    if (*(_ReadStatusReg(tpidr_el0) + 0x28) != x8_1)
    {
        __stack_chk_fail();
        /* no return */
    }
    return var_1c;
}

int64_t Java_com_pudutech_serialport_library_SerialPort_close(int64_t* arg1, int64_t arg2)
{
    int32_t var_84 = 3;
    int64_t x0_1 = *(*arg1 + 0xf8)(arg1, arg2, "fd", "Ljava/io/FileDescriptor;");
    int64_t x0_3 = *(*arg1 + 0x30)(arg1, "java/io/FileDescriptor");
    int64_t x0_5 = *(*arg1 + 0x2f0)(arg1, x0_1, "fd", "Ljava/io/FileDescriptor;");
    int64_t x0_7 = *(*arg1 + 0x2f0)(arg1, x0_3, "descriptor", "I");
    int32_t x0_11 = *(*arg1 + 0x320)(arg1, *(*arg1 + 0x2f8)(arg1, arg2, x0_5), x0_7);
    __android_log_print(3, data_3000, "close(fd = %d)", x0_11);
    return close(x0_11);
}

int64_t Java_com_pudutech_serialport_library_SerialPort_tcflush(int64_t* arg1, int64_t arg2)
{
    int32_t var_84 = 3;
    int32_t var_94 = 2;
    int64_t x0_1 = *(*arg1 + 0xf8)(arg1, arg2, "fd", "Ljava/io/FileDescriptor;");
    int64_t x0_3 = *(*arg1 + 0x30)(arg1, "java/io/FileDescriptor");
    int64_t x0_5 = *(*arg1 + 0x2f0)(arg1, x0_1, "fd", "Ljava/io/FileDescriptor;");
    int64_t x0_7 = *(*arg1 + 0x2f0)(arg1, x0_3, "descriptor", "I");
    int32_t x0_11 = *(*arg1 + 0x320)(arg1, *(*arg1 + 0x2f8)(arg1, arg2, x0_5), x0_7);
    __android_log_print(3, data_3000, "tcflush(fd = %d)", x0_11);
    return tcflush(x0_11, 2);
}

