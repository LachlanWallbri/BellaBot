package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ThrowableDeserializer extends JavaBeanDeserializer {
    @Override // com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
    
        if (java.lang.Throwable.class.isAssignableFrom(r14) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e8, code lost:
    
        if (r14 != null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
    
        r15 = (T) new java.lang.Exception(r5, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0103, code lost:
    
        if (r6 == null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0105, code lost:
    
        ((java.lang.Throwable) r15).setStackTrace(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0108, code lost:
    
        if (r0 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x010a, code lost:
    
        if (r14 == null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010e, code lost:
    
        if (r14 != r12.clazz) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0110, code lost:
    
        r2 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0112, code lost:
    
        r13 = r13.getConfig().getDeserializer(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x011c, code lost:
    
        if ((r13 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x011e, code lost:
    
        r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0121, code lost:
    
        r13 = r0.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012d, code lost:
    
        if (r13.hasNext() == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012f, code lost:
    
        r14 = (java.util.Map.Entry) r13.next();
        r0 = (java.lang.String) r14.getKey();
        r14 = r14.getValue();
        r0 = r2.getFieldDeserializer(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0143, code lost:
    
        if (r0 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0145, code lost:
    
        r0.setValue(r15, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0149, code lost:
    
        return (T) r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f6, code lost:
    
        if (java.lang.Throwable.class.isAssignableFrom(r14) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x016d, code lost:
    
        throw new com.alibaba.fastjson.JSONException("type not match, not Throwable. " + r14.getName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f8, code lost:
    
        r15 = (T) createException(r5, r3, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00fc, code lost:
    
        if (r15 != null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00fe, code lost:
    
        r15 = (T) new java.lang.Exception(r5, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x014a, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0152, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r13);
     */
    @Override // com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Class<?> cls;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        JavaBeanDeserializer javaBeanDeserializer = null;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
        } else if (jSONLexer.token() != 12) {
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
            String scanSymbol = jSONLexer.scanSymbol(defaultJSONParser.getSymbolTable());
            if (scanSymbol == null) {
                if (jSONLexer.token() == 13) {
                    jSONLexer.nextToken(16);
                    break;
                }
                if (jSONLexer.token() == 16 && jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            jSONLexer.nextTokenWithColon(4);
            if (JSON.DEFAULT_TYPE_KEY.equals(scanSymbol)) {
                if (jSONLexer.token() == 4) {
                    cls = defaultJSONParser.getConfig().checkAutoType(jSONLexer.stringVal(), Throwable.class);
                    jSONLexer.nextToken(16);
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("message".equals(scanSymbol)) {
                if (jSONLexer.token() == 8) {
                    str = null;
                } else if (jSONLexer.token() == 4) {
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
            if (jSONLexer.token() == 13) {
                jSONLexer.nextToken(16);
                break;
            }
        }
    }

    private Throwable createException(String str, Throwable th, Class<?> cls) throws Exception {
        Constructor<?> constructor = null;
        Constructor<?> constructor2 = null;
        Constructor<?> constructor3 = null;
        for (Constructor<?> constructor4 : cls.getConstructors()) {
            Class<?>[] parameterTypes = constructor4.getParameterTypes();
            if (parameterTypes.length == 0) {
                constructor3 = constructor4;
            } else if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
                constructor2 = constructor4;
            } else if (parameterTypes.length == 2 && parameterTypes[0] == String.class && parameterTypes[1] == Throwable.class) {
                constructor = constructor4;
            }
        }
        if (constructor != null) {
            return (Throwable) constructor.newInstance(str, th);
        }
        if (constructor2 != null) {
            return (Throwable) constructor2.newInstance(str);
        }
        if (constructor3 != null) {
            return (Throwable) constructor3.newInstance(new Object[0]);
        }
        return null;
    }
}
