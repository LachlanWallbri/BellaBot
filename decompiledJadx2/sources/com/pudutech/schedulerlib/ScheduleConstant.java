package com.pudutech.schedulerlib;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ScheduleConstant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ScheduleConstant;", "", "()V", "FIRST_CHANNEL_INDEX", "", "MULTICAST_ADDRESS_LIST", "", "", "getMULTICAST_ADDRESS_LIST", "()[Ljava/lang/String;", "[Ljava/lang/String;", "PREFERENCE_KEY", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ScheduleConstant {
    public static final int FIRST_CHANNEL_INDEX = 1;
    public static final ScheduleConstant INSTANCE = new ScheduleConstant();
    private static final String[] MULTICAST_ADDRESS_LIST = {"230.10.10.10", "230.11.10.10", "230.12.10.10", "230.13.10.10", "230.14.10.10", "230.15.10.10", "230.16.10.10", "230.17.10.10", "230.18.10.10", "230.19.10.10", "230.20.10.10", "230.21.10.10", "230.22.10.10"};
    public static final String PREFERENCE_KEY = "ScheduleChannelId";

    private ScheduleConstant() {
    }

    public final String[] getMULTICAST_ADDRESS_LIST() {
        return MULTICAST_ADDRESS_LIST;
    }
}
