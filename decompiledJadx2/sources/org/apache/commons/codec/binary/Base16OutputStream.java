package org.apache.commons.codec.binary;

import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes5.dex */
public class Base16OutputStream extends BaseNCodecOutputStream {
    public Base16OutputStream(OutputStream outputStream) {
        this(outputStream, true);
    }

    public Base16OutputStream(OutputStream outputStream, boolean z) {
        this(outputStream, z, false);
    }

    public Base16OutputStream(OutputStream outputStream, boolean z, boolean z2) {
        this(outputStream, z, z2, CodecPolicy.LENIENT);
    }

    public Base16OutputStream(OutputStream outputStream, boolean z, boolean z2, CodecPolicy codecPolicy) {
        super(outputStream, new Base16(z2, codecPolicy), z);
    }
}
