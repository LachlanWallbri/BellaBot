package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedValueConverter;
import io.grpc.netty.shaded.io.netty.handler.codec.ValueConverter;
import io.grpc.netty.shaded.io.netty.util.AsciiString;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CharSequenceMap<V> extends DefaultHeaders<CharSequence, V, CharSequenceMap<V>> {
    public CharSequenceMap() {
        this(true);
    }

    public CharSequenceMap(boolean z) {
        this(z, UnsupportedValueConverter.instance());
    }

    public CharSequenceMap(boolean z, ValueConverter<V> valueConverter) {
        super(z ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter);
    }

    public CharSequenceMap(boolean z, ValueConverter<V> valueConverter, int i) {
        super(z ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter, DefaultHeaders.NameValidator.NOT_NULL, i);
    }
}
