package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.handler.codec.CharSequenceValueConverter;
import io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.HeadersUtils;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.HashingStrategy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyHeaders extends DefaultHeaders<CharSequence, CharSequence, SpdyHeaders> implements SpdyHeaders {
    private static final DefaultHeaders.NameValidator<CharSequence> SpdyNameValidator = new DefaultHeaders.NameValidator<CharSequence>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyHeaders.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders.NameValidator
        public void validateName(CharSequence charSequence) {
            SpdyCodecUtil.validateHeaderName(charSequence);
        }
    };

    public DefaultSpdyHeaders() {
        this(true);
    }

    public DefaultSpdyHeaders(boolean z) {
        super(AsciiString.CASE_INSENSITIVE_HASHER, z ? HeaderValueConverterAndValidator.INSTANCE : CharSequenceValueConverter.INSTANCE, z ? SpdyNameValidator : DefaultHeaders.NameValidator.NOT_NULL);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders
    public String getAsString(CharSequence charSequence) {
        return HeadersUtils.getAsString(this, charSequence);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders
    public List<String> getAllAsString(CharSequence charSequence) {
        return HeadersUtils.getAllAsString(this, charSequence);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders
    public Iterator<Map.Entry<String, String>> iteratorAsString() {
        return HeadersUtils.iteratorAsString(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        return contains(charSequence, charSequence2, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders
    public boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return contains((DefaultSpdyHeaders) charSequence, charSequence2, (HashingStrategy<? super CharSequence>) (z ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class HeaderValueConverterAndValidator extends CharSequenceValueConverter {
        public static final HeaderValueConverterAndValidator INSTANCE = new HeaderValueConverterAndValidator();

        private HeaderValueConverterAndValidator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.CharSequenceValueConverter, io.grpc.netty.shaded.io.netty.handler.codec.ValueConverter
        public CharSequence convertObject(Object obj) {
            CharSequence convertObject = super.convertObject(obj);
            SpdyCodecUtil.validateHeaderValue(convertObject);
            return convertObject;
        }
    }
}
