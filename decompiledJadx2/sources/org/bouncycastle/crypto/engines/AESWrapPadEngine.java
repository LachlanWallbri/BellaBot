package org.bouncycastle.crypto.engines;

/* loaded from: classes9.dex */
public class AESWrapPadEngine extends RFC5649WrapEngine {
    public AESWrapPadEngine() {
        super(new AESEngine());
    }
}
