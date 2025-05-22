package io.opencensus.tags;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_TagKey extends TagKey {
    private final String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TagKey(String str) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
    }

    @Override // io.opencensus.tags.TagKey
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "TagKey{name=" + this.name + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagKey) {
            return this.name.equals(((TagKey) obj).getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }
}
