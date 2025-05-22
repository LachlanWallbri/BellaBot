package org.bouncycastle.est;

/* loaded from: classes9.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient() throws ESTException;
}
