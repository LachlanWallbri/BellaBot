package com.pudutech.mirsdk.config;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SystemProperty.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/SystemProperty;", "", "()V", "ADB_WIFI_CONNECT_KEY", "", "getADB_WIFI_CONNECT_KEY", "()Ljava/lang/String;", "ADB_WIFI_ENABLE_KEY", "getADB_WIFI_ENABLE_KEY", "getProperty", TransferTable.COLUMN_KEY, "context", "Landroid/content/Context;", "setProperty", "", ES6Iterator.VALUE_PROPERTY, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemProperty {
    public static final SystemProperty INSTANCE = new SystemProperty();
    private static final String ADB_WIFI_CONNECT_KEY = ADB_WIFI_CONNECT_KEY;
    private static final String ADB_WIFI_CONNECT_KEY = ADB_WIFI_CONNECT_KEY;
    private static final String ADB_WIFI_ENABLE_KEY = ADB_WIFI_ENABLE_KEY;
    private static final String ADB_WIFI_ENABLE_KEY = ADB_WIFI_ENABLE_KEY;

    private SystemProperty() {
    }

    public final String getProperty(String key, Context context) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class).invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            Pdlog.m3274e("SystemProperty get e:", " stack:", e.getMessage());
            return "";
        }
    }

    public final void setProperty(String key, String value, Context context) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_SET, String.class, String.class).invoke(loadClass, key, value);
        } catch (Exception e) {
            Pdlog.m3274e("SystemProperty set e:", " stack:", e.getMessage());
        }
    }

    public final String getADB_WIFI_CONNECT_KEY() {
        return ADB_WIFI_CONNECT_KEY;
    }

    public final String getADB_WIFI_ENABLE_KEY() {
        return ADB_WIFI_ENABLE_KEY;
    }
}
