package com.pudutech.easynodelib;

import com.pudutech.base.Pdlog;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes5.dex
 */
/* compiled from: EasyNode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.easynodelib.EasyNode$initEasyNode$1", m3970f = "EasyNode.kt", m3971i = {0, 0}, m3972l = {40}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "reu"}, m3975s = {"L$0", "Z$0"})
/* loaded from: classes.dex */
public final class EasyNode$initEasyNode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $ret;
    Object L$0;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5093p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EasyNode$initEasyNode$1(Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.$ret = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EasyNode$initEasyNode$1 easyNode$initEasyNode$1 = new EasyNode$initEasyNode$1(this.$ret, completion);
        easyNode$initEasyNode$1.f5093p$ = (CoroutineScope) obj;
        return easyNode$initEasyNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EasyNode$initEasyNode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int initTheEasyNode;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5093p$;
            Pdlog.m3273d(EasyNode.TAG, "launchEspService " + EasyNode.INSTANCE.executeCommand("killall easynode_server"));
            boolean executeCommand = EasyNode.INSTANCE.executeCommand("easynode_server&");
            Pdlog.m3273d(EasyNode.TAG, "launchEspService " + executeCommand);
            Pdlog.m3273d(EasyNode.TAG, "launchEspService delay end---");
            this.L$0 = coroutineScope;
            this.Z$0 = executeCommand;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Ref.IntRef intRef = this.$ret;
        initTheEasyNode = EasyNode.INSTANCE.initTheEasyNode();
        intRef.element = initTheEasyNode;
        if (this.$ret.element == 0) {
            EasyNode easyNode = EasyNode.INSTANCE;
            EasyNode.initStatus = true;
        }
        return Unit.INSTANCE;
    }

    /* compiled from: EasyNode.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.easynodelib.EasyNode$initEasyNode$1$1", m3970f = "EasyNode.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.easynodelib.EasyNode$initEasyNode$1$1 */
    /* loaded from: classes5.dex */
    static final class C44511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $path;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5094p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C44511(String str, Continuation continuation) {
            super(2, continuation);
            this.$path = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44511 c44511 = new C44511(this.$path, completion);
            c44511.f5094p$ = (CoroutineScope) obj;
            return c44511;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5094p$;
            Pdlog.m3273d(EasyNode.TAG, "mount result = " + EasyNode.INSTANCE.executeCommand("chmod 777 " + this.$path));
            EasyNode easyNode = EasyNode.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("cd ");
            File filesDir = EasyNode$initEasyNode$1.this.$context.getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
            sb.append(filesDir.getAbsolutePath());
            Pdlog.m3273d(EasyNode.TAG, "launchEspService " + easyNode.executeCommand(sb.toString()));
            Pdlog.m3273d(EasyNode.TAG, "launchEspService " + EasyNode.INSTANCE.executeCommand(this.$path + " &"));
            return Unit.INSTANCE;
        }
    }
}
