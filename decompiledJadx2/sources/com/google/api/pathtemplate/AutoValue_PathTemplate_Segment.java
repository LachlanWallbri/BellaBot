package com.google.api.pathtemplate;

import com.google.api.pathtemplate.PathTemplate;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_PathTemplate_Segment extends PathTemplate.Segment {
    private final String complexSeparator;
    private final PathTemplate.SegmentKind kind;
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_PathTemplate_Segment(PathTemplate.SegmentKind segmentKind, String str, String str2) {
        if (segmentKind == null) {
            throw new NullPointerException("Null kind");
        }
        this.kind = segmentKind;
        if (str == null) {
            throw new NullPointerException("Null value");
        }
        this.value = str;
        if (str2 == null) {
            throw new NullPointerException("Null complexSeparator");
        }
        this.complexSeparator = str2;
    }

    @Override // com.google.api.pathtemplate.PathTemplate.Segment
    PathTemplate.SegmentKind kind() {
        return this.kind;
    }

    @Override // com.google.api.pathtemplate.PathTemplate.Segment
    String value() {
        return this.value;
    }

    @Override // com.google.api.pathtemplate.PathTemplate.Segment
    String complexSeparator() {
        return this.complexSeparator;
    }

    public String toString() {
        return "Segment{kind=" + this.kind + ", value=" + this.value + ", complexSeparator=" + this.complexSeparator + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PathTemplate.Segment)) {
            return false;
        }
        PathTemplate.Segment segment = (PathTemplate.Segment) obj;
        return this.kind.equals(segment.kind()) && this.value.equals(segment.value()) && this.complexSeparator.equals(segment.complexSeparator());
    }

    public int hashCode() {
        return ((((this.kind.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode()) * 1000003) ^ this.complexSeparator.hashCode();
    }
}
