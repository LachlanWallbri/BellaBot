package com.iflytek.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3692ad;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

/* loaded from: classes3.dex */
abstract class SpeechModuleAidl<I extends IInterface> implements ISpeechModule {
    private String mBindAction;
    protected Context mContext;
    private InitListener mInitListener;
    protected I mService;
    protected Object mSynLock = new Object();
    private ServiceConnection mConnection = null;
    private HashMap<String, String> mParams = new HashMap<>();
    private volatile boolean userDestroy = false;
    private Handler mUiHandler = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.speech.SpeechModuleAidl.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SpeechModuleAidl.this.mInitListener == null) {
                return;
            }
            SpeechModuleAidl.this.mInitListener.onInit(message.what);
        }
    };

    public SpeechModuleAidl(Context context, InitListener initListener, String str) {
        this.mContext = null;
        this.mInitListener = null;
        this.mBindAction = null;
        this.mContext = context;
        this.mInitListener = initListener;
        this.mBindAction = str;
        bindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        if (!isActionInstalled(this.mContext, this.mBindAction)) {
            if (this.mInitListener != null) {
                Message.obtain(this.mUiHandler, 21001, 0, 0, null).sendToTarget();
                return;
            }
            return;
        }
        Intent intent = getIntent();
        intent.setAction(this.mBindAction);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        this.mConnection = new ServiceConnection() { // from class: com.iflytek.speech.SpeechModuleAidl.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (SpeechModuleAidl.this.mSynLock) {
                    Log.d(SpeechModuleAidl.this.getTag(), "init success");
                    SpeechModuleAidl.this.mService = (I) SpeechModuleAidl.this.getService(iBinder);
                    Log.d(SpeechModuleAidl.this.getTag(), "mService :" + SpeechModuleAidl.this.mService);
                    if (SpeechModuleAidl.this.mInitListener != null) {
                        Message.obtain(SpeechModuleAidl.this.mUiHandler, 0, 0, 0, null).sendToTarget();
                    }
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(SpeechModuleAidl.this.getTag(), "onServiceDisconnected");
                SpeechModuleAidl speechModuleAidl = SpeechModuleAidl.this;
                speechModuleAidl.mService = null;
                if (speechModuleAidl.userDestroy) {
                    return;
                }
                try {
                    SpeechModuleAidl.this.bindService();
                } catch (Exception e) {
                    Log.e(SpeechModuleAidl.this.getTag(), "rebindService error = " + e.toString());
                }
            }
        };
        try {
            this.mContext.bindService(intent, this.mConnection, 1);
        } catch (SecurityException e) {
            DebugLog.LogE(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public I getService(IBinder iBinder) {
        try {
            String name = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
            Log.d(getTag(), "className = " + name);
            return (I) Class.forName(name + "$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, iBinder);
        } catch (ClassNotFoundException e) {
            DebugLog.LogE(e);
            return null;
        } catch (IllegalAccessException e2) {
            DebugLog.LogE(e2);
            return null;
        } catch (IllegalArgumentException e3) {
            DebugLog.LogE(e3);
            return null;
        } catch (NoSuchMethodException e4) {
            DebugLog.LogE(e4);
            return null;
        } catch (SecurityException e5) {
            DebugLog.LogE(e5);
            return null;
        } catch (InvocationTargetException e6) {
            DebugLog.LogE(e6);
            return null;
        } catch (Exception e7) {
            DebugLog.LogE(e7);
            return null;
        }
    }

    @Override // com.iflytek.speech.ISpeechModule
    public boolean isAvailable() {
        return this.mService != null;
    }

    @Override // com.iflytek.speech.ISpeechModule
    public int setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 20012;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mParams.remove(str);
            return 0;
        }
        this.mParams.put(str, str2);
        return 0;
    }

    @Override // com.iflytek.speech.ISpeechModule
    public String getParameter(String str) {
        return this.mParams.get(str);
    }

    @Override // com.iflytek.speech.ISpeechModule
    public Intent getIntent() {
        Intent intent = new Intent();
        if (!this.mParams.isEmpty()) {
            for (String str : this.mParams.keySet()) {
                intent.putExtra(str, this.mParams.get(str));
            }
            HashMap<String, String> m1830c = new C3692ad(this.mParams.get("params"), (String[][]) null).m1830c();
            if (m1830c != null && !m1830c.isEmpty()) {
                for (String str2 : m1830c.keySet()) {
                    intent.putExtra(str2, m1830c.get(str2));
                }
            }
        }
        intent.putExtra(UtilityConfig.KEY_CALLER_APPID, SpeechUtility.getUtility().getParameter("appid"));
        intent.putExtra(UtilityConfig.KEY_CALLER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_PKG_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_PKG_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_CODE, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_CODE));
        return intent;
    }

    public boolean isActionInstalled(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || context.getPackageManager().resolveService(new Intent(str), 0) == null) ? false : true;
    }

    @Override // com.iflytek.speech.ISpeechModule
    public boolean destory() {
        Log.d(getTag(), "destory");
        try {
            this.userDestroy = true;
            if (this.mConnection != null) {
                this.mContext.unbindService(this.mConnection);
                this.mConnection = null;
            }
            return true;
        } catch (IllegalArgumentException e) {
            DebugLog.LogE(e);
            return false;
        }
    }

    protected final String getTag() {
        return getClass().toString();
    }
}
