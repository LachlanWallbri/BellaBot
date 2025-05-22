package com.pudutech.event_tracking.component;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.pudutech.event_tracking.TrackDeviceInfo;
import com.pudutech.pd_network.PdNetworkManager;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BaseDataComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u00106\u001a\u0002072\n\u00108\u001a\u000609j\u0002`:2\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\bH\u0002J \u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020\bH\u0002J\u0006\u0010B\u001a\u00020\u000eJ\b\u0010C\u001a\u00020\u000eH\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u0011\u0010%\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010R\u001a\u0010'\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0010\"\u0004\b)\u0010\u0012R\u001a\u0010*\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0010\"\u0004\b,\u0010\u0012R\u001a\u0010-\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0010\"\u0004\b/\u0010\u0012R\u001a\u00100\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0010\"\u0004\b2\u0010\u0012R\u001a\u00103\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0010\"\u0004\b5\u0010\u0012¨\u0006D"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/BaseDataComponent;", "", "context", "Landroid/content/Context;", "deviceInfo", "Lcom/pudutech/event_tracking/TrackDeviceInfo;", "(Landroid/content/Context;Lcom/pudutech/event_tracking/TrackDeviceInfo;)V", "appId", "", "getAppId", "()I", "setAppId", "(I)V", "appLanguage", "", "getAppLanguage", "()Ljava/lang/String;", "setAppLanguage", "(Ljava/lang/String;)V", "appName", "getAppName", "setAppName", "density", "getDensity", "setDensity", "deviceBrand", "getDeviceBrand", "setDeviceBrand", "deviceModel", "getDeviceModel", "setDeviceModel", "deviceType", "getDeviceType", "setDeviceType", "mChannel", "getMChannel", "setMChannel", "osVersion", "getOsVersion", "packageName", "getPackageName", "setPackageName", "screenResolution", "getScreenResolution", "setScreenResolution", "timeZone", "getTimeZone", "setTimeZone", "versionCode", "getVersionCode", "setVersionCode", "versionName", "getVersionName", "setVersionName", "appendNumber", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "count", ES6Iterator.VALUE_PROPERTY, "createGmtOffsetString", "includeGmt", "", "includeMinuteSeparator", "offsetMillis", "fetchNetwork", "getCurrentTimeZone", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BaseDataComponent {
    private int appId;
    private String appLanguage;
    private String appName;
    private String density;
    private String deviceBrand;
    private String deviceModel;
    private String deviceType;
    private String mChannel;
    private final String osVersion;
    private String packageName;
    private String screenResolution;
    private String timeZone;
    private String versionCode;
    private String versionName;

    public BaseDataComponent(Context context, TrackDeviceInfo deviceInfo) {
        PackageInfo packageInfo;
        String valueOf;
        String str;
        String str2 = "common";
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        this.packageName = "";
        this.versionName = "";
        this.appName = "";
        this.versionCode = "";
        this.mChannel = "";
        this.appLanguage = "";
        this.deviceBrand = "";
        this.deviceModel = "";
        this.timeZone = "";
        this.density = "";
        this.screenResolution = "";
        this.deviceType = "";
        Locale locale = Locale.getDefault();
        this.appLanguage = locale.getLanguage() + Soundex.SILENT_MARKER + locale.getCountry();
        String str3 = Build.BRAND;
        Intrinsics.checkExpressionValueIsNotNull(str3, "Build.BRAND");
        this.deviceBrand = str3;
        String str4 = Build.MODEL;
        Intrinsics.checkExpressionValueIsNotNull(str4, "Build.MODEL");
        this.deviceModel = str4;
        this.deviceType = deviceInfo.getDeviceType();
        this.timeZone = getCurrentTimeZone();
        this.appId = deviceInfo.getAppId();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.content.pm.PackageManager");
        }
        this.packageName = deviceInfo.getAppPackage();
        try {
            packageInfo = packageManager.getPackageInfo(this.packageName, 0);
        } catch (Exception unused) {
            packageInfo = null;
        }
        this.versionName = (packageInfo == null || (str = packageInfo.versionName) == null) ? "" : str;
        this.appName = deviceInfo.getAppName();
        this.versionCode = (packageInfo == null || (valueOf = String.valueOf(packageInfo.versionCode)) == null) ? "" : valueOf;
        try {
            Bundle bundle = packageManager.getApplicationInfo(this.packageName, 128).metaData;
            if (bundle != null) {
                String string = bundle.getString("channel");
                if (string != null) {
                    str2 = string;
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        this.mChannel = str2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }
        ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics);
        this.density = String.valueOf(displayMetrics.density);
        StringBuilder sb = new StringBuilder();
        sb.append(displayMetrics.widthPixels);
        sb.append('x');
        sb.append(displayMetrics.heightPixels);
        this.screenResolution = sb.toString();
        this.osVersion = String.valueOf(Build.VERSION.SDK_INT);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final void setPackageName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.packageName = str;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setVersionName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.versionName = str;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final void setAppName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appName = str;
    }

    public final String getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.versionCode = str;
    }

    public final String getMChannel() {
        return this.mChannel;
    }

    public final void setMChannel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mChannel = str;
    }

    public final String getAppLanguage() {
        return this.appLanguage;
    }

    public final void setAppLanguage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appLanguage = str;
    }

    public final String getDeviceBrand() {
        return this.deviceBrand;
    }

    public final void setDeviceBrand(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceBrand = str;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final void setDeviceModel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceModel = str;
    }

    public final String getTimeZone() {
        return this.timeZone;
    }

    public final void setTimeZone(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.timeZone = str;
    }

    public final String getDensity() {
        return this.density;
    }

    public final void setDensity(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.density = str;
    }

    public final String getScreenResolution() {
        return this.screenResolution;
    }

    public final void setScreenResolution(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.screenResolution = str;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceType = str;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final void setAppId(int i) {
        this.appId = i;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final String fetchNetwork() {
        String simpleName = PdNetworkManager.f10310INSTANCE.networkType().getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "PdNetworkManager.network…pe().javaClass.simpleName");
        return simpleName;
    }

    private final String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(tz, "tz");
        return createGmtOffsetString(true, true, tz.getRawOffset());
    }

    private final String createGmtOffsetString(boolean includeGmt, boolean includeMinuteSeparator, int offsetMillis) {
        char c;
        int i = offsetMillis / 60000;
        if (i < 0) {
            c = Soundex.SILENT_MARKER;
            i = -i;
        } else {
            c = '+';
        }
        StringBuilder sb = new StringBuilder(9);
        if (includeGmt) {
            sb.append("GMT");
        }
        sb.append(c);
        appendNumber(sb, 2, i / 60);
        if (includeMinuteSeparator) {
            sb.append(':');
        }
        appendNumber(sb, 2, i % 60);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    private final void appendNumber(StringBuilder builder, int count, int value) {
        String valueOf = String.valueOf(value);
        int length = count - valueOf.length();
        for (int i = 0; i < length; i++) {
            builder.append('0');
        }
        builder.append(valueOf);
    }
}
