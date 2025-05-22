package com.pudutech.mirsdk.config;

import android.content.SharedPreferences;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: InstallationModeConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.config.InstallationModeConfig$checkOpenTime$1", m3970f = "InstallationModeConfig.kt", m3971i = {0}, m3972l = {88}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class InstallationModeConfig$checkOpenTime$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5764p$;
    final /* synthetic */ InstallationModeConfig this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstallationModeConfig$checkOpenTime$1(InstallationModeConfig installationModeConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = installationModeConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        InstallationModeConfig$checkOpenTime$1 installationModeConfig$checkOpenTime$1 = new InstallationModeConfig$checkOpenTime$1(this.this$0, completion);
        installationModeConfig$checkOpenTime$1.f5764p$ = (CoroutineScope) obj;
        return installationModeConfig$checkOpenTime$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InstallationModeConfig$checkOpenTime$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5764p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            if (CoroutineScopeKt.isActive(coroutineScope) && this.this$0.getIsOpen()) {
                j = this.this$0.openInstallationModeTime;
                j2 = this.this$0.EACH_LOOP_DURATION;
                long j12 = j + j2;
                j3 = this.this$0.WAIT_CLOSE_TIME;
                if (j12 >= j3) {
                    this.this$0.setOpen(false);
                    InstallationModeConfig installationModeConfig = this.this$0;
                    installationModeConfig.closeInstallationMode(InstallationModeConfig.access$getContext$p(installationModeConfig));
                    this.this$0.openInstallationModeTime = 0L;
                    this.this$0.lastSaveTime = 0L;
                    SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
                    edit.putBoolean("InstallMode", this.this$0.getIsOpen());
                    j4 = this.this$0.openInstallationModeTime;
                    edit.putLong("InstallModeOpenTime", j4);
                    edit.apply();
                    InstallationModeConfig installationModeConfig2 = this.this$0;
                    installationModeConfig2.apply(InstallationModeConfig.access$getContext$p(installationModeConfig2));
                    this.this$0.modeJob = (Job) null;
                } else {
                    InstallationModeConfig installationModeConfig3 = this.this$0;
                    j5 = installationModeConfig3.openInstallationModeTime;
                    j6 = this.this$0.EACH_LOOP_DURATION;
                    installationModeConfig3.openInstallationModeTime = j5 + j6;
                    InstallationModeConfig installationModeConfig4 = this.this$0;
                    j7 = installationModeConfig4.lastSaveTime;
                    j8 = this.this$0.EACH_LOOP_DURATION;
                    installationModeConfig4.lastSaveTime = j7 + j8;
                    j9 = this.this$0.lastSaveTime;
                    j10 = this.this$0.EACH_SAVE_DURATION;
                    if (j9 > j10) {
                        this.this$0.lastSaveTime = 0L;
                        SharedPreferences.Editor edit2 = SDKConfig.INSTANCE.getPreferences().edit();
                        j11 = this.this$0.openInstallationModeTime;
                        edit2.putLong("InstallModeOpenTime", j11);
                        edit2.apply();
                    }
                    this.L$0 = coroutineScope;
                    this.label = 1;
                }
            }
            return Unit.INSTANCE;
        } while (DelayKt.delay(60000L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
