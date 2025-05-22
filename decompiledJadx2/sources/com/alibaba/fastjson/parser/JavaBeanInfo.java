package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class JavaBeanInfo {
    final Constructor<?> creatorConstructor;
    public final String[] creatorConstructorParameters;
    final Constructor<?> defaultConstructor;
    final int defaultConstructorParameterSize;
    final Method factoryMethod;
    final FieldInfo[] fields;
    final JSONType jsonType;
    boolean ordered = false;
    public final int parserFeatures;
    final FieldInfo[] sortedFields;
    final boolean supportBeanToArray;
    public final String typeKey;
    public final long typeKeyHashCode;
    public final String typeName;

    JavaBeanInfo(Class<?> cls, Constructor<?> constructor, Constructor<?> constructor2, Method method, FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2, JSONType jSONType, String[] strArr) {
        int i;
        boolean z;
        int i2 = 0;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.fields = fieldInfoArr;
        this.jsonType = jSONType;
        if (strArr != null && strArr.length == fieldInfoArr.length) {
            this.creatorConstructorParameters = null;
        } else {
            this.creatorConstructorParameters = strArr;
        }
        if (jSONType != null) {
            String typeName = jSONType.typeName();
            this.typeName = typeName.length() <= 0 ? cls.getName() : typeName;
            String typeKey = jSONType.typeKey();
            this.typeKey = typeKey.length() > 0 ? typeKey : null;
            i = 0;
            for (Feature feature : jSONType.parseFeatures()) {
                i |= feature.mask;
            }
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            i = 0;
        }
        String str = this.typeKey;
        if (str == null) {
            this.typeKeyHashCode = 0L;
        } else {
            this.typeKeyHashCode = TypeUtils.fnv_64_lower(str);
        }
        this.parserFeatures = i;
        if (jSONType != null) {
            Feature[] parseFeatures = jSONType.parseFeatures();
            z = false;
            for (Feature feature2 : parseFeatures) {
                if (feature2 == Feature.SupportArrayToBean) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        this.supportBeanToArray = z;
        FieldInfo[] computeSortedFields = computeSortedFields(fieldInfoArr, fieldInfoArr2);
        this.sortedFields = Arrays.equals(fieldInfoArr, computeSortedFields) ? fieldInfoArr : computeSortedFields;
        if (constructor != null) {
            i2 = constructor.getParameterTypes().length;
        } else if (method != null) {
            i2 = method.getParameterTypes().length;
        }
        this.defaultConstructorParameterSize = i2;
    }

    private FieldInfo[] computeSortedFields(FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2) {
        String[] orders;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        JSONType jSONType = this.jsonType;
        if (jSONType != null && (orders = jSONType.orders()) != null && orders.length != 0) {
            int i = 0;
            while (true) {
                if (i >= orders.length) {
                    z = true;
                    break;
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= fieldInfoArr2.length) {
                        z4 = false;
                        break;
                    }
                    if (fieldInfoArr2[i2].name.equals(orders[i])) {
                        z4 = true;
                        break;
                    }
                    i2++;
                }
                if (!z4) {
                    z = false;
                    break;
                }
                i++;
            }
            if (!z) {
                return fieldInfoArr2;
            }
            if (orders.length == fieldInfoArr.length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= orders.length) {
                        z3 = true;
                        break;
                    }
                    if (!fieldInfoArr2[i3].name.equals(orders[i3])) {
                        z3 = false;
                        break;
                    }
                    i3++;
                }
                if (z3) {
                    return fieldInfoArr2;
                }
                FieldInfo[] fieldInfoArr3 = new FieldInfo[fieldInfoArr2.length];
                for (int i4 = 0; i4 < orders.length; i4++) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= fieldInfoArr2.length) {
                            break;
                        }
                        if (fieldInfoArr2[i5].name.equals(orders[i4])) {
                            fieldInfoArr3[i4] = fieldInfoArr2[i5];
                            break;
                        }
                        i5++;
                    }
                }
                this.ordered = true;
                return fieldInfoArr3;
            }
            int length = fieldInfoArr2.length;
            FieldInfo[] fieldInfoArr4 = new FieldInfo[length];
            for (int i6 = 0; i6 < orders.length; i6++) {
                int i7 = 0;
                while (true) {
                    if (i7 >= fieldInfoArr2.length) {
                        break;
                    }
                    if (fieldInfoArr2[i7].name.equals(orders[i6])) {
                        fieldInfoArr4[i6] = fieldInfoArr2[i7];
                        break;
                    }
                    i7++;
                }
            }
            int length2 = orders.length;
            for (int i8 = 0; i8 < fieldInfoArr2.length; i8++) {
                for (int i9 = 0; i9 < length && i9 < length2; i9++) {
                    if (fieldInfoArr4[i8].equals(fieldInfoArr2[i9])) {
                        z2 = true;
                        break;
                    }
                }
                z2 = false;
                if (!z2) {
                    fieldInfoArr4[length2] = fieldInfoArr2[i8];
                    length2++;
                }
            }
            this.ordered = true;
        }
        return fieldInfoArr2;
    }

    static boolean addField(List<FieldInfo> list, FieldInfo fieldInfo, boolean z) {
        if (!z) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                FieldInfo fieldInfo2 = list.get(i);
                if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                    return false;
                }
            }
        }
        list.add(fieldInfo);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:213:0x078c, code lost:
    
        if (r1.length() > 0) goto L363;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x05cd  */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.reflect.Type[]] */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.reflect.Type[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JavaBeanInfo build(Class<?> cls, int i, Type type, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        Constructor<?> constructor;
        Method[] methodArr;
        Method method;
        Method method2;
        Constructor<?> constructor2;
        Constructor<?> constructor3;
        Method[] methodArr2;
        Field[] fieldArr;
        HashMap hashMap;
        JSONField jSONField;
        String str;
        int i2;
        int i3;
        JSONField jSONField2;
        JSONField jSONField3;
        Constructor<?> constructor4;
        Method method3;
        Field[] fieldArr2;
        Method[] methodArr3;
        int i4;
        int i5;
        String str2;
        PropertyNamingStrategy propertyNamingStrategy2;
        int i6;
        int i7;
        Class<?> returnType;
        int i8;
        int i9;
        Method[] methodArr4;
        Constructor<?> constructor5;
        Method method4;
        HashMap hashMap2;
        Field[] fieldArr3;
        Method method5;
        int i10;
        int i11;
        String decapitalize;
        HashMap hashMap3;
        Field field;
        Field field2;
        HashMap hashMap4;
        Field[] fieldArr4;
        PropertyNamingStrategy propertyNamingStrategy3;
        int i12;
        JSONField jSONField4;
        Constructor<?> constructor6;
        ArrayList arrayList = new ArrayList();
        HashMap hashMap5 = new HashMap();
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        boolean isKotlin = TypeUtils.isKotlin(cls);
        int i13 = i & 1024;
        int i14 = 0;
        if (i13 != 0 || (declaredConstructors.length != 1 && isKotlin)) {
            constructor = null;
        } else {
            try {
                constructor6 = cls.getDeclaredConstructor(new Class[0]);
            } catch (Exception unused) {
                constructor6 = null;
            }
            if (constructor6 == null && cls.isMemberClass() && (i & 8) == 0) {
                int length = declaredConstructors.length;
                int i15 = 0;
                while (i15 < length) {
                    Constructor<?> constructor7 = declaredConstructors[i15];
                    Class<?>[] parameterTypes = constructor7.getParameterTypes();
                    Constructor<?> constructor8 = constructor6;
                    if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                        constructor = constructor7;
                        break;
                    }
                    i15++;
                    constructor6 = constructor8;
                }
            }
            constructor = constructor6;
        }
        String[] strArr = null;
        if (z) {
            methodArr = null;
            method = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            Class<?> cls2 = cls;
            Method method6 = null;
            while (cls2 != null && cls2 != Object.class) {
                Method[] declaredMethods = cls2.getDeclaredMethods();
                int length2 = declaredMethods.length;
                while (i14 < length2) {
                    int i16 = length2;
                    Method method7 = declaredMethods[i14];
                    Method[] methodArr5 = declaredMethods;
                    int modifiers = method7.getModifiers();
                    if ((modifiers & 8) != 0) {
                        if (method7.isAnnotationPresent(JSONCreator.class)) {
                            if (method6 != null) {
                                throw new JSONException("multi-json creator");
                            }
                            method6 = method7;
                            i14++;
                            length2 = i16;
                            declaredMethods = methodArr5;
                        }
                    } else if ((modifiers & 2) == 0) {
                        method2 = method6;
                        if ((modifiers & 256) == 0 && (modifiers & 4) == 0) {
                            arrayList2.add(method7);
                        }
                        method6 = method2;
                        i14++;
                        length2 = i16;
                        declaredMethods = methodArr5;
                    }
                    method2 = method6;
                    method6 = method2;
                    i14++;
                    length2 = i16;
                    declaredMethods = methodArr5;
                }
                cls2 = cls2.getSuperclass();
                i14 = 0;
            }
            Method[] methodArr6 = new Method[arrayList2.size()];
            arrayList2.toArray(methodArr6);
            methodArr = methodArr6;
            method = method6;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        boolean z5 = cls.isInterface() || i13 != 0;
        if (constructor == null || z5) {
            int length3 = declaredConstructors.length;
            int i17 = 0;
            while (true) {
                if (i17 >= length3) {
                    constructor2 = null;
                    break;
                }
                constructor2 = declaredConstructors[i17];
                int i18 = length3;
                if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                    break;
                }
                i17++;
                length3 = i18;
            }
            String str3 = "illegal json creator";
            if (constructor2 != null) {
                TypeUtils.setAccessible(cls, constructor2, i);
                Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
                Class<?>[] genericParameterTypes = z4 ? constructor2.getGenericParameterTypes() : parameterTypes2;
                Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                int i19 = 0;
                while (i19 < parameterTypes2.length) {
                    Annotation[] annotationArr = parameterAnnotations[i19];
                    int length4 = annotationArr.length;
                    String str4 = str3;
                    int i20 = 0;
                    while (true) {
                        if (i20 >= length4) {
                            jSONField3 = null;
                            break;
                        }
                        int i21 = length4;
                        Annotation annotation = annotationArr[i20];
                        Annotation[] annotationArr2 = annotationArr;
                        if (annotation instanceof JSONField) {
                            jSONField3 = (JSONField) annotation;
                            break;
                        }
                        i20++;
                        length4 = i21;
                        annotationArr = annotationArr2;
                    }
                    if (jSONField3 == null) {
                        throw new JSONException(str4);
                    }
                    Class<?> cls3 = parameterTypes2[i19];
                    Class<?> cls4 = genericParameterTypes[i19];
                    Field field3 = TypeUtils.getField(cls, jSONField3.name(), declaredFields, hashMap5);
                    if (field3 != null) {
                        TypeUtils.setAccessible(cls, field3, i);
                    }
                    addField(arrayList, new FieldInfo(jSONField3.name(), cls, cls3, cls4, field3, jSONField3.ordinal(), SerializerFeature.m85of(jSONField3.serialzeFeatures())), z);
                    i19++;
                    str3 = str4;
                    declaredFields = declaredFields;
                    constructor2 = constructor2;
                    hashMap5 = hashMap5;
                    parameterTypes2 = parameterTypes2;
                    methodArr = methodArr;
                    constructor = constructor;
                }
                constructor3 = constructor2;
                Constructor<?> constructor9 = constructor;
                methodArr2 = methodArr;
                HashMap hashMap6 = hashMap5;
                fieldArr = declaredFields;
                int size = arrayList.size();
                FieldInfo[] fieldInfoArr = new FieldInfo[size];
                arrayList.toArray(fieldInfoArr);
                FieldInfo[] fieldInfoArr2 = new FieldInfo[size];
                System.arraycopy(fieldInfoArr, 0, fieldInfoArr2, 0, size);
                Arrays.sort(fieldInfoArr2);
                if (z2) {
                }
                String[] strArr2 = new String[size];
                for (int i22 = 0; i22 < size; i22++) {
                    strArr2[i22] = fieldInfoArr[i22].name;
                }
                strArr = strArr2;
                hashMap = hashMap6;
                constructor = constructor9;
            } else {
                constructor3 = constructor2;
                Constructor<?> constructor10 = constructor;
                methodArr2 = methodArr;
                HashMap hashMap7 = hashMap5;
                fieldArr = declaredFields;
                if (method != null) {
                    TypeUtils.setAccessible(cls, method, i);
                    Class<?>[] parameterTypes3 = method.getParameterTypes();
                    if (parameterTypes3.length > 0) {
                        Class<?>[] genericParameterTypes2 = z4 ? method.getGenericParameterTypes() : parameterTypes3;
                        Annotation[][] parameterAnnotations2 = method.getParameterAnnotations();
                        int i23 = 0;
                        while (i23 < parameterTypes3.length) {
                            Annotation[] annotationArr3 = parameterAnnotations2[i23];
                            int length5 = annotationArr3.length;
                            int i24 = 0;
                            while (true) {
                                if (i24 >= length5) {
                                    jSONField2 = null;
                                    break;
                                }
                                Annotation annotation2 = annotationArr3[i24];
                                if (annotation2 instanceof JSONField) {
                                    jSONField2 = (JSONField) annotation2;
                                    break;
                                }
                                i24++;
                            }
                            if (jSONField2 == null) {
                                throw new JSONException("illegal json creator");
                            }
                            HashMap hashMap8 = hashMap7;
                            addField(arrayList, new FieldInfo(jSONField2.name(), cls, parameterTypes3[i23], genericParameterTypes2[i23], TypeUtils.getField(cls, jSONField2.name(), fieldArr, hashMap8), jSONField2.ordinal(), SerializerFeature.m85of(jSONField2.serialzeFeatures())), z);
                            i23++;
                            genericParameterTypes2 = genericParameterTypes2;
                            hashMap7 = hashMap8;
                            parameterTypes3 = parameterTypes3;
                        }
                        int size2 = arrayList.size();
                        FieldInfo[] fieldInfoArr3 = new FieldInfo[size2];
                        arrayList.toArray(fieldInfoArr3);
                        FieldInfo[] fieldInfoArr4 = new FieldInfo[size2];
                        System.arraycopy(fieldInfoArr3, 0, fieldInfoArr4, 0, size2);
                        Arrays.sort(fieldInfoArr4);
                        return new JavaBeanInfo(cls, null, null, method, fieldInfoArr3, Arrays.equals(fieldInfoArr3, fieldInfoArr4) ? fieldInfoArr3 : fieldInfoArr4, z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null, null);
                    }
                    hashMap = hashMap7;
                } else {
                    hashMap = hashMap7;
                    if (!z5) {
                        if (isKotlin && declaredConstructors.length > 0) {
                            String[] koltinConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                            if (koltinConstructorParameters != null) {
                                Constructor<?> constructor11 = constructor3;
                                for (Constructor<?> constructor12 : declaredConstructors) {
                                    Class<?>[] parameterTypes4 = constructor12.getParameterTypes();
                                    if ((parameterTypes4.length <= 0 || !parameterTypes4[parameterTypes4.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor11 == null || constructor11.getParameterTypes().length < parameterTypes4.length)) {
                                        constructor11 = constructor12;
                                    }
                                }
                                constructor11.setAccessible(true);
                                TypeUtils.setAccessible(cls, constructor11, i);
                                Class<?>[] parameterTypes5 = constructor11.getParameterTypes();
                                Class<?>[] genericParameterTypes3 = z4 ? constructor11.getGenericParameterTypes() : parameterTypes5;
                                Annotation[][] parameterAnnotations3 = constructor11.getParameterAnnotations();
                                int i25 = 0;
                                while (i25 < parameterTypes5.length) {
                                    String str5 = koltinConstructorParameters[i25];
                                    Annotation[] annotationArr4 = parameterAnnotations3[i25];
                                    int length6 = annotationArr4.length;
                                    int i26 = 0;
                                    while (true) {
                                        if (i26 >= length6) {
                                            jSONField = null;
                                            break;
                                        }
                                        Annotation annotation3 = annotationArr4[i26];
                                        Annotation[] annotationArr5 = annotationArr4;
                                        if (annotation3 instanceof JSONField) {
                                            jSONField = (JSONField) annotation3;
                                            break;
                                        }
                                        i26++;
                                        annotationArr4 = annotationArr5;
                                    }
                                    Class<?> cls5 = parameterTypes5[i25];
                                    Class<?> cls6 = genericParameterTypes3[i25];
                                    Field field4 = TypeUtils.getField(cls, str5, fieldArr, hashMap);
                                    if (field4 != null && jSONField == null) {
                                        jSONField = (JSONField) field4.getAnnotation(JSONField.class);
                                    }
                                    if (jSONField != null) {
                                        int ordinal = jSONField.ordinal();
                                        int m85of = SerializerFeature.m85of(jSONField.serialzeFeatures());
                                        String name = jSONField.name();
                                        if (name.length() != 0) {
                                            str5 = name;
                                        }
                                        i3 = m85of;
                                        i2 = ordinal;
                                        str = str5;
                                    } else {
                                        str = str5;
                                        i2 = 0;
                                        i3 = 0;
                                    }
                                    addField(arrayList, new FieldInfo(str, cls, cls5, cls6, field4, i2, i3), z);
                                    i25++;
                                    constructor11 = constructor11;
                                    parameterTypes5 = parameterTypes5;
                                    koltinConstructorParameters = koltinConstructorParameters;
                                }
                                Constructor<?> constructor13 = constructor11;
                                int size3 = arrayList.size();
                                FieldInfo[] fieldInfoArr5 = new FieldInfo[size3];
                                arrayList.toArray(fieldInfoArr5);
                                FieldInfo[] fieldInfoArr6 = new FieldInfo[size3];
                                System.arraycopy(fieldInfoArr5, 0, fieldInfoArr6, 0, size3);
                                Arrays.sort(fieldInfoArr6);
                                String[] strArr3 = new String[size3];
                                for (int i27 = 0; i27 < size3; i27++) {
                                    strArr3[i27] = fieldInfoArr5[i27].name;
                                }
                                strArr = strArr3;
                                constructor3 = constructor13;
                                constructor = constructor10;
                            } else {
                                throw new JSONException("default constructor not found. " + cls);
                            }
                        } else {
                            throw new JSONException("default constructor not found. " + cls);
                        }
                    }
                }
                constructor = constructor10;
            }
        } else {
            methodArr2 = methodArr;
            hashMap = hashMap5;
            constructor3 = null;
            fieldArr = declaredFields;
        }
        if (constructor != null) {
            TypeUtils.setAccessible(cls, constructor, i);
        }
        int i28 = 4;
        if (z) {
            constructor4 = constructor;
            method3 = method;
            fieldArr2 = fieldArr;
            methodArr3 = methodArr2;
        } else {
            Method[] methodArr7 = methodArr2;
            int length7 = methodArr7.length;
            int i29 = 0;
            while (i29 < length7) {
                Method method8 = methodArr7[i29];
                String name2 = method8.getName();
                if (name2.length() >= i28 && (((returnType = method8.getReturnType()) == Void.TYPE || returnType == method8.getDeclaringClass()) && method8.getParameterTypes().length == 1)) {
                    JSONField jSONField5 = z3 ? (JSONField) method8.getAnnotation(JSONField.class) : null;
                    if (jSONField5 == null && z3) {
                        jSONField5 = TypeUtils.getSupperMethodAnnotation(cls, method8);
                    }
                    JSONField jSONField6 = jSONField5;
                    if (jSONField6 == null) {
                        i8 = i29;
                        i9 = length7;
                        methodArr4 = methodArr7;
                        constructor5 = constructor;
                        method4 = method;
                        hashMap2 = hashMap;
                        fieldArr3 = fieldArr;
                        method5 = method8;
                        i10 = 0;
                        i11 = 0;
                    } else if (jSONField6.deserialize()) {
                        int ordinal2 = jSONField6.ordinal();
                        i11 = SerializerFeature.m85of(jSONField6.serialzeFeatures());
                        if (jSONField6.name().length() != 0) {
                            i8 = i29;
                            i9 = length7;
                            constructor5 = constructor;
                            methodArr4 = methodArr7;
                            method4 = method;
                            hashMap2 = hashMap;
                            fieldArr3 = fieldArr;
                            addField(arrayList, new FieldInfo(jSONField6.name(), method8, null, cls, type, ordinal2, i11, jSONField6, null, z4), z);
                            TypeUtils.setAccessible(cls, method8, i);
                            hashMap4 = hashMap2;
                            fieldArr4 = fieldArr3;
                            i29 = i8 + 1;
                            method = method4;
                            constructor = constructor5;
                            fieldArr = fieldArr4;
                            hashMap = hashMap4;
                            length7 = i9;
                            methodArr7 = methodArr4;
                            i28 = 4;
                        } else {
                            i8 = i29;
                            i9 = length7;
                            methodArr4 = methodArr7;
                            constructor5 = constructor;
                            method4 = method;
                            hashMap2 = hashMap;
                            fieldArr3 = fieldArr;
                            method5 = method8;
                            i10 = ordinal2;
                        }
                    }
                    if (name2.startsWith(TmpConstant.PROPERTY_IDENTIFIER_SET)) {
                        char charAt = name2.charAt(3);
                        if (Character.isUpperCase(charAt)) {
                            if (TypeUtils.compatibleWithJavaBean) {
                                decapitalize = TypeUtils.decapitalize(name2.substring(3));
                                hashMap3 = hashMap2;
                                field = TypeUtils.getField(cls, decapitalize, fieldArr3, hashMap3);
                                if (field == null && method5.getParameterTypes()[0] == Boolean.TYPE) {
                                    field = TypeUtils.getField(cls, "is" + Character.toUpperCase(decapitalize.charAt(0)) + decapitalize.substring(1), fieldArr3, hashMap3);
                                }
                                field2 = field;
                                if (field2 != null) {
                                    JSONField jSONField7 = z3 ? (JSONField) field2.getAnnotation(JSONField.class) : null;
                                    if (jSONField7 != null) {
                                        i10 = jSONField7.ordinal();
                                        i11 = SerializerFeature.m85of(jSONField7.serialzeFeatures());
                                        if (jSONField7.name().length() != 0) {
                                            fieldArr4 = fieldArr3;
                                            hashMap4 = hashMap3;
                                            addField(arrayList, new FieldInfo(jSONField7.name(), method5, field2, cls, type, i10, i11, jSONField6, jSONField7, z4), z);
                                            i29 = i8 + 1;
                                            method = method4;
                                            constructor = constructor5;
                                            fieldArr = fieldArr4;
                                            hashMap = hashMap4;
                                            length7 = i9;
                                            methodArr7 = methodArr4;
                                            i28 = 4;
                                        } else {
                                            hashMap4 = hashMap3;
                                            fieldArr4 = fieldArr3;
                                            if (jSONField6 == null) {
                                                propertyNamingStrategy3 = propertyNamingStrategy;
                                                i12 = i10;
                                                jSONField4 = jSONField7;
                                                int i30 = i11;
                                                if (propertyNamingStrategy3 != null) {
                                                    decapitalize = propertyNamingStrategy3.translate(decapitalize);
                                                }
                                                addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i12, i30, jSONField4, null, z4), z);
                                                TypeUtils.setAccessible(cls, method5, i);
                                                i29 = i8 + 1;
                                                method = method4;
                                                constructor = constructor5;
                                                fieldArr = fieldArr4;
                                                hashMap = hashMap4;
                                                length7 = i9;
                                                methodArr7 = methodArr4;
                                                i28 = 4;
                                            }
                                            propertyNamingStrategy3 = propertyNamingStrategy;
                                            i12 = i10;
                                            jSONField4 = jSONField6;
                                            int i302 = i11;
                                            if (propertyNamingStrategy3 != null) {
                                            }
                                            addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i12, i302, jSONField4, null, z4), z);
                                            TypeUtils.setAccessible(cls, method5, i);
                                            i29 = i8 + 1;
                                            method = method4;
                                            constructor = constructor5;
                                            fieldArr = fieldArr4;
                                            hashMap = hashMap4;
                                            length7 = i9;
                                            methodArr7 = methodArr4;
                                            i28 = 4;
                                        }
                                    }
                                }
                                hashMap4 = hashMap3;
                                fieldArr4 = fieldArr3;
                                propertyNamingStrategy3 = propertyNamingStrategy;
                                i12 = i10;
                                jSONField4 = jSONField6;
                                int i3022 = i11;
                                if (propertyNamingStrategy3 != null) {
                                }
                                addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i12, i3022, jSONField4, null, z4), z);
                                TypeUtils.setAccessible(cls, method5, i);
                                i29 = i8 + 1;
                                method = method4;
                                constructor = constructor5;
                                fieldArr = fieldArr4;
                                hashMap = hashMap4;
                                length7 = i9;
                                methodArr7 = methodArr4;
                                i28 = 4;
                            } else {
                                decapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                            }
                        } else if (charAt == '_') {
                            decapitalize = name2.substring(4);
                        } else if (charAt == 'f') {
                            decapitalize = name2.substring(3);
                        } else if (name2.length() >= 5 && Character.isUpperCase(name2.charAt(4))) {
                            decapitalize = TypeUtils.decapitalize(name2.substring(3));
                        }
                        hashMap3 = hashMap2;
                        field = TypeUtils.getField(cls, decapitalize, fieldArr3, hashMap3);
                        if (field == null) {
                            field = TypeUtils.getField(cls, "is" + Character.toUpperCase(decapitalize.charAt(0)) + decapitalize.substring(1), fieldArr3, hashMap3);
                        }
                        field2 = field;
                        if (field2 != null) {
                        }
                        hashMap4 = hashMap3;
                        fieldArr4 = fieldArr3;
                        propertyNamingStrategy3 = propertyNamingStrategy;
                        i12 = i10;
                        jSONField4 = jSONField6;
                        int i30222 = i11;
                        if (propertyNamingStrategy3 != null) {
                        }
                        addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i12, i30222, jSONField4, null, z4), z);
                        TypeUtils.setAccessible(cls, method5, i);
                        i29 = i8 + 1;
                        method = method4;
                        constructor = constructor5;
                        fieldArr = fieldArr4;
                        hashMap = hashMap4;
                        length7 = i9;
                        methodArr7 = methodArr4;
                        i28 = 4;
                    }
                    hashMap4 = hashMap2;
                    fieldArr4 = fieldArr3;
                    i29 = i8 + 1;
                    method = method4;
                    constructor = constructor5;
                    fieldArr = fieldArr4;
                    hashMap = hashMap4;
                    length7 = i9;
                    methodArr7 = methodArr4;
                    i28 = 4;
                }
                i8 = i29;
                i9 = length7;
                methodArr4 = methodArr7;
                constructor5 = constructor;
                method4 = method;
                fieldArr4 = fieldArr;
                hashMap4 = hashMap;
                i29 = i8 + 1;
                method = method4;
                constructor = constructor5;
                fieldArr = fieldArr4;
                hashMap = hashMap4;
                length7 = i9;
                methodArr7 = methodArr4;
                i28 = 4;
            }
            methodArr3 = methodArr7;
            constructor4 = constructor;
            method3 = method;
            fieldArr2 = fieldArr;
        }
        Field[] fieldArr5 = fieldArr2;
        ArrayList<Field> arrayList3 = new ArrayList(fieldArr5.length);
        for (Field field5 : fieldArr5) {
            int modifiers2 = field5.getModifiers();
            if ((modifiers2 & 8) == 0) {
                if ((modifiers2 & 16) != 0) {
                    Class<?> type2 = field5.getType();
                    if (!(Map.class.isAssignableFrom(type2) || Collection.class.isAssignableFrom(type2))) {
                    }
                }
                if ((field5.getModifiers() & 1) != 0) {
                    arrayList3.add(field5);
                }
            }
        }
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Field field6 : superclass.getDeclaredFields()) {
                int modifiers3 = field6.getModifiers();
                if ((modifiers3 & 8) == 0) {
                    if ((modifiers3 & 16) != 0) {
                        Class<?> type3 = field6.getType();
                        if (!(Map.class.isAssignableFrom(type3) || Collection.class.isAssignableFrom(type3))) {
                        }
                    }
                    if ((modifiers3 & 1) != 0) {
                        arrayList3.add(field6);
                    }
                }
            }
        }
        for (Field field7 : arrayList3) {
            String name3 = field7.getName();
            int size4 = arrayList.size();
            boolean z6 = false;
            for (int i31 = 0; i31 < size4; i31++) {
                if (((FieldInfo) arrayList.get(i31)).name.equals(name3)) {
                    z6 = true;
                }
            }
            if (!z6) {
                JSONField jSONField8 = z3 ? (JSONField) field7.getAnnotation(JSONField.class) : null;
                if (jSONField8 != null) {
                    int ordinal3 = jSONField8.ordinal();
                    int m85of2 = SerializerFeature.m85of(jSONField8.serialzeFeatures());
                    if (jSONField8.name().length() != 0) {
                        name3 = jSONField8.name();
                    }
                    propertyNamingStrategy2 = propertyNamingStrategy;
                    i6 = ordinal3;
                    i7 = m85of2;
                } else {
                    propertyNamingStrategy2 = propertyNamingStrategy;
                    i6 = 0;
                    i7 = 0;
                }
                if (propertyNamingStrategy2 != null) {
                    name3 = propertyNamingStrategy2.translate(name3);
                }
                TypeUtils.setAccessible(cls, field7, i);
                addField(arrayList, new FieldInfo(name3, null, field7, cls, type, i6, i7, null, jSONField8, z4), z);
            }
        }
        if (!z) {
            Method[] methodArr8 = methodArr3;
            int length8 = methodArr8.length;
            int i32 = 0;
            while (i32 < length8) {
                Method method9 = methodArr8[i32];
                String name4 = method9.getName();
                if (name4.length() >= 4 && name4.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET) && Character.isUpperCase(name4.charAt(3)) && method9.getParameterTypes().length == 0) {
                    Class<?> returnType2 = method9.getReturnType();
                    if (Collection.class.isAssignableFrom(returnType2) || Map.class.isAssignableFrom(returnType2)) {
                        JSONField jSONField9 = z3 ? (JSONField) method9.getAnnotation(JSONField.class) : null;
                        if (jSONField9 != null) {
                            str2 = jSONField9.name();
                        }
                        str2 = Character.toLowerCase(name4.charAt(3)) + name4.substring(4);
                        JSONField jSONField10 = jSONField9;
                        i4 = i32;
                        i5 = length8;
                        addField(arrayList, new FieldInfo(str2, method9, null, cls, type, 0, 0, jSONField10, null, z4), z);
                        TypeUtils.setAccessible(cls, method9, i);
                        i32 = i4 + 1;
                        length8 = i5;
                    }
                }
                i4 = i32;
                i5 = length8;
                i32 = i4 + 1;
                length8 = i5;
            }
        }
        int size5 = arrayList.size();
        FieldInfo[] fieldInfoArr7 = new FieldInfo[size5];
        arrayList.toArray(fieldInfoArr7);
        FieldInfo[] fieldInfoArr8 = new FieldInfo[size5];
        System.arraycopy(fieldInfoArr7, 0, fieldInfoArr8, 0, size5);
        Arrays.sort(fieldInfoArr8);
        return new JavaBeanInfo(cls, constructor4, constructor3, method3, fieldInfoArr7, fieldInfoArr8, z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null, strArr);
    }
}
