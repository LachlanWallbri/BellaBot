package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class HpackHeaderField {
    static final int HEADER_ENTRY_OVERHEAD = 32;
    final CharSequence name;
    final CharSequence value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long sizeOf(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence.length() + charSequence2.length() + 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HpackHeaderField(CharSequence charSequence, CharSequence charSequence2) {
        this.name = (CharSequence) ObjectUtil.checkNotNull(charSequence, "name");
        this.value = (CharSequence) ObjectUtil.checkNotNull(charSequence2, ES6Iterator.VALUE_PROPERTY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int size() {
        return this.name.length() + this.value.length() + 32;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HpackHeaderField)) {
            return false;
        }
        HpackHeaderField hpackHeaderField = (HpackHeaderField) obj;
        return (HpackUtil.equalsConstantTime(this.value, hpackHeaderField.value) & HpackUtil.equalsConstantTime(this.name, hpackHeaderField.name)) != 0;
    }

    public String toString() {
        return ((Object) this.name) + ": " + ((Object) this.value);
    }
}
