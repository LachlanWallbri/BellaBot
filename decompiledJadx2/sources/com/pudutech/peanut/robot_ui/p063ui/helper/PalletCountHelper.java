package com.pudutech.peanut.robot_ui.p063ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.robot_ui.RobotContext;
import kotlin.Metadata;

/* compiled from: PalletCountHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004J\b\u0010\n\u001a\u00020\u0004H\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/PalletCountHelper;", "", "()V", "DEFUALT_COUNT", "", PalletCountHelper.SP_PALLET_COUNT, "", "SP_PALLET_ENABLE_INDEX", "TAG", "getCount", "getDefaultCount", "isPalletEnable", "", "i", "setCount", "", "c", "setPalletEnable", "boolean", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PalletCountHelper {
    private static final int DEFUALT_COUNT;
    public static final PalletCountHelper INSTANCE;
    private static final String SP_PALLET_COUNT;
    private static final String SP_PALLET_ENABLE_INDEX;
    private static final String TAG;

    public final int getCount() {
        return 3;
    }

    static {
        PalletCountHelper palletCountHelper = new PalletCountHelper();
        INSTANCE = palletCountHelper;
        TAG = TAG;
        SP_PALLET_COUNT = SP_PALLET_COUNT;
        SP_PALLET_ENABLE_INDEX = SP_PALLET_ENABLE_INDEX;
        DEFUALT_COUNT = 3;
        int defaultCount = palletCountHelper.getDefaultCount();
        Pdlog.m3273d(TAG, "PalletCountHelper defualt = " + defaultCount);
        palletCountHelper.setCount(defaultCount);
    }

    private PalletCountHelper() {
    }

    private final int getDefaultCount() {
        Pdlog.m3273d(TAG, "getDefaultCount");
        int i = SpUtils.get(RobotContext.INSTANCE.getContext(), SP_PALLET_COUNT, 0);
        if (i == 0) {
            return DEFUALT_COUNT;
        }
        Pdlog.m3273d(TAG, "getDefaultCount , use new save value = " + i);
        return i;
    }

    public final void setCount(int c) {
        Pdlog.m3273d(TAG, "setCount " + c);
        SpUtils.set(RobotContext.INSTANCE.getContext(), SP_PALLET_COUNT, c);
    }

    public final boolean isPalletEnable(int i) {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), SP_PALLET_ENABLE_INDEX + i, true);
    }

    public final void setPalletEnable(int i, boolean r5) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), SP_PALLET_ENABLE_INDEX + i, r5);
    }
}
