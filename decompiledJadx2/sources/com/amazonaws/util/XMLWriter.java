package com.amazonaws.util;

import com.amazonaws.AmazonClientException;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Stack;

/* loaded from: classes.dex */
public class XMLWriter {
    private static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private Stack<String> elementStack;
    private boolean rootElement;
    private final Writer writer;
    private final String xmlns;

    public XMLWriter(Writer writer) {
        this(writer, null);
    }

    public XMLWriter(Writer writer, String str) {
        this.elementStack = new Stack<>();
        this.rootElement = true;
        this.writer = writer;
        this.xmlns = str;
        append(PROLOG);
    }

    public XMLWriter startElement(String str) {
        append("<" + str);
        if (this.rootElement && this.xmlns != null) {
            append(" xmlns=\"" + this.xmlns + "\"");
            this.rootElement = false;
        }
        append(">");
        this.elementStack.push(str);
        return this;
    }

    public XMLWriter endElement() {
        append("</" + this.elementStack.pop() + ">");
        return this;
    }

    public XMLWriter value(String str) {
        append(escapeXMLEntities(str));
        return this;
    }

    public XMLWriter value(Date date) {
        append(escapeXMLEntities(StringUtils.fromDate(date)));
        return this;
    }

    public XMLWriter value(Object obj) {
        append(escapeXMLEntities(obj.toString()));
        return this;
    }

    private void append(String str) {
        try {
            this.writer.append((CharSequence) str);
        } catch (IOException e) {
            throw new AmazonClientException("Unable to write XML document", e);
        }
    }

    private String escapeXMLEntities(String str) {
        if (str.contains("&")) {
            str = str.replace("&quot;", "\"").replace("&apos;", "'").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
        }
        return str.replace("&", "&amp;").replace("\"", "&quot;").replace("'", "&apos;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
