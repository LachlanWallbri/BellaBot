package com.pudutech.peanut.robot_ui.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.RobotContext;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: FaceAnimationUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.util.FaceAnimationUtil$loadRobotAnimation$1", m3970f = "FaceAnimationUtil.kt", m3971i = {0}, m3972l = {41}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class FaceAnimationUtil$loadRobotAnimation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7057p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FaceAnimationUtil$loadRobotAnimation$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FaceAnimationUtil$loadRobotAnimation$1 faceAnimationUtil$loadRobotAnimation$1 = new FaceAnimationUtil$loadRobotAnimation$1(completion);
        faceAnimationUtil$loadRobotAnimation$1.f7057p$ = (CoroutineScope) obj;
        return faceAnimationUtil$loadRobotAnimation$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FaceAnimationUtil$loadRobotAnimation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        ConcurrentHashMap concurrentHashMap;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7057p$;
            FaceAnimationUtil faceAnimationUtil = FaceAnimationUtil.INSTANCE;
            str = FaceAnimationUtil.TAG;
            Pdlog.m3273d(str, "reloadRobotAnimation : start");
            for (int i2 : FaceAnimationUtil.INSTANCE.getRobotAnimation()) {
                int intValue = Boxing.boxInt(i2).intValue();
                Bitmap decodeResource = BitmapFactory.decodeResource(RobotContext.INSTANCE.getContext().getResources(), intValue);
                FaceAnimationUtil faceAnimationUtil2 = FaceAnimationUtil.INSTANCE;
                concurrentHashMap = FaceAnimationUtil.cacheImages;
                concurrentHashMap.put(Boxing.boxInt(intValue), decodeResource);
            }
            FaceAnimationUtil faceAnimationUtil3 = FaceAnimationUtil.INSTANCE;
            str2 = FaceAnimationUtil.TAG;
            Pdlog.m3273d(str2, "reloadRobotAnimation : end");
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C56012 c56012 = new C56012(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(main, c56012, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FaceAnimationUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.util.FaceAnimationUtil$loadRobotAnimation$1$2", m3970f = "FaceAnimationUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.util.FaceAnimationUtil$loadRobotAnimation$1$2 */
    /* loaded from: classes5.dex */
    public static final class C56012 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7058p$;

        C56012(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56012 c56012 = new C56012(completion);
            c56012.f7058p$ = (CoroutineScope) obj;
            return c56012;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56012) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ConcurrentHashMap concurrentHashMap;
            Function1 function1;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7058p$;
            FaceAnimationUtil faceAnimationUtil = FaceAnimationUtil.INSTANCE;
            FaceAnimationUtil.isLoadedRobotImage = true;
            FaceAnimationUtil faceAnimationUtil2 = FaceAnimationUtil.INSTANCE;
            FaceAnimationUtil.isLoadingRobotImage = false;
            HashMap hashMap = new HashMap();
            FaceAnimationUtil faceAnimationUtil3 = FaceAnimationUtil.INSTANCE;
            concurrentHashMap = FaceAnimationUtil.cacheImages;
            hashMap.putAll(concurrentHashMap);
            FaceAnimationUtil faceAnimationUtil4 = FaceAnimationUtil.INSTANCE;
            function1 = FaceAnimationUtil.loadRobotImageCallBack;
            if (function1 != null) {
                return (Unit) function1.invoke(hashMap);
            }
            return null;
        }
    }
}
