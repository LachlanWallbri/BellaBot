package com.pudutech.robot.module.voice;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.VoicePackInfoReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloudResponse;
import com.pudutech.robot.module.voice.VoicePackageManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$updateVoicePkgList$1", m3970f = "VoicePackageManager.kt", m3971i = {0, 1, 1}, m3972l = {66, 77}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "resp"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class VoicePackageManager$updateVoicePkgList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7236p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$updateVoicePkgList$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$updateVoicePkgList$1 voicePackageManager$updateVoicePkgList$1 = new VoicePackageManager$updateVoicePkgList$1(this.$cb, completion);
        voicePackageManager$updateVoicePkgList$1.f7236p$ = (CoroutineScope) obj;
        return voicePackageManager$updateVoicePkgList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$updateVoicePkgList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        String str2;
        VoicePackageHelper voicePackageHelper;
        List<VoicePackageInfo> list;
        HashMap hashMap;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            str = VoicePackageManager.TAG;
            Pdlog.m3274e(str, "updateVoicePkgList : " + Log.getStackTraceString(e));
            this.$cb.invoke(new VoicePackageManager.UpdateResult(false, null));
        }
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7236p$;
            NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
            VoicePackInfoReq voicePackInfoReq = new VoicePackInfoReq();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = NetWorkApiManager.CloudApiService.DefaultImpls.getVoicePackInfo$default(cloudApi, voicePackInfoReq, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        ApiResponse apiResponse = (ApiResponse) obj;
        VoicePackageManager voicePackageManager2 = VoicePackageManager.INSTANCE;
        str2 = VoicePackageManager.TAG;
        Pdlog.m3273d(str2, "updateVoicePkgList " + apiResponse);
        for (VoicePackCloud voicePackCloud : ((VoicePackCloudResponse) apiResponse.getData()).getList()) {
            VoicePackageManager voicePackageManager3 = VoicePackageManager.INSTANCE;
            hashMap = VoicePackageManager.cloudLib;
            HashMap hashMap2 = hashMap;
            Long id = voicePackCloud.getId();
            long j = 0;
            hashMap2.put(Boxing.boxLong(id != null ? id.longValue() : 0L), voicePackCloud);
            VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
            String name = voicePackCloud.getName();
            if (name == null) {
                name = "";
            }
            voicePackageInfo.setName$module_robot_voice_release(name);
            Long id2 = voicePackCloud.getId();
            voicePackageInfo.setId$module_robot_voice_release(id2 != null ? id2.longValue() : 0L);
            Long version_code = voicePackCloud.getVersion_code();
            if (version_code != null) {
                j = version_code.longValue();
            }
            voicePackageInfo.setVersion_code$module_robot_voice_release(j);
            VoicePackageManager.INSTANCE.addToAll(voicePackageInfo);
        }
        VoicePackageManager voicePackageManager4 = VoicePackageManager.INSTANCE;
        voicePackageHelper = VoicePackageManager.packageHelper;
        VoicePackageManager voicePackageManager5 = VoicePackageManager.INSTANCE;
        list = VoicePackageManager.allPackages;
        voicePackageHelper.syncRecordToSDCard(list);
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C57032 c57032 = new C57032(null);
        this.L$0 = coroutineScope;
        this.L$1 = apiResponse;
        this.label = 2;
        if (BuildersKt.withContext(main, c57032, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$updateVoicePkgList$1$2", m3970f = "VoicePackageManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.module.voice.VoicePackageManager$updateVoicePkgList$1$2 */
    /* loaded from: classes6.dex */
    public static final class C57032 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7237p$;

        C57032(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57032 c57032 = new C57032(completion);
            c57032.f7237p$ = (CoroutineScope) obj;
            return c57032;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57032) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7237p$;
            ArrayList arrayList = new ArrayList();
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            list = VoicePackageManager.allPackages;
            arrayList.addAll(list);
            VoicePackageManager$updateVoicePkgList$1.this.$cb.invoke(new VoicePackageManager.UpdateResult(true, arrayList));
            return Unit.INSTANCE;
        }
    }
}
