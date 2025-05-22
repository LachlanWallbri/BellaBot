package org.mozilla.javascript.typedarrays;

import com.iflytek.cloud.SpeechEvent;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class NativeArrayBufferView extends IdScriptableObject {
    private static final int Id_buffer = 1;
    private static final int Id_byteLength = 3;
    private static final int Id_byteOffset = 2;
    private static final int MAX_INSTANCE_ID = 3;
    private static final long serialVersionUID = 6884475582973958419L;
    protected final NativeArrayBuffer arrayBuffer;
    protected final int byteLength;
    protected final int offset;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int getMaxInstanceId() {
        return 3;
    }

    public NativeArrayBufferView() {
        this.arrayBuffer = NativeArrayBuffer.EMPTY_BUFFER;
        this.offset = 0;
        this.byteLength = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NativeArrayBufferView(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        this.offset = i;
        this.byteLength = i2;
        this.arrayBuffer = nativeArrayBuffer;
    }

    public NativeArrayBuffer getBuffer() {
        return this.arrayBuffer;
    }

    public int getByteOffset() {
        return this.offset;
    }

    public int getByteLength() {
        return this.byteLength;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isArg(Object[] objArr, int i) {
        return objArr.length > i && !Undefined.instance.equals(objArr[i]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public String getInstanceIdName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? super.getInstanceIdName(i) : "byteLength" : "byteOffset" : SpeechEvent.KEY_EVENT_TTS_BUFFER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return this.arrayBuffer;
        }
        if (i == 2) {
            return ScriptRuntime.wrapInt(this.offset);
        }
        if (i == 3) {
            return ScriptRuntime.wrapInt(this.byteLength);
        }
        return super.getInstanceIdValue(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 6) {
            i = 1;
            str2 = SpeechEvent.KEY_EVENT_TTS_BUFFER;
        } else {
            if (length == 10) {
                char charAt = str.charAt(4);
                if (charAt == 'L') {
                    i = 3;
                    str2 = "byteLength";
                } else if (charAt == 'O') {
                    i = 2;
                    str2 = "byteOffset";
                }
            }
            str2 = null;
            i = 0;
        }
        int i2 = (str2 == null || str2 == str || str2.equals(str)) ? i : 0;
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        return instanceIdInfo(5, i2);
    }
}
