package com.pudutech.tts_sdk.tts;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: TtsInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "", "onComplete", "", "filePath", "", "onError", "code", "", NotificationCompat.CATEGORY_MESSAGE, "onProgress", "proses", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface OnTtsListener {
    void onComplete(String filePath);

    void onError(int code, String msg);

    void onProgress(int proses);
}
