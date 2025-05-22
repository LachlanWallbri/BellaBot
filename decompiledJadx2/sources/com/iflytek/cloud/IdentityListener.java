package com.iflytek.cloud;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface IdentityListener {
    void onError(SpeechError speechError);

    void onEvent(int i, int i2, int i3, Bundle bundle);

    void onResult(IdentityResult identityResult, boolean z);
}
