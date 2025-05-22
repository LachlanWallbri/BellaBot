package com.pudutech.shared.lib_syntime;

import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SntpTimeSyncManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.shared.lib_syntime.SntpTimeSyncManager$startSync$1", m3970f = "SntpTimeSyncManager.kt", m3971i = {0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3}, m3972l = {34, 45, 49, 53}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "hostUrl", SpeechUtility.TAG_RESOURCE_RESULT, "mlocalCurTime", "nowNtpTime", "$this$launch", "hostUrl", SpeechUtility.TAG_RESOURCE_RESULT, "$this$launch", "hostUrl", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "Z$0", "J$0", "J$1", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$3"})
/* loaded from: classes7.dex */
public final class SntpTimeSyncManager$startSync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayMills;
    final /* synthetic */ List $mNtpServerUrls;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7505p$;
    final /* synthetic */ SntpTimeSyncManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SntpTimeSyncManager$startSync$1(SntpTimeSyncManager sntpTimeSyncManager, long j, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sntpTimeSyncManager;
        this.$delayMills = j;
        this.$mNtpServerUrls = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SntpTimeSyncManager$startSync$1 sntpTimeSyncManager$startSync$1 = new SntpTimeSyncManager$startSync$1(this.this$0, this.$delayMills, this.$mNtpServerUrls, completion);
        sntpTimeSyncManager$startSync$1.f7505p$ = (CoroutineScope) obj;
        return sntpTimeSyncManager$startSync$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SntpTimeSyncManager$startSync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:(1:42)|43|44|45|46|47|48|49|(1:51)(7:52|53|54|55|9|10|(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:42|43|44|45|46|47|48|49|(1:51)(7:52|53|54|55|9|10|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01bf, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01c0, code lost:
    
        r14 = r3;
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01d0, code lost:
    
        r10 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01c3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01cd, code lost:
    
        r8 = r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01ec A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x01ea -> B:8:0x01ed). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x01b3 -> B:9:0x0207). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Iterator it;
        CoroutineScope coroutineScope2;
        Object obj2;
        SntpTimeSyncManager$startSync$1 sntpTimeSyncManager$startSync$1;
        String str;
        char c;
        Exception e;
        Iterator it2;
        CoroutineScope coroutineScope3;
        Object obj3;
        boolean z;
        String str2;
        char c2;
        char c3;
        int i;
        SntpTimeSyncManager sntpTimeSyncManager;
        long j;
        long j2;
        String str3;
        String str4;
        SntpClient sntpClient;
        SntpClient sntpClient2;
        String str5;
        String str6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        char c4 = 3;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7505p$;
            long j3 = this.$delayMills;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    long j4 = this.J$1;
                    j = this.J$0;
                    boolean z2 = this.Z$0;
                    Iterator it3 = (Iterator) this.L$2;
                    String str7 = (String) this.L$1;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        j2 = j4;
                        it = it3;
                        str = str7;
                        obj2 = coroutine_suspended;
                        sntpTimeSyncManager$startSync$1 = this;
                    } catch (Exception e2) {
                        e = e2;
                        c = 3;
                        it2 = it3;
                        str = str7;
                        obj2 = coroutine_suspended;
                    }
                    str3 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                    Pdlog.m3273d(str3, "startSync()" + str + " mlocalCurTime =" + j + "  nowNtpTime = " + j2 + "  nowTime = " + SystemTimeSetting.INSTANCE.formatDateOne(j2));
                    return Unit.INSTANCE;
                }
                if (i2 == 3) {
                    z = this.Z$0;
                    it = (Iterator) this.L$2;
                    str = (String) this.L$1;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        obj3 = coroutine_suspended;
                        c = 3;
                        coroutineScope3 = coroutineScope4;
                        sntpTimeSyncManager$startSync$1 = this;
                    } catch (Exception e3) {
                        e = e3;
                        obj2 = coroutine_suspended;
                        c = 3;
                        it2 = it;
                        coroutineScope2 = coroutineScope4;
                    }
                    str2 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                    Pdlog.m3273d(str2, "startSync()" + str + " result =" + z);
                    obj2 = obj3;
                    coroutineScope2 = coroutineScope3;
                    c2 = 4;
                    c3 = 2;
                    i = 1;
                    c4 = c;
                    i3 = i;
                    if (it.hasNext()) {
                    }
                    return Unit.INSTANCE;
                }
                if (i2 == 4) {
                    e = (Exception) this.L$3;
                    Iterator it4 = (Iterator) this.L$2;
                    CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    c2 = 4;
                    c = 3;
                    it2 = it4;
                    coroutineScope2 = coroutineScope5;
                    sntpTimeSyncManager$startSync$1 = this;
                    str6 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                    c3 = 2;
                    e.printStackTrace();
                    i = 1;
                    Pdlog.m3274e(str6, " startSync() ", Unit.INSTANCE);
                    it = it2;
                    c4 = c;
                    i3 = i;
                    if (it.hasNext()) {
                        String str8 = (String) it.next();
                        try {
                        } catch (Exception e4) {
                            e = e4;
                            c = c4;
                        }
                        sntpClient = sntpTimeSyncManager$startSync$1.this$0.mSntpTimer;
                        z = sntpClient.requestTime(str8, SntpClient._udpSocketTimeoutInMillis);
                        if (z) {
                            try {
                            } catch (Exception e5) {
                                e = e5;
                                c = c4;
                                it2 = it;
                                str = str8;
                            }
                            j = System.currentTimeMillis();
                            sntpClient2 = sntpTimeSyncManager$startSync$1.this$0.mSntpTimer;
                            Date nowTime = sntpClient2.getNowTime();
                            Intrinsics.checkExpressionValueIsNotNull(nowTime, "mSntpTimer.nowTime");
                            j2 = nowTime.getTime();
                            if (Math.abs(j2 - j) > 1300) {
                                try {
                                } catch (Exception e6) {
                                    e = e6;
                                    it2 = it;
                                    str = str8;
                                    c = 3;
                                    sntpTimeSyncManager = sntpTimeSyncManager$startSync$1.this$0;
                                    sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                                    sntpTimeSyncManager$startSync$1.L$1 = str;
                                    sntpTimeSyncManager$startSync$1.L$2 = it2;
                                    sntpTimeSyncManager$startSync$1.L$3 = e;
                                    c2 = 4;
                                    sntpTimeSyncManager$startSync$1.label = 4;
                                    if (SntpTimeSyncManager.callMain$default(sntpTimeSyncManager, 0L, sntpTimeSyncManager$startSync$1, 1, null) == obj2) {
                                    }
                                    str6 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                                    c3 = 2;
                                    e.printStackTrace();
                                    i = 1;
                                    Pdlog.m3274e(str6, " startSync() ", Unit.INSTANCE);
                                    it = it2;
                                    c4 = c;
                                    i3 = i;
                                    if (it.hasNext()) {
                                    }
                                    return Unit.INSTANCE;
                                }
                                SystemTimeSetting.INSTANCE.setSystemCurTime(j2);
                                str5 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                                Object[] objArr = new Object[i3];
                                objArr[0] = "startSync()" + str8 + " update completed";
                                Pdlog.m3273d(str5, objArr);
                            }
                            SntpTimeSyncManager sntpTimeSyncManager2 = sntpTimeSyncManager$startSync$1.this$0;
                            sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                            sntpTimeSyncManager$startSync$1.L$1 = str8;
                            sntpTimeSyncManager$startSync$1.L$2 = it;
                            sntpTimeSyncManager$startSync$1.Z$0 = z;
                            sntpTimeSyncManager$startSync$1.J$0 = j;
                            sntpTimeSyncManager$startSync$1.J$1 = j2;
                            sntpTimeSyncManager$startSync$1.label = 2;
                            if (sntpTimeSyncManager2.callMain(j2, sntpTimeSyncManager$startSync$1) == obj2) {
                                return obj2;
                            }
                            str = str8;
                            try {
                            } catch (Exception e7) {
                                e = e7;
                                it2 = it;
                                c = 3;
                                sntpTimeSyncManager = sntpTimeSyncManager$startSync$1.this$0;
                                sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                                sntpTimeSyncManager$startSync$1.L$1 = str;
                                sntpTimeSyncManager$startSync$1.L$2 = it2;
                                sntpTimeSyncManager$startSync$1.L$3 = e;
                                c2 = 4;
                                sntpTimeSyncManager$startSync$1.label = 4;
                                if (SntpTimeSyncManager.callMain$default(sntpTimeSyncManager, 0L, sntpTimeSyncManager$startSync$1, 1, null) == obj2) {
                                }
                                str6 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                                c3 = 2;
                                e.printStackTrace();
                                i = 1;
                                Pdlog.m3274e(str6, " startSync() ", Unit.INSTANCE);
                                it = it2;
                                c4 = c;
                                i3 = i;
                                if (it.hasNext()) {
                                }
                                return Unit.INSTANCE;
                            }
                            str3 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                            Pdlog.m3273d(str3, "startSync()" + str + " mlocalCurTime =" + j + "  nowNtpTime = " + j2 + "  nowTime = " + SystemTimeSetting.INSTANCE.formatDateOne(j2));
                        } else {
                            try {
                            } catch (Exception e8) {
                                e = e8;
                                str4 = str8;
                                c = 3;
                            }
                            SntpTimeSyncManager sntpTimeSyncManager3 = sntpTimeSyncManager$startSync$1.this$0;
                            sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                            sntpTimeSyncManager$startSync$1.L$1 = str8;
                            sntpTimeSyncManager$startSync$1.L$2 = it;
                            sntpTimeSyncManager$startSync$1.Z$0 = z;
                            c = 3;
                            sntpTimeSyncManager$startSync$1.label = 3;
                            str4 = str8;
                            obj3 = obj2;
                            coroutineScope3 = coroutineScope2;
                            if (SntpTimeSyncManager.callMain$default(sntpTimeSyncManager3, 0L, sntpTimeSyncManager$startSync$1, 1, null) == obj3) {
                                return obj3;
                            }
                            str = str4;
                            try {
                            } catch (Exception e9) {
                                e = e9;
                                obj2 = obj3;
                                coroutineScope2 = coroutineScope3;
                                it2 = it;
                                sntpTimeSyncManager = sntpTimeSyncManager$startSync$1.this$0;
                                sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                                sntpTimeSyncManager$startSync$1.L$1 = str;
                                sntpTimeSyncManager$startSync$1.L$2 = it2;
                                sntpTimeSyncManager$startSync$1.L$3 = e;
                                c2 = 4;
                                sntpTimeSyncManager$startSync$1.label = 4;
                                if (SntpTimeSyncManager.callMain$default(sntpTimeSyncManager, 0L, sntpTimeSyncManager$startSync$1, 1, null) == obj2) {
                                }
                                str6 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                                c3 = 2;
                                e.printStackTrace();
                                i = 1;
                                Pdlog.m3274e(str6, " startSync() ", Unit.INSTANCE);
                                it = it2;
                                c4 = c;
                                i3 = i;
                                if (it.hasNext()) {
                                }
                                return Unit.INSTANCE;
                            }
                            str2 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                            Pdlog.m3273d(str2, "startSync()" + str + " result =" + z);
                            obj2 = obj3;
                            coroutineScope2 = coroutineScope3;
                            c2 = 4;
                            c3 = 2;
                            i = 1;
                            c4 = c;
                            i3 = i;
                            if (it.hasNext()) {
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                sntpTimeSyncManager$startSync$1 = this;
                sntpTimeSyncManager = sntpTimeSyncManager$startSync$1.this$0;
                sntpTimeSyncManager$startSync$1.L$0 = coroutineScope2;
                sntpTimeSyncManager$startSync$1.L$1 = str;
                sntpTimeSyncManager$startSync$1.L$2 = it2;
                sntpTimeSyncManager$startSync$1.L$3 = e;
                c2 = 4;
                sntpTimeSyncManager$startSync$1.label = 4;
                if (SntpTimeSyncManager.callMain$default(sntpTimeSyncManager, 0L, sntpTimeSyncManager$startSync$1, 1, null) == obj2) {
                    return obj2;
                }
                str6 = sntpTimeSyncManager$startSync$1.this$0.TAG;
                c3 = 2;
                e.printStackTrace();
                i = 1;
                Pdlog.m3274e(str6, " startSync() ", Unit.INSTANCE);
                it = it2;
                c4 = c;
                i3 = i;
                if (it.hasNext()) {
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        it = this.$mNtpServerUrls.iterator();
        coroutineScope2 = coroutineScope;
        obj2 = coroutine_suspended;
        sntpTimeSyncManager$startSync$1 = this;
        if (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
