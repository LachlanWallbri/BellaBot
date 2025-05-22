package com.pudutech.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jetbrains.anko.DimensionsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class Pdlog {

    /* renamed from: A */
    public static final int f4482A = 7;
    private static final String ARGS = "args";
    private static final String BOTTOM_BORDER = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String BOTTOM_CORNER = "└";

    /* renamed from: D */
    public static final int f4483D = 3;

    /* renamed from: E */
    public static final int f4484E = 6;
    private static final int FILE = 16;
    private static final String FILE_SEP;
    private static final Format FORMAT;
    private static final Format FORMAT_TIME;

    /* renamed from: I */
    public static final int f4485I = 4;
    private static final int JSON = 32;
    private static final String LEFT_BORDER = "│ ";
    private static final String LINE_SEP;
    private static final int MAX_LEN = 3000;
    private static final String MIDDLE_BORDER = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MIDDLE_CORNER = "├";
    private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String NOTHING = "log nothing";
    private static final String NULL = "null";
    private static final String PLACEHOLDER = " ";
    private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";

    /* renamed from: T */
    private static final char[] f4486T;
    private static String TAG = null;
    private static final String TOP_BORDER = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String TOP_CORNER = "┌";

    /* renamed from: V */
    public static final int f4487V = 2;

    /* renamed from: W */
    public static final int f4488W = 5;
    private static final int XML = 48;
    private static Context sAppContext = null;
    private static Config sConfig = null;
    private static long sLastCoutMilli = 0;
    private static long sLastFlush = 0;
    private static final int s_flush_interval = 100;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TYPE {
    }

    public static native void initNativeLog(String str, String str2, String str3, boolean z, String str4, String str5);

    public static native void nativeLog(int i, String str, String str2);

    public static native void releaseLog();

    public static native void resetGitHash(String str);

    public static native void setPuduLogHighestLevel(int i);

    static {
        System.loadLibrary("pudutech_log");
        f4486T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};
        FILE_SEP = System.getProperty("file.separator");
        LINE_SEP = System.getProperty("line.separator");
        FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ");
        FORMAT_TIME = new SimpleDateFormat("HH:mm:ss.SSS ");
        sConfig = new Config();
        sLastFlush = 0L;
        sLastCoutMilli = 0L;
        TAG = "pdlog";
    }

    private Pdlog() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Config init(Context context, String str, boolean z, String str2, String str3) {
        sAppContext = context;
        if (Build.VERSION.SDK_INT < 24) {
            mountTmpCheck();
            initNativeLog("/sdcard/PuduRobotMap/logconfig.json", "/tmp", str, z, str2, str3);
        } else {
            File file = new File("/sdcard/pudu/log");
            if (!file.exists()) {
                file.mkdirs();
            }
            initNativeLog("/sdcard/PuduRobotMap/logconfig.json", "/sdcard/pudu/log", str, z, str2, str3);
        }
        sConfig.setConsoleFilter(2);
        setPuduLogHighestLevel(2);
        return sConfig;
    }

    private static String readFile(String str) {
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append('\n');
            }
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    private static void mountTmpCheck() {
        if (readFile("/proc/mounts").contains("tmpfs /tmp tmpfs")) {
            m3275i(TAG, "tmpfs /tmp mount point exsit");
            return;
        }
        Tools.execCommand("mount -o remount,rw /;mkdir /tmp;mount -o size=300m,mode=777 -t tmpfs tmpfs /tmp;mount -o remount,ro /", true);
        Tools.execCommand("echo 20 > /sys/bus/spi/drivers/mcp251x/spi1.0/net/can0/tx_queue_len", true);
        if (readFile("/proc/mounts").contains("tmpfs /tmp tmpfs")) {
            m3275i(TAG, "tmpfs /tmp mount point create success");
        } else {
            sConfig.mLog2FileSwitch = false;
            Log.w(TAG, "tmpfs /tmp create fail, close log to file");
        }
    }

    public static Config getConfig() {
        Config config = sConfig;
        if (config != null) {
            return config;
        }
        throw new NullPointerException("U should init first.");
    }

    /* renamed from: v */
    public static void m3276v(String str, Object... objArr) {
        log(2, str, objArr);
    }

    /* renamed from: d */
    public static void m3273d(String str, Object... objArr) {
        log(3, str, objArr);
    }

    /* renamed from: i */
    public static void m3275i(String str, Object... objArr) {
        log(4, str, objArr);
    }

    /* renamed from: w */
    public static void m3277w(String str, Object... objArr) {
        log(5, str, objArr);
    }

    /* renamed from: e */
    public static void m3274e(String str, Object... objArr) {
        log(6, str, objArr);
    }

    /* renamed from: a */
    public static void m3272a(String str, Object... objArr) {
        log(7, str, objArr);
    }

    public static void file(String str, Object obj) {
        log(19, str, obj);
    }

    public static void file(int i, String str, Object obj) {
        log(i | 16, str, obj);
    }

    public static void json(String str, String str2) {
        log(35, str, str2);
    }

    public static void json(int i, String str, String str2) {
        log(i | 32, str, str2);
    }

    public static void xml(String str, String str2) {
        log(51, str, str2);
    }

    public static void xml(int i, String str, String str2) {
        log(i | 48, str, str2);
    }

    public static void log(int i, String str, Object... objArr) {
        if (sConfig.mLogSwitch) {
            if (sConfig.mLog2ConsoleSwitch || sConfig.mLog2FileSwitch) {
                int i2 = i & 15;
                int i3 = i & DimensionsKt.HDPI;
                if (i2 >= sConfig.mConsoleFilter || i2 >= sConfig.mFileFilter) {
                    String processBody = processBody(i3, objArr);
                    if ((sConfig.mLog2FileSwitch || i3 == 16) && i2 >= sConfig.mFileFilter) {
                        nativeLog(i2, str, processBody);
                    }
                }
            }
        }
    }

    private static String processBody(int i, Object... objArr) {
        String str;
        String formatXml;
        str = "null";
        if (objArr != null) {
            if (objArr.length == 1) {
                Object obj = objArr[0];
                str = obj != null ? obj.toString() : "null";
                if (i == 32) {
                    formatXml = formatJson(str);
                } else if (i == 48) {
                    formatXml = formatXml(str);
                }
                str = formatXml;
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj2 = objArr[i2];
                    sb.append(obj2 == null ? "null" : obj2.toString());
                    sb.append(PLACEHOLDER);
                }
                str = sb.toString();
            }
        }
        return str.length() == 0 ? NOTHING : str;
    }

    private static String formatJson(String str) {
        try {
            if (str.startsWith("{")) {
                str = new JSONObject(str).toString(4);
            } else if (str.startsWith("[")) {
                str = new JSONArray(str).toString(4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String formatXml(String str) {
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
            newTransformer.transform(streamSource, streamResult);
            return streamResult.getWriter().toString().replaceFirst(">", ">" + LINE_SEP);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            boolean createNewFile = file.createNewFile();
            file.setReadable(true, false);
            file.setExecutable(true, false);
            file.setWritable(true, false);
            return createNewFile;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void printDeviceInfo() {
        String str = "";
        long j = 0;
        try {
            PackageInfo packageInfo = sAppContext.getPackageManager().getPackageInfo(sAppContext.getPackageName(), 0);
            if (packageInfo != null) {
                str = packageInfo.versionName;
                j = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        m3275i(TAG, "************* Log Head ****************\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + str + "\nApp VersionCode    : " + j + "\n************* Log Head ****************\n\n");
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public static class Config {
        private int mConsoleFilter;
        private String mDefaultDir;
        private String mDir;
        private int mFileFilter;
        private String mFilePrefix;
        private boolean mLog2ConsoleSwitch;
        private boolean mLog2FileSwitch;
        private boolean mLogSwitch;
        private int mStackDeep;
        private int mStackOffset;

        private Config() {
            this.mDefaultDir = "/tmp/";
            this.mFilePrefix = Pdlog.TAG;
            this.mLogSwitch = true;
            this.mLog2ConsoleSwitch = true;
            this.mLog2FileSwitch = true;
            this.mConsoleFilter = 2;
            this.mFileFilter = 2;
            this.mStackDeep = 1;
            this.mStackOffset = 0;
            if (this.mDefaultDir != null) {
                return;
            }
            if ("mounted".equals(Environment.getExternalStorageState()) && Pdlog.sAppContext.getExternalCacheDir() != null) {
                this.mDefaultDir = Pdlog.sAppContext.getExternalCacheDir() + Pdlog.FILE_SEP + "log" + Pdlog.FILE_SEP;
                return;
            }
            this.mDefaultDir = Pdlog.sAppContext.getCacheDir() + Pdlog.FILE_SEP + "log" + Pdlog.FILE_SEP;
        }

        public Config setLogSwitch(boolean z) {
            this.mLogSwitch = z;
            return this;
        }

        public Config setConsoleSwitch(boolean z) {
            this.mLog2ConsoleSwitch = z;
            return this;
        }

        public Config setLog2FileSwitch(boolean z) {
            this.mLog2FileSwitch = z;
            return this;
        }

        public Config setDir(String str) {
            if (!Pdlog.isSpace(str)) {
                if (!str.endsWith(Pdlog.FILE_SEP)) {
                    str = str + Pdlog.FILE_SEP;
                }
                this.mDir = str;
            } else {
                this.mDir = null;
            }
            return this;
        }

        public Config setDir(File file) {
            String str;
            if (file == null) {
                str = null;
            } else {
                str = file.getAbsolutePath() + Pdlog.FILE_SEP;
            }
            this.mDir = str;
            return this;
        }

        public Config setFilePrefix(String str) {
            if (Pdlog.isSpace(str)) {
                this.mFilePrefix = "util";
            } else {
                this.mFilePrefix = str;
            }
            return this;
        }

        public Config setConsoleFilter(int i) {
            this.mConsoleFilter = i;
            return this;
        }

        public Config setFileFilter(int i) {
            this.mFileFilter = i;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("switch: ");
            sb.append(this.mLogSwitch);
            sb.append(Pdlog.LINE_SEP);
            sb.append("console: ");
            sb.append(this.mLog2ConsoleSwitch);
            sb.append(Pdlog.LINE_SEP);
            sb.append("file: ");
            sb.append(this.mLog2FileSwitch);
            sb.append(Pdlog.LINE_SEP);
            sb.append("dir: ");
            String str = this.mDir;
            if (str == null) {
                str = this.mDefaultDir;
            }
            sb.append(str);
            sb.append(Pdlog.LINE_SEP);
            sb.append("filePrefix: ");
            sb.append(this.mFilePrefix);
            sb.append(Pdlog.LINE_SEP);
            sb.append("consoleFilter: ");
            sb.append(Pdlog.f4486T[this.mConsoleFilter - 2]);
            sb.append(Pdlog.LINE_SEP);
            sb.append("fileFilter: ");
            sb.append(Pdlog.f4486T[this.mFileFilter - 2]);
            sb.append(Pdlog.LINE_SEP);
            sb.append("stackDeep: ");
            sb.append(this.mStackDeep);
            sb.append(Pdlog.LINE_SEP);
            sb.append("mStackOffset: ");
            sb.append(this.mStackOffset);
            return sb.toString();
        }
    }
}
