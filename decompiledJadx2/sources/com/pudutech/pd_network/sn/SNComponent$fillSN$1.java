package com.pudutech.pd_network.sn;

import android.util.Log;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SNComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.sn.SNComponent$fillSN$1", m3970f = "SNComponent.kt", m3971i = {0, 1}, m3972l = {43, 58}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
public final class SNComponent$fillSN$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6851p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SNComponent$fillSN$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SNComponent$fillSN$1 sNComponent$fillSN$1 = new SNComponent$fillSN$1(completion);
        sNComponent$fillSN$1.f6851p$ = (CoroutineScope) obj;
        return sNComponent$fillSN$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SNComponent$fillSN$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:53|(3:54|55|56)|21|22|(6:24|(6:27|28|29|31|32|25)|34|35|36|(1:38)(5:39|7|(3:11|(1:13)(1:48)|(3:15|16|(1:18)(4:20|21|22|(2:40|41)(0))))|49|50))(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e5, code lost:
    
        r12 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007f A[Catch: Exception -> 0x00e5, TryCatch #3 {Exception -> 0x00e5, blocks: (B:22:0x007b, B:24:0x007f, B:25:0x00b5, B:27:0x00bb, B:35:0x00d3, B:40:0x00dd, B:41:0x00e4), top: B:21:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0120 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00dd A[Catch: Exception -> 0x00e5, TryCatch #3 {Exception -> 0x00e5, blocks: (B:22:0x007b, B:24:0x007f, B:25:0x00b5, B:27:0x00bb, B:35:0x00d3, B:40:0x00dd, B:41:0x00e4), top: B:21:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0066  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0121 -> B:7:0x0048). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String TAG;
        CoroutineScope coroutineScope;
        Exception e;
        CoroutineScope coroutineScope2;
        Object obj2;
        SNComponent$fillSN$1 sNComponent$fillSN$1;
        String TAG2;
        Object obj3;
        SNComponent$fillSN$1 sNComponent$fillSN$12;
        String str;
        Job job;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f6851p$;
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            SNComponent sNComponent = SNComponent.INSTANCE;
            TAG = SNComponent.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "fillSN.launch");
            coroutineScope = coroutineScope3;
        } else if (i == 1) {
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                coroutineScope2 = coroutineScope4;
                obj2 = coroutine_suspended;
                sNComponent$fillSN$1 = this;
            } catch (Exception e2) {
                e = e2;
                coroutineScope2 = coroutineScope4;
                obj2 = coroutine_suspended;
                sNComponent$fillSN$1 = this;
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                SNComponent sNComponent2 = SNComponent.INSTANCE;
                TAG2 = SNComponent.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                netWorkLog2.mo3279e(TAG2, "fillSN.error " + Log.getStackTraceString(e));
                obj3 = obj2;
                coroutineScope = coroutineScope2;
                sNComponent$fillSN$1.L$0 = coroutineScope;
                sNComponent$fillSN$1.label = 2;
                if (DelayKt.delay(5000L, sNComponent$fillSN$1) != obj3) {
                }
            }
            String str3 = (String) obj;
            if (str3 == null) {
                String upperCase = str3.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                SNUtils sNUtils = SNUtils.INSTANCE;
                String packageName = SNComponent.access$getContext$p(SNComponent.INSTANCE).getPackageName();
                Intrinsics.checkExpressionValueIsNotNull(packageName, "context.packageName");
                sNUtils.saveSN(upperCase, packageName, String.valueOf(System.currentTimeMillis()));
                SNComponent sNComponent3 = SNComponent.INSTANCE;
                SNComponent.mSN = upperCase;
                for (CancellableContinuation cancellableContinuation : SNComponent.access$getResumeList$p(SNComponent.INSTANCE)) {
                    try {
                        SNComponent sNComponent4 = SNComponent.INSTANCE;
                        str = SNComponent.mSN;
                        Result.Companion companion = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m4510constructorimpl(str));
                    } catch (Exception unused) {
                    }
                }
                SNComponent.access$getResumeList$p(SNComponent.INSTANCE).clear();
                obj3 = obj2;
                coroutineScope = coroutineScope2;
                sNComponent$fillSN$1.L$0 = coroutineScope;
                sNComponent$fillSN$1.label = 2;
                if (DelayKt.delay(5000L, sNComponent$fillSN$1) != obj3) {
                    return obj3;
                }
                SNComponent$fillSN$1 sNComponent$fillSN$13 = sNComponent$fillSN$1;
                coroutine_suspended = obj3;
                sNComponent$fillSN$12 = sNComponent$fillSN$13;
                SNComponent sNComponent5 = SNComponent.INSTANCE;
                job = SNComponent.fillSNJob;
                if (job != null && job.isActive()) {
                    SNComponent sNComponent6 = SNComponent.INSTANCE;
                    str2 = SNComponent.mSN;
                    if (str2.length() != 0) {
                        try {
                        } catch (Exception e3) {
                            Object obj4 = coroutine_suspended;
                            sNComponent$fillSN$1 = sNComponent$fillSN$12;
                            e = e3;
                            coroutineScope2 = coroutineScope;
                            obj2 = obj4;
                            NetWorkLog netWorkLog22 = NetWorkLog.INSTANCE;
                            SNComponent sNComponent22 = SNComponent.INSTANCE;
                            TAG2 = SNComponent.TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                            netWorkLog22.mo3279e(TAG2, "fillSN.error " + Log.getStackTraceString(e));
                            obj3 = obj2;
                            coroutineScope = coroutineScope2;
                            sNComponent$fillSN$1.L$0 = coroutineScope;
                            sNComponent$fillSN$1.label = 2;
                            if (DelayKt.delay(5000L, sNComponent$fillSN$1) != obj3) {
                            }
                        }
                        SNComponent sNComponent7 = SNComponent.INSTANCE;
                        sNComponent$fillSN$12.L$0 = coroutineScope;
                        sNComponent$fillSN$12.label = 1;
                        Object sNFromNet = sNComponent7.getSNFromNet(sNComponent$fillSN$12);
                        if (sNFromNet == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj5 = coroutine_suspended;
                        sNComponent$fillSN$1 = sNComponent$fillSN$12;
                        obj = sNFromNet;
                        coroutineScope2 = coroutineScope;
                        obj2 = obj5;
                        String str32 = (String) obj;
                        if (str32 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        sNComponent$fillSN$12 = this;
        SNComponent sNComponent52 = SNComponent.INSTANCE;
        job = SNComponent.fillSNJob;
        if (job != null) {
            SNComponent sNComponent62 = SNComponent.INSTANCE;
            str2 = SNComponent.mSN;
            if (str2.length() != 0) {
            }
        }
        return Unit.INSTANCE;
    }
}
