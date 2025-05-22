package com.pudutech.lib_update.util;

import android.content.Context;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SystemProperty.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/lib_update/util/SystemProperty;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "getProperty", "", TransferTable.COLUMN_KEY, "setProperty", "", ES6Iterator.VALUE_PROPERTY, "Companion", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemProperty {
    private Context mContext;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ADB_WIFI_CONNECT_KEY = ADB_WIFI_CONNECT_KEY;
    private static final String ADB_WIFI_CONNECT_KEY = ADB_WIFI_CONNECT_KEY;
    private static final String ADB_WIFI_ENABLE_KEY = ADB_WIFI_ENABLE_KEY;
    private static final String ADB_WIFI_ENABLE_KEY = ADB_WIFI_ENABLE_KEY;

    public SystemProperty(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mContext = context;
    }

    public final String getProperty(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            Class<?> loadClass = this.mContext.getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class).invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            Log.e("SystemProperty get e:", " stack:" + e.getMessage());
            return "";
        }
    }

    public final void setProperty(String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        try {
            Class<?> loadClass = this.mContext.getClassLoader().loadClass("android.os.SystemProperties");
            loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_SET, String.class, String.class).invoke(loadClass, key, value);
        } catch (Exception e) {
            Log.e("SystemProperty set e:", " stack:" + e.getMessage());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: SystemProperty.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/lib_update/util/SystemProperty$Companion;", "", "()V", "ADB_WIFI_CONNECT_KEY", "", "getADB_WIFI_CONNECT_KEY", "()Ljava/lang/String;", "ADB_WIFI_ENABLE_KEY", "getADB_WIFI_ENABLE_KEY", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getADB_WIFI_CONNECT_KEY() {
            return SystemProperty.ADB_WIFI_CONNECT_KEY;
        }

        public final String getADB_WIFI_ENABLE_KEY() {
            return SystemProperty.ADB_WIFI_ENABLE_KEY;
        }
    }
}
