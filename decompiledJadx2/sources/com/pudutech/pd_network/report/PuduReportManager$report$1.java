package com.pudutech.pd_network.report;

import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.PdNetworkManagerKt;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.HttpUrl;
import org.apache.http.HttpHost;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.report.PuduReportManager$report$1", m3970f = "PuduReportManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class PuduReportManager$report$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    final /* synthetic */ String $url;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6847p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PuduReportManager$report$1(String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$url = str;
        this.$data = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduReportManager$report$1 puduReportManager$report$1 = new PuduReportManager$report$1(this.$url, this.$data, completion);
        puduReportManager$report$1.f6847p$ = (CoroutineScope) obj;
        return puduReportManager$report$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduReportManager$report$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String TAG;
        String TAG2;
        String TAG3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = this.f6847p$;
            if (!StringsKt.contains$default((CharSequence) this.$url, (CharSequence) HttpHost.DEFAULT_SCHEME_NAME, false, 2, (Object) null)) {
                str = OSSConfig.PREFIX_HTTPS + this.$url;
            } else {
                str = this.$url;
            }
            HttpUrl parse = HttpUrl.Companion.parse(str);
            if (parse == null) {
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                PuduReportManager puduReportManager = PuduReportManager.INSTANCE;
                TAG = PuduReportManager.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                netWorkLog.mo3280i(TAG, "reportToDb.errorUrl " + this.$url);
                return Unit.INSTANCE;
            }
            final String host = parse.host();
            final String encodedPath = parse.encodedPath();
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            PuduReportManager puduReportManager2 = PuduReportManager.INSTANCE;
            TAG2 = PuduReportManager.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog2.mo3280i(TAG2, "report > " + host);
            NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
            PuduReportManager puduReportManager3 = PuduReportManager.INSTANCE;
            TAG3 = PuduReportManager.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            netWorkLog3.mo3280i(TAG3, "report > " + encodedPath);
            PdNetworkManagerKt.setOnGatewayAction(PdNetworkManager.f10310INSTANCE, new Function1<ServiceGatewayConfig, Unit>() { // from class: com.pudutech.pd_network.report.PuduReportManager$report$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServiceGatewayConfig serviceGatewayConfig) {
                    invoke2(serviceGatewayConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServiceGatewayConfig it) {
                    Object obj2;
                    String TAG4;
                    String name;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Iterator<T> it2 = it.getList().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj2 = null;
                            break;
                        } else {
                            obj2 = it2.next();
                            if (StringsKt.contains((CharSequence) ((GatewayBean) obj2).getHost(), (CharSequence) host, true)) {
                                break;
                            }
                        }
                    }
                    GatewayBean gatewayBean = (GatewayBean) obj2;
                    if (gatewayBean == null || (name = gatewayBean.getName()) == null) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                        PuduReportManager puduReportManager4 = PuduReportManager.INSTANCE;
                        TAG4 = PuduReportManager.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                        netWorkLog4.mo3280i(TAG4, "newReport.error 找不到对应的网关，抛弃该数据 > source " + it + ' ' + PuduReportManager$report$1.this.$url);
                        return;
                    }
                    GatewayName fromTag = GatewayName.INSTANCE.fromTag(name);
                    if (fromTag == null) {
                        Intrinsics.throwNpe();
                    }
                    PuduReportManager.INSTANCE.report(fromTag, encodedPath, PuduReportManager$report$1.this.$data);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
