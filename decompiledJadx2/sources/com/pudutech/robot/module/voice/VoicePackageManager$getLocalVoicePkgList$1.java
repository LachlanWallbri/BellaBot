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
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$getLocalVoicePkgList$1", m3970f = "VoicePackageManager.kt", m3971i = {0, 0}, m3972l = {52}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "record"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
public final class VoicePackageManager$getLocalVoicePkgList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7231p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$getLocalVoicePkgList$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$getLocalVoicePkgList$1 voicePackageManager$getLocalVoicePkgList$1 = new VoicePackageManager$getLocalVoicePkgList$1(this.$cb, completion);
        voicePackageManager$getLocalVoicePkgList$1.f7231p$ = (CoroutineScope) obj;
        return voicePackageManager$getLocalVoicePkgList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$getLocalVoicePkgList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VoicePackageHelper voicePackageHelper;
        List list;
        List list2;
        VoicePackageHelper voicePackageHelper2;
        VoicePackageHelper voicePackageHelper3;
        VoicePackageHelper voicePackageHelper4;
        List list3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7231p$;
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            voicePackageHelper = VoicePackageManager.packageHelper;
            voicePackageHelper.cleanDownloads();
            VoicePackageManager voicePackageManager2 = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            list.clear();
            VoicePackageManager voicePackageManager3 = VoicePackageManager.INSTANCE;
            list2 = VoicePackageManager.allPackages;
            VoicePackageManager voicePackageManager4 = VoicePackageManager.INSTANCE;
            voicePackageHelper2 = VoicePackageManager.packageHelper;
            list2.add(voicePackageHelper2.defaultPackage());
            VoicePackageManager voicePackageManager5 = VoicePackageManager.INSTANCE;
            voicePackageHelper3 = VoicePackageManager.packageHelper;
            voicePackageHelper3.loadRecord();
            VoicePackageManager voicePackageManager6 = VoicePackageManager.INSTANCE;
            voicePackageHelper4 = VoicePackageManager.packageHelper;
            List<VoicePackageInfo> downloadList = voicePackageHelper4.getDownloadList();
            VoicePackageManager voicePackageManager7 = VoicePackageManager.INSTANCE;
            list3 = VoicePackageManager.allPackages;
            list3.addAll(downloadList);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C57021 c57021 = new C57021(null);
            this.L$0 = coroutineScope;
            this.L$1 = downloadList;
            this.label = 1;
            if (BuildersKt.withContext(main, c57021, this) == coroutine_suspended) {
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
    @DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$getLocalVoicePkgList$1$1", m3970f = "VoicePackageManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.module.voice.VoicePackageManager$getLocalVoicePkgList$1$1 */
    /* loaded from: classes6.dex */
    public static final class C57021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7232p$;

        C57021(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57021 c57021 = new C57021(completion);
            c57021.f7232p$ = (CoroutineScope) obj;
            return c57021;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7232p$;
            ArrayList arrayList = new ArrayList();
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            arrayList.addAll(list);
            VoicePackageManager$getLocalVoicePkgList$1.this.$cb.invoke(arrayList);
            return Unit.INSTANCE;
        }
    }
}
