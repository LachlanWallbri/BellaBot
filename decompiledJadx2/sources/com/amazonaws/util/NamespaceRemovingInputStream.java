package com.amazonaws.util;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class NamespaceRemovingInputStream extends SdkFilterInputStream {
    private static final int BUFFER_SIZE = 200;
    private boolean hasRemovedNamespace;
    private final byte[] lookAheadData;

    public NamespaceRemovingInputStream(InputStream inputStream) {
        super(new BufferedInputStream(inputStream));
        this.lookAheadData = new byte[200];
        this.hasRemovedNamespace = false;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        abortIfNeeded();
        int read = this.in.read();
        if (read != 120 || this.hasRemovedNamespace) {
            return read;
        }
        this.lookAheadData[0] = (byte) read;
        this.in.mark(this.lookAheadData.length);
        InputStream inputStream = this.in;
        byte[] bArr = this.lookAheadData;
        int read2 = inputStream.read(bArr, 1, bArr.length - 1);
        this.in.reset();
        int matchXmlNamespaceAttribute = matchXmlNamespaceAttribute(new String(this.lookAheadData, 0, read2 + 1, StringUtils.UTF8));
        if (matchXmlNamespaceAttribute <= 0) {
            return read;
        }
        for (int i = 0; i < matchXmlNamespaceAttribute - 1; i++) {
            this.in.read();
        }
        int read3 = this.in.read();
        this.hasRemovedNamespace = true;
        return read3;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            int read = read();
            if (read == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            bArr[i3 + i] = (byte) read;
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    private int matchXmlNamespaceAttribute(String str) {
        StringPrefixSlicer stringPrefixSlicer = new StringPrefixSlicer(str);
        if (!stringPrefixSlicer.removePrefix("xmlns")) {
            return -1;
        }
        stringPrefixSlicer.removeRepeatingPrefix(" ");
        if (!stringPrefixSlicer.removePrefix("=")) {
            return -1;
        }
        stringPrefixSlicer.removeRepeatingPrefix(" ");
        if (stringPrefixSlicer.removePrefix("\"") && stringPrefixSlicer.removePrefixEndingWith("\"")) {
            return str.length() - stringPrefixSlicer.getString().length();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StringPrefixSlicer {

        /* renamed from: s */
        private String f1199s;

        public StringPrefixSlicer(String str) {
            this.f1199s = str;
        }

        public String getString() {
            return this.f1199s;
        }

        public boolean removePrefix(String str) {
            if (!this.f1199s.startsWith(str)) {
                return false;
            }
            this.f1199s = this.f1199s.substring(str.length());
            return true;
        }

        public boolean removeRepeatingPrefix(String str) {
            if (!this.f1199s.startsWith(str)) {
                return false;
            }
            while (this.f1199s.startsWith(str)) {
                this.f1199s = this.f1199s.substring(str.length());
            }
            return true;
        }

        public boolean removePrefixEndingWith(String str) {
            int indexOf = this.f1199s.indexOf(str);
            if (indexOf < 0) {
                return false;
            }
            this.f1199s = this.f1199s.substring(indexOf + str.length());
            return true;
        }
    }
}
