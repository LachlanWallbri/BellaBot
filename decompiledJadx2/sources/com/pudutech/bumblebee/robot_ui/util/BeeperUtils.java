package com.pudutech.bumblebee.robot_ui.util;

import android.util.Log;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BeeperUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\f\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0019\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00178F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00178F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001b¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/BeeperUtils;", "", "()V", "AUTO_COMPLETE_CALL_TIME", "", "AUTO_COMPLETE_CALL_TIME_SWITCH", "AUTO_COMPLETE_TIME", "", "CALL_REACHED_CONFIRMATION_SWITCH", "CHARS", "", "", "[Ljava/lang/Character;", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", ES6Iterator.VALUE_PROPERTY, "getAutoCompleteCallTime", "getGetAutoCompleteCallTime", "()J", "setGetAutoCompleteCallTime", "(J)V", "", "isAutoCompleteCallTimeSwitch", "()Z", "setAutoCompleteCallTimeSwitch", "(Z)V", "isCallReachedoConfirmationSwitch", "setCallReachedoConfirmationSwitch", "generateRandomCentralControlCode", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BeeperUtils {
    private static final String AUTO_COMPLETE_CALL_TIME = "auto_complete_call_time";
    private static final String AUTO_COMPLETE_CALL_TIME_SWITCH = "auto_complete_call_time_switch";
    public static final long AUTO_COMPLETE_TIME = 120000;
    private static final String CALL_REACHED_CONFIRMATION_SWITCH = "call_reached_confirmation_switch";
    private static final Character[] CHARS;
    public static final BeeperUtils INSTANCE;
    private static final String TAG;

    static {
        BeeperUtils beeperUtils = new BeeperUtils();
        INSTANCE = beeperUtils;
        TAG = beeperUtils.getClass().getSimpleName();
        CHARS = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), 'M', 'N', 'O', 'P', 'Q', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR), 'S', 'T', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), 'V', 'W', 'X', 'Y', Character.valueOf(Matrix.MATRIX_TYPE_ZERO)};
    }

    private BeeperUtils() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final String generateRandomCentralControlCode() {
        int random;
        char[] cArr = new char[8];
        boolean[] zArr = new boolean[CHARS.length];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            do {
                random = RangesKt.random(ArraysKt.getIndices(CHARS), Random.INSTANCE);
            } while (zArr[random]);
            cArr[i] = CHARS[random].charValue();
            zArr[random] = true;
        }
        String str = new String(cArr);
        Log.d(TAG, "random centralControl code = " + str);
        return str;
    }

    public final void setCallReachedoConfirmationSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(CALL_REACHED_CONFIRMATION_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isCallReachedoConfirmationSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(CALL_REACHED_CONFIRMATION_SWITCH);
    }

    public final void setAutoCompleteCallTimeSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(AUTO_COMPLETE_CALL_TIME_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isAutoCompleteCallTimeSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(AUTO_COMPLETE_CALL_TIME_SWITCH);
    }

    public final void setGetAutoCompleteCallTime(long j) {
        MMKVManager.INSTANCE.getINSTANCE().set(AUTO_COMPLETE_CALL_TIME, Long.valueOf(j));
    }

    public final long getGetAutoCompleteCallTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getLong(AUTO_COMPLETE_CALL_TIME, 120000L);
    }
}
