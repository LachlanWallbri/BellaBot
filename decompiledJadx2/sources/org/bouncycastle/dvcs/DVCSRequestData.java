package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;

/* loaded from: classes9.dex */
public abstract class DVCSRequestData {
    protected Data data;

    /* JADX INFO: Access modifiers changed from: protected */
    public DVCSRequestData(Data data) {
        this.data = data;
    }

    public Data toASN1Structure() {
        return this.data;
    }
}
