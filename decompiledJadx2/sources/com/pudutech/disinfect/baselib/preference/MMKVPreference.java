package com.pudutech.disinfect.baselib.preference;

import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.tencent.mmkv.MMKV;
import java.util.Collections;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MMKVPreference.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u000eJ\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0019¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0002\u0010 J\u0015\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010#J-\u0010$\u001a\u0004\u0018\u0001H%\"\b\b\u0000\u0010%*\u00020&2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0(¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010*\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eJ\u0016\u0010+\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010,2\u0006\u0010\r\u001a\u00020\u000eJ'\u0010-\u001a\u00020\n\"\b\b\u0000\u0010%*\u00020&2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u0001H%¢\u0006\u0002\u0010/J\u0018\u0010-\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001J(\u0010-\u001a\u00020\n\"\b\b\u0000\u0010%*\u00020&2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010,J\u0015\u00101\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u00102R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u00063"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/preference/MMKVPreference;", "", "()V", "mmkv", "Lcom/tencent/mmkv/MMKV;", "getMmkv", "()Lcom/tencent/mmkv/MMKV;", "setMmkv", "(Lcom/tencent/mmkv/MMKV;)V", "clearAll", "", "containsKey", "", TransferTable.COLUMN_KEY, "", "(Ljava/lang/String;)Ljava/lang/Boolean;", "decodeBoolean", "flag", "(Ljava/lang/String;Z)Ljava/lang/Boolean;", "decodeByteArray", "", "decodeDouble", "", "(Ljava/lang/String;)Ljava/lang/Double;", "decodeFloat", "", "(Ljava/lang/String;)Ljava/lang/Float;", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;F)Ljava/lang/Float;", "decodeInt", "", "i", "(Ljava/lang/String;I)Ljava/lang/Integer;", "decodeLong", "", "(Ljava/lang/String;)Ljava/lang/Long;", "decodeParcelable", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/os/Parcelable;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Landroid/os/Parcelable;", "decodeString", "decodeStringSet", "", "encode", "t", "(Ljava/lang/String;Landroid/os/Parcelable;)V", "sets", "removeKey", "(Ljava/lang/String;)Lkotlin/Unit;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MMKVPreference {
    public static final MMKVPreference INSTANCE = new MMKVPreference();
    private static MMKV mmkv;

    static {
        LogExtKt.logD$default("mmkv dir " + MMKV.initialize(KtxKt.getAppContext()), null, 1, null);
        mmkv = MMKV.defaultMMKV();
    }

    private MMKVPreference() {
    }

    public final MMKV getMmkv() {
        return mmkv;
    }

    public final void setMmkv(MMKV mmkv2) {
        mmkv = mmkv2;
    }

    public final void encode(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (value instanceof String) {
            MMKV mmkv2 = mmkv;
            if (mmkv2 != null) {
                mmkv2.encode(key, (String) value);
                return;
            }
            return;
        }
        if (value instanceof Float) {
            MMKV mmkv3 = mmkv;
            if (mmkv3 != null) {
                mmkv3.encode(key, ((Number) value).floatValue());
                return;
            }
            return;
        }
        if (value instanceof Boolean) {
            MMKV mmkv4 = mmkv;
            if (mmkv4 != null) {
                mmkv4.encode(key, ((Boolean) value).booleanValue());
                return;
            }
            return;
        }
        if (value instanceof Integer) {
            MMKV mmkv5 = mmkv;
            if (mmkv5 != null) {
                mmkv5.encode(key, ((Number) value).intValue());
                return;
            }
            return;
        }
        if (value instanceof Long) {
            MMKV mmkv6 = mmkv;
            if (mmkv6 != null) {
                mmkv6.encode(key, ((Number) value).longValue());
                return;
            }
            return;
        }
        if (value instanceof Double) {
            MMKV mmkv7 = mmkv;
            if (mmkv7 != null) {
                mmkv7.encode(key, ((Number) value).doubleValue());
                return;
            }
            return;
        }
        if (!(value instanceof byte[])) {
            if (value instanceof Void) {
            }
            return;
        }
        MMKV mmkv8 = mmkv;
        if (mmkv8 != null) {
            mmkv8.encode(key, (byte[]) value);
        }
    }

    public final <T extends Parcelable> void encode(String key, T t) {
        MMKV mmkv2;
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (t == null || (mmkv2 = mmkv) == null) {
            return;
        }
        mmkv2.encode(key, t);
    }

    public final <T extends Parcelable> void encode(String key, Set<String> sets) {
        MMKV mmkv2;
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (sets == null || (mmkv2 = mmkv) == null) {
            return;
        }
        mmkv2.encode(key, sets);
    }

    public final Integer decodeInt(String key, int i) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Integer.valueOf(mmkv2.decodeInt(key, i));
        }
        return null;
    }

    public final Float decodeFloat(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Float.valueOf(mmkv2.decodeFloat(key, 0.0f));
        }
        return null;
    }

    public final Float decodeFloat(String key, float value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Float.valueOf(mmkv2.decodeFloat(key, value));
        }
        return null;
    }

    public final Double decodeDouble(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Double.valueOf(mmkv2.decodeDouble(key, 0.0d));
        }
        return null;
    }

    public final Long decodeLong(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Long.valueOf(mmkv2.decodeLong(key, 0L));
        }
        return null;
    }

    public final Boolean decodeBoolean(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Boolean.valueOf(mmkv2.decodeBool(key, false));
        }
        return null;
    }

    public final Boolean decodeBoolean(String key, boolean flag) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Boolean.valueOf(mmkv2.decodeBool(key, flag));
        }
        return null;
    }

    public final byte[] decodeByteArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return mmkv2.decodeBytes(key);
        }
        return null;
    }

    public final String decodeString(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return mmkv2.decodeString(key, "");
        }
        return null;
    }

    public final String decodeString(String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return mmkv2.decodeString(key, value);
        }
        return null;
    }

    public final Set<String> decodeStringSet(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return mmkv2.decodeStringSet(key, Collections.emptySet());
        }
        return null;
    }

    public final <T extends Parcelable> T decodeParcelable(String key, Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return (T) mmkv2.decodeParcelable(key, clazz);
        }
        return null;
    }

    public final Unit removeKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 == null) {
            return null;
        }
        mmkv2.removeValueForKey(key);
        return Unit.INSTANCE;
    }

    public final Boolean containsKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            return Boolean.valueOf(mmkv2.containsKey(key));
        }
        return null;
    }

    public final void clearAll() {
        MMKV mmkv2 = mmkv;
        if (mmkv2 != null) {
            mmkv2.clearAll();
        }
    }
}
