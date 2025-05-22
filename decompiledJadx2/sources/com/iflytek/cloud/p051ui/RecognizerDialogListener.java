package com.iflytek.cloud.p051ui;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;

/* loaded from: classes3.dex */
public interface RecognizerDialogListener {
    void onError(SpeechError speechError);

    void onResult(RecognizerResult recognizerResult, boolean z);
}
