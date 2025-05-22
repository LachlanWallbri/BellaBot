package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$addFlTtsVoice$1", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "onComplete", "", "filePath", "", "onError", "code", "", NotificationCompat.CATEGORY_MESSAGE, "onProgress", "proses", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper$addFlTtsVoice$1 implements OnTtsListener {
    final /* synthetic */ Function1 $callBack;
    final /* synthetic */ String $md5;
    final /* synthetic */ String $text;
    final /* synthetic */ TtsVoiceHelper.TtsVoiceType $ttsVoiceType;

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onProgress(int proses) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsVoiceHelper$addFlTtsVoice$1(String str, String str2, TtsVoiceHelper.TtsVoiceType ttsVoiceType, Function1 function1) {
        this.$md5 = str;
        this.$text = str2;
        this.$ttsVoiceType = ttsVoiceType;
        this.$callBack = function1;
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onComplete(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$addFlTtsVoice$1$onComplete$1(this, filePath, null), 3, null);
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onError(int code, String msg) {
        String str;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        str = TtsVoiceHelper.TAG;
        Pdlog.m3273d(str, "addFlTtsVoice onError:" + this.$text + "---code:" + code + "---msg：" + msg);
        this.$callBack.invoke(BaseApplication.INSTANCE.getInstance().getResources().getString(C4188R.string.pdStr7_79));
    }
}
