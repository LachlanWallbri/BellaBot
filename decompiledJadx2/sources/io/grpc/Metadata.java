package io.grpc;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Metadata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String BINARY_HEADER_SUFFIX = "-bin";
    private Object[] namesAndValues;
    private int size;
    public static final BinaryMarshaller<byte[]> BINARY_BYTE_MARSHALLER = new BinaryMarshaller<byte[]>() { // from class: io.grpc.Metadata.1
        @Override // io.grpc.Metadata.BinaryMarshaller
        public byte[] parseBytes(byte[] bArr) {
            return bArr;
        }

        @Override // io.grpc.Metadata.BinaryMarshaller
        public byte[] toBytes(byte[] bArr) {
            return bArr;
        }
    };
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() { // from class: io.grpc.Metadata.2
        @Override // io.grpc.Metadata.AsciiMarshaller
        public String parseAsciiString(String str) {
            return str;
        }

        @Override // io.grpc.Metadata.AsciiMarshaller
        public String toAsciiString(String str) {
            return str;
        }
    };
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = BaseEncoding.base64().omitPadding();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface AsciiMarshaller<T> {
        T parseAsciiString(String str);

        String toAsciiString(T t);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface BinaryMarshaller<T> {
        T parseBytes(byte[] bArr);

        byte[] toBytes(T t);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface BinaryStreamMarshaller<T> {
        T parseStream(InputStream inputStream);

        InputStream toStream(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface TrustedAsciiMarshaller<T> {
        T parseAsciiString(byte[] bArr);

        byte[] toAsciiString(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Metadata(byte[]... bArr) {
        this(bArr.length / 2, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Metadata(int i, byte[]... bArr) {
        this(i, (Object[]) bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Metadata(int i, Object[] objArr) {
        this.size = i;
        this.namesAndValues = objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] name(int i) {
        return (byte[]) this.namesAndValues[i * 2];
    }

    private void name(int i, byte[] bArr) {
        this.namesAndValues[i * 2] = bArr;
    }

    private Object value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    private void value(int i, byte[] bArr) {
        this.namesAndValues[(i * 2) + 1] = bArr;
    }

    private void value(int i, Object obj) {
        if (this.namesAndValues instanceof byte[][]) {
            expand(cap());
        }
        this.namesAndValues[(i * 2) + 1] = obj;
    }

    private byte[] valueAsBytes(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        return ((LazyValue) value).toBytes();
    }

    private Object valueAsBytesOrStream(int i) {
        Object value = value(i);
        return value instanceof byte[] ? value : ((LazyValue) value).toStream();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T valueAsT(int i, Key<T> key) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return key.parseBytes((byte[]) value);
        }
        return (T) ((LazyValue) value).toObject(key);
    }

    private int cap() {
        Object[] objArr = this.namesAndValues;
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    private int len() {
        return this.size * 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Metadata() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int headerCount() {
        return this.size;
    }

    public boolean containsKey(Key<?> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return (T) valueAsT(i, key);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class IterableAt<T> implements Iterable<T> {
        private final Key<T> key;
        private int startIdx;

        private IterableAt(Key<T> key, int i) {
            this.key = key;
            this.startIdx = i;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return new Iterator<T>() { // from class: io.grpc.Metadata.IterableAt.1
                private boolean hasNext = true;
                private int idx;

                {
                    this.idx = IterableAt.this.startIdx;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    if (this.hasNext) {
                        return true;
                    }
                    while (this.idx < Metadata.this.size) {
                        if (!Metadata.this.bytesEqual(IterableAt.this.key.asciiName(), Metadata.this.name(this.idx))) {
                            this.idx++;
                        } else {
                            this.hasNext = true;
                            return this.hasNext;
                        }
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public T next() {
                    if (hasNext()) {
                        this.hasNext = false;
                        Metadata metadata = Metadata.this;
                        int i = this.idx;
                        this.idx = i + 1;
                        return (T) metadata.valueAsT(i, IterableAt.this.key);
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    @Nullable
    public <T> Iterable<T> getAll(Key<T> key) {
        int i = 0;
        while (true) {
            if (i >= this.size) {
                return null;
            }
            if (bytesEqual(key.asciiName(), name(i))) {
                return new IterableAt(key, i);
            }
            i++;
        }
    }

    public Set<String> keys() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.size);
        for (int i = 0; i < this.size; i++) {
            hashSet.add(new String(name(i), 0));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public <T> void put(Key<T> key, T t) {
        Preconditions.checkNotNull(key, TransferTable.COLUMN_KEY);
        Preconditions.checkNotNull(t, ES6Iterator.VALUE_PROPERTY);
        maybeExpand();
        name(this.size, key.asciiName());
        if (key.serializesToStreams()) {
            value(this.size, LazyValue.create(key, t));
        } else {
            value(this.size, key.toBytes(t));
        }
        this.size++;
    }

    private void maybeExpand() {
        if (len() == 0 || len() == cap()) {
            expand(Math.max(len() * 2, 8));
        }
    }

    private void expand(int i) {
        Object[] objArr = new Object[i];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, objArr, 0, len());
        }
        this.namesAndValues = objArr;
    }

    public <T> boolean remove(Key<T> key, T t) {
        Preconditions.checkNotNull(key, TransferTable.COLUMN_KEY);
        Preconditions.checkNotNull(t, ES6Iterator.VALUE_PROPERTY);
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i)) && t.equals(valueAsT(i, key))) {
                int i2 = i * 2;
                int i3 = (i + 1) * 2;
                int len = len() - i3;
                Object[] objArr = this.namesAndValues;
                System.arraycopy(objArr, i3, objArr, i2, len);
                this.size--;
                name(this.size, null);
                value(this.size, (byte[]) null);
                return true;
            }
        }
        return false;
    }

    public <T> Iterable<T> removeAll(Key<T> key) {
        if (isEmpty()) {
            return null;
        }
        int i = 0;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < this.size; i2++) {
            if (bytesEqual(key.asciiName(), name(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(valueAsT(i2, key));
            } else {
                name(i, name(i2));
                value(i, value(i2));
                i++;
            }
        }
        Arrays.fill(this.namesAndValues, i * 2, len(), (Object) null);
        this.size = i;
        return arrayList;
    }

    public <T> void discardAll(Key<T> key) {
        if (isEmpty()) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.size; i2++) {
            if (!bytesEqual(key.asciiName(), name(i2))) {
                name(i, name(i2));
                value(i, value(i2));
                i++;
            }
        }
        Arrays.fill(this.namesAndValues, i * 2, len(), (Object) null);
        this.size = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public byte[][] serialize() {
        byte[][] bArr = new byte[len()];
        Object[] objArr = this.namesAndValues;
        if (objArr instanceof byte[][]) {
            System.arraycopy(objArr, 0, bArr, 0, len());
        } else {
            for (int i = 0; i < this.size; i++) {
                int i2 = i * 2;
                bArr[i2] = name(i);
                bArr[i2 + 1] = valueAsBytes(i);
            }
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Object[] serializePartial() {
        Object[] objArr = new Object[len()];
        for (int i = 0; i < this.size; i++) {
            int i2 = i * 2;
            objArr[i2] = name(i);
            objArr[i2 + 1] = valueAsBytesOrStream(i);
        }
        return objArr;
    }

    public void merge(Metadata metadata) {
        if (metadata.isEmpty()) {
            return;
        }
        int cap = cap() - len();
        if (isEmpty() || cap < metadata.len()) {
            expand(len() + metadata.len());
        }
        System.arraycopy(metadata.namesAndValues, 0, this.namesAndValues, len(), metadata.len());
        this.size += metadata.size;
    }

    public void merge(Metadata metadata, Set<Key<?>> set) {
        Preconditions.checkNotNull(metadata, "other");
        HashMap hashMap = new HashMap(set.size());
        for (Key<?> key : set) {
            hashMap.put(ByteBuffer.wrap(key.asciiName()), key);
        }
        for (int i = 0; i < metadata.size; i++) {
            if (hashMap.containsKey(ByteBuffer.wrap(metadata.name(i)))) {
                maybeExpand();
                name(this.size, metadata.name(i));
                value(this.size, metadata.value(i));
                this.size++;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(name(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith(BINARY_HEADER_SUFFIX)) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(valueAsBytes(i)));
            } else {
                sb.append(new String(valueAsBytes(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bytesEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Key<T> {
        private static final BitSet VALID_T_CHARS = generateValidTChars();
        private final Object marshaller;
        private final String name;
        private final byte[] nameBytes;
        private final String originalName;

        abstract T parseBytes(byte[] bArr);

        boolean serializesToStreams() {
            return false;
        }

        abstract byte[] toBytes(T t);

        /* renamed from: of */
        public static <T> Key<T> m3902of(String str, BinaryMarshaller<T> binaryMarshaller) {
            return new BinaryKey(str, binaryMarshaller);
        }

        /* renamed from: of */
        public static <T> Key<T> m3903of(String str, BinaryStreamMarshaller<T> binaryStreamMarshaller) {
            return new LazyStreamBinaryKey(str, binaryStreamMarshaller);
        }

        /* renamed from: of */
        public static <T> Key<T> m3901of(String str, AsciiMarshaller<T> asciiMarshaller) {
            return m3904of(str, false, (AsciiMarshaller) asciiMarshaller);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: of */
        public static <T> Key<T> m3904of(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            return new AsciiKey(str, z, asciiMarshaller);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: of */
        public static <T> Key<T> m3905of(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            return new TrustedAsciiKey(str, z, trustedAsciiMarshaller);
        }

        private static BitSet generateValidTChars() {
            BitSet bitSet = new BitSet(127);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            return bitSet;
        }

        private static String validateName(String str, boolean z) {
            Preconditions.checkNotNull(str, "name");
            Preconditions.checkArgument(!str.isEmpty(), "token must have at least 1 tchar");
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!z || charAt != ':' || i != 0) {
                    Preconditions.checkArgument(VALID_T_CHARS.get(charAt), "Invalid character '%s' in key name '%s'", charAt, (Object) str);
                }
            }
            return str;
        }

        private Key(String str, boolean z, Object obj) {
            this.originalName = (String) Preconditions.checkNotNull(str, "name");
            this.name = validateName(this.originalName.toLowerCase(Locale.ROOT), z);
            this.nameBytes = this.name.getBytes(Charsets.US_ASCII);
            this.marshaller = obj;
        }

        public final String originalName() {
            return this.originalName;
        }

        public final String name() {
            return this.name;
        }

        byte[] asciiName() {
            return this.nameBytes;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.name.equals(((Key) obj).name);
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Key{name='" + this.name + "'}";
        }

        @Nullable
        final <M> M getMarshaller(Class<M> cls) {
            if (cls.isInstance(this.marshaller)) {
                return cls.cast(this.marshaller);
            }
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class BinaryKey<T> extends Key<T> {
        private final BinaryMarshaller<T> marshaller;

        private BinaryKey(String str, BinaryMarshaller<T> binaryMarshaller) {
            super(str, false, binaryMarshaller);
            Preconditions.checkArgument(str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", str, Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(str.length() > 4, "empty key name");
            this.marshaller = (BinaryMarshaller) Preconditions.checkNotNull(binaryMarshaller, "marshaller is null");
        }

        @Override // io.grpc.Metadata.Key
        byte[] toBytes(T t) {
            return this.marshaller.toBytes(t);
        }

        @Override // io.grpc.Metadata.Key
        T parseBytes(byte[] bArr) {
            return this.marshaller.parseBytes(bArr);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class LazyStreamBinaryKey<T> extends Key<T> {
        private final BinaryStreamMarshaller<T> marshaller;

        @Override // io.grpc.Metadata.Key
        boolean serializesToStreams() {
            return true;
        }

        private LazyStreamBinaryKey(String str, BinaryStreamMarshaller<T> binaryStreamMarshaller) {
            super(str, false, binaryStreamMarshaller);
            Preconditions.checkArgument(str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", str, Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(str.length() > 4, "empty key name");
            this.marshaller = (BinaryStreamMarshaller) Preconditions.checkNotNull(binaryStreamMarshaller, "marshaller is null");
        }

        @Override // io.grpc.Metadata.Key
        byte[] toBytes(T t) {
            return Metadata.streamToBytes(this.marshaller.toStream(t));
        }

        @Override // io.grpc.Metadata.Key
        T parseBytes(byte[] bArr) {
            return this.marshaller.parseStream(new ByteArrayInputStream(bArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LazyValue<T> {
        private final BinaryStreamMarshaller<T> marshaller;
        private volatile byte[] serialized;
        private final T value;

        static <T> LazyValue<T> create(Key<T> key, T t) {
            return new LazyValue<>((BinaryStreamMarshaller) Preconditions.checkNotNull(getBinaryStreamMarshaller(key)), t);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LazyValue(BinaryStreamMarshaller<T> binaryStreamMarshaller, T t) {
            this.marshaller = binaryStreamMarshaller;
            this.value = t;
        }

        InputStream toStream() {
            return this.marshaller.toStream(this.value);
        }

        byte[] toBytes() {
            if (this.serialized == null) {
                synchronized (this) {
                    if (this.serialized == null) {
                        this.serialized = Metadata.streamToBytes(toStream());
                    }
                }
            }
            return this.serialized;
        }

        <T2> T2 toObject(Key<T2> key) {
            BinaryStreamMarshaller binaryStreamMarshaller;
            if (key.serializesToStreams() && (binaryStreamMarshaller = getBinaryStreamMarshaller(key)) != null) {
                return (T2) binaryStreamMarshaller.parseStream(toStream());
            }
            return key.parseBytes(toBytes());
        }

        @Nullable
        private static <T> BinaryStreamMarshaller<T> getBinaryStreamMarshaller(Key<T> key) {
            return (BinaryStreamMarshaller) key.getMarshaller(BinaryStreamMarshaller.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class AsciiKey<T> extends Key<T> {
        private final AsciiMarshaller<T> marshaller;

        private AsciiKey(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            super(str, z, asciiMarshaller);
            Preconditions.checkArgument(!str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", str, Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (AsciiMarshaller) Preconditions.checkNotNull(asciiMarshaller, "marshaller");
        }

        @Override // io.grpc.Metadata.Key
        byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t).getBytes(Charsets.US_ASCII);
        }

        @Override // io.grpc.Metadata.Key
        T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(new String(bArr, Charsets.US_ASCII));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class TrustedAsciiKey<T> extends Key<T> {
        private final TrustedAsciiMarshaller<T> marshaller;

        private TrustedAsciiKey(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            super(str, z, trustedAsciiMarshaller);
            Preconditions.checkArgument(!str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", str, Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (TrustedAsciiMarshaller) Preconditions.checkNotNull(trustedAsciiMarshaller, "marshaller");
        }

        @Override // io.grpc.Metadata.Key
        byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t);
        }

        @Override // io.grpc.Metadata.Key
        T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] streamToBytes(InputStream inputStream) {
        try {
            return ByteStreams.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("failure reading serialized stream", e);
        }
    }
}
