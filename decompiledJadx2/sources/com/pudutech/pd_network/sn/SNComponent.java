package com.pudutech.pd_network.sn;

import android.content.Context;
import com.pudutech.pd_network.ISN;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetSNRes;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.NetDataUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: SNComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0011\u0010\u0018\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u001b\u001a\u00020\u0017J\u0011\u0010\u001c\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0019R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/pd_network/sn/SNComponent;", "Lcom/pudutech/pd_network/ISN;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "api", "Lcom/pudutech/pd_network/sn/Api;", "getApi", "()Lcom/pudutech/pd_network/sn/Api;", "api$delegate", "Lkotlin/Lazy;", "context", "Landroid/content/Context;", "fillSNJob", "Lkotlinx/coroutines/Job;", "mSN", "resumeList", "", "Lkotlinx/coroutines/CancellableContinuation;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "fillSN", "", "getSNFromNet", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "refreshSN", "sn", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SNComponent implements ISN {
    public static final SNComponent INSTANCE;
    private static final String TAG;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api;
    private static Context context;
    private static Job fillSNJob;
    private static String mSN;
    private static final List<CancellableContinuation<String>> resumeList;
    private static final CoroutineScope scope;

    private final Api getApi() {
        return (Api) api.getValue();
    }

    static {
        SNComponent sNComponent = new SNComponent();
        INSTANCE = sNComponent;
        TAG = sNComponent.getClass().getSimpleName();
        api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.sn.SNComponent$api$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Api invoke() {
                return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
            }
        });
        resumeList = new ArrayList();
        mSN = "";
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    private SNComponent() {
    }

    public static final /* synthetic */ Context access$getContext$p(SNComponent sNComponent) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public static final /* synthetic */ List access$getResumeList$p(SNComponent sNComponent) {
        return resumeList;
    }

    public final void init(Context context2) {
        String str;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "init > ");
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        context = applicationContext;
        SNBean sn = SNUtils.INSTANCE.getSN();
        if (sn == null || (str = sn.getSn()) == null) {
            str = "";
        }
        mSN = str;
        fillSN();
    }

    private final void fillSN() {
        Job launch$default;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        StringBuilder sb = new StringBuilder();
        sb.append("fillSN > ");
        Job job = fillSNJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        netWorkLog.mo3280i(TAG2, sb.toString());
        Job job2 = fillSNJob;
        if (job2 == null || !job2.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new SNComponent$fillSN$1(null), 3, null);
            fillSNJob = launch$default;
        }
    }

    @Override // com.pudutech.pd_network.ISN
    /* renamed from: sn */
    public Object mo3303sn(Continuation<? super String> continuation) {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "sn > " + mSN);
        if (mSN.length() > 0) {
            return mSN;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        access$getResumeList$p(INSTANCE).add(cancellableContinuationImpl);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final void refreshSN() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "refreshSN > ");
        mSN = "";
        SNUtils.INSTANCE.deleteSN();
        fillSN();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object getSNFromNet(Continuation<? super String> continuation) {
        SNComponent$getSNFromNet$1 sNComponent$getSNFromNet$1;
        int i;
        GetSNRes getSNRes;
        String pid;
        if (continuation instanceof SNComponent$getSNFromNet$1) {
            sNComponent$getSNFromNet$1 = (SNComponent$getSNFromNet$1) continuation;
            if ((sNComponent$getSNFromNet$1.label & Integer.MIN_VALUE) != 0) {
                sNComponent$getSNFromNet$1.label -= Integer.MIN_VALUE;
                Object obj = sNComponent$getSNFromNet$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = sNComponent$getSNFromNet$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Api api2 = getApi();
                    Map<String, String> mapOf = MapsKt.mapOf(TuplesKt.m3968to("mac", NetDataUtils.INSTANCE.mac()));
                    sNComponent$getSNFromNet$1.L$0 = this;
                    sNComponent$getSNFromNet$1.label = 1;
                    obj = api2.getSN(mapOf, sNComponent$getSNFromNet$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                BaseResponse baseResponse = (BaseResponse) obj;
                return (baseResponse != null || (getSNRes = (GetSNRes) baseResponse.getData()) == null || (pid = getSNRes.getPid()) == null) ? "" : pid;
            }
        }
        sNComponent$getSNFromNet$1 = new SNComponent$getSNFromNet$1(this, continuation);
        Object obj2 = sNComponent$getSNFromNet$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = sNComponent$getSNFromNet$1.label;
        if (i != 0) {
        }
        BaseResponse baseResponse2 = (BaseResponse) obj2;
        if (baseResponse2 != null) {
        }
    }
}
