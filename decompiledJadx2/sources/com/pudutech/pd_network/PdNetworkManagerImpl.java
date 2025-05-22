package com.pudutech.pd_network;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.pudutech.pd_network.IGateway;
import com.pudutech.pd_network.ability.SupportAbilityComponent;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.GatewayPlace;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.gateway.GatewayComponent;
import com.pudutech.pd_network.log.ILog;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.IReportClient;
import com.pudutech.pd_network.report.PuduReportManager;
import com.pudutech.pd_network.sn.SNComponent;
import com.pudutech.pd_network.storage.StorageComponent;
import com.pudutech.pd_network.utils.ExtKt;
import com.pudutech.pd_network.utils.NetDataUtils;
import com.pudutech.pd_network.utils.NetSpUtils;
import com.pudutech.pd_network.verify.AutoVerifyComponent;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* compiled from: PdNetworkManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0017H\u0096\u0001J\u0011\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017H\u0096\u0001J\u0011\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0096\u0001J!\u0010\u001e\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0!H\u0016¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020$H\u0016J!\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020*H\u0096\u0001J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020/H\u0096\u0001J\t\u00100\u001a\u000201H\u0096\u0001J\u0013\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u000205H\u0096\u0001J\u001b\u00106\u001a\u0002032\u0006\u00104\u001a\u0002052\b\b\u0002\u00107\u001a\u000208H\u0096\u0001J\u0011\u00109\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010:J)\u0010;\u001a\u00020\u00192\u0006\u0010<\u001a\u00020\u000f2\u0017\u0010=\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00190>¢\u0006\u0002\b?H\u0016J\t\u0010@\u001a\u00020AH\u0096\u0001J\u001b\u0010B\u001a\u00020\u00192\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010CH\u0096\u0001J\u0011\u0010D\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010F\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0096\u0001J!\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u0002052\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000bH\u0096\u0001J\u0019\u0010G\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000bH\u0096\u0001J\u0019\u0010K\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000bH\u0096\u0001J\u0010\u0010L\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000bH\u0016J\u0011\u0010M\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017H\u0096\u0001J\u0011\u0010N\u001a\u00020\u000bH\u0096Aø\u0001\u0000¢\u0006\u0002\u0010:J\u0011\u0010O\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000bH\u0096\u0001J\t\u0010P\u001a\u00020\u0019H\u0096\u0001J\u0011\u0010Q\u001a\u00020,2\u0006\u0010R\u001a\u00020SH\u0096\u0001R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006T"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkManagerImpl;", "Lcom/pudutech/pd_network/IPdNetworkManager;", "Lcom/pudutech/pd_network/IGateway;", "Lcom/pudutech/pd_network/IAutoVerify;", "Lcom/pudutech/pd_network/IStorage;", "Lcom/pudutech/pd_network/report/IReportClient;", "Lcom/pudutech/pd_network/ISupportAbility;", "Lcom/pudutech/pd_network/INetworkType;", "Lcom/pudutech/pd_network/ISN;", "()V", "TAG", "", "client", "Lretrofit2/Retrofit;", "context", "Landroid/content/Context;", "managerBuilder", "Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "getManagerBuilder", "()Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "setManagerBuilder", "(Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;)V", "abilities", "", "addAbilities", "", "ability", "addOnGatewayAction", "action", "Lcom/pudutech/pd_network/OnGatewayAction;", "createService", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "crtEnvironment", "Lcom/pudutech/pd_network/bean/NetEnvironment;", RequestParameters.SUBRESOURCE_DELETE, "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "objectKey", "callback", "Lcom/pudutech/pd_network/OssCallback;", "download", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "downloadFile", "Ljava/io/File;", "gatewayPlace", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "getGateway", "Lcom/pudutech/pd_network/bean/GatewayBean;", "mGatewayName", "Lcom/pudutech/pd_network/bean/GatewayName;", "getGatewayExecute", "throwException", "", "hardID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "mContext", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "networkType", "Lcom/pudutech/pd_network/PdNetworkType;", "refreshGateway", "Lkotlin/Function0;", "refreshToken", "host", "removeOnGatewayAction", "report", DevFoundOutputParams.PARAMS_GATEWAY_NAME, "apiStr", "data", "reportOrThrown", "resetBaseUrl", "setAbilities", "sn", "token", "updateAbility", "upload", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetworkManagerImpl implements IPdNetworkManager, IGateway, IAutoVerify, IStorage, IReportClient, ISupportAbility, INetworkType, ISN {
    private final /* synthetic */ GatewayComponent $$delegate_0 = GatewayComponent.INSTANCE;
    private final /* synthetic */ AutoVerifyComponent $$delegate_1 = AutoVerifyComponent.INSTANCE;
    private final /* synthetic */ StorageComponent $$delegate_2 = StorageComponent.INSTANCE;
    private final /* synthetic */ PuduReportManager $$delegate_3 = PuduReportManager.INSTANCE;
    private final /* synthetic */ SupportAbilityComponent $$delegate_4 = SupportAbilityComponent.INSTANCE;
    private final /* synthetic */ NetworkTypeComponent $$delegate_5 = NetworkTypeComponent.INSTANCE;
    private final /* synthetic */ SNComponent $$delegate_6 = SNComponent.INSTANCE;
    private final String TAG = "PdNetworkManagerImpl";
    private Retrofit client;
    private Context context;
    public PdNetworkManagerBuilder managerBuilder;

    @Override // com.pudutech.pd_network.ISupportAbility
    public int abilities() {
        return this.$$delegate_4.abilities();
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void addAbilities(int ability) {
        this.$$delegate_4.addAbilities(ability);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void addOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.$$delegate_0.addOnGatewayAction(action);
    }

    @Override // com.pudutech.pd_network.IStorage
    public void delete(StorageBucketType bucketType, String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(bucketType, "bucketType");
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.$$delegate_2.delete(bucketType, objectKey, callback);
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController download(String url, File downloadFile) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
        return this.$$delegate_2.download(url, downloadFile);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayPlace gatewayPlace() {
        return this.$$delegate_0.gatewayPlace();
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGateway(GatewayName mGatewayName) throws Exception {
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        return this.$$delegate_0.getGateway(mGatewayName);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGatewayExecute(GatewayName mGatewayName, boolean throwException) {
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        return this.$$delegate_0.getGatewayExecute(mGatewayName, throwException);
    }

    @Override // com.pudutech.pd_network.INetworkType
    public PdNetworkType networkType() {
        return this.$$delegate_5.networkType();
    }

    @Override // com.pudutech.pd_network.IGateway
    public void refreshGateway(Function0<Unit> block) {
        this.$$delegate_0.refreshGateway(block);
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String refreshToken(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        return this.$$delegate_1.refreshToken(host);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void removeOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.$$delegate_0.removeOnGatewayAction(action);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(GatewayName gatewayName, String apiStr, String data) {
        Intrinsics.checkParameterIsNotNull(gatewayName, "gatewayName");
        Intrinsics.checkParameterIsNotNull(apiStr, "apiStr");
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.$$delegate_3.report(gatewayName, apiStr, data);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.$$delegate_3.report(url, data);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void reportOrThrown(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.$$delegate_3.reportOrThrown(url, data);
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void setAbilities(int ability) {
        this.$$delegate_4.setAbilities(ability);
    }

    @Override // com.pudutech.pd_network.ISN
    /* renamed from: sn */
    public Object mo3303sn(Continuation<? super String> continuation) {
        return this.$$delegate_6.mo3303sn(continuation);
    }

    @Override // com.pudutech.pd_network.IAutoVerify
    public String token(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        return this.$$delegate_1.token(host);
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void updateAbility() {
        this.$$delegate_4.updateAbility();
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController upload(PdUploadConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        return this.$$delegate_2.upload(config);
    }

    public final PdNetworkManagerBuilder getManagerBuilder() {
        PdNetworkManagerBuilder pdNetworkManagerBuilder = this.managerBuilder;
        if (pdNetworkManagerBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        return pdNetworkManagerBuilder;
    }

    public final void setManagerBuilder(PdNetworkManagerBuilder pdNetworkManagerBuilder) {
        Intrinsics.checkParameterIsNotNull(pdNetworkManagerBuilder, "<set-?>");
        this.managerBuilder = pdNetworkManagerBuilder;
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public void init(Context mContext, Function1<? super PdNetworkManagerBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Context applicationContext = mContext.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "mContext.applicationContext");
        this.context = applicationContext;
        PdNetworkManagerBuilder pdNetworkManagerBuilder = new PdNetworkManagerBuilder();
        block.invoke(pdNetworkManagerBuilder);
        ILog proxyLog = pdNetworkManagerBuilder.getProxyLog();
        if (proxyLog != null) {
            NetWorkLog.INSTANCE.setLog(proxyLog);
        }
        this.managerBuilder = pdNetworkManagerBuilder;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        PdNetworkManagerBuilder pdNetworkManagerBuilder2 = this.managerBuilder;
        if (pdNetworkManagerBuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        this.client = ClientKt.normalClient(context, pdNetworkManagerBuilder2.getClient());
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("init ");
        PdNetworkManagerBuilder pdNetworkManagerBuilder3 = this.managerBuilder;
        if (pdNetworkManagerBuilder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        sb.append(pdNetworkManagerBuilder3);
        netWorkLog.mo3280i(str, sb.toString());
        NetworkTypeComponent networkTypeComponent = NetworkTypeComponent.INSTANCE;
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        networkTypeComponent.init(context2);
        PdNetConfig pdNetConfig = PdNetConfig.INSTANCE;
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        PdNetworkManagerBuilder pdNetworkManagerBuilder4 = this.managerBuilder;
        if (pdNetworkManagerBuilder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        pdNetConfig.init(context3, pdNetworkManagerBuilder4);
        NetSpUtils netSpUtils = NetSpUtils.INSTANCE;
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        netSpUtils.init(context4, PdNetConfig.INSTANCE.getBaseGatewayBean().getHost());
        NetDataUtils netDataUtils = NetDataUtils.INSTANCE;
        Context context5 = this.context;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        netDataUtils.init(context5);
        NetDataUtils netDataUtils2 = NetDataUtils.INSTANCE;
        PdNetworkManagerBuilder pdNetworkManagerBuilder5 = this.managerBuilder;
        if (pdNetworkManagerBuilder5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        netDataUtils2.forceMac(pdNetworkManagerBuilder5.getForceMac());
        PuduReportManager puduReportManager = PuduReportManager.INSTANCE;
        Context context6 = this.context;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        puduReportManager.init(context6);
        GatewayComponent.INSTANCE.init();
        AutoVerifyComponent autoVerifyComponent = AutoVerifyComponent.INSTANCE;
        Context context7 = this.context;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        PdNetworkManagerBuilder pdNetworkManagerBuilder6 = this.managerBuilder;
        if (pdNetworkManagerBuilder6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        autoVerifyComponent.init(context7, pdNetworkManagerBuilder6.getDeviceType());
        SNComponent sNComponent = SNComponent.INSTANCE;
        Context context8 = this.context;
        if (context8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        sNComponent.init(context8);
        StorageComponent storageComponent = StorageComponent.INSTANCE;
        Context context9 = this.context;
        if (context9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        storageComponent.init(context9);
        SupportAbilityComponent supportAbilityComponent = SupportAbilityComponent.INSTANCE;
        PdNetworkManagerBuilder pdNetworkManagerBuilder7 = this.managerBuilder;
        if (pdNetworkManagerBuilder7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        supportAbilityComponent.init(pdNetworkManagerBuilder7.getAbility());
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public <T> T createService(Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "createNormalService " + clazz.getSimpleName());
        Retrofit retrofit = this.client;
        if (retrofit == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        return (T) retrofit.create(clazz);
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public NetEnvironment crtEnvironment() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "crtEnvironment");
        PdNetworkManagerBuilder pdNetworkManagerBuilder = this.managerBuilder;
        if (pdNetworkManagerBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        return pdNetworkManagerBuilder.getEnvironment();
    }

    @Override // com.pudutech.pd_network.IPdNetworkManager
    public void resetBaseUrl(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "resetBaseUrl > " + url);
        PdNetworkManagerBuilder pdNetworkManagerBuilder = this.managerBuilder;
        if (pdNetworkManagerBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("managerBuilder");
        }
        pdNetworkManagerBuilder.setForceBaseUrl(url);
        PdNetConfig.INSTANCE.baseUrl(url);
        NetSpUtils.INSTANCE.refreshSp(url);
        IGateway.DefaultImpls.refreshGateway$default(this, null, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.pudutech.pd_network.IPdNetworkManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object hardID(Continuation<? super String> continuation) {
        PdNetworkManagerImpl$hardID$1 pdNetworkManagerImpl$hardID$1;
        int i;
        StringBuilder sb;
        if (continuation instanceof PdNetworkManagerImpl$hardID$1) {
            pdNetworkManagerImpl$hardID$1 = (PdNetworkManagerImpl$hardID$1) continuation;
            if ((pdNetworkManagerImpl$hardID$1.label & Integer.MIN_VALUE) != 0) {
                pdNetworkManagerImpl$hardID$1.label -= Integer.MIN_VALUE;
                Object obj = pdNetworkManagerImpl$hardID$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdNetworkManagerImpl$hardID$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(NetDataUtils.INSTANCE.mac());
                    NetDataUtils netDataUtils = NetDataUtils.INSTANCE;
                    pdNetworkManagerImpl$hardID$1.L$0 = this;
                    pdNetworkManagerImpl$hardID$1.L$1 = sb2;
                    pdNetworkManagerImpl$hardID$1.label = 1;
                    Object btMac = netDataUtils.btMac(pdNetworkManagerImpl$hardID$1);
                    if (btMac == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sb = sb2;
                    obj = btMac;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    sb = (StringBuilder) pdNetworkManagerImpl$hardID$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                sb.append((String) obj);
                String md5 = ExtKt.md5(sb.toString());
                return md5 == null ? md5 : "";
            }
        }
        pdNetworkManagerImpl$hardID$1 = new PdNetworkManagerImpl$hardID$1(this, continuation);
        Object obj2 = pdNetworkManagerImpl$hardID$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdNetworkManagerImpl$hardID$1.label;
        if (i != 0) {
        }
        sb.append((String) obj2);
        String md52 = ExtKt.md5(sb.toString());
        if (md52 == null) {
        }
    }
}
