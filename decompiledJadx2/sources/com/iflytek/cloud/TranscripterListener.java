package com.iflytek.cloud;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface TranscripterListener {
    void onBeginOfSpeech();

    void onEndOfSpeech();

    void onError(SpeechError speechError);

    void onEvent(int i, int i2, int i3, Bundle bundle);

    void onResult(TranscripterResult transcripterResult, boolean z);

    void onVolumeChanged(int i, byte[] bArr);
}
