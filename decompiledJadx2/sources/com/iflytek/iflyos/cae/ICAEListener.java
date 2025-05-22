package com.iflytek.iflyos.cae;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public interface ICAEListener {
    void onAudioCallback(byte[] bArr, int i);

    void onIvwAudioCallback(byte[] bArr, int i);

    void onWakeup(int i, int i2);
}
