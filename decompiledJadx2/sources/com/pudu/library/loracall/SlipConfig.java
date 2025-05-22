package com.pudu.library.loracall;

import kotlin.Metadata;

/* compiled from: SlipConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudu/library/loracall/SlipConfig;", "", "()V", "END", "", "ESC", "ESC_END", "ESC_ESC", "FRAME_TYPE01", "FRAME_TYPE02", "FRAME_TYPE03", "FRAME_TYPE05", "FRAME_TYPE10", "FRAME_TYPE20", "waitTime", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SlipConfig {
    public static final byte END = -64;
    public static final byte ESC = -37;
    public static final byte ESC_END = -36;
    public static final byte ESC_ESC = -35;
    public static final byte FRAME_TYPE01 = 1;
    public static final byte FRAME_TYPE02 = 2;
    public static final byte FRAME_TYPE03 = 3;
    public static final byte FRAME_TYPE05 = 5;
    public static final byte FRAME_TYPE10 = 16;
    public static final byte FRAME_TYPE20 = 32;
    public static final SlipConfig INSTANCE = new SlipConfig();
    public static final long waitTime = 500;

    private SlipConfig() {
    }
}
