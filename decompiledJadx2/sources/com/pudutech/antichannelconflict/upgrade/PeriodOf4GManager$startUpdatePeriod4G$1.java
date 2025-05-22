package com.pudutech.antichannelconflict.upgrade;

import com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$startUpdatePeriod4G$1", m3970f = "PeriodOf4GManager.kt", m3971i = {0, 0}, m3972l = {550}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "file"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$startUpdatePeriod4G$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $force;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4479p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodOf4GManager$startUpdatePeriod4G$1(boolean z, Continuation continuation) {
        super(2, continuation);
        this.$force = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeriodOf4GManager$startUpdatePeriod4G$1 periodOf4GManager$startUpdatePeriod4G$1 = new PeriodOf4GManager$startUpdatePeriod4G$1(this.$force, completion);
        periodOf4GManager$startUpdatePeriod4G$1.f4479p$ = (CoroutineScope) obj;
        return periodOf4GManager$startUpdatePeriod4G$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeriodOf4GManager$startUpdatePeriod4G$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PeriodStatusListener periodStatusListener;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4479p$;
                Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "startUpdatePeriod4G download begin", PeriodOf4GManager.INSTANCE.getFirmware_download_url$AntiChannelConflict_release());
                File file = new File("/sdcard/" + StringsKt.substringAfterLast$default(PeriodOf4GManager.INSTANCE.getFirmware_download_url$AntiChannelConflict_release(), "/", (String) null, 2, (Object) null));
                PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
                String firmware_download_url$AntiChannelConflict_release = PeriodOf4GManager.INSTANCE.getFirmware_download_url$AntiChannelConflict_release();
                OssCallback ossCallback = new OssCallback() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$startUpdatePeriod4G$1$task$1
                    @Override // com.pudutech.pd_network.OssCallback
                    public void onCompleted(String url) {
                        PeriodStatusListener periodStatusListener2;
                        Intrinsics.checkParameterIsNotNull(url, "url");
                        PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
                        periodStatusListener2 = PeriodOf4GManager.myStatusListener;
                        periodStatusListener2.onUpdateProgressCB("downloading success", "100", UpgradeStatus.DOWNLOADING);
                        if (PeriodOf4GManager$startUpdatePeriod4G$1.this.$force) {
                            PeriodOf4GManager.INSTANCE.startUpdate();
                        }
                    }

                    @Override // com.pudutech.pd_network.OssCallback
                    public void onError(Exception ex) {
                        Intrinsics.checkParameterIsNotNull(ex, "ex");
                        Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), ex);
                    }

                    @Override // com.pudutech.pd_network.OssCallback
                    public void onProgressChanged(long bytesCurrent, long bytesTotal) {
                        PeriodStatusListener periodStatusListener2;
                        String progress;
                        PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
                        periodStatusListener2 = PeriodOf4GManager.myStatusListener;
                        progress = PeriodOf4GManager.INSTANCE.getProgress(bytesTotal, bytesCurrent);
                        periodStatusListener2.onUpdateProgressCB("downloading", progress, UpgradeStatus.DOWNLOADING);
                    }

                    @Override // com.pudutech.pd_network.OssCallback
                    public void onStateChanged(OssState state) {
                        Intrinsics.checkParameterIsNotNull(state, "state");
                        Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "onStateChanged:", state);
                    }
                };
                this.L$0 = coroutineScope;
                this.L$1 = file;
                this.label = 1;
                obj = periodOf4GManager.startDownload(firmware_download_url$AntiChannelConflict_release, file, ossCallback, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            IOssTaskController iOssTaskController = (IOssTaskController) obj;
            if (!this.$force) {
                iOssTaskController.start();
            } else {
                iOssTaskController.resume();
            }
        } catch (Exception e) {
            PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
            periodStatusListener = PeriodOf4GManager.myStatusListener;
            periodStatusListener.onUpdateProgressCB(e.getMessage(), "0", UpgradeStatus.DOWNLOADING_FAIL);
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), e);
        }
        return Unit.INSTANCE;
    }
}
