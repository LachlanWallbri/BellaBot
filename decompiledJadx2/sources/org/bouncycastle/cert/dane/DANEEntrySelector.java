package org.bouncycastle.cert.dane;

import org.bouncycastle.util.Selector;

/* loaded from: classes9.dex */
public class DANEEntrySelector implements Selector {
    private final String domainName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DANEEntrySelector(String str) {
        this.domainName = str;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return this;
    }

    public String getDomainName() {
        return this.domainName;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return ((DANEEntry) obj).getDomainName().equals(this.domainName);
    }
}
