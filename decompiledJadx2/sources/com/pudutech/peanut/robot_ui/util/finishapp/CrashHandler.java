package com.pudutech.peanut.robot_ui.util.finishapp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.ProcessUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes5.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private static CrashHandler mCrashHandler;
    private static String mCrashTip;
    private static Toast mCustomToast;
    private boolean hasToast;
    public Application mApplication;
    private Class mClassOfFirstActivity;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private boolean mIsDebug;
    private boolean mIsRestartApp;
    private long mRestartTime;
    ControlActivityLifecycleCallbacks mControlActivityLifecycleCallbacks = new ControlActivityLifecycleCallbacks();
    private Map<String, String> infos = new HashMap();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            mCrashHandler = new CrashHandler();
        }
        return mCrashHandler;
    }

    public static void setCloseAnimation(int i) {
        ControlActivityLifecycleCallbacks.sAnimationId = i;
    }

    public static void setCustomToast(Toast toast) {
        mCustomToast = toast;
    }

    public static void setCrashTip(String str) {
        mCrashTip = str;
    }

    public void init(Application application, boolean z, boolean z2, long j, Class cls) {
        this.mIsRestartApp = z2;
        this.mRestartTime = j;
        this.mClassOfFirstActivity = cls;
        initCrashHandler(application, z);
        mCrashTip = application.getString(C5508R.string.app_crash);
    }

    public void init(Application application, boolean z) {
        initCrashHandler(application, z);
    }

    private void initCrashHandler(Application application, boolean z) {
        this.mIsDebug = z;
        this.mApplication = application;
        this.mApplication.registerActivityLifecycleCallbacks(this.mControlActivityLifecycleCallbacks);
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(2800L);
        } catch (InterruptedException e) {
            Log.e("CrashHandler", "uncaughtException() InterruptedException:" + e);
        }
        if (this.mIsRestartApp) {
            AlarmManager alarmManager = (AlarmManager) this.mApplication.getSystemService(NotificationCompat.CATEGORY_ALARM);
            try {
                Intent intent = new Intent(this.mApplication, (Class<?>) this.mClassOfFirstActivity);
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                alarmManager.set(1, System.currentTimeMillis() + this.mRestartTime, PendingIntent.getActivity(this.mApplication, 0, intent, 1073741824));
            } catch (Exception e2) {
                Log.e("CrashHandler", "first class error:" + e2);
            }
        }
        this.mControlActivityLifecycleCallbacks.removeAllActivities();
        try {
            if (ProcessUtils.isMainProcess(this.mApplication)) {
                RobotContext.INSTANCE.killMirSdk(this.mApplication);
            }
        } catch (Throwable th2) {
            Log.e("CrashHandler", th2.getLocalizedMessage());
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
        System.gc();
    }

    private boolean handleException(Throwable th) {
        if (!this.hasToast) {
            new Thread(new Runnable() { // from class: com.pudutech.peanut.robot_ui.util.finishapp.CrashHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    Toast toast;
                    try {
                        Looper.prepare();
                        if (CrashHandler.mCustomToast == null) {
                            toast = Toast.makeText(CrashHandler.this.mApplication, CrashHandler.mCrashTip, 1);
                            toast.setGravity(17, 0, 0);
                        } else {
                            toast = CrashHandler.mCustomToast;
                        }
                        toast.show();
                        Looper.loop();
                        CrashHandler.this.hasToast = true;
                    } catch (Exception e) {
                        Log.e("CrashHandler", "handleException Toast error" + e);
                    }
                }
            }).start();
        }
        if (th == null) {
            return false;
        }
        if (!this.mIsDebug) {
            return true;
        }
        collectDeviceInfo();
        saveCatchInfo2File(th);
        return true;
    }

    public void collectDeviceInfo() {
        try {
            PackageInfo packageInfo = this.mApplication.getPackageManager().getPackageInfo(this.mApplication.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                this.infos.put("versionName", str);
                this.infos.put("versionCode", str2);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("CrashHandler", "collectDeviceInfo() an error occured when collect package info NameNotFoundException:");
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
                Log.i("CrashHandler", field.getName() + " : " + field.get(null));
            } catch (Exception unused2) {
                Log.e("CrashHandler", "collectDeviceInfo() an error occured when collect crash info Exception:");
            }
        }
    }

    private String saveCatchInfo2File(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("------------------------start------------------------------\n");
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        stringBuffer.append(getCrashInfo(th));
        stringBuffer.append("\n------------------------end------------------------------");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String str = "crash-" + this.formatter.format(new Date()) + "-" + currentTimeMillis + ".txt";
            if (Environment.getExternalStorageState().equals("mounted")) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "crash/";
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str2 + str);
                fileOutputStream.write(stringBuffer.toString().getBytes());
                LogcatCrashInfo(str2 + str);
                fileOutputStream.close();
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CrashHandler", "saveCatchInfo2File() an error occured while writing file... Exception:");
            return null;
        }
    }

    private void LogcatCrashInfo(String str) {
        FileInputStream fileInputStream;
        String readLine;
        if (!new File(str).exists()) {
            Log.e("CrashHandler", "LogcatCrashInfo() 日志文件不存在");
            return;
        }
        BufferedReader bufferedReader = null;
        r0 = null;
        r0 = null;
        BufferedReader bufferedReader2 = null;
        BufferedReader bufferedReader3 = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));
                        while (true) {
                            try {
                                readLine = bufferedReader4.readLine();
                                if (readLine == null) {
                                    break;
                                } else {
                                    Log.e("CrashHandler", readLine);
                                }
                            } catch (FileNotFoundException e) {
                                bufferedReader2 = bufferedReader4;
                                e = e;
                                e.printStackTrace();
                                bufferedReader2.close();
                                fileInputStream.close();
                                bufferedReader = bufferedReader2;
                            } catch (IOException e2) {
                                bufferedReader3 = bufferedReader4;
                                e = e2;
                                e.printStackTrace();
                                bufferedReader3.close();
                                fileInputStream.close();
                                bufferedReader = bufferedReader3;
                            } catch (Throwable th) {
                                bufferedReader = bufferedReader4;
                                th = th;
                                try {
                                    bufferedReader.close();
                                    fileInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                throw th;
                            }
                        }
                        bufferedReader4.close();
                        fileInputStream.close();
                        bufferedReader = readLine;
                    } catch (FileNotFoundException e4) {
                        e = e4;
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    fileInputStream = null;
                } catch (IOException e7) {
                    e = e7;
                    fileInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public String getCrashInfo(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.setStackTrace(th.getStackTrace());
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
}
