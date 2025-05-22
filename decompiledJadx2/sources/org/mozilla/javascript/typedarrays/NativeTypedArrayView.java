package org.mozilla.javascript.typedarrays;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ExternalArrayData;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeArrayIterator;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Symbol;
import org.mozilla.javascript.SymbolKey;
import org.mozilla.javascript.Undefined;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class NativeTypedArrayView<T> extends NativeArrayBufferView implements List<T>, RandomAccess, ExternalArrayData {
    private static final int Id_BYTES_PER_ELEMENT = 11;
    private static final int Id_constructor = 1;
    private static final int Id_get = 2;
    private static final int Id_length = 10;
    private static final int Id_set = 3;
    private static final int Id_subarray = 4;
    private static final int MAX_INSTANCE_ID = 11;
    protected static final int MAX_PROTOTYPE_ID = 5;
    private static final int SymbolId_iterator = 5;
    protected final int length;

    protected abstract NativeTypedArrayView construct(NativeArrayBuffer nativeArrayBuffer, int i, int i2);

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
    }

    public abstract int getBytesPerElement();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.typedarrays.NativeArrayBufferView, org.mozilla.javascript.IdScriptableObject
    public int getMaxInstanceId() {
        return 11;
    }

    protected abstract Object js_get(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object js_set(int i, Object obj);

    protected abstract NativeTypedArrayView realThis(Scriptable scriptable, IdFunctionObject idFunctionObject);

    /* JADX INFO: Access modifiers changed from: protected */
    public NativeTypedArrayView() {
        this.length = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NativeTypedArrayView(NativeArrayBuffer nativeArrayBuffer, int i, int i2, int i3) {
        super(nativeArrayBuffer, i, i3);
        this.length = i2;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        return js_get(i);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        return i > 0 && i < this.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        js_set(i, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = Integer.valueOf(i);
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkIndex(int i) {
        return i < 0 || i >= this.length;
    }

    private NativeArrayBuffer makeArrayBuffer(Context context, Scriptable scriptable, int i) {
        return (NativeArrayBuffer) context.newObject(scriptable, NativeArrayBuffer.CLASS_NAME, new Object[]{Integer.valueOf(i)});
    }

    private NativeTypedArrayView js_constructor(Context context, Scriptable scriptable, Object[] objArr) {
        int length;
        if (!isArg(objArr, 0)) {
            return construct(NativeArrayBuffer.EMPTY_BUFFER, 0, 0);
        }
        if ((objArr[0] instanceof Number) || (objArr[0] instanceof String)) {
            int int32 = ScriptRuntime.toInt32(objArr[0]);
            return construct(makeArrayBuffer(context, scriptable, getBytesPerElement() * int32), 0, int32);
        }
        if (objArr[0] instanceof NativeTypedArrayView) {
            NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) objArr[0];
            NativeTypedArrayView construct = construct(makeArrayBuffer(context, scriptable, nativeTypedArrayView.length * getBytesPerElement()), 0, nativeTypedArrayView.length);
            while (r0 < nativeTypedArrayView.length) {
                construct.js_set(r0, nativeTypedArrayView.js_get(r0));
                r0++;
            }
            return construct;
        }
        if (objArr[0] instanceof NativeArrayBuffer) {
            NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) objArr[0];
            r0 = isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0;
            if (isArg(objArr, 2)) {
                length = ScriptRuntime.toInt32(objArr[2]) * getBytesPerElement();
            } else {
                length = nativeArrayBuffer.getLength() - r0;
            }
            if (r0 < 0 || r0 > nativeArrayBuffer.buffer.length) {
                throw ScriptRuntime.constructError("RangeError", "offset out of range");
            }
            if (length < 0 || r0 + length > nativeArrayBuffer.buffer.length) {
                throw ScriptRuntime.constructError("RangeError", "length out of range");
            }
            if (r0 % getBytesPerElement() != 0) {
                throw ScriptRuntime.constructError("RangeError", "offset must be a multiple of the byte size");
            }
            if (length % getBytesPerElement() != 0) {
                throw ScriptRuntime.constructError("RangeError", "offset and buffer must be a multiple of the byte size");
            }
            return construct(nativeArrayBuffer, r0, length / getBytesPerElement());
        }
        if (objArr[0] instanceof NativeArray) {
            List list = (List) objArr[0];
            NativeTypedArrayView construct2 = construct(makeArrayBuffer(context, scriptable, list.size() * getBytesPerElement()), 0, list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                construct2.js_set(r0, it.next());
                r0++;
            }
            return construct2;
        }
        throw ScriptRuntime.constructError(MoveError.LEVEL_ERROR, "invalid argument");
    }

    private void setRange(NativeTypedArrayView nativeTypedArrayView, int i) {
        int i2 = this.length;
        if (i >= i2) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
        if (nativeTypedArrayView.length > i2 - i) {
            throw ScriptRuntime.constructError("RangeError", "source array too long");
        }
        int i3 = 0;
        if (nativeTypedArrayView.arrayBuffer == this.arrayBuffer) {
            Object[] objArr = new Object[nativeTypedArrayView.length];
            for (int i4 = 0; i4 < nativeTypedArrayView.length; i4++) {
                objArr[i4] = nativeTypedArrayView.js_get(i4);
            }
            while (i3 < nativeTypedArrayView.length) {
                js_set(i3 + i, objArr[i3]);
                i3++;
            }
            return;
        }
        while (i3 < nativeTypedArrayView.length) {
            js_set(i3 + i, nativeTypedArrayView.js_get(i3));
            i3++;
        }
    }

    private void setRange(NativeArray nativeArray, int i) {
        if (i > this.length) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
        if (nativeArray.size() + i > this.length) {
            throw ScriptRuntime.constructError("RangeError", "offset + length out of range");
        }
        Iterator it = nativeArray.iterator();
        while (it.hasNext()) {
            js_set(i, it.next());
            i++;
        }
    }

    private Object js_subarray(Context context, Scriptable scriptable, int i, int i2) {
        if (i < 0) {
            i += this.length;
        }
        if (i2 < 0) {
            i2 += this.length;
        }
        int max = Math.max(0, i);
        int max2 = Math.max(0, Math.min(this.length, i2) - max);
        return context.newObject(scriptable, getClassName(), new Object[]{this.arrayBuffer, Integer.valueOf(Math.min(max * getBytesPerElement(), this.arrayBuffer.getLength())), Integer.valueOf(max2)});
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(getClassName())) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return js_constructor(context, scriptable, objArr);
        }
        if (methodId == 2) {
            if (objArr.length > 0) {
                return realThis(scriptable2, idFunctionObject).js_get(ScriptRuntime.toInt32(objArr[0]));
            }
            throw ScriptRuntime.constructError(MoveError.LEVEL_ERROR, "invalid arguments");
        }
        if (methodId != 3) {
            if (methodId != 4) {
                if (methodId == 5) {
                    return new NativeArrayIterator(scriptable, scriptable2);
                }
                throw new IllegalArgumentException(String.valueOf(methodId));
            }
            if (objArr.length > 0) {
                NativeTypedArrayView realThis = realThis(scriptable2, idFunctionObject);
                return realThis.js_subarray(context, scriptable, ScriptRuntime.toInt32(objArr[0]), isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : realThis.length);
            }
            throw ScriptRuntime.constructError(MoveError.LEVEL_ERROR, "invalid arguments");
        }
        if (objArr.length > 0) {
            NativeTypedArrayView realThis2 = realThis(scriptable2, idFunctionObject);
            if (objArr[0] instanceof NativeTypedArrayView) {
                realThis2.setRange((NativeTypedArrayView) objArr[0], isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0);
                return Undefined.instance;
            }
            if (objArr[0] instanceof NativeArray) {
                realThis2.setRange((NativeArray) objArr[0], isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0);
                return Undefined.instance;
            }
            if (objArr[0] instanceof Scriptable) {
                return Undefined.instance;
            }
            if (isArg(objArr, 2)) {
                return realThis2.js_set(ScriptRuntime.toInt32(objArr[0]), objArr[1]);
            }
        }
        throw ScriptRuntime.constructError(MoveError.LEVEL_ERROR, "invalid arguments");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2;
        String str3;
        if (i == 5) {
            initPrototypeMethod(getClassName(), i, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
        if (i == 1) {
            str = "constructor";
        } else {
            if (i != 2) {
                if (i == 3) {
                    str3 = TmpConstant.PROPERTY_IDENTIFIER_SET;
                } else {
                    if (i != 4) {
                        throw new IllegalArgumentException(String.valueOf(i));
                    }
                    str3 = "subarray";
                }
                i2 = 2;
                str2 = str3;
                initPrototypeMethod(getClassName(), i, str2, (String) null, i2);
            }
            str = TmpConstant.PROPERTY_IDENTIFIER_GET;
        }
        str2 = str;
        i2 = 1;
        initPrototypeMethod(getClassName(), i, str2, (String) null, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.ITERATOR.equals(symbol) ? 5 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 3) {
            char charAt = str.charAt(0);
            if (charAt == 'g') {
                if (str.charAt(2) == 't' && str.charAt(1) == 'e') {
                    return 2;
                }
            } else if (charAt == 's' && str.charAt(2) == 't' && str.charAt(1) == 'e') {
                return 3;
            }
        } else {
            if (length == 8) {
                i = 4;
                str2 = "subarray";
            } else if (length == 11) {
                str2 = "constructor";
                i = 1;
            }
            if (str2 != null || str2 == str || str2.equals(str)) {
                return i;
            }
            return 0;
        }
        str2 = null;
        i = 0;
        if (str2 != null) {
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.put("BYTES_PER_ELEMENT", idFunctionObject, ScriptRuntime.wrapInt(getBytesPerElement()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.typedarrays.NativeArrayBufferView, org.mozilla.javascript.IdScriptableObject
    public String getInstanceIdName(int i) {
        return i != 10 ? i != 11 ? super.getInstanceIdName(i) : "BYTES_PER_ELEMENT" : Name.LENGTH;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.typedarrays.NativeArrayBufferView, org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 10) {
            return ScriptRuntime.wrapInt(this.length);
        }
        if (i == 11) {
            return ScriptRuntime.wrapInt(getBytesPerElement());
        }
        return super.getInstanceIdValue(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.typedarrays.NativeArrayBufferView, org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 6) {
            i = 10;
            str2 = Name.LENGTH;
        } else if (length == 17) {
            i = 11;
            str2 = "BYTES_PER_ELEMENT";
        } else {
            str2 = null;
            i = 0;
        }
        int i2 = (str2 == null || str2 == str || str2.equals(str)) ? i : 0;
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        return instanceIdInfo(5, i2);
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public Object getArrayElement(int i) {
        return js_get(i);
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public void setArrayElement(int i, Object obj) {
        js_set(i, obj);
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public int getArrayLength() {
        return this.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public int size() {
        return this.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = js_get(i);
        }
        return objArr;
    }

    @Override // java.util.List, java.util.Collection
    public <U> U[] toArray(U[] uArr) {
        if (uArr.length < this.length) {
            uArr = (U[]) ((Object[]) Array.newInstance(uArr.getClass().getComponentType(), this.length));
        }
        for (int i = 0; i < this.length; i++) {
            try {
                uArr[i] = js_get(i);
            } catch (ClassCastException unused) {
                throw new ArrayStoreException();
            }
        }
        return uArr;
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        try {
            NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) obj;
            if (this.length != nativeTypedArrayView.length) {
                return false;
            }
            for (int i = 0; i < this.length; i++) {
                if (!js_get(i).equals(nativeTypedArrayView.js_get(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            i += js_get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return new NativeTypedArrayIterator(this, i);
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
