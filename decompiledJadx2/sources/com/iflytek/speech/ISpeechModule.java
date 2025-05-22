package com.iflytek.speech;

import android.content.Intent;

/* loaded from: classes3.dex */
public interface ISpeechModule {
    boolean destory();

    Intent getIntent();

    String getParameter(String str);

    boolean isAvailable();

    int setParameter(String str, String str2);
}
