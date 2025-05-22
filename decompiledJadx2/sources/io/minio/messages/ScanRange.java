package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ScanRange")
/* loaded from: classes7.dex */
public class ScanRange {

    @Element(name = "End", required = false)
    private Long end;

    @Element(name = "Start", required = false)
    private Long start;

    public ScanRange(Long l, Long l2) {
        this.start = l;
        this.end = l2;
    }
}
