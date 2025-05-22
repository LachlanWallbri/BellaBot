package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "NoncurrentVersionExpiration")
/* loaded from: classes7.dex */
public class NoncurrentVersionExpiration {

    @Element(name = "NoncurrentDays")
    private int noncurrentDays;

    public NoncurrentVersionExpiration(@Element(name = "NoncurrentDays", required = false) int i) {
        this.noncurrentDays = i;
    }

    public int noncurrentDays() {
        return this.noncurrentDays;
    }
}
