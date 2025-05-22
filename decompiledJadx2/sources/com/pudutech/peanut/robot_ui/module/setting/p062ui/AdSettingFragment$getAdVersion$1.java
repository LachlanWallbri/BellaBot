package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.widget.TextView;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RespApkVersionData;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.AdControllerManager;
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
/* compiled from: AdSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$getAdVersion$1", m3970f = "AdSettingFragment.kt", m3971i = {0, 1, 1}, m3972l = {169, 170}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "v"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class AdSettingFragment$getAdVersion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7017p$;
    final /* synthetic */ AdSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdSettingFragment$getAdVersion$1(AdSettingFragment adSettingFragment, Continuation continuation) {
        super(2, continuation);
        this.this$0 = adSettingFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdSettingFragment$getAdVersion$1 adSettingFragment$getAdVersion$1 = new AdSettingFragment$getAdVersion$1(this.this$0, completion);
        adSettingFragment$getAdVersion$1.f7017p$ = (CoroutineScope) obj;
        return adSettingFragment$getAdVersion$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdSettingFragment$getAdVersion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r7v5, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7017p$;
            objectRef = new Ref.ObjectRef();
            AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.L$2 = objectRef;
            this.label = 1;
            obj = adControllerManager.getAdAppVersion(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef2 = objectRef;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef = (Ref.ObjectRef) this.L$2;
            objectRef2 = (Ref.ObjectRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        objectRef.element = (ApiResponse) obj;
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C55261 c55261 = new C55261(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c55261, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AdSettingFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$getAdVersion$1$1", m3970f = "AdSettingFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$getAdVersion$1$1 */
    /* loaded from: classes5.dex */
    public static final class C55261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $v */
        final /* synthetic */ Ref.ObjectRef f7018$v;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7019p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C55261(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.f7018$v = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55261 c55261 = new C55261(this.f7018$v, completion);
            c55261.f7019p$ = (CoroutineScope) obj;
            return c55261;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C55261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7019p$;
                if (AdSettingFragment$getAdVersion$1.this.this$0.isDetached() || AdSettingFragment$getAdVersion$1.this.this$0.getContext() == null) {
                    return Unit.INSTANCE;
                }
                TextView current_version_tv = (TextView) AdSettingFragment$getAdVersion$1.this.this$0._$_findCachedViewById(C5508R.id.current_version_tv);
                Intrinsics.checkExpressionValueIsNotNull(current_version_tv, "current_version_tv");
                current_version_tv.setText(((RespApkVersionData) ((ApiResponse) this.f7018$v.element).getData()).getVersionName());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
