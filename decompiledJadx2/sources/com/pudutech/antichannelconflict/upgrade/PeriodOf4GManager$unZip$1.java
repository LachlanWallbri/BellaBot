package com.pudutech.antichannelconflict.upgrade;

import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import com.pudutech.base.Pdlog;
import java.io.File;
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
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$unZip$1", m3970f = "PeriodOf4GManager.kt", m3971i = {0}, m3972l = {VoiceWakeuperAidl.RES_FROM_CLIENT}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$unZip$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $descDir;
    final /* synthetic */ String $model;
    final /* synthetic */ String $zipFile;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4480p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodOf4GManager$unZip$1(String str, String str2, String str3, Continuation continuation) {
        super(2, continuation);
        this.$zipFile = str;
        this.$descDir = str2;
        this.$model = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeriodOf4GManager$unZip$1 periodOf4GManager$unZip$1 = new PeriodOf4GManager$unZip$1(this.$zipFile, this.$descDir, this.$model, completion);
        periodOf4GManager$unZip$1.f4480p$ = (CoroutineScope) obj;
        return periodOf4GManager$unZip$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeriodOf4GManager$unZip$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PeriodStatusListener periodStatusListener;
        PeriodStatusListener periodStatusListener2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4480p$;
            PeriodOf4GManager$unZip$1$zipStatus$1 periodOf4GManager$unZip$1$zipStatus$1 = new PeriodOf4GManager$unZip$1$zipStatus$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(15000L, periodOf4GManager$unZip$1$zipStatus$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Boolean bool = (Boolean) obj;
        Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "unZip zipStatus:", bool);
        if (Intrinsics.areEqual(Boxing.boxBoolean(true), bool)) {
            PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
            periodStatusListener2 = PeriodOf4GManager.myStatusListener;
            periodStatusListener2.onUpdateProgressCB("unZip success", "5", UpgradeStatus.UPDATING);
            new File(this.$zipFile);
            PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
            String str = this.$model;
            Object obj2 = PeriodOf4GManager.access$getContext$p(PeriodOf4GManager.INSTANCE).get();
            if (obj2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(obj2, "context.get()!!");
            periodOf4GManager2.copyFirmware(str, (Context) obj2, this.$zipFile);
        } else {
            PeriodOf4GManager periodOf4GManager3 = PeriodOf4GManager.INSTANCE;
            periodStatusListener = PeriodOf4GManager.myStatusListener;
            periodStatusListener.onUpdateProgressCB("unZip fail", "0", UpgradeStatus.UPDATE_FAIL);
        }
        return Unit.INSTANCE;
    }
}
