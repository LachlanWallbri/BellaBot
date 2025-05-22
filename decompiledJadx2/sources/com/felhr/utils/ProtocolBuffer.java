package com.felhr.utils;

import com.annimon.stream.IntStream;
import com.annimon.stream.function.IntPredicate;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class ProtocolBuffer {
    public static final String BINARY = "binary";
    private static final int DEFAULT_BUFFER_SIZE = 16384;
    public static final String TEXT = "text";
    private String delimiter;
    private String mode;
    private byte[] rawBuffer;
    private byte[] separator;
    private StringBuilder stringBuffer;
    private int bufferPointer = 0;
    private List<String> commands = new ArrayList();
    private List<byte[]> rawCommands = new ArrayList();

    public ProtocolBuffer(String str) {
        this.mode = str;
        if (str.equals("binary")) {
            this.rawBuffer = new byte[16384];
        } else {
            this.stringBuffer = new StringBuilder(16384);
        }
    }

    public ProtocolBuffer(String str, int i) {
        this.mode = str;
        if (str.equals("binary")) {
            this.rawBuffer = new byte[i];
        } else {
            this.stringBuffer = new StringBuilder(i);
        }
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public void setDelimiter(byte[] bArr) {
        this.separator = bArr;
    }

    public synchronized void appendData(byte[] bArr) {
        if (bArr.length == 0) {
            return;
        }
        if (this.mode.equals("text")) {
            try {
                this.stringBuffer.append(new String(bArr, "UTF-8"));
                String sb = this.stringBuffer.toString();
                int indexOf = sb.indexOf(this.delimiter);
                int i = 0;
                while (indexOf >= 0) {
                    this.commands.add(sb.substring(i, this.delimiter.length() + indexOf));
                    i = this.delimiter.length() + indexOf;
                    indexOf = this.stringBuffer.toString().indexOf(this.delimiter, i);
                }
                if (i > 0) {
                    String substring = sb.substring(i, sb.length());
                    this.stringBuffer.setLength(0);
                    this.stringBuffer.append(substring);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (this.mode.equals("binary")) {
            appendRawData(bArr);
        }
    }

    public boolean hasMoreCommands() {
        return this.mode.equals("text") ? this.commands.size() > 0 : this.rawCommands.size() > 0;
    }

    public String nextTextCommand() {
        if (this.commands.size() > 0) {
            return this.commands.remove(0);
        }
        return null;
    }

    public byte[] nextBinaryCommand() {
        if (this.rawCommands.size() > 0) {
            return this.rawCommands.remove(0);
        }
        return null;
    }

    private void appendRawData(byte[] bArr) {
        System.arraycopy(bArr, 0, this.rawBuffer, this.bufferPointer, bArr.length);
        this.bufferPointer += bArr.length;
        int i = 0;
        for (int i2 : IntStream.range(0, this.bufferPointer).filter(new SeparatorPredicate()).toArray()) {
            Integer valueOf = Integer.valueOf(i2);
            this.rawCommands.add(Arrays.copyOfRange(this.rawBuffer, i, valueOf.intValue() + this.separator.length));
            i = valueOf.intValue() + this.separator.length;
        }
        byte[] bArr2 = this.rawBuffer;
        if (i >= bArr2.length || i <= 0) {
            return;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, i, bArr2.length);
        this.bufferPointer = 0;
        System.arraycopy(copyOfRange, 0, this.rawBuffer, this.bufferPointer, bArr.length);
        this.bufferPointer += bArr.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public class SeparatorPredicate implements IntPredicate {
        private SeparatorPredicate() {
        }

        @Override // com.annimon.stream.function.IntPredicate
        public boolean test(int i) {
            if (ProtocolBuffer.this.rawBuffer[i] != ProtocolBuffer.this.separator[0]) {
                return false;
            }
            for (int i2 = 1; i2 <= ProtocolBuffer.this.separator.length - 1; i2++) {
                if (ProtocolBuffer.this.rawBuffer[i + i2] != ProtocolBuffer.this.separator[i2]) {
                    return false;
                }
            }
            return true;
        }
    }
}
