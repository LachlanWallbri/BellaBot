package com.pudutech.antichannelconflict;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.antichannelconflict.escape.util.ProductType;
import com.pudutech.antichannelconflict.location.util.LocationType;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.utils.NetDataUtils;
import defpackage.CHARSET;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AntiConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010:\u001a\u00020\u0006H\u0016R&\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\t\"\u0004\b,\u0010\u000bR$\u0010.\u001a\u00020-2\u0006\u0010\u0005\u001a\u00020-@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\n 4*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\t\"\u0004\b6\u0010\u000bR\u001a\u00107\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\t\"\u0004\b9\u0010\u000b¨\u0006;"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/AntiConfig;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ES6Iterator.VALUE_PROPERTY, "", "amap_key", "getAmap_key", "()Ljava/lang/String;", "setAmap_key", "(Ljava/lang/String;)V", "autoLock", "", "getAutoLock", "()Z", "setAutoLock", "(Z)V", "autoUpdate", "getAutoUpdate", "setAutoUpdate", "defaultLocateType", "Lcom/pudutech/antichannelconflict/location/util/LocationType;", "getDefaultLocateType", "()Lcom/pudutech/antichannelconflict/location/util/LocationType;", "setDefaultLocateType", "(Lcom/pudutech/antichannelconflict/location/util/LocationType;)V", "locate_retry_times", "", "getLocate_retry_times", "()I", "setLocate_retry_times", "(I)V", "mac", "getMac", "setMac", TypedValues.Cycle.S_WAVE_PERIOD, "", "getPeriod", "()J", "setPeriod", "(J)V", "pid", "getPid", "setPid", "Lcom/pudutech/antichannelconflict/escape/util/ProductType;", "product_type", "getProduct_type$AntiChannelConflict_release", "()Lcom/pudutech/antichannelconflict/escape/util/ProductType;", "setProduct_type$AntiChannelConflict_release", "(Lcom/pudutech/antichannelconflict/escape/util/ProductType;)V", "softVersion", "kotlin.jvm.PlatformType", "getSoftVersion", "setSoftVersion", SpeechConstant.ISV_VID, "getVid", "setVid", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AntiConfig {
    private String amap_key;
    private boolean autoLock;
    private boolean autoUpdate;
    private LocationType defaultLocateType;
    private int locate_retry_times;
    private String mac;
    private long period;
    private String pid;
    private ProductType product_type;
    private String softVersion;
    private String vid;

    public AntiConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mac = NetDataUtils.INSTANCE.mac();
        this.softVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        this.autoLock = true;
        this.vid = "1e0e";
        this.pid = "9001";
        this.locate_retry_times = 5;
        this.amap_key = "nhOv9n3vOSQFqIeIncyOkka9eTiwbkWM4/bEc2KwM869ZvnLUDy7rYWVIE+QWXH2";
        this.defaultLocateType = LocationType.LOCATE_FROM_HARDWARE;
        this.period = 10800000L;
        this.autoUpdate = true;
        this.product_type = ProductType.HLS_VSLAM;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final String getSoftVersion() {
        return this.softVersion;
    }

    public final void setSoftVersion(String str) {
        this.softVersion = str;
    }

    public final boolean getAutoLock() {
        return this.autoLock;
    }

    public final void setAutoLock(boolean z) {
        this.autoLock = z;
    }

    public final String getVid() {
        return this.vid;
    }

    public final void setVid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.vid = str;
    }

    public final String getPid() {
        return this.pid;
    }

    public final void setPid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pid = str;
    }

    public final int getLocate_retry_times() {
        return this.locate_retry_times;
    }

    public final void setLocate_retry_times(int i) {
        this.locate_retry_times = i;
    }

    public final void setAmap_key(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.amap_key = CHARSET.encryptTry(value);
        Pdlog.m3273d("AntiConfig", "amap_key SET  " + this.amap_key);
    }

    public final String getAmap_key() {
        return CHARSET.decryptTry(this.amap_key);
    }

    public final LocationType getDefaultLocateType() {
        return this.defaultLocateType;
    }

    public final void setDefaultLocateType(LocationType locationType) {
        Intrinsics.checkParameterIsNotNull(locationType, "<set-?>");
        this.defaultLocateType = locationType;
    }

    public final long getPeriod() {
        return this.period;
    }

    public final void setPeriod(long j) {
        this.period = j;
    }

    public final boolean getAutoUpdate() {
        return this.autoUpdate;
    }

    public final void setAutoUpdate(boolean z) {
        this.autoUpdate = z;
    }

    /* renamed from: getProduct_type$AntiChannelConflict_release, reason: from getter */
    public final ProductType getProduct_type() {
        return this.product_type;
    }

    public final void setProduct_type$AntiChannelConflict_release(ProductType value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Pdlog.m3273d("AntiConfig", "product_type SET " + value);
        this.product_type = value;
    }

    public String toString() {
        return "AntiConfig(mac=" + this.mac + ",softVersion=" + this.softVersion + ",autoLock=" + this.autoLock + ",vid=" + this.vid + ",pid=" + this.vid + ",locate_retry_times=" + this.locate_retry_times + ",defaultLocateType=" + this.defaultLocateType + ",period=" + this.period + ",autoUpdate=" + this.autoUpdate + ",product_type=" + this.product_type + ')';
    }
}
