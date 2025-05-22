package com.pudutech.disinfect.baselib.util;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LocalStoringUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u000e\u001a\u00020\u0006J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/LocalStoringUtil;", "", "()V", "TAG", "", "mLocalStoring", "Lcom/tencent/mmkv/MMKV;", "clearAll", "", "containsKey", "", TransferTable.COLUMN_KEY, TmpConstant.PROPERTY_IDENTIFIER_GET, "defaultValue", "getMMKV", "put", ES6Iterator.VALUE_PROPERTY, "removeKey", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocalStoringUtil {
    private static MMKV mLocalStoring;
    public static final LocalStoringUtil INSTANCE = new LocalStoringUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    static {
        MMKV mmkvWithID = MMKV.mmkvWithID("local_storing");
        Intrinsics.checkExpressionValueIsNotNull(mmkvWithID, "MMKV.mmkvWithID(\"local_storing\")");
        mLocalStoring = mmkvWithID;
    }

    private LocalStoringUtil() {
    }

    public final void put(String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (value == null) {
            Pdlog.m3273d(TAG, "put() key =" + key + " value =" + value);
            removeKey(key);
            return;
        }
        mLocalStoring.encode(key, value);
        Pdlog.m3273d(TAG, "put() key =" + key + " value =" + value);
    }

    public final String get(String key, String defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String decodeString = mLocalStoring.decodeString(key, defaultValue);
        Pdlog.m3273d(TAG, "get() key =" + key + " result =" + decodeString);
        return decodeString;
    }

    public final MMKV getMMKV() {
        return mLocalStoring;
    }

    public final void removeKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (containsKey(key)) {
            mLocalStoring.removeValueForKey(key);
            Pdlog.m3273d(TAG, "removeKey()");
        }
    }

    public final boolean containsKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return mLocalStoring.containsKey(key);
    }

    public final void clearAll() {
        mLocalStoring.clearAll();
        Pdlog.m3273d(TAG, "clearAll()");
    }
}
