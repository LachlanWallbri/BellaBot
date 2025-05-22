package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import java.util.List;
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
/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addFlTtsVoice$1$onComplete$1", m3970f = "TtsVoiceHelper.kt", m3971i = {0, 0, 0, 0}, m3972l = {461}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "ttsVoiceData", "ttsConfigData", "ttsVoiceList"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper$addFlTtsVoice$1$onComplete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $filePath;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4931p$;
    final /* synthetic */ TtsVoiceHelper$addFlTtsVoice$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceHelper$addFlTtsVoice$1$onComplete$1(TtsVoiceHelper$addFlTtsVoice$1 ttsVoiceHelper$addFlTtsVoice$1, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ttsVoiceHelper$addFlTtsVoice$1;
        this.$filePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TtsVoiceHelper$addFlTtsVoice$1$onComplete$1 ttsVoiceHelper$addFlTtsVoice$1$onComplete$1 = new TtsVoiceHelper$addFlTtsVoice$1$onComplete$1(this.this$0, this.$filePath, completion);
        ttsVoiceHelper$addFlTtsVoice$1$onComplete$1.f4931p$ = (CoroutineScope) obj;
        return ttsVoiceHelper$addFlTtsVoice$1$onComplete$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TtsVoiceHelper$addFlTtsVoice$1$onComplete$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$TtsConfigData, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4931p$;
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            str = TtsVoiceHelper.TAG;
            Pdlog.m3273d(str, "addFlTtsVoice:onComplete:" + this.$filePath);
            TtsVoiceData ttsVoiceData = TtsVoiceFlHelper.INSTANCE.getTtsVoiceData(this.$filePath, this.this$0.$md5, this.this$0.$text, this.this$0.$ttsVoiceType);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new TtsVoiceHelper.TtsConfigData(this.$filePath, this.this$0.$md5, this.this$0.$text, false, false, 16, null);
            List<TtsVoiceData> ttsVoiceList = TtsVoiceFlHelper.INSTANCE.getTtsVoiceList(this.this$0.$ttsVoiceType);
            if (ttsVoiceList == null || ttsVoiceList.size() <= 0) {
                ttsVoiceData.setSelect(true);
                ((TtsVoiceHelper.TtsConfigData) objectRef.element).setSelect(true);
            }
            TtsVoiceFlHelper.INSTANCE.addConfigItem(ttsVoiceData);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C43431 c43431 = new C43431(objectRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = ttsVoiceData;
            this.L$2 = objectRef;
            this.L$3 = ttsVoiceList;
            this.label = 1;
            if (BuildersKt.withContext(main, c43431, this) == coroutine_suspended) {
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
    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addFlTtsVoice$1$onComplete$1$1", m3970f = "TtsVoiceHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addFlTtsVoice$1$onComplete$1$1 */
    /* loaded from: classes3.dex */
    public static final class C43431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $ttsConfigData;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4932p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C43431(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$ttsConfigData = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43431 c43431 = new C43431(this.$ttsConfigData, completion);
            c43431.f4932p$ = (CoroutineScope) obj;
            return c43431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4932p$;
            TtsVoiceHelper.INSTANCE.setVoiceList(TtsVoiceHelper$addFlTtsVoice$1$onComplete$1.this.this$0.$ttsVoiceType, TtsVoiceHelper$addFlTtsVoice$1$onComplete$1.this.this$0.$text, (TtsVoiceHelper.TtsConfigData) this.$ttsConfigData.element);
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            str = TtsVoiceHelper.TAG;
            Pdlog.m3273d(str, "setVoiceList:text:" + TtsVoiceHelper$addFlTtsVoice$1$onComplete$1.this.this$0.$text + "---ttsConfigData:" + ((TtsVoiceHelper.TtsConfigData) this.$ttsConfigData.element));
            TtsVoiceHelper$addFlTtsVoice$1$onComplete$1.this.this$0.$callBack.invoke(null);
            return Unit.INSTANCE;
        }
    }
}
