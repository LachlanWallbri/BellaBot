package com.amazonaws.services.p048s3.internal;

import com.amazonaws.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class XmlWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    List<String> tags = new ArrayList();

    /* renamed from: sb */
    StringBuilder f1175sb = new StringBuilder();

    public XmlWriter start(String str) {
        StringBuilder sb = this.f1175sb;
        sb.append("<");
        sb.append(str);
        sb.append(">");
        this.tags.add(str);
        return this;
    }

    public XmlWriter start(String str, String str2, String str3) {
        StringBuilder sb = this.f1175sb;
        sb.append("<");
        sb.append(str);
        writeAttr(str2, str3);
        this.f1175sb.append(">");
        this.tags.add(str);
        return this;
    }

    public XmlWriter start(String str, String[] strArr, String[] strArr2) {
        StringBuilder sb = this.f1175sb;
        sb.append("<");
        sb.append(str);
        for (int i = 0; i < Math.min(strArr.length, strArr2.length); i++) {
            writeAttr(strArr[i], strArr2[i]);
        }
        this.f1175sb.append(">");
        this.tags.add(str);
        return this;
    }

    public XmlWriter end() {
        String remove = this.tags.remove(r0.size() - 1);
        StringBuilder sb = this.f1175sb;
        sb.append("</");
        sb.append(remove);
        sb.append(">");
        return this;
    }

    public byte[] getBytes() {
        return toString().getBytes(StringUtils.UTF8);
    }

    public String toString() {
        return this.f1175sb.toString();
    }

    public XmlWriter value(String str) {
        appendEscapedString(str, this.f1175sb);
        return this;
    }

    private void writeAttr(String str, String str2) {
        StringBuilder sb = this.f1175sb;
        sb.append(' ');
        sb.append(str);
        sb.append("=\"");
        appendEscapedString(str2, this.f1175sb);
        this.f1175sb.append("\"");
    }

    private void appendEscapedString(String str, StringBuilder sb) {
        if (str == null) {
            str = "";
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String str2 = charAt != '\t' ? charAt != '\n' ? charAt != '\r' ? charAt != '\"' ? charAt != '&' ? charAt != '<' ? charAt != '>' ? null : "&gt;" : "&lt;" : "&amp;" : "&quot;" : "&#13;" : "&#10;" : "&#9;";
            if (str2 != null) {
                if (i2 < i) {
                    sb.append((CharSequence) str, i2, i);
                }
                this.f1175sb.append(str2);
                i2 = i + 1;
            }
            i++;
        }
        if (i2 < i) {
            this.f1175sb.append((CharSequence) str, i2, i);
        }
    }
}
