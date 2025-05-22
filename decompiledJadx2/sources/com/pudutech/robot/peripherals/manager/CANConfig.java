package com.pudutech.robot.peripherals.manager;

import kotlin.Metadata;
import kotlin.UByte;

/* compiled from: CANManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b(\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0016\u0010\n\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0016\u0010\f\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0016\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0016\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006R\u0016\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0013\u0010\u0006R\u0016\u0010\u0014\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0015\u0010\u0006R\u0016\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0017\u0010\u0006R\u0016\u0010\u0018\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0019\u0010\u0006R\u0016\u0010\u001a\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001b\u0010\u0006R\u0016\u0010\u001c\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001d\u0010\u0006R\u0016\u0010\u001e\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001f\u0010\u0006R\u0016\u0010 \u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b!\u0010\u0006R\u0016\u0010\"\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b#\u0010\u0006R\u0016\u0010$\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b%\u0010\u0006R\u0016\u0010&\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b'\u0010\u0006R\u0016\u0010(\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b)\u0010\u0006R\u0016\u0010*\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b+\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/manager/CANConfig;", "", "()V", "CAN_CMD_HEAD_41_PROTOCOL", "Lkotlin/UByte;", "getCAN_CMD_HEAD_41_PROTOCOL", "()B", "B", "CAN_CMD_HEAD_47_PROTOCOL", "getCAN_CMD_HEAD_47_PROTOCOL", "CAN_CMD_HEAD_82_PROTOCOL", "getCAN_CMD_HEAD_82_PROTOCOL", "CAN_CMD_HEAD_83_PROTOCOL", "getCAN_CMD_HEAD_83_PROTOCOL", "CAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY", "getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY", "CAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT", "getCAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT", "CAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT", "getCAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT", "CAN_CMD_HEAD_89_PROTOCOL", "getCAN_CMD_HEAD_89_PROTOCOL", "CAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG", "getCAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG", "CAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH", "getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH", "CAN_CMD_HEAD_94_PROTOCOL", "getCAN_CMD_HEAD_94_PROTOCOL", "CAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT", "getCAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT", "CAN_CMD_MSG_00", "getCAN_CMD_MSG_00", "CAN_CMD_MSG_01", "getCAN_CMD_MSG_01", "CAN_CMD_MSG_07", "getCAN_CMD_MSG_07", "CAN_CMD_MSG_09", "getCAN_CMD_MSG_09", "CAN_CMD_MSG_81", "getCAN_CMD_MSG_81", "CAN_CMD_MSG_84", "getCAN_CMD_MSG_84", "CAN_CMD_MSG_C0", "getCAN_CMD_MSG_C0", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CANConfig {
    private static final byte CAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY;
    private static final byte CAN_CMD_HEAD_89_PROTOCOL;
    private static final byte CAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG;
    private static final byte CAN_CMD_MSG_84;
    public static final CANConfig INSTANCE = new CANConfig();
    private static final byte CAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH = UByte.m4528constructorimpl((byte) 139);
    private static final byte CAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT = UByte.m4528constructorimpl((byte) 176);
    private static final byte CAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT = UByte.m4528constructorimpl((byte) 136);
    private static final byte CAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT = UByte.m4528constructorimpl((byte) 135);
    private static final byte CAN_CMD_HEAD_83_PROTOCOL = UByte.m4528constructorimpl((byte) 131);
    private static final byte CAN_CMD_HEAD_82_PROTOCOL = UByte.m4528constructorimpl((byte) 130);
    private static final byte CAN_CMD_HEAD_47_PROTOCOL = UByte.m4528constructorimpl((byte) 71);
    private static final byte CAN_CMD_HEAD_41_PROTOCOL = UByte.m4528constructorimpl((byte) 65);
    private static final byte CAN_CMD_HEAD_94_PROTOCOL = UByte.m4528constructorimpl((byte) 148);
    private static final byte CAN_CMD_MSG_07 = UByte.m4528constructorimpl((byte) 7);
    private static final byte CAN_CMD_MSG_09 = UByte.m4528constructorimpl((byte) 9);
    private static final byte CAN_CMD_MSG_01 = UByte.m4528constructorimpl((byte) 1);
    private static final byte CAN_CMD_MSG_00 = UByte.m4528constructorimpl((byte) 0);
    private static final byte CAN_CMD_MSG_81 = UByte.m4528constructorimpl((byte) 129);
    private static final byte CAN_CMD_MSG_C0 = UByte.m4528constructorimpl((byte) 192);

    static {
        byte b = (byte) 132;
        CAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY = UByte.m4528constructorimpl(b);
        byte b2 = (byte) 137;
        CAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG = UByte.m4528constructorimpl(b2);
        CAN_CMD_HEAD_89_PROTOCOL = UByte.m4528constructorimpl(b2);
        CAN_CMD_MSG_84 = UByte.m4528constructorimpl(b);
    }

    private CANConfig() {
    }

    public final byte getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH() {
        return CAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH;
    }

    public final byte getCAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT() {
        return CAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT;
    }

    public final byte getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY() {
        return CAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY;
    }

    public final byte getCAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG() {
        return CAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG;
    }

    public final byte getCAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT() {
        return CAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT;
    }

    public final byte getCAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT() {
        return CAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT;
    }

    public final byte getCAN_CMD_HEAD_83_PROTOCOL() {
        return CAN_CMD_HEAD_83_PROTOCOL;
    }

    public final byte getCAN_CMD_HEAD_89_PROTOCOL() {
        return CAN_CMD_HEAD_89_PROTOCOL;
    }

    public final byte getCAN_CMD_HEAD_82_PROTOCOL() {
        return CAN_CMD_HEAD_82_PROTOCOL;
    }

    public final byte getCAN_CMD_HEAD_47_PROTOCOL() {
        return CAN_CMD_HEAD_47_PROTOCOL;
    }

    public final byte getCAN_CMD_HEAD_41_PROTOCOL() {
        return CAN_CMD_HEAD_41_PROTOCOL;
    }

    public final byte getCAN_CMD_HEAD_94_PROTOCOL() {
        return CAN_CMD_HEAD_94_PROTOCOL;
    }

    public final byte getCAN_CMD_MSG_07() {
        return CAN_CMD_MSG_07;
    }

    public final byte getCAN_CMD_MSG_09() {
        return CAN_CMD_MSG_09;
    }

    public final byte getCAN_CMD_MSG_01() {
        return CAN_CMD_MSG_01;
    }

    public final byte getCAN_CMD_MSG_00() {
        return CAN_CMD_MSG_00;
    }

    public final byte getCAN_CMD_MSG_81() {
        return CAN_CMD_MSG_81;
    }

    public final byte getCAN_CMD_MSG_84() {
        return CAN_CMD_MSG_84;
    }

    public final byte getCAN_CMD_MSG_C0() {
        return CAN_CMD_MSG_C0;
    }
}
