package org.apache.commons.codec.binary;

import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes5.dex */
public class Base16InputStream extends BaseNCodecInputStream {
    public Base16InputStream(InputStream inputStream) {
        this(inputStream, false);
    }

    public Base16InputStream(InputStream inputStream, boolean z) {
        this(inputStream, z, false);
    }

    public Base16InputStream(InputStream inputStream, boolean z, boolean z2) {
        this(inputStream, z, z2, CodecPolicy.LENIENT);
    }

    public Base16InputStream(InputStream inputStream, boolean z, boolean z2, CodecPolicy codecPolicy) {
        super(inputStream, new Base16(z2, codecPolicy), z);
    }
}
