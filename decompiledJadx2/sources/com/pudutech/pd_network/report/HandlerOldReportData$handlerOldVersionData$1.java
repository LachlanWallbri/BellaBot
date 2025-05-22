package com.pudutech.pd_network.report;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.OKHttpClient;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.bean.OldReportBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandlerOldReportData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.report.HandlerOldReportData$handlerOldVersionData$1", m3970f = "HandlerOldReportData.kt", m3971i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, m3972l = {77, 103}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "historyFile", "$this$launch", "historyFile", "history", "stream", "count", "$this$forEach$iv", "element$iv", "it"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8"})
/* loaded from: classes6.dex */
public final class HandlerOldReportData$handlerOldVersionData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6843p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerOldReportData$handlerOldVersionData$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HandlerOldReportData$handlerOldVersionData$1 handlerOldReportData$handlerOldVersionData$1 = new HandlerOldReportData$handlerOldVersionData$1(this.$context, completion);
        handlerOldReportData$handlerOldVersionData$1.f6843p$ = (CoroutineScope) obj;
        return handlerOldReportData$handlerOldVersionData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HandlerOldReportData$handlerOldVersionData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:(1:(1:(7:5|6|(4:9|(3:11|12|(2:14|15)(1:17))(1:19)|18|7)|20|21|22|23)(2:24|25))(1:26))(2:61|(2:63|64)(2:65|(2:67|68)(2:69|(2:76|77)(1:75))))|37|38|40|41|42|43|(5:45|6|(1:7)|20|21)|22|23) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:1|(1:(1:(7:5|6|(4:9|(3:11|12|(2:14|15)(1:17))(1:19)|18|7)|20|21|22|23)(2:24|25))(1:26))(2:61|(2:63|64)(2:65|(2:67|68)(2:69|(2:76|77)(1:75))))|27|(3:30|(2:32|33)(1:34)|28)|35|36|37|38|40|41|42|43|(5:45|6|(1:7)|20|21)|22|23|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0138, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0139, code lost:
    
        r12 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0140, code lost:
    
        r10 = com.pudutech.pd_network.log.NetWorkLog.INSTANCE;
        r13 = com.pudutech.pd_network.report.HandlerOldReportData.INSTANCE;
        r13 = com.pudutech.pd_network.report.HandlerOldReportData.TAG;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, "TAG");
        r10.mo3279e(r13, "read history file error: " + r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x015f, code lost:
    
        if (r12 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0161, code lost:
    
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0164, code lost:
    
        r0 = r9;
        r10 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0135, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x020c, code lost:
    
        if (r10 != null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x020e, code lost:
    
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0211, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x013f, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x01b1  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String TAG;
        Object obj2;
        File file;
        CoroutineScope coroutineScope;
        String TAG2;
        String TAG3;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        String TAG4;
        HistoryBody historyBody;
        HandlerOldReportData$handlerOldVersionData$1 handlerOldReportData$handlerOldVersionData$1;
        Iterator it;
        Object obj3;
        Ref.IntRef intRef;
        Iterable iterable;
        File file2;
        FileInputStream fileInputStream3;
        CoroutineScope coroutineScope2;
        String TAG5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope3 = this.f6843p$;
                if (!PdNetConfig.INSTANCE.isMainProcess()) {
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    HandlerOldReportData handlerOldReportData = HandlerOldReportData.INSTANCE;
                    TAG3 = HandlerOldReportData.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                    netWorkLog.mo3278d(TAG3, "handlerOldVersionData isMainProcess =  " + PdNetConfig.INSTANCE.isMainProcess());
                    return Unit.INSTANCE;
                }
                if (ContextCompat.checkSelfPermission(this.$context, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                    HandlerOldReportData handlerOldReportData2 = HandlerOldReportData.INSTANCE;
                    TAG2 = HandlerOldReportData.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    netWorkLog2.mo3278d(TAG2, "handlerOldVersionData WRITE_EXTERNAL_STORAGE");
                    return Unit.INSTANCE;
                }
                File file3 = new File("/sdcard/history_path.json");
                if (!file3.exists() || !file3.isFile() || file3.length() <= 0) {
                    NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                    HandlerOldReportData handlerOldReportData3 = HandlerOldReportData.INSTANCE;
                    TAG = HandlerOldReportData.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    netWorkLog3.mo3278d(TAG, "handlerOldVersionData historyFile not exists");
                    return Unit.INSTANCE;
                }
                obj2 = coroutine_suspended;
                file = file3;
                coroutineScope = coroutineScope3;
            } else {
                if (i != 1) {
                    if (i == 2) {
                        Object obj4 = this.L$7;
                        it = (Iterator) this.L$6;
                        iterable = (Iterable) this.L$5;
                        intRef = (Ref.IntRef) this.L$4;
                        fileInputStream3 = (FileInputStream) this.L$3;
                        historyBody = (HistoryBody) this.L$2;
                        file2 = (File) this.L$1;
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        obj3 = coroutine_suspended;
                        handlerOldReportData$handlerOldVersionData$1 = this;
                        while (it.hasNext()) {
                            Object next = it.next();
                            HistoryContent historyContent = (HistoryContent) next;
                            HandlerOldReportData.INSTANCE.report(new OldReportBean(0L, 0L, historyContent.getData(), historyContent.getUrl(), 0, 19, null));
                            intRef.element++;
                            if (intRef.element % 5 == 0) {
                                handlerOldReportData$handlerOldVersionData$1.L$0 = coroutineScope2;
                                handlerOldReportData$handlerOldVersionData$1.L$1 = file2;
                                handlerOldReportData$handlerOldVersionData$1.L$2 = historyBody;
                                handlerOldReportData$handlerOldVersionData$1.L$3 = fileInputStream3;
                                handlerOldReportData$handlerOldVersionData$1.L$4 = intRef;
                                handlerOldReportData$handlerOldVersionData$1.L$5 = iterable;
                                handlerOldReportData$handlerOldVersionData$1.L$6 = it;
                                handlerOldReportData$handlerOldVersionData$1.L$7 = next;
                                handlerOldReportData$handlerOldVersionData$1.L$8 = historyContent;
                                handlerOldReportData$handlerOldVersionData$1.label = 2;
                                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, handlerOldReportData$handlerOldVersionData$1) == obj3) {
                                    return obj3;
                                }
                            }
                        }
                        file = file2;
                        file.renameTo(new File("/sdcard/history_path_cache.json"));
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                File file4 = (File) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                file = file4;
            }
            fileInputStream2 = new FileInputStream(file);
            Object fromJson = new Gson().fromJson((Reader) new BufferedReader(new InputStreamReader(fileInputStream2)), (Class<Object>) HistoryBody.class);
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "Gson().fromJson(reader, HistoryBody::class.java)");
            HistoryBody historyBody2 = (HistoryBody) fromJson;
            fileInputStream2.close();
            NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
            HandlerOldReportData handlerOldReportData4 = HandlerOldReportData.INSTANCE;
            TAG4 = HandlerOldReportData.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
            netWorkLog4.mo3280i(TAG4, "history = " + historyBody2.getSize());
            Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 0;
            ArrayList<HistoryContent> history = historyBody2.getHistory();
            if (history != null) {
                ArrayList<HistoryContent> arrayList = history;
                Object obj5 = obj2;
                historyBody = historyBody2;
                handlerOldReportData$handlerOldVersionData$1 = this;
                it = arrayList.iterator();
                obj3 = obj5;
                CoroutineScope coroutineScope4 = coroutineScope;
                intRef = intRef2;
                iterable = arrayList;
                file2 = file;
                fileInputStream3 = fileInputStream2;
                coroutineScope2 = coroutineScope4;
                while (it.hasNext()) {
                }
                file = file2;
            }
            file.renameTo(new File("/sdcard/history_path_cache.json"));
            return Unit.INSTANCE;
        } catch (Throwable th) {
            th = th;
            fileInputStream2 = fileInputStream;
        }
        while (!PdNetworkManager.f10310INSTANCE.networkType().isConnect()) {
            NetWorkLog netWorkLog5 = NetWorkLog.INSTANCE;
            HandlerOldReportData handlerOldReportData5 = HandlerOldReportData.INSTANCE;
            TAG5 = HandlerOldReportData.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
            netWorkLog5.mo3278d(TAG5, "handlerOldVersionData no net connect");
            this.L$0 = coroutineScope;
            this.L$1 = file;
            this.label = 1;
            if (DelayKt.delay(5000L, this) == obj2) {
                return obj2;
            }
        }
        HandlerOldReportData handlerOldReportData6 = HandlerOldReportData.INSTANCE;
        HandlerOldReportData.client = OKHttpClient.getInstance4Https(this.$context, HttpsServiceType.Cloud, null, null, false);
        HistoryBody historyBody3 = new HistoryBody(0, null, 3, null);
        fileInputStream = (FileInputStream) null;
    }
}
