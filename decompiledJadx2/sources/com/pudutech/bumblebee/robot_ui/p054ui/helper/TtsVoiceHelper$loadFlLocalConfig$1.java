package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import java.io.File;
import java.util.ArrayList;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$loadFlLocalConfig$1", m3970f = "TtsVoiceHelper.kt", m3971i = {0, 0, 0, 0, 0, 0}, m3972l = {265}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "type", "pathList", "list"}, m3975s = {"L$0", "L$1", "L$3", "L$4", "L$5", "L$6"})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper$loadFlLocalConfig$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4937p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsVoiceHelper$loadFlLocalConfig$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TtsVoiceHelper$loadFlLocalConfig$1 ttsVoiceHelper$loadFlLocalConfig$1 = new TtsVoiceHelper$loadFlLocalConfig$1(completion);
        ttsVoiceHelper$loadFlLocalConfig$1.f4937p$ = (CoroutineScope) obj;
        return ttsVoiceHelper$loadFlLocalConfig$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TtsVoiceHelper$loadFlLocalConfig$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0113 -> B:5:0x0116). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope;
        int i;
        TtsVoiceHelper.TtsVoiceType[] ttsVoiceTypeArr;
        TtsVoiceHelper$loadFlLocalConfig$1 ttsVoiceHelper$loadFlLocalConfig$1;
        int length;
        TtsVoiceHelper.TtsVoiceType[] ttsVoiceTypeArr2;
        String str;
        String str2;
        ArrayList listByType;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        char c = 0;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f4937p$;
            TtsVoiceHelper.TtsVoiceType[] values = TtsVoiceHelper.TtsVoiceType.values();
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope2;
            i = 0;
            ttsVoiceTypeArr = values;
            ttsVoiceHelper$loadFlLocalConfig$1 = this;
            length = values.length;
            ttsVoiceTypeArr2 = ttsVoiceTypeArr;
            if (i < length) {
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$1;
            length = this.I$0;
            ttsVoiceTypeArr2 = (TtsVoiceHelper.TtsVoiceType[]) this.L$2;
            ttsVoiceTypeArr = (TtsVoiceHelper.TtsVoiceType[]) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            ttsVoiceHelper$loadFlLocalConfig$1 = this;
            int i4 = i3;
            char c2 = 0;
            i += i4;
            i3 = i4;
            c = c2;
            if (i < length) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType = ttsVoiceTypeArr2[i];
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                str = TtsVoiceHelper.TAG;
                Object[] objArr = new Object[i3];
                objArr[c] = "loadFlLocalConfig--" + ttsVoiceType.name();
                Pdlog.m3274e(str, objArr);
                List<TtsVoiceData> ttsVoiceList = TtsVoiceFlHelper.INSTANCE.getTtsVoiceList(ttsVoiceType);
                if (ttsVoiceList != null && !ttsVoiceList.isEmpty()) {
                    listByType = TtsVoiceHelper.INSTANCE.getListByType(ttsVoiceType);
                    for (TtsVoiceData ttsVoiceData : ttsVoiceList) {
                        if (new File(ttsVoiceData.getFliePath()).exists()) {
                            listByType.add(TtsVoiceFlHelper.INSTANCE.getTtsConfigData(ttsVoiceData));
                        } else {
                            TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                            str3 = TtsVoiceHelper.TAG;
                            Object[] objArr2 = new Object[i3];
                            objArr2[0] = "initLocalConfig " + ttsVoiceType.name() + " ,check file failed, " + ttsVoiceData;
                            Pdlog.m3274e(str3, objArr2);
                        }
                        i3 = 1;
                    }
                    if (listByType.size() > 0) {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        TtsVoiceHelper$loadFlLocalConfig$1$1$2 ttsVoiceHelper$loadFlLocalConfig$1$1$2 = new TtsVoiceHelper$loadFlLocalConfig$1$1$2(ttsVoiceType, null);
                        ttsVoiceHelper$loadFlLocalConfig$1.L$0 = coroutineScope;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$1 = ttsVoiceTypeArr;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$2 = ttsVoiceTypeArr2;
                        ttsVoiceHelper$loadFlLocalConfig$1.I$0 = length;
                        ttsVoiceHelper$loadFlLocalConfig$1.I$1 = i;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$3 = ttsVoiceType;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$4 = ttsVoiceType;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$5 = ttsVoiceList;
                        ttsVoiceHelper$loadFlLocalConfig$1.L$6 = listByType;
                        i3 = 1;
                        ttsVoiceHelper$loadFlLocalConfig$1.label = 1;
                        if (BuildersKt.withContext(main, ttsVoiceHelper$loadFlLocalConfig$1$1$2, ttsVoiceHelper$loadFlLocalConfig$1) == obj2) {
                            return obj2;
                        }
                        int i42 = i3;
                        char c22 = 0;
                    } else {
                        c22 = 0;
                        i42 = 1;
                    }
                } else {
                    TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                    str2 = TtsVoiceHelper.TAG;
                    Object[] objArr3 = new Object[i3];
                    c22 = 0;
                    objArr3[0] = "loadFlLocalConfig--" + ttsVoiceType.name() + " 的数据为空";
                    Pdlog.m3274e(str2, objArr3);
                    i42 = 1;
                }
                i += i42;
                i3 = i42;
                c = c22;
                if (i < length) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
