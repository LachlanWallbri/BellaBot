package org.bouncycastle.operator;

import java.io.OutputStream;

/* loaded from: classes9.dex */
public interface AADProcessor {
    OutputStream getAADStream();

    byte[] getMAC();
}
