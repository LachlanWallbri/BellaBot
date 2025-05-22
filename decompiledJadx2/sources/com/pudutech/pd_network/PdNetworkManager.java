package com.pudutech.pd_network;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.GatewayPlace;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdNetworkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0002J!\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0012H\u0016¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016J\u0011\u0010*\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010+J)\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020.2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\t00¢\u0006\u0002\b2H\u0016J\b\u00103\u001a\u000204H\u0016J\u0018\u00105\u001a\u00020\t2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u000106H\u0016J\u0010\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005H\u0016J\u0010\u00109\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020&2\u0006\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0016J\u0018\u0010:\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0016J\u0018\u0010>\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0016J\u0010\u0010?\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0005H\u0016J\u0010\u0010@\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0011\u0010A\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0010\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0005H\u0016J\b\u0010D\u001a\u00020\tH\u0016J\u0010\u0010E\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020GH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkManager;", "Lcom/pudutech/pd_network/IPdNetworkManager;", "()V", "INSTANCE", "TAG", "", "abilities", "", "addAbilities", "", "ability", "addOnGatewayAction", "action", "Lcom/pudutech/pd_network/OnGatewayAction;", "check", "createService", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "crtEnvironment", "Lcom/pudutech/pd_network/bean/NetEnvironment;", RequestParameters.SUBRESOURCE_DELETE, "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "objectKey", "callback", "Lcom/pudutech/pd_network/OssCallback;", "download", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "downloadFile", "Ljava/io/File;", "gatewayPlace", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "getGateway", "Lcom/pudutech/pd_network/bean/GatewayBean;", "mGatewayName", "Lcom/pudutech/pd_network/bean/GatewayName;", "getGatewayExecute", "throwException", "", "hardID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "Lkotlin/ExtensionFunctionType;", "networkType", "Lcom/pudutech/pd_network/PdNetworkType;", "refreshGateway", "Lkotlin/Function0;", "refreshToken", "hostTag", "removeOnGatewayAction", "report", DevFoundOutputParams.PARAMS_GATEWAY_NAME, "apiStr", "data", "reportOrThrown", "resetBaseUrl", "setAbilities", "sn", "token", "host", "updateAbility", "upload", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetworkManager implements IPdNetworkManager {
    private static volatile IPdNetworkManager INSTANCE = null;

    /* renamed from: INSTANCE, reason: collision with other field name */
    public static final PdNetworkManager f10310INSTANCE = new PdNetworkManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private PdNetworkManager() {
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public void init(Context context, Function1<? super PdNetworkManagerBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        NetWorkLog.INSTANCE.mo3280i(TAG, "init " + INSTANCE);
        if (INSTANCE != null) {
            return;
        }
        synchronized (this) {
            if (INSTANCE == null) {
                PdNetworkManagerImpl pdNetworkManagerImpl = new PdNetworkManagerImpl();
                INSTANCE = pdNetworkManagerImpl;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
                pdNetworkManagerImpl.init(applicationContext, block);
                PdNetworkManagerImpl pdNetworkManagerImpl2 = pdNetworkManagerImpl;
            }
        }
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public <T> T createService(Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return (T) iPdNetworkManager.createService(clazz);
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public NetEnvironment crtEnvironment() {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.crtEnvironment();
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public void resetBaseUrl(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.resetBaseUrl(url);
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public Object hardID(Continuation<? super String> continuation) {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.hardID(continuation);
    }

    @Override // com.pudutech.pd_network.ISN
    /* renamed from: sn */
    public Object mo3303sn(Continuation<? super String> continuation) {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.mo3303sn(continuation);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void refreshGateway(Function0<Unit> block) {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.refreshGateway(block);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGateway(GatewayName mGatewayName) {
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.getGateway(mGatewayName);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGatewayExecute(GatewayName mGatewayName, boolean throwException) {
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.getGatewayExecute(mGatewayName, throwException);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void addOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.addOnGatewayAction(action);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void removeOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.removeOnGatewayAction(action);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayPlace gatewayPlace() {
        NetWorkLog.INSTANCE.mo3280i(TAG, "gatewayPlace:3");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.gatewayPlace();
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String refreshToken(String hostTag) {
        Intrinsics.checkParameterIsNotNull(hostTag, "hostTag");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.refreshToken(hostTag);
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String token(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.token(host);
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController upload(PdUploadConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.upload(config);
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController download(String url, File downloadFile) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.download(url, downloadFile);
    }

    @Override // com.pudutech.pd_network.IStorage
    public void delete(StorageBucketType bucketType, String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(bucketType, "bucketType");
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.delete(bucketType, objectKey, callback);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.report(url, data);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(GatewayName gatewayName, String apiStr, String data) {
        Intrinsics.checkParameterIsNotNull(gatewayName, "gatewayName");
        Intrinsics.checkParameterIsNotNull(apiStr, "apiStr");
        Intrinsics.checkParameterIsNotNull(data, "data");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.report(gatewayName, apiStr, data);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void reportOrThrown(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.reportOrThrown(url, data);
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public int abilities() {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.abilities();
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void addAbilities(int ability) {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.addAbilities(ability);
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void setAbilities(int ability) {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.setAbilities(ability);
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void updateAbility() {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        iPdNetworkManager.updateAbility();
    }

    @Override // com.pudutech.pd_network.INetworkType
    public PdNetworkType networkType() {
        check();
        IPdNetworkManager iPdNetworkManager = INSTANCE;
        if (iPdNetworkManager == null) {
            Intrinsics.throwNpe();
        }
        return iPdNetworkManager.networkType();
    }

    private final void check() {
        if (!(INSTANCE != null)) {
            throw new IllegalStateException("PdNetworkManager 尚未初始化,请在application里初始化后再使用\n\"\n    PdNetworkManager.init(this) {\n        environment = NetEnvironment.Test\n    }\n\"".toString());
        }
    }
}
