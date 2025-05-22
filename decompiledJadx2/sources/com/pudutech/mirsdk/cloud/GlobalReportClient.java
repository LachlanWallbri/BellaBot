package com.pudutech.mirsdk.cloud;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.log.ILog;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlobalReportClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/cloud/GlobalReportClient;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "init", "", "reportToCloud", "type", "name", "Lcom/pudutech/pd_network/bean/GatewayName;", NotificationCompat.CATEGORY_MESSAGE, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GlobalReportClient {
    private static Context context;
    public static final GlobalReportClient INSTANCE = new GlobalReportClient();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private GlobalReportClient() {
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
        PdNetworkManager.f10310INSTANCE.init(context2, new Function1<PdNetworkManagerBuilder, Unit>() { // from class: com.pudutech.mirsdk.cloud.GlobalReportClient$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PdNetworkManagerBuilder pdNetworkManagerBuilder) {
                invoke2(pdNetworkManagerBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PdNetworkManagerBuilder receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.setEnvironment(NetEnvironment.Product.INSTANCE);
                if (new File("/sdcard/TestServer").exists()) {
                    receiver.setEnvironment(NetEnvironment.Test.INSTANCE);
                }
                if (new File("/sdcard/PreTestServer").exists()) {
                    receiver.setEnvironment(NetEnvironment.PreTest.INSTANCE);
                }
                if (new File("/sdcard/DevTestServer").exists()) {
                    receiver.setEnvironment(NetEnvironment.Dev.INSTANCE);
                }
                receiver.setProxyLog(new ILog() { // from class: com.pudutech.mirsdk.cloud.GlobalReportClient$init$1.1
                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: d */
                    public void mo3278d(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3273d(tag, content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: e */
                    public void mo3279e(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3274e(tag, content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: i */
                    public void mo3280i(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3275i(tag, content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: w */
                    public void mo3281w(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3277w(tag, content);
                    }
                });
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void reportToCloud(String type, GatewayName name, String msg) {
        String str;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (Intrinsics.areEqual(name, GatewayName.ROBOT_COLL.INSTANCE)) {
            if (type.hashCode() == -331239923 && type.equals("battery")) {
                str = "/api/common/battery/collect/v1";
                if (!(str.length() != 0)) {
                    Pdlog.m3277w(TAG, "reportCloud reportUrl = " + str + ", not support!");
                    return;
                }
                Pdlog.m3273d(TAG, "global report url " + str);
                PdNetworkManager.f10310INSTANCE.report(name, str, msg);
                return;
            }
        } else if (!Intrinsics.areEqual(name, GatewayName.ROBOT_AI.INSTANCE) && !Intrinsics.areEqual(name, GatewayName.ROBOT_BIZ.INSTANCE) && !Intrinsics.areEqual(name, GatewayName.ROBOT_MQTT.INSTANCE) && !Intrinsics.areEqual(name, GatewayName.CLOUD_OAPI.INSTANCE) && !Intrinsics.areEqual(name, GatewayName.CLUSTERS_CFG.INSTANCE)) {
            Pdlog.m3277w(TAG, "reportCloud name = " + name + ", not support!");
            return;
        }
        str = "";
        if (!(str.length() != 0)) {
        }
    }
}
