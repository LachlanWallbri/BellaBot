package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper$addNewTtsVoice$p$2<T> implements Consumer<String> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ boolean $notNeedCache;
    final /* synthetic */ String $text;
    final /* synthetic */ TtsVoiceHelper.TtsVoiceType $ttsVoiceType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsVoiceHelper$addNewTtsVoice$p$2(String str, TtsVoiceHelper.TtsVoiceType ttsVoiceType, Function1 function1, boolean z) {
        this.$text = str;
        this.$ttsVoiceType = ttsVoiceType;
        this.$callback = function1;
        this.$notNeedCache = z;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(String it) {
        String filePath;
        PdTtsSdk pdTtsSdk = PdTtsSdk.INSTANCE;
        String str = this.$text;
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        filePath = ttsVoiceHelper.getFilePath(context, it, this.$ttsVoiceType);
        pdTtsSdk.startTts(str, filePath, new C43441(it), PdTtsSdk.TtsEngine.Ifly, this.$notNeedCache);
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$addNewTtsVoice$p$2$1", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "onComplete", "", "filePath", "", "onError", "code", "", NotificationCompat.CATEGORY_MESSAGE, "onProgress", "proses", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$2$1 */
    /* loaded from: classes3.dex */
    public static final class C43441 implements OnTtsListener {
        final /* synthetic */ String $it;

        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
        public void onProgress(int proses) {
        }

        C43441(String str) {
            this.$it = str;
        }

        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
        public void onComplete(String filePath) {
            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
            if (LanguageUtils.INSTANCE.isZhTw()) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$addNewTtsVoice$p$2$1$onComplete$1(this, filePath, null), 3, null);
                return;
            }
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            String it = this.$it;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            ttsVoiceHelper.addConfigItem(filePath, it, TtsVoiceHelper$addNewTtsVoice$p$2.this.$text, TtsVoiceHelper$addNewTtsVoice$p$2.this.$ttsVoiceType);
            TtsVoiceHelper$addNewTtsVoice$p$2.this.$callback.invoke(null);
        }

        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
        public void onError(int code, String msg) {
            String str;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            str = TtsVoiceHelper.TAG;
            Pdlog.m3274e(str, "addNewTtsVoice onError , " + code + " , " + msg);
            TtsVoiceHelper$addNewTtsVoice$p$2.this.$callback.invoke(new Exception(msg));
        }
    }
}
