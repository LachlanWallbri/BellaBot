package io.opencensus.trace;

import io.opencensus.trace.Tracestate;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Tracestate extends Tracestate {
    private final List<Tracestate.Entry> entries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tracestate(List<Tracestate.Entry> list) {
        if (list == null) {
            throw new NullPointerException("Null entries");
        }
        this.entries = list;
    }

    @Override // io.opencensus.trace.Tracestate
    public List<Tracestate.Entry> getEntries() {
        return this.entries;
    }

    public String toString() {
        return "Tracestate{entries=" + this.entries + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tracestate) {
            return this.entries.equals(((Tracestate) obj).getEntries());
        }
        return false;
    }

    public int hashCode() {
        return this.entries.hashCode() ^ 1000003;
    }
}
