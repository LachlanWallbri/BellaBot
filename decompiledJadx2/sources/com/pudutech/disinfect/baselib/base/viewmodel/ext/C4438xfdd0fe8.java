package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "t", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$2$1$1", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$$special$$inlined$runCatching$lambda$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$2$invokeSuspend$$inlined$onSuccess$lambda$1 */
/* loaded from: classes2.dex */
public final class C4438xfdd0fe8<T> extends SuspendLambda implements Function3<CoroutineScope, T, Continuation<? super Unit>, Object> {
    final /* synthetic */ BaseResponse $it$inlined;
    final /* synthetic */ CoroutineScope $this_launch$inlined;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5019p$;
    private Object p$0;
    final /* synthetic */ BaseViewModelExtKt$requestNetworkDataCallMain$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4438xfdd0fe8(Continuation continuation, BaseResponse baseResponse, BaseViewModelExtKt$requestNetworkDataCallMain$2 baseViewModelExtKt$requestNetworkDataCallMain$2, CoroutineScope coroutineScope) {
        super(3, continuation);
        this.$it$inlined = baseResponse;
        this.this$0 = baseViewModelExtKt$requestNetworkDataCallMain$2;
        this.$this_launch$inlined = coroutineScope;
    }

    public final Continuation<Unit> create(CoroutineScope create, T t, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        C4438xfdd0fe8 c4438xfdd0fe8 = new C4438xfdd0fe8(continuation, this.$it$inlined, this.this$0, this.$this_launch$inlined);
        c4438xfdd0fe8.f5019p$ = create;
        c4438xfdd0fe8.p$0 = t;
        return c4438xfdd0fe8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
        return ((C4438xfdd0fe8) create(coroutineScope, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5019p$;
            Object obj2 = this.p$0;
            Pdlog.m3273d(this.this$0.$tag, this.$it$inlined);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(obj2, null);
            this.L$0 = coroutineScope;
            this.L$1 = obj2;
            this.label = 1;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj3 = this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BaseViewModelExt.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$2$1$1$1", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$$special$$inlined$runCatching$lambda$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$2$invokeSuspend$$inlined$onSuccess$lambda$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $t */
        final /* synthetic */ Object f5020$t;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5021p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Object obj, Continuation continuation) {
            super(2, continuation);
            this.f5020$t = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f5020$t, completion);
            anonymousClass1.f5021p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5021p$;
            C4438xfdd0fe8.this.this$0.$success.invoke(this.f5020$t);
            return Unit.INSTANCE;
        }
    }
}
