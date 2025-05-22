package io.opencensus.metrics.export;

import io.opencensus.metrics.data.Exemplar;
import io.opencensus.metrics.export.Distribution;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Distribution_Bucket extends Distribution.Bucket {
    private final long count;
    private final Exemplar exemplar;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution_Bucket(long j, @Nullable Exemplar exemplar) {
        this.count = j;
        this.exemplar = exemplar;
    }

    @Override // io.opencensus.metrics.export.Distribution.Bucket
    public long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Distribution.Bucket
    @Nullable
    public Exemplar getExemplar() {
        return this.exemplar;
    }

    public String toString() {
        return "Bucket{count=" + this.count + ", exemplar=" + this.exemplar + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution.Bucket)) {
            return false;
        }
        Distribution.Bucket bucket = (Distribution.Bucket) obj;
        if (this.count == bucket.getCount()) {
            Exemplar exemplar = this.exemplar;
            if (exemplar == null) {
                if (bucket.getExemplar() == null) {
                    return true;
                }
            } else if (exemplar.equals(bucket.getExemplar())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.count;
        int i = ((int) (1000003 ^ (j ^ (j >>> 32)))) * 1000003;
        Exemplar exemplar = this.exemplar;
        return (exemplar == null ? 0 : exemplar.hashCode()) ^ i;
    }
}
