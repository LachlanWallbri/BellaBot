package com.pudutech.mic_array.mic;

/* loaded from: classes5.dex */
public interface IAudioListener {
    void onRawData(byte[] bArr);

    void onSingleData(byte[] bArr);
}
