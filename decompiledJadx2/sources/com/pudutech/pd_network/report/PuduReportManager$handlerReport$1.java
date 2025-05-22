package com.pudutech.pd_network.report;

import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.bean.ReportBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.report.PuduReportManager$handlerReport$1", m3970f = "PuduReportManager.kt", m3971i = {0, 1, 1, 1, 1, 2, 2}, m3972l = {161, 174, 199}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "networkConnected", "listByUpload", "bean", "$this$launch", "networkConnected"}, m3975s = {"L$0", "L$0", "Z$0", "L$1", "L$2", "L$0", "Z$0"})
/* loaded from: classes6.dex */
public final class PuduReportManager$handlerReport$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6846p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduReportManager$handlerReport$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduReportManager$handlerReport$1 puduReportManager$handlerReport$1 = new PuduReportManager$handlerReport$1(completion);
        puduReportManager$handlerReport$1.f6846p$ = (CoroutineScope) obj;
        return puduReportManager$handlerReport$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduReportManager$handlerReport$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:18|(6:21|(1:23)(1:31)|24|(3:26|27|28)(1:30)|29|19)|32|33|(2:36|34)|37|38|39|40|41|42|(1:51)|44|(6:46|(3:48|13|(0)(0))|42|(0)|44|(2:49|50)(0))(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(4:53|54|55|56) */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x019f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01a0, code lost:
    
        r8 = com.pudutech.pd_network.log.NetWorkLog.INSTANCE;
        r12 = com.pudutech.pd_network.report.PuduReportManager.INSTANCE;
        r12 = com.pudutech.pd_network.report.PuduReportManager.TAG;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, "TAG");
        r8.mo3279e(r12, "updateUploadState error " + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01c5, code lost:
    
        com.pudutech.pd_network.report.PuduReportManager.access$getDb$p(com.pudutech.pd_network.report.PuduReportManager.INSTANCE).deleteById(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01cf, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01d0, code lost:
    
        r2 = com.pudutech.pd_network.log.NetWorkLog.INSTANCE;
        r8 = com.pudutech.pd_network.report.PuduReportManager.INSTANCE;
        r8 = com.pudutech.pd_network.report.PuduReportManager.TAG;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, "TAG");
        r2.mo3279e(r8, "deleteById error " + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x021b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x00fa -> B:13:0x00d3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0219 -> B:38:0x0057). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00fc -> B:13:0x00d3). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        PuduReportManager$handlerReport$1 puduReportManager$handlerReport$1;
        String TAG;
        String TAG2;
        String TAG3;
        long j;
        String TAG4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    Iterator<ReportBean> it = (Iterator) this.L$3;
                    ReportBean reportBean = (ReportBean) this.L$2;
                    List<ReportBean> list = (List) this.L$1;
                    boolean z = this.Z$0;
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Object reportToCloud = obj;
                    Object obj3 = coroutine_suspended;
                    ReportBean next = reportBean;
                    PuduReportManager$handlerReport$1 puduReportManager$handlerReport$12 = this;
                    if (((Boolean) reportToCloud).booleanValue()) {
                        next.setUpload(1);
                    }
                    if (it.hasNext()) {
                        next = it.next();
                        PuduReportManager puduReportManager = PuduReportManager.INSTANCE;
                        puduReportManager$handlerReport$12.L$0 = coroutineScope2;
                        puduReportManager$handlerReport$12.Z$0 = z;
                        puduReportManager$handlerReport$12.L$1 = list;
                        puduReportManager$handlerReport$12.L$2 = next;
                        puduReportManager$handlerReport$12.L$3 = it;
                        puduReportManager$handlerReport$12.label = 2;
                        reportToCloud = puduReportManager.reportToCloud(next, puduReportManager$handlerReport$12);
                        if (reportToCloud == obj3) {
                            return obj3;
                        }
                        if (((Boolean) reportToCloud).booleanValue()) {
                        }
                        if (it.hasNext()) {
                            ArrayList arrayList = new ArrayList();
                            for (Object obj4 : list) {
                                if (Boxing.boxBoolean(((ReportBean) obj4).getUpload() == 1).booleanValue()) {
                                    arrayList.add(obj4);
                                }
                            }
                            ArrayList arrayList2 = arrayList;
                            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                            Iterator it2 = arrayList2.iterator();
                            while (it2.hasNext()) {
                                arrayList3.add(Boxing.boxLong(((ReportBean) it2.next()).getReportId()));
                            }
                            ArrayList arrayList4 = arrayList3;
                            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                            PuduReportManager puduReportManager2 = PuduReportManager.INSTANCE;
                            TAG4 = PuduReportManager.TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                            netWorkLog.mo3280i(TAG4, "handlerReport > ids = " + CollectionsKt.joinToString$default(arrayList4, null, null, null, 0, null, null, 63, null));
                            PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).updateUploadState(1, arrayList4);
                            puduReportManager$handlerReport$1 = puduReportManager$handlerReport$12;
                            boolean isConnect = z;
                            coroutineScope = coroutineScope2;
                            obj2 = obj3;
                            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                            PuduReportManager puduReportManager3 = PuduReportManager.INSTANCE;
                            TAG3 = PuduReportManager.TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                            netWorkLog2.mo3280i(TAG3, "handlerReport upload end");
                            PuduReportManager puduReportManager4 = PuduReportManager.INSTANCE;
                            j = PuduReportManager.UPLOAD_INTERVAL;
                            puduReportManager$handlerReport$1.L$0 = coroutineScope;
                            puduReportManager$handlerReport$1.Z$0 = isConnect;
                            puduReportManager$handlerReport$1.label = 3;
                            if (DelayKt.delay(j, puduReportManager$handlerReport$1) == obj2) {
                                return obj2;
                            }
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                                isConnect = PdNetworkManager.f10310INSTANCE.networkType().isConnect();
                                NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                                PuduReportManager puduReportManager5 = PuduReportManager.INSTANCE;
                                TAG = PuduReportManager.TAG;
                                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                                netWorkLog3.mo3280i(TAG, "handlerReport upload start networkConnected:" + isConnect);
                                if (isConnect) {
                                    List<ReportBean> list2 = PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).getList(PdNetConfig.INSTANCE.getEnvironment().toString(), null, 0, 0L);
                                    NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                                    PuduReportManager puduReportManager6 = PuduReportManager.INSTANCE;
                                    TAG2 = PuduReportManager.TAG;
                                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                                    netWorkLog4.mo3280i(TAG2, "handlerReport upload size " + list2.size());
                                    obj3 = obj2;
                                    puduReportManager$handlerReport$12 = puduReportManager$handlerReport$1;
                                    coroutineScope2 = coroutineScope;
                                    it = list2.iterator();
                                    z = isConnect;
                                    list = list2;
                                    if (it.hasNext()) {
                                    }
                                }
                                NetWorkLog netWorkLog22 = NetWorkLog.INSTANCE;
                                PuduReportManager puduReportManager32 = PuduReportManager.INSTANCE;
                                TAG3 = PuduReportManager.TAG;
                                Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                                netWorkLog22.mo3280i(TAG3, "handlerReport upload end");
                                PuduReportManager puduReportManager42 = PuduReportManager.INSTANCE;
                                j = PuduReportManager.UPLOAD_INTERVAL;
                                puduReportManager$handlerReport$1.L$0 = coroutineScope;
                                puduReportManager$handlerReport$1.Z$0 = isConnect;
                                puduReportManager$handlerReport$1.label = 3;
                                if (DelayKt.delay(j, puduReportManager$handlerReport$1) == obj2) {
                                }
                                if (CoroutineScopeKt.isActive(coroutineScope)) {
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z2 = this.Z$0;
                }
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6846p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        obj2 = coroutine_suspended;
        puduReportManager$handlerReport$1 = this;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
