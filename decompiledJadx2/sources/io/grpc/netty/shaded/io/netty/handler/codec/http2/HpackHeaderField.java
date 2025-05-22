package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
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

    public final boolean equalsForTest(HpackHeaderField hpackHeaderField) {
        return HpackUtil.equalsVariableTime(this.name, hpackHeaderField.name) && HpackUtil.equalsVariableTime(this.value, hpackHeaderField.value);
    }

    public String toString() {
        return ((Object) this.name) + ": " + ((Object) this.value);
    }
}
