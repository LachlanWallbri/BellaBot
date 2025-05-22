package com.pudutech.pd_network.ability;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.NetDataUtils;
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
/* compiled from: SupportAbilityComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.ability.SupportAbilityComponent$updateAbility$1", m3970f = "SupportAbilityComponent.kt", m3971i = {0, 0, 1}, m3972l = {91, 98}, m3973m = "invokeSuspend", m3974n = {"$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch"}, m3975s = {"L$0", "L$1", "L$0"})
/* loaded from: classes6.dex */
public final class SupportAbilityComponent$updateAbility$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6837p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SupportAbilityComponent$updateAbility$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SupportAbilityComponent$updateAbility$1 supportAbilityComponent$updateAbility$1 = new SupportAbilityComponent$updateAbility$1(completion);
        supportAbilityComponent$updateAbility$1.f6837p$ = (CoroutineScope) obj;
        return supportAbilityComponent$updateAbility$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SupportAbilityComponent$updateAbility$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:33|(3:34|35|36)|17|18|(1:20)|21|22|(1:24)|7|(3:12|13|(1:15)(9:16|17|18|(0)|21|22|(0)|7|(2:9|10)(0)))(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0094, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0095, code lost:
    
        r9 = r4;
        r4 = r11;
        r11 = r0;
        r0 = r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008d A[Catch: Exception -> 0x0094, TRY_LEAVE, TryCatch #2 {Exception -> 0x0094, blocks: (B:18:0x0081, B:20:0x008d), top: B:17:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00ca  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00c7 -> B:7:0x0045). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        SupportAbilityComponent$updateAbility$1 supportAbilityComponent$updateAbility$1;
        Exception e;
        String str2;
        Object obj2;
        SupportAbilityComponent$updateAbility$1 supportAbilityComponent$updateAbility$12;
        int i;
        Api api;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6837p$;
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            SupportAbilityComponent supportAbilityComponent = SupportAbilityComponent.INSTANCE;
            str = SupportAbilityComponent.TAG;
            netWorkLog.mo3280i(str, "updateAbility.args >");
            coroutineScope = coroutineScope2;
        } else if (i2 == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                supportAbilityComponent$updateAbility$12 = this;
            } catch (Exception e2) {
                e = e2;
                supportAbilityComponent$updateAbility$1 = this;
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                SupportAbilityComponent supportAbilityComponent2 = SupportAbilityComponent.INSTANCE;
                str2 = SupportAbilityComponent.TAG;
                netWorkLog2.mo3280i(str2, "updateAbility.error > " + Log.getStackTraceString(e));
                supportAbilityComponent$updateAbility$1.L$0 = coroutineScope;
                supportAbilityComponent$updateAbility$1.label = 2;
                if (DelayKt.delay(5000L, supportAbilityComponent$updateAbility$1) == coroutine_suspended) {
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            if (((BaseResponse) obj).getCode() == 0) {
                CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            }
            supportAbilityComponent$updateAbility$1 = supportAbilityComponent$updateAbility$12;
            coroutine_suspended = obj2;
            supportAbilityComponent$updateAbility$1.L$0 = coroutineScope;
            supportAbilityComponent$updateAbility$1.label = 2;
            if (DelayKt.delay(5000L, supportAbilityComponent$updateAbility$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                try {
                } catch (Exception e3) {
                    e = e3;
                    NetWorkLog netWorkLog22 = NetWorkLog.INSTANCE;
                    SupportAbilityComponent supportAbilityComponent22 = SupportAbilityComponent.INSTANCE;
                    str2 = SupportAbilityComponent.TAG;
                    netWorkLog22.mo3280i(str2, "updateAbility.error > " + Log.getStackTraceString(e));
                    supportAbilityComponent$updateAbility$1.L$0 = coroutineScope;
                    supportAbilityComponent$updateAbility$1.label = 2;
                    if (DelayKt.delay(5000L, supportAbilityComponent$updateAbility$1) == coroutine_suspended) {
                    }
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                }
                String mac = NetDataUtils.INSTANCE.mac();
                String versionName = PdNetConfig.INSTANCE.getVersionName();
                SupportAbilityComponent supportAbilityComponent3 = SupportAbilityComponent.INSTANCE;
                i = SupportAbilityComponent.crtAbility;
                AbilityReq abilityReq = new AbilityReq(mac, versionName, CollectionsKt.listOf(Boxing.boxInt(i)));
                api = SupportAbilityComponent.INSTANCE.getApi();
                supportAbilityComponent$updateAbility$1.L$0 = coroutineScope;
                supportAbilityComponent$updateAbility$1.L$1 = abilityReq;
                supportAbilityComponent$updateAbility$1.label = 1;
                Object updateAbility = api.updateAbility(abilityReq, supportAbilityComponent$updateAbility$1);
                if (updateAbility == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj3 = coroutine_suspended;
                supportAbilityComponent$updateAbility$12 = supportAbilityComponent$updateAbility$1;
                obj = updateAbility;
                obj2 = obj3;
                if (((BaseResponse) obj).getCode() == 0) {
                }
                supportAbilityComponent$updateAbility$1 = supportAbilityComponent$updateAbility$12;
                coroutine_suspended = obj2;
                supportAbilityComponent$updateAbility$1.L$0 = coroutineScope;
                supportAbilityComponent$updateAbility$1.label = 2;
                if (DelayKt.delay(5000L, supportAbilityComponent$updateAbility$1) == coroutine_suspended) {
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                    SupportAbilityComponent supportAbilityComponent4 = SupportAbilityComponent.INSTANCE;
                    str3 = SupportAbilityComponent.TAG;
                    netWorkLog3.mo3280i(str3, "updateAbility.end");
                    return Unit.INSTANCE;
                }
            }
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        supportAbilityComponent$updateAbility$1 = this;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
