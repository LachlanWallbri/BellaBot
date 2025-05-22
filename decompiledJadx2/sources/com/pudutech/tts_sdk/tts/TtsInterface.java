package com.pudutech.tts_sdk.tts;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: TtsInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005H&J5\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2#\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH&J\b\u0010\u0013\u001a\u00020\u0003H&¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/tts_sdk/tts/TtsInterface;", "", "genTtsFile", "", "text", "", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "extraParam", "init", "context", "Landroid/content/Context;", "initCallback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "code", "release", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface TtsInterface {
    void genTtsFile(String text, String filePath, OnTtsListener onTtsListener, String extraParam);

    void init(Context context, Function1<? super Integer, Unit> initCallback);

    void release();

    /* compiled from: TtsInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void genTtsFile$default(TtsInterface ttsInterface, String str, String str2, OnTtsListener onTtsListener, String str3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: genTtsFile");
            }
            if ((i & 8) != 0) {
                str3 = "";
            }
            ttsInterface.genTtsFile(str, str2, onTtsListener, str3);
        }
    }
}
