package com.pudutech.peanut.robot_ui.manager;

import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RespStateData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AdControllerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkState$1", m3970f = "AdControllerManager.kt", m3971i = {0, 1, 1, 2, 2}, m3972l = {84, 86, 91}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "state", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class AdControllerManager$checkState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6991p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdControllerManager$checkState$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdControllerManager$checkState$1 adControllerManager$checkState$1 = new AdControllerManager$checkState$1(this.$cb, completion);
        adControllerManager$checkState$1.f6991p$ = (CoroutineScope) obj;
        return adControllerManager$checkState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdControllerManager$checkState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r12v9, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
        } catch (Exception e) {
            e = e;
            coroutineScope = r1;
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6991p$;
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            NetWorkApiManager.AdPlatformService adControl = NetWorkApiManager.INSTANCE.getAdControl();
            this.L$0 = coroutineScope2;
            this.L$1 = objectRef3;
            this.L$2 = objectRef3;
            this.label = 1;
            Object state = adControl.getState(this);
            if (state == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            objectRef = objectRef3;
            obj = state;
            objectRef2 = objectRef;
        } else if (r1 == 1) {
            objectRef = (Ref.ObjectRef) this.L$2;
            objectRef2 = (Ref.ObjectRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e2) {
                e = e2;
                AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
                str = AdControllerManager.TAG;
                Pdlog.m3274e(str, "checkState : " + Log.getStackTraceString(e));
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C55162 c55162 = new C55162(null);
                this.L$0 = coroutineScope;
                this.L$1 = e;
                this.label = 3;
                if (BuildersKt.withContext(main, c55162, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else {
            if (r1 == 2) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (r1 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        objectRef.element = (ApiResponse) obj;
        AdControllerManager adControllerManager2 = AdControllerManager.INSTANCE;
        str2 = AdControllerManager.TAG;
        Pdlog.m3273d(str2, "checkState " + ((ApiResponse) objectRef2.element));
        MainCoroutineDispatcher main2 = Dispatchers.getMain();
        C55151 c55151 = new C55151(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main2, c55151, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AdControllerManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkState$1$1", m3970f = "AdControllerManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkState$1$1 */
    /* loaded from: classes5.dex */
    public static final class C55151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $state;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6992p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C55151(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$state = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55151 c55151 = new C55151(this.$state, completion);
            c55151.f6992p$ = (CoroutineScope) obj;
            return c55151;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C55151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6992p$;
            AdControllerManager$checkState$1.this.$cb.invoke(Boxing.boxBoolean(((RespStateData) ((ApiResponse) this.$state.element).getData()).getState()));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AdControllerManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkState$1$2", m3970f = "AdControllerManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkState$1$2 */
    /* loaded from: classes5.dex */
    public static final class C55162 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6993p$;

        C55162(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55162 c55162 = new C55162(completion);
            c55162.f6993p$ = (CoroutineScope) obj;
            return c55162;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C55162) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6993p$;
            AdControllerManager$checkState$1.this.$cb.invoke(Boxing.boxBoolean(false));
            return Unit.INSTANCE;
        }
    }
}
