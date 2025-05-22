package org.yaml.snakeyaml.reader;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.scanner.Constant;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class StreamReader {
    public static final Pattern NON_PRINTABLE = Pattern.compile("[^\t\n\r -~\u0085 -\ud7ff\ue000-�]");
    private String buffer;
    private int column;
    private char[] data;
    private boolean eof;
    private int index;
    private int line;
    private String name;
    private int pointer;
    private final Reader stream;

    public static boolean isPrintable(char c) {
        return (c >= ' ' && c <= '~') || c == '\n' || c == '\r' || c == '\t' || c == 133 || (c >= 160 && c <= 55295) || (c >= 57344 && c <= 65533);
    }

    public StreamReader(String str) {
        this.pointer = 0;
        this.eof = true;
        this.index = 0;
        this.line = 0;
        this.column = 0;
        this.name = "'string'";
        this.buffer = "";
        checkPrintable(str);
        this.buffer = str + "\u0000";
        this.stream = null;
        this.eof = true;
        this.data = null;
    }

    public StreamReader(Reader reader) {
        this.pointer = 0;
        this.eof = true;
        this.index = 0;
        this.line = 0;
        this.column = 0;
        this.name = "'reader'";
        this.buffer = "";
        this.stream = reader;
        this.eof = false;
        this.data = new char[1024];
        update();
    }

    void checkPrintable(CharSequence charSequence) {
        Matcher matcher = NON_PRINTABLE.matcher(charSequence);
        if (matcher.find()) {
            throw new ReaderException(this.name, ((this.index + this.buffer.length()) - this.pointer) + matcher.start(), matcher.group().charAt(0), "special characters are not allowed");
        }
    }

    void checkPrintable(char[] cArr, int i, int i2) {
        while (i < i2) {
            char c = cArr[i];
            if (!isPrintable(c)) {
                throw new ReaderException(this.name, ((this.index + this.buffer.length()) - this.pointer) + i, c, "special characters are not allowed");
            }
            i++;
        }
    }

    public Mark getMark() {
        return new Mark(this.name, this.index, this.line, this.column, this.buffer, this.pointer);
    }

    public void forward() {
        forward(1);
    }

    public void forward(int i) {
        if (this.pointer + i + 1 >= this.buffer.length()) {
            update();
        }
        for (int i2 = 0; i2 < i; i2++) {
            char charAt = this.buffer.charAt(this.pointer);
            this.pointer++;
            this.index++;
            if (Constant.LINEBR.has(charAt) || (charAt == '\r' && this.buffer.charAt(this.pointer) != '\n')) {
                this.line++;
                this.column = 0;
            } else if (charAt != 65279) {
                this.column++;
            }
        }
    }

    public char peek() {
        return this.buffer.charAt(this.pointer);
    }

    public char peek(int i) {
        if (this.pointer + i + 1 > this.buffer.length()) {
            update();
        }
        return this.buffer.charAt(this.pointer + i);
    }

    public String prefix(int i) {
        if (this.pointer + i >= this.buffer.length()) {
            update();
        }
        if (this.pointer + i > this.buffer.length()) {
            return this.buffer.substring(this.pointer);
        }
        String str = this.buffer;
        int i2 = this.pointer;
        return str.substring(i2, i + i2);
    }

    public String prefixForward(int i) {
        String prefix = prefix(i);
        this.pointer += i;
        this.index += i;
        this.column += i;
        return prefix;
    }

    private void update() {
        if (this.eof) {
            return;
        }
        this.buffer = this.buffer.substring(this.pointer);
        this.pointer = 0;
        try {
            int read = this.stream.read(this.data);
            if (read > 0) {
                checkPrintable(this.data, 0, read);
                StringBuilder sb = new StringBuilder(this.buffer.length() + read);
                sb.append(this.buffer);
                sb.append(this.data, 0, read);
                this.buffer = sb.toString();
            } else {
                this.eof = true;
                this.buffer += "\u0000";
            }
        } catch (IOException e) {
            throw new YAMLException(e);
        }
    }

    public int getColumn() {
        return this.column;
    }

    public Charset getEncoding() {
        return Charset.forName(((UnicodeReader) this.stream).getEncoding());
    }

    public int getIndex() {
        return this.index;
    }

    public int getLine() {
        return this.line;
    }
}
