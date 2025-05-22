package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Metadata;
import io.grpc.netty.shaded.io.netty.handler.codec.CharSequenceValueConverter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Error;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.http2.Header;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class GrpcHttp2HeadersUtils {
    GrpcHttp2HeadersUtils() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class GrpcHttp2ServerHeadersDecoder extends DefaultHttp2HeadersDecoder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public GrpcHttp2ServerHeadersDecoder(long j) {
            super(true, j);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder
        public GrpcHttp2InboundHeaders newHeaders() {
            return new GrpcHttp2RequestHeaders(numberOfHeadersGuess());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class GrpcHttp2ClientHeadersDecoder extends DefaultHttp2HeadersDecoder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public GrpcHttp2ClientHeadersDecoder(long j) {
            super(true, j);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder
        public GrpcHttp2InboundHeaders newHeaders() {
            return new GrpcHttp2ResponseHeaders(numberOfHeadersGuess());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class GrpcHttp2InboundHeaders extends AbstractHttp2Headers {
        private static final AsciiString binaryHeaderSuffix = new AsciiString(Metadata.BINARY_HEADER_SUFFIX.getBytes(Charsets.US_ASCII));
        private byte[][] namesAndValues;
        private int namesAndValuesIdx;
        private AsciiString[] values;

        GrpcHttp2InboundHeaders(int i) {
            Preconditions.checkArgument(i > 0, "numHeadersGuess needs to be positive: %s", i);
            this.namesAndValues = new byte[i * 2];
            this.values = new AsciiString[i];
        }

        protected Http2Headers add(AsciiString asciiString, AsciiString asciiString2) {
            byte[] bytes = bytes(asciiString);
            if (!asciiString.endsWith(binaryHeaderSuffix)) {
                addHeader(asciiString2, bytes, bytes(asciiString2));
                return this;
            }
            int i = -1;
            int i2 = 0;
            while (i < asciiString2.length()) {
                int indexOf = asciiString2.indexOf(',', i2);
                int length = indexOf == -1 ? asciiString2.length() : indexOf;
                AsciiString subSequence = asciiString2.subSequence(i2, length, false);
                addHeader(subSequence, bytes, BaseEncoding.base64().decode(subSequence));
                i2 = indexOf + 1;
                i = length;
            }
            return this;
        }

        private void addHeader(AsciiString asciiString, byte[] bArr, byte[] bArr2) {
            if (this.namesAndValuesIdx == this.namesAndValues.length) {
                expandHeadersAndValues();
            }
            AsciiString[] asciiStringArr = this.values;
            int i = this.namesAndValuesIdx;
            asciiStringArr[i / 2] = asciiString;
            byte[][] bArr3 = this.namesAndValues;
            bArr3[i] = bArr;
            this.namesAndValuesIdx = i + 1;
            int i2 = this.namesAndValuesIdx;
            bArr3[i2] = bArr2;
            this.namesAndValuesIdx = i2 + 1;
        }

        protected CharSequence get(AsciiString asciiString) {
            for (int i = 0; i < this.namesAndValuesIdx; i += 2) {
                if (equals(asciiString, this.namesAndValues[i])) {
                    return this.values[i / 2];
                }
            }
            return null;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
        public CharSequence status() {
            return get(Http2Headers.PseudoHeaderName.STATUS.value());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public List<CharSequence> getAll(CharSequence charSequence) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            ArrayList arrayList = new ArrayList(4);
            for (int i = 0; i < this.namesAndValuesIdx; i += 2) {
                if (equals(requireAsciiString, this.namesAndValues[i])) {
                    arrayList.add(this.values[i / 2]);
                }
            }
            return arrayList;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public boolean remove(CharSequence charSequence) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            int i = 0;
            while (i < this.namesAndValuesIdx && !equals(requireAsciiString, this.namesAndValues[i])) {
                i += 2;
            }
            if (i >= this.namesAndValuesIdx) {
                return false;
            }
            int i2 = i;
            while (i < this.namesAndValuesIdx) {
                if (!equals(requireAsciiString, this.namesAndValues[i])) {
                    AsciiString[] asciiStringArr = this.values;
                    asciiStringArr[i2 / 2] = asciiStringArr[i / 2];
                    byte[][] bArr = this.namesAndValues;
                    bArr[i2] = bArr[i];
                    bArr[i2 + 1] = bArr[i + 1];
                    i2 += 2;
                }
                i += 2;
            }
            this.namesAndValuesIdx = i2;
            return true;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public Http2Headers set(CharSequence charSequence, CharSequence charSequence2) {
            remove(charSequence);
            return add(charSequence, charSequence2);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public Http2Headers setLong(CharSequence charSequence, long j) {
            return set(charSequence, (CharSequence) AsciiString.m3920of(CharSequenceValueConverter.INSTANCE.convertLong(j)));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[][] namesAndValues() {
            return this.namesAndValues;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public int numHeaders() {
            return this.namesAndValuesIdx / 2;
        }

        protected static boolean equals(AsciiString asciiString, byte[] bArr) {
            return equals(asciiString.array(), asciiString.arrayOffset(), asciiString.length(), bArr, 0, bArr.length);
        }

        protected static boolean equals(AsciiString asciiString, AsciiString asciiString2) {
            return equals(asciiString.array(), asciiString.arrayOffset(), asciiString.length(), asciiString2.array(), asciiString2.arrayOffset(), asciiString2.length());
        }

        protected static boolean equals(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
            if (i2 != i4) {
                return false;
            }
            return PlatformDependent.equals(bArr, i, bArr2, i3, i2);
        }

        protected static byte[] bytes(AsciiString asciiString) {
            return asciiString.isEntireArrayUsed() ? asciiString.array() : asciiString.toByteArray();
        }

        protected static AsciiString requireAsciiString(CharSequence charSequence) {
            if (!(charSequence instanceof AsciiString)) {
                throw new IllegalArgumentException("AsciiString expected. Was: " + charSequence.getClass().getName());
            }
            return (AsciiString) charSequence;
        }

        protected static boolean isPseudoHeader(AsciiString asciiString) {
            return !asciiString.isEmpty() && asciiString.charAt(0) == ':';
        }

        protected AsciiString validateName(AsciiString asciiString) {
            int arrayOffset = asciiString.arrayOffset();
            int length = asciiString.length();
            byte[] array = asciiString.array();
            for (int i = arrayOffset; i < arrayOffset + length; i++) {
                if (AsciiString.isUpperCase(array[i])) {
                    PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "invalid header name '%s'", asciiString));
                }
            }
            return asciiString;
        }

        private void expandHeadersAndValues() {
            AsciiString[] asciiStringArr = this.values;
            int max = Math.max(2, asciiStringArr.length + (asciiStringArr.length / 2));
            byte[][] bArr = new byte[max * 2];
            AsciiString[] asciiStringArr2 = new AsciiString[max];
            byte[][] bArr2 = this.namesAndValues;
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            AsciiString[] asciiStringArr3 = this.values;
            System.arraycopy(asciiStringArr3, 0, asciiStringArr2, 0, asciiStringArr3.length);
            this.namesAndValues = bArr;
            this.values = asciiStringArr2;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public int size() {
            return numHeaders();
        }

        protected static void appendNameAndValue(StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, boolean z) {
            if (z) {
                sb.append(", ");
            }
            sb.append(charSequence);
            sb.append(": ");
            sb.append(charSequence2);
        }

        protected final String namesAndValuesToString() {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (int i = 0; i < this.namesAndValuesIdx; i += 2) {
                appendNameAndValue(sb, new String(this.namesAndValues[i], Charsets.US_ASCII), this.values[i / 2], z);
                z = true;
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class GrpcHttp2RequestHeaders extends GrpcHttp2InboundHeaders {
        private AsciiString authority;
        private AsciiString method;
        private AsciiString path;
        private AsciiString scheme;

        /* renamed from: te */
        private AsciiString f8325te;
        private static final AsciiString PATH_HEADER = AsciiString.m3920of(":path");
        private static final AsciiString AUTHORITY_HEADER = AsciiString.m3920of(Header.TARGET_AUTHORITY_UTF8);
        private static final AsciiString METHOD_HEADER = AsciiString.m3920of(":method");
        private static final AsciiString SCHEME_HEADER = AsciiString.m3920of(":scheme");

        GrpcHttp2RequestHeaders(int i) {
            super(i);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public Http2Headers add(CharSequence charSequence, CharSequence charSequence2) {
            AsciiString validateName = validateName(requireAsciiString(charSequence));
            AsciiString requireAsciiString = requireAsciiString(charSequence2);
            if (isPseudoHeader(validateName)) {
                addPseudoHeader(validateName, requireAsciiString);
                return this;
            }
            if (equals(Utils.TE_HEADER, validateName)) {
                this.f8325te = requireAsciiString;
                return this;
            }
            return add(validateName, requireAsciiString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CharSequence get(CharSequence charSequence) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            Preconditions.checkArgument(!isPseudoHeader(requireAsciiString), "Use direct accessor methods for pseudo headers.");
            if (equals(Utils.TE_HEADER, requireAsciiString)) {
                return this.f8325te;
            }
            return get(requireAsciiString);
        }

        private void addPseudoHeader(CharSequence charSequence, CharSequence charSequence2) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            AsciiString requireAsciiString2 = requireAsciiString(charSequence2);
            if (equals(PATH_HEADER, requireAsciiString)) {
                this.path = requireAsciiString2;
                return;
            }
            if (equals(AUTHORITY_HEADER, requireAsciiString)) {
                this.authority = requireAsciiString2;
                return;
            }
            if (equals(METHOD_HEADER, requireAsciiString)) {
                this.method = requireAsciiString2;
            } else if (equals(SCHEME_HEADER, requireAsciiString)) {
                this.scheme = requireAsciiString2;
            } else {
                PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Illegal pseudo-header '%s' in request.", requireAsciiString));
            }
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
        public CharSequence path() {
            return this.path;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
        public CharSequence authority() {
            return this.authority;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
        public CharSequence method() {
            return this.method;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
        public CharSequence scheme() {
            return this.scheme;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders, io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public List<CharSequence> getAll(CharSequence charSequence) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            if (isPseudoHeader(requireAsciiString)) {
                throw new IllegalArgumentException("Use direct accessor methods for pseudo headers.");
            }
            if (equals(Utils.TE_HEADER, requireAsciiString)) {
                return Collections.singletonList(this.f8325te);
            }
            return super.getAll(charSequence);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders, io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public boolean remove(CharSequence charSequence) {
            AsciiString requireAsciiString = requireAsciiString(charSequence);
            if (isPseudoHeader(requireAsciiString)) {
                throw new IllegalArgumentException("Use direct accessor methods for pseudo headers.");
            }
            if (equals(Utils.TE_HEADER, requireAsciiString)) {
                boolean z = this.f8325te != null;
                this.f8325te = null;
                return z;
            }
            return super.remove((CharSequence) requireAsciiString);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders, io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public int size() {
            int i = this.path != null ? 1 : 0;
            if (this.authority != null) {
                i++;
            }
            if (this.method != null) {
                i++;
            }
            if (this.scheme != null) {
                i++;
            }
            if (this.f8325te != null) {
                i++;
            }
            return i + super.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(getClass().getSimpleName());
            sb.append('[');
            AsciiString asciiString = this.path;
            boolean z = true;
            boolean z2 = false;
            if (asciiString != null) {
                appendNameAndValue(sb, PATH_HEADER, asciiString, false);
                z2 = true;
            }
            AsciiString asciiString2 = this.authority;
            if (asciiString2 != null) {
                appendNameAndValue(sb, AUTHORITY_HEADER, asciiString2, z2);
                z2 = true;
            }
            AsciiString asciiString3 = this.method;
            if (asciiString3 != null) {
                appendNameAndValue(sb, METHOD_HEADER, asciiString3, z2);
                z2 = true;
            }
            AsciiString asciiString4 = this.scheme;
            if (asciiString4 != null) {
                appendNameAndValue(sb, SCHEME_HEADER, asciiString4, z2);
            } else {
                z = z2;
            }
            if (this.f8325te != null) {
                appendNameAndValue(sb, Utils.TE_HEADER, this.f8325te, z);
            }
            String namesAndValuesToString = namesAndValuesToString();
            if (sb.length() > 0 && namesAndValuesToString.length() > 0) {
                sb.append(", ");
            }
            sb.append(namesAndValuesToString);
            sb.append(']');
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class GrpcHttp2ResponseHeaders extends GrpcHttp2InboundHeaders {
        GrpcHttp2ResponseHeaders(int i) {
            super(i);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public Http2Headers add(CharSequence charSequence, CharSequence charSequence2) {
            return add(validateName(requireAsciiString(charSequence)), requireAsciiString(charSequence2));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractHttp2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CharSequence get(CharSequence charSequence) {
            return get(requireAsciiString(charSequence));
        }

        public String toString() {
            return getClass().getSimpleName() + '[' + namesAndValuesToString() + ']';
        }
    }
}
