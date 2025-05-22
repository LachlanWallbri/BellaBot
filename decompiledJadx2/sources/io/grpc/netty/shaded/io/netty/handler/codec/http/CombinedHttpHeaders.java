package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.ValueConverter;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.HashingStrategy;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CombinedHttpHeaders extends DefaultHttpHeaders {
    public CombinedHttpHeaders(boolean z) {
        super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, valueConverter(z), nameValidator(z)));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders
    public boolean containsValue(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return super.containsValue(charSequence, StringUtil.trimOws(charSequence2), z);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class CombinedHttpHeadersImpl extends DefaultHeaders<CharSequence, CharSequence, CombinedHttpHeadersImpl> {
        private static final int VALUE_LENGTH_ESTIMATE = 10;
        private CsvValueEscaper<CharSequence> charSequenceEscaper;
        private CsvValueEscaper<Object> objectEscaper;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public interface CsvValueEscaper<T> {
            CharSequence escape(T t);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers add(Headers headers) {
            return add((Headers<? extends CharSequence, ? extends CharSequence, ?>) headers);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers add(Object obj, Iterable iterable) {
            return add((CharSequence) obj, (Iterable<? extends CharSequence>) iterable);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers addObject(Object obj, Iterable iterable) {
            return addObject((CharSequence) obj, (Iterable<?>) iterable);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers set(Headers headers) {
            return set((Headers<? extends CharSequence, ? extends CharSequence, ?>) headers);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers set(Object obj, Iterable iterable) {
            return set((CharSequence) obj, (Iterable<? extends CharSequence>) iterable);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers setAll(Headers headers) {
            return setAll((Headers<? extends CharSequence, ? extends CharSequence, ?>) headers);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public /* bridge */ /* synthetic */ Headers setObject(Object obj, Iterable iterable) {
            return setObject((CharSequence) obj, (Iterable<?>) iterable);
        }

        private CsvValueEscaper<Object> objectEscaper() {
            if (this.objectEscaper == null) {
                this.objectEscaper = new CsvValueEscaper<Object>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.CombinedHttpHeaders.CombinedHttpHeadersImpl.1
                    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.CombinedHttpHeaders.CombinedHttpHeadersImpl.CsvValueEscaper
                    public CharSequence escape(Object obj) {
                        return StringUtil.escapeCsv((CharSequence) CombinedHttpHeadersImpl.this.valueConverter().convertObject(obj), true);
                    }
                };
            }
            return this.objectEscaper;
        }

        private CsvValueEscaper<CharSequence> charSequenceEscaper() {
            if (this.charSequenceEscaper == null) {
                this.charSequenceEscaper = new CsvValueEscaper<CharSequence>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.CombinedHttpHeaders.CombinedHttpHeadersImpl.2
                    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.CombinedHttpHeaders.CombinedHttpHeadersImpl.CsvValueEscaper
                    public CharSequence escape(CharSequence charSequence) {
                        return StringUtil.escapeCsv(charSequence, true);
                    }
                };
            }
            return this.charSequenceEscaper;
        }

        CombinedHttpHeadersImpl(HashingStrategy<CharSequence> hashingStrategy, ValueConverter<CharSequence> valueConverter, DefaultHeaders.NameValidator<CharSequence> nameValidator) {
            super(hashingStrategy, valueConverter, nameValidator);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders
        public Iterator<CharSequence> valueIterator(CharSequence charSequence) {
            Iterator<CharSequence> valueIterator = super.valueIterator((CombinedHttpHeadersImpl) charSequence);
            if (!valueIterator.hasNext() || cannotBeCombined(charSequence)) {
                return valueIterator;
            }
            Iterator<CharSequence> it = StringUtil.unescapeCsvFields(valueIterator.next()).iterator();
            if (valueIterator.hasNext()) {
                throw new IllegalStateException("CombinedHttpHeaders should only have one value");
            }
            return it;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public List<CharSequence> getAll(CharSequence charSequence) {
            List<CharSequence> all = super.getAll((CombinedHttpHeadersImpl) charSequence);
            if (all.isEmpty() || cannotBeCombined(charSequence)) {
                return all;
            }
            if (all.size() != 1) {
                throw new IllegalStateException("CombinedHttpHeaders should only have one value");
            }
            return StringUtil.unescapeCsvFields(all.get(0));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl add(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
            if (headers == this) {
                throw new IllegalArgumentException("can't add to itself.");
            }
            if (headers instanceof CombinedHttpHeadersImpl) {
                if (isEmpty()) {
                    addImpl(headers);
                } else {
                    for (Map.Entry<? extends CharSequence, ? extends CharSequence> entry : headers) {
                        addEscapedValue(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                for (Map.Entry<? extends CharSequence, ? extends CharSequence> entry2 : headers) {
                    add(entry2.getKey(), entry2.getValue());
                }
            }
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl set(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
            if (headers == this) {
                return this;
            }
            clear();
            return add(headers);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl setAll(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
            if (headers == this) {
                return this;
            }
            Iterator<? extends CharSequence> it = headers.names().iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return add(headers);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl add(CharSequence charSequence, CharSequence charSequence2) {
            return addEscapedValue(charSequence, charSequenceEscaper().escape(charSequence2));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl add(CharSequence charSequence, CharSequence... charSequenceArr) {
            return addEscapedValue(charSequence, commaSeparate(charSequenceEscaper(), charSequenceArr));
        }

        public CombinedHttpHeadersImpl add(CharSequence charSequence, Iterable<? extends CharSequence> iterable) {
            return addEscapedValue(charSequence, commaSeparate(charSequenceEscaper(), iterable));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl addObject(CharSequence charSequence, Object obj) {
            return addEscapedValue(charSequence, commaSeparate(objectEscaper(), obj));
        }

        public CombinedHttpHeadersImpl addObject(CharSequence charSequence, Iterable<?> iterable) {
            return addEscapedValue(charSequence, commaSeparate(objectEscaper(), iterable));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl addObject(CharSequence charSequence, Object... objArr) {
            return addEscapedValue(charSequence, commaSeparate(objectEscaper(), objArr));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl set(CharSequence charSequence, CharSequence... charSequenceArr) {
            super.set((CombinedHttpHeadersImpl) charSequence, commaSeparate(charSequenceEscaper(), charSequenceArr));
            return this;
        }

        public CombinedHttpHeadersImpl set(CharSequence charSequence, Iterable<? extends CharSequence> iterable) {
            super.set((CombinedHttpHeadersImpl) charSequence, commaSeparate(charSequenceEscaper(), iterable));
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl setObject(CharSequence charSequence, Object obj) {
            super.set((CombinedHttpHeadersImpl) charSequence, commaSeparate(objectEscaper(), obj));
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders, io.grpc.netty.shaded.io.netty.handler.codec.Headers
        public CombinedHttpHeadersImpl setObject(CharSequence charSequence, Object... objArr) {
            super.set((CombinedHttpHeadersImpl) charSequence, commaSeparate(objectEscaper(), objArr));
            return this;
        }

        public CombinedHttpHeadersImpl setObject(CharSequence charSequence, Iterable<?> iterable) {
            super.set((CombinedHttpHeadersImpl) charSequence, commaSeparate(objectEscaper(), iterable));
            return this;
        }

        private static boolean cannotBeCombined(CharSequence charSequence) {
            return HttpHeaderNames.SET_COOKIE.contentEqualsIgnoreCase(charSequence);
        }

        private CombinedHttpHeadersImpl addEscapedValue(CharSequence charSequence, CharSequence charSequence2) {
            CharSequence charSequence3 = (CharSequence) super.get(charSequence);
            if (charSequence3 == null || cannotBeCombined(charSequence)) {
                super.add((CombinedHttpHeadersImpl) charSequence, charSequence2);
            } else {
                super.set((CombinedHttpHeadersImpl) charSequence, commaSeparateEscapedValues(charSequence3, charSequence2));
            }
            return this;
        }

        private static <T> CharSequence commaSeparate(CsvValueEscaper<T> csvValueEscaper, T... tArr) {
            StringBuilder sb = new StringBuilder(tArr.length * 10);
            if (tArr.length > 0) {
                int length = tArr.length - 1;
                for (int i = 0; i < length; i++) {
                    sb.append(csvValueEscaper.escape(tArr[i]));
                    sb.append(',');
                }
                sb.append(csvValueEscaper.escape(tArr[length]));
            }
            return sb;
        }

        private static <T> CharSequence commaSeparate(CsvValueEscaper<T> csvValueEscaper, Iterable<? extends T> iterable) {
            StringBuilder sb = iterable instanceof Collection ? new StringBuilder(((Collection) iterable).size() * 10) : new StringBuilder();
            Iterator<? extends T> it = iterable.iterator();
            if (it.hasNext()) {
                T next = it.next();
                while (it.hasNext()) {
                    sb.append(csvValueEscaper.escape(next));
                    sb.append(',');
                    next = it.next();
                }
                sb.append(csvValueEscaper.escape(next));
            }
            return sb;
        }

        private static CharSequence commaSeparateEscapedValues(CharSequence charSequence, CharSequence charSequence2) {
            StringBuilder sb = new StringBuilder(charSequence.length() + 1 + charSequence2.length());
            sb.append(charSequence);
            sb.append(',');
            sb.append(charSequence2);
            return sb;
        }
    }
}
