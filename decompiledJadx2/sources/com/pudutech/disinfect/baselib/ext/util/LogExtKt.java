package com.pudutech.disinfect.baselib.ext.util;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: LogExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\t*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001\u001a\u0014\u0010\u000f\u001a\u00020\t*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001\u001a\u0014\u0010\u0010\u001a\u00020\t*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001\u001a\u0014\u0010\u0011\u001a\u00020\t*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001\u001a\u0014\u0010\u0012\u001a\u00020\t*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"TAG", "", "logOptions", "", "getLogOptions", "()Z", "setLogOptions", "(Z)V", "log", "", "level", "Lcom/pudutech/disinfect/baselib/ext/util/LEVEL;", AIUIConstant.KEY_TAG, "message", "logD", "logE", "logI", "logV", "logW", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LogExtKt {
    public static final String TAG = "PdLog";
    private static boolean logOptions = true;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LEVEL.values().length];

        static {
            $EnumSwitchMapping$0[LEVEL.V.ordinal()] = 1;
            $EnumSwitchMapping$0[LEVEL.D.ordinal()] = 2;
            $EnumSwitchMapping$0[LEVEL.I.ordinal()] = 3;
            $EnumSwitchMapping$0[LEVEL.W.ordinal()] = 4;
            $EnumSwitchMapping$0[LEVEL.E.ordinal()] = 5;
        }
    }

    public static final boolean getLogOptions() {
        return logOptions;
    }

    public static final void setLogOptions(boolean z) {
        logOptions = z;
    }

    public static /* synthetic */ void logV$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = TAG;
        }
        logV(str, str2);
    }

    public static final void logV(String logV, String tag) {
        Intrinsics.checkParameterIsNotNull(logV, "$this$logV");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        log(LEVEL.V, tag, logV);
    }

    public static /* synthetic */ void logD$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = TAG;
        }
        logD(str, str2);
    }

    public static final void logD(String logD, String tag) {
        Intrinsics.checkParameterIsNotNull(logD, "$this$logD");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        log(LEVEL.D, tag, logD);
    }

    public static /* synthetic */ void logI$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = TAG;
        }
        logI(str, str2);
    }

    public static final void logI(String logI, String tag) {
        Intrinsics.checkParameterIsNotNull(logI, "$this$logI");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        log(LEVEL.I, tag, logI);
    }

    public static /* synthetic */ void logW$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = TAG;
        }
        logW(str, str2);
    }

    public static final void logW(String logW, String tag) {
        Intrinsics.checkParameterIsNotNull(logW, "$this$logW");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        log(LEVEL.W, tag, logW);
    }

    public static /* synthetic */ void logE$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = TAG;
        }
        logE(str, str2);
    }

    public static final void logE(String logE, String tag) {
        Intrinsics.checkParameterIsNotNull(logE, "$this$logE");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        log(LEVEL.E, tag, logE);
    }

    private static final void log(LEVEL level, String str, String str2) {
        if (logOptions) {
            int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i == 1) {
                Pdlog.m3276v(str, str2);
                return;
            }
            if (i == 2) {
                Pdlog.m3273d(str, str2);
                return;
            }
            if (i == 3) {
                Pdlog.m3275i(str, str2);
            } else if (i == 4) {
                Pdlog.m3277w(str, str2);
            } else {
                if (i != 5) {
                    return;
                }
                Pdlog.m3274e(str, str2);
            }
        }
    }
}
