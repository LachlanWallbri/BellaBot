package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "AbortIncompleteMultipartUpload")
/* loaded from: classes7.dex */
public class AbortIncompleteMultipartUpload {

    @Element(name = "DaysAfterInitiation")
    private int daysAfterInitiation;

    public AbortIncompleteMultipartUpload(@Element(name = "DaysAfterInitiation") int i) {
        this.daysAfterInitiation = i;
    }

    public int daysAfterInitiation() {
        return this.daysAfterInitiation;
    }
}
