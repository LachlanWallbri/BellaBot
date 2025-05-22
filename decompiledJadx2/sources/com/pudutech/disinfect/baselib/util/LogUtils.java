package com.pudutech.disinfect.baselib.util;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.acs.smartcard.Reader;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: LogUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u0018\u0010\t\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\n\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/LogUtils;", "", "()V", "DEFAULT_TAG", "", "debugInfo", "", NotificationCompat.CATEGORY_MESSAGE, AIUIConstant.KEY_TAG, "debugLongInfo", "warnInfo", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LogUtils {
    private static final String DEFAULT_TAG = "PuTag";
    public static final LogUtils INSTANCE = new LogUtils();

    private LogUtils() {
    }

    public final void debugInfo(String tag, String msg) {
        if (!LogExtKt.getLogOptions() || TextUtils.isEmpty(msg)) {
            return;
        }
        Pdlog.m3273d(tag, msg);
    }

    public final void debugInfo(String msg) {
        debugInfo(DEFAULT_TAG, msg);
    }

    public final void warnInfo(String tag, String msg) {
        if (!LogExtKt.getLogOptions() || TextUtils.isEmpty(msg)) {
            return;
        }
        Pdlog.m3277w(tag, msg);
    }

    public final void warnInfo(String msg) {
        warnInfo(DEFAULT_TAG, msg);
    }

    public final void debugLongInfo(String tag, String msg) {
        String substring;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (LogExtKt.getLogOptions()) {
            String str = msg;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = str.charAt(!z ? i : length) <= ' ';
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            String obj = str.subSequence(i, length + 1).toString();
            int i2 = 0;
            while (i2 < obj.length()) {
                int length2 = obj.length();
                int i3 = i2 + Reader.IOCTL_CCID_ESCAPE;
                if (length2 <= i3) {
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    substring = obj.substring(i2);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                } else if (obj != null) {
                    substring = obj.substring(i2, i3);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                Object[] objArr = new Object[1];
                String str2 = substring;
                boolean z3 = false;
                int length3 = str2.length() - 1;
                int i4 = 0;
                while (i4 <= length3) {
                    boolean z4 = str2.charAt(!z3 ? i4 : length3) <= ' ';
                    if (z3) {
                        if (!z4) {
                            break;
                        } else {
                            length3--;
                        }
                    } else if (z4) {
                        i4++;
                    } else {
                        z3 = true;
                    }
                }
                objArr[0] = str2.subSequence(i4, length3 + 1).toString();
                Pdlog.m3273d(tag, objArr);
                i2 = i3;
            }
        }
    }

    public final void debugLongInfo(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        debugLongInfo(DEFAULT_TAG, msg);
    }
}
