package com.pudutech.robot.module.voice;

import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.Iterator;
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
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$selectPkg$1", m3970f = "VoicePackageManager.kt", m3971i = {0, 0, 1, 1}, m3972l = {102, 116}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "it", "$this$launch", "it"}, m3975s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class VoicePackageManager$selectPkg$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    final /* synthetic */ VoicePackageInfo $voice;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7235p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$selectPkg$1(VoicePackageInfo voicePackageInfo, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$voice = voicePackageInfo;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$selectPkg$1 voicePackageManager$selectPkg$1 = new VoicePackageManager$selectPkg$1(this.$voice, this.$cb, completion);
        voicePackageManager$selectPkg$1.f7235p$ = (CoroutineScope) obj;
        return voicePackageManager$selectPkg$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$selectPkg$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        VoicePackageHelper voicePackageHelper;
        VoicePackageHelper voicePackageHelper2;
        List list2;
        VoicePackageHelper voicePackageHelper3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7235p$;
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((VoicePackageInfo) it.next()).setSelected$module_robot_voice_release(false);
            }
            VoicePackageManager voicePackageManager2 = VoicePackageManager.INSTANCE;
            VoicePackageManager.selectedOne = this.$voice;
            VoicePackageInfo voicePackageInfo = this.$voice;
            if (voicePackageInfo == null || voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultID()) {
                VoicePackageManager voicePackageManager3 = VoicePackageManager.INSTANCE;
                voicePackageHelper = VoicePackageManager.packageHelper;
                voicePackageHelper.defaultPackage().setSelected$module_robot_voice_release(true);
                VoicePackageManager voicePackageManager4 = VoicePackageManager.INSTANCE;
                voicePackageHelper2 = VoicePackageManager.packageHelper;
                voicePackageHelper2.setSelectedIDRecord((String) null);
                Function1 function1 = this.$cb;
                if (function1 != null) {
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C5699x3ff2c268 c5699x3ff2c268 = new C5699x3ff2c268(null, this);
                    this.L$0 = coroutineScope;
                    this.L$1 = function1;
                    this.label = 1;
                    if (BuildersKt.withContext(main, c5699x3ff2c268, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                VoicePackageManager voicePackageManager5 = VoicePackageManager.INSTANCE;
                list2 = VoicePackageManager.allPackages;
                if (list2.contains(this.$voice)) {
                    if (this.$voice.getIsExist()) {
                        this.$voice.setSelected$module_robot_voice_release(true);
                        if (new VoiceDataSourceHelper().loadCustomVoiceToMemory$module_robot_voice_release(String.valueOf(this.$voice.getId()))) {
                            VoicePackageManager voicePackageManager6 = VoicePackageManager.INSTANCE;
                            voicePackageHelper3 = VoicePackageManager.packageHelper;
                            voicePackageHelper3.setSelectedIDRecord(String.valueOf(this.$voice.getId()));
                        }
                        Function1 function12 = this.$cb;
                        if (function12 != null) {
                            MainCoroutineDispatcher main2 = Dispatchers.getMain();
                            C5700x3ff2c269 c5700x3ff2c269 = new C5700x3ff2c269(null, this);
                            this.L$0 = coroutineScope;
                            this.L$1 = function12;
                            this.label = 2;
                            if (BuildersKt.withContext(main2, c5700x3ff2c269, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        VoicePackageManager.INSTANCE.download(this.$voice, this.$cb);
                    }
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
