package com.pudutech.bumblebee.presenter.information_system_task;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: InformationSystemConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemConfig;", "", "()V", "KEY_IP", "", "KEY_PORT", "TAG", "context", "Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "ip", "getIp", "()Ljava/lang/String;", "setIp", "(Ljava/lang/String;)V", "port", "getPort", "setPort", "checkHost", "", "getInformationSystemHost", "init", "", "isIpv4", "ipAddress", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InformationSystemConfig {
    private static Context context;
    public static final InformationSystemConfig INSTANCE = new InformationSystemConfig();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String KEY_IP = KEY_IP;
    private static final String KEY_IP = KEY_IP;
    private static final String KEY_PORT = KEY_PORT;
    private static final String KEY_PORT = KEY_PORT;
    private static volatile String ip = "";
    private static volatile String port = "";

    private InformationSystemConfig() {
    }

    public final String getIp() {
        return ip;
    }

    public final void setIp(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        ip = value;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        SpUtils.set(context2, KEY_IP, value);
    }

    public final String getPort() {
        return port;
    }

    public final void setPort(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        port = value;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        SpUtils.set(context2, KEY_PORT, value);
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
        setIp(SpUtils.get(context2, KEY_IP, ""));
        setPort(SpUtils.get(context2, KEY_PORT, "7001"));
    }

    public final String getInformationSystemHost() {
        String str = (ip + ":") + port;
        Pdlog.m3273d(TAG, "getInformationSystemHost " + str);
        return str;
    }

    public final boolean checkHost() {
        try {
            int parseInt = Integer.parseInt(port);
            return isIpv4(ip) && parseInt > 1 && parseInt < 65535;
        } catch (Exception unused) {
            return false;
        }
    }

    private final boolean isIpv4(String ipAddress) {
        return Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(ipAddress).matches();
    }
}
