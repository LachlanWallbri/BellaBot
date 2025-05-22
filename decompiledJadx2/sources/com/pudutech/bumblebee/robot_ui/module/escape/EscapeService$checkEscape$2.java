package com.pudutech.bumblebee.robot_ui.module.escape;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.EscapeReq;
import com.pudutech.disinfect.baselib.network.response.EscapeResBase;
import com.pudutech.disinfect.baselib.network.response.EscapeResData;
import com.pudutech.location.Location;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EscapeService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$checkEscape$2", m3970f = "EscapeService.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2}, m3972l = {118, 133, 144}, m3973m = "invokeSuspend", m3974n = {"$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "escapeRes", "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, C3898x.f4338g}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class EscapeService$checkEscape$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4854p$;
    final /* synthetic */ EscapeService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EscapeService$checkEscape$2(EscapeService escapeService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = escapeService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EscapeService$checkEscape$2 escapeService$checkEscape$2 = new EscapeService$checkEscape$2(this.this$0, completion);
        escapeService$checkEscape$2.f4854p$ = (CoroutineScope) obj;
        return escapeService$checkEscape$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EscapeService$checkEscape$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x01b4: IPUT (r10 I:java.lang.Object), (r1 I:com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$checkEscape$2) com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$checkEscape$2.L$1 java.lang.Object, block:B:56:0x01b2 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x01b2: IPUT (r11 I:java.lang.Object), (r1 I:com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$checkEscape$2) (LINE:144) com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$checkEscape$2.L$0 java.lang.Object, block:B:56:0x01b2 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        Object obj2;
        Object obj3;
        String str;
        int i3;
        CoroutineScope coroutineScope;
        EscapeReq escapeReq;
        Location location;
        Location location2;
        String encryptParam;
        String str2;
        Location location3;
        Location location4;
        String encryptParam2;
        String str3;
        boolean isAutoLock;
        String str4;
        Object checkHasEscape;
        String str5;
        int i4;
        int i5;
        int i6;
        String str6;
        int i7;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i8 = this.label;
        try {
        } catch (Exception e) {
            e = e;
            EscapeService escapeService = this.this$0;
            i = escapeService.reqCount;
            escapeService.reqCount = i + 1;
            i2 = this.this$0.reqCount;
            if (i2 >= 5) {
                this.this$0.checkMapVersion();
            } else {
                this.L$0 = obj2;
                this.L$1 = obj3;
                this.L$2 = e;
                this.label = 3;
                if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        if (i8 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4854p$;
            location = this.this$0.location;
            if (location == null) {
                str2 = null;
            } else {
                EscapeService escapeService2 = this.this$0;
                location2 = escapeService2.location;
                encryptParam = escapeService2.encryptParam(location2 != null ? location2.getLongitude() : null);
                str2 = encryptParam;
            }
            location3 = this.this$0.location;
            if (location3 == null) {
                str3 = null;
            } else {
                EscapeService escapeService3 = this.this$0;
                location4 = escapeService3.location;
                encryptParam2 = escapeService3.encryptParam(location4 != null ? location4.getLatitude() : null);
                str3 = encryptParam2;
            }
            isAutoLock = this.this$0.isAutoLock();
            escapeReq = new EscapeReq(null, str2, str3, isAutoLock, null, null, 0L, 113, null);
            str4 = this.this$0.TAG;
            Pdlog.m3273d(str4, "checkEscape() req = " + escapeReq);
            NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
            this.L$0 = coroutineScope;
            this.L$1 = escapeReq;
            this.label = 1;
            checkHasEscape = cloudApi.checkHasEscape(escapeReq, this);
            if (checkHasEscape == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i8 != 1) {
                if (i8 == 2) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.checkEscape();
                    str6 = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("checkEscape failure = ");
                    i7 = this.this$0.reqCount;
                    sb.append(i7);
                    Pdlog.m3273d(str6, sb.toString());
                    return Unit.INSTANCE;
                }
                if (i8 == 3) {
                    e = (Exception) this.L$2;
                    ResultKt.throwOnFailure(obj);
                    this.this$0.checkEscape();
                    str = this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("checkEscape excep = ");
                    i3 = this.this$0.reqCount;
                    sb2.append(i3);
                    e.printStackTrace();
                    Pdlog.m3273d(str, sb2.toString(), Unit.INSTANCE);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            escapeReq = (EscapeReq) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            checkHasEscape = obj;
        }
        EscapeResBase escapeResBase = (EscapeResBase) checkHasEscape;
        str5 = this.this$0.TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("reqCount =");
        i4 = this.this$0.reqCount;
        sb3.append(i4);
        sb3.append(" escapeRes=");
        sb3.append(escapeResBase);
        Pdlog.m3275i(str5, sb3.toString());
        EscapeService escapeService4 = this.this$0;
        i5 = escapeService4.reqCount;
        escapeService4.reqCount = i5 + 1;
        if (escapeResBase.isSuccess()) {
            this.this$0.locationReset = 0;
            this.this$0.getEscapeObservable().onNext(Boxing.boxBoolean(((EscapeResData) escapeResBase.getData()).getLock()));
            if (!((EscapeResData) escapeResBase.getData()).getLock()) {
                this.this$0.checkMapVersion();
                this.this$0.getStorage().mapCheckEnd();
            }
            return Unit.INSTANCE;
        }
        i6 = this.this$0.reqCount;
        if (i6 >= 5) {
            this.this$0.checkMapVersion();
            str6 = this.this$0.TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("checkEscape failure = ");
            i7 = this.this$0.reqCount;
            sb4.append(i7);
            Pdlog.m3273d(str6, sb4.toString());
            return Unit.INSTANCE;
        }
        this.L$0 = coroutineScope;
        this.L$1 = escapeReq;
        this.L$2 = escapeResBase;
        this.label = 2;
        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.checkEscape();
        str6 = this.this$0.TAG;
        StringBuilder sb42 = new StringBuilder();
        sb42.append("checkEscape failure = ");
        i7 = this.this$0.reqCount;
        sb42.append(i7);
        Pdlog.m3273d(str6, sb42.toString());
        return Unit.INSTANCE;
    }
}
