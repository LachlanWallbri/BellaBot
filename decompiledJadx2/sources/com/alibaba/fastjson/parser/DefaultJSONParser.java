package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i = 0; i < 17; i++) {
            primitiveClasses.add(clsArr[i]);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        Object obj = this.input;
        if (obj instanceof char[]) {
            return new String((char[]) this.input);
        }
        return obj.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:230:0x023d, code lost:
    
        r4.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0248, code lost:
    
        if (r4.token() != 13) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x024a, code lost:
    
        r4.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x024d, code lost:
    
        r0 = r16.config.getDeserializer(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0255, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0257, code lost:
    
        r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0;
        r2 = r0.createInstance(r16, r7);
        r3 = r8.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0269, code lost:
    
        if (r3.hasNext() == false) goto L364;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x026b, code lost:
    
        r4 = (java.util.Map.Entry) r3.next();
        r8 = r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0277, code lost:
    
        if ((r8 instanceof java.lang.String) == false) goto L367;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0279, code lost:
    
        r8 = r0.getFieldDeserializer((java.lang.String) r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x027f, code lost:
    
        if (r8 == null) goto L368;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0281, code lost:
    
        r8.setValue(r2, r4.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x028a, code lost:
    
        if (r2 != null) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x028e, code lost:
    
        if (r7 != java.lang.Cloneable.class) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x0290, code lost:
    
        r2 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x029c, code lost:
    
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x029e, code lost:
    
        r2 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x02a3, code lost:
    
        r2 = r7.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x02aa, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0289, code lost:
    
        r2 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x02ab, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x02b3, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x02b4, code lost:
    
        setResolveStatus(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x02ba, code lost:
    
        if (r16.context == null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x02bc, code lost:
    
        if (r18 == null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x02c0, code lost:
    
        if ((r18 instanceof java.lang.Integer) != false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x02c8, code lost:
    
        if ((r16.context.fieldName instanceof java.lang.Integer) != false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x02ca, code lost:
    
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x02d1, code lost:
    
        if (r17.size() <= 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x02d3, code lost:
    
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r17, (java.lang.Class<java.lang.Object>) r7, r16.config);
        parseObject(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x02df, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x02ed, code lost:
    
        return r16.config.getDeserializer(r7).deserialze(r16, r7, r18);
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x03e4 A[Catch: all -> 0x05bc, TRY_ENTER, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0440 A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0449 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0511 A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x051d A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0529 A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x053e A[Catch: all -> 0x05bc, TRY_ENTER, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0534 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01e7 A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03b7 A[Catch: all -> 0x05bc, TryCatch #1 {all -> 0x05bc, blocks: (B:18:0x005d, B:20:0x0061, B:22:0x006b, B:25:0x007e, B:29:0x0098, B:33:0x01e7, B:34:0x01ed, B:36:0x01f8, B:218:0x0200, B:224:0x0214, B:226:0x0222, B:228:0x0231, B:230:0x023d, B:232:0x024a, B:234:0x024d, B:236:0x0257, B:237:0x0265, B:239:0x026b, B:242:0x0279, B:245:0x0281, B:254:0x0290, B:255:0x0296, B:257:0x029e, B:258:0x02a3, B:263:0x02ac, B:264:0x02b3, B:265:0x02b4, B:268:0x02be, B:270:0x02c2, B:272:0x02ca, B:273:0x02cd, B:275:0x02d3, B:278:0x02e0, B:281:0x0228, B:43:0x02f5, B:46:0x02fd, B:48:0x0307, B:50:0x0318, B:52:0x031c, B:54:0x0324, B:57:0x0329, B:59:0x032d, B:60:0x037f, B:62:0x0387, B:65:0x0390, B:66:0x0395, B:69:0x0334, B:71:0x033c, B:73:0x0340, B:74:0x0343, B:75:0x034f, B:78:0x0358, B:80:0x035c, B:82:0x035f, B:84:0x0363, B:85:0x0367, B:86:0x0373, B:87:0x0396, B:88:0x03b4, B:90:0x03b7, B:92:0x03bb, B:94:0x03c1, B:96:0x03c7, B:97:0x03ca, B:101:0x03d2, B:107:0x03e4, B:109:0x03f3, B:111:0x03fe, B:112:0x0406, B:113:0x0409, B:114:0x0435, B:116:0x0440, B:123:0x044b, B:126:0x045b, B:127:0x0479, B:132:0x0419, B:134:0x0423, B:135:0x0432, B:136:0x0428, B:141:0x047e, B:143:0x0488, B:145:0x0490, B:146:0x0493, B:148:0x049e, B:149:0x04a2, B:158:0x04ad, B:151:0x04b4, B:155:0x04c1, B:156:0x04c6, B:163:0x04cb, B:165:0x04d0, B:168:0x04db, B:170:0x04e8, B:171:0x04ee, B:174:0x04f4, B:175:0x04fa, B:177:0x0502, B:179:0x0511, B:182:0x0519, B:184:0x051d, B:185:0x0524, B:187:0x0529, B:188:0x052c, B:199:0x0534, B:190:0x053e, B:193:0x0548, B:194:0x054d, B:196:0x0552, B:197:0x056c, B:206:0x056d, B:214:0x057f, B:208:0x0586, B:211:0x0591, B:212:0x05af, B:284:0x00aa, B:285:0x00c8, B:350:0x00cb, B:352:0x00d6, B:354:0x00da, B:356:0x00e0, B:358:0x00e6, B:359:0x00e9, B:289:0x00f8, B:291:0x0100, B:295:0x0110, B:296:0x0128, B:298:0x0129, B:299:0x012e, B:308:0x0143, B:310:0x0149, B:312:0x0150, B:314:0x015a, B:318:0x0162, B:319:0x017a, B:320:0x0155, B:322:0x017b, B:323:0x0193, B:331:0x019d, B:333:0x01a5, B:337:0x01b6, B:338:0x01d6, B:340:0x01d7, B:341:0x01dc, B:342:0x01dd, B:344:0x05b0, B:345:0x05b5, B:347:0x05b6, B:348:0x05bb), top: B:17:0x005d, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object parseObject(Map map, Object obj) {
        Object parse;
        boolean z;
        char current;
        Object obj2;
        Object obj3;
        char current2;
        Object obj4;
        Object obj5;
        Class<?> checkAutoType;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (jSONLexer.token() == 13) {
            jSONLexer.nextToken();
            return map;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName() + ", " + jSONLexer.info());
        }
        ParseContext parseContext = this.context;
        try {
            Map innerMap = map instanceof JSONObject ? ((JSONObject) map).getInnerMap() : map;
            boolean z2 = false;
            while (true) {
                jSONLexer.skipWhitespace();
                char current3 = jSONLexer.getCurrent();
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (current3 == ',') {
                        jSONLexer.next();
                        jSONLexer.skipWhitespace();
                        current3 = jSONLexer.getCurrent();
                    }
                }
                boolean z3 = true;
                if (current3 == '\"') {
                    parse = jSONLexer.scanSymbol(this.symbolTable, '\"');
                    jSONLexer.skipWhitespace();
                    if (jSONLexer.getCurrent() != ':') {
                        throw new JSONException("expect ':' at " + jSONLexer.pos() + ", name " + parse);
                    }
                } else {
                    if (current3 == '}') {
                        jSONLexer.next();
                        jSONLexer.resetStringPosition();
                        jSONLexer.nextToken();
                        if (!z2) {
                            if (this.context != null && obj == this.context.fieldName && map == this.context.object) {
                                parseContext = this.context;
                            } else {
                                ParseContext context = setContext(map, obj);
                                if (parseContext == null) {
                                    parseContext = context;
                                }
                            }
                        }
                        return map;
                    }
                    if (current3 == '\'') {
                        if (!jSONLexer.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("syntax error");
                        }
                        parse = jSONLexer.scanSymbol(this.symbolTable, '\'');
                        jSONLexer.skipWhitespace();
                        if (jSONLexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + jSONLexer.pos());
                        }
                    } else {
                        if (current3 == 26) {
                            throw new JSONException("syntax error");
                        }
                        if (current3 == ',') {
                            throw new JSONException("syntax error");
                        }
                        if ((current3 < '0' || current3 > '9') && current3 != '-') {
                            if (current3 != '{' && current3 != '[') {
                                if (!jSONLexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                    throw new JSONException("syntax error");
                                }
                                parse = jSONLexer.scanSymbolUnQuoted(this.symbolTable);
                                jSONLexer.skipWhitespace();
                                char current4 = jSONLexer.getCurrent();
                                if (current4 != ':') {
                                    throw new JSONException("expect ':' at " + jSONLexer.pos() + ", actual " + current4);
                                }
                            }
                            jSONLexer.nextToken();
                            parse = parse();
                            z = true;
                            if (!z) {
                                jSONLexer.next();
                                jSONLexer.skipWhitespace();
                            }
                            current = jSONLexer.getCurrent();
                            jSONLexer.resetStringPosition();
                            if (parse != JSON.DEFAULT_TYPE_KEY && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                String scanSymbol = jSONLexer.scanSymbol(this.symbolTable, '\"');
                                if (!jSONLexer.isEnabled(Feature.IgnoreAutoType)) {
                                    if (map == null || !map.getClass().getName().equals(scanSymbol)) {
                                        obj5 = null;
                                        checkAutoType = this.config.checkAutoType(scanSymbol, null);
                                    } else {
                                        checkAutoType = map.getClass();
                                        obj5 = null;
                                    }
                                    if (checkAutoType != null) {
                                        break;
                                    }
                                    innerMap.put(JSON.DEFAULT_TYPE_KEY, scanSymbol);
                                } else {
                                    obj5 = null;
                                }
                            } else {
                                if (parse != "$ref" && parseContext != null && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                    jSONLexer.nextToken(4);
                                    if (jSONLexer.token() != 4) {
                                        throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                                    }
                                    String stringVal = jSONLexer.stringVal();
                                    jSONLexer.nextToken(13);
                                    if ("@".equals(stringVal)) {
                                        if (this.context != null) {
                                            ParseContext parseContext2 = this.context;
                                            Object obj6 = parseContext2.object;
                                            if (!(obj6 instanceof Object[]) && !(obj6 instanceof Collection)) {
                                                if (parseContext2.parent != null) {
                                                    obj4 = parseContext2.parent.object;
                                                }
                                            }
                                            obj4 = obj6;
                                        }
                                        obj4 = null;
                                    } else if (!"..".equals(stringVal)) {
                                        if ("$".equals(stringVal)) {
                                            ParseContext parseContext3 = parseContext;
                                            while (parseContext3.parent != null) {
                                                parseContext3 = parseContext3.parent;
                                            }
                                            if (parseContext3.object != null) {
                                                obj4 = parseContext3.object;
                                            } else {
                                                addResolveTask(new ResolveTask(parseContext3, stringVal));
                                                setResolveStatus(1);
                                            }
                                        } else {
                                            addResolveTask(new ResolveTask(parseContext, stringVal));
                                            setResolveStatus(1);
                                        }
                                        obj4 = null;
                                    } else if (parseContext.object != null) {
                                        obj4 = parseContext.object;
                                    } else {
                                        addResolveTask(new ResolveTask(parseContext, stringVal));
                                        setResolveStatus(1);
                                        obj4 = null;
                                    }
                                    if (jSONLexer.token() != 13) {
                                        throw new JSONException("syntax error");
                                    }
                                    jSONLexer.nextToken(16);
                                    return obj4;
                                }
                                if (!z2) {
                                    if (this.context != null && obj == this.context.fieldName && map == this.context.object) {
                                        parseContext = this.context;
                                    } else {
                                        ParseContext context2 = setContext(map, obj);
                                        if (parseContext == null) {
                                            parseContext = context2;
                                        }
                                        z2 = true;
                                    }
                                }
                                if (map.getClass() == JSONObject.class && parse == null) {
                                    parse = "null";
                                }
                                if (current != '\"') {
                                    jSONLexer.scanString();
                                    String stringVal2 = jSONLexer.stringVal();
                                    Object obj7 = stringVal2;
                                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                        JSONScanner jSONScanner = new JSONScanner(stringVal2);
                                        Object obj8 = stringVal2;
                                        if (jSONScanner.scanISO8601DateIfMatch()) {
                                            obj8 = jSONScanner.getCalendar().getTime();
                                        }
                                        jSONScanner.close();
                                        obj7 = obj8;
                                    }
                                    innerMap.put(parse, obj7);
                                    obj3 = obj7;
                                } else if ((current >= '0' && current <= '9') || current == '-') {
                                    jSONLexer.scanNumber();
                                    Number integerValue = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                                    innerMap.put(parse, integerValue);
                                    obj3 = integerValue;
                                } else if (current == '[') {
                                    jSONLexer.nextToken();
                                    Collection jSONArray = new JSONArray();
                                    if (obj != null) {
                                        obj.getClass();
                                    }
                                    if (obj == null) {
                                        setContext(parseContext);
                                    }
                                    parseArray(jSONArray, parse);
                                    Object[] objArr = jSONArray;
                                    if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                        objArr = jSONArray.toArray();
                                    }
                                    innerMap.put(parse, objArr);
                                    if (jSONLexer.token() == 13) {
                                        jSONLexer.nextToken();
                                        return map;
                                    }
                                    if (jSONLexer.token() != 16) {
                                        throw new JSONException("syntax error");
                                    }
                                } else if (current == '{') {
                                    jSONLexer.nextToken();
                                    boolean z4 = obj != null && obj.getClass() == Integer.class;
                                    JSONObject jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                                    ParseContext context3 = !z4 ? setContext(parseContext, jSONObject, parse) : null;
                                    if (this.fieldTypeResolver != null) {
                                        Type resolve = this.fieldTypeResolver.resolve(map, parse != null ? parse.toString() : null);
                                        if (resolve != null) {
                                            obj2 = this.config.getDeserializer(resolve).deserialze(this, resolve, parse);
                                            if (!z3) {
                                                obj2 = parseObject(jSONObject, parse);
                                            }
                                            if (context3 != null && jSONObject != obj2) {
                                                context3.object = map;
                                            }
                                            if (parse != null) {
                                                checkMapResolve(map, parse.toString());
                                            }
                                            innerMap.put(parse, obj2);
                                            if (z4) {
                                                setContext(obj2, parse);
                                            }
                                            if (jSONLexer.token() != 13) {
                                                jSONLexer.nextToken();
                                                setContext(parseContext);
                                                return map;
                                            }
                                            if (jSONLexer.token() != 16) {
                                                throw new JSONException("syntax error, " + jSONLexer.tokenName());
                                            }
                                            if (z4) {
                                                popContext();
                                            } else {
                                                setContext(parseContext);
                                            }
                                        }
                                    }
                                    obj2 = null;
                                    z3 = false;
                                    if (!z3) {
                                    }
                                    if (context3 != null) {
                                        context3.object = map;
                                    }
                                    if (parse != null) {
                                    }
                                    innerMap.put(parse, obj2);
                                    if (z4) {
                                    }
                                    if (jSONLexer.token() != 13) {
                                    }
                                } else {
                                    jSONLexer.nextToken();
                                    innerMap.put(parse, parse());
                                    if (jSONLexer.token() == 13) {
                                        jSONLexer.nextToken();
                                        return map;
                                    }
                                    if (jSONLexer.token() != 16) {
                                        throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + parse);
                                    }
                                }
                                jSONLexer.skipWhitespace();
                                current2 = jSONLexer.getCurrent();
                                if (current2 == ',') {
                                    if (current2 == '}') {
                                        jSONLexer.next();
                                        jSONLexer.resetStringPosition();
                                        jSONLexer.nextToken();
                                        setContext(obj3, parse);
                                        return map;
                                    }
                                    throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + parse);
                                }
                                jSONLexer.next();
                            }
                        } else {
                            jSONLexer.resetStringPosition();
                            jSONLexer.scanNumber();
                            try {
                                parse = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(true);
                                if (jSONLexer.getCurrent() != ':') {
                                    throw new JSONException("parse number key error" + jSONLexer.info());
                                }
                            } catch (NumberFormatException unused) {
                                throw new JSONException("parse number key error" + jSONLexer.info());
                            }
                        }
                    }
                }
                z = false;
                if (!z) {
                }
                current = jSONLexer.getCurrent();
                jSONLexer.resetStringPosition();
                if (parse != JSON.DEFAULT_TYPE_KEY) {
                }
                if (parse != "$ref") {
                }
                if (!z2) {
                }
                if (map.getClass() == JSONObject.class) {
                    parse = "null";
                }
                if (current != '\"') {
                }
                jSONLexer.skipWhitespace();
                current2 = jSONLexer.getCurrent();
                if (current2 == ',') {
                }
            }
        } finally {
            setContext(parseContext);
        }
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) stringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(deserializer.getFastMatchToken());
        }
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i2 = 0;
        while (true) {
            try {
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (this.lexer.token() == 16) {
                        this.lexer.nextToken();
                    }
                }
                if (this.lexer.token() != 15) {
                    Object obj2 = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            obj2 = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object parse = parse();
                            if (parse != null) {
                                obj2 = parse.toString();
                            }
                        }
                        collection.add(obj2);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            obj2 = deserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(obj2);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i2++;
                } else {
                    setContext(parseContext);
                    this.lexer.nextToken(16);
                    return;
                }
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object cast;
        Class<?> cls;
        boolean z;
        int i = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i2 = 14;
        if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return new Object[0];
        }
        this.lexer.nextToken(2);
        int i3 = 0;
        while (i3 < typeArr.length) {
            if (this.lexer.token() == i) {
                this.lexer.nextToken(16);
                cast = null;
            } else {
                Type type = typeArr[i3];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token() == 2) {
                        cast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        cast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token() == 4) {
                        cast = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        cast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else {
                    if (i3 == typeArr.length - 1 && (type instanceof Class)) {
                        Class cls2 = (Class) type;
                        z = cls2.isArray();
                        cls = cls2.getComponentType();
                    } else {
                        cls = null;
                        z = false;
                    }
                    if (z && this.lexer.token() != i2) {
                        ArrayList arrayList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
                        int fastMatchToken = deserializer.getFastMatchToken();
                        if (this.lexer.token() != 15) {
                            while (true) {
                                arrayList.add(deserializer.deserialze(this, type, null));
                                if (this.lexer.token() != 16) {
                                    break;
                                }
                                this.lexer.nextToken(fastMatchToken);
                            }
                            if (this.lexer.token() != 15) {
                                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                            }
                        }
                        cast = TypeUtils.cast(arrayList, type, this.config);
                    } else {
                        cast = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i3));
                    }
                }
            }
            objArr[i3] = cast;
            if (this.lexer.token() == 15) {
                break;
            }
            if (this.lexer.token() != 16) {
                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
            }
            if (i3 == typeArr.length - 1) {
                this.lexer.nextToken(15);
            } else {
                this.lexer.nextToken(2);
            }
            i3++;
            i = 8;
            i2 = 14;
        }
        if (this.lexer.token() != 15) {
            throw new JSONException("syntax error");
        }
        this.lexer.nextToken(16);
        return objArr;
    }

    public void parseObject(Object obj) {
        Object deserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer ? (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
        while (true) {
            String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (scanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() == 16 && this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
            if (fieldDeserializer == null) {
                if (!this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
                this.lexer.nextTokenWithColon();
                parse();
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken();
                    return;
                }
            } else {
                Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                Type type = fieldDeserializer.fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    deserialze = StringCodec.deserialze(this);
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    deserialze = LongCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                    deserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, deserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (Object.class.equals(type3)) {
                if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                }
                throw new JSONException("not support type : " + type);
            }
            ArrayList arrayList2 = new ArrayList();
            parseArray((Class<?>) type3, (Collection) arrayList2);
            return arrayList2;
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
                return;
            }
            return;
        }
        throw new JSONException("type not match error");
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (collection instanceof List) {
                int size = collection.size() - 1;
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.fieldDeserializer = new com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer(this, (List) collection, size);
                lastResolveTask.ownerContext = this.context;
                setResolveStatus(0);
                return;
            }
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.fieldDeserializer = new com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer(collection);
            lastResolveTask2.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer resolveFieldDeserializer = new com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Number decimalValue;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 21 || jSONLexer.token() == 22) {
            jSONLexer.nextToken();
        }
        if (jSONLexer.token() != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(jSONLexer.token()) + ", pos " + jSONLexer.pos() + ", fieldName " + obj);
        }
        jSONLexer.nextToken(4);
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                int i2 = jSONLexer.token();
                Object obj2 = null;
                obj2 = null;
                if (i2 == 2) {
                    Number integerValue = jSONLexer.integerValue();
                    jSONLexer.nextToken(16);
                    obj2 = integerValue;
                } else if (i2 == 3) {
                    if (jSONLexer.isEnabled(Feature.UseBigDecimal)) {
                        decimalValue = jSONLexer.decimalValue(true);
                    } else {
                        decimalValue = jSONLexer.decimalValue(false);
                    }
                    obj2 = decimalValue;
                    jSONLexer.nextToken(16);
                } else if (i2 == 4) {
                    String stringVal = jSONLexer.stringVal();
                    jSONLexer.nextToken(16);
                    obj2 = stringVal;
                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                        JSONScanner jSONScanner = new JSONScanner(stringVal);
                        Object obj3 = stringVal;
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            obj3 = jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                        obj2 = obj3;
                    }
                } else if (i2 == 6) {
                    Boolean bool = Boolean.TRUE;
                    jSONLexer.nextToken(16);
                    obj2 = bool;
                } else if (i2 == 7) {
                    Boolean bool2 = Boolean.FALSE;
                    jSONLexer.nextToken(16);
                    obj2 = bool2;
                } else if (i2 == 8) {
                    jSONLexer.nextToken(4);
                } else if (i2 == 12) {
                    obj2 = parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), Integer.valueOf(i));
                } else {
                    if (i2 == 20) {
                        throw new JSONException("unclosed jsonArray");
                    }
                    if (i2 == 23) {
                        jSONLexer.nextToken(4);
                    } else if (i2 == 14) {
                        JSONArray jSONArray = new JSONArray();
                        parseArray(jSONArray, Integer.valueOf(i));
                        obj2 = jSONArray;
                        if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                            obj2 = jSONArray.toArray();
                        }
                    } else {
                        if (i2 == 15) {
                            jSONLexer.nextToken(16);
                            return;
                        }
                        obj2 = parse();
                    }
                }
                collection.add(obj2);
                checkListResolve(collection);
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
                i++;
            } finally {
                setContext(parseContext);
            }
        }
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        int i = this.contextArrayIndex;
        if (i <= 0) {
            return;
        }
        int i2 = i - 1;
        this.contextArrayIndex = i2;
        this.contextArray[i2] = null;
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        ParseContext parseContext2 = new ParseContext(parseContext, obj, obj2);
        this.context = parseContext2;
        addContext(parseContext2);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() == 18) {
            String stringVal = this.lexer.stringVal();
            this.lexer.nextToken(16);
            return stringVal;
        }
        return parse(null);
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token();
        if (i == 2) {
            Number integerValue = jSONLexer.integerValue();
            jSONLexer.nextToken();
            return integerValue;
        }
        if (i == 3) {
            Number decimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
            jSONLexer.nextToken();
            return decimalValue;
        }
        if (i == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(stringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                } finally {
                    jSONScanner.close();
                }
            }
            return stringVal;
        }
        if (i == 12) {
            return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
        }
        if (i == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (i == 18) {
            if ("NaN".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                return null;
            }
            throw new JSONException("syntax error, " + jSONLexer.info());
        }
        if (i != 26) {
            switch (i) {
                case 6:
                    jSONLexer.nextToken();
                    return Boolean.TRUE;
                case 7:
                    jSONLexer.nextToken();
                    return Boolean.FALSE;
                case 8:
                    jSONLexer.nextToken();
                    return null;
                case 9:
                    jSONLexer.nextToken(18);
                    if (jSONLexer.token() != 18) {
                        throw new JSONException("syntax error");
                    }
                    jSONLexer.nextToken(10);
                    accept(10);
                    long longValue = jSONLexer.integerValue().longValue();
                    accept(2);
                    accept(11);
                    return new Date(longValue);
                default:
                    switch (i) {
                        case 20:
                            if (jSONLexer.isBlankInput()) {
                                return null;
                            }
                            throw new JSONException("unterminated json string, " + jSONLexer.info());
                        case 21:
                            jSONLexer.nextToken();
                            HashSet hashSet = new HashSet();
                            parseArray(hashSet, obj);
                            return hashSet;
                        case 22:
                            jSONLexer.nextToken();
                            TreeSet treeSet = new TreeSet();
                            parseArray(treeSet, obj);
                            return treeSet;
                        case 23:
                            jSONLexer.nextToken();
                            return null;
                        default:
                            throw new JSONException("syntax error, " + jSONLexer.info());
                    }
            }
        }
        byte[] bytesValue = jSONLexer.bytesValue();
        jSONLexer.nextToken();
        return bytesValue;
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource) && jSONLexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
            }
        } finally {
            jSONLexer.close();
        }
    }

    public Object resolveReference(String str) {
        if (this.contextArray == null) {
            return null;
        }
        int i = 0;
        while (true) {
            ParseContext[] parseContextArr = this.contextArray;
            if (i >= parseContextArr.length || i >= this.contextArrayIndex) {
                break;
            }
            ParseContext parseContext = parseContextArr[i];
            if (parseContext.toString().equals(str)) {
                return parseContext.object;
            }
            i++;
        }
        return null;
    }

    public void handleResovleTask(Object obj) {
        Object obj2;
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            String str = resolveTask.referenceValue;
            Object obj3 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
            if (str.startsWith("$")) {
                obj2 = getObject(str);
                if (obj2 == null) {
                    try {
                        obj2 = JSONPath.eval(obj, str);
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                obj2 = resolveTask.context.object;
            }
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                if (obj2 != null && obj2.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                    obj2 = JSONPath.eval(this.contextArray[0].object, str);
                }
                fieldDeserializer.setValue(obj3, obj2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object parseObject;
        this.lexer.nextTokenWithColon();
        List<ExtraTypeProvider> list = this.extraTypeProviders;
        Type type = null;
        if (list != null) {
            Iterator<ExtraTypeProvider> it = list.iterator();
            while (it.hasNext()) {
                type = it.next().getExtraType(obj, str);
            }
        }
        if (type == null) {
            parseObject = parse();
        } else {
            parseObject = parseObject(type);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, parseObject);
            return;
        }
        List<ExtraProcessor> list2 = this.extraProcessors;
        if (list2 != null) {
            Iterator<ExtraProcessor> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().processExtra(obj, str, parseObject);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0230, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object parse(PropertyProcessable propertyProcessable, Object obj) {
        String scanSymbolUnQuoted;
        int i = 0;
        if (this.lexer.token() != 12) {
            String str = "syntax error, expect {, actual " + this.lexer.tokenName();
            if (obj instanceof String) {
                str = (str + ", fieldName ") + obj;
            }
            String str2 = (str + ", ") + this.lexer.info();
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            if (jSONArray.size() == 1) {
                Object obj2 = jSONArray.get(0);
                if (obj2 instanceof JSONObject) {
                    return (JSONObject) obj2;
                }
            }
            throw new JSONException(str2);
        }
        ParseContext parseContext = this.context;
        while (true) {
            try {
                this.lexer.skipWhitespace();
                char current = this.lexer.getCurrent();
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (current == ',') {
                        this.lexer.next();
                        this.lexer.skipWhitespace();
                        current = this.lexer.getCurrent();
                    }
                }
                if (current == '\"') {
                    scanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, '\"');
                    this.lexer.skipWhitespace();
                    if (this.lexer.getCurrent() != ':') {
                        throw new JSONException("expect ':' at " + this.lexer.pos());
                    }
                } else {
                    if (current == '}') {
                        this.lexer.next();
                        this.lexer.resetStringPosition();
                        this.lexer.nextToken(16);
                        return propertyProcessable;
                    }
                    if (current == '\'') {
                        if (!this.lexer.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("syntax error");
                        }
                        scanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, '\'');
                        this.lexer.skipWhitespace();
                        if (this.lexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos());
                        }
                    } else {
                        if (!this.lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                            throw new JSONException("syntax error");
                        }
                        scanSymbolUnQuoted = this.lexer.scanSymbolUnQuoted(this.symbolTable);
                        this.lexer.skipWhitespace();
                        char current2 = this.lexer.getCurrent();
                        if (current2 != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos() + ", actual " + current2);
                        }
                    }
                }
                this.lexer.next();
                this.lexer.skipWhitespace();
                this.lexer.getCurrent();
                this.lexer.resetStringPosition();
                Object obj3 = null;
                if (scanSymbolUnQuoted != JSON.DEFAULT_TYPE_KEY || this.lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    this.lexer.nextToken();
                    if (i != 0) {
                        setContext(parseContext);
                    }
                    Type type = propertyProcessable.getType(scanSymbolUnQuoted);
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                    } else {
                        obj3 = parseObject(type, scanSymbolUnQuoted);
                    }
                    propertyProcessable.apply(scanSymbolUnQuoted, obj3);
                    setContext(parseContext, obj3, scanSymbolUnQuoted);
                    setContext(parseContext);
                    int i2 = this.lexer.token();
                    if (i2 == 20 || i2 == 15) {
                        break;
                    }
                    if (i2 == 13) {
                        this.lexer.nextToken();
                        return propertyProcessable;
                    }
                } else {
                    Class<?> checkAutoType = this.config.checkAutoType(this.lexer.scanSymbol(this.symbolTable, '\"'), null);
                    if (!Map.class.isAssignableFrom(checkAutoType)) {
                        ObjectDeserializer deserializer = this.config.getDeserializer(checkAutoType);
                        this.lexer.nextToken(16);
                        setResolveStatus(2);
                        if (parseContext != null && !(obj instanceof Integer)) {
                            popContext();
                        }
                        return (Map) deserializer.deserialze(this, checkAutoType, obj);
                    }
                    this.lexer.nextToken(16);
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return propertyProcessable;
                    }
                }
                i++;
            } finally {
                setContext(parseContext);
            }
        }
    }
}
