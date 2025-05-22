package com.pudutech.pd_network.verify;

import android.content.Context;
import com.pudutech.pd_network.IAutoVerify;
import com.pudutech.pd_network.IAutoVerifyInternal;
import com.pudutech.pd_network.bean.DeviceType;
import com.pudutech.pd_network.gateway.GatewayComponent;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.ProcessUtil;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AutoVerifyComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/pd_network/verify/AutoVerifyComponent;", "Lcom/pudutech/pd_network/IAutoVerify;", "()V", "TAG", "", "appType", "deviceType", "Lcom/pudutech/pd_network/bean/DeviceType;", "packageName", "verifyExecuteMap", "Ljava/util/HashMap;", "Lcom/pudutech/pd_network/IAutoVerifyInternal;", "Lkotlin/collections/HashMap;", "getAndFillExecute", "host", "init", "", "context", "Landroid/content/Context;", "refreshToken", "token", "verifyKey", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutoVerifyComponent implements IAutoVerify {
    public static final AutoVerifyComponent INSTANCE = new AutoVerifyComponent();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String appType = "";
    private static DeviceType deviceType = DeviceType.Robot;
    private static String packageName = "";
    private static final HashMap<String, IAutoVerifyInternal> verifyExecuteMap = new HashMap<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeviceType.values().length];

        static {
            $EnumSwitchMapping$0[DeviceType.Robot.ordinal()] = 1;
            $EnumSwitchMapping$0[DeviceType.Other.ordinal()] = 2;
        }
    }

    private AutoVerifyComponent() {
    }

    public final void init(Context context, DeviceType deviceType2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceType2, "deviceType");
        String packageName2 = context.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName2, "context.packageName");
        packageName = packageName2;
        appType = context.getPackageName() + ":" + ProcessUtil.INSTANCE.getCurrentProcessName(context);
        deviceType = deviceType2;
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String refreshToken(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        NetWorkLog.INSTANCE.mo3280i(TAG, "refreshToken > " + host);
        return getAndFillExecute(host).refreshToken();
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String token(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        return getAndFillExecute(host).getMToken();
    }

    private final String verifyKey(String host) {
        return host + ':' + deviceType + ':' + appType;
    }

    private final synchronized IAutoVerifyInternal getAndFillExecute(String host) {
        IAutoVerifyInternal iAutoVerifyInternal;
        RobotVerifyExecute robotVerifyExecute;
        String host2AuthHost = GatewayComponent.INSTANCE.host2AuthHost(host);
        if (!StringsKt.contains$default((CharSequence) host2AuthHost, (CharSequence) "https", false, 2, (Object) null)) {
            host2AuthHost = OSSConfig.PREFIX_HTTPS + host2AuthHost;
        }
        String verifyKey = verifyKey(host2AuthHost);
        iAutoVerifyInternal = verifyExecuteMap.get(verifyKey);
        if (iAutoVerifyInternal == null) {
            NetWorkLog.INSTANCE.mo3280i(TAG, "getAndFillExecute >host:" + host + " authHost:" + host2AuthHost + ' ');
            int i = WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
            if (i == 1) {
                robotVerifyExecute = new RobotVerifyExecute(host2AuthHost, appType);
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                robotVerifyExecute = new DeviceVerifyExecute(host2AuthHost, appType);
            }
            iAutoVerifyInternal = robotVerifyExecute;
            verifyExecuteMap.put(verifyKey, iAutoVerifyInternal);
        }
        return iAutoVerifyInternal;
    }
}
