package com.pudutech.tts_sdk.tts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.utils.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GoogleTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.tts_sdk.tts.GoogleTtsTask$updateLanguageConfig$2", m3970f = "GoogleTtsTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
public final class GoogleTtsTask$updateLanguageConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7516p$;
    final /* synthetic */ GoogleTtsTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GoogleTtsTask$updateLanguageConfig$2(GoogleTtsTask googleTtsTask, Continuation continuation) {
        super(2, continuation);
        this.this$0 = googleTtsTask;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GoogleTtsTask$updateLanguageConfig$2 googleTtsTask$updateLanguageConfig$2 = new GoogleTtsTask$updateLanguageConfig$2(this.this$0, completion);
        googleTtsTask$updateLanguageConfig$2.f7516p$ = (CoroutineScope) obj;
        return googleTtsTask$updateLanguageConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GoogleTtsTask$updateLanguageConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ReentrantReadWriteLock reentrantReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock2;
        ReentrantReadWriteLock reentrantReadWriteLock3;
        Map map;
        String str2;
        String str3;
        String str4;
        Map map2;
        String str5;
        Map map3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7516p$;
            try {
                try {
                    reentrantReadWriteLock3 = this.this$0.LOCK;
                    reentrantReadWriteLock3.writeLock().lock();
                    map = this.this$0.languageMap;
                    map.clear();
                    str2 = this.this$0.DIALOG_FLOW_CONFIG_DIRECTORY;
                    String str6 = str2 + File.separator;
                    StringBuilder sb = new StringBuilder();
                    sb.append(str6);
                    str3 = this.this$0.LANGUAGE_CONFIG_NAME;
                    sb.append(str3);
                    File file = new File(sb.toString());
                    if (file.exists()) {
                        String readFile = FileUtil.INSTANCE.readFile(new FileInputStream(file));
                        str5 = this.this$0.TAG;
                        Pdlog.m3273d(str5, "updateLanguageConfig load config languege = " + readFile);
                        Object fromJson = new Gson().fromJson(readFile, new TypeToken<HashMap<String, String>>() { // from class: com.pudutech.tts_sdk.tts.GoogleTtsTask$updateLanguageConfig$2$mapConfig$1
                        }.getType());
                        Intrinsics.checkExpressionValueIsNotNull(fromJson, "Gson().fromJson(\n       …{}.type\n                )");
                        map3 = this.this$0.languageMap;
                        map3.putAll((HashMap) fromJson);
                    }
                    str4 = this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("updateLanguageConfig languageMap size = ");
                    map2 = this.this$0.languageMap;
                    sb2.append(map2.size());
                    Pdlog.m3273d(str4, sb2.toString());
                } catch (Exception e) {
                    str = this.this$0.TAG;
                    Pdlog.m3274e(str, "updateLanguageConfig e = " + e.getMessage());
                }
                reentrantReadWriteLock2 = this.this$0.LOCK;
                reentrantReadWriteLock2.writeLock().unlock();
                return Unit.INSTANCE;
            } catch (Throwable th) {
                reentrantReadWriteLock = this.this$0.LOCK;
                reentrantReadWriteLock.writeLock().unlock();
                throw th;
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
