package com.fasterxml.jackson.core;

import java.io.Serializable;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class JsonLocation implements Serializable {
    public static final int MAX_CONTENT_SNIPPET = 500;

    /* renamed from: NA */
    public static final JsonLocation f1251NA = new JsonLocation(null, -1, -1, -1, -1);
    private static final long serialVersionUID = 1;
    protected final int _columnNr;
    protected final int _lineNr;
    final transient Object _sourceRef;
    protected final long _totalBytes;
    protected final long _totalChars;

    public JsonLocation(Object obj, long j, int i, int i2) {
        this(obj, -1L, j, i, i2);
    }

    public JsonLocation(Object obj, long j, long j2, int i, int i2) {
        this._sourceRef = obj;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }

    public Object getSourceRef() {
        return this._sourceRef;
    }

    public int getLineNr() {
        return this._lineNr;
    }

    public int getColumnNr() {
        return this._columnNr;
    }

    public long getCharOffset() {
        return this._totalChars;
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public String sourceDescription() {
        return _appendSourceDesc(new StringBuilder(100)).toString();
    }

    public int hashCode() {
        Object obj = this._sourceRef;
        return ((((obj == null ? 1 : obj.hashCode()) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        Object obj2 = this._sourceRef;
        if (obj2 == null) {
            if (jsonLocation._sourceRef != null) {
                return false;
            }
        } else if (!obj2.equals(jsonLocation._sourceRef)) {
            return false;
        }
        return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && getByteOffset() == jsonLocation.getByteOffset();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        _appendSourceDesc(sb);
        sb.append("; line: ");
        sb.append(this._lineNr);
        sb.append(", column: ");
        sb.append(this._columnNr);
        sb.append(']');
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected StringBuilder _appendSourceDesc(StringBuilder sb) {
        int length;
        int _append;
        Object obj = this._sourceRef;
        if (obj == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        String name = cls.getName();
        if (name.startsWith("java.")) {
            name = cls.getSimpleName();
        } else if (obj instanceof byte[]) {
            name = "byte[]";
        } else if (obj instanceof char[]) {
            name = "char[]";
        }
        sb.append('(');
        sb.append(name);
        sb.append(')');
        int i = 0;
        String str = " chars";
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            length = charSequence.length();
            _append = _append(sb, charSequence.subSequence(0, Math.min(length, 500)).toString());
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            length = cArr.length;
            _append = _append(sb, new String(cArr, 0, Math.min(length, 500)));
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int min = Math.min(bArr.length, 500);
                _append(sb, new String(bArr, 0, min, Charset.forName("UTF-8")));
                i = bArr.length - min;
                str = " bytes";
            }
            if (i > 0) {
                sb.append("[truncated ");
                sb.append(i);
                sb.append(str);
                sb.append(']');
            }
            return sb;
        }
        i = length - _append;
        if (i > 0) {
        }
        return sb;
    }

    private int _append(StringBuilder sb, String str) {
        sb.append('\"');
        sb.append(str);
        sb.append('\"');
        return str.length();
    }
}
