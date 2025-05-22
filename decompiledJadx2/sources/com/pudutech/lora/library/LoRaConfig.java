package com.pudutech.lora.library;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: LoRaConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0014\u0010\u0012\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/lora/library/LoRaConfig;", "", "()V", "BAUD_RATE", "", "FLAGS", "FRAME_BROADCAST_ADDR", "", "getFRAME_BROADCAST_ADDR", "()[B", "FRAME_CMD_TYPE", "getFRAME_CMD_TYPE", "FRAME_DEFAULT_CHECK_CODE", "getFRAME_DEFAULT_CHECK_CODE", "FRAME_DEFAULT_HEAD", "getFRAME_DEFAULT_HEAD", "FRAME_DEFAULT_SEQ", "getFRAME_DEFAULT_SEQ", "FRAME_HEADLEN_BEFORE_DATA", "getFRAME_HEADLEN_BEFORE_DATA", "()I", "ID_PRODUCT", "", "ID_VENDOR", "PRODUCT", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class LoRaConfig {
    public static final int BAUD_RATE = 9600;
    public static final int FLAGS = 0;
    private static final byte[] FRAME_BROADCAST_ADDR;
    private static final byte[] FRAME_DEFAULT_CHECK_CODE;
    private static final byte[] FRAME_DEFAULT_SEQ;
    public static final String ID_PRODUCT = "2303";
    public static final String ID_VENDOR = "067b";
    public static final String PRODUCT = "USB-Serial Controller";
    public static final LoRaConfig INSTANCE = new LoRaConfig();
    private static final byte[] FRAME_DEFAULT_HEAD = {(byte) 90};
    private static final byte[] FRAME_CMD_TYPE = {(byte) 224};
    private static final int FRAME_HEADLEN_BEFORE_DATA = 8;

    static {
        byte b = (byte) 255;
        FRAME_BROADCAST_ADDR = new byte[]{b, b, b, b};
        byte b2 = (byte) 0;
        FRAME_DEFAULT_SEQ = new byte[]{b2};
        FRAME_DEFAULT_CHECK_CODE = new byte[]{b2};
    }

    private LoRaConfig() {
    }

    public final byte[] getFRAME_BROADCAST_ADDR() {
        return FRAME_BROADCAST_ADDR;
    }

    public final byte[] getFRAME_DEFAULT_HEAD() {
        return FRAME_DEFAULT_HEAD;
    }

    public final byte[] getFRAME_DEFAULT_SEQ() {
        return FRAME_DEFAULT_SEQ;
    }

    public final byte[] getFRAME_DEFAULT_CHECK_CODE() {
        return FRAME_DEFAULT_CHECK_CODE;
    }

    public final byte[] getFRAME_CMD_TYPE() {
        return FRAME_CMD_TYPE;
    }

    public final int getFRAME_HEADLEN_BEFORE_DATA() {
        return FRAME_HEADLEN_BEFORE_DATA;
    }
}
