package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AsciiString implements CharSequence, Comparable<CharSequence> {
    public static final int INDEX_NOT_FOUND = -1;
    private static final char MAX_CHAR_VALUE = 255;
    private int hash;
    private final int length;
    private final int offset;
    private String string;
    private final byte[] value;
    public static final AsciiString EMPTY_STRING = cached("");
    public static final HashingStrategy<CharSequence> CASE_INSENSITIVE_HASHER = new HashingStrategy<CharSequence>() { // from class: io.grpc.netty.shaded.io.netty.util.AsciiString.1
        @Override // io.grpc.netty.shaded.io.netty.util.HashingStrategy
        public int hashCode(CharSequence charSequence) {
            return AsciiString.hashCode(charSequence);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.HashingStrategy
        public boolean equals(CharSequence charSequence, CharSequence charSequence2) {
            return AsciiString.contentEqualsIgnoreCase(charSequence, charSequence2);
        }
    };
    public static final HashingStrategy<CharSequence> CASE_SENSITIVE_HASHER = new HashingStrategy<CharSequence>() { // from class: io.grpc.netty.shaded.io.netty.util.AsciiString.2
        @Override // io.grpc.netty.shaded.io.netty.util.HashingStrategy
        public int hashCode(CharSequence charSequence) {
            return AsciiString.hashCode(charSequence);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.HashingStrategy
        public boolean equals(CharSequence charSequence, CharSequence charSequence2) {
            return AsciiString.contentEquals(charSequence, charSequence2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface CharEqualityComparator {
        boolean equals(char c, char c2);
    }

    public static char b2c(byte b) {
        return (char) (b & 255);
    }

    public static byte c2b(char c) {
        if (c > 255) {
            c = '?';
        }
        return (byte) c;
    }

    private static byte c2b0(char c) {
        return (byte) c;
    }

    private static boolean isLowerCase(byte b) {
        return b >= 97 && b <= 122;
    }

    public static boolean isUpperCase(byte b) {
        return b >= 65 && b <= 90;
    }

    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public AsciiString(byte[] bArr) {
        this(bArr, true);
    }

    public AsciiString(byte[] bArr, boolean z) {
        this(bArr, 0, bArr.length, z);
    }

    public AsciiString(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            this.value = Arrays.copyOfRange(bArr, i, i + i2);
            this.offset = 0;
        } else {
            if (MathUtil.isOutOfBounds(i, i2, bArr.length)) {
                throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= start + length(" + i2 + ") <= value.length(" + bArr.length + ')');
            }
            this.value = bArr;
            this.offset = i;
        }
        this.length = i2;
    }

    public AsciiString(ByteBuffer byteBuffer) {
        this(byteBuffer, true);
    }

    public AsciiString(ByteBuffer byteBuffer, boolean z) {
        this(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), z);
    }

    public AsciiString(ByteBuffer byteBuffer, int i, int i2, boolean z) {
        if (MathUtil.isOutOfBounds(i, i2, byteBuffer.capacity())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= start + length(" + i2 + ") <= value.capacity(" + byteBuffer.capacity() + ')');
        }
        if (!byteBuffer.hasArray()) {
            this.value = PlatformDependent.allocateUninitializedArray(i2);
            int position = byteBuffer.position();
            byteBuffer.get(this.value, 0, i2);
            byteBuffer.position(position);
            this.offset = 0;
        } else if (z) {
            int arrayOffset = byteBuffer.arrayOffset() + i;
            this.value = Arrays.copyOfRange(byteBuffer.array(), arrayOffset, arrayOffset + i2);
            this.offset = 0;
        } else {
            this.value = byteBuffer.array();
            this.offset = i;
        }
        this.length = i2;
    }

    public AsciiString(char[] cArr) {
        this(cArr, 0, cArr.length);
    }

    public AsciiString(char[] cArr, int i, int i2) {
        if (MathUtil.isOutOfBounds(i, i2, cArr.length)) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= start + length(" + i2 + ") <= value.length(" + cArr.length + ')');
        }
        this.value = PlatformDependent.allocateUninitializedArray(i2);
        int i3 = i;
        int i4 = 0;
        while (i4 < i2) {
            this.value[i4] = c2b(cArr[i3]);
            i4++;
            i3++;
        }
        this.offset = 0;
        this.length = i2;
    }

    public AsciiString(char[] cArr, Charset charset) {
        this(cArr, charset, 0, cArr.length);
    }

    public AsciiString(char[] cArr, Charset charset, int i, int i2) {
        CharBuffer wrap = CharBuffer.wrap(cArr, i, i2);
        CharsetEncoder encoder = CharsetUtil.encoder(charset);
        ByteBuffer allocate = ByteBuffer.allocate((int) (encoder.maxBytesPerChar() * i2));
        encoder.encode(wrap, allocate, true);
        int arrayOffset = allocate.arrayOffset();
        this.value = Arrays.copyOfRange(allocate.array(), arrayOffset, allocate.position() + arrayOffset);
        this.offset = 0;
        this.length = this.value.length;
    }

    public AsciiString(CharSequence charSequence) {
        this(charSequence, 0, charSequence.length());
    }

    public AsciiString(CharSequence charSequence, int i, int i2) {
        if (MathUtil.isOutOfBounds(i, i2, charSequence.length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= start + length(" + i2 + ") <= value.length(" + charSequence.length() + ')');
        }
        this.value = PlatformDependent.allocateUninitializedArray(i2);
        int i3 = i;
        int i4 = 0;
        while (i4 < i2) {
            this.value[i4] = c2b(charSequence.charAt(i3));
            i4++;
            i3++;
        }
        this.offset = 0;
        this.length = i2;
    }

    public AsciiString(CharSequence charSequence, Charset charset) {
        this(charSequence, charset, 0, charSequence.length());
    }

    public AsciiString(CharSequence charSequence, Charset charset, int i, int i2) {
        CharBuffer wrap = CharBuffer.wrap(charSequence, i, i + i2);
        CharsetEncoder encoder = CharsetUtil.encoder(charset);
        ByteBuffer allocate = ByteBuffer.allocate((int) (encoder.maxBytesPerChar() * i2));
        encoder.encode(wrap, allocate, true);
        int arrayOffset = allocate.arrayOffset();
        this.value = Arrays.copyOfRange(allocate.array(), arrayOffset, allocate.position() + arrayOffset);
        this.offset = 0;
        this.length = this.value.length;
    }

    public int forEachByte(ByteProcessor byteProcessor) throws Exception {
        return forEachByte0(0, length(), byteProcessor);
    }

    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        if (MathUtil.isOutOfBounds(i, i2, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= index(" + i + ") <= start + length(" + i2 + ") <= length(" + length() + ')');
        }
        return forEachByte0(i, i2, byteProcessor);
    }

    private int forEachByte0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int i3 = this.offset;
        int i4 = i3 + i + i2;
        for (int i5 = i3 + i; i5 < i4; i5++) {
            if (!byteProcessor.process(this.value[i5])) {
                return i5 - this.offset;
            }
        }
        return -1;
    }

    public int forEachByteDesc(ByteProcessor byteProcessor) throws Exception {
        return forEachByteDesc0(0, length(), byteProcessor);
    }

    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        if (MathUtil.isOutOfBounds(i, i2, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= index(" + i + ") <= start + length(" + i2 + ") <= length(" + length() + ')');
        }
        return forEachByteDesc0(i, i2, byteProcessor);
    }

    private int forEachByteDesc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int i3 = this.offset;
        int i4 = i3 + i;
        for (int i5 = ((i3 + i) + i2) - 1; i5 >= i4; i5--) {
            if (!byteProcessor.process(this.value[i5])) {
                return i5 - this.offset;
            }
        }
        return -1;
    }

    public byte byteAt(int i) {
        if (i < 0 || i >= this.length) {
            throw new IndexOutOfBoundsException("index: " + i + " must be in the range [0," + this.length + ")");
        }
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getByte(this.value, i + this.offset);
        }
        return this.value[i + this.offset];
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    public void arrayChanged() {
        this.string = null;
        this.hash = 0;
    }

    public byte[] array() {
        return this.value;
    }

    public int arrayOffset() {
        return this.offset;
    }

    public boolean isEntireArrayUsed() {
        return this.offset == 0 && this.length == this.value.length;
    }

    public byte[] toByteArray() {
        return toByteArray(0, length());
    }

    public byte[] toByteArray(int i, int i2) {
        byte[] bArr = this.value;
        int i3 = this.offset;
        return Arrays.copyOfRange(bArr, i + i3, i2 + i3);
    }

    public void copy(int i, byte[] bArr, int i2, int i3) {
        if (MathUtil.isOutOfBounds(i, i3, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= srcIdx(" + i + ") <= srcIdx + length(" + i3 + ") <= srcLen(" + length() + ')');
        }
        System.arraycopy(this.value, i + this.offset, ObjectUtil.checkNotNull(bArr, "dst"), i2, i3);
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return b2c(byteAt(i));
    }

    public boolean contains(CharSequence charSequence) {
        return indexOf(charSequence) >= 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(CharSequence charSequence) {
        int i = 0;
        if (this == charSequence) {
            return 0;
        }
        int length = length();
        int length2 = charSequence.length();
        int min = Math.min(length, length2);
        int arrayOffset = arrayOffset();
        while (i < min) {
            int b2c = b2c(this.value[arrayOffset]) - charSequence.charAt(i);
            if (b2c != 0) {
                return b2c;
            }
            i++;
            arrayOffset++;
        }
        return length - length2;
    }

    public AsciiString concat(CharSequence charSequence) {
        int length = length();
        int length2 = charSequence.length();
        if (length2 == 0) {
            return this;
        }
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            if (isEmpty()) {
                return asciiString;
            }
            byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(length + length2);
            System.arraycopy(this.value, arrayOffset(), allocateUninitializedArray, 0, length);
            System.arraycopy(asciiString.value, asciiString.arrayOffset(), allocateUninitializedArray, length, length2);
            return new AsciiString(allocateUninitializedArray, false);
        }
        if (isEmpty()) {
            return new AsciiString(charSequence);
        }
        byte[] allocateUninitializedArray2 = PlatformDependent.allocateUninitializedArray(length2 + length);
        System.arraycopy(this.value, arrayOffset(), allocateUninitializedArray2, 0, length);
        int i = 0;
        while (length < allocateUninitializedArray2.length) {
            allocateUninitializedArray2[length] = c2b(charSequence.charAt(i));
            length++;
            i++;
        }
        return new AsciiString(allocateUninitializedArray2, false);
    }

    public boolean endsWith(CharSequence charSequence) {
        int length = charSequence.length();
        return regionMatches(length() - length, charSequence, 0, length);
    }

    public boolean contentEqualsIgnoreCase(CharSequence charSequence) {
        if (this == charSequence) {
            return true;
        }
        if (charSequence == null || charSequence.length() != length()) {
            return false;
        }
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            int arrayOffset = arrayOffset();
            int arrayOffset2 = asciiString.arrayOffset();
            int length = length() + arrayOffset;
            while (arrayOffset < length) {
                if (!equalsIgnoreCase(this.value[arrayOffset], asciiString.value[arrayOffset2])) {
                    return false;
                }
                arrayOffset++;
                arrayOffset2++;
            }
            return true;
        }
        int arrayOffset3 = arrayOffset();
        int length2 = length();
        int i = arrayOffset3;
        for (int i2 = 0; i2 < length2; i2++) {
            if (!equalsIgnoreCase(b2c(this.value[i]), charSequence.charAt(i2))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public char[] toCharArray() {
        return toCharArray(0, length());
    }

    public char[] toCharArray(int i, int i2) {
        int i3 = i2 - i;
        if (i3 == 0) {
            return EmptyArrays.EMPTY_CHARS;
        }
        if (MathUtil.isOutOfBounds(i, i3, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= srcIdx + length(" + i3 + ") <= srcLen(" + length() + ')');
        }
        char[] cArr = new char[i3];
        int i4 = 0;
        int arrayOffset = i + arrayOffset();
        while (i4 < i3) {
            cArr[i4] = b2c(this.value[arrayOffset]);
            i4++;
            arrayOffset++;
        }
        return cArr;
    }

    public void copy(int i, char[] cArr, int i2, int i3) {
        ObjectUtil.checkNotNull(cArr, "dst");
        if (MathUtil.isOutOfBounds(i, i3, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= srcIdx(" + i + ") <= srcIdx + length(" + i3 + ") <= srcLen(" + length() + ')');
        }
        int i4 = i3 + i2;
        int arrayOffset = i + arrayOffset();
        while (i2 < i4) {
            cArr[i2] = b2c(this.value[arrayOffset]);
            i2++;
            arrayOffset++;
        }
    }

    public AsciiString subSequence(int i) {
        return subSequence(i, length());
    }

    @Override // java.lang.CharSequence
    public AsciiString subSequence(int i, int i2) {
        return subSequence(i, i2, true);
    }

    public AsciiString subSequence(int i, int i2, boolean z) {
        int i3 = i2 - i;
        if (MathUtil.isOutOfBounds(i, i3, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= end (" + i2 + ") <= length(" + length() + ')');
        }
        if (i == 0 && i2 == length()) {
            return this;
        }
        if (i2 == i) {
            return EMPTY_STRING;
        }
        return new AsciiString(this.value, i + this.offset, i3, z);
    }

    public int indexOf(CharSequence charSequence) {
        return indexOf(charSequence, 0);
    }

    public int indexOf(CharSequence charSequence, int i) {
        char charAt;
        int length = charSequence.length();
        if (i < 0) {
            i = 0;
        }
        if (length <= 0) {
            int i2 = this.length;
            return i < i2 ? i : i2;
        }
        if (length > this.length - i || (charAt = charSequence.charAt(0)) > 255) {
            return -1;
        }
        byte c2b0 = c2b0(charAt);
        int i3 = this.offset;
        int i4 = (this.length + i3) - length;
        for (int i5 = i + i3; i5 <= i4; i5++) {
            if (this.value[i5] == c2b0) {
                int i6 = i5;
                int i7 = 0;
                do {
                    i7++;
                    if (i7 >= length) {
                        break;
                    }
                    i6++;
                } while (b2c(this.value[i6]) == charSequence.charAt(i7));
                if (i7 == length) {
                    return i5 - this.offset;
                }
            }
        }
        return -1;
    }

    public int indexOf(char c, int i) {
        if (c > 255) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        byte c2b0 = c2b0(c);
        int i2 = this.offset;
        int i3 = this.length + i2;
        for (int i4 = i + i2; i4 < i3; i4++) {
            if (this.value[i4] == c2b0) {
                return i4 - this.offset;
            }
        }
        return -1;
    }

    public int lastIndexOf(CharSequence charSequence) {
        return lastIndexOf(charSequence, this.length);
    }

    public int lastIndexOf(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int min = Math.min(i, this.length - length);
        if (min < 0) {
            return -1;
        }
        if (length == 0) {
            return min;
        }
        char charAt = charSequence.charAt(0);
        if (charAt > 255) {
            return -1;
        }
        byte c2b0 = c2b0(charAt);
        for (int i2 = this.offset + min; i2 >= 0; i2--) {
            if (this.value[i2] == c2b0) {
                int i3 = 0;
                int i4 = i2;
                do {
                    i3++;
                    if (i3 >= length) {
                        break;
                    }
                    i4++;
                } while (b2c(this.value[i4]) == charSequence.charAt(i3));
                if (i3 == length) {
                    return i2 - this.offset;
                }
            }
        }
        return -1;
    }

    public boolean regionMatches(int i, CharSequence charSequence, int i2, int i3) {
        ObjectUtil.checkNotNull(charSequence, "string");
        if (i2 >= 0 && charSequence.length() - i2 >= i3) {
            int length = length();
            if (i >= 0 && length - i >= i3) {
                if (i3 <= 0) {
                    return true;
                }
                int i4 = i3 + i2;
                int arrayOffset = i + arrayOffset();
                while (i2 < i4) {
                    if (b2c(this.value[arrayOffset]) != charSequence.charAt(i2)) {
                        return false;
                    }
                    i2++;
                    arrayOffset++;
                }
                return true;
            }
        }
        return false;
    }

    public boolean regionMatches(boolean z, int i, CharSequence charSequence, int i2, int i3) {
        if (!z) {
            return regionMatches(i, charSequence, i2, i3);
        }
        ObjectUtil.checkNotNull(charSequence, "string");
        int length = length();
        if (i < 0 || i3 > length - i || i2 < 0 || i3 > charSequence.length() - i2) {
            return false;
        }
        int arrayOffset = i + arrayOffset();
        int i4 = i3 + arrayOffset;
        while (arrayOffset < i4) {
            int i5 = arrayOffset + 1;
            char b2c = b2c(this.value[arrayOffset]);
            int i6 = i2 + 1;
            if (!equalsIgnoreCase(b2c, charSequence.charAt(i2))) {
                return false;
            }
            i2 = i6;
            arrayOffset = i5;
        }
        return true;
    }

    public AsciiString replace(char c, char c2) {
        if (c > 255) {
            return this;
        }
        byte c2b0 = c2b0(c);
        byte c2b = c2b(c2);
        int i = this.offset;
        int i2 = this.length + i;
        while (i < i2) {
            if (this.value[i] == c2b0) {
                byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(length());
                byte[] bArr = this.value;
                int i3 = this.offset;
                System.arraycopy(bArr, i3, allocateUninitializedArray, 0, i - i3);
                allocateUninitializedArray[i - this.offset] = c2b;
                while (true) {
                    i++;
                    if (i < i2) {
                        byte b = this.value[i];
                        int i4 = i - this.offset;
                        if (b == c2b0) {
                            b = c2b;
                        }
                        allocateUninitializedArray[i4] = b;
                    } else {
                        return new AsciiString(allocateUninitializedArray, false);
                    }
                }
            } else {
                i++;
            }
        }
        return this;
    }

    public boolean startsWith(CharSequence charSequence) {
        return startsWith(charSequence, 0);
    }

    public boolean startsWith(CharSequence charSequence, int i) {
        return regionMatches(i, charSequence, 0, charSequence.length());
    }

    public AsciiString toLowerCase() {
        boolean z;
        int length = length() + arrayOffset();
        int arrayOffset = arrayOffset();
        while (true) {
            if (arrayOffset >= length) {
                z = true;
                break;
            }
            byte b = this.value[arrayOffset];
            if (b >= 65 && b <= 90) {
                z = false;
                break;
            }
            arrayOffset++;
        }
        if (z) {
            return this;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(length());
        int arrayOffset2 = arrayOffset();
        int i = 0;
        while (i < allocateUninitializedArray.length) {
            allocateUninitializedArray[i] = toLowerCase(this.value[arrayOffset2]);
            i++;
            arrayOffset2++;
        }
        return new AsciiString(allocateUninitializedArray, false);
    }

    public AsciiString toUpperCase() {
        boolean z;
        int length = length() + arrayOffset();
        int arrayOffset = arrayOffset();
        while (true) {
            if (arrayOffset >= length) {
                z = true;
                break;
            }
            byte b = this.value[arrayOffset];
            if (b >= 97 && b <= 122) {
                z = false;
                break;
            }
            arrayOffset++;
        }
        if (z) {
            return this;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(length());
        int arrayOffset2 = arrayOffset();
        int i = 0;
        while (i < allocateUninitializedArray.length) {
            allocateUninitializedArray[i] = toUpperCase(this.value[arrayOffset2]);
            i++;
            arrayOffset2++;
        }
        return new AsciiString(allocateUninitializedArray, false);
    }

    public static CharSequence trim(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).trim();
        }
        if (charSequence instanceof String) {
            return ((String) charSequence).trim();
        }
        int i = 0;
        int length = charSequence.length() - 1;
        while (i <= length && charSequence.charAt(i) <= ' ') {
            i++;
        }
        int i2 = length;
        while (i2 >= i && charSequence.charAt(i2) <= ' ') {
            i2--;
        }
        return (i == 0 && i2 == length) ? charSequence : charSequence.subSequence(i, i2);
    }

    public AsciiString trim() {
        int arrayOffset = arrayOffset();
        int arrayOffset2 = (arrayOffset() + length()) - 1;
        while (arrayOffset <= arrayOffset2 && this.value[arrayOffset] <= 32) {
            arrayOffset++;
        }
        int i = arrayOffset2;
        while (i >= arrayOffset && this.value[i] <= 32) {
            i--;
        }
        return (arrayOffset == 0 && i == arrayOffset2) ? this : new AsciiString(this.value, arrayOffset, (i - arrayOffset) + 1, false);
    }

    public boolean contentEquals(CharSequence charSequence) {
        if (this == charSequence) {
            return true;
        }
        if (charSequence == null || charSequence.length() != length()) {
            return false;
        }
        if (charSequence instanceof AsciiString) {
            return equals(charSequence);
        }
        int arrayOffset = arrayOffset();
        for (int i = 0; i < charSequence.length(); i++) {
            if (b2c(this.value[arrayOffset]) != charSequence.charAt(i)) {
                return false;
            }
            arrayOffset++;
        }
        return true;
    }

    public boolean matches(String str) {
        return Pattern.matches(str, this);
    }

    public AsciiString[] split(String str, int i) {
        return toAsciiStringArray(Pattern.compile(str).split(this, i));
    }

    public AsciiString[] split(char c) {
        ArrayList arrayList = InternalThreadLocalMap.get().arrayList();
        int length = length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (charAt(i2) == c) {
                if (i == i2) {
                    arrayList.add(EMPTY_STRING);
                } else {
                    arrayList.add(new AsciiString(this.value, arrayOffset() + i, i2 - i, false));
                }
                i = i2 + 1;
            }
        }
        if (i == 0) {
            arrayList.add(this);
        } else if (i != length) {
            arrayList.add(new AsciiString(this.value, arrayOffset() + i, length - i, false));
        } else {
            for (int size = arrayList.size() - 1; size >= 0 && ((AsciiString) arrayList.get(size)).isEmpty(); size--) {
                arrayList.remove(size);
            }
        }
        return (AsciiString[]) arrayList.toArray(new AsciiString[0]);
    }

    public int hashCode() {
        int i = this.hash;
        if (i != 0) {
            return i;
        }
        int hashCodeAscii = PlatformDependent.hashCodeAscii(this.value, this.offset, this.length);
        this.hash = hashCodeAscii;
        return hashCodeAscii;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != AsciiString.class) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AsciiString asciiString = (AsciiString) obj;
        return length() == asciiString.length() && hashCode() == asciiString.hashCode() && PlatformDependent.equals(array(), arrayOffset(), asciiString.array(), asciiString.arrayOffset(), length());
    }

    @Override // java.lang.CharSequence
    public String toString() {
        String str = this.string;
        if (str != null) {
            return str;
        }
        String asciiString = toString(0);
        this.string = asciiString;
        return asciiString;
    }

    public String toString(int i) {
        return toString(i, length());
    }

    public String toString(int i, int i2) {
        int i3 = i2 - i;
        if (i3 == 0) {
            return "";
        }
        if (MathUtil.isOutOfBounds(i, i3, length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= srcIdx + length(" + i3 + ") <= srcLen(" + length() + ')');
        }
        return new String(this.value, 0, i + this.offset, i3);
    }

    public boolean parseBoolean() {
        return this.length >= 1 && this.value[this.offset] != 0;
    }

    public char parseChar() {
        return parseChar(0);
    }

    public char parseChar(int i) {
        if (i + 1 >= length()) {
            throw new IndexOutOfBoundsException("2 bytes required to convert to character. index " + i + " would go out of bounds.");
        }
        int i2 = i + this.offset;
        return (char) (b2c(this.value[i2 + 1]) | (b2c(this.value[i2]) << '\b'));
    }

    public short parseShort() {
        return parseShort(0, length(), 10);
    }

    public short parseShort(int i) {
        return parseShort(0, length(), i);
    }

    public short parseShort(int i, int i2) {
        return parseShort(i, i2, 10);
    }

    public short parseShort(int i, int i2, int i3) {
        int parseInt = parseInt(i, i2, i3);
        short s = (short) parseInt;
        if (s == parseInt) {
            return s;
        }
        throw new NumberFormatException(subSequence(i, i2, false).toString());
    }

    public int parseInt() {
        return parseInt(0, length(), 10);
    }

    public int parseInt(int i) {
        return parseInt(0, length(), i);
    }

    public int parseInt(int i, int i2) {
        return parseInt(i, i2, 10);
    }

    public int parseInt(int i, int i2, int i3) {
        if (i3 < 2 || i3 > 36) {
            throw new NumberFormatException();
        }
        if (i == i2) {
            throw new NumberFormatException();
        }
        boolean z = byteAt(i) == 45;
        if (z) {
            int i4 = i + 1;
            if (i4 == i2) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            i = i4;
        }
        return parseInt(i, i2, i3, z);
    }

    private int parseInt(int i, int i2, int i3, boolean z) {
        int i4 = Integer.MIN_VALUE / i3;
        int i5 = i;
        int i6 = 0;
        while (i5 < i2) {
            int i7 = i5 + 1;
            int digit = Character.digit((char) (this.value[i5 + this.offset] & 255), i3);
            if (digit == -1) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            if (i4 > i6) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            int i8 = (i6 * i3) - digit;
            if (i8 > i6) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            i6 = i8;
            i5 = i7;
        }
        if (z || (i6 = -i6) >= 0) {
            return i6;
        }
        throw new NumberFormatException(subSequence(i, i2, false).toString());
    }

    public long parseLong() {
        return parseLong(0, length(), 10);
    }

    public long parseLong(int i) {
        return parseLong(0, length(), i);
    }

    public long parseLong(int i, int i2) {
        return parseLong(i, i2, 10);
    }

    public long parseLong(int i, int i2, int i3) {
        if (i3 < 2 || i3 > 36) {
            throw new NumberFormatException();
        }
        if (i == i2) {
            throw new NumberFormatException();
        }
        boolean z = byteAt(i) == 45;
        if (z) {
            int i4 = i + 1;
            if (i4 == i2) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            i = i4;
        }
        return parseLong(i, i2, i3, z);
    }

    private long parseLong(int i, int i2, int i3, boolean z) {
        long j = i3;
        long j2 = Long.MIN_VALUE / j;
        int i4 = i;
        long j3 = 0;
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit = Character.digit((char) (this.value[i4 + this.offset] & 255), i3);
            if (digit == -1) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            if (j2 > j3) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            long j4 = (j3 * j) - digit;
            if (j4 > j3) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
            j3 = j4;
            i4 = i5;
        }
        if (!z) {
            j3 = -j3;
            if (j3 < 0) {
                throw new NumberFormatException(subSequence(i, i2, false).toString());
            }
        }
        return j3;
    }

    public float parseFloat() {
        return parseFloat(0, length());
    }

    public float parseFloat(int i, int i2) {
        return Float.parseFloat(toString(i, i2));
    }

    public double parseDouble() {
        return parseDouble(0, length());
    }

    public double parseDouble(int i, int i2) {
        return Double.parseDouble(toString(i, i2));
    }

    /* renamed from: of */
    public static AsciiString m3920of(CharSequence charSequence) {
        return charSequence instanceof AsciiString ? (AsciiString) charSequence : new AsciiString(charSequence);
    }

    public static AsciiString cached(String str) {
        AsciiString asciiString = new AsciiString(str);
        asciiString.string = str;
        return asciiString;
    }

    public static int hashCode(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        if (charSequence instanceof AsciiString) {
            return charSequence.hashCode();
        }
        return PlatformDependent.hashCodeAscii(charSequence);
    }

    public static boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        return contains(charSequence, charSequence2, DefaultCharEqualityComparator.INSTANCE);
    }

    public static boolean containsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return contains(charSequence, charSequence2, AsciiCaseInsensitiveCharEqualityComparator.INSTANCE);
    }

    public static boolean contentEqualsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return charSequence == charSequence2;
        }
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).contentEqualsIgnoreCase(charSequence2);
        }
        if (charSequence2 instanceof AsciiString) {
            return ((AsciiString) charSequence2).contentEqualsIgnoreCase(charSequence);
        }
        if (charSequence.length() != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (!equalsIgnoreCase(charSequence.charAt(i), charSequence2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsContentEqualsIgnoreCase(Collection<CharSequence> collection, CharSequence charSequence) {
        Iterator<CharSequence> it = collection.iterator();
        while (it.hasNext()) {
            if (contentEqualsIgnoreCase(charSequence, it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAllContentEqualsIgnoreCase(Collection<CharSequence> collection, Collection<CharSequence> collection2) {
        Iterator<CharSequence> it = collection2.iterator();
        while (it.hasNext()) {
            if (!containsContentEqualsIgnoreCase(collection, it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean contentEquals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return charSequence == charSequence2;
        }
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).contentEquals(charSequence2);
        }
        if (charSequence2 instanceof AsciiString) {
            return ((AsciiString) charSequence2).contentEquals(charSequence);
        }
        if (charSequence.length() != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static AsciiString[] toAsciiStringArray(String[] strArr) {
        AsciiString[] asciiStringArr = new AsciiString[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            asciiStringArr[i] = new AsciiString(strArr[i]);
        }
        return asciiStringArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DefaultCharEqualityComparator implements CharEqualityComparator {
        static final DefaultCharEqualityComparator INSTANCE = new DefaultCharEqualityComparator();

        @Override // io.grpc.netty.shaded.io.netty.util.AsciiString.CharEqualityComparator
        public boolean equals(char c, char c2) {
            return c == c2;
        }

        private DefaultCharEqualityComparator() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class AsciiCaseInsensitiveCharEqualityComparator implements CharEqualityComparator {
        static final AsciiCaseInsensitiveCharEqualityComparator INSTANCE = new AsciiCaseInsensitiveCharEqualityComparator();

        private AsciiCaseInsensitiveCharEqualityComparator() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.AsciiString.CharEqualityComparator
        public boolean equals(char c, char c2) {
            return AsciiString.equalsIgnoreCase(c, c2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class GeneralCaseInsensitiveCharEqualityComparator implements CharEqualityComparator {
        static final GeneralCaseInsensitiveCharEqualityComparator INSTANCE = new GeneralCaseInsensitiveCharEqualityComparator();

        private GeneralCaseInsensitiveCharEqualityComparator() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.AsciiString.CharEqualityComparator
        public boolean equals(char c, char c2) {
            return Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
        }
    }

    private static boolean contains(CharSequence charSequence, CharSequence charSequence2, CharEqualityComparator charEqualityComparator) {
        if (charSequence != null && charSequence2 != null && charSequence.length() >= charSequence2.length()) {
            if (charSequence2.length() == 0) {
                return true;
            }
            int i = 0;
            for (int i2 = 0; i2 < charSequence.length(); i2++) {
                if (charEqualityComparator.equals(charSequence2.charAt(i), charSequence.charAt(i2))) {
                    i++;
                    if (i == charSequence2.length()) {
                        return true;
                    }
                } else {
                    if (charSequence.length() - i2 < charSequence2.length()) {
                        return false;
                    }
                    i = 0;
                }
            }
        }
        return false;
    }

    private static boolean regionMatchesCharSequences(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, CharEqualityComparator charEqualityComparator) {
        if (i < 0 || i3 > charSequence.length() - i || i2 < 0 || i3 > charSequence2.length() - i2) {
            return false;
        }
        int i4 = i3 + i;
        while (i < i4) {
            int i5 = i + 1;
            int i6 = i2 + 1;
            if (!charEqualityComparator.equals(charSequence.charAt(i), charSequence2.charAt(i2))) {
                return false;
            }
            i = i5;
            i2 = i6;
        }
        return true;
    }

    public static boolean regionMatches(CharSequence charSequence, boolean z, int i, CharSequence charSequence2, int i2, int i3) {
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(z, i, (String) charSequence2, i2, i3);
        }
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).regionMatches(z, i, charSequence2, i2, i3);
        }
        return regionMatchesCharSequences(charSequence, i, charSequence2, i2, i3, z ? GeneralCaseInsensitiveCharEqualityComparator.INSTANCE : DefaultCharEqualityComparator.INSTANCE);
    }

    public static boolean regionMatchesAscii(CharSequence charSequence, boolean z, int i, CharSequence charSequence2, int i2, int i3) {
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(false, i, (String) charSequence2, i2, i3);
        }
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).regionMatches(z, i, charSequence2, i2, i3);
        }
        return regionMatchesCharSequences(charSequence, i, charSequence2, i2, i3, z ? AsciiCaseInsensitiveCharEqualityComparator.INSTANCE : DefaultCharEqualityComparator.INSTANCE);
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence != null && charSequence2 != null) {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence2.length();
            int length2 = (charSequence.length() - length) + 1;
            if (i > length2) {
                return -1;
            }
            if (length == 0) {
                return i;
            }
            while (i < length2) {
                if (regionMatches(charSequence, true, i, charSequence2, 0, length)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCaseAscii(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence != null && charSequence2 != null) {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence2.length();
            int length2 = (charSequence.length() - length) + 1;
            if (i > length2) {
                return -1;
            }
            if (length == 0) {
                return i;
            }
            while (i < length2) {
                if (regionMatchesAscii(charSequence, true, i, charSequence2, 0, length)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static int indexOf(CharSequence charSequence, char c, int i) {
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(c, i);
        }
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).indexOf(c, i);
        }
        if (charSequence == null) {
            return -1;
        }
        int length = charSequence.length();
        if (i < 0) {
            i = 0;
        }
        while (i < length) {
            if (charSequence.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean equalsIgnoreCase(byte b, byte b2) {
        return b == b2 || toLowerCase(b) == toLowerCase(b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equalsIgnoreCase(char c, char c2) {
        return c == c2 || toLowerCase(c) == toLowerCase(c2);
    }

    private static byte toLowerCase(byte b) {
        return isUpperCase(b) ? (byte) (b + 32) : b;
    }

    public static char toLowerCase(char c) {
        return isUpperCase(c) ? (char) (c + ' ') : c;
    }

    private static byte toUpperCase(byte b) {
        return isLowerCase(b) ? (byte) (b - 32) : b;
    }
}
