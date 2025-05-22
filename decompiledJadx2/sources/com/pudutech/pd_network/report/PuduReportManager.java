package com.pudutech.pd_network.report;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.bean.ReportBean;
import com.pudutech.pd_network.report.dao.IReportDao;
import com.pudutech.pd_network.report.dao.ReportDaoImpl;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import okhttp3.HttpUrl;
import retrofit2.Response;

/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\b\u0010$\u001a\u00020\"H\u0002J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0011J \u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0016J\u0018\u0010'\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0016J\u0018\u0010-\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0016J\u0019\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020 H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/pd_network/report/PuduReportManager;", "Lcom/pudutech/pd_network/report/IReportClient;", "()V", "MAX_NUMS", "", "MAX_UN_UPLOAD_NUMS", "TAG", "", "kotlin.jvm.PlatformType", "UPLOAD_INTERVAL", "api", "Lcom/pudutech/pd_network/report/Api;", "getApi", "()Lcom/pudutech/pd_network/report/Api;", "api$delegate", "Lkotlin/Lazy;", "context", "Landroid/content/Context;", "db", "Lcom/pudutech/pd_network/report/dao/IReportDao;", "isHandlerRemove", "Ljava/util/concurrent/atomic/AtomicBoolean;", "reportJob", "Lkotlinx/coroutines/Job;", "getReportJob", "()Lkotlinx/coroutines/Job;", "setReportJob", "(Lkotlinx/coroutines/Job;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "fixUrl", "bean", "Lcom/pudutech/pd_network/report/bean/ReportBean;", "handlerData", "", "handlerDelete", "handlerReport", "init", "mContext", "report", DevFoundOutputParams.PARAMS_GATEWAY_NAME, "Lcom/pudutech/pd_network/bean/GatewayName;", "apiStr", "data", "url", "reportOrThrown", "reportToCloud", "", AIUIConstant.KEY_CONTENT, "(Lcom/pudutech/pd_network/report/bean/ReportBean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PuduReportManager implements IReportClient {
    public static final PuduReportManager INSTANCE;
    private static final long MAX_NUMS;
    private static final long MAX_UN_UPLOAD_NUMS;
    private static final String TAG;
    private static long UPLOAD_INTERVAL;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api;
    private static Context context;
    private static IReportDao db;
    private static AtomicBoolean isHandlerRemove;
    private static Job reportJob;
    private static final CoroutineScope scope;

    private final Api getApi() {
        return (Api) api.getValue();
    }

    static {
        PuduReportManager puduReportManager = new PuduReportManager();
        INSTANCE = puduReportManager;
        TAG = puduReportManager.getClass().getSimpleName();
        MAX_UN_UPLOAD_NUMS = MAX_UN_UPLOAD_NUMS;
        MAX_NUMS = MAX_NUMS;
        UPLOAD_INTERVAL = 15000L;
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        isHandlerRemove = new AtomicBoolean(false);
        api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.report.PuduReportManager$api$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Api invoke() {
                return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
            }
        });
    }

    private PuduReportManager() {
    }

    public static final /* synthetic */ IReportDao access$getDb$p(PuduReportManager puduReportManager) {
        IReportDao iReportDao = db;
        if (iReportDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        }
        return iReportDao;
    }

    public final void init(Context mContext) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        Context applicationContext = mContext.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "mContext.applicationContext");
        context = applicationContext;
        ReportDaoImpl.Companion companion = ReportDaoImpl.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        db = companion.getDb$pd_network_release(context2);
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3278d(TAG2, "init");
        if (PdNetConfig.INSTANCE.getBuilder().getHandlerOlderReportData()) {
            HandlerOldReportData handlerOldReportData = HandlerOldReportData.INSTANCE;
            Context context3 = context;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            handlerOldReportData.handler(context3);
        }
        handlerReport();
        if (!Intrinsics.areEqual(PdNetConfig.INSTANCE.getEnvironment(), NetEnvironment.Product.INSTANCE)) {
            UPLOAD_INTERVAL = 5000L;
        }
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "reportToDb > url:" + url + " data:" + data + ' ');
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduReportManager$report$1(url, data, null), 3, null);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void reportOrThrown(String url, String data) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(data, "data");
        report(url, data);
    }

    @Override // com.pudutech.pd_network.report.IReportClient
    public void report(GatewayName gatewayName, String apiStr, String data) {
        Intrinsics.checkParameterIsNotNull(gatewayName, "gatewayName");
        Intrinsics.checkParameterIsNotNull(apiStr, "apiStr");
        Intrinsics.checkParameterIsNotNull(data, "data");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "reportToDb > gatewayName:" + gatewayName + " apiStr:" + apiStr + " data:" + data + ' ');
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduReportManager$report$2(data, gatewayName, apiStr, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlerData() {
        IReportDao iReportDao = db;
        if (iReportDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        }
        long count = iReportDao.getCount(0);
        IReportDao iReportDao2 = db;
        if (iReportDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        }
        long count2 = iReportDao2.getCount(1);
        if (count > MAX_UN_UPLOAD_NUMS && PdNetworkManager.f10310INSTANCE.networkType().isConnect()) {
            handlerReport();
        }
        if (count + count2 <= MAX_NUMS || isHandlerRemove.get()) {
            return;
        }
        handlerDelete();
    }

    private final void handlerDelete() {
        isHandlerRemove.set(true);
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduReportManager$handlerDelete$1(null), 3, null);
    }

    public final Job getReportJob() {
        return reportJob;
    }

    public final void setReportJob(Job job) {
        reportJob = job;
    }

    private final void handlerReport() {
        Job launch$default;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        StringBuilder sb = new StringBuilder();
        sb.append("handlerReport ");
        Job job = reportJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        netWorkLog.mo3278d(TAG2, sb.toString());
        Job job2 = reportJob;
        if (job2 == null || !job2.isActive()) {
            Job job3 = reportJob;
            if (job3 != null) {
                Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(scope, ThreadPoolDispatcherKt.newSingleThreadContext("ReportManagerThread"), null, new PuduReportManager$handlerReport$1(null), 2, null);
            reportJob = launch$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(9:5|6|7|(1:(2:10|11)(2:20|21))(3:22|23|(1:25))|12|13|(1:15)|16|17))|28|6|7|(0)(0)|12|13|(0)|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00f7, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00f8, code lost:
    
        r10.printStackTrace();
        r11 = com.pudutech.pd_network.log.NetWorkLog.INSTANCE;
        r0 = com.pudutech.pd_network.report.PuduReportManager.TAG;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, "TAG");
        r11.mo3279e(r0, "reportToCloud.error : " + r10.getMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object reportToCloud(ReportBean reportBean, Continuation<? super Boolean> continuation) {
        PuduReportManager$reportToCloud$1 puduReportManager$reportToCloud$1;
        int i;
        Response response;
        if (continuation instanceof PuduReportManager$reportToCloud$1) {
            puduReportManager$reportToCloud$1 = (PuduReportManager$reportToCloud$1) continuation;
            if ((puduReportManager$reportToCloud$1.label & Integer.MIN_VALUE) != 0) {
                puduReportManager$reportToCloud$1.label -= Integer.MIN_VALUE;
                Object obj = puduReportManager$reportToCloud$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = puduReportManager$reportToCloud$1.label;
                boolean z = false;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    netWorkLog.mo3280i(TAG2, "reportToCloud content : " + reportBean);
                    JobKt.ensureActive(puduReportManager$reportToCloud$1.get$context());
                    String fixUrl = fixUrl(reportBean);
                    NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                    String TAG3 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                    netWorkLog2.mo3279e(TAG3, "reportToCloud url : " + fixUrl);
                    Map<String, Object> jsonMap = GsonUtils.gsonToMaps(reportBean.getData());
                    Api api2 = getApi();
                    Intrinsics.checkExpressionValueIsNotNull(jsonMap, "jsonMap");
                    puduReportManager$reportToCloud$1.L$0 = this;
                    puduReportManager$reportToCloud$1.L$1 = reportBean;
                    puduReportManager$reportToCloud$1.L$2 = fixUrl;
                    puduReportManager$reportToCloud$1.L$3 = jsonMap;
                    puduReportManager$reportToCloud$1.label = 1;
                    obj = api2.report(jsonMap, fixUrl, puduReportManager$reportToCloud$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                response = (Response) obj;
                NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                String TAG4 = TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                netWorkLog3.mo3279e(TAG4, "reportToCloud.res : code = " + response.code());
                NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                String TAG5 = TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
                netWorkLog4.mo3279e(TAG5, "reportToCloud.res : body = " + ((BaseResponse) response.body()));
                if (response.code() < 500) {
                    z = true;
                }
                return Boxing.boxBoolean(z);
            }
        }
        puduReportManager$reportToCloud$1 = new PuduReportManager$reportToCloud$1(this, continuation);
        Object obj2 = puduReportManager$reportToCloud$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = puduReportManager$reportToCloud$1.label;
        boolean z2 = false;
        if (i != 0) {
        }
        response = (Response) obj2;
        NetWorkLog netWorkLog32 = NetWorkLog.INSTANCE;
        String TAG42 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG42, "TAG");
        netWorkLog32.mo3279e(TAG42, "reportToCloud.res : code = " + response.code());
        NetWorkLog netWorkLog42 = NetWorkLog.INSTANCE;
        String TAG52 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG52, "TAG");
        netWorkLog42.mo3279e(TAG52, "reportToCloud.res : body = " + ((BaseResponse) response.body()));
        if (response.code() < 500) {
        }
        return Boxing.boxBoolean(z2);
    }

    private final String fixUrl(ReportBean bean) {
        String host;
        GatewayName fromTag = GatewayName.INSTANCE.fromTag(bean.getHost());
        if (fromTag == null) {
            throw new IOException("找不到对应的host " + bean.getHost());
        }
        GatewayBean gateway = PdNetworkManager.f10310INSTANCE.getGateway(fromTag);
        if (gateway == null || (host = gateway.getHost()) == null) {
            throw new IOException("找不到对应的GATEWAY " + fromTag);
        }
        return new HttpUrl.Builder().host(StringsKt.replace$default(StringsKt.replace$default(host, OSSConfig.PREFIX_HTTPS, "", false, 4, (Object) null), "http://", "", false, 4, (Object) null)).encodedPath(bean.getApi()).scheme("https").build().toString();
    }
}
