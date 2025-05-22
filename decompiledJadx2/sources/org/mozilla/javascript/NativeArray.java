package org.mozilla.javascript;

import android.support.v4.media.session.PlaybackStateCompat;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.regexp.NativeRegExp;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeArray extends IdScriptableObject implements List {
    private static final int ConstructorId_concat = -13;
    private static final int ConstructorId_every = -17;
    private static final int ConstructorId_filter = -18;
    private static final int ConstructorId_find = -22;
    private static final int ConstructorId_findIndex = -23;
    private static final int ConstructorId_forEach = -19;
    private static final int ConstructorId_indexOf = -15;
    private static final int ConstructorId_isArray = -26;
    private static final int ConstructorId_join = -5;
    private static final int ConstructorId_lastIndexOf = -16;
    private static final int ConstructorId_map = -20;
    private static final int ConstructorId_pop = -9;
    private static final int ConstructorId_push = -8;
    private static final int ConstructorId_reduce = -24;
    private static final int ConstructorId_reduceRight = -25;
    private static final int ConstructorId_reverse = -6;
    private static final int ConstructorId_shift = -10;
    private static final int ConstructorId_slice = -14;
    private static final int ConstructorId_some = -21;
    private static final int ConstructorId_sort = -7;
    private static final int ConstructorId_splice = -12;
    private static final int ConstructorId_unshift = -11;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5d;
    private static final int Id_concat = 13;
    private static final int Id_constructor = 1;
    private static final int Id_every = 17;
    private static final int Id_filter = 18;
    private static final int Id_find = 22;
    private static final int Id_findIndex = 23;
    private static final int Id_forEach = 19;
    private static final int Id_indexOf = 15;
    private static final int Id_join = 5;
    private static final int Id_lastIndexOf = 16;
    private static final int Id_length = 1;
    private static final int Id_map = 20;
    private static final int Id_pop = 9;
    private static final int Id_push = 8;
    private static final int Id_reduce = 24;
    private static final int Id_reduceRight = 25;
    private static final int Id_reverse = 6;
    private static final int Id_shift = 10;
    private static final int Id_slice = 14;
    private static final int Id_some = 21;
    private static final int Id_sort = 7;
    private static final int Id_splice = 12;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 4;
    private static final int Id_toString = 2;
    private static final int Id_unshift = 11;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PRE_GROW_SIZE = 1431655764;
    private static final int MAX_PROTOTYPE_ID = 26;
    private static final int SymbolId_iterator = 26;
    static final long serialVersionUID = 7331366857676127338L;
    private Object[] dense;
    private boolean denseOnly;
    private long length;
    private int lengthAttr;
    private static final Object ARRAY_TAG = "Array";
    private static final Integer NEGATIVE_ONE = -1;
    private static final Comparator<Object> STRING_COMPARATOR = new StringLikeComparator();
    private static final Comparator<Object> DEFAULT_COMPARATOR = new ElementComparator();
    private static int maximumInitialCapacity = 10000;

    private static long toSliceIndex(double d, long j) {
        if (d < 0.0d) {
            d += j;
            if (d < 0.0d) {
                return 0L;
            }
        } else if (d > j) {
            return j;
        }
        return (long) d;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Array";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int getMaxInstanceId() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeArray(0L).exportAsJSClass(26, scriptable, z);
    }

    static int getMaximumInitialCapacity() {
        return maximumInitialCapacity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setMaximumInitialCapacity(int i) {
        maximumInitialCapacity = i;
    }

    public NativeArray(long j) {
        this.lengthAttr = 6;
        boolean z = j <= ((long) maximumInitialCapacity);
        this.denseOnly = z;
        if (z) {
            int i = (int) j;
            Object[] objArr = new Object[i < 10 ? 10 : i];
            this.dense = objArr;
            Arrays.fill(objArr, Scriptable.NOT_FOUND);
        }
        this.length = j;
    }

    public NativeArray(Object[] objArr) {
        this.lengthAttr = 6;
        this.denseOnly = true;
        this.dense = objArr;
        this.length = objArr.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.lengthAttr = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        if (str.equals(Name.LENGTH)) {
            return instanceIdInfo(this.lengthAttr, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public String getInstanceIdName(int i) {
        return i == 1 ? Name.LENGTH : super.getInstanceIdName(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapNumber(this.length);
        }
        return super.getInstanceIdValue(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            setLength(obj);
        } else {
            super.setInstanceIdValue(i, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -5, "join", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -6, "reverse", 0);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -7, "sort", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -8, IoTConfig.PARAM_PUSH_FILE, 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -9, "pop", 0);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -10, "shift", 0);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -11, "unshift", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -12, "splice", 2);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -13, "concat", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -14, "slice", 2);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -15, "indexOf", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -16, "lastIndexOf", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -17, "every", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -18, "filter", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -19, "forEach", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -20, MapElement.Key.MAP, 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -21, "some", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -22, "find", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -23, "findIndex", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, -24, "reduce", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, ConstructorId_reduceRight, "reduceRight", 1);
        addIdFunctionProperty(idFunctionObject, ARRAY_TAG, ConstructorId_isArray, "isArray", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0015. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        String str3;
        int i2;
        String str4;
        if (i == 26) {
            initPrototypeMethod(ARRAY_TAG, i, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
        switch (i) {
            case 1:
                str = "constructor";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 2:
                str2 = "toString";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 3:
                str2 = "toLocaleString";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 4:
                str2 = "toSource";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 5:
                str = "join";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 6:
                str2 = "reverse";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 7:
                str = "sort";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 8:
                str = IoTConfig.PARAM_PUSH_FILE;
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 9:
                str2 = "pop";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 10:
                str2 = "shift";
                str3 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 11:
                str = "unshift";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 12:
                str4 = "splice";
                i2 = 2;
                str3 = str4;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 13:
                str = "concat";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 14:
                str4 = "slice";
                i2 = 2;
                str3 = str4;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 15:
                str = "indexOf";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 16:
                str = "lastIndexOf";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 17:
                str = "every";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 18:
                str = "filter";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 19:
                str = "forEach";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 20:
                str = MapElement.Key.MAP;
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 21:
                str = "some";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 22:
                str = "find";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 23:
                str = "findIndex";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 24:
                str = "reduce";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            case 25:
                str = "reduceRight";
                str3 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str3, (String) null, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ARRAY_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        while (true) {
            int i = 0;
            switch (methodId) {
                case ConstructorId_isArray /* -26 */:
                    return Boolean.valueOf(objArr.length > 0 && js_isArray(objArr[0]));
                case ConstructorId_reduceRight /* -25 */:
                case -24:
                case -23:
                case -22:
                case -21:
                case -20:
                case -19:
                case -18:
                case -17:
                case -16:
                case -15:
                case -14:
                case -13:
                case -12:
                case -11:
                case -10:
                case -9:
                case -8:
                case -7:
                case -6:
                case -5:
                    if (objArr.length > 0) {
                        scriptable2 = ScriptRuntime.toObject(context, scriptable, objArr[0]);
                        int length = objArr.length - 1;
                        Object[] objArr2 = new Object[length];
                        while (i < length) {
                            int i2 = i + 1;
                            objArr2[i] = objArr[i2];
                            i = i2;
                        }
                        objArr = objArr2;
                    }
                    methodId = -methodId;
                default:
                    switch (methodId) {
                        case 1:
                            if (!(scriptable2 == null)) {
                                return idFunctionObject.construct(context, scriptable, objArr);
                            }
                            return jsConstructor(context, scriptable, objArr);
                        case 2:
                            return toStringHelper(context, scriptable, scriptable2, context.hasFeature(4), false);
                        case 3:
                            return toStringHelper(context, scriptable, scriptable2, false, true);
                        case 4:
                            return toStringHelper(context, scriptable, scriptable2, true, false);
                        case 5:
                            return js_join(context, scriptable2, objArr);
                        case 6:
                            return js_reverse(context, scriptable2, objArr);
                        case 7:
                            return js_sort(context, scriptable, scriptable2, objArr);
                        case 8:
                            return js_push(context, scriptable2, objArr);
                        case 9:
                            return js_pop(context, scriptable2, objArr);
                        case 10:
                            return js_shift(context, scriptable2, objArr);
                        case 11:
                            return js_unshift(context, scriptable2, objArr);
                        case 12:
                            return js_splice(context, scriptable, scriptable2, objArr);
                        case 13:
                            return js_concat(context, scriptable, scriptable2, objArr);
                        case 14:
                            return js_slice(context, scriptable2, objArr);
                        case 15:
                            return js_indexOf(context, scriptable2, objArr);
                        case 16:
                            return js_lastIndexOf(context, scriptable2, objArr);
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                            return iterativeMethod(context, idFunctionObject, scriptable, scriptable2, objArr);
                        case 24:
                        case 25:
                            return reduceMethod(context, methodId, scriptable, scriptable2, objArr);
                        case 26:
                            return new NativeArrayIterator(scriptable, scriptable2);
                        default:
                            throw new IllegalArgumentException("Array.prototype has no method: " + idFunctionObject.getFunctionName());
                    }
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        if (!this.denseOnly && isGetterOrSetter(null, i, false)) {
            return super.get(i, scriptable);
        }
        Object[] objArr = this.dense;
        if (objArr != null && i >= 0 && i < objArr.length) {
            return objArr[i];
        }
        return super.get(i, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (!this.denseOnly && isGetterOrSetter(null, i, false)) {
            return super.has(i, scriptable);
        }
        Object[] objArr = this.dense;
        if (objArr == null || i < 0 || i >= objArr.length) {
            return super.has(i, scriptable);
        }
        return objArr[i] != NOT_FOUND;
    }

    private static long toArrayIndex(Object obj) {
        if (obj instanceof String) {
            return toArrayIndex((String) obj);
        }
        if (obj instanceof Number) {
            return toArrayIndex(((Number) obj).doubleValue());
        }
        return -1L;
    }

    private static long toArrayIndex(String str) {
        long arrayIndex = toArrayIndex(ScriptRuntime.toNumber(str));
        if (Long.toString(arrayIndex).equals(str)) {
            return arrayIndex;
        }
        return -1L;
    }

    private static long toArrayIndex(double d) {
        if (d != d) {
            return -1L;
        }
        long uint32 = ScriptRuntime.toUint32(d);
        if (uint32 != d || uint32 == 4294967295L) {
            return -1L;
        }
        return uint32;
    }

    private static int toDenseIndex(Object obj) {
        long arrayIndex = toArrayIndex(obj);
        if (0 > arrayIndex || arrayIndex >= 2147483647L) {
            return -1;
        }
        return (int) arrayIndex;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        super.put(str, scriptable, obj);
        if (scriptable == this) {
            long arrayIndex = toArrayIndex(str);
            if (arrayIndex >= this.length) {
                this.length = arrayIndex + 1;
                this.denseOnly = false;
            }
        }
    }

    private boolean ensureCapacity(int i) {
        if (i <= this.dense.length) {
            return true;
        }
        if (i > MAX_PRE_GROW_SIZE) {
            this.denseOnly = false;
            return false;
        }
        int max = Math.max(i, (int) (r0.length * 1.5d));
        Object[] objArr = new Object[max];
        Object[] objArr2 = this.dense;
        System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
        Arrays.fill(objArr, this.dense.length, max, Scriptable.NOT_FOUND);
        this.dense = objArr;
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (scriptable == this && !isSealed() && this.dense != null && i >= 0 && (this.denseOnly || !isGetterOrSetter(null, i, true))) {
            if (!isExtensible() && this.length <= i) {
                return;
            }
            Object[] objArr = this.dense;
            if (i < objArr.length) {
                objArr[i] = obj;
                long j = i;
                if (this.length <= j) {
                    this.length = j + 1;
                    return;
                }
                return;
            }
            if (this.denseOnly && i < objArr.length * 1.5d && ensureCapacity(i + 1)) {
                this.dense[i] = obj;
                this.length = i + 1;
                return;
            }
            this.denseOnly = false;
        }
        super.put(i, scriptable, obj);
        if (scriptable == this && (this.lengthAttr & 1) == 0) {
            long j2 = i;
            if (this.length <= j2) {
                this.length = j2 + 1;
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
        Object[] objArr = this.dense;
        if (objArr != null && i >= 0 && i < objArr.length && !isSealed() && (this.denseOnly || !isGetterOrSetter(null, i, true))) {
            this.dense[i] = NOT_FOUND;
        } else {
            super.delete(i);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    public Object[] getIds(boolean z, boolean z2) {
        Object[] ids = super.getIds(z, z2);
        Object[] objArr = this.dense;
        if (objArr == null) {
            return ids;
        }
        int length = objArr.length;
        long j = this.length;
        if (length > j) {
            length = (int) j;
        }
        if (length == 0) {
            return ids;
        }
        int length2 = ids.length;
        Object[] objArr2 = new Object[length + length2];
        int i = 0;
        for (int i2 = 0; i2 != length; i2++) {
            if (this.dense[i2] != NOT_FOUND) {
                objArr2[i] = Integer.valueOf(i2);
                i++;
            }
        }
        if (i != length) {
            Object[] objArr3 = new Object[i + length2];
            System.arraycopy(objArr2, 0, objArr3, 0, i);
            objArr2 = objArr3;
        }
        System.arraycopy(ids, 0, objArr2, i, length2);
        return objArr2;
    }

    public Integer[] getIndexIds() {
        Object[] ids = getIds();
        ArrayList arrayList = new ArrayList(ids.length);
        for (Object obj : ids) {
            int int32 = ScriptRuntime.toInt32(obj);
            if (int32 >= 0 && ScriptRuntime.toString(int32).equals(ScriptRuntime.toString(obj))) {
                arrayList.add(Integer.valueOf(int32));
            }
        }
        return (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        if (cls == ScriptRuntime.NumberClass && Context.getContext().getLanguageVersion() == 120) {
            return Long.valueOf(this.length);
        }
        return super.getDefaultValue(cls);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object obj) {
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, parentScope, TopLevel.Builtins.Object);
        nativeObject.defineProperty(ES6Iterator.VALUE_PROPERTY, obj, 0);
        nativeObject.defineProperty("writable", (Object) true, 0);
        nativeObject.defineProperty("enumerable", (Object) true, 0);
        nativeObject.defineProperty("configurable", (Object) true, 0);
        return nativeObject;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(int i) {
        Object[] objArr = this.dense;
        if (objArr == null || i < 0 || i >= objArr.length || objArr[i] == NOT_FOUND) {
            return super.getAttributes(i);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    public ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        int denseIndex;
        if (this.dense != null && (denseIndex = toDenseIndex(obj)) >= 0) {
            Object[] objArr = this.dense;
            if (denseIndex < objArr.length && objArr[denseIndex] != NOT_FOUND) {
                return defaultIndexPropertyDescriptor(this.dense[denseIndex]);
            }
        }
        return super.getOwnPropertyDescriptor(context, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.ScriptableObject
    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        Object[] objArr = this.dense;
        if (objArr != null) {
            this.dense = null;
            this.denseOnly = false;
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] != NOT_FOUND) {
                    put(i, this, objArr[i]);
                }
            }
        }
        long arrayIndex = toArrayIndex(obj);
        if (arrayIndex >= this.length) {
            this.length = arrayIndex + 1;
        }
        super.defineOwnProperty(context, obj, scriptableObject, z);
    }

    private static Object jsConstructor(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length == 0) {
            return new NativeArray(0L);
        }
        if (context.getLanguageVersion() == 120) {
            return new NativeArray(objArr);
        }
        Object obj = objArr[0];
        if (objArr.length > 1 || !(obj instanceof Number)) {
            return new NativeArray(objArr);
        }
        long uint32 = ScriptRuntime.toUint32(obj);
        if (uint32 != ((Number) obj).doubleValue()) {
            throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.arraylength.bad"));
        }
        return new NativeArray(uint32);
    }

    public long getLength() {
        return this.length;
    }

    @Deprecated
    public long jsGet_length() {
        return getLength();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDenseOnly(boolean z) {
        if (z && !this.denseOnly) {
            throw new IllegalArgumentException();
        }
        this.denseOnly = z;
    }

    private void setLength(Object obj) {
        if ((this.lengthAttr & 1) != 0) {
            return;
        }
        double number = ScriptRuntime.toNumber(obj);
        long uint32 = ScriptRuntime.toUint32(number);
        double d = uint32;
        if (d != number) {
            throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.arraylength.bad"));
        }
        if (this.denseOnly) {
            long j = this.length;
            if (uint32 < j) {
                Object[] objArr = this.dense;
                Arrays.fill(objArr, (int) uint32, objArr.length, NOT_FOUND);
                this.length = uint32;
                return;
            } else {
                if (uint32 < 1431655764 && d < j * 1.5d && ensureCapacity((int) uint32)) {
                    this.length = uint32;
                    return;
                }
                this.denseOnly = false;
            }
        }
        long j2 = this.length;
        if (uint32 < j2) {
            if (j2 - uint32 > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
                for (Object obj2 : getIds()) {
                    if (obj2 instanceof String) {
                        String str = (String) obj2;
                        if (toArrayIndex(str) >= uint32) {
                            delete(str);
                        }
                    } else {
                        int intValue = ((Integer) obj2).intValue();
                        if (intValue >= uint32) {
                            delete(intValue);
                        }
                    }
                }
            } else {
                for (long j3 = uint32; j3 < this.length; j3++) {
                    deleteElem(this, j3);
                }
            }
        }
        this.length = uint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLengthProperty(Context context, Scriptable scriptable) {
        if (scriptable instanceof NativeString) {
            return ((NativeString) scriptable).getLength();
        }
        if (scriptable instanceof NativeArray) {
            return ((NativeArray) scriptable).getLength();
        }
        Object property = ScriptableObject.getProperty(scriptable, Name.LENGTH);
        if (property == Scriptable.NOT_FOUND) {
            return 0L;
        }
        return ScriptRuntime.toUint32(property);
    }

    private static Object setLengthProperty(Context context, Scriptable scriptable, long j) {
        Number wrapNumber = ScriptRuntime.wrapNumber(j);
        ScriptableObject.putProperty(scriptable, Name.LENGTH, wrapNumber);
        return wrapNumber;
    }

    private static void deleteElem(Scriptable scriptable, long j) {
        int i = (int) j;
        if (i == j) {
            scriptable.delete(i);
        } else {
            scriptable.delete(Long.toString(j));
        }
    }

    private static Object getElem(Context context, Scriptable scriptable, long j) {
        Object rawElem = getRawElem(scriptable, j);
        return rawElem != Scriptable.NOT_FOUND ? rawElem : Undefined.instance;
    }

    private static Object getRawElem(Scriptable scriptable, long j) {
        if (j > 2147483647L) {
            return ScriptableObject.getProperty(scriptable, Long.toString(j));
        }
        return ScriptableObject.getProperty(scriptable, (int) j);
    }

    private static void defineElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (j > 2147483647L) {
            scriptable.put(Long.toString(j), scriptable, obj);
        } else {
            scriptable.put((int) j, scriptable, obj);
        }
    }

    private static void setElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (j > 2147483647L) {
            ScriptableObject.putProperty(scriptable, Long.toString(j), obj);
        } else {
            ScriptableObject.putProperty(scriptable, (int) j, obj);
        }
    }

    private static void setRawElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (obj == NOT_FOUND) {
            deleteElem(scriptable, j);
        } else {
            setElem(context, scriptable, j, obj);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String toStringHelper(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z, boolean z2) {
        String str;
        boolean has;
        boolean z3;
        boolean z4;
        long j;
        boolean z5;
        long lengthProperty = getLengthProperty(context, scriptable2);
        StringBuilder sb = new StringBuilder(256);
        if (z) {
            sb.append('[');
            str = ", ";
        } else {
            str = ",";
        }
        if (context.iterating == null) {
            context.iterating = new ObjToIntMap(31);
            has = false;
            z3 = true;
        } else {
            has = context.iterating.has(scriptable2);
            z3 = false;
        }
        long j2 = 0;
        if (has) {
            z5 = false;
            j = 0;
        } else {
            try {
                context.iterating.put(scriptable2, 0);
                if (z && context.getLanguageVersion() >= 150) {
                    z4 = false;
                    boolean z6 = false;
                    j = 0;
                    while (j < lengthProperty) {
                        if (j > j2) {
                            sb.append(str);
                        }
                        Object rawElem = getRawElem(scriptable2, j);
                        if (rawElem != NOT_FOUND && (!z4 || (rawElem != null && rawElem != Undefined.instance))) {
                            if (z) {
                                sb.append(ScriptRuntime.uneval(context, scriptable, rawElem));
                            } else if (rawElem instanceof String) {
                                String str2 = (String) rawElem;
                                if (z) {
                                    sb.append('\"');
                                    sb.append(ScriptRuntime.escapeString(str2));
                                    sb.append('\"');
                                } else {
                                    sb.append(str2);
                                }
                            } else {
                                if (z2) {
                                    rawElem = ScriptRuntime.getPropFunctionAndThis(rawElem, "toLocaleString", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), ScriptRuntime.emptyArgs);
                                }
                                sb.append(ScriptRuntime.toString(rawElem));
                            }
                            z6 = true;
                            j++;
                            j2 = 0;
                        }
                        z6 = false;
                        j++;
                        j2 = 0;
                    }
                    z5 = z6;
                }
                z4 = true;
                boolean z62 = false;
                j = 0;
                while (j < lengthProperty) {
                }
                z5 = z62;
            } finally {
                if (z3) {
                    context.iterating = null;
                }
            }
        }
        if (z) {
            if (!z5 && j > 0) {
                sb.append(", ]");
            } else {
                sb.append(']');
            }
        }
        return sb.toString();
    }

    private static String js_join(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        long lengthProperty = getLengthProperty(context, scriptable);
        int i = (int) lengthProperty;
        if (lengthProperty != i) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(lengthProperty));
        }
        int i2 = 0;
        String scriptRuntime = (objArr.length < 1 || objArr[0] == Undefined.instance) ? "," : ScriptRuntime.toString(objArr[0]);
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                StringBuilder sb = new StringBuilder();
                while (i2 < i) {
                    if (i2 != 0) {
                        sb.append(scriptRuntime);
                    }
                    Object[] objArr2 = nativeArray.dense;
                    if (i2 < objArr2.length && (obj = objArr2[i2]) != null && obj != Undefined.instance && obj != Scriptable.NOT_FOUND) {
                        sb.append(ScriptRuntime.toString(obj));
                    }
                    i2++;
                }
                return sb.toString();
            }
        }
        if (i == 0) {
            return "";
        }
        String[] strArr = new String[i];
        int i3 = 0;
        for (int i4 = 0; i4 != i; i4++) {
            Object elem = getElem(context, scriptable, i4);
            if (elem != null && elem != Undefined.instance) {
                String scriptRuntime2 = ScriptRuntime.toString(elem);
                i3 += scriptRuntime2.length();
                strArr[i4] = scriptRuntime2;
            }
        }
        StringBuilder sb2 = new StringBuilder(i3 + ((i - 1) * scriptRuntime.length()));
        while (i2 != i) {
            if (i2 != 0) {
                sb2.append(scriptRuntime);
            }
            String str = strArr[i2];
            if (str != null) {
                sb2.append(str);
            }
            i2++;
        }
        return sb2.toString();
    }

    private static Scriptable js_reverse(Context context, Scriptable scriptable, Object[] objArr) {
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                int i = 0;
                for (int i2 = ((int) nativeArray.length) - 1; i < i2; i2--) {
                    Object[] objArr2 = nativeArray.dense;
                    Object obj = objArr2[i];
                    objArr2[i] = objArr2[i2];
                    objArr2[i2] = obj;
                    i++;
                }
                return scriptable;
            }
        }
        long lengthProperty = getLengthProperty(context, scriptable);
        long j = lengthProperty / 2;
        for (long j2 = 0; j2 < j; j2++) {
            long j3 = (lengthProperty - j2) - 1;
            Object rawElem = getRawElem(scriptable, j2);
            setRawElem(context, scriptable, j2, getRawElem(scriptable, j3));
            setRawElem(context, scriptable, j3, rawElem);
        }
        return scriptable;
    }

    private static Scriptable js_sort(final Context context, final Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Comparator comparator;
        if (objArr.length > 0 && Undefined.instance != objArr[0]) {
            final Callable valueFunctionAndThis = ScriptRuntime.getValueFunctionAndThis(objArr[0], context);
            final Scriptable lastStoredScriptable = ScriptRuntime.lastStoredScriptable(context);
            final Object[] objArr2 = new Object[2];
            comparator = new ElementComparator(new Comparator<Object>() { // from class: org.mozilla.javascript.NativeArray.1
                @Override // java.util.Comparator
                public int compare(Object obj, Object obj2) {
                    Object[] objArr3 = objArr2;
                    objArr3[0] = obj;
                    objArr3[1] = obj2;
                    double number = ScriptRuntime.toNumber(valueFunctionAndThis.call(context, scriptable, lastStoredScriptable, objArr3));
                    if (number < 0.0d) {
                        return -1;
                    }
                    return number > 0.0d ? 1 : 0;
                }
            });
        } else {
            comparator = DEFAULT_COMPARATOR;
        }
        long lengthProperty = getLengthProperty(context, scriptable2);
        int i = (int) lengthProperty;
        if (lengthProperty != i) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(lengthProperty));
        }
        Object[] objArr3 = new Object[i];
        for (int i2 = 0; i2 != i; i2++) {
            objArr3[i2] = getRawElem(scriptable2, i2);
        }
        Sorting.hybridSort(objArr3, comparator);
        for (int i3 = 0; i3 < i; i3++) {
            setRawElem(context, scriptable2, i3, objArr3[i3]);
        }
        return scriptable2;
    }

    private static Object js_push(Context context, Scriptable scriptable, Object[] objArr) {
        int i = 0;
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly && nativeArray.ensureCapacity(((int) nativeArray.length) + objArr.length)) {
                while (i < objArr.length) {
                    Object[] objArr2 = nativeArray.dense;
                    long j = nativeArray.length;
                    nativeArray.length = 1 + j;
                    objArr2[(int) j] = objArr[i];
                    i++;
                }
                return ScriptRuntime.wrapNumber(nativeArray.length);
            }
        }
        long lengthProperty = getLengthProperty(context, scriptable);
        while (i < objArr.length) {
            setElem(context, scriptable, i + lengthProperty, objArr[i]);
            i++;
        }
        return context.getLanguageVersion() == 120 ? objArr.length == 0 ? Undefined.instance : objArr[objArr.length - 1] : setLengthProperty(context, scriptable, lengthProperty + objArr.length);
    }

    private static Object js_pop(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                long j = nativeArray.length;
                if (j > 0) {
                    long j2 = j - 1;
                    nativeArray.length = j2;
                    Object[] objArr2 = nativeArray.dense;
                    Object obj2 = objArr2[(int) j2];
                    objArr2[(int) j2] = NOT_FOUND;
                    return obj2;
                }
            }
        }
        long lengthProperty = getLengthProperty(context, scriptable);
        if (lengthProperty > 0) {
            lengthProperty--;
            obj = getElem(context, scriptable, lengthProperty);
            deleteElem(scriptable, lengthProperty);
        } else {
            obj = Undefined.instance;
        }
        setLengthProperty(context, scriptable, lengthProperty);
        return obj;
    }

    private static Object js_shift(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                long j = nativeArray.length;
                if (j > 0) {
                    long j2 = j - 1;
                    nativeArray.length = j2;
                    Object[] objArr2 = nativeArray.dense;
                    Object obj2 = objArr2[0];
                    System.arraycopy(objArr2, 1, objArr2, 0, (int) j2);
                    nativeArray.dense[(int) nativeArray.length] = NOT_FOUND;
                    return obj2 == NOT_FOUND ? Undefined.instance : obj2;
                }
            }
        }
        long lengthProperty = getLengthProperty(context, scriptable);
        if (lengthProperty > 0) {
            lengthProperty--;
            obj = getElem(context, scriptable, 0L);
            if (lengthProperty > 0) {
                for (long j3 = 1; j3 <= lengthProperty; j3++) {
                    setRawElem(context, scriptable, j3 - 1, getRawElem(scriptable, j3));
                }
            }
            deleteElem(scriptable, lengthProperty);
        } else {
            obj = Undefined.instance;
        }
        setLengthProperty(context, scriptable, lengthProperty);
        return obj;
    }

    private static Object js_unshift(Context context, Scriptable scriptable, Object[] objArr) {
        int i = 0;
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly && nativeArray.ensureCapacity(((int) nativeArray.length) + objArr.length)) {
                Object[] objArr2 = nativeArray.dense;
                System.arraycopy(objArr2, 0, objArr2, objArr.length, (int) nativeArray.length);
                while (i < objArr.length) {
                    nativeArray.dense[i] = objArr[i];
                    i++;
                }
                long length = nativeArray.length + objArr.length;
                nativeArray.length = length;
                return ScriptRuntime.wrapNumber(length);
            }
        }
        long lengthProperty = getLengthProperty(context, scriptable);
        int length2 = objArr.length;
        if (objArr.length > 0) {
            if (lengthProperty > 0) {
                for (long j = lengthProperty - 1; j >= 0; j--) {
                    setRawElem(context, scriptable, length2 + j, getRawElem(scriptable, j));
                }
            }
            while (i < objArr.length) {
                setElem(context, scriptable, i, objArr[i]);
                i++;
            }
        }
        return setLengthProperty(context, scriptable, lengthProperty + objArr.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014c A[LOOP:1: B:42:0x014a->B:43:0x014c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0125  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object js_splice(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        NativeArray nativeArray;
        boolean z;
        boolean z2;
        long j;
        NativeArray nativeArray2;
        long j2;
        Object obj;
        long j3;
        int i;
        Object obj2;
        if (scriptable2 instanceof NativeArray) {
            nativeArray = (NativeArray) scriptable2;
            z = nativeArray.denseOnly;
        } else {
            nativeArray = null;
            z = false;
        }
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        int length = objArr.length;
        if (length == 0) {
            return context.newArray(topLevelScope, 0);
        }
        long lengthProperty = getLengthProperty(context, scriptable2);
        long sliceIndex = toSliceIndex(ScriptRuntime.toInteger(objArr[0]), lengthProperty);
        int i2 = length - 1;
        if (objArr.length == 1) {
            j = lengthProperty - sliceIndex;
            z2 = z;
        } else {
            double integer = ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0.0d) {
                z2 = z;
                j = 0;
            } else {
                z2 = z;
                long j4 = lengthProperty - sliceIndex;
                if (integer <= j4) {
                    j4 = (long) integer;
                }
                j = j4;
            }
            i2--;
        }
        long j5 = sliceIndex + j;
        if (j != 0) {
            if (j == 1 && context.getLanguageVersion() == 120) {
                nativeArray2 = nativeArray;
                j2 = lengthProperty;
                obj2 = getElem(context, scriptable2, sliceIndex);
            } else {
                if (z2) {
                    int i3 = (int) (j5 - sliceIndex);
                    Object[] objArr2 = new Object[i3];
                    nativeArray2 = nativeArray;
                    j2 = lengthProperty;
                    System.arraycopy(nativeArray.dense, (int) sliceIndex, objArr2, 0, i3);
                    obj = context.newArray(topLevelScope, objArr2);
                    long j6 = i2;
                    j3 = j6 - j;
                    if (z2) {
                        long j7 = j2 + j3;
                        if (j7 < 2147483647L) {
                            int i4 = (int) j7;
                            NativeArray nativeArray3 = nativeArray2;
                            if (nativeArray3.ensureCapacity(i4)) {
                                Object[] objArr3 = nativeArray3.dense;
                                System.arraycopy(objArr3, (int) j5, objArr3, (int) (j6 + sliceIndex), (int) (j2 - j5));
                                if (i2 > 0) {
                                    System.arraycopy(objArr, 2, nativeArray3.dense, (int) sliceIndex, i2);
                                }
                                if (j3 < 0) {
                                    Arrays.fill(nativeArray3.dense, i4, (int) j2, NOT_FOUND);
                                }
                                nativeArray3.length = j7;
                                return obj;
                            }
                        }
                    }
                    long j8 = j2;
                    if (j3 <= 0) {
                        long j9 = j8 - 1;
                        while (j9 >= j5) {
                            setRawElem(context, scriptable2, j9 + j3, getRawElem(scriptable2, j9));
                            j9--;
                            j5 = j5;
                        }
                    } else if (j3 < 0) {
                        for (long j10 = j5; j10 < j8; j10++) {
                            setRawElem(context, scriptable2, j10 + j3, getRawElem(scriptable2, j10));
                        }
                        for (long j11 = j8 + j3; j11 < j8; j11++) {
                            deleteElem(scriptable2, j11);
                        }
                    }
                    int length2 = objArr.length - i2;
                    for (i = 0; i < i2; i++) {
                        setElem(context, scriptable2, i + sliceIndex, objArr[i + length2]);
                    }
                    setLengthProperty(context, scriptable2, j8 + j3);
                    return obj;
                }
                nativeArray2 = nativeArray;
                j2 = lengthProperty;
                Scriptable newArray = context.newArray(topLevelScope, 0);
                for (long j12 = sliceIndex; j12 != j5; j12++) {
                    Object rawElem = getRawElem(scriptable2, j12);
                    if (rawElem != NOT_FOUND) {
                        setElem(context, newArray, j12 - sliceIndex, rawElem);
                    }
                }
                setLengthProperty(context, newArray, j5 - sliceIndex);
                obj2 = newArray;
            }
            obj = obj2;
            long j62 = i2;
            j3 = j62 - j;
            if (z2) {
            }
            long j82 = j2;
            if (j3 <= 0) {
            }
            int length22 = objArr.length - i2;
            while (i < i2) {
            }
            setLengthProperty(context, scriptable2, j82 + j3);
            return obj;
        }
        nativeArray2 = nativeArray;
        j2 = lengthProperty;
        if (context.getLanguageVersion() == 120) {
            obj2 = Undefined.instance;
            obj = obj2;
            long j622 = i2;
            j3 = j622 - j;
            if (z2) {
            }
            long j822 = j2;
            if (j3 <= 0) {
            }
            int length222 = objArr.length - i2;
            while (i < i2) {
            }
            setLengthProperty(context, scriptable2, j822 + j3);
            return obj;
        }
        obj = context.newArray(topLevelScope, 0);
        long j6222 = i2;
        j3 = j6222 - j;
        if (z2) {
        }
        long j8222 = j2;
        if (j3 <= 0) {
        }
        int length2222 = objArr.length - i2;
        while (i < i2) {
        }
        setLengthProperty(context, scriptable2, j8222 + j3);
        return obj;
    }

    private static Scriptable js_concat(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long j;
        int i = 0;
        Scriptable newArray = context.newArray(getTopLevelScope(scriptable), 0);
        if ((scriptable2 instanceof NativeArray) && (newArray instanceof NativeArray)) {
            NativeArray nativeArray = (NativeArray) scriptable2;
            NativeArray nativeArray2 = (NativeArray) newArray;
            if (nativeArray.denseOnly && nativeArray2.denseOnly) {
                int i2 = (int) nativeArray.length;
                boolean z = true;
                for (int i3 = 0; i3 < objArr.length && z; i3++) {
                    if (objArr[i3] instanceof NativeArray) {
                        NativeArray nativeArray3 = (NativeArray) objArr[i3];
                        boolean z2 = nativeArray3.denseOnly;
                        i2 = (int) (i2 + nativeArray3.length);
                        z = z2;
                    } else {
                        i2++;
                    }
                }
                if (z && nativeArray2.ensureCapacity(i2)) {
                    System.arraycopy(nativeArray.dense, 0, nativeArray2.dense, 0, (int) nativeArray.length);
                    int i4 = (int) nativeArray.length;
                    for (int i5 = 0; i5 < objArr.length && z; i5++) {
                        if (objArr[i5] instanceof NativeArray) {
                            NativeArray nativeArray4 = (NativeArray) objArr[i5];
                            System.arraycopy(nativeArray4.dense, 0, nativeArray2.dense, i4, (int) nativeArray4.length);
                            i4 += (int) nativeArray4.length;
                        } else {
                            nativeArray2.dense[i4] = objArr[i5];
                            i4++;
                        }
                    }
                    nativeArray2.length = i2;
                    return newArray;
                }
            }
        }
        long j2 = 0;
        if (js_isArray(scriptable2)) {
            long lengthProperty = getLengthProperty(context, scriptable2);
            j = 0;
            while (j < lengthProperty) {
                Object rawElem = getRawElem(scriptable2, j);
                if (rawElem != NOT_FOUND) {
                    defineElem(context, newArray, j, rawElem);
                }
                j++;
            }
        } else {
            defineElem(context, newArray, 0L, scriptable2);
            j = 1;
        }
        while (i < objArr.length) {
            if (js_isArray(objArr[i])) {
                Scriptable scriptable3 = (Scriptable) objArr[i];
                long lengthProperty2 = getLengthProperty(context, scriptable3);
                long j3 = j2;
                while (j3 < lengthProperty2) {
                    Object rawElem2 = getRawElem(scriptable3, j3);
                    if (rawElem2 != NOT_FOUND) {
                        defineElem(context, newArray, j, rawElem2);
                    }
                    j3++;
                    j++;
                }
            } else {
                defineElem(context, newArray, j, objArr[i]);
                j++;
            }
            i++;
            j2 = 0;
        }
        setLengthProperty(context, newArray, j);
        return newArray;
    }

    private Scriptable js_slice(Context context, Scriptable scriptable, Object[] objArr) {
        long sliceIndex;
        Scriptable newArray = context.newArray(getTopLevelScope(this), 0);
        long lengthProperty = getLengthProperty(context, scriptable);
        if (objArr.length == 0) {
            sliceIndex = 0;
        } else {
            sliceIndex = toSliceIndex(ScriptRuntime.toInteger(objArr[0]), lengthProperty);
            if (objArr.length != 1 && objArr[1] != Undefined.instance) {
                lengthProperty = toSliceIndex(ScriptRuntime.toInteger(objArr[1]), lengthProperty);
            }
        }
        for (long j = sliceIndex; j < lengthProperty; j++) {
            Object rawElem = getRawElem(scriptable, j);
            if (rawElem != NOT_FOUND) {
                defineElem(context, newArray, j - sliceIndex, rawElem);
            }
        }
        setLengthProperty(context, newArray, Math.max(0L, lengthProperty - sliceIndex));
        return newArray;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        if (r8 < 0) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object js_indexOf(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        long lengthProperty = getLengthProperty(context, scriptable);
        long j = 0;
        if (objArr.length >= 2) {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0) {
                integer += lengthProperty;
            }
            j = integer;
            if (j > lengthProperty - 1) {
                return NEGATIVE_ONE;
            }
        }
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                Scriptable prototype = nativeArray.getPrototype();
                int i = (int) j;
                while (true) {
                    long j2 = i;
                    if (j2 < lengthProperty) {
                        Object obj2 = nativeArray.dense[i];
                        if (obj2 == NOT_FOUND && prototype != null) {
                            obj2 = ScriptableObject.getProperty(prototype, i);
                        }
                        if (obj2 != NOT_FOUND && ScriptRuntime.shallowEq(obj2, obj)) {
                            return Long.valueOf(j2);
                        }
                        i++;
                    } else {
                        return NEGATIVE_ONE;
                    }
                }
            }
        }
        while (j < lengthProperty) {
            Object rawElem = getRawElem(scriptable, j);
            if (rawElem != NOT_FOUND && ScriptRuntime.shallowEq(rawElem, obj)) {
                return Long.valueOf(j);
            }
            j++;
        }
        return NEGATIVE_ONE;
    }

    private static Object js_lastIndexOf(Context context, Scriptable scriptable, Object[] objArr) {
        long j;
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        long lengthProperty = getLengthProperty(context, scriptable);
        if (objArr.length < 2) {
            j = lengthProperty - 1;
        } else {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer >= lengthProperty) {
                j = lengthProperty - 1;
            } else {
                if (integer < 0) {
                    integer += lengthProperty;
                }
                j = integer;
            }
            if (j < 0) {
                return NEGATIVE_ONE;
            }
        }
        if (scriptable instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly) {
                Scriptable prototype = nativeArray.getPrototype();
                for (int i = (int) j; i >= 0; i--) {
                    Object obj2 = nativeArray.dense[i];
                    if (obj2 == NOT_FOUND && prototype != null) {
                        obj2 = ScriptableObject.getProperty(prototype, i);
                    }
                    if (obj2 != NOT_FOUND && ScriptRuntime.shallowEq(obj2, obj)) {
                        return Long.valueOf(i);
                    }
                }
                return NEGATIVE_ONE;
            }
        }
        while (j >= 0) {
            Object rawElem = getRawElem(scriptable, j);
            if (rawElem != NOT_FOUND && ScriptRuntime.shallowEq(rawElem, obj)) {
                return Long.valueOf(j);
            }
            j--;
        }
        return NEGATIVE_ONE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:42:0x00b2. Please report as an issue. */
    private static Object iterativeMethod(Context context, IdFunctionObject idFunctionObject, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable newArray;
        Scriptable scriptable3;
        long j;
        boolean z;
        int abs = Math.abs(idFunctionObject.methodId());
        int i = 23;
        int i2 = 22;
        Scriptable requireObjectCoercible = (22 == abs || 23 == abs) ? ScriptRuntimeES6.requireObjectCoercible(context, scriptable2, idFunctionObject) : scriptable2;
        long lengthProperty = getLengthProperty(context, requireObjectCoercible);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (obj == null || !(obj instanceof Function)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        if (context.getLanguageVersion() >= 200 && (obj instanceof NativeRegExp)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        Function function = (Function) obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(function);
        Scriptable object = (objArr.length < 2 || objArr[1] == null || objArr[1] == Undefined.instance) ? topLevelScope : ScriptRuntime.toObject(context, scriptable, objArr[1]);
        if (abs == 18 || abs == 20) {
            newArray = context.newArray(scriptable, abs == 20 ? (int) lengthProperty : 0);
        } else {
            newArray = null;
        }
        long j2 = 0;
        long j3 = 0;
        while (j2 < lengthProperty) {
            Object[] objArr2 = new Object[3];
            Object rawElem = getRawElem(requireObjectCoercible, j2);
            if (rawElem == Scriptable.NOT_FOUND) {
                if (abs == i2 || abs == i) {
                    rawElem = Undefined.instance;
                } else {
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                }
            }
            objArr2[0] = rawElem;
            objArr2[1] = Long.valueOf(j2);
            objArr2[2] = requireObjectCoercible;
            Object call = function.call(context, topLevelScope, object, objArr2);
            switch (abs) {
                case 17:
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    if (!ScriptRuntime.toBoolean(call)) {
                        return Boolean.FALSE;
                    }
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 18:
                    if (ScriptRuntime.toBoolean(call)) {
                        scriptable3 = requireObjectCoercible;
                        long j4 = j3;
                        j3 = j4 + 1;
                        z = false;
                        defineElem(context, newArray, j4, objArr2[0]);
                        j2++;
                        requireObjectCoercible = scriptable3;
                        i = 23;
                        i2 = 22;
                    }
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 19:
                default:
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 20:
                    defineElem(context, newArray, j2, call);
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 21:
                    if (ScriptRuntime.toBoolean(call)) {
                        return Boolean.TRUE;
                    }
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 22:
                    if (ScriptRuntime.toBoolean(call)) {
                        return rawElem;
                    }
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
                case 23:
                    if (ScriptRuntime.toBoolean(call)) {
                        return ScriptRuntime.wrapNumber(j2);
                    }
                    scriptable3 = requireObjectCoercible;
                    j = j3;
                    z = false;
                    j3 = j;
                    j2++;
                    requireObjectCoercible = scriptable3;
                    i = 23;
                    i2 = 22;
            }
        }
        switch (abs) {
            case 17:
                return Boolean.TRUE;
            case 18:
            case 20:
                return newArray;
            case 19:
            case 22:
            default:
                return Undefined.instance;
            case 21:
                return Boolean.FALSE;
            case 23:
                return ScriptRuntime.wrapNumber(-1.0d);
        }
    }

    private static Object reduceMethod(Context context, int i, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long lengthProperty = getLengthProperty(context, scriptable2);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (obj == null || !(obj instanceof Function)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        Function function = (Function) obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(function);
        boolean z = i == 24;
        Object obj2 = objArr.length > 1 ? objArr[1] : Scriptable.NOT_FOUND;
        for (long j = 0; j < lengthProperty; j++) {
            long j2 = z ? j : (lengthProperty - 1) - j;
            Object rawElem = getRawElem(scriptable2, j2);
            if (rawElem != Scriptable.NOT_FOUND) {
                obj2 = obj2 == Scriptable.NOT_FOUND ? rawElem : function.call(context, topLevelScope, topLevelScope, new Object[]{obj2, rawElem, Long.valueOf(j2), scriptable2});
            }
        }
        if (obj2 != Scriptable.NOT_FOUND) {
            return obj2;
        }
        throw ScriptRuntime.typeError0("msg.empty.array.reduce");
    }

    private static boolean js_isArray(Object obj) {
        if (obj instanceof Scriptable) {
            return "Array".equals(((Scriptable) obj).getClassName());
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) > -1;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return toArray(ScriptRuntime.emptyArgs);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        long j = this.length;
        if (j > 2147483647L) {
            throw new IllegalStateException();
        }
        int i = (int) j;
        if (objArr.length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        }
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = get(i2);
        }
        return objArr;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public int size() {
        long j = this.length;
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new IllegalStateException();
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.length == 0;
    }

    public Object get(long j) {
        if (j < 0 || j >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object rawElem = getRawElem(this, j);
        if (rawElem == Scriptable.NOT_FOUND || rawElem == Undefined.instance) {
            return null;
        }
        return rawElem instanceof Wrapper ? ((Wrapper) rawElem).unwrap() : rawElem;
    }

    @Override // java.util.List
    public Object get(int i) {
        return get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        long j = this.length;
        if (j > 2147483647L) {
            throw new IllegalStateException();
        }
        int i = (int) j;
        int i2 = 0;
        if (obj == null) {
            while (i2 < i) {
                if (get(i2) == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        while (i2 < i) {
            if (obj.equals(get(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        long j = this.length;
        if (j > 2147483647L) {
            throw new IllegalStateException();
        }
        int i = (int) j;
        if (obj == null) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (get(i2) == null) {
                    return i2;
                }
            }
            return -1;
        }
        for (int i3 = i - 1; i3 >= 0; i3--) {
            if (obj.equals(get(i3))) {
                return i3;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator(final int i) {
        long j = this.length;
        if (j > 2147483647L) {
            throw new IllegalStateException();
        }
        final int i2 = (int) j;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException("Index: " + i);
        }
        return new ListIterator() { // from class: org.mozilla.javascript.NativeArray.2
            int cursor;

            {
                this.cursor = i;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.cursor < i2;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public Object next() {
                int i3 = this.cursor;
                if (i3 == i2) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                this.cursor = i3 + 1;
                return nativeArray.get(i3);
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.cursor > 0;
            }

            @Override // java.util.ListIterator
            public Object previous() {
                int i3 = this.cursor;
                if (i3 == 0) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                int i4 = i3 - 1;
                this.cursor = i4;
                return nativeArray.get(i4);
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.cursor;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.cursor - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void add(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void set(Object obj) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.ITERATOR.equals(symbol) ? 26 : 0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class StringLikeComparator implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ScriptRuntime.toString(obj).compareTo(ScriptRuntime.toString(obj2));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class ElementComparator implements Comparator<Object> {
        private final Comparator<Object> child;

        public ElementComparator() {
            this.child = NativeArray.STRING_COMPARATOR;
        }

        public ElementComparator(Comparator<Object> comparator) {
            this.child = comparator;
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == Undefined.instance) {
                if (obj2 == Undefined.instance) {
                    return 0;
                }
                return obj2 == Scriptable.NOT_FOUND ? -1 : 1;
            }
            if (obj == Scriptable.NOT_FOUND) {
                return obj2 == Scriptable.NOT_FOUND ? 0 : 1;
            }
            if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
                return -1;
            }
            return this.child.compare(obj, obj2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0022. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 3;
        if (length == 11) {
            char charAt = str.charAt(0);
            if (charAt == 'c') {
                str2 = "constructor";
                i = 1;
            } else if (charAt == 'l') {
                i = 16;
                str2 = "lastIndexOf";
            } else {
                if (charAt == 'r') {
                    i = 25;
                    str2 = "reduceRight";
                }
                str2 = null;
                i = 0;
            }
        } else if (length != 14) {
            switch (length) {
                case 3:
                    char charAt2 = str.charAt(0);
                    if (charAt2 == 'm') {
                        if (str.charAt(2) == 'p' && str.charAt(1) == 'a') {
                            return 20;
                        }
                    } else if (charAt2 == 'p' && str.charAt(2) == 'p' && str.charAt(1) == 'o') {
                        return 9;
                    }
                    str2 = null;
                    i = 0;
                    break;
                case 4:
                    char charAt3 = str.charAt(2);
                    if (charAt3 == 'i') {
                        i = 5;
                        str2 = "join";
                        break;
                    } else if (charAt3 == 'm') {
                        i = 21;
                        str2 = "some";
                        break;
                    } else if (charAt3 == 'n') {
                        i = 22;
                        str2 = "find";
                        break;
                    } else if (charAt3 == 'r') {
                        i = 7;
                        str2 = "sort";
                        break;
                    } else {
                        if (charAt3 == 's') {
                            i = 8;
                            str2 = IoTConfig.PARAM_PUSH_FILE;
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    }
                case 5:
                    char charAt4 = str.charAt(1);
                    if (charAt4 != 'h') {
                        if (charAt4 != 'l') {
                            if (charAt4 == 'v') {
                                i = 17;
                                str2 = "every";
                                break;
                            }
                            str2 = null;
                            i = 0;
                            break;
                        } else {
                            str2 = "slice";
                            i = 14;
                            break;
                        }
                    } else {
                        i = 10;
                        str2 = "shift";
                        break;
                    }
                case 6:
                    char charAt5 = str.charAt(0);
                    if (charAt5 == 'c') {
                        i = 13;
                        str2 = "concat";
                        break;
                    } else if (charAt5 == 'f') {
                        i = 18;
                        str2 = "filter";
                        break;
                    } else if (charAt5 == 'r') {
                        i = 24;
                        str2 = "reduce";
                        break;
                    } else {
                        if (charAt5 == 's') {
                            i = 12;
                            str2 = "splice";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    }
                case 7:
                    char charAt6 = str.charAt(0);
                    if (charAt6 == 'f') {
                        i = 19;
                        str2 = "forEach";
                        break;
                    } else if (charAt6 == 'i') {
                        i = 15;
                        str2 = "indexOf";
                        break;
                    } else if (charAt6 == 'r') {
                        i = 6;
                        str2 = "reverse";
                        break;
                    } else {
                        if (charAt6 == 'u') {
                            str2 = "unshift";
                            i = 11;
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    }
                case 8:
                    char charAt7 = str.charAt(3);
                    if (charAt7 != 'o') {
                        if (charAt7 == 't') {
                            str2 = "toString";
                            i = 2;
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 4;
                        str2 = "toSource";
                        break;
                    }
                case 9:
                    i = 23;
                    str2 = "findIndex";
                    break;
                default:
                    str2 = null;
                    i = 0;
                    break;
            }
        } else {
            str2 = "toLocaleString";
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
