package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeDataView extends NativeArrayBufferView {
    public static final String CLASS_NAME = "DataView";
    private static final int Id_constructor = 1;
    private static final int Id_getFloat32 = 8;
    private static final int Id_getFloat64 = 9;
    private static final int Id_getInt16 = 4;
    private static final int Id_getInt32 = 6;
    private static final int Id_getInt8 = 2;
    private static final int Id_getUint16 = 5;
    private static final int Id_getUint32 = 7;
    private static final int Id_getUint8 = 3;
    private static final int Id_setFloat32 = 16;
    private static final int Id_setFloat64 = 17;
    private static final int Id_setInt16 = 12;
    private static final int Id_setInt32 = 14;
    private static final int Id_setInt8 = 10;
    private static final int Id_setUint16 = 13;
    private static final int Id_setUint32 = 15;
    private static final int Id_setUint8 = 11;
    private static final int MAX_PROTOTYPE_ID = 17;
    private static final long serialVersionUID = 1427967607557438968L;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CLASS_NAME;
    }

    public NativeDataView() {
    }

    public NativeDataView(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        super(nativeArrayBuffer, i, i2);
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new NativeDataView().exportAsJSClass(17, scriptable, z);
    }

    private void rangeCheck(int i, int i2) {
        if (i < 0 || i + i2 > this.byteLength) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
    }

    private void checkOffset(Object[] objArr, int i) {
        if (objArr.length <= i) {
            throw ScriptRuntime.constructError("TypeError", "missing required offset parameter");
        }
        if (Undefined.instance.equals(objArr[i])) {
            throw ScriptRuntime.constructError("RangeError", "invalid offset");
        }
    }

    private void checkValue(Object[] objArr, int i) {
        if (objArr.length <= i) {
            throw ScriptRuntime.constructError("TypeError", "missing required value parameter");
        }
        if (Undefined.instance.equals(objArr[i])) {
            throw ScriptRuntime.constructError("RangeError", "invalid value parameter");
        }
    }

    private static NativeDataView realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (!(scriptable instanceof NativeDataView)) {
            throw incompatibleCallError(idFunctionObject);
        }
        return (NativeDataView) scriptable;
    }

    private NativeDataView js_constructor(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        if (i2 < 0) {
            throw ScriptRuntime.constructError("RangeError", "length out of range");
        }
        if (i < 0 || i + i2 > nativeArrayBuffer.getLength()) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
        return new NativeDataView(nativeArrayBuffer, i, i2);
    }

    private Object js_getInt(int i, boolean z, Object[] objArr) {
        boolean z2 = false;
        checkOffset(objArr, 0);
        int int32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(int32, i);
        if (isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1])) {
            z2 = true;
        }
        if (i == 1) {
            byte[] bArr = this.arrayBuffer.buffer;
            return z ? ByteIo.readInt8(bArr, this.offset + int32) : ByteIo.readUint8(bArr, this.offset + int32);
        }
        if (i == 2) {
            byte[] bArr2 = this.arrayBuffer.buffer;
            return z ? ByteIo.readInt16(bArr2, this.offset + int32, z2) : ByteIo.readUint16(bArr2, this.offset + int32, z2);
        }
        if (i == 4) {
            byte[] bArr3 = this.arrayBuffer.buffer;
            return z ? ByteIo.readInt32(bArr3, this.offset + int32, z2) : ByteIo.readUint32(bArr3, this.offset + int32, z2);
        }
        throw new AssertionError();
    }

    private Object js_getFloat(int i, Object[] objArr) {
        boolean z = false;
        checkOffset(objArr, 0);
        int int32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(int32, i);
        if (isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1])) {
            z = true;
        }
        if (i == 4) {
            return ByteIo.readFloat32(this.arrayBuffer.buffer, this.offset + int32, z);
        }
        if (i == 8) {
            return ByteIo.readFloat64(this.arrayBuffer.buffer, this.offset + int32, z);
        }
        throw new AssertionError();
    }

    private void js_setInt(int i, boolean z, Object[] objArr) {
        boolean z2 = false;
        checkOffset(objArr, 0);
        checkValue(objArr, 1);
        int int32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(int32, i);
        if (isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2])) {
            z2 = true;
        }
        if (i == 1) {
            if (z) {
                ByteIo.writeInt8(this.arrayBuffer.buffer, this.offset + int32, Conversions.toInt8(objArr[1]));
                return;
            } else {
                ByteIo.writeUint8(this.arrayBuffer.buffer, this.offset + int32, Conversions.toUint8(objArr[1]));
                return;
            }
        }
        if (i == 2) {
            if (z) {
                ByteIo.writeInt16(this.arrayBuffer.buffer, this.offset + int32, Conversions.toInt16(objArr[1]), z2);
                return;
            } else {
                ByteIo.writeUint16(this.arrayBuffer.buffer, this.offset + int32, Conversions.toUint16(objArr[1]), z2);
                return;
            }
        }
        if (i != 4) {
            throw new AssertionError();
        }
        if (z) {
            ByteIo.writeInt32(this.arrayBuffer.buffer, this.offset + int32, Conversions.toInt32(objArr[1]), z2);
        } else {
            ByteIo.writeUint32(this.arrayBuffer.buffer, this.offset + int32, Conversions.toUint32(objArr[1]), z2);
        }
    }

    private void js_setFloat(int i, Object[] objArr) {
        boolean z = false;
        checkOffset(objArr, 0);
        checkValue(objArr, 1);
        int int32 = ScriptRuntime.toInt32(objArr[0]);
        rangeCheck(int32, i);
        if (isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2])) {
            z = true;
        }
        double number = ScriptRuntime.toNumber(objArr[1]);
        if (i == 4) {
            ByteIo.writeFloat32(this.arrayBuffer.buffer, this.offset + int32, number, z);
        } else {
            if (i == 8) {
                ByteIo.writeFloat64(this.arrayBuffer.buffer, this.offset + int32, number, z);
                return;
            }
            throw new AssertionError();
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(getClassName())) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                if (isArg(objArr, 0) && (objArr[0] instanceof NativeArrayBuffer)) {
                    NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) objArr[0];
                    int int32 = isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0;
                    return js_constructor(nativeArrayBuffer, int32, isArg(objArr, 2) ? ScriptRuntime.toInt32(objArr[2]) : nativeArrayBuffer.getLength() - int32);
                }
                throw ScriptRuntime.constructError("TypeError", "Missing parameters");
            case 2:
                return realThis(scriptable2, idFunctionObject).js_getInt(1, true, objArr);
            case 3:
                return realThis(scriptable2, idFunctionObject).js_getInt(1, false, objArr);
            case 4:
                return realThis(scriptable2, idFunctionObject).js_getInt(2, true, objArr);
            case 5:
                return realThis(scriptable2, idFunctionObject).js_getInt(2, false, objArr);
            case 6:
                return realThis(scriptable2, idFunctionObject).js_getInt(4, true, objArr);
            case 7:
                return realThis(scriptable2, idFunctionObject).js_getInt(4, false, objArr);
            case 8:
                return realThis(scriptable2, idFunctionObject).js_getFloat(4, objArr);
            case 9:
                return realThis(scriptable2, idFunctionObject).js_getFloat(8, objArr);
            case 10:
                realThis(scriptable2, idFunctionObject).js_setInt(1, true, objArr);
                return Undefined.instance;
            case 11:
                realThis(scriptable2, idFunctionObject).js_setInt(1, false, objArr);
                return Undefined.instance;
            case 12:
                realThis(scriptable2, idFunctionObject).js_setInt(2, true, objArr);
                return Undefined.instance;
            case 13:
                realThis(scriptable2, idFunctionObject).js_setInt(2, false, objArr);
                return Undefined.instance;
            case 14:
                realThis(scriptable2, idFunctionObject).js_setInt(4, true, objArr);
                return Undefined.instance;
            case 15:
                realThis(scriptable2, idFunctionObject).js_setInt(4, false, objArr);
                return Undefined.instance;
            case 16:
                realThis(scriptable2, idFunctionObject).js_setFloat(4, objArr);
                return Undefined.instance;
            case 17:
                realThis(scriptable2, idFunctionObject).js_setFloat(8, objArr);
                return Undefined.instance;
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0002. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 2;
        switch (i) {
            case 1:
                str = "constructor";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 2:
                str = "getInt8";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 3:
                str = "getUint8";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 4:
                str = "getInt16";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 5:
                str = "getUint16";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 6:
                str = "getInt32";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 7:
                str = "getUint32";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 8:
                str = "getFloat32";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 9:
                str = "getFloat64";
                str2 = str;
                i2 = 1;
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 10:
                str2 = "setInt8";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 11:
                str2 = "setUint8";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 12:
                str2 = "setInt16";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 13:
                str2 = "setUint16";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 14:
                str2 = "setInt32";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 15:
                str2 = "setUint32";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 16:
                str2 = "setFloat32";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            case 17:
                str2 = "setFloat64";
                initPrototypeMethod(getClassName(), i, str2, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int i = 6;
        switch (str.length()) {
            case 7:
                char charAt = str.charAt(0);
                if (charAt != 'g') {
                    if (charAt == 's') {
                        i = 10;
                        str2 = "setInt8";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 2;
                    str2 = "getInt8";
                    break;
                }
            case 8:
                char charAt2 = str.charAt(6);
                if (charAt2 == '1') {
                    char charAt3 = str.charAt(0);
                    if (charAt3 != 'g') {
                        if (charAt3 == 's') {
                            i = 12;
                            str2 = "setInt16";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 4;
                        str2 = "getInt16";
                        break;
                    }
                } else if (charAt2 == '3') {
                    char charAt4 = str.charAt(0);
                    if (charAt4 == 'g') {
                        str2 = "getInt32";
                        break;
                    } else {
                        if (charAt4 == 's') {
                            i = 14;
                            str2 = "setInt32";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else {
                    if (charAt2 == 't') {
                        char charAt5 = str.charAt(0);
                        if (charAt5 != 'g') {
                            if (charAt5 == 's') {
                                i = 11;
                                str2 = "setUint8";
                                break;
                            }
                        } else {
                            i = 3;
                            str2 = "getUint8";
                            break;
                        }
                    }
                    str2 = null;
                    i = 0;
                }
            case 9:
                char charAt6 = str.charAt(0);
                if (charAt6 == 'g') {
                    char charAt7 = str.charAt(8);
                    if (charAt7 != '2') {
                        if (charAt7 == '6') {
                            i = 5;
                            str2 = "getUint16";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 7;
                        str2 = "getUint32";
                        break;
                    }
                } else {
                    if (charAt6 == 's') {
                        char charAt8 = str.charAt(8);
                        if (charAt8 != '2') {
                            if (charAt8 == '6') {
                                i = 13;
                                str2 = "setUint16";
                                break;
                            }
                        } else {
                            i = 15;
                            str2 = "setUint32";
                            break;
                        }
                    }
                    str2 = null;
                    i = 0;
                }
            case 10:
                char charAt9 = str.charAt(0);
                if (charAt9 == 'g') {
                    char charAt10 = str.charAt(9);
                    if (charAt10 != '2') {
                        if (charAt10 == '4') {
                            str2 = "getFloat64";
                            i = 9;
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        str2 = "getFloat32";
                        i = 8;
                        break;
                    }
                } else {
                    if (charAt9 == 's') {
                        char charAt11 = str.charAt(9);
                        if (charAt11 != '2') {
                            if (charAt11 == '4') {
                                i = 17;
                                str2 = "setFloat64";
                                break;
                            }
                        } else {
                            i = 16;
                            str2 = "setFloat32";
                            break;
                        }
                    }
                    str2 = null;
                    i = 0;
                }
            case 11:
                i = 1;
                str2 = "constructor";
                break;
            default:
                str2 = null;
                i = 0;
                break;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
