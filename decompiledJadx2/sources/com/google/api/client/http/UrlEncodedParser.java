package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class UrlEncodedParser implements ObjectParser {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded").setCharsetParameter(StandardCharsets.UTF_8).build();

    public static void parse(String str, Object obj) {
        parse(str, obj, true);
    }

    public static void parse(String str, Object obj, boolean z) {
        if (str == null) {
            return;
        }
        try {
            parse(new StringReader(str), obj, z);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static void parse(Reader reader, Object obj) throws IOException {
        parse(reader, obj, true);
    }

    public static void parse(Reader reader, Object obj, boolean z) throws IOException {
        int read;
        String stringWriter;
        Class<?> cls = obj.getClass();
        ClassInfo m572of = ClassInfo.m572of(cls);
        List asList = Arrays.asList(cls);
        GenericData genericData = GenericData.class.isAssignableFrom(cls) ? (GenericData) obj : null;
        Map map = Map.class.isAssignableFrom(cls) ? (Map) obj : null;
        ArrayValueMap arrayValueMap = new ArrayValueMap(obj);
        StringWriter stringWriter2 = new StringWriter();
        StringWriter stringWriter3 = new StringWriter();
        do {
            StringWriter stringWriter4 = stringWriter2;
            boolean z2 = true;
            while (true) {
                read = reader.read();
                if (read == -1 || read == 38) {
                    break;
                }
                if (read != 61) {
                    if (z2) {
                        stringWriter4.write(read);
                    } else {
                        stringWriter3.write(read);
                    }
                } else if (z2) {
                    z2 = false;
                } else {
                    stringWriter3.write(read);
                }
            }
            String decodeUri = z ? CharEscapers.decodeUri(stringWriter4.toString()) : stringWriter4.toString();
            if (decodeUri.length() != 0) {
                if (z) {
                    stringWriter = CharEscapers.decodeUri(stringWriter3.toString());
                } else {
                    stringWriter = stringWriter3.toString();
                }
                FieldInfo fieldInfo = m572of.getFieldInfo(decodeUri);
                if (fieldInfo != null) {
                    Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(asList, fieldInfo.getGenericType());
                    if (Types.isArray(resolveWildcardTypeOrTypeVariable)) {
                        Class<?> rawArrayComponentType = Types.getRawArrayComponentType(asList, Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable));
                        arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, asList, stringWriter));
                    } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(asList, resolveWildcardTypeOrTypeVariable), Iterable.class)) {
                        Collection<Object> collection = (Collection) fieldInfo.getValue(obj);
                        if (collection == null) {
                            collection = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                            fieldInfo.setValue(obj, collection);
                        }
                        collection.add(parseValue(resolveWildcardTypeOrTypeVariable == Object.class ? null : Types.getIterableParameter(resolveWildcardTypeOrTypeVariable), asList, stringWriter));
                    } else {
                        fieldInfo.setValue(obj, parseValue(resolveWildcardTypeOrTypeVariable, asList, stringWriter));
                    }
                } else if (map != null) {
                    ArrayList arrayList = (ArrayList) map.get(decodeUri);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        if (genericData != null) {
                            genericData.set(decodeUri, arrayList);
                        } else {
                            map.put(decodeUri, arrayList);
                        }
                    }
                    arrayList.add(stringWriter);
                }
            }
            stringWriter2 = new StringWriter();
            stringWriter3 = new StringWriter();
        } while (read != -1);
        arrayValueMap.setValues();
    }

    private static Object parseValue(Type type, List<Type> list, String str) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, type), str);
    }

    @Override // com.google.api.client.util.ObjectParser
    public <T> T parseAndClose(InputStream inputStream, Charset charset, Class<T> cls) throws IOException {
        return (T) parseAndClose((Reader) new InputStreamReader(inputStream, charset), (Class) cls);
    }

    @Override // com.google.api.client.util.ObjectParser
    public Object parseAndClose(InputStream inputStream, Charset charset, Type type) throws IOException {
        return parseAndClose(new InputStreamReader(inputStream, charset), type);
    }

    @Override // com.google.api.client.util.ObjectParser
    public <T> T parseAndClose(Reader reader, Class<T> cls) throws IOException {
        return (T) parseAndClose(reader, (Type) cls);
    }

    @Override // com.google.api.client.util.ObjectParser
    public Object parseAndClose(Reader reader, Type type) throws IOException {
        Preconditions.checkArgument(type instanceof Class, "dataType has to be of type Class<?>");
        Object newInstance = Types.newInstance((Class) type);
        parse(new BufferedReader(reader), newInstance);
        return newInstance;
    }
}
