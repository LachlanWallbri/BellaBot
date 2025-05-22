package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.widget.FrameLayout;
import android.widget.TextView;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MultiMapFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initData$1", m3970f = "MultiMapFragment.kt", m3971i = {0, 1, 1}, m3972l = {87, 88}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "haveUpdate"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class MultiMapFragment$initData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4881p$;
    final /* synthetic */ MultiMapFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiMapFragment$initData$1(MultiMapFragment multiMapFragment, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multiMapFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MultiMapFragment$initData$1 multiMapFragment$initData$1 = new MultiMapFragment$initData$1(this.this$0, completion);
        multiMapFragment$initData$1.f4881p$ = (CoroutineScope) obj;
        return multiMapFragment$initData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MultiMapFragment$initData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Ref.BooleanRef booleanRef;
        MapUpdatePresenter mapUpdatePresenter;
        Ref.BooleanRef booleanRef2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4881p$;
            booleanRef = new Ref.BooleanRef();
            mapUpdatePresenter = this.this$0.getMapUpdatePresenter();
            this.L$0 = coroutineScope;
            this.L$1 = booleanRef;
            this.L$2 = booleanRef;
            this.label = 1;
            obj = mapUpdatePresenter.checkMapUpdate(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef2 = booleanRef;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            booleanRef = (Ref.BooleanRef) this.L$2;
            booleanRef2 = (Ref.BooleanRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        booleanRef.element = ((Boolean) obj).booleanValue();
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C42501 c42501 = new C42501(booleanRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = booleanRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c42501, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MultiMapFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initData$1$1", m3970f = "MultiMapFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initData$1$1 */
    /* loaded from: classes3.dex */
    public static final class C42501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $haveUpdate;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4882p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C42501(Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$haveUpdate = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42501 c42501 = new C42501(this.$haveUpdate, completion);
            c42501.f4882p$ = (CoroutineScope) obj;
            return c42501;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42501) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4882p$;
            if (this.$haveUpdate.element) {
                FrameLayout update_fl = (FrameLayout) MultiMapFragment$initData$1.this.this$0._$_findCachedViewById(C4188R.id.update_fl);
                Intrinsics.checkExpressionValueIsNotNull(update_fl, "update_fl");
                update_fl.setClickable(true);
                ((TextView) MultiMapFragment$initData$1.this.this$0._$_findCachedViewById(C4188R.id.update_tv)).setText(C4188R.string.map_update_sync);
            } else {
                FrameLayout update_fl2 = (FrameLayout) MultiMapFragment$initData$1.this.this$0._$_findCachedViewById(C4188R.id.update_fl);
                Intrinsics.checkExpressionValueIsNotNull(update_fl2, "update_fl");
                update_fl2.setClickable(false);
                ((TextView) MultiMapFragment$initData$1.this.this$0._$_findCachedViewById(C4188R.id.update_tv)).setText(C4188R.string.map_update_latest);
            }
            return Unit.INSTANCE;
        }
    }
}
