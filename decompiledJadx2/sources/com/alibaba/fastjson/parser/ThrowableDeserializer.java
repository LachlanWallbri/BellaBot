package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        if (java.lang.Throwable.class.isAssignableFrom(r2) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d7, code lost:
    
        if (r2 != null) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d9, code lost:
    
        r3 = (T) new java.lang.Exception(r11, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0161, code lost:
    
        if (r12 == null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0163, code lost:
    
        ((java.lang.Throwable) r3).setStackTrace(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0166, code lost:
    
        if (r4 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0168, code lost:
    
        if (r2 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x016c, code lost:
    
        if (r2 != r17.clazz) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x016e, code lost:
    
        r6 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x017f, code lost:
    
        if (r6 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0181, code lost:
    
        r0 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x018d, code lost:
    
        if (r0.hasNext() == false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x018f, code lost:
    
        r2 = (java.util.Map.Entry) r0.next();
        r4 = (java.lang.String) r2.getKey();
        r2 = r2.getValue();
        r4 = r6.getFieldDeserializer(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x01a3, code lost:
    
        if (r4 == null) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01a5, code lost:
    
        r4.setValue(r3, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0170, code lost:
    
        r0 = r18.config.getDeserializer(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0178, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x017a, code lost:
    
        r6 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x017e, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01a9, code lost:
    
        return (T) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e0, code lost:
    
        r3 = r2.getConstructors();
        r5 = r3.length;
        r8 = null;
        r13 = null;
        r14 = null;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ea, code lost:
    
        if (r7 >= r5) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ec, code lost:
    
        r16 = r3[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f3, code lost:
    
        if (r16.getParameterTypes().length != 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f5, code lost:
    
        r14 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x012b, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00fd, code lost:
    
        if (r16.getParameterTypes().length != 1) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0107, code lost:
    
        if (r16.getParameterTypes()[0] != java.lang.String.class) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0109, code lost:
    
        r13 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0112, code lost:
    
        if (r16.getParameterTypes().length != 2) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x011c, code lost:
    
        if (r16.getParameterTypes()[0] != java.lang.String.class) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0127, code lost:
    
        if (r16.getParameterTypes()[1] != java.lang.Throwable.class) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0129, code lost:
    
        r8 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x012f, code lost:
    
        if (r8 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0131, code lost:
    
        r3 = (T) ((java.lang.Throwable) r8.newInstance(r11, r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x015a, code lost:
    
        if (r3 != null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x015c, code lost:
    
        r3 = (T) new java.lang.Exception(r11, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0140, code lost:
    
        if (r13 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0142, code lost:
    
        r3 = (java.lang.Throwable) r13.newInstance(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x014e, code lost:
    
        if (r14 == null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0150, code lost:
    
        r3 = (java.lang.Throwable) r14.newInstance(new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0159, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01aa, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b2, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    @Override // com.alibaba.fastjson.parser.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Class<?> cls;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
        } else if (jSONLexer.token != 12) {
            throw new JSONException("syntax error");
        }
        if (type != null && (type instanceof Class)) {
            cls = (Class) type;
        }
        cls = null;
        HashMap hashMap = null;
        Throwable th = null;
        String str = null;
        StackTraceElement[] stackTraceElementArr = null;
        while (true) {
            String scanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
            if (scanSymbol == null) {
                if (jSONLexer.token == 13) {
                    jSONLexer.nextToken(16);
                    break;
                }
                if (jSONLexer.token == 16) {
                    continue;
                }
            }
            jSONLexer.nextTokenWithChar(':');
            if ("@type".equals(scanSymbol)) {
                if (jSONLexer.token == 4) {
                    cls = TypeUtils.loadClass(jSONLexer.stringVal(), defaultJSONParser.config.defaultClassLoader, false);
                    jSONLexer.nextToken(16);
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("message".equals(scanSymbol)) {
                if (jSONLexer.token == 8) {
                    str = null;
                } else if (jSONLexer.token == 4) {
                    str = jSONLexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken();
            } else if ("cause".equals(scanSymbol)) {
                th = (Throwable) deserialze(defaultJSONParser, null, "cause");
            } else if ("stackTrace".equals(scanSymbol)) {
                stackTraceElementArr = (StackTraceElement[]) defaultJSONParser.parseObject((Class) StackTraceElement[].class);
            } else {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(scanSymbol, defaultJSONParser.parse());
            }
            if (jSONLexer.token == 13) {
                jSONLexer.nextToken(16);
                break;
            }
        }
    }
}
