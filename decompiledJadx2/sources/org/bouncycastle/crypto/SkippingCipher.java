package org.bouncycastle.crypto;

/* loaded from: classes9.dex */
public interface SkippingCipher {
    long getPosition();

    long seekTo(long j);

    long skip(long j);
}
