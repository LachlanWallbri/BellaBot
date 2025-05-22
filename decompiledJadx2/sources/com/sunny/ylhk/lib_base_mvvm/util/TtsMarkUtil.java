package com.sunny.ylhk.lib_base_mvvm.util;

import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TtsMarkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J%\u0010\u0012\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u0002H\u0013H\u0002¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J$\u0010\u0019\u001a\u0004\u0018\u0001H\u0013\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0004H\u0082\b¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010!\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0002J)\u0010#\u001a\u00020\u0010\"\b\b\u0000\u0010\u0013*\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u0001H\u0013H\u0002¢\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0016\u0010&\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0004J\u0016\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006+"}, m3961d2 = {"Lcom/sunny/ylhk/lib_base_mvvm/util/TtsMarkUtil;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mTtsMark", "Lcom/tencent/mmkv/MMKV;", "getMTtsMark", "()Lcom/tencent/mmkv/MMKV;", "setMTtsMark", "(Lcom/tencent/mmkv/MMKV;)V", "clearAll", "", "containsKey", "", TransferTable.COLUMN_KEY, TmpConstant.PROPERTY_IDENTIFIER_GET, ExifInterface.GPS_DIRECTION_TRUE, "t", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getLocalTtsSwitchKey", "ttsVoiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getParcelable", "Landroid/os/Parcelable;", "(Ljava/lang/String;)Landroid/os/Parcelable;", "getTtsPlayInterval", "getTtsPlayIntervalKey", "getTtsSwitch", "", "getTtsSwitchKey", "put", ES6Iterator.VALUE_PROPERTY, "putParcelable", "(Ljava/lang/String;Landroid/os/Parcelable;)Z", "removeKey", "saveTtsPlayInterval", "interval", "saveTtsSwitch", "ttsVoiceOpenType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceOpenType;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class TtsMarkUtil {
    public static final TtsMarkUtil INSTANCE = new TtsMarkUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static MMKV mTtsMark = MMKV.mmkvWithID("ttsMark");

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 6;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 7;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
            $EnumSwitchMapping$1 = new int[TtsVoiceHelper.TtsVoiceType.values().length];
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.GREETER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 4;
        }
    }

    private TtsMarkUtil() {
    }

    public final MMKV getMTtsMark() {
        return mTtsMark;
    }

    public final void setMTtsMark(MMKV mmkv) {
        mTtsMark = mmkv;
    }

    public final String getTAG() {
        return TAG;
    }

    private final void put(String key, Object value) {
        MMKV mmkv;
        if (value == null) {
            removeKey(key);
            return;
        }
        if (value instanceof String) {
            MMKV mmkv2 = mTtsMark;
            if (mmkv2 != null) {
                mmkv2.encode(key, (String) value);
                return;
            }
            return;
        }
        if (value instanceof Float) {
            MMKV mmkv3 = mTtsMark;
            if (mmkv3 != null) {
                mmkv3.encode(key, ((Number) value).floatValue());
                return;
            }
            return;
        }
        if (value instanceof Boolean) {
            MMKV mmkv4 = mTtsMark;
            if (mmkv4 != null) {
                mmkv4.encode(key, ((Boolean) value).booleanValue());
                return;
            }
            return;
        }
        if (value instanceof Integer) {
            MMKV mmkv5 = mTtsMark;
            if (mmkv5 != null) {
                mmkv5.encode(key, ((Number) value).intValue());
                return;
            }
            return;
        }
        if (value instanceof Long) {
            MMKV mmkv6 = mTtsMark;
            if (mmkv6 != null) {
                mmkv6.encode(key, ((Number) value).longValue());
                return;
            }
            return;
        }
        if (value instanceof Double) {
            MMKV mmkv7 = mTtsMark;
            if (mmkv7 != null) {
                mmkv7.encode(key, ((Number) value).doubleValue());
                return;
            }
            return;
        }
        if (!(value instanceof byte[]) || (mmkv = mTtsMark) == null) {
            return;
        }
        mmkv.encode(key, (byte[]) value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> Object get(String key, T t) {
        Object decodeBytes;
        MMKV mmkv = mTtsMark;
        Object obj = null;
        if (mmkv != null) {
            if (t instanceof String) {
                decodeBytes = mmkv.decodeString(key, (String) t);
            } else if (t instanceof Boolean) {
                decodeBytes = Boolean.valueOf(mmkv.decodeBool(key, ((Boolean) t).booleanValue()));
            } else if (t instanceof Float) {
                decodeBytes = Float.valueOf(mmkv.decodeFloat(key, ((Number) t).floatValue()));
            } else if (t instanceof Integer) {
                decodeBytes = Integer.valueOf(mmkv.decodeInt(key, ((Number) t).intValue()));
            } else if (t instanceof Long) {
                decodeBytes = Long.valueOf(mmkv.decodeLong(key, ((Number) t).longValue()));
            } else if (t instanceof Double) {
                decodeBytes = Double.valueOf(mmkv.decodeDouble(key, ((Number) t).doubleValue()));
            } else if (t instanceof byte[]) {
                decodeBytes = mmkv.decodeBytes(key, (byte[]) t);
            }
            obj = decodeBytes;
        }
        if (obj == null) {
            return 0;
        }
        return obj;
    }

    private final <T extends Parcelable> boolean putParcelable(String key, T t) {
        if (t == null) {
            return false;
        }
        MMKV mmkv = mTtsMark;
        Boolean valueOf = mmkv != null ? Boolean.valueOf(mmkv.encode(key, t)) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        return valueOf.booleanValue();
    }

    private final /* synthetic */ <T extends Parcelable> T getParcelable(String key) {
        MMKV mTtsMark2 = getMTtsMark();
        if (mTtsMark2 == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) mTtsMark2.decodeParcelable(key, Parcelable.class);
    }

    private final void removeKey(String key) {
        MMKV mmkv;
        if (containsKey(key) && (mmkv = mTtsMark) != null) {
            mmkv.removeValueForKey(key);
        }
    }

    private final boolean containsKey(String key) {
        MMKV mmkv = mTtsMark;
        return mmkv != null && mmkv.containsKey(key);
    }

    private final void clearAll() {
        MMKV mmkv = mTtsMark;
        if (mmkv != null) {
            mmkv.clearAll();
        }
    }

    public final void saveTtsSwitch(TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceOpenType, "ttsVoiceOpenType");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        String localTtsSwitchKey = getLocalTtsSwitchKey(ttsVoiceType);
        Pdlog.m3273d(TAG, "saveTtsSwitchKey--:" + localTtsSwitchKey);
        put(localTtsSwitchKey, Integer.valueOf(ttsVoiceOpenType.getType()));
    }

    public final int getTtsSwitch(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        String localTtsSwitchKey = getLocalTtsSwitchKey(ttsVoiceType);
        Pdlog.m3273d(TAG, "getTtsSwitch--:" + localTtsSwitchKey);
        Object obj = get(localTtsSwitchKey, 0);
        if (obj != null) {
            return ((Integer) obj).intValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }

    private final String getLocalTtsSwitchKey(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        return getTtsSwitchKey(ttsVoiceType) + localeStr$default;
    }

    public final String getTtsSwitchKey(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                return "key_cruise_tts_type";
            case DELIVER_TYPE:
                return "key_deliver_tts_type";
            case GREETER_TYPE:
                return Constans.KEY_GREETER_TTS_TYPE;
            case GREETER_GUIDE_TYPE:
                return Constans.KEY_GREETER_GUIDE_TTS_TYPE;
            case GUIDE_ARRIVAL_TYPE:
                return Constans.KEY_GUIDE_ARRIVAL_TTS_TYPE;
            case SPECIAL_MODE_ARRIVE:
                return Constans.KEY_SPECIAL_MODE_ARRIVE_TTS_TYPE;
            case CRUISE_STAY_TYPE:
                return Constans.KEY_CRUISE_STAY_TYPE;
            case BIRTHDAY_TYPE:
                return Constans.KEY_BIRTHDAY_MODE_TYPE;
            case PALLET_DELIVER_TYPE:
                return Constans.KEY_PALLET_DELIVER_TTS_TYPE;
            case RECYCLE_TABLE_ARRIVE:
                return Constans.KEY_RECYCLE_TABLE_ARRIVE_TYPE;
            case RECYCLE_TABLE_LEAVE:
                return Constans.KEY_RECYCLE_TABLE_LEAVE_TYPE;
            case RECYCLE_POINT_ARRIVE:
                return Constans.KEY_RECYCLE_POINT_ARRIVE_TYPE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void saveTtsPlayInterval(TtsVoiceHelper.TtsVoiceType ttsVoiceType, String interval) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(interval, "interval");
        String ttsPlayIntervalKey = getTtsPlayIntervalKey(ttsVoiceType);
        Pdlog.m3273d(TAG, "saveTtsPlayInterval--key：" + ttsPlayIntervalKey);
        put(ttsPlayIntervalKey, interval);
    }

    public final String getTtsPlayInterval(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        String ttsPlayIntervalKey = getTtsPlayIntervalKey(ttsVoiceType);
        Pdlog.m3273d(TAG, "getTtsPlayInterval--key：" + ttsPlayIntervalKey);
        Object obj = get(ttsPlayIntervalKey, "20");
        if (obj != null) {
            return (String) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    private final String getTtsPlayIntervalKey(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        int i = WhenMappings.$EnumSwitchMapping$1[ttsVoiceType.ordinal()];
        return (i != 1 ? i != 2 ? i != 3 ? i != 4 ? "mmkv_error" : Constans.KEY_CRUISE_STAY_VOICE_INTERVAL_TIME : "key_greeter_guide_voice_interval" : "key_greeter_voice_interval" : "key_cruise_voice_interval") + localeStr$default;
    }
}
