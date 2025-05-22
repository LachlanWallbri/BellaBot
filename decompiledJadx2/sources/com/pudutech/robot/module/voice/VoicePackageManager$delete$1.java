package com.pudutech.robot.module.voice;

import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$delete$1", m3970f = "VoicePackageManager.kt", m3971i = {0}, m3972l = {170}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class VoicePackageManager$delete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    final /* synthetic */ VoicePackageInfo $theOne;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7227p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$delete$1(VoicePackageInfo voicePackageInfo, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$theOne = voicePackageInfo;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$delete$1 voicePackageManager$delete$1 = new VoicePackageManager$delete$1(this.$theOne, this.$cb, completion);
        voicePackageManager$delete$1.f7227p$ = (CoroutineScope) obj;
        return voicePackageManager$delete$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$delete$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VoicePackageHelper voicePackageHelper;
        VoicePackageHelper voicePackageHelper2;
        List<VoicePackageInfo> list;
        VoicePackageHelper voicePackageHelper3;
        VoicePackageHelper voicePackageHelper4;
        VoicePackageHelper voicePackageHelper5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7227p$;
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            voicePackageHelper = VoicePackageManager.packageHelper;
            voicePackageHelper.deletePackFile(String.valueOf(this.$theOne.getId()));
            this.$theOne.setExist$module_robot_voice_release(false);
            if (this.$theOne.getSelected()) {
                VoicePackageManager voicePackageManager2 = VoicePackageManager.INSTANCE;
                voicePackageHelper3 = VoicePackageManager.packageHelper;
                voicePackageHelper3.defaultPackage().setSelected$module_robot_voice_release(true);
                VoicePackageManager voicePackageManager3 = VoicePackageManager.INSTANCE;
                VoicePackageManager voicePackageManager4 = VoicePackageManager.INSTANCE;
                voicePackageHelper4 = VoicePackageManager.packageHelper;
                VoicePackageManager.selectedOne = voicePackageHelper4.defaultPackage();
                VoicePackageManager voicePackageManager5 = VoicePackageManager.INSTANCE;
                voicePackageHelper5 = VoicePackageManager.packageHelper;
                voicePackageHelper5.setSelectedIDRecord((String) null);
                this.$theOne.setSelected$module_robot_voice_release(false);
            }
            this.$theOne.setNewVersionAvailable$module_robot_voice_release(false);
            VoicePackageManager voicePackageManager6 = VoicePackageManager.INSTANCE;
            voicePackageHelper2 = VoicePackageManager.packageHelper;
            VoicePackageManager voicePackageManager7 = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            voicePackageHelper2.syncRecordToSDCard(list);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C57011 c57011 = new C57011(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(main, c57011, this) == coroutine_suspended) {
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
    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$delete$1$1", m3970f = "VoicePackageManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.module.voice.VoicePackageManager$delete$1$1 */
    /* loaded from: classes6.dex */
    public static final class C57011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7228p$;

        C57011(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57011 c57011 = new C57011(completion);
            c57011.f7228p$ = (CoroutineScope) obj;
            return c57011;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7228p$;
            ArrayList arrayList = new ArrayList();
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            arrayList.addAll(list);
            VoicePackageManager$delete$1.this.$cb.invoke(arrayList);
            return Unit.INSTANCE;
        }
    }
}
