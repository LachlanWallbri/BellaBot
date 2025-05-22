package com.pudutech.shared.lib_syntime;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.provider.Settings;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemTimeSetting.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010 \u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u000bJ\u0016\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u000bJ\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\n\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/shared/lib_syntime/SystemTimeSetting;", "", "()V", "NTP_ALI_ORG_SERVER", "", "NTP_ALI_SERVER", "NTP_GOOGLE_ANDROID_SERVER", "NTP_GOOGLE_SERVER", "TAG", "TIME_FORMAT", "isNoAutoTime", "", "()Z", "isNoAutoTime$delegate", "Lkotlin/Lazy;", "noAutoTimeFile", "formatDate", TmpConstant.TYPE_VALUE_DATE, "Ljava/util/Date;", "pattern", "formatDateOne", "mills", "", "getTimeZoneId", "getTimeZoneName", "isConnected", "context", "Landroid/content/Context;", "isDateTimeAuto", "isTimeZoneAuto", "openAutoDateTime", "", "setAutoDateTime", "isOpenAutoDate", "setAutoTimeZone", "isOpenTimeZone", "setSystemCurTime", "lib_syntime_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SystemTimeSetting {
    public static final String NTP_ALI_ORG_SERVER = "cn.pool.ntp.org";
    public static final String NTP_ALI_SERVER = "ntp.aliyun.com";
    public static final String NTP_GOOGLE_ANDROID_SERVER = "time.android.com";
    public static final String NTP_GOOGLE_SERVER = "time.google.com";
    private static final String TAG = "SystemTimeSetting";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final SystemTimeSetting INSTANCE = new SystemTimeSetting();
    private static final String noAutoTimeFile = noAutoTimeFile;
    private static final String noAutoTimeFile = noAutoTimeFile;

    /* renamed from: isNoAutoTime$delegate, reason: from kotlin metadata */
    private static final Lazy isNoAutoTime = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.shared.lib_syntime.SystemTimeSetting$isNoAutoTime$2
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            SystemTimeSetting systemTimeSetting = SystemTimeSetting.INSTANCE;
            str = SystemTimeSetting.noAutoTimeFile;
            return new File(str).exists();
        }
    });

    public final boolean isNoAutoTime() {
        return ((Boolean) isNoAutoTime.getValue()).booleanValue();
    }

    private SystemTimeSetting() {
    }

    public final String getTimeZoneName() {
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        String displayName = timeZone.getDisplayName();
        Intrinsics.checkExpressionValueIsNotNull(displayName, "TimeZone.getDefault().displayName");
        return displayName;
    }

    public final String getTimeZoneId() {
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        String id = timeZone.getID();
        Intrinsics.checkExpressionValueIsNotNull(id, "TimeZone.getDefault().id");
        return id;
    }

    public final void setSystemCurTime(long mills) {
        try {
            Pdlog.m3273d(TAG, "setSystemCurTime() start syncTime = " + formatDateOne(mills) + "  localCurTime = " + formatDateOne(System.currentTimeMillis()));
            SystemClock.setCurrentTimeMillis(mills);
            Pdlog.m3273d(TAG, "setSystemCurTime() end syncTime = " + formatDateOne(mills) + "  localCurTime = " + formatDateOne(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "setSystemCurTime() exception ", Unit.INSTANCE);
        }
    }

    public final void openAutoDateTime(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (isNoAutoTime()) {
            Pdlog.m3273d(TAG, "openAutoDateTime() is noNeedAutoTime");
        } else {
            if (isDateTimeAuto(context)) {
                return;
            }
            setAutoDateTime(context, true);
        }
    }

    public final void setAutoDateTime(Context context, boolean isOpenAutoDate) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Settings.Global.putInt(context.getContentResolver(), "auto_time", isOpenAutoDate ? 1 : 0);
            Pdlog.m3273d(TAG, "setAutoDateTime isOpenAutoDate = " + isOpenAutoDate);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "setAutoDateTime() exception", Unit.INSTANCE);
        }
    }

    public final boolean isDateTimeAuto(Context context) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (Settings.Global.getInt(context.getContentResolver(), "auto_time") > 0) {
            z = true;
            Pdlog.m3273d(TAG, "isDateTimeAuto = " + z);
            return z;
        }
        z = false;
        Pdlog.m3273d(TAG, "isDateTimeAuto = " + z);
        return z;
    }

    public final void setAutoTimeZone(Context context, boolean isOpenTimeZone) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Settings.Global.putInt(context.getContentResolver(), "auto_time_zone", isOpenTimeZone ? 1 : 0);
        Pdlog.m3273d(TAG, "setAutoTimeZone = " + isOpenTimeZone);
    }

    public final boolean isTimeZoneAuto(Context context) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (Settings.Global.getInt(context.getContentResolver(), "auto_time_zone") > 0) {
            z = true;
            Pdlog.m3273d(TAG, "isTimeZoneAuto = " + z);
            return z;
        }
        z = false;
        Pdlog.m3273d(TAG, "isTimeZoneAuto = " + z);
        return z;
    }

    public final String formatDateOne(long mills) {
        return formatDate(new Date(mills), TIME_FORMAT);
    }

    public final String formatDateOne(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        return formatDate(date, TIME_FORMAT);
    }

    private final String formatDate(Date date, String pattern) {
        String format = new SimpleDateFormat(pattern).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(date)");
        return format;
    }

    public final boolean isConnected(Context context) {
        boolean z;
        Object systemService;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            systemService = context.getSystemService("connectivity");
        } catch (Exception e) {
            e = e;
            z = false;
        }
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        Intrinsics.checkExpressionValueIsNotNull(activeNetInfo, "activeNetInfo");
        z = activeNetInfo.isConnected();
        try {
            Pdlog.m3273d(TAG, "isConnected() connect =" + z);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            Pdlog.m3274e(TAG, "isConnected() ", Unit.INSTANCE);
            return z;
        }
        return z;
    }
}
