package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 65536;
    static final int MIN_SEGMENT_LEN = 500;
    static final char[] NO_CHARS = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    protected TextBuffer(BufferRecycler bufferRecycler, char[] cArr) {
        this._allocator = bufferRecycler;
        this._currentSegment = cArr;
        this._currentSize = cArr.length;
        this._inputStart = -1;
    }

    public static TextBuffer fromInitial(char[] cArr) {
        return new TextBuffer(null, cArr);
    }

    public void releaseBuffers() {
        char[] cArr;
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        BufferRecycler bufferRecycler = this._allocator;
        if (bufferRecycler == null || (cArr = this._currentSegment) == null) {
            return;
        }
        this._currentSegment = null;
        bufferRecycler.releaseCharBuffer(2, cArr);
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWith(char c) {
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(1);
        }
        this._currentSegment[0] = c;
        this._segmentSize = 1;
        this._currentSize = 1;
    }

    public void resetWithShared(char[] cArr, int i, int i2) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i;
        this._inputLen = i2;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithCopy(char[] cArr, int i, int i2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i2);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, i, i2);
    }

    public void resetWithCopy(String str, int i, int i2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i2);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(str, i, i2);
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    public char[] getBufferWithoutReset() {
        return this._currentSegment;
    }

    private char[] buf(int i) {
        BufferRecycler bufferRecycler = this._allocator;
        if (bufferRecycler != null) {
            return bufferRecycler.allocCharBuffer(2, i);
        }
        return new char[Math.max(i, 500)];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this._resultString;
        if (str != null) {
            return str.length();
        }
        return this._segmentSize + this._currentSize;
    }

    public int getTextOffset() {
        int i = this._inputStart;
        if (i >= 0) {
            return i;
        }
        return 0;
    }

    public boolean hasTextAsCharacters() {
        return this._inputStart >= 0 || this._resultArray != null || this._resultString == null;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        String str = this._resultString;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this._resultArray = charArray;
            return charArray;
        }
        if (!this._hasSegments) {
            char[] cArr2 = this._currentSegment;
            return cArr2 == null ? NO_CHARS : cArr2;
        }
        return contentsAsArray();
    }

    public String contentsAsString() {
        if (this._resultString == null) {
            char[] cArr = this._resultArray;
            if (cArr != null) {
                this._resultString = new String(cArr);
            } else {
                int i = this._inputStart;
                if (i >= 0) {
                    int i2 = this._inputLen;
                    if (i2 < 1) {
                        this._resultString = "";
                        return "";
                    }
                    this._resultString = new String(this._inputBuffer, i, i2);
                } else {
                    int i3 = this._segmentSize;
                    int i4 = this._currentSize;
                    if (i3 == 0) {
                        this._resultString = i4 != 0 ? new String(this._currentSegment, 0, i4) : "";
                    } else {
                        StringBuilder sb = new StringBuilder(i3 + i4);
                        ArrayList<char[]> arrayList = this._segments;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i5 = 0; i5 < size; i5++) {
                                char[] cArr2 = this._segments.get(i5);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this._currentSegment, 0, this._currentSize);
                        this._resultString = sb.toString();
                    }
                }
            }
        }
        return this._resultString;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        char[] resultArray = resultArray();
        this._resultArray = resultArray;
        return resultArray;
    }

    public BigDecimal contentsAsDecimal() throws NumberFormatException {
        char[] cArr;
        char[] cArr2;
        char[] cArr3 = this._resultArray;
        if (cArr3 != null) {
            return NumberInput.parseBigDecimal(cArr3);
        }
        int i = this._inputStart;
        if (i >= 0 && (cArr2 = this._inputBuffer) != null) {
            return NumberInput.parseBigDecimal(cArr2, i, this._inputLen);
        }
        if (this._segmentSize == 0 && (cArr = this._currentSegment) != null) {
            return NumberInput.parseBigDecimal(cArr, 0, this._currentSize);
        }
        return NumberInput.parseBigDecimal(contentsAsArray());
    }

    public double contentsAsDouble() throws NumberFormatException {
        return NumberInput.parseDouble(contentsAsString());
    }

    public int contentsAsInt(boolean z) {
        char[] cArr;
        int i = this._inputStart;
        if (i < 0 || (cArr = this._inputBuffer) == null) {
            if (z) {
                return -NumberInput.parseInt(this._currentSegment, 1, this._currentSize - 1);
            }
            return NumberInput.parseInt(this._currentSegment, 0, this._currentSize);
        }
        if (z) {
            return -NumberInput.parseInt(cArr, i + 1, this._inputLen - 1);
        }
        return NumberInput.parseInt(cArr, i, this._inputLen);
    }

    public long contentsAsLong(boolean z) {
        char[] cArr;
        int i = this._inputStart;
        if (i < 0 || (cArr = this._inputBuffer) == null) {
            if (z) {
                return -NumberInput.parseLong(this._currentSegment, 1, this._currentSize - 1);
            }
            return NumberInput.parseLong(this._currentSegment, 0, this._currentSize);
        }
        if (z) {
            return -NumberInput.parseLong(cArr, i + 1, this._inputLen - 1);
        }
        return NumberInput.parseLong(cArr, i, this._inputLen);
    }

    public int contentsToWriter(Writer writer) throws IOException {
        int i;
        char[] cArr = this._resultArray;
        if (cArr != null) {
            writer.write(cArr);
            return this._resultArray.length;
        }
        String str = this._resultString;
        if (str != null) {
            writer.write(str);
            return this._resultString.length();
        }
        int i2 = this._inputStart;
        if (i2 >= 0) {
            int i3 = this._inputLen;
            if (i3 > 0) {
                writer.write(this._inputBuffer, i2, i3);
            }
            return i3;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size = arrayList.size();
            i = 0;
            for (int i4 = 0; i4 < size; i4++) {
                char[] cArr2 = this._segments.get(i4);
                int length = cArr2.length;
                writer.write(cArr2, 0, length);
                i += length;
            }
        } else {
            i = 0;
        }
        int i5 = this._currentSize;
        if (i5 <= 0) {
            return i;
        }
        writer.write(this._currentSegment, 0, i5);
        return i + i5;
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public void append(char c) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i = this._currentSize;
        this._currentSize = i + 1;
        cArr[i] = c;
    }

    public void append(char[] cArr, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr2 = this._currentSegment;
        int length = cArr2.length;
        int i3 = this._currentSize;
        int i4 = length - i3;
        if (i4 >= i2) {
            System.arraycopy(cArr, i, cArr2, i3, i2);
            this._currentSize += i2;
            return;
        }
        if (i4 > 0) {
            System.arraycopy(cArr, i, cArr2, i3, i4);
            i += i4;
            i2 -= i4;
        }
        do {
            expand(i2);
            int min = Math.min(this._currentSegment.length, i2);
            System.arraycopy(cArr, i, this._currentSegment, 0, min);
            this._currentSize += min;
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    public void append(String str, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i3 = this._currentSize;
        int i4 = length - i3;
        if (i4 >= i2) {
            str.getChars(i, i + i2, cArr, i3);
            this._currentSize += i2;
            return;
        }
        if (i4 > 0) {
            int i5 = i + i4;
            str.getChars(i, i5, cArr, i3);
            i2 -= i4;
            i = i5;
        }
        while (true) {
            expand(i2);
            int min = Math.min(this._currentSegment.length, i2);
            int i6 = i + min;
            str.getChars(i, i6, this._currentSegment, 0);
            this._currentSize += min;
            i2 -= min;
            if (i2 <= 0) {
                return;
            } else {
                i = i6;
            }
        }
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = buf(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr != null) {
            return cArr;
        }
        char[] buf = buf(0);
        this._currentSegment = buf;
        return buf;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public void setCurrentLength(int i) {
        this._currentSize = i;
    }

    public String setCurrentAndReturn(int i) {
        this._currentSize = i;
        if (this._segmentSize > 0) {
            return contentsAsString();
        }
        int i2 = this._currentSize;
        String str = i2 == 0 ? "" : new String(this._currentSegment, 0, i2);
        this._resultString = str;
        return str;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        this._currentSize = 0;
        int i = length + (length >> 1);
        if (i < 500) {
            i = 500;
        } else if (i > 65536) {
            i = 65536;
        }
        char[] carr = carr(i);
        this._currentSegment = carr;
        return carr;
    }

    public char[] expandCurrentSegment() {
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i = (length >> 1) + length;
        if (i > 65536) {
            i = (length >> 2) + length;
        }
        char[] copyOf = Arrays.copyOf(cArr, i);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public char[] expandCurrentSegment(int i) {
        char[] cArr = this._currentSegment;
        if (cArr.length >= i) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, i);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public String toString() {
        return contentsAsString();
    }

    private void unshare(int i) {
        int i2 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int i3 = this._inputStart;
        this._inputStart = -1;
        int i4 = i + i2;
        char[] cArr2 = this._currentSegment;
        if (cArr2 == null || i4 > cArr2.length) {
            this._currentSegment = buf(i4);
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, this._currentSegment, 0, i2);
        }
        this._segmentSize = 0;
        this._currentSize = i2;
    }

    private void expand(int i) {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        char[] cArr = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(cArr);
        this._segmentSize += cArr.length;
        this._currentSize = 0;
        int length = cArr.length;
        int i2 = length + (length >> 1);
        if (i2 < 500) {
            i2 = 500;
        } else if (i2 > 65536) {
            i2 = 65536;
        }
        this._currentSegment = carr(i2);
    }

    private char[] resultArray() {
        int i;
        String str = this._resultString;
        if (str != null) {
            return str.toCharArray();
        }
        int i2 = this._inputStart;
        if (i2 >= 0) {
            int i3 = this._inputLen;
            if (i3 < 1) {
                return NO_CHARS;
            }
            if (i2 == 0) {
                return Arrays.copyOf(this._inputBuffer, i3);
            }
            return Arrays.copyOfRange(this._inputBuffer, i2, i3 + i2);
        }
        int size = size();
        if (size < 1) {
            return NO_CHARS;
        }
        char[] carr = carr(size);
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size2 = arrayList.size();
            i = 0;
            for (int i4 = 0; i4 < size2; i4++) {
                char[] cArr = this._segments.get(i4);
                int length = cArr.length;
                System.arraycopy(cArr, 0, carr, i, length);
                i += length;
            }
        } else {
            i = 0;
        }
        System.arraycopy(this._currentSegment, 0, carr, i, this._currentSize);
        return carr;
    }

    private char[] carr(int i) {
        return new char[i];
    }
}
