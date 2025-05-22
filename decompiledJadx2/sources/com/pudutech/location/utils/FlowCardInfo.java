package com.pudutech.location.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\tJ\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\tH\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/location/utils/FlowCardInfo;", "", "()V", "KEY_4G_ACTIVATE_STATUS", "", "getKEY_4G_ACTIVATE_STATUS", "()Ljava/lang/String;", "TAG", "get4GActivateStatus", "", "context", "Landroid/content/Context;", "get4GDataEnable", "getCanActivate", "status", "", "getSimSerialNumber", "hasSimCard", "isMobile", "isWifiConnected", "set4GActivateStatus", "", "isActivate", "set4GDataEnable", "isEnable", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FlowCardInfo {
    private static final String TAG = "FlowCardInfo";
    public static final FlowCardInfo INSTANCE = new FlowCardInfo();
    private static final String KEY_4G_ACTIVATE_STATUS = KEY_4G_ACTIVATE_STATUS;
    private static final String KEY_4G_ACTIVATE_STATUS = KEY_4G_ACTIVATE_STATUS;

    public final boolean getCanActivate(int status) {
        if (status == 1 || status == 5 || status == 6 || status == 7 || status == 8) {
            return true;
        }
        switch (status) {
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }

    private FlowCardInfo() {
    }

    public final String getKEY_4G_ACTIVATE_STATUS() {
        return KEY_4G_ACTIVATE_STATUS;
    }

    public final void set4GDataEnable(Context context, boolean isEnable) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        ((TelephonyManager) systemService).setDataEnabled(isEnable);
    }

    public final boolean get4GDataEnable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        return ((TelephonyManager) systemService).isDataEnabled();
    }

    public final void set4GActivateStatus(Context context, boolean isActivate) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = KEY_4G_ACTIVATE_STATUS + '_' + getSimSerialNumber(context);
        SpUtils.set(context, str, isActivate);
        Pdlog.m3273d(TAG, "set4GActivateStatus key = " + str);
    }

    public final boolean get4GActivateStatus(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = KEY_4G_ACTIVATE_STATUS + '_' + getSimSerialNumber(context);
        boolean z = SpUtils.get(context, str, false);
        Pdlog.m3273d(TAG, "get4GActivateStatus = " + z + " key = " + str);
        return z;
    }

    public final String getSimSerialNumber(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Object systemService = context.getSystemService("phone");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
            }
            String simSerialNumber = ((TelephonyManager) systemService).getSimSerialNumber();
            Pdlog.m3273d(TAG, "simSerialNumber = " + simSerialNumber);
            return simSerialNumber;
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, Unit.INSTANCE);
            return null;
        }
    }

    public final boolean hasSimCard(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        int simState = ((TelephonyManager) systemService).getSimState();
        Pdlog.m3273d(TAG, "simState = " + simState);
        return (simState == 0 || simState == 1) ? false : true;
    }

    public final boolean isMobile(Context context) {
        Object systemService;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            systemService = context.getSystemService("connectivity");
        } catch (Exception unused) {
        }
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo networkInfo = ((ConnectivityManager) systemService).getNetworkInfo(0);
        if (networkInfo == null) {
            Intrinsics.throwNpe();
        }
        return networkInfo.isConnected();
    }

    public final boolean isWifiConnected(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Object systemService = context.getSystemService("connectivity");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
            }
            NetworkInfo networkInfo = ((ConnectivityManager) systemService).getNetworkInfo(1);
            if (networkInfo == null) {
                Intrinsics.throwNpe();
            }
            return networkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }
}
