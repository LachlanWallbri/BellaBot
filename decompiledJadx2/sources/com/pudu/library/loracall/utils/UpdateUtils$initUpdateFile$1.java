package com.pudu.library.loracall.utils;

import android.os.Environment;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.library.loracall.bean.LoraUpdateFile;
import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudu.library.loracall.utils.UpdateUtils$initUpdateFile$1", m3970f = "UpdateUtils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class UpdateUtils$initUpdateFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4384p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdateUtils$initUpdateFile$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateUtils$initUpdateFile$1 updateUtils$initUpdateFile$1 = new UpdateUtils$initUpdateFile$1(completion);
        updateUtils$initUpdateFile$1.f4384p$ = (CoroutineScope) obj;
        return updateUtils$initUpdateFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateUtils$initUpdateFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final Map sortUpdateFile;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final CoroutineScope coroutineScope = this.f4384p$;
        try {
            StringBuilder sb = new StringBuilder();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append(File.separator);
            sb.append("pudu/loracall");
            File file = new File(sb.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            UpdateUtils.INSTANCE.copyUpdateFile();
            sortUpdateFile = UpdateUtils.INSTANCE.sortUpdateFile(file);
            LoRaClient.INSTANCE.getInstance().getLoRaVersion(new Function1<LoRaVersionParam, Unit>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$initUpdateFile$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LoRaVersionParam loRaVersionParam) {
                    invoke2(loRaVersionParam);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final LoRaVersionParam it) {
                    List list;
                    LoraUpdateFile loraUpdateFile;
                    String absolutePath;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    KotlinUtilsKt.log$default(CoroutineScope.this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils.initUpdateFile.1.1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "当前版本信息--" + LoRaVersionParam.this;
                        }
                    }, 1, null);
                    if (!(it.getFirmwareVersion().length() > 0) || (list = (List) sortUpdateFile.get(Integer.valueOf(it.getFirmwareType()))) == null || (loraUpdateFile = (LoraUpdateFile) CollectionsKt.firstOrNull(list)) == null) {
                        return;
                    }
                    UpdateUtils updateUtils = UpdateUtils.INSTANCE;
                    File file2 = loraUpdateFile.getFile();
                    if (file2 == null || (absolutePath = file2.getAbsolutePath()) == null) {
                        return;
                    }
                    updateUtils.setUpdateFilePath(absolutePath);
                    KotlinUtilsKt.log$default(CoroutineScope.this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils.initUpdateFile.1.1.2
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "updateFilePath--" + UpdateUtils.INSTANCE.getUpdateFilePath();
                        }
                    }, 1, null);
                    final float version = loraUpdateFile.getVersion();
                    final float parseFloat = Float.parseFloat(it.getFirmwareVersion());
                    KotlinUtilsKt.log$default(CoroutineScope.this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils.initUpdateFile.1.1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "本地最高版本--" + version + "   当前版本--" + parseFloat;
                        }
                    }, 1, null);
                    if (version > parseFloat) {
                        LoRaClient.startUpdate$default(LoRaClient.INSTANCE.getInstance(), UpdateUtils.INSTANCE.getUpdateFilePath(), null, 2, null);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
