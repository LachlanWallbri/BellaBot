package org.jetbrains.anko.sdk27.coroutines;

import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
@DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/__SeekBar_OnSeekBarChangeListener$onProgressChanged$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {913, 915}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes9.dex */
final class __SeekBar_OnSeekBarChangeListener$onProgressChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $fromUser;
    final /* synthetic */ Function5 $handler;
    final /* synthetic */ int $progress;
    final /* synthetic */ SeekBar $seekBar;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f10178p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public __SeekBar_OnSeekBarChangeListener$onProgressChanged$1(Function5 function5, SeekBar seekBar, int i, boolean z, Continuation continuation) {
        super(2, continuation);
        this.$handler = function5;
        this.$seekBar = seekBar;
        this.$progress = i;
        this.$fromUser = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        __SeekBar_OnSeekBarChangeListener$onProgressChanged$1 __seekbar_onseekbarchangelistener_onprogresschanged_1 = new __SeekBar_OnSeekBarChangeListener$onProgressChanged$1(this.$handler, this.$seekBar, this.$progress, this.$fromUser, completion);
        __seekbar_onseekbarchangelistener_onprogresschanged_1.f10178p$ = (CoroutineScope) obj;
        return __seekbar_onseekbarchangelistener_onprogresschanged_1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((__SeekBar_OnSeekBarChangeListener$onProgressChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else {
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            CoroutineScope coroutineScope = this.f10178p$;
            Function5 function5 = this.$handler;
            SeekBar seekBar = this.$seekBar;
            Integer boxInt = Boxing.boxInt(this.$progress);
            Boolean boxBoolean = Boxing.boxBoolean(this.$fromUser);
            this.label = 1;
            if (function5.invoke(coroutineScope, seekBar, boxInt, boxBoolean, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
