package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        boolean z;
        JSONField jSONField;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        if (jSONType != null) {
            String typeName = jSONType.typeName();
            String typeKey = jSONType.typeKey();
            this.typeKey = typeKey.length() <= 0 ? null : typeKey;
            if (typeName.length() != 0) {
                this.typeName = typeName;
            } else {
                this.typeName = cls.getName();
            }
            String[] orders = jSONType.orders();
            this.orders = orders.length == 0 ? null : orders;
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            this.orders = null;
        }
        FieldInfo[] fieldInfoArr = new FieldInfo[list.size()];
        this.fields = fieldInfoArr;
        list.toArray(fieldInfoArr);
        FieldInfo[] fieldInfoArr2 = this.fields;
        FieldInfo[] fieldInfoArr3 = new FieldInfo[fieldInfoArr2.length];
        if (this.orders != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (FieldInfo fieldInfo : this.fields) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            int i = 0;
            for (String str : this.orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    fieldInfoArr3[i] = fieldInfo2;
                    linkedHashMap.remove(str);
                    i++;
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                fieldInfoArr3[i] = (FieldInfo) it.next();
                i++;
            }
        } else {
            System.arraycopy(fieldInfoArr2, 0, fieldInfoArr3, 0, fieldInfoArr2.length);
            Arrays.sort(fieldInfoArr3);
        }
        this.sortedFields = Arrays.equals(this.fields, fieldInfoArr3) ? this.fields : fieldInfoArr3;
        if (constructor != null) {
            this.defaultConstructorParameterSize = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.defaultConstructorParameterSize = method.getParameterTypes().length;
        } else {
            this.defaultConstructorParameterSize = 0;
        }
        if (constructor2 != null) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            this.creatorConstructorParameterTypes = parameterTypes;
            if (parameterTypes.length == this.fields.length) {
                int i2 = 0;
                while (true) {
                    Type[] typeArr = this.creatorConstructorParameterTypes;
                    if (i2 >= typeArr.length) {
                        z = true;
                        break;
                    } else if (typeArr[i2] != this.fields[i2].fieldClass) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            z = false;
            if (z) {
                return;
            }
            if (TypeUtils.isKotlin(cls)) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                for (int i3 = 0; i3 < this.creatorConstructorParameters.length && i3 < parameterAnnotations.length; i3++) {
                    Annotation[] annotationArr = parameterAnnotations[i3];
                    int length = annotationArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            jSONField = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i4];
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                        i4++;
                    }
                    if (jSONField != null) {
                        String name = jSONField.name();
                        if (name.length() > 0) {
                            this.creatorConstructorParameters[i3] = name;
                        }
                    }
                }
                return;
            }
            this.creatorConstructorParameters = ASMUtils.lookupParameterNames(constructor2);
        }
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo fieldInfo : list) {
            if (fieldInfo.name.equals(str)) {
                return fieldInfo;
            }
            Field field = fieldInfo.field;
            if (field != null && fieldInfo.getAnnotation() != null && field.getName().equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        for (int size = list.size() - 1; size >= 0; size--) {
            FieldInfo fieldInfo2 = list.get(size);
            if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
                    list.remove(size);
                } else {
                    if (fieldInfo2.compareTo(fieldInfo) >= 0) {
                        return false;
                    }
                    list.remove(size);
                }
                list.add(fieldInfo);
                return true;
            }
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean);
    }

    /* JADX WARN: Code restructure failed: missing block: B:264:0x08b3, code lost:
    
        if (r1.deserialize() == false) goto L390;
     */
    /* JADX WARN: Removed duplicated region for block: B:121:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0721  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0798  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x07d2 A[EDGE_INSN: B:216:0x07d2->B:217:0x07d2 BREAK  A[LOOP:4: B:119:0x053b->B:125:0x07c2], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x07eb  */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v25, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v30 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2) {
        Constructor<?> defaultConstructor;
        Constructor<?> constructor;
        Method[] methodArr;
        PropertyNamingStrategy propertyNamingStrategy2;
        ArrayList arrayList;
        Constructor<?> constructor2;
        Field[] fieldArr;
        JSONType jSONType;
        Method[] methodArr2;
        Class<?> cls2;
        int i;
        Method[] methodArr3;
        int length;
        int i2;
        int i3;
        int length2;
        int i4;
        JSONField jSONField;
        String str;
        Field[] fieldArr2;
        Field[] fieldArr3;
        int i5;
        int i6;
        int i7;
        Method[] methodArr4;
        int i8;
        int i9;
        int i10;
        Method[] methodArr5;
        Class<?> cls3;
        String decapitalize;
        Field[] fieldArr4;
        Field field;
        boolean z3;
        int i11;
        int i12;
        String str2;
        Field[] fieldArr5;
        int i13;
        JSONField jSONField2;
        int i14;
        PropertyNamingStrategy propertyNamingStrategy3;
        int i15;
        int i16;
        Method[] methodArr6;
        String str3;
        Field[] fieldArr6;
        JSONType jSONType2;
        Method[] methodArr7;
        Class<?> cls4;
        int i17;
        int i18;
        int i19;
        String str4;
        StringBuilder sb;
        String str5;
        int i20;
        String[] strArr;
        Constructor<?> constructor3;
        ?? r7;
        String[] strArr2;
        String[] lookupParameterNames;
        JSONField jSONField3;
        int m84of;
        int i21;
        int i22;
        JSONField jSONField4;
        JSONField jSONField5;
        PropertyNamingStrategy naming;
        JSONType jSONType3 = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        PropertyNamingStrategy propertyNamingStrategy4 = (jSONType3 == null || (naming = jSONType3.naming()) == null || naming == PropertyNamingStrategy.CamelCase) ? propertyNamingStrategy : naming;
        Class<?> builderClass = getBuilderClass(cls, jSONType3);
        Field[] declaredFields = cls.getDeclaredFields();
        Method[] methods = cls.getMethods();
        boolean isKotlin = TypeUtils.isKotlin(cls);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (!isKotlin || declaredConstructors.length == 1) {
            if (builderClass == null) {
                defaultConstructor = getDefaultConstructor(cls, declaredConstructors);
            } else {
                defaultConstructor = getDefaultConstructor(builderClass, builderClass.getDeclaredConstructors());
            }
            constructor = defaultConstructor;
        } else {
            constructor = null;
        }
        Method method = null;
        Method method2 = null;
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            for (Class<?> cls5 = cls; cls5 != null; cls5 = cls5.getSuperclass()) {
                computeFields(cls, type, propertyNamingStrategy4, arrayList2, cls5.getDeclaredFields());
            }
            return new JavaBeanInfo(cls, builderClass, constructor, null, null, null, jSONType3, arrayList2);
        }
        boolean z4 = cls.isInterface() || Modifier.isAbstract(cls.getModifiers());
        if ((constructor == null && builderClass == null) || z4) {
            constructor2 = getCreatorConstructor(declaredConstructors);
            String str6 = "illegal json creator";
            if (constructor2 != null && !z4) {
                TypeUtils.setAccessible(constructor2);
                Class<?>[] parameterTypes = constructor2.getParameterTypes();
                if (parameterTypes.length > 0) {
                    Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                    int i23 = 0;
                    while (i23 < parameterTypes.length) {
                        Annotation[] annotationArr = parameterAnnotations[i23];
                        int length3 = annotationArr.length;
                        int i24 = 0;
                        while (true) {
                            if (i24 >= length3) {
                                jSONField5 = null;
                                break;
                            }
                            Annotation annotation = annotationArr[i24];
                            if (annotation instanceof JSONField) {
                                jSONField5 = (JSONField) annotation;
                                break;
                            }
                            i24++;
                        }
                        if (jSONField5 == null) {
                            throw new JSONException(str6);
                        }
                        PropertyNamingStrategy propertyNamingStrategy5 = propertyNamingStrategy4;
                        ArrayList arrayList3 = arrayList2;
                        add(arrayList3, new FieldInfo(jSONField5.name(), cls, parameterTypes[i23], constructor2.getGenericParameterTypes()[i23], TypeUtils.getField(cls, jSONField5.name(), declaredFields), jSONField5.ordinal(), SerializerFeature.m85of(jSONField5.serialzeFeatures()), Feature.m84of(jSONField5.parseFeatures())));
                        i23++;
                        str6 = str6;
                        arrayList2 = arrayList3;
                        propertyNamingStrategy4 = propertyNamingStrategy5;
                        parameterTypes = parameterTypes;
                    }
                }
                propertyNamingStrategy2 = propertyNamingStrategy4;
                arrayList = arrayList2;
            } else {
                propertyNamingStrategy2 = propertyNamingStrategy4;
                arrayList = arrayList2;
                method2 = getFactoryMethod(cls, methods);
                if (method2 != null) {
                    TypeUtils.setAccessible(method2);
                    Class<?>[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length > 0) {
                        Annotation[][] parameterAnnotations2 = method2.getParameterAnnotations();
                        int i25 = 0;
                        while (i25 < parameterTypes2.length) {
                            Annotation[] annotationArr2 = parameterAnnotations2[i25];
                            int length4 = annotationArr2.length;
                            int i26 = 0;
                            while (true) {
                                if (i26 >= length4) {
                                    jSONField4 = null;
                                    break;
                                }
                                Annotation annotation2 = annotationArr2[i26];
                                if (annotation2 instanceof JSONField) {
                                    jSONField4 = (JSONField) annotation2;
                                    break;
                                }
                                i26++;
                            }
                            if (jSONField4 == null) {
                                throw new JSONException("illegal json creator");
                            }
                            add(arrayList, new FieldInfo(jSONField4.name(), cls, parameterTypes2[i25], method2.getGenericParameterTypes()[i25], TypeUtils.getField(cls, jSONField4.name(), declaredFields), jSONField4.ordinal(), SerializerFeature.m85of(jSONField4.serialzeFeatures()), Feature.m84of(jSONField4.parseFeatures())));
                            i25++;
                            parameterAnnotations2 = parameterAnnotations2;
                            parameterTypes2 = parameterTypes2;
                        }
                        return new JavaBeanInfo(cls, builderClass, null, null, method2, null, jSONType3, arrayList);
                    }
                } else if (!z4) {
                    String name = cls.getName();
                    if (isKotlin && declaredConstructors.length > 0) {
                        String[] koltinConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                        Constructor<?> koltinConstructor = TypeUtils.getKoltinConstructor(declaredConstructors);
                        TypeUtils.setAccessible(koltinConstructor);
                        constructor2 = koltinConstructor;
                        strArr = koltinConstructorParameters;
                    } else {
                        int length5 = declaredConstructors.length;
                        String[] strArr3 = null;
                        int i27 = 0;
                        while (true) {
                            if (i27 >= length5) {
                                i20 = 0;
                                strArr = strArr3;
                                break;
                            }
                            constructor3 = declaredConstructors[i27];
                            Class<?>[] parameterTypes3 = constructor3.getParameterTypes();
                            if (name.equals("org.springframework.security.web.authentication.WebAuthenticationDetails") && parameterTypes3.length == 2 && parameterTypes3[0] == String.class) {
                                r7 = 1;
                                if (parameterTypes3[1] == String.class) {
                                    constructor3.setAccessible(true);
                                    strArr2 = ASMUtils.lookupParameterNames(constructor3);
                                    break;
                                }
                            } else {
                                r7 = 1;
                            }
                            if (name.equals("org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken") && parameterTypes3.length == 3 && parameterTypes3[0] == Object.class && parameterTypes3[r7] == Object.class && parameterTypes3[2] == Collection.class) {
                                constructor3.setAccessible(r7);
                                strArr2 = new String[]{"principal", "credentials", "authorities"};
                                break;
                            }
                            if (name.equals("org.springframework.security.core.authority.SimpleGrantedAuthority") && parameterTypes3.length == r7) {
                                i20 = 0;
                                if (parameterTypes3[0] == String.class) {
                                    strArr = new String[]{"authority"};
                                    constructor2 = constructor3;
                                    break;
                                }
                            } else {
                                i20 = 0;
                            }
                            if (((constructor3.getModifiers() & r7) != 0 ? r7 : i20) != 0 && (lookupParameterNames = ASMUtils.lookupParameterNames(constructor3)) != null && lookupParameterNames.length != 0 && (constructor2 == null || strArr3 == null || lookupParameterNames.length > strArr3.length)) {
                                constructor2 = constructor3;
                                strArr3 = lookupParameterNames;
                            }
                            i27++;
                        }
                        strArr = strArr2;
                        constructor2 = constructor3;
                    }
                    i20 = 0;
                    Class<?>[] parameterTypes4 = strArr != null ? constructor2.getParameterTypes() : null;
                    if (strArr != null && parameterTypes4.length == strArr.length) {
                        Annotation[][] parameterAnnotations3 = constructor2.getParameterAnnotations();
                        int i28 = i20;
                        while (i28 < parameterTypes4.length) {
                            Annotation[] annotationArr3 = parameterAnnotations3[i28];
                            String str7 = strArr[i28];
                            int length6 = annotationArr3.length;
                            int i29 = i20;
                            while (true) {
                                if (i29 >= length6) {
                                    jSONField3 = null;
                                    break;
                                }
                                Annotation annotation3 = annotationArr3[i29];
                                if (annotation3 instanceof JSONField) {
                                    jSONField3 = (JSONField) annotation3;
                                    break;
                                }
                                i29++;
                            }
                            Class<?> cls6 = parameterTypes4[i28];
                            Type type2 = constructor2.getGenericParameterTypes()[i28];
                            Field field2 = TypeUtils.getField(cls, str7, declaredFields);
                            if (field2 != null && jSONField3 == null) {
                                jSONField3 = (JSONField) field2.getAnnotation(JSONField.class);
                            }
                            if (jSONField3 == null) {
                                if ("org.springframework.security.core.userdetails.User".equals(name) && CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD.equals(str7)) {
                                    m84of = Feature.InitStringFieldAsEmpty.mask;
                                    i22 = 0;
                                    i21 = 0;
                                } else {
                                    i22 = 0;
                                    i21 = 0;
                                    m84of = 0;
                                }
                            } else {
                                String name2 = jSONField3.name();
                                if (name2.length() != 0) {
                                    str7 = name2;
                                }
                                int ordinal = jSONField3.ordinal();
                                int m85of = SerializerFeature.m85of(jSONField3.serialzeFeatures());
                                m84of = Feature.m84of(jSONField3.parseFeatures());
                                i21 = m85of;
                                i22 = ordinal;
                            }
                            add(arrayList, new FieldInfo(str7, cls, cls6, type2, field2, i22, i21, m84of));
                            i28++;
                            strArr = strArr;
                            parameterTypes4 = parameterTypes4;
                            methods = methods;
                            i20 = 0;
                        }
                        methodArr = methods;
                        if (!isKotlin && !cls.getName().equals("javax.servlet.http.Cookie")) {
                            return new JavaBeanInfo(cls, builderClass, null, constructor2, null, null, jSONType3, arrayList);
                        }
                    } else {
                        throw new JSONException("default constructor not found. " + cls);
                    }
                }
            }
            methodArr = methods;
        } else {
            methodArr = methods;
            propertyNamingStrategy2 = propertyNamingStrategy4;
            arrayList = arrayList2;
            constructor2 = null;
        }
        if (constructor != null) {
            TypeUtils.setAccessible(constructor);
        }
        String str8 = TmpConstant.PROPERTY_IDENTIFIER_SET;
        if (builderClass != null) {
            JSONPOJOBuilder jSONPOJOBuilder = (JSONPOJOBuilder) builderClass.getAnnotation(JSONPOJOBuilder.class);
            String withPrefix = jSONPOJOBuilder != null ? jSONPOJOBuilder.withPrefix() : null;
            if (withPrefix == null || withPrefix.length() == 0) {
                withPrefix = JsonPOJOBuilder.DEFAULT_WITH_PREFIX;
            }
            String str9 = withPrefix;
            Method[] methods2 = builderClass.getMethods();
            int length7 = methods2.length;
            int i30 = 0;
            while (i30 < length7) {
                Method method3 = methods2[i30];
                if (!Modifier.isStatic(method3.getModifiers()) && method3.getReturnType().equals(builderClass)) {
                    JSONField jSONField6 = (JSONField) method3.getAnnotation(JSONField.class);
                    if (jSONField6 == null) {
                        jSONField6 = TypeUtils.getSuperMethodAnnotation(cls, method3);
                    }
                    JSONField jSONField7 = jSONField6;
                    if (jSONField7 == null) {
                        i15 = i30;
                        i16 = length7;
                        methodArr6 = methods2;
                        str3 = str9;
                        fieldArr6 = declaredFields;
                        jSONType2 = jSONType3;
                        methodArr7 = methodArr;
                        cls4 = builderClass;
                        i17 = 0;
                        i18 = 0;
                        i19 = 0;
                    } else if (jSONField7.deserialize()) {
                        int ordinal2 = jSONField7.ordinal();
                        int m85of2 = SerializerFeature.m85of(jSONField7.serialzeFeatures());
                        int m84of2 = Feature.m84of(jSONField7.parseFeatures());
                        if (jSONField7.name().length() != 0) {
                            i15 = i30;
                            i16 = length7;
                            methodArr6 = methods2;
                            methodArr7 = methodArr;
                            fieldArr6 = declaredFields;
                            jSONType2 = jSONType3;
                            cls4 = builderClass;
                            add(arrayList, new FieldInfo(jSONField7.name(), method3, null, cls, type, ordinal2, m85of2, m84of2, jSONField7, null, null));
                            str5 = str9;
                            i30 = i15 + 1;
                            builderClass = cls4;
                            str9 = str5;
                            length7 = i16;
                            methods2 = methodArr6;
                            methodArr = methodArr7;
                            declaredFields = fieldArr6;
                            jSONType3 = jSONType2;
                        } else {
                            i15 = i30;
                            i16 = length7;
                            methodArr6 = methods2;
                            str3 = str9;
                            fieldArr6 = declaredFields;
                            jSONType2 = jSONType3;
                            methodArr7 = methodArr;
                            cls4 = builderClass;
                            i17 = ordinal2;
                            i18 = m85of2;
                            i19 = m84of2;
                        }
                    }
                    String name3 = method3.getName();
                    if (name3.startsWith(TmpConstant.PROPERTY_IDENTIFIER_SET) && name3.length() > 3) {
                        sb = new StringBuilder(name3.substring(3));
                        str4 = str3;
                    } else {
                        str4 = str3;
                        if (name3.startsWith(str4) && name3.length() > str4.length()) {
                            sb = new StringBuilder(name3.substring(str4.length()));
                        }
                        str5 = str4;
                        i30 = i15 + 1;
                        builderClass = cls4;
                        str9 = str5;
                        length7 = i16;
                        methods2 = methodArr6;
                        methodArr = methodArr7;
                        declaredFields = fieldArr6;
                        jSONType3 = jSONType2;
                    }
                    char charAt = sb.charAt(0);
                    if (Character.isUpperCase(charAt)) {
                        sb.setCharAt(0, Character.toLowerCase(charAt));
                        str5 = str4;
                        add(arrayList, new FieldInfo(sb.toString(), method3, null, cls, type, i17, i18, i19, jSONField7, null, null));
                        i30 = i15 + 1;
                        builderClass = cls4;
                        str9 = str5;
                        length7 = i16;
                        methods2 = methodArr6;
                        methodArr = methodArr7;
                        declaredFields = fieldArr6;
                        jSONType3 = jSONType2;
                    }
                    str5 = str4;
                    i30 = i15 + 1;
                    builderClass = cls4;
                    str9 = str5;
                    length7 = i16;
                    methods2 = methodArr6;
                    methodArr = methodArr7;
                    declaredFields = fieldArr6;
                    jSONType3 = jSONType2;
                }
                i15 = i30;
                i16 = length7;
                methodArr6 = methods2;
                str5 = str9;
                fieldArr6 = declaredFields;
                jSONType2 = jSONType3;
                methodArr7 = methodArr;
                cls4 = builderClass;
                i30 = i15 + 1;
                builderClass = cls4;
                str9 = str5;
                length7 = i16;
                methods2 = methodArr6;
                methodArr = methodArr7;
                declaredFields = fieldArr6;
                jSONType3 = jSONType2;
            }
            fieldArr = declaredFields;
            jSONType = jSONType3;
            methodArr2 = methodArr;
            cls2 = builderClass;
            if (cls2 != null) {
                JSONPOJOBuilder jSONPOJOBuilder2 = (JSONPOJOBuilder) cls2.getAnnotation(JSONPOJOBuilder.class);
                String buildMethod = jSONPOJOBuilder2 != null ? jSONPOJOBuilder2.buildMethod() : null;
                if (buildMethod == null || buildMethod.length() == 0) {
                    buildMethod = JsonPOJOBuilder.DEFAULT_BUILD_METHOD;
                }
                i = 0;
                try {
                    method = cls2.getMethod(buildMethod, new Class[0]);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
                if (method == null) {
                    try {
                        method = cls2.getMethod("create", new Class[0]);
                    } catch (NoSuchMethodException | SecurityException unused2) {
                    }
                }
                if (method == null) {
                    throw new JSONException("buildMethod not found.");
                }
                TypeUtils.setAccessible(method);
                methodArr3 = methodArr2;
                length = methodArr3.length;
                i2 = i;
                while (true) {
                    i3 = 4;
                    if (i2 < length) {
                        break;
                    }
                    Method method4 = methodArr3[i2];
                    int i31 = 0;
                    int i32 = 0;
                    int i33 = 0;
                    String name4 = method4.getName();
                    if (!Modifier.isStatic(method4.getModifiers())) {
                        Class<?> returnType = method4.getReturnType();
                        if ((returnType.equals(Void.TYPE) || returnType.equals(method4.getDeclaringClass())) && method4.getDeclaringClass() != Object.class) {
                            Class<?>[] parameterTypes5 = method4.getParameterTypes();
                            if (parameterTypes5.length != 0) {
                                if (parameterTypes5.length > 2) {
                                    i9 = i2;
                                    i10 = length;
                                    methodArr5 = methodArr3;
                                    i12 = i;
                                    str2 = str8;
                                    cls3 = cls2;
                                } else {
                                    JSONField jSONField8 = (JSONField) method4.getAnnotation(JSONField.class);
                                    if (jSONField8 != null && parameterTypes5.length == 2 && parameterTypes5[i] == String.class && parameterTypes5[1] == Object.class) {
                                        i9 = i2;
                                        i10 = length;
                                        methodArr5 = methodArr3;
                                        cls3 = cls2;
                                        add(arrayList, new FieldInfo("", method4, null, cls, type, 0, 0, 0, jSONField8, null, null));
                                        str2 = str8;
                                        i12 = i;
                                    } else {
                                        i9 = i2;
                                        i10 = length;
                                        methodArr5 = methodArr3;
                                        cls3 = cls2;
                                        int i34 = i;
                                        if (parameterTypes5.length == 1) {
                                            JSONField superMethodAnnotation = jSONField8 == null ? TypeUtils.getSuperMethodAnnotation(cls, method4) : jSONField8;
                                            if (superMethodAnnotation != null || name4.length() >= 4) {
                                                if (superMethodAnnotation != null) {
                                                    if (superMethodAnnotation.deserialize()) {
                                                        i31 = superMethodAnnotation.ordinal();
                                                        int m85of3 = SerializerFeature.m85of(superMethodAnnotation.serialzeFeatures());
                                                        int m84of3 = Feature.m84of(superMethodAnnotation.parseFeatures());
                                                        if (superMethodAnnotation.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(superMethodAnnotation.name(), method4, null, cls, type, i31, m85of3, m84of3, superMethodAnnotation, null, null));
                                                            str2 = str8;
                                                            propertyNamingStrategy3 = propertyNamingStrategy2;
                                                            i12 = 0;
                                                            i2 = i9 + 1;
                                                            propertyNamingStrategy2 = propertyNamingStrategy3;
                                                            i = i12;
                                                            length = i10;
                                                            methodArr3 = methodArr5;
                                                            str8 = str2;
                                                            cls2 = cls3;
                                                        } else {
                                                            i32 = m85of3;
                                                            i33 = m84of3;
                                                        }
                                                    }
                                                }
                                                if (superMethodAnnotation != null || name4.startsWith(str8)) {
                                                    char charAt2 = name4.charAt(3);
                                                    if (Character.isUpperCase(charAt2) || charAt2 > 512) {
                                                        decapitalize = TypeUtils.compatibleWithJavaBean ? TypeUtils.decapitalize(name4.substring(3)) : Character.toLowerCase(name4.charAt(3)) + name4.substring(4);
                                                    } else if (charAt2 == '_') {
                                                        decapitalize = name4.substring(4);
                                                        fieldArr4 = fieldArr;
                                                        field = TypeUtils.getField(cls, decapitalize, fieldArr4);
                                                        if (field != null) {
                                                            i11 = 0;
                                                            if (parameterTypes5[0] == Boolean.TYPE) {
                                                                StringBuilder sb2 = new StringBuilder();
                                                                sb2.append("is");
                                                                sb2.append(Character.toUpperCase(decapitalize.charAt(0)));
                                                                z3 = true;
                                                                sb2.append(decapitalize.substring(1));
                                                                field = TypeUtils.getField(cls, sb2.toString(), fieldArr4);
                                                            } else {
                                                                z3 = true;
                                                            }
                                                        } else {
                                                            z3 = true;
                                                            i11 = 0;
                                                        }
                                                        if (field == null) {
                                                            JSONField jSONField9 = (JSONField) field.getAnnotation(JSONField.class);
                                                            if (jSONField9 != null) {
                                                                if (jSONField9.deserialize()) {
                                                                    i31 = jSONField9.ordinal();
                                                                    i13 = SerializerFeature.m85of(jSONField9.serialzeFeatures());
                                                                    int m84of4 = Feature.m84of(jSONField9.parseFeatures());
                                                                    if (jSONField9.name().length() != 0) {
                                                                        i12 = i11;
                                                                        str2 = str8;
                                                                        add(arrayList, new FieldInfo(jSONField9.name(), method4, field, cls, type, i31, i13, m84of4, superMethodAnnotation, jSONField9, null));
                                                                        fieldArr = fieldArr4;
                                                                    } else {
                                                                        i12 = i11;
                                                                        str2 = str8;
                                                                        fieldArr5 = fieldArr4;
                                                                        i14 = m84of4;
                                                                    }
                                                                } else {
                                                                    i12 = i11;
                                                                    fieldArr = fieldArr4;
                                                                    str2 = str8;
                                                                }
                                                                propertyNamingStrategy3 = propertyNamingStrategy2;
                                                                i2 = i9 + 1;
                                                                propertyNamingStrategy2 = propertyNamingStrategy3;
                                                                i = i12;
                                                                length = i10;
                                                                methodArr3 = methodArr5;
                                                                str8 = str2;
                                                                cls2 = cls3;
                                                            } else {
                                                                i12 = i11;
                                                                str2 = str8;
                                                                fieldArr5 = fieldArr4;
                                                                i13 = i32;
                                                                i14 = i33;
                                                            }
                                                            jSONField2 = jSONField9;
                                                        } else {
                                                            i12 = i11;
                                                            str2 = str8;
                                                            fieldArr5 = fieldArr4;
                                                            i13 = i32;
                                                            jSONField2 = null;
                                                            i14 = i33;
                                                        }
                                                        propertyNamingStrategy3 = propertyNamingStrategy2;
                                                        if (propertyNamingStrategy2 != null) {
                                                            decapitalize = propertyNamingStrategy3.translate(decapitalize);
                                                        }
                                                        fieldArr = fieldArr5;
                                                        add(arrayList, new FieldInfo(decapitalize, method4, field, cls, type, i31, i13, i14, superMethodAnnotation, jSONField2, null));
                                                        i2 = i9 + 1;
                                                        propertyNamingStrategy2 = propertyNamingStrategy3;
                                                        i = i12;
                                                        length = i10;
                                                        methodArr3 = methodArr5;
                                                        str8 = str2;
                                                        cls2 = cls3;
                                                    } else if (charAt2 == 'f') {
                                                        decapitalize = name4.substring(3);
                                                    } else if (name4.length() >= 5 && Character.isUpperCase(name4.charAt(4))) {
                                                        decapitalize = TypeUtils.decapitalize(name4.substring(3));
                                                    }
                                                    fieldArr4 = fieldArr;
                                                    field = TypeUtils.getField(cls, decapitalize, fieldArr4);
                                                    if (field != null) {
                                                    }
                                                    if (field == null) {
                                                    }
                                                    propertyNamingStrategy3 = propertyNamingStrategy2;
                                                    if (propertyNamingStrategy2 != null) {
                                                    }
                                                    fieldArr = fieldArr5;
                                                    add(arrayList, new FieldInfo(decapitalize, method4, field, cls, type, i31, i13, i14, superMethodAnnotation, jSONField2, null));
                                                    i2 = i9 + 1;
                                                    propertyNamingStrategy2 = propertyNamingStrategy3;
                                                    i = i12;
                                                    length = i10;
                                                    methodArr3 = methodArr5;
                                                    str8 = str2;
                                                    cls2 = cls3;
                                                }
                                                str2 = str8;
                                                propertyNamingStrategy3 = propertyNamingStrategy2;
                                                i12 = 0;
                                                i2 = i9 + 1;
                                                propertyNamingStrategy2 = propertyNamingStrategy3;
                                                i = i12;
                                                length = i10;
                                                methodArr3 = methodArr5;
                                                str8 = str2;
                                                cls2 = cls3;
                                            }
                                        }
                                        str2 = str8;
                                        i12 = i34;
                                        propertyNamingStrategy3 = propertyNamingStrategy2;
                                        i2 = i9 + 1;
                                        propertyNamingStrategy2 = propertyNamingStrategy3;
                                        i = i12;
                                        length = i10;
                                        methodArr3 = methodArr5;
                                        str8 = str2;
                                        cls2 = cls3;
                                    }
                                }
                                propertyNamingStrategy3 = propertyNamingStrategy2;
                                i2 = i9 + 1;
                                propertyNamingStrategy2 = propertyNamingStrategy3;
                                i = i12;
                                length = i10;
                                methodArr3 = methodArr5;
                                str8 = str2;
                                cls2 = cls3;
                            }
                        }
                    }
                    i9 = i2;
                    i10 = length;
                    methodArr5 = methodArr3;
                    i12 = i;
                    str2 = str8;
                    cls3 = cls2;
                    propertyNamingStrategy3 = propertyNamingStrategy2;
                    i2 = i9 + 1;
                    propertyNamingStrategy2 = propertyNamingStrategy3;
                    i = i12;
                    length = i10;
                    methodArr3 = methodArr5;
                    str8 = str2;
                    cls2 = cls3;
                }
                int i35 = i;
                Class<?> cls7 = cls2;
                PropertyNamingStrategy propertyNamingStrategy6 = propertyNamingStrategy2;
                int i36 = 3;
                computeFields(cls, type, propertyNamingStrategy6, arrayList, cls.getFields());
                Method[] methods3 = cls.getMethods();
                length2 = methods3.length;
                i4 = i35;
                while (i4 < length2) {
                    Method method5 = methods3[i4];
                    String name5 = method5.getName();
                    if (name5.length() >= i3 && !Modifier.isStatic(method5.getModifiers()) && cls7 == null && name5.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET) && Character.isUpperCase(name5.charAt(i36)) && method5.getParameterTypes().length == 0 && ((Collection.class.isAssignableFrom(method5.getReturnType()) || Map.class.isAssignableFrom(method5.getReturnType()) || AtomicBoolean.class == method5.getReturnType() || AtomicInteger.class == method5.getReturnType() || AtomicLong.class == method5.getReturnType()) && ((jSONField = (JSONField) method5.getAnnotation(JSONField.class)) == null || !jSONField.deserialize()))) {
                        if (jSONField != null && jSONField.name().length() > 0) {
                            str = jSONField.name();
                            fieldArr2 = fieldArr;
                        } else {
                            str = Character.toLowerCase(name5.charAt(i36)) + name5.substring(i3);
                            fieldArr2 = fieldArr;
                            Field field3 = TypeUtils.getField(cls, str, fieldArr2);
                            if (field3 != null) {
                                JSONField jSONField10 = (JSONField) field3.getAnnotation(JSONField.class);
                                if (jSONField10 != null) {
                                }
                            }
                        }
                        if (getField(arrayList, str) == null) {
                            if (propertyNamingStrategy6 != null) {
                                str = propertyNamingStrategy6.translate(str);
                            }
                            fieldArr3 = fieldArr2;
                            i5 = i3;
                            i6 = i4;
                            i7 = length2;
                            methodArr4 = methods3;
                            i8 = i36;
                            add(arrayList, new FieldInfo(str, method5, null, cls, type, 0, 0, 0, jSONField, null, null));
                        }
                        fieldArr3 = fieldArr2;
                        i5 = i3;
                        i6 = i4;
                        i7 = length2;
                        methodArr4 = methods3;
                        i8 = i36;
                    } else {
                        i5 = i3;
                        i6 = i4;
                        i7 = length2;
                        methodArr4 = methods3;
                        i8 = i36;
                        fieldArr3 = fieldArr;
                    }
                    i4 = i6 + 1;
                    methods3 = methodArr4;
                    i3 = i5;
                    length2 = i7;
                    i36 = i8;
                    fieldArr = fieldArr3;
                }
                return new JavaBeanInfo(cls, cls7, constructor, constructor2, method2, method, jSONType, arrayList);
            }
        } else {
            fieldArr = declaredFields;
            jSONType = jSONType3;
            methodArr2 = methodArr;
            cls2 = builderClass;
        }
        i = 0;
        methodArr3 = methodArr2;
        length = methodArr3.length;
        i2 = i;
        while (true) {
            i3 = 4;
            if (i2 < length) {
            }
            i2 = i9 + 1;
            propertyNamingStrategy2 = propertyNamingStrategy3;
            i = i12;
            length = i10;
            methodArr3 = methodArr5;
            str8 = str2;
            cls2 = cls3;
        }
        int i352 = i;
        Class<?> cls72 = cls2;
        PropertyNamingStrategy propertyNamingStrategy62 = propertyNamingStrategy2;
        int i362 = 3;
        computeFields(cls, type, propertyNamingStrategy62, arrayList, cls.getFields());
        Method[] methods32 = cls.getMethods();
        length2 = methods32.length;
        i4 = i352;
        while (i4 < length2) {
        }
        return new JavaBeanInfo(cls, cls72, constructor, constructor2, method2, method, jSONType, arrayList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if ((java.util.Map.class.isAssignableFrom(r5) || java.util.Collection.class.isAssignableFrom(r5) || java.util.concurrent.atomic.AtomicLong.class.equals(r5) || java.util.concurrent.atomic.AtomicInteger.class.equals(r5) || java.util.concurrent.atomic.AtomicBoolean.class.equals(r5)) == false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void computeFields(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, List<FieldInfo> list, Field[] fieldArr) {
        int i;
        int i2;
        int i3;
        for (Field field : fieldArr) {
            int modifiers = field.getModifiers();
            if ((modifiers & 8) == 0) {
                boolean z = true;
                if ((modifiers & 16) != 0) {
                    Class<?> type2 = field.getType();
                }
                Iterator<FieldInfo> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().name.equals(field.getName())) {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    String name = field.getName();
                    JSONField jSONField = (JSONField) field.getAnnotation(JSONField.class);
                    if (jSONField == null) {
                        i = 0;
                        i2 = 0;
                        i3 = 0;
                    } else if (jSONField.deserialize()) {
                        int ordinal = jSONField.ordinal();
                        int m85of = SerializerFeature.m85of(jSONField.serialzeFeatures());
                        int m84of = Feature.m84of(jSONField.parseFeatures());
                        if (jSONField.name().length() != 0) {
                            name = jSONField.name();
                        }
                        i = ordinal;
                        i2 = m85of;
                        i3 = m84of;
                    }
                    if (propertyNamingStrategy != null) {
                        name = propertyNamingStrategy.translate(name);
                    }
                    add(list, new FieldInfo(name, null, field, cls, type, i, i2, i3, null, jSONField, null));
                }
            }
        }
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        int length = constructorArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        boolean z;
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = constructor3.getParameterAnnotations();
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Annotation[] annotationArr = parameterAnnotations[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        }
                        if (annotationArr[i2] instanceof JSONField) {
                            break;
                        }
                        i2++;
                    }
                    if (!z) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    continue;
                } else {
                    if (constructor != null) {
                        throw new JSONException("multi-JSONCreator");
                    }
                    constructor = constructor3;
                }
            }
        }
        if (constructor != null) {
        }
        return constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) method2.getAnnotation(JSONCreator.class)) != null) {
                if (method != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                method = method2;
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass(null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> builder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (builder = jSONType.builder()) == Void.class) {
            return null;
        }
        return builder;
    }
}
