package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class JsonParser implements Closeable {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap<>();
    private static final Lock lock = new ReentrantLock();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> cls) throws IOException {
        return (T) parseAndClose((Class) cls, (CustomizeJsonParser) null);
    }

    public final <T> T parseAndClose(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return (T) parse((Class) cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final void skipToKey(String str) throws IOException {
        skipToKey(Collections.singleton(str));
    }

    public final String skipToKey(Set<String> set) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (set.contains(text)) {
                return text;
            }
            skipChildren();
            startParsingObjectOrArray = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken startParsing = startParsing();
        int i = C20321.$SwitchMap$com$google$api$client$json$JsonToken[startParsing.ordinal()];
        boolean z = true;
        if (i != 1) {
            return i != 2 ? startParsing : nextToken();
        }
        JsonToken nextToken = nextToken();
        if (nextToken != JsonToken.FIELD_NAME && nextToken != JsonToken.END_OBJECT) {
            z = false;
        }
        Preconditions.checkArgument(z, nextToken);
        return nextToken;
    }

    public final void parseAndClose(Object obj) throws IOException {
        parseAndClose(obj, (CustomizeJsonParser) null);
    }

    public final void parseAndClose(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parse(obj, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> cls) throws IOException {
        return (T) parse((Class) cls, (CustomizeJsonParser) null);
    }

    public final <T> T parse(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        return (T) parse((Type) cls, false, customizeJsonParser);
    }

    public Object parse(Type type, boolean z) throws IOException {
        return parse(type, z, (CustomizeJsonParser) null);
    }

    public Object parse(Type type, boolean z, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                startParsing();
            }
            return parseValue(null, type, new ArrayList<>(), null, customizeJsonParser, true);
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final void parse(Object obj) throws IOException {
        parse(obj, (CustomizeJsonParser) null);
    }

    public final void parse(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        ArrayList<Type> arrayList = new ArrayList<>();
        arrayList.add(obj.getClass());
        parse(arrayList, obj, customizeJsonParser);
    }

    private void parse(ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        if (obj instanceof GenericJson) {
            ((GenericJson) obj).setFactory(getFactory());
        }
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        Class<?> cls = obj.getClass();
        ClassInfo m572of = ClassInfo.m572of(cls);
        boolean isAssignableFrom = GenericData.class.isAssignableFrom(cls);
        if (!isAssignableFrom && Map.class.isAssignableFrom(cls)) {
            parseMap(null, (Map) obj, Types.getMapValueParameter(cls), arrayList, customizeJsonParser);
            return;
        }
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(obj, text)) {
                return;
            }
            FieldInfo fieldInfo = m572of.getFieldInfo(text);
            if (fieldInfo != null) {
                if (fieldInfo.isFinal() && !fieldInfo.isPrimitive()) {
                    throw new IllegalArgumentException("final array/object fields are not supported");
                }
                Field field = fieldInfo.getField();
                int size = arrayList.size();
                arrayList.add(field.getGenericType());
                Object parseValue = parseValue(field, fieldInfo.getGenericType(), arrayList, obj, customizeJsonParser, true);
                arrayList.remove(size);
                fieldInfo.setValue(obj, parseValue);
            } else if (isAssignableFrom) {
                ((GenericData) obj).set(text, parseValue(null, null, arrayList, obj, customizeJsonParser, true));
            } else {
                if (customizeJsonParser != null) {
                    customizeJsonParser.handleUnrecognizedKey(obj, text);
                }
                skipChildren();
            }
            startParsingObjectOrArray = nextToken();
        }
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArrayAndClose(cls, cls2, (CustomizeJsonParser) null);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return parseArray(cls, cls2, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArrayAndClose(collection, cls, (CustomizeJsonParser) null);
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parseArray(collection, cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArray(cls, cls2, (CustomizeJsonParser) null);
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        Collection<T> collection = (Collection<T>) Data.newCollectionInstance(cls);
        parseArray(collection, cls2, customizeJsonParser);
        return collection;
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArray(collection, cls, (CustomizeJsonParser) null);
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        parseArray(null, collection, cls, new ArrayList<>(), customizeJsonParser);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void parseArray(Field field, Collection<T> collection, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray != JsonToken.END_ARRAY) {
            collection.add(parseValue(field, type, arrayList, collection, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    private void parseMap(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(map, text)) {
                return;
            }
            map.put(text, parseValue(field, type, arrayList, map, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x01a9 A[Catch: IllegalArgumentException -> 0x0313, TryCatch #0 {IllegalArgumentException -> 0x0313, blocks: (B:14:0x0025, B:15:0x002f, B:16:0x0032, B:17:0x02fe, B:18:0x0312, B:20:0x0038, B:22:0x003f, B:24:0x0046, B:26:0x004e, B:28:0x0056, B:30:0x0063, B:32:0x006b, B:34:0x0078, B:37:0x0081, B:41:0x0097, B:46:0x00b9, B:49:0x00c3, B:51:0x00cc, B:52:0x00d1, B:55:0x009f, B:57:0x00a7, B:59:0x00af, B:62:0x00dc, B:64:0x00e5, B:66:0x00ec, B:71:0x00fa, B:75:0x0103, B:80:0x010d, B:85:0x0116, B:90:0x011f, B:95:0x0128, B:100:0x0131, B:103:0x0136, B:104:0x014c, B:105:0x014d, B:107:0x0156, B:109:0x015f, B:111:0x0168, B:113:0x0171, B:115:0x017a, B:117:0x0183, B:121:0x018a, B:124:0x0190, B:128:0x019c, B:130:0x01a9, B:132:0x01ac, B:135:0x01af, B:139:0x01b9, B:143:0x01c5, B:146:0x01d2, B:148:0x01da, B:150:0x01e0, B:151:0x01f3, B:153:0x0202, B:157:0x01e7, B:159:0x01ef, B:162:0x020c, B:165:0x0215, B:167:0x0220, B:170:0x022a, B:172:0x0232, B:176:0x023f, B:177:0x0255, B:179:0x025b, B:181:0x0260, B:183:0x0268, B:185:0x0270, B:187:0x0279, B:190:0x0285, B:192:0x028a, B:195:0x0290, B:198:0x02a0, B:200:0x02b9, B:204:0x02c5, B:202:0x02ca, B:208:0x02d1, B:216:0x024c, B:217:0x0251), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01ac A[Catch: IllegalArgumentException -> 0x0313, TryCatch #0 {IllegalArgumentException -> 0x0313, blocks: (B:14:0x0025, B:15:0x002f, B:16:0x0032, B:17:0x02fe, B:18:0x0312, B:20:0x0038, B:22:0x003f, B:24:0x0046, B:26:0x004e, B:28:0x0056, B:30:0x0063, B:32:0x006b, B:34:0x0078, B:37:0x0081, B:41:0x0097, B:46:0x00b9, B:49:0x00c3, B:51:0x00cc, B:52:0x00d1, B:55:0x009f, B:57:0x00a7, B:59:0x00af, B:62:0x00dc, B:64:0x00e5, B:66:0x00ec, B:71:0x00fa, B:75:0x0103, B:80:0x010d, B:85:0x0116, B:90:0x011f, B:95:0x0128, B:100:0x0131, B:103:0x0136, B:104:0x014c, B:105:0x014d, B:107:0x0156, B:109:0x015f, B:111:0x0168, B:113:0x0171, B:115:0x017a, B:117:0x0183, B:121:0x018a, B:124:0x0190, B:128:0x019c, B:130:0x01a9, B:132:0x01ac, B:135:0x01af, B:139:0x01b9, B:143:0x01c5, B:146:0x01d2, B:148:0x01da, B:150:0x01e0, B:151:0x01f3, B:153:0x0202, B:157:0x01e7, B:159:0x01ef, B:162:0x020c, B:165:0x0215, B:167:0x0220, B:170:0x022a, B:172:0x0232, B:176:0x023f, B:177:0x0255, B:179:0x025b, B:181:0x0260, B:183:0x0268, B:185:0x0270, B:187:0x0279, B:190:0x0285, B:192:0x028a, B:195:0x0290, B:198:0x02a0, B:200:0x02b9, B:204:0x02c5, B:202:0x02ca, B:208:0x02d1, B:216:0x024c, B:217:0x0251), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01da A[Catch: IllegalArgumentException -> 0x0313, TryCatch #0 {IllegalArgumentException -> 0x0313, blocks: (B:14:0x0025, B:15:0x002f, B:16:0x0032, B:17:0x02fe, B:18:0x0312, B:20:0x0038, B:22:0x003f, B:24:0x0046, B:26:0x004e, B:28:0x0056, B:30:0x0063, B:32:0x006b, B:34:0x0078, B:37:0x0081, B:41:0x0097, B:46:0x00b9, B:49:0x00c3, B:51:0x00cc, B:52:0x00d1, B:55:0x009f, B:57:0x00a7, B:59:0x00af, B:62:0x00dc, B:64:0x00e5, B:66:0x00ec, B:71:0x00fa, B:75:0x0103, B:80:0x010d, B:85:0x0116, B:90:0x011f, B:95:0x0128, B:100:0x0131, B:103:0x0136, B:104:0x014c, B:105:0x014d, B:107:0x0156, B:109:0x015f, B:111:0x0168, B:113:0x0171, B:115:0x017a, B:117:0x0183, B:121:0x018a, B:124:0x0190, B:128:0x019c, B:130:0x01a9, B:132:0x01ac, B:135:0x01af, B:139:0x01b9, B:143:0x01c5, B:146:0x01d2, B:148:0x01da, B:150:0x01e0, B:151:0x01f3, B:153:0x0202, B:157:0x01e7, B:159:0x01ef, B:162:0x020c, B:165:0x0215, B:167:0x0220, B:170:0x022a, B:172:0x0232, B:176:0x023f, B:177:0x0255, B:179:0x025b, B:181:0x0260, B:183:0x0268, B:185:0x0270, B:187:0x0279, B:190:0x0285, B:192:0x028a, B:195:0x0290, B:198:0x02a0, B:200:0x02b9, B:204:0x02c5, B:202:0x02ca, B:208:0x02d1, B:216:0x024c, B:217:0x0251), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e0 A[Catch: IllegalArgumentException -> 0x0313, TryCatch #0 {IllegalArgumentException -> 0x0313, blocks: (B:14:0x0025, B:15:0x002f, B:16:0x0032, B:17:0x02fe, B:18:0x0312, B:20:0x0038, B:22:0x003f, B:24:0x0046, B:26:0x004e, B:28:0x0056, B:30:0x0063, B:32:0x006b, B:34:0x0078, B:37:0x0081, B:41:0x0097, B:46:0x00b9, B:49:0x00c3, B:51:0x00cc, B:52:0x00d1, B:55:0x009f, B:57:0x00a7, B:59:0x00af, B:62:0x00dc, B:64:0x00e5, B:66:0x00ec, B:71:0x00fa, B:75:0x0103, B:80:0x010d, B:85:0x0116, B:90:0x011f, B:95:0x0128, B:100:0x0131, B:103:0x0136, B:104:0x014c, B:105:0x014d, B:107:0x0156, B:109:0x015f, B:111:0x0168, B:113:0x0171, B:115:0x017a, B:117:0x0183, B:121:0x018a, B:124:0x0190, B:128:0x019c, B:130:0x01a9, B:132:0x01ac, B:135:0x01af, B:139:0x01b9, B:143:0x01c5, B:146:0x01d2, B:148:0x01da, B:150:0x01e0, B:151:0x01f3, B:153:0x0202, B:157:0x01e7, B:159:0x01ef, B:162:0x020c, B:165:0x0215, B:167:0x0220, B:170:0x022a, B:172:0x0232, B:176:0x023f, B:177:0x0255, B:179:0x025b, B:181:0x0260, B:183:0x0268, B:185:0x0270, B:187:0x0279, B:190:0x0285, B:192:0x028a, B:195:0x0290, B:198:0x02a0, B:200:0x02b9, B:204:0x02c5, B:202:0x02ca, B:208:0x02d1, B:216:0x024c, B:217:0x0251), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0202 A[Catch: IllegalArgumentException -> 0x0313, TryCatch #0 {IllegalArgumentException -> 0x0313, blocks: (B:14:0x0025, B:15:0x002f, B:16:0x0032, B:17:0x02fe, B:18:0x0312, B:20:0x0038, B:22:0x003f, B:24:0x0046, B:26:0x004e, B:28:0x0056, B:30:0x0063, B:32:0x006b, B:34:0x0078, B:37:0x0081, B:41:0x0097, B:46:0x00b9, B:49:0x00c3, B:51:0x00cc, B:52:0x00d1, B:55:0x009f, B:57:0x00a7, B:59:0x00af, B:62:0x00dc, B:64:0x00e5, B:66:0x00ec, B:71:0x00fa, B:75:0x0103, B:80:0x010d, B:85:0x0116, B:90:0x011f, B:95:0x0128, B:100:0x0131, B:103:0x0136, B:104:0x014c, B:105:0x014d, B:107:0x0156, B:109:0x015f, B:111:0x0168, B:113:0x0171, B:115:0x017a, B:117:0x0183, B:121:0x018a, B:124:0x0190, B:128:0x019c, B:130:0x01a9, B:132:0x01ac, B:135:0x01af, B:139:0x01b9, B:143:0x01c5, B:146:0x01d2, B:148:0x01da, B:150:0x01e0, B:151:0x01f3, B:153:0x0202, B:157:0x01e7, B:159:0x01ef, B:162:0x020c, B:165:0x0215, B:167:0x0220, B:170:0x022a, B:172:0x0232, B:176:0x023f, B:177:0x0255, B:179:0x025b, B:181:0x0260, B:183:0x0268, B:185:0x0270, B:187:0x0279, B:190:0x0285, B:192:0x028a, B:195:0x0290, B:198:0x02a0, B:200:0x02b9, B:204:0x02c5, B:202:0x02ca, B:208:0x02d1, B:216:0x024c, B:217:0x0251), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x020b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Object parseValue(Field field, Type type, ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser, boolean z) throws IOException {
        boolean z2;
        Collection<Object> newInstanceForArray;
        boolean z3;
        Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(arrayList, type);
        Type type2 = null;
        Class<?> cls = resolveWildcardTypeOrTypeVariable instanceof Class ? (Class) resolveWildcardTypeOrTypeVariable : null;
        if (resolveWildcardTypeOrTypeVariable instanceof ParameterizedType) {
            cls = Types.getRawClass((ParameterizedType) resolveWildcardTypeOrTypeVariable);
        }
        if (cls == Void.class) {
            skipChildren();
            return null;
        }
        JsonToken currentToken = getCurrentToken();
        try {
            switch (currentToken) {
                case START_OBJECT:
                case FIELD_NAME:
                case END_OBJECT:
                    Preconditions.checkArgument(!Types.isArray(resolveWildcardTypeOrTypeVariable), "expected object or map type but got %s", resolveWildcardTypeOrTypeVariable);
                    Field cachedTypemapFieldFor = z ? getCachedTypemapFieldFor(cls) : null;
                    Object newInstanceForObject = (cls == null || customizeJsonParser == null) ? null : customizeJsonParser.newInstanceForObject(obj, cls);
                    boolean z4 = cls != null && Types.isAssignableToOrFrom(cls, Map.class);
                    if (cachedTypemapFieldFor != null) {
                        newInstanceForObject = new GenericJson();
                    } else if (newInstanceForObject == null) {
                        if (!z4 && cls != null) {
                            newInstanceForObject = Types.newInstance(cls);
                        }
                        newInstanceForObject = Data.newMapInstance(cls);
                    }
                    int size = arrayList.size();
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.add(resolveWildcardTypeOrTypeVariable);
                    }
                    if (z4 && !GenericData.class.isAssignableFrom(cls)) {
                        Type mapValueParameter = Map.class.isAssignableFrom(cls) ? Types.getMapValueParameter(resolveWildcardTypeOrTypeVariable) : null;
                        if (mapValueParameter != null) {
                            parseMap(field, (Map) newInstanceForObject, mapValueParameter, arrayList, customizeJsonParser);
                            return newInstanceForObject;
                        }
                    }
                    parse(arrayList, newInstanceForObject, customizeJsonParser);
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.remove(size);
                    }
                    if (cachedTypemapFieldFor == null) {
                        return newInstanceForObject;
                    }
                    Object obj2 = ((GenericJson) newInstanceForObject).get(cachedTypemapFieldFor.getName());
                    Preconditions.checkArgument(obj2 != null, "No value specified for @JsonPolymorphicTypeMap field");
                    String obj3 = obj2.toString();
                    JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = ((JsonPolymorphicTypeMap) cachedTypemapFieldFor.getAnnotation(JsonPolymorphicTypeMap.class)).typeDefinitions();
                    int length = typeDefinitions.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            JsonPolymorphicTypeMap.TypeDef typeDef = typeDefinitions[i];
                            if (typeDef.key().equals(obj3)) {
                                type2 = typeDef.ref();
                            } else {
                                i++;
                            }
                        }
                    }
                    Type type3 = type2;
                    Preconditions.checkArgument(type3 != null, "No TypeDef annotation found with key: " + obj3);
                    JsonFactory factory = getFactory();
                    JsonParser createJsonParser = factory.createJsonParser(factory.toString(newInstanceForObject));
                    createJsonParser.startParsing();
                    return createJsonParser.parseValue(field, type3, arrayList, null, null, false);
                case START_ARRAY:
                case END_ARRAY:
                    boolean isArray = Types.isArray(resolveWildcardTypeOrTypeVariable);
                    if (resolveWildcardTypeOrTypeVariable != null && !isArray && (cls == null || !Types.isAssignableToOrFrom(cls, Collection.class))) {
                        z2 = false;
                        Preconditions.checkArgument(z2, "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                        newInstanceForArray = (customizeJsonParser != null || field == null) ? null : customizeJsonParser.newInstanceForArray(obj, field);
                        if (newInstanceForArray == null) {
                            newInstanceForArray = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                        }
                        if (!isArray) {
                            type2 = Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable);
                        } else if (cls != null && Iterable.class.isAssignableFrom(cls)) {
                            type2 = Types.getIterableParameter(resolveWildcardTypeOrTypeVariable);
                        }
                        Type resolveWildcardTypeOrTypeVariable2 = Data.resolveWildcardTypeOrTypeVariable(arrayList, type2);
                        parseArray(field, newInstanceForArray, resolveWildcardTypeOrTypeVariable2, arrayList, customizeJsonParser);
                        return !isArray ? Types.toArray(newInstanceForArray, Types.getRawArrayComponentType(arrayList, resolveWildcardTypeOrTypeVariable2)) : newInstanceForArray;
                    }
                    z2 = true;
                    Preconditions.checkArgument(z2, "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                    if (customizeJsonParser != null) {
                    }
                    if (newInstanceForArray == null) {
                    }
                    if (!isArray) {
                    }
                    Type resolveWildcardTypeOrTypeVariable22 = Data.resolveWildcardTypeOrTypeVariable(arrayList, type2);
                    parseArray(field, newInstanceForArray, resolveWildcardTypeOrTypeVariable22, arrayList, customizeJsonParser);
                    if (!isArray) {
                    }
                    break;
                case VALUE_TRUE:
                case VALUE_FALSE:
                    if (resolveWildcardTypeOrTypeVariable != null && cls != Boolean.TYPE && (cls == null || !cls.isAssignableFrom(Boolean.class))) {
                        z3 = false;
                        Preconditions.checkArgument(z3, "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                        return currentToken != JsonToken.VALUE_TRUE ? Boolean.TRUE : Boolean.FALSE;
                    }
                    z3 = true;
                    Preconditions.checkArgument(z3, "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                    if (currentToken != JsonToken.VALUE_TRUE) {
                    }
                    break;
                case VALUE_NUMBER_FLOAT:
                case VALUE_NUMBER_INT:
                    Preconditions.checkArgument(field == null || field.getAnnotation(JsonString.class) == null, "number type formatted as a JSON number cannot use @JsonString annotation");
                    if (cls != null && !cls.isAssignableFrom(BigDecimal.class)) {
                        if (cls == BigInteger.class) {
                            return getBigIntegerValue();
                        }
                        if (cls != Double.class && cls != Double.TYPE) {
                            if (cls != Long.class && cls != Long.TYPE) {
                                if (cls != Float.class && cls != Float.TYPE) {
                                    if (cls != Integer.class && cls != Integer.TYPE) {
                                        if (cls != Short.class && cls != Short.TYPE) {
                                            if (cls != Byte.class && cls != Byte.TYPE) {
                                                throw new IllegalArgumentException("expected numeric type but got " + resolveWildcardTypeOrTypeVariable);
                                            }
                                            return Byte.valueOf(getByteValue());
                                        }
                                        return Short.valueOf(getShortValue());
                                    }
                                    return Integer.valueOf(getIntValue());
                                }
                                return Float.valueOf(getFloatValue());
                            }
                            return Long.valueOf(getLongValue());
                        }
                        return Double.valueOf(getDoubleValue());
                    }
                    return getDecimalValue();
                case VALUE_STRING:
                    String lowerCase = getText().trim().toLowerCase(Locale.US);
                    if ((cls != Float.TYPE && cls != Float.class && cls != Double.TYPE && cls != Double.class) || (!lowerCase.equals("nan") && !lowerCase.equals("infinity") && !lowerCase.equals("-infinity"))) {
                        if (cls == null || !Number.class.isAssignableFrom(cls) || (field != null && field.getAnnotation(JsonString.class) != null)) {
                            r4 = true;
                        }
                        Preconditions.checkArgument(r4, "number field formatted as a JSON string must use the @JsonString annotation");
                    }
                    return Data.parsePrimitiveValue(resolveWildcardTypeOrTypeVariable, getText());
                case VALUE_NULL:
                    Preconditions.checkArgument(cls == null || !cls.isPrimitive(), "primitive number field but found a JSON null");
                    if (cls != null && (cls.getModifiers() & 1536) != 0) {
                        if (Types.isAssignableToOrFrom(cls, Collection.class)) {
                            return Data.nullOf(Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable).getClass());
                        }
                        if (Types.isAssignableToOrFrom(cls, Map.class)) {
                            return Data.nullOf(Data.newMapInstance(cls).getClass());
                        }
                    }
                    return Data.nullOf(Types.getRawArrayComponentType(arrayList, resolveWildcardTypeOrTypeVariable));
                default:
                    throw new IllegalArgumentException("unexpected JSON node type: " + currentToken);
            }
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            String currentName = getCurrentName();
            if (currentName != null) {
                sb.append("key ");
                sb.append(currentName);
            }
            if (field != null) {
                if (currentName != null) {
                    sb.append(", ");
                }
                sb.append("field ");
                sb.append(field);
            }
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    private static Field getCachedTypemapFieldFor(Class<?> cls) {
        Field field = null;
        if (cls == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(cls)) {
                return cachedTypemapFields.get(cls);
            }
            Iterator<FieldInfo> it = ClassInfo.m572of(cls).getFieldInfos().iterator();
            while (it.hasNext()) {
                Field field2 = it.next().getField();
                JsonPolymorphicTypeMap jsonPolymorphicTypeMap = (JsonPolymorphicTypeMap) field2.getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    Preconditions.checkArgument(field == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", cls);
                    Preconditions.checkArgument(Data.isPrimitive(field2.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", cls, field2.getType());
                    JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    HashSet newHashSet = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefinitions.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (JsonPolymorphicTypeMap.TypeDef typeDef : typeDefinitions) {
                        Preconditions.checkArgument(newHashSet.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                    field = field2;
                }
            }
            cachedTypemapFields.put(cls, field);
            return field;
        } finally {
            lock.unlock();
        }
    }
}
