package com.iflytek.cloud;

/* loaded from: classes3.dex */
public interface TextUnderstanderListener {
    void onError(SpeechError speechError);

    void onResult(UnderstanderResult understanderResult);
}
