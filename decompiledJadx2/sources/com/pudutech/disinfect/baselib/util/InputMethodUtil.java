package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class InputMethodUtil {
    public static final String INPUT_NAME_GOKEY = "gokey";
    private static String TAG = "InputMethodUtil";

    public static void setDefaultInputMethod(Context context, String str) {
        String[] inputMethodIdList = getInputMethodIdList(context);
        if (inputMethodIdList == null || inputMethodIdList.length == 0) {
            Log.d(TAG, "found no input method.");
            return;
        }
        String str2 = "";
        for (String str3 : inputMethodIdList) {
            Log.d(TAG, String.format("find : %s", str3));
            if (str3.toLowerCase().contains(str.toLowerCase())) {
                str2 = str3;
            }
        }
        if (str2 == "") {
            Log.d(TAG, "didn't find " + str);
            return;
        }
        Log.d(TAG, String.format("writeDbDefaultInputMethod(%s),result: %s", str2, Boolean.valueOf(Settings.Secure.putString(context.getContentResolver(), "default_input_method", str2))));
        Log.d(TAG, String.format("current default: %s", Settings.Secure.getString(context.getContentResolver(), "default_input_method")));
    }

    public static String[] getInputMethodIdList(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager == null || inputMethodManager.getInputMethodList() == null) {
            return new String[0];
        }
        String[] strArr = new String[inputMethodManager.getInputMethodList().size()];
        for (int i = 0; i < inputMethodManager.getInputMethodList().size(); i++) {
            strArr[i] = inputMethodManager.getInputMethodList().get(i).getId();
        }
        return strArr;
    }

    public static String getDefaultInputMethod(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "default_input_method");
    }

    public static Boolean isGoInput(Context context) {
        if (getDefaultInputMethod(context).toLowerCase().contains("gokey")) {
            return true;
        }
        return false;
    }

    public static Boolean hasInputMethod(Context context, String str) {
        String[] inputMethodIdList = getInputMethodIdList(context);
        if (inputMethodIdList == null || inputMethodIdList.length == 0) {
            Log.d(TAG, "found no input method.");
            return false;
        }
        String str2 = "";
        for (String str3 : inputMethodIdList) {
            Log.d(TAG, String.format("find : %s", str3));
            if (str3.toLowerCase().contains(str.toLowerCase())) {
                str2 = str3;
            }
        }
        if (str2 == "") {
            Log.d(TAG, "didn't find " + str);
            return false;
        }
        return true;
    }

    public static void changeInputMethodIfNeed(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        ThreadPoolManager.getInstance().execSimpleTask(new Runnable() { // from class: com.pudutech.disinfect.baselib.util.InputMethodUtil.1
            @Override // java.lang.Runnable
            public void run() {
                if (InputMethodUtil.getDefaultInputMethod(applicationContext).toLowerCase().contains(str)) {
                    Log.d(InputMethodUtil.TAG, "changeInputMethodIfNeed ,already is " + str);
                    return;
                }
                String[] inputMethodIdList = InputMethodUtil.getInputMethodIdList(applicationContext);
                if (inputMethodIdList == null || inputMethodIdList.length == 0) {
                    Log.e(InputMethodUtil.TAG, "found no input method.");
                    return;
                }
                String str2 = "";
                for (String str3 : inputMethodIdList) {
                    if (str3.toLowerCase().contains(str.toLowerCase())) {
                        str2 = str3;
                    }
                }
                if ("".equals(str2)) {
                    Log.d(InputMethodUtil.TAG, "changeInputMethodIfNeed , not has " + str);
                    return;
                }
                Log.d(InputMethodUtil.TAG, String.format("writeDbDefaultInputMethod(%s),result: %s", str2, Boolean.valueOf(Settings.Secure.putString(applicationContext.getContentResolver(), "default_input_method", str2))));
            }
        });
    }
}
