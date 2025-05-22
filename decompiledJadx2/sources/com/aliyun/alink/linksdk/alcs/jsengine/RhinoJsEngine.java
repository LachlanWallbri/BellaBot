package com.aliyun.alink.linksdk.alcs.jsengine;

import com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0942c;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Arrays;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.json.JsonParser;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RhinoJsEngine implements IJSEngine {
    private static final String TAG = "[AlcsLPBS]RhinoJsEngine";

    private RhinoJsEngine() {
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine
    public String rawDataToProtocol(String str, byte[] bArr) {
        ALog.m479d(TAG, "rawDataToProtocol  params:" + C0942c.m366a(bArr));
        ALog.m479d(TAG, "rawDataToProtocol  params:" + Arrays.toString(bArr));
        Context enter = Context.enter();
        enter.setOptimizationLevel(-1);
        enter.setLanguageVersion(200);
        ScriptableObject initStandardObjects = enter.initStandardObjects();
        String str2 = null;
        try {
            NativeObject nativeObject = (NativeObject) callJsFunction(enter, initStandardObjects, str, IJSEngine.RAWDATA_TO_PROTOCAL_FUNCNAME, new Object[]{new NativeJavaArray(initStandardObjects, bArr)});
            if (nativeObject != null) {
                str2 = new JSONObject(nativeObject).toString();
            }
        } catch (Exception e) {
            ALog.m480e(TAG, e.toString());
        }
        ALog.m479d(TAG, "rawDataToProtocol  result:" + str2);
        Context.exit();
        return str2;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine
    public byte[] protocolToRawData(String str, String str2) {
        ALog.m479d(TAG, "protocolToRawData  params:" + str2);
        Context enter = Context.enter();
        enter.setOptimizationLevel(-1);
        enter.setLanguageVersion(200);
        ScriptableObject initStandardObjects = enter.initStandardObjects();
        byte[] bArr = null;
        try {
            NativeArray nativeArray = (NativeArray) callJsFunction(enter, initStandardObjects, str, IJSEngine.PROTOCAL_TO_RAWDATA_FUNCNAME, new Object[]{new JsonParser(enter, initStandardObjects).parseValue(str2)});
            if (nativeArray != null) {
                bArr = new byte[nativeArray.size()];
                int size = nativeArray.size();
                for (int i = 0; i < size; i++) {
                    bArr[i] = (byte) ((Integer) nativeArray.get(i)).intValue();
                }
            }
        } catch (Exception e) {
            ALog.m480e(TAG, e.toString());
        }
        Context.exit();
        return bArr;
    }

    public Object callJsFunction(Context context, ScriptableObject scriptableObject, String str, String str2, Object[] objArr) {
        context.evaluateString(scriptableObject, str, null, 1, null);
        return ((Function) scriptableObject.get(str2, scriptableObject)).call(context, scriptableObject, scriptableObject, objArr);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class InstanceHolder {
        private static RhinoJsEngine mInstance = new RhinoJsEngine();

        private InstanceHolder() {
        }
    }

    public static RhinoJsEngine getInstance() {
        return InstanceHolder.mInstance;
    }
}
