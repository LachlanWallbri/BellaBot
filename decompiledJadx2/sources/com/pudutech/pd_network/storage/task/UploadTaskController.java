package com.pudutech.pd_network.storage.task;

import android.content.Context;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastChannelKt;
import kotlinx.coroutines.flow.FlowKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: UploadTaskController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0016\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0016J\u0010\u0010\"\u001a\u00020 2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010#\u001a\u00020 H\u0016J\b\u0010$\u001a\u00020 H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/task/UploadTaskController;", "Lcom/pudutech/pd_network/IOssTaskController;", "context", "Landroid/content/Context;", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "(Landroid/content/Context;Lcom/pudutech/pd_network/bean/PdUploadConfig;)V", "TAG", "", "_progressFL", "Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlin/Pair;", "", "callback", "Lcom/pudutech/pd_network/OssCallback;", "controller", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/pd_network/OssState;", "crtState", "setCrtState", "(Lcom/pudutech/pd_network/OssState;)V", "mCallback", "com/pudutech/pd_network/storage/task/UploadTaskController$mCallback$1", "Lcom/pudutech/pd_network/storage/task/UploadTaskController$mCallback$1;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "startJob", "Lkotlinx/coroutines/Job;", "checkState", "", "currentState", "pause", "", "resume", "setCallback", "start", "stop", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UploadTaskController implements IOssTaskController {
    private final String TAG;
    private final BroadcastChannel<Pair<Long, Long>> _progressFL;
    private OssCallback callback;
    private final PdUploadConfig config;
    private final Context context;
    private IOssTaskController controller;
    private OssState crtState;
    private final UploadTaskController$mCallback$1 mCallback;
    private final CoroutineScope scope;
    private Job startJob;

    public UploadTaskController(Context context, PdUploadConfig config) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.context = context;
        this.config = config;
        this.TAG = "UploadTaskController";
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        this._progressFL = BroadcastChannelKt.BroadcastChannel(-2);
        this.crtState = OssState.WAITING;
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "init > " + this.config);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.debounce(FlowKt.asFlow(this._progressFL), 500L), new C54841(null)), this.scope);
        this.mCallback = new UploadTaskController$mCallback$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCrtState(OssState ossState) {
        OssCallback ossCallback = this.callback;
        if (ossCallback != null) {
            ossCallback.onStateChanged(ossState);
        }
        this.crtState = ossState;
    }

    /* compiled from: UploadTaskController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.pd_network.storage.task.UploadTaskController$1", m3970f = "UploadTaskController.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.pd_network.storage.task.UploadTaskController$1 */
    /* loaded from: classes6.dex */
    static final class C54841 extends SuspendLambda implements Function2<Pair<? extends Long, ? extends Long>, Continuation<? super Unit>, Object> {
        int label;
        private Pair p$0;

        C54841(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C54841 c54841 = new C54841(completion);
            c54841.p$0 = (Pair) obj;
            return c54841;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Pair<? extends Long, ? extends Long> pair, Continuation<? super Unit> continuation) {
            return ((C54841) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Pair pair = this.p$0;
            NetWorkLog.INSTANCE.mo3280i(UploadTaskController.this.TAG, "onProgressChanged > " + pair);
            return Unit.INSTANCE;
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    /* renamed from: currentState, reason: from getter */
    public OssState getCrtState() {
        return this.crtState;
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void start() {
        Job launch$default;
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "start > " + this.crtState);
        if (this.callback == null) {
            PdNetConfig.INSTANCE.throwOrLog("为避免丢失状态，请先设置回调 setCallback(callback: OssCallback) 后，在启动任务。");
        } else if (this.startJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new UploadTaskController$start$1(this, null), 3, null);
            this.startJob = launch$default;
        } else {
            PdNetConfig.INSTANCE.throwOrLog("每个任务只允许start一次");
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void stop() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "stop > " + this.crtState);
        if (checkState()) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new UploadTaskController$stop$1(this, null), 3, null);
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void resume() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "resume > " + this.crtState);
        if (checkState()) {
            if (getCrtState() == OssState.PAUSED) {
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new UploadTaskController$resume$1(this, null), 3, null);
                return;
            }
            PdNetConfig.INSTANCE.throwOrLog("cant resume : task is " + getCrtState());
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void pause() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "pause > " + this.crtState);
        if (getCrtState() != OssState.IN_PROGRESS) {
            PdNetConfig.INSTANCE.throwOrLog("cant pause : task is " + getCrtState());
            return;
        }
        if (checkState()) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new UploadTaskController$pause$1(this, null), 3, null);
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void setCallback(OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "setCallback > callback:" + callback + ' ');
        this.callback = callback;
    }

    private final boolean checkState() {
        if (this.startJob == null) {
            PdNetConfig.INSTANCE.throwOrLog("task has not started");
            return false;
        }
        if (getCrtState() == OssState.CANCELED) {
            PdNetConfig.INSTANCE.throwOrLog("task is canceled");
            return false;
        }
        if (getCrtState() == OssState.COMPLETED) {
            PdNetConfig.INSTANCE.throwOrLog("task is completed");
            return false;
        }
        if (getCrtState() != OssState.FAILED) {
            return true;
        }
        PdNetConfig.INSTANCE.throwOrLog("task is failed");
        return false;
    }
}
