package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes7.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class Caverphone implements StringEncoder {
    private final Caverphone2 encoder = new Caverphone2();

    public String caverphone(String str) {
        return this.encoder.encode(str);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Caverphone encode is not of type java.lang.String");
        }
        return caverphone((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return caverphone(str);
    }

    public boolean isCaverphoneEqual(String str, String str2) {
        return caverphone(str).equals(caverphone(str2));
    }
}
