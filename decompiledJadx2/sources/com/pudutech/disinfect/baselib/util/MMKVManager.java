package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.tencent.mmkv.MMKV;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: MMKVManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00182\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001J\u001e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/MMKVManager;", "", "()V", "mmkvInstance", "Lcom/tencent/mmkv/MMKV;", "containsKey", "", TransferTable.COLUMN_KEY, "", "getBoolean", "defaultValue", "getByteArray", "", "getDouble", "", "getFloat", "", "getInt", "", "getLong", "", "def", "getString", "getStringSet", "", "init", "", "context", "Landroid/content/Context;", "remove", TmpConstant.PROPERTY_IDENTIFIER_SET, ES6Iterator.VALUE_PROPERTY, "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MMKVManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MMKVManager>() { // from class: com.pudutech.disinfect.baselib.util.MMKVManager$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MMKVManager invoke() {
            return new MMKVManager(null);
        }
    });
    private static final String TAG = "MMKVManager";
    private MMKV mmkvInstance;

    private MMKVManager() {
    }

    public /* synthetic */ MMKVManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: MMKVManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/MMKVManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/disinfect/baselib/util/MMKVManager;", "getINSTANCE", "()Lcom/pudutech/disinfect/baselib/util/MMKVManager;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        public final MMKVManager getINSTANCE() {
            Lazy lazy = MMKVManager.INSTANCE$delegate;
            Companion companion = MMKVManager.INSTANCE;
            return (MMKVManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        MMKV.initialize(context);
        MMKV defaultMMKV = MMKV.defaultMMKV();
        if (defaultMMKV == null) {
            Intrinsics.throwNpe();
        }
        this.mmkvInstance = defaultMMKV;
    }

    public final void set(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (value == null) {
            remove(key);
            return;
        }
        Pdlog.m3273d(TAG, "set() key =" + key + "  value =" + value);
        if (value instanceof String) {
            MMKV mmkv = this.mmkvInstance;
            if (mmkv == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv.encode(key, (String) value);
            return;
        }
        if (value instanceof Integer) {
            MMKV mmkv2 = this.mmkvInstance;
            if (mmkv2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv2.encode(key, ((Number) value).intValue());
            return;
        }
        if (value instanceof Long) {
            MMKV mmkv3 = this.mmkvInstance;
            if (mmkv3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv3.encode(key, ((Number) value).longValue());
            return;
        }
        if (value instanceof Float) {
            MMKV mmkv4 = this.mmkvInstance;
            if (mmkv4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv4.encode(key, ((Number) value).floatValue());
            return;
        }
        if (value instanceof Double) {
            MMKV mmkv5 = this.mmkvInstance;
            if (mmkv5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv5.encode(key, ((Number) value).doubleValue());
            return;
        }
        if (value instanceof Boolean) {
            MMKV mmkv6 = this.mmkvInstance;
            if (mmkv6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv6.encode(key, ((Boolean) value).booleanValue());
            return;
        }
        if (!(value instanceof byte[])) {
            if (value instanceof Void) {
            }
            return;
        }
        MMKV mmkv7 = this.mmkvInstance;
        if (mmkv7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        mmkv7.encode(key, (byte[]) value);
    }

    public final void set(String key, Set<String> set) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Set<String> set2 = set;
        if (set2 == null || set2.isEmpty()) {
            remove(key);
            return;
        }
        Pdlog.m3273d(TAG, "set() key =" + key + "  value =" + set);
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        mmkv.encode(key, set);
    }

    public final String getString(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        String decodeString = mmkv.decodeString(key);
        Pdlog.m3273d(TAG, "getString() key =" + key + "  value =" + decodeString);
        return decodeString;
    }

    public final String getString(String key, String defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        String decodeString = mmkv.decodeString(key, defaultValue);
        Pdlog.m3273d(TAG, "getString() key =" + key + "  value =" + decodeString);
        return decodeString;
    }

    public final int getInt(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getInt(key, 0);
    }

    public final int getInt(String key, int defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        int decodeInt = mmkv.decodeInt(key, defaultValue);
        Pdlog.m3273d(TAG, "getInt() key =" + key + "  value =" + decodeInt);
        return decodeInt;
    }

    public final long getLong(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getLong(key, 0L);
    }

    public final long getLong(String key, long def) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        long decodeLong = mmkv.decodeLong(key, def);
        Pdlog.m3273d(TAG, "getLong() key =" + key + "  value =" + decodeLong);
        return decodeLong;
    }

    public final float getFloat(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getFloat(key, 0.0f);
    }

    public final float getFloat(String key, float defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.decodeFloat(key, defaultValue);
    }

    public final double getDouble(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getDouble(key, 0.0d);
    }

    public final double getDouble(String key, double defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.decodeDouble(key, defaultValue);
    }

    public final boolean getBoolean(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return getBoolean(key, false);
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.decodeBool(key, defaultValue);
    }

    public final byte[] getByteArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.decodeBytes(key);
    }

    public final Set<String> getStringSet(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.decodeStringSet(key);
    }

    public final void remove(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (containsKey(key)) {
            MMKV mmkv = this.mmkvInstance;
            if (mmkv == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
            }
            mmkv.removeValueForKey(key);
        }
    }

    public final boolean containsKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv = this.mmkvInstance;
        if (mmkv == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mmkvInstance");
        }
        return mmkv.containsKey(key);
    }
}
