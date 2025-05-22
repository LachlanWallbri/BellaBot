package com.pudutech.bumblebee.business.ims.config;

import kotlin.Metadata;

/* compiled from: IMSConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/config/IMSConfig;", "", "()V", "DATA_LENGTH_SIZE", "", "FRAME_HEAD", "", "MSG_RESEND_COUNT", "MSG_RESEND_INTERVAL", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSConfig {
    public static final int DATA_LENGTH_SIZE = 1;
    public static final byte[] FRAME_HEAD;
    public static final IMSConfig INSTANCE = new IMSConfig();
    public static final int MSG_RESEND_COUNT = Integer.MAX_VALUE;
    public static final int MSG_RESEND_INTERVAL = 2000;

    static {
        byte b = (byte) 202;
        FRAME_HEAD = new byte[]{b, b};
    }

    private IMSConfig() {
    }
}
