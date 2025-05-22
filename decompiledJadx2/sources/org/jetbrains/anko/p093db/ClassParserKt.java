package org.jetbrains.anko.p093db;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ClassParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0001H\u0086\b\u001a\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004H\u0001\u001a\u0014\u0010\t\u001a\u00020\n2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u000b"}, m3961d2 = {"castValue", "", ES6Iterator.VALUE_PROPERTY, "type", "Ljava/lang/Class;", "classParser", "Lorg/jetbrains/anko/db/RowParser;", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "hasApplicableType", "", "sqlite-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class ClassParserKt {
    private static final <T> RowParser<T> classParser() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return classParser(Object.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0054, code lost:
    
        if (r7 != false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> RowParser<T> classParser(Class<T> clazz) {
        final Constructor constructor;
        Class<?>[] parameterTypes;
        boolean z;
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        Intrinsics.checkExpressionValueIsNotNull(declaredConstructors, "clazz.declaredConstructors");
        ArrayList arrayList = new ArrayList();
        int length = declaredConstructors.length;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= length) {
                break;
            }
            Constructor<?> ctr = declaredConstructors[i];
            Intrinsics.checkExpressionValueIsNotNull(ctr, "ctr");
            if (!ctr.isVarArgs() && Modifier.isPublic(ctr.getModifiers()) && (parameterTypes = ctr.getParameterTypes()) != null) {
                if (!(parameterTypes.length == 0)) {
                    int length2 = parameterTypes.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = true;
                            break;
                        }
                        if (!hasApplicableType(parameterTypes[i2])) {
                            z = false;
                            break;
                        }
                        i2++;
                    }
                }
            }
            z2 = false;
            if (z2) {
                arrayList.add(ctr);
            }
            i++;
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            throw new AnkoException("Can't initialize object parser for " + clazz.getCanonicalName() + ", no acceptable constructors found");
        }
        if (arrayList2.size() <= 1) {
            constructor = (Constructor) arrayList2.get(0);
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (T t : arrayList2) {
                if (((Constructor) t).isAnnotationPresent(ClassParserConstructor.class)) {
                    arrayList3.add(t);
                }
            }
            constructor = (Constructor) CollectionsKt.singleOrNull((List) arrayList3);
            if (constructor == null) {
                throw new AnkoException("Several constructors are annotated with ClassParserConstructor");
            }
        }
        return new RowParser<T>(constructor) { // from class: org.jetbrains.anko.db.ClassParserKt$classParser$1
            final /* synthetic */ Constructor $preferredConstructor;
            private final Class<?>[] parameterTypes;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$preferredConstructor = constructor;
                Intrinsics.checkExpressionValueIsNotNull(constructor, "preferredConstructor");
                this.parameterTypes = constructor.getParameterTypes();
            }

            @Override // org.jetbrains.anko.p093db.RowParser
            public T parseRow(Object[] columns) {
                Object castValue;
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                Class<?>[] clsArr = this.parameterTypes;
                if (clsArr.length != columns.length) {
                    String joinToString$default = ArraysKt.joinToString$default(columns, (CharSequence) null, "[", "]", 0, (CharSequence) null, (Function1) null, 57, (Object) null);
                    Class<?>[] parameterTypes2 = this.parameterTypes;
                    Intrinsics.checkExpressionValueIsNotNull(parameterTypes2, "parameterTypes");
                    String joinToString$default2 = ArraysKt.joinToString$default(parameterTypes2, (CharSequence) null, "[", "]", 0, (CharSequence) null, new Function1<Class<?>, String>() { // from class: org.jetbrains.anko.db.ClassParserKt$classParser$1$parseRow$parameterTypesRendered$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(Class<?> it) {
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            String canonicalName = it.getCanonicalName();
                            Intrinsics.checkExpressionValueIsNotNull(canonicalName, "it.canonicalName");
                            return canonicalName;
                        }
                    }, 25, (Object) null);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Class parser for ");
                    Constructor preferredConstructor = this.$preferredConstructor;
                    Intrinsics.checkExpressionValueIsNotNull(preferredConstructor, "preferredConstructor");
                    sb.append(preferredConstructor.getName());
                    sb.append(' ');
                    sb.append("failed to parse the row: ");
                    sb.append(joinToString$default);
                    sb.append(" (constructor parameter types: ");
                    sb.append(joinToString$default2);
                    sb.append(')');
                    throw new AnkoException(sb.toString());
                }
                int i3 = 0;
                int length3 = clsArr.length - 1;
                if (length3 >= 0) {
                    while (true) {
                        Class<?> type = this.parameterTypes[i3];
                        Object obj = columns[i3];
                        if (!type.isInstance(obj)) {
                            Intrinsics.checkExpressionValueIsNotNull(type, "type");
                            castValue = ClassParserKt.castValue(obj, type);
                            columns[i3] = castValue;
                        }
                        if (i3 == length3) {
                            break;
                        }
                        i3++;
                    }
                }
                return (T) JavaSqliteUtils.newInstance(this.$preferredConstructor, columns);
            }
        };
    }

    private static final boolean hasApplicableType(Class<?> cls) {
        return cls.isPrimitive() || Intrinsics.areEqual(cls, String.class) || Intrinsics.areEqual(cls, CharSequence.class) || Intrinsics.areEqual(cls, Long.class) || Intrinsics.areEqual(cls, Integer.class) || Intrinsics.areEqual(cls, Byte.class) || Intrinsics.areEqual(cls, Character.class) || Intrinsics.areEqual(cls, Boolean.class) || Intrinsics.areEqual(cls, Float.class) || Intrinsics.areEqual(cls, Double.class) || Intrinsics.areEqual(cls, Short.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object castValue(Object obj, Class<?> cls) {
        if (obj == null && cls.isPrimitive()) {
            throw new AnkoException("null can't be converted to the value of primitive type " + cls.getCanonicalName());
        }
        if (obj == null || Intrinsics.areEqual(cls, Object.class)) {
            return obj;
        }
        if (cls.isPrimitive() && Intrinsics.areEqual(JavaSqliteUtils.PRIMITIVES_TO_WRAPPERS.get(cls), obj.getClass())) {
            return obj;
        }
        if ((obj instanceof Double) && (Intrinsics.areEqual(cls, Float.TYPE) || Intrinsics.areEqual(cls, Float.class))) {
            return Float.valueOf((float) ((Number) obj).doubleValue());
        }
        if ((obj instanceof Float) && (Intrinsics.areEqual(cls, Double.TYPE) || Intrinsics.areEqual(cls, Double.class))) {
            return Double.valueOf(((Number) obj).floatValue());
        }
        if ((obj instanceof Character) && CharSequence.class.isAssignableFrom(cls)) {
            return obj.toString();
        }
        if (obj instanceof Long) {
            if (Intrinsics.areEqual(cls, Integer.TYPE) || Intrinsics.areEqual(cls, Integer.class)) {
                return Integer.valueOf((int) ((Number) obj).longValue());
            }
            if (Intrinsics.areEqual(cls, Short.TYPE) || Intrinsics.areEqual(cls, Short.class)) {
                return Short.valueOf((short) ((Number) obj).longValue());
            }
            if (Intrinsics.areEqual(cls, Byte.TYPE) || Intrinsics.areEqual(cls, Byte.class)) {
                return Byte.valueOf((byte) ((Number) obj).longValue());
            }
            if (Intrinsics.areEqual(cls, Boolean.TYPE) || Intrinsics.areEqual(cls, Boolean.class)) {
                return Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0L));
            }
            if (Intrinsics.areEqual(cls, Character.TYPE) || Intrinsics.areEqual(cls, Character.class)) {
                return Character.valueOf((char) ((Number) obj).longValue());
            }
        }
        if (obj instanceof Integer) {
            if (Intrinsics.areEqual(cls, Long.TYPE) || Intrinsics.areEqual(cls, Long.class)) {
                return Long.valueOf(((Number) obj).intValue());
            }
            if (Intrinsics.areEqual(cls, Short.TYPE) || Intrinsics.areEqual(cls, Short.class)) {
                return Short.valueOf((short) ((Number) obj).intValue());
            }
            if (Intrinsics.areEqual(cls, Byte.TYPE) || Intrinsics.areEqual(cls, Byte.class)) {
                return Byte.valueOf((byte) ((Number) obj).intValue());
            }
            if (Intrinsics.areEqual(cls, Boolean.TYPE) || Intrinsics.areEqual(cls, Boolean.class)) {
                return Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0));
            }
            if (Intrinsics.areEqual(cls, Character.TYPE) || Intrinsics.areEqual(cls, Character.class)) {
                return Character.valueOf((char) ((Number) obj).intValue());
            }
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 1 && (Intrinsics.areEqual(cls, Character.TYPE) || Intrinsics.areEqual(cls, Character.class))) {
                return Character.valueOf(str.charAt(0));
            }
        }
        throw new AnkoException("Value " + obj + " of type " + obj.getClass() + " can't be cast to " + cls.getCanonicalName());
    }
}
