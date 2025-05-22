package com.pudutech.pd_network.gateway;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.OnGatewayAction;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GetListReq;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import com.pudutech.pd_network.gateway.Api;
import com.pudutech.pd_network.gateway.GatewayComponent;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.NetDataUtils;
import com.pudutech.pd_network.utils.NetSpUtils;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GatewayComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.gateway.GatewayComponent$fillGateway$1", m3970f = "GatewayComponent.kt", m3971i = {0, 0, 0, 1, 1, 1, 2}, m3972l = {45, 46, 91}, m3973m = "invokeSuspend", m3974n = {"$this$launch", MqttServiceConstants.TRACE_EXCEPTION, TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch", MqttServiceConstants.TRACE_EXCEPTION, TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch"}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0"})
/* loaded from: classes6.dex */
public final class GatewayComponent$fillGateway$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6838p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GatewayComponent$fillGateway$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GatewayComponent$fillGateway$1 gatewayComponent$fillGateway$1 = new GatewayComponent$fillGateway$1(completion);
        gatewayComponent$fillGateway$1.f6838p$ = (CoroutineScope) obj;
        return gatewayComponent$fillGateway$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GatewayComponent$fillGateway$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0054: MOVE (r13 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:110:0x0053 */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e2 A[Catch: Exception -> 0x0147, TryCatch #2 {Exception -> 0x0147, blocks: (B:17:0x00b8, B:18:0x00d8, B:20:0x00e2, B:22:0x00ea, B:24:0x00f0, B:26:0x00f9, B:28:0x0101, B:29:0x0104, B:31:0x0111, B:32:0x0114, B:34:0x011f, B:35:0x0122, B:80:0x0126, B:81:0x0146, B:91:0x00d6), top: B:16:0x00b8 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x018f A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0068, B:86:0x014b, B:36:0x016f, B:38:0x018f, B:39:0x01b7, B:41:0x01c2, B:43:0x01ca, B:44:0x01cd, B:46:0x01da, B:47:0x01e6, B:49:0x01ec, B:51:0x01fa, B:53:0x01fd, B:61:0x0203, B:62:0x020e, B:78:0x0206, B:79:0x01b2), top: B:85:0x014b }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01c2 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0068, B:86:0x014b, B:36:0x016f, B:38:0x018f, B:39:0x01b7, B:41:0x01c2, B:43:0x01ca, B:44:0x01cd, B:46:0x01da, B:47:0x01e6, B:49:0x01ec, B:51:0x01fa, B:53:0x01fd, B:61:0x0203, B:62:0x020e, B:78:0x0206, B:79:0x01b2), top: B:85:0x014b }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0206 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0068, B:86:0x014b, B:36:0x016f, B:38:0x018f, B:39:0x01b7, B:41:0x01c2, B:43:0x01ca, B:44:0x01cd, B:46:0x01da, B:47:0x01e6, B:49:0x01ec, B:51:0x01fa, B:53:0x01fd, B:61:0x0203, B:62:0x020e, B:78:0x0206, B:79:0x01b2), top: B:85:0x014b }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b2 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0068, B:86:0x014b, B:36:0x016f, B:38:0x018f, B:39:0x01b7, B:41:0x01c2, B:43:0x01ca, B:44:0x01cd, B:46:0x01da, B:47:0x01e6, B:49:0x01ec, B:51:0x01fa, B:53:0x01fd, B:61:0x0203, B:62:0x020e, B:78:0x0206, B:79:0x01b2), top: B:85:0x014b }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0068 A[Catch: Exception -> 0x020f, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0068, B:86:0x014b, B:36:0x016f, B:38:0x018f, B:39:0x01b7, B:41:0x01c2, B:43:0x01ca, B:44:0x01cd, B:46:0x01da, B:47:0x01e6, B:49:0x01ec, B:51:0x01fa, B:53:0x01fd, B:61:0x0203, B:62:0x020e, B:78:0x0206, B:79:0x01b2), top: B:85:0x014b }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0275  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0272 -> B:5:0x0060). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        GatewayComponent$fillGateway$1 gatewayComponent$fillGateway$1;
        CoroutineScope coroutineScope3;
        GetListReq getListReq;
        Object obj3;
        CoroutineScope coroutineScope4;
        BaseResponse baseResponse;
        String str;
        ServiceGatewayConfig serviceGatewayConfig;
        String str2;
        ConcurrentLinkedQueue<OnGatewayAction> concurrentLinkedQueue;
        String str3;
        ServiceGatewayConfig serviceGatewayConfig2;
        ServiceGatewayConfig serviceGatewayConfig3;
        ConcurrentLinkedQueue<OnGatewayAction> concurrentLinkedQueue2;
        ServiceGatewayConfig serviceGatewayConfig4;
        String str4;
        ServiceGatewayConfig serviceGatewayConfig5;
        List<GatewayBean> list;
        boolean z;
        Api api;
        Api api2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            try {
            } catch (Exception e) {
                e = e;
                obj2 = coroutine_suspended;
                coroutineScope2 = coroutineScope;
                gatewayComponent$fillGateway$1 = this;
                try {
                    e.printStackTrace();
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent = GatewayComponent.INSTANCE;
                    str = GatewayComponent.TAG;
                    netWorkLog.mo3280i(str, "api.getList error " + e.getMessage());
                    serviceGatewayConfig = null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent2 = GatewayComponent.INSTANCE;
                    str2 = GatewayComponent.TAG;
                    netWorkLog2.mo3279e(str2, "fillServiceConfig error " + e2.getMessage());
                    GatewayComponent gatewayComponent3 = GatewayComponent.INSTANCE;
                    concurrentLinkedQueue = GatewayComponent.actionList;
                    for (OnGatewayAction onGatewayAction : concurrentLinkedQueue) {
                        if (e2 instanceof IOException) {
                            onGatewayAction.onException(e2);
                        } else {
                            onGatewayAction.onException(new IOException(e2));
                        }
                    }
                }
                NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                GatewayComponent gatewayComponent4 = GatewayComponent.INSTANCE;
                str3 = GatewayComponent.TAG;
                netWorkLog3.mo3280i(str3, "list from service = " + serviceGatewayConfig);
                GatewayComponent gatewayComponent5 = GatewayComponent.INSTANCE;
                if (serviceGatewayConfig != null) {
                }
                GatewayComponent.serviceConfigBean = serviceGatewayConfig;
                GatewayComponent gatewayComponent6 = GatewayComponent.INSTANCE;
                serviceGatewayConfig2 = GatewayComponent.serviceConfigBean;
                if (serviceGatewayConfig2 != null) {
                }
                if (e == null) {
                }
                throw e;
            }
            if (i == 1) {
                GetListReq getListReq2 = (GetListReq) this.L$2;
                Exception exc = (Exception) this.L$1;
                coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                getListReq = getListReq2;
                obj2 = coroutine_suspended;
                e = exc;
                obj3 = obj;
                gatewayComponent$fillGateway$1 = this;
                baseResponse = (BaseResponse) obj3;
                if (baseResponse.getCode() != 0) {
                }
                throw new IOException("获取服务器配置失败 reqBody = " + getListReq + " response = " + baseResponse);
            }
            if (i == 2) {
                GetListReq getListReq3 = (GetListReq) this.L$2;
                Exception exc2 = (Exception) this.L$1;
                coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                getListReq = getListReq3;
                obj2 = coroutine_suspended;
                e = exc2;
                Object obj4 = obj;
                gatewayComponent$fillGateway$1 = this;
                try {
                } catch (Exception e3) {
                    e = e3;
                    coroutineScope2 = coroutineScope3;
                    e.printStackTrace();
                    NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent7 = GatewayComponent.INSTANCE;
                    str = GatewayComponent.TAG;
                    netWorkLog4.mo3280i(str, "api.getList error " + e.getMessage());
                    serviceGatewayConfig = null;
                    NetWorkLog netWorkLog32 = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent42 = GatewayComponent.INSTANCE;
                    str3 = GatewayComponent.TAG;
                    netWorkLog32.mo3280i(str3, "list from service = " + serviceGatewayConfig);
                    GatewayComponent gatewayComponent52 = GatewayComponent.INSTANCE;
                    if (serviceGatewayConfig != null) {
                    }
                    GatewayComponent.serviceConfigBean = serviceGatewayConfig;
                    GatewayComponent gatewayComponent62 = GatewayComponent.INSTANCE;
                    serviceGatewayConfig2 = GatewayComponent.serviceConfigBean;
                    if (serviceGatewayConfig2 != null) {
                    }
                    if (e == null) {
                    }
                    throw e;
                }
                baseResponse = (BaseResponse) obj4;
                if (baseResponse.getCode() != 0 && (serviceGatewayConfig5 = (ServiceGatewayConfig) baseResponse.getData()) != null && (list = serviceGatewayConfig5.getList()) != null && (!list.isEmpty())) {
                    GatewayComponent gatewayComponent8 = GatewayComponent.INSTANCE;
                    Object data = baseResponse.getData();
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    gatewayComponent8.handlerData((ServiceGatewayConfig) data);
                    NetSpUtils netSpUtils = NetSpUtils.INSTANCE;
                    Object data2 = baseResponse.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    netSpUtils.putServiceConfigIsp((ServiceGatewayConfig) data2);
                    Object data3 = baseResponse.getData();
                    if (data3 == null) {
                        Intrinsics.throwNpe();
                    }
                    serviceGatewayConfig = (ServiceGatewayConfig) data3;
                    coroutineScope2 = coroutineScope3;
                    NetWorkLog netWorkLog322 = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent422 = GatewayComponent.INSTANCE;
                    str3 = GatewayComponent.TAG;
                    netWorkLog322.mo3280i(str3, "list from service = " + serviceGatewayConfig);
                    GatewayComponent gatewayComponent522 = GatewayComponent.INSTANCE;
                    if (serviceGatewayConfig != null) {
                        serviceGatewayConfig = NetSpUtils.INSTANCE.getServiceConfigWithIsp();
                        NetWorkLog netWorkLog5 = NetWorkLog.INSTANCE;
                        GatewayComponent gatewayComponent9 = GatewayComponent.INSTANCE;
                        str4 = GatewayComponent.TAG;
                        netWorkLog5.mo3280i(str4, "list from local = " + serviceGatewayConfig);
                    } else {
                        GatewayComponent gatewayComponent10 = GatewayComponent.INSTANCE;
                        GatewayComponent.SERVICE_GATEWAY = true;
                    }
                    GatewayComponent.serviceConfigBean = serviceGatewayConfig;
                    GatewayComponent gatewayComponent622 = GatewayComponent.INSTANCE;
                    serviceGatewayConfig2 = GatewayComponent.serviceConfigBean;
                    if (serviceGatewayConfig2 != null) {
                        GatewayComponent gatewayComponent11 = GatewayComponent.INSTANCE;
                        serviceGatewayConfig3 = GatewayComponent.serviceConfigBean;
                        if (serviceGatewayConfig3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!serviceGatewayConfig3.getList().isEmpty()) {
                            GatewayComponent gatewayComponent12 = GatewayComponent.INSTANCE;
                            concurrentLinkedQueue2 = GatewayComponent.actionList;
                            for (OnGatewayAction onGatewayAction2 : concurrentLinkedQueue2) {
                                GatewayComponent gatewayComponent13 = GatewayComponent.INSTANCE;
                                serviceGatewayConfig4 = GatewayComponent.serviceConfigBean;
                                if (serviceGatewayConfig4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                onGatewayAction2.onSuccess(serviceGatewayConfig4);
                            }
                            CoroutineScope coroutineScope5 = coroutineScope2;
                            gatewayComponent$fillGateway$1.L$0 = coroutineScope5;
                            gatewayComponent$fillGateway$1.label = 3;
                            if (DelayKt.delay(5000L, gatewayComponent$fillGateway$1) == obj2) {
                                return obj2;
                            }
                            coroutineScope2 = coroutineScope5;
                            GatewayComponent gatewayComponent14 = GatewayComponent.INSTANCE;
                            z = GatewayComponent.SERVICE_GATEWAY;
                            if (z) {
                                GatewayComponent gatewayComponent15 = GatewayComponent.INSTANCE;
                                GatewayComponent.serviceConfigBean = NetSpUtils.INSTANCE.getServiceConfigWithIsp();
                                e = (Exception) null;
                                try {
                                } catch (Exception e4) {
                                    e = e4;
                                    e.printStackTrace();
                                    NetWorkLog netWorkLog42 = NetWorkLog.INSTANCE;
                                    GatewayComponent gatewayComponent72 = GatewayComponent.INSTANCE;
                                    str = GatewayComponent.TAG;
                                    netWorkLog42.mo3280i(str, "api.getList error " + e.getMessage());
                                    serviceGatewayConfig = null;
                                    NetWorkLog netWorkLog3222 = NetWorkLog.INSTANCE;
                                    GatewayComponent gatewayComponent4222 = GatewayComponent.INSTANCE;
                                    str3 = GatewayComponent.TAG;
                                    netWorkLog3222.mo3280i(str3, "list from service = " + serviceGatewayConfig);
                                    GatewayComponent gatewayComponent5222 = GatewayComponent.INSTANCE;
                                    if (serviceGatewayConfig != null) {
                                    }
                                    GatewayComponent.serviceConfigBean = serviceGatewayConfig;
                                    GatewayComponent gatewayComponent6222 = GatewayComponent.INSTANCE;
                                    serviceGatewayConfig2 = GatewayComponent.serviceConfigBean;
                                    if (serviceGatewayConfig2 != null) {
                                    }
                                    if (e == null) {
                                    }
                                    throw e;
                                }
                                getListReq = new GetListReq(NetDataUtils.INSTANCE.pid(), NetDataUtils.INSTANCE.mac(), PdNetConfig.INSTANCE.getLanguage());
                                if (GatewayComponent.WhenMappings.$EnumSwitchMapping$0[PdNetConfig.INSTANCE.getDeviceType().ordinal()] != 1) {
                                    api2 = GatewayComponent.INSTANCE.getApi();
                                    gatewayComponent$fillGateway$1.L$0 = coroutineScope2;
                                    gatewayComponent$fillGateway$1.L$1 = e;
                                    gatewayComponent$fillGateway$1.L$2 = getListReq;
                                    gatewayComponent$fillGateway$1.label = 2;
                                    obj4 = Api.DefaultImpls.getDeviceList$default(api2, getListReq, null, gatewayComponent$fillGateway$1, 2, null);
                                    if (obj4 == obj2) {
                                        return obj2;
                                    }
                                    coroutineScope3 = coroutineScope2;
                                    baseResponse = (BaseResponse) obj4;
                                    if (baseResponse.getCode() != 0) {
                                    }
                                    throw new IOException("获取服务器配置失败 reqBody = " + getListReq + " response = " + baseResponse);
                                }
                                api = GatewayComponent.INSTANCE.getApi();
                                gatewayComponent$fillGateway$1.L$0 = coroutineScope2;
                                gatewayComponent$fillGateway$1.L$1 = e;
                                gatewayComponent$fillGateway$1.L$2 = getListReq;
                                gatewayComponent$fillGateway$1.label = 1;
                                obj3 = Api.DefaultImpls.getList$default(api, getListReq, null, gatewayComponent$fillGateway$1, 2, null);
                                if (obj3 == obj2) {
                                    return obj2;
                                }
                                coroutineScope3 = coroutineScope2;
                                baseResponse = (BaseResponse) obj3;
                                if (baseResponse.getCode() != 0) {
                                }
                                throw new IOException("获取服务器配置失败 reqBody = " + getListReq + " response = " + baseResponse);
                            }
                            GatewayComponent gatewayComponent16 = GatewayComponent.INSTANCE;
                            GatewayComponent.job = (Job) null;
                            return Unit.INSTANCE;
                        }
                    }
                    if (e == null) {
                        e = new IOException("获取不到网关列表:未知原因");
                    }
                    throw e;
                }
                throw new IOException("获取服务器配置失败 reqBody = " + getListReq + " response = " + baseResponse);
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope4 = this.f6838p$;
        }
        coroutineScope2 = coroutineScope4;
        obj2 = coroutine_suspended;
        gatewayComponent$fillGateway$1 = this;
        GatewayComponent gatewayComponent142 = GatewayComponent.INSTANCE;
        z = GatewayComponent.SERVICE_GATEWAY;
        if (z) {
        }
    }
}
