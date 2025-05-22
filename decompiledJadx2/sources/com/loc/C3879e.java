package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.APSServiceBase;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.tencent.bugly.BuglyStrategy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/* compiled from: APSServiceCore.java */
/* renamed from: com.loc.e */
/* loaded from: classes4.dex */
public class C3879e implements APSServiceBase {

    /* renamed from: a */
    Context f4151a;

    /* renamed from: e */
    Messenger f4155e;

    /* renamed from: b */
    boolean f4152b = false;

    /* renamed from: c */
    boolean f4153c = false;

    /* renamed from: v */
    private boolean f4172v = false;

    /* renamed from: w */
    private boolean f4173w = false;

    /* renamed from: d */
    String f4154d = null;

    /* renamed from: f */
    b f4156f = null;

    /* renamed from: x */
    private long f4174x = 0;

    /* renamed from: y */
    private String f4175y = "";

    /* renamed from: z */
    private long f4176z = 0;

    /* renamed from: A */
    private AMapLocationServer f4144A = null;

    /* renamed from: g */
    AMapLocation f4157g = null;

    /* renamed from: B */
    private long f4145B = 0;

    /* renamed from: C */
    private int f4146C = 0;

    /* renamed from: D */
    private volatile C3851bz f4147D = null;

    /* renamed from: E */
    private C3882h f4148E = null;

    /* renamed from: h */
    C3846bu f4158h = null;

    /* renamed from: i */
    boolean f4159i = false;

    /* renamed from: j */
    long f4160j = 0;

    /* renamed from: k */
    long f4161k = 0;

    /* renamed from: l */
    C3873cu f4162l = null;

    /* renamed from: F */
    private boolean f4149F = true;

    /* renamed from: G */
    private String f4150G = "";

    /* renamed from: m */
    String f4163m = null;

    /* renamed from: n */
    ServerSocket f4164n = null;

    /* renamed from: o */
    boolean f4165o = false;

    /* renamed from: p */
    Socket f4166p = null;

    /* renamed from: q */
    boolean f4167q = false;

    /* renamed from: r */
    a f4168r = null;

    /* renamed from: s */
    String f4169s = null;

    /* renamed from: t */
    boolean f4170t = true;

    /* renamed from: u */
    boolean f4171u = false;

    /* compiled from: APSServiceCore.java */
    /* renamed from: com.loc.e$a */
    /* loaded from: classes4.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0069 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0070 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0091 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x009c A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00b7  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00c7 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00d2 A[Catch: all -> 0x00e0, TryCatch #0 {all -> 0x00e0, blocks: (B:22:0x0062, B:23:0x0064, B:25:0x00dc, B:31:0x0069, B:32:0x0070, B:33:0x007b, B:34:0x0086, B:35:0x0091, B:36:0x009c, B:38:0x00a9, B:42:0x00b0, B:45:0x00b9, B:49:0x00c0, B:51:0x00c7, B:52:0x00d2, B:55:0x005b), top: B:54:0x005b }] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void handleMessage(Message message) {
            Bundle bundle;
            Messenger messenger = null;
            try {
                bundle = message.getData();
            } catch (Throwable th) {
                th = th;
                bundle = null;
            }
            try {
                messenger = message.replyTo;
                if (bundle != null && !bundle.isEmpty()) {
                    String string = bundle.getString("c");
                    if (TextUtils.isEmpty(C3879e.this.f4169s)) {
                        C3879e.this.f4169s = C3880f.m3102c(C3879e.this.f4151a);
                    }
                    if (string == null || !string.equals(C3879e.this.f4169s)) {
                        if (message.what == 1) {
                            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                            aMapLocationServer.setErrorCode(10);
                            aMapLocationServer.setLocationDetail("invalid handlder scode!!!");
                            C3879e c3879e = C3879e.this;
                            C3879e.m3073a(messenger, aMapLocationServer.toStr(1), 0, false);
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    C3880f.m3097a(th, "APSServiceCore", "ActionHandler handlerMessage");
                    switch (message.what) {
                    }
                    super.handleMessage(message);
                } catch (Throwable th3) {
                    C3880f.m3097a(th3, "actionHandler", "handleMessage");
                    return;
                }
            }
            switch (message.what) {
                case 0:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3076a(C3879e.this, messenger, bundle);
                    break;
                case 1:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3082b(C3879e.this, messenger, bundle);
                    break;
                case 2:
                    if (bundle != null && !bundle.isEmpty()) {
                        C3879e.this.m3087a();
                        break;
                    }
                    return;
                case 3:
                    if (bundle != null && !bundle.isEmpty()) {
                        C3879e.this.m3089b();
                        break;
                    }
                    return;
                case 4:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3081b(C3879e.this);
                    break;
                case 5:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3084c(C3879e.this);
                    break;
                case 7:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3085d(C3879e.this);
                    break;
                case 9:
                    C3879e.this.m3069a(bundle);
                    C3879e.m3075a(C3879e.this, messenger);
                    break;
                case 10:
                    C3879e.this.m3069a(bundle);
                    C3879e.this.m3088a(messenger, bundle);
                    break;
                case 11:
                    C3879e.m3086e(C3879e.this);
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: APSServiceCore.java */
    /* renamed from: com.loc.e$b */
    /* loaded from: classes4.dex */
    public class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                if (C3879e.this.f4147D == null) {
                    C3879e.this.f4147D = new C3851bz(C3879e.this.f4151a);
                }
                try {
                    C3879e.this.f4148E = new C3882h(C3879e.this.f4151a);
                } catch (Throwable th) {
                    C3880f.m3097a(th, "AMapLocationManager", "init 2");
                }
                C3879e.this.f4158h = new C3846bu();
                super.onLooperPrepared();
            } catch (Throwable th2) {
                C3880f.m3097a(th2, "actionHandler", "onLooperPrepared");
            }
        }
    }

    public C3879e(Context context) {
        this.f4151a = context.getApplicationContext();
    }

    /* renamed from: a */
    private static AMapLocationServer m3065a(int i, String str) {
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(i);
            aMapLocationServer.setLocationDetail(str);
            return aMapLocationServer;
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3069a(Bundle bundle) {
        try {
            if (this.f4172v) {
                return;
            }
            C3880f.m3095a(this.f4151a);
            if (bundle != null) {
                this.f4163m = bundle.getString("extraJson");
            }
            if (TextUtils.isEmpty(this.f4163m)) {
                Context context = this.f4151a;
                this.f4163m = C3880f.m3094a((AMapLocationClientOption) null);
            }
            this.f4172v = true;
            m3077a(this.f4163m);
            this.f4158h.m2708a(this.f4151a);
        } catch (Throwable th) {
            this.f4149F = false;
            this.f4150G = th.getMessage();
            C3880f.m3097a(th, "APSServiceCore", "init");
        }
    }

    /* renamed from: a */
    private void m3070a(Messenger messenger) {
        try {
            if (C3869cq.m2882d(this.f4151a) && messenger != null) {
                Message obtain = Message.obtain();
                obtain.what = 100;
                messenger.send(obtain);
            }
            if (C3869cq.m2870a()) {
                this.f4168r.sendEmptyMessage(4);
            }
            if (!C3869cq.m2879c() || C3869cq.m2881d() <= 2) {
                return;
            }
            this.f4168r.sendEmptyMessage(5);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "checkConfig");
        }
    }

    /* renamed from: a */
    private static void m3071a(Messenger messenger, int i, Bundle bundle) {
        try {
            Message obtain = Message.obtain();
            obtain.setData(bundle);
            obtain.what = i;
            messenger.send(obtain);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "sendMessage");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static void m3073a(Messenger messenger, String str, int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("locationJson", str);
        bundle.putInt("originalLocType", i);
        bundle.putBoolean("fixlastlocation", z);
        m3071a(messenger, 1, bundle);
    }

    /* renamed from: a */
    static /* synthetic */ void m3075a(C3879e c3879e, Messenger messenger) {
        try {
            c3879e.m3080b(messenger);
            C3869cq.m2885f(c3879e.f4151a);
            c3879e.m3079a(true, c3879e.f4151a);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "doCallOtherSer");
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3076a(C3879e c3879e, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty() || c3879e.f4173w) {
                    return;
                }
                c3879e.f4173w = true;
                c3879e.m3080b(messenger);
                C3869cq.m2885f(c3879e.f4151a);
                c3879e.m3079a(false, c3879e.f4151a);
                c3879e.m3070a(messenger);
                if (C3869cq.m2871a(c3879e.f4145B) && "1".equals(bundle.getString("isCacheLoc"))) {
                    c3879e.f4145B = C3876cx.m2985b();
                    c3879e.f4158h.m2713b();
                }
                c3879e.m3083c();
            } catch (Throwable th) {
                C3880f.m3097a(th, "APSServiceCore", "doInitAuth");
            }
        }
    }

    /* renamed from: a */
    private void m3077a(String str) {
        if (str != null) {
            try {
                if (this.f4175y.equals(str)) {
                    return;
                }
                this.f4176z = 0L;
                JSONObject jSONObject = new JSONObject(str);
                try {
                    if (this.f4158h != null) {
                        this.f4158h.m2711a(jSONObject);
                        boolean z = true;
                        if (jSONObject.optInt("isKillProcess", 0) != 1) {
                            z = false;
                        }
                        this.f4152b = z;
                        this.f4153c = jSONObject.optBoolean("sensorEnable", false);
                    }
                } catch (Throwable th) {
                    C3880f.m3097a(th, "APSServiceCore", "setExtra");
                }
                this.f4175y = str;
                if (C3876cx.m2979a(jSONObject, "isLocationCacheEnable")) {
                    this.f4170t = jSONObject.getBoolean("isLocationCacheEnable");
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m3078a(Socket socket) {
        String str;
        BufferedReader bufferedReader;
        String str2;
        String str3;
        String[] split;
        String[] split2;
        String[] split3;
        if (socket == null) {
            return;
        }
        int i = BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH;
        String str4 = "jsonp1";
        int i2 = 1;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            } catch (Throwable th) {
                th = th;
                str = null;
                bufferedReader = null;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && readLine.length() > 0 && (split = readLine.split(" ")) != null && split.length > 1 && (split2 = split[1].split("\\?")) != null && split2.length > 1 && (split3 = split2[1].split("&")) != null && split3.length > 0) {
                    String str5 = "jsonp1";
                    int i3 = 30000;
                    int i4 = 0;
                    while (i4 < split3.length) {
                        try {
                            String[] split4 = split3[i4].split("=");
                            if (split4 != null && split4.length > i2) {
                                if (TypedValues.Transition.S_TO.equals(split4[0])) {
                                    i3 = Integer.parseInt(split4[1]);
                                }
                                int i5 = i3;
                                if ("callback".equals(split4[0])) {
                                    str5 = split4[1];
                                }
                                i3 = i5;
                            }
                            i4++;
                            i2 = 1;
                        } catch (Throwable th2) {
                            th = th2;
                            str4 = str5;
                            str = null;
                            try {
                                str = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':1,'error':'params error'})";
                                C3880f.m3097a(th, "APSServiceCore", "invoke part2");
                                try {
                                    PrintStream printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                    printStream.println("HTTP/1.0 200 OK");
                                    printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
                                    printStream.println();
                                    printStream.println(str);
                                    printStream.close();
                                    try {
                                        bufferedReader.close();
                                        socket.close();
                                    } catch (Throwable th3) {
                                        th = th3;
                                        C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                                        return;
                                    }
                                } catch (Throwable th4) {
                                    try {
                                        C3880f.m3097a(th4, "APSServiceCore", "invoke part3");
                                        try {
                                            bufferedReader.close();
                                            socket.close();
                                            return;
                                        } catch (Throwable th5) {
                                            th = th5;
                                            C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                                            return;
                                        }
                                    } finally {
                                    }
                                }
                            } catch (Throwable th6) {
                                try {
                                    PrintStream printStream2 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                    printStream2.println("HTTP/1.0 200 OK");
                                    printStream2.println("Content-Length:" + str.getBytes("UTF-8").length);
                                    printStream2.println();
                                    printStream2.println(str);
                                    printStream2.close();
                                    try {
                                        bufferedReader.close();
                                        socket.close();
                                        throw th6;
                                    } catch (Throwable th7) {
                                        th = th7;
                                        C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                                        throw th6;
                                    }
                                } catch (Throwable th8) {
                                    try {
                                        C3880f.m3097a(th8, "APSServiceCore", "invoke part3");
                                        try {
                                            bufferedReader.close();
                                            socket.close();
                                            throw th6;
                                        } catch (Throwable th9) {
                                            th = th9;
                                            C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                                            throw th6;
                                        }
                                    } finally {
                                    }
                                }
                            }
                        }
                    }
                    i = i3;
                    str4 = str5;
                }
                int i6 = C3880f.f4181c;
                C3880f.f4181c = i;
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    try {
                        if ((this.f4144A == null || currentTimeMillis - this.f4144A.getTime() > 5000) && !C3876cx.m3004d(this.f4151a)) {
                            try {
                                this.f4144A = this.f4158h.m2703a();
                                if (this.f4144A.getErrorCode() != 0) {
                                    str2 = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':" + this.f4144A.getErrorCode() + ",'error':'" + this.f4144A.getErrorInfo() + "'})";
                                } else {
                                    str2 = null;
                                }
                            } catch (Throwable th10) {
                                try {
                                    C3880f.m3097a(th10, "APSServiceCore", "invoke part1");
                                    C3880f.f4181c = i6;
                                } finally {
                                    C3880f.f4181c = i6;
                                }
                            }
                            try {
                                str = str2;
                                if (str == null) {
                                    try {
                                        if (this.f4144A == null) {
                                            str3 = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':8,'error':'unknown error'})";
                                        } else {
                                            AMapLocationServer aMapLocationServer = this.f4144A;
                                            str3 = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':0,'error':'','location':{'y':" + aMapLocationServer.getLatitude() + ",'precision':" + aMapLocationServer.getAccuracy() + ",'x':" + aMapLocationServer.getLongitude() + "},'version_code':'3.3.0','version':'3.3.0'})";
                                        }
                                        str = str3;
                                        if (C3876cx.m3004d(this.f4151a)) {
                                            str = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':36,'error':'app is background'})";
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        str = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':1,'error':'params error'})";
                                        C3880f.m3097a(th, "APSServiceCore", "invoke part2");
                                        PrintStream printStream3 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                        printStream3.println("HTTP/1.0 200 OK");
                                        printStream3.println("Content-Length:" + str.getBytes("UTF-8").length);
                                        printStream3.println();
                                        printStream3.println(str);
                                        printStream3.close();
                                        bufferedReader.close();
                                        socket.close();
                                    }
                                }
                                PrintStream printStream4 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                printStream4.println("HTTP/1.0 200 OK");
                                printStream4.println("Content-Length:" + str.getBytes("UTF-8").length);
                                printStream4.println();
                                printStream4.println(str);
                                printStream4.close();
                                bufferedReader.close();
                                socket.close();
                                return;
                            } catch (Throwable th12) {
                                th = th12;
                                str = str2;
                                str = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':1,'error':'params error'})";
                                C3880f.m3097a(th, "APSServiceCore", "invoke part2");
                                PrintStream printStream32 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                printStream32.println("HTTP/1.0 200 OK");
                                printStream32.println("Content-Length:" + str.getBytes("UTF-8").length);
                                printStream32.println();
                                printStream32.println(str);
                                printStream32.close();
                                bufferedReader.close();
                                socket.close();
                            }
                        }
                        bufferedReader.close();
                        socket.close();
                        return;
                    } catch (Throwable th13) {
                        th = th13;
                        C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                        return;
                    }
                    PrintStream printStream42 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                    printStream42.println("HTTP/1.0 200 OK");
                    printStream42.println("Content-Length:" + str.getBytes("UTF-8").length);
                    printStream42.println();
                    printStream42.println(str);
                    printStream42.close();
                } catch (Throwable th14) {
                    try {
                        C3880f.m3097a(th14, "APSServiceCore", "invoke part3");
                        try {
                            bufferedReader.close();
                            socket.close();
                            return;
                        } catch (Throwable th15) {
                            th = th15;
                            C3880f.m3097a(th, "APSServiceCore", "invoke part4");
                            return;
                        }
                    } finally {
                        try {
                            bufferedReader.close();
                            socket.close();
                            throw th;
                        } catch (Throwable th16) {
                            C3880f.m3097a(th16, "APSServiceCore", "invoke part4");
                        }
                    }
                }
                str = null;
                if (str == null) {
                }
            } catch (Throwable th17) {
                th = th17;
                str = null;
                str = str4 + "&&" + str4 + "({'package':'" + this.f4154d + "','error_code':1,'error':'params error'})";
                C3880f.m3097a(th, "APSServiceCore", "invoke part2");
                PrintStream printStream322 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                printStream322.println("HTTP/1.0 200 OK");
                printStream322.println("Content-Length:" + str.getBytes("UTF-8").length);
                printStream322.println();
                printStream322.println(str);
                printStream322.close();
                bufferedReader.close();
                socket.close();
            }
        } catch (Throwable th18) {
            C3880f.m3097a(th18, "APSServiceCore", "invoke part5");
        }
    }

    /* renamed from: a */
    private void m3079a(boolean z, Context context) {
        try {
            this.f4158h.m2712a(z, context);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    private void m3080b(Messenger messenger) {
        try {
            this.f4158h.m2714b(this.f4151a);
            if (C3869cq.m2900q()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("ngpsAble", C3869cq.m2902s());
                m3071a(messenger, 7, bundle);
                C3869cq.m2901r();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "initAuth");
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m3081b(C3879e c3879e) {
        try {
            if (c3879e.f4146C < C3869cq.m2876b()) {
                c3879e.f4146C++;
                c3879e.f4158h.m2713b();
                c3879e.f4168r.sendEmptyMessageDelayed(4, 2000L);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "doGpsFusion");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:40|41|(3:42|43|(1:45)(1:91))|(5:46|47|(1:49)(1:87)|50|(1:86)(1:54))|55|(1:59)|60|(1:62)|63|(1:65)|66|67|68|(2:80|81)|83) */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x017e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x017f, code lost:
    
        com.loc.C3880f.m3097a(r0, "APSServiceCore", "fixLastLocation");
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x019a A[Catch: all -> 0x01be, TryCatch #3 {all -> 0x01be, blocks: (B:4:0x000a, B:9:0x0012, B:11:0x0018, B:13:0x002c, B:15:0x004c, B:17:0x0050, B:18:0x0056, B:19:0x005b, B:21:0x0060, B:23:0x0068, B:25:0x0071, B:26:0x018f, B:28:0x019a, B:29:0x019d, B:31:0x01a5, B:37:0x01af, B:38:0x01ba, B:40:0x007c, B:55:0x0116, B:57:0x0121, B:59:0x0129, B:60:0x012f, B:62:0x0133, B:63:0x013b, B:65:0x0140, B:83:0x0184, B:85:0x017f, B:89:0x0107, B:68:0x0148, B:70:0x014e, B:72:0x0156, B:74:0x015a, B:76:0x015e, B:78:0x0167, B:80:0x0175), top: B:3:0x000a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0133 A[Catch: all -> 0x01be, TryCatch #3 {all -> 0x01be, blocks: (B:4:0x000a, B:9:0x0012, B:11:0x0018, B:13:0x002c, B:15:0x004c, B:17:0x0050, B:18:0x0056, B:19:0x005b, B:21:0x0060, B:23:0x0068, B:25:0x0071, B:26:0x018f, B:28:0x019a, B:29:0x019d, B:31:0x01a5, B:37:0x01af, B:38:0x01ba, B:40:0x007c, B:55:0x0116, B:57:0x0121, B:59:0x0129, B:60:0x012f, B:62:0x0133, B:63:0x013b, B:65:0x0140, B:83:0x0184, B:85:0x017f, B:89:0x0107, B:68:0x0148, B:70:0x014e, B:72:0x0156, B:74:0x015a, B:76:0x015e, B:78:0x0167, B:80:0x0175), top: B:3:0x000a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0140 A[Catch: all -> 0x01be, TRY_LEAVE, TryCatch #3 {all -> 0x01be, blocks: (B:4:0x000a, B:9:0x0012, B:11:0x0018, B:13:0x002c, B:15:0x004c, B:17:0x0050, B:18:0x0056, B:19:0x005b, B:21:0x0060, B:23:0x0068, B:25:0x0071, B:26:0x018f, B:28:0x019a, B:29:0x019d, B:31:0x01a5, B:37:0x01af, B:38:0x01ba, B:40:0x007c, B:55:0x0116, B:57:0x0121, B:59:0x0129, B:60:0x012f, B:62:0x0133, B:63:0x013b, B:65:0x0140, B:83:0x0184, B:85:0x017f, B:89:0x0107, B:68:0x0148, B:70:0x014e, B:72:0x0156, B:74:0x015a, B:76:0x015e, B:78:0x0167, B:80:0x0175), top: B:3:0x000a, inners: #2 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ void m3082b(C3879e c3879e, Messenger messenger, Bundle bundle) {
        int i;
        String str;
        AMapLocation m3113a;
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                int i2 = 0;
                if (!c3879e.f4149F) {
                    c3879e.f4144A = m3065a(9, c3879e.f4150G);
                    m3073a(messenger, c3879e.f4144A.toStr(1), 0, false);
                    return;
                }
                String string = bundle.getString("extraJson");
                double d = bundle.getDouble(C3898x.f4338g, 0.0d);
                float f = bundle.getFloat(C3898x.f4339h, 0.0f);
                c3879e.m3077a(string);
                long m2985b = C3876cx.m2985b();
                if (c3879e.f4147D != null) {
                    if (c3879e.f4153c) {
                        c3879e.f4147D.m2734a();
                    } else {
                        c3879e.f4147D.m2737c();
                    }
                }
                if (c3879e.f4144A == null || c3879e.f4144A.getErrorCode() != 0 || m2985b - c3879e.f4176z >= 600) {
                    C3872ct c3872ct = new C3872ct();
                    c3872ct.m2922a(C3876cx.m2985b());
                    try {
                        c3879e.f4144A = c3879e.f4158h.m2703a();
                        i = c3879e.f4144A != null ? c3879e.f4144A.getLocationType() : 0;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                    }
                    try {
                        c3872ct.m2923a(c3879e.f4144A);
                        long time = c3879e.f4144A != null ? c3879e.f4144A.getTime() : 0L;
                        c3879e.f4144A = c3879e.f4158h.m2705a(c3879e.f4144A, new String[0]);
                        c3879e.f4144A.setTime(time);
                        if (!c3879e.f4153c || c3879e.f4147D == null) {
                            c3879e.f4144A.setAltitude(0.0d);
                            c3879e.f4144A.setBearing(0.0f);
                            c3879e.f4144A.setSpeed(0.0f);
                        } else {
                            c3879e.f4147D.m2735a(f);
                            c3879e.f4144A.setAltitude(c3879e.f4147D.m2733a(d));
                            c3879e.f4144A.setBearing(c3879e.f4147D.m2738d());
                            c3879e.f4144A.setSpeed((float) c3879e.f4147D.m2739e());
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        c3879e.f4144A = m3065a(8, th.getMessage());
                        C3880f.m3097a(th, "APSServiceCore", "run part2");
                        c3872ct.m2925b(C3876cx.m2985b());
                        if (c3879e.f4144A != null) {
                            c3879e.f4176z = C3876cx.m2985b();
                        }
                        if (c3879e.f4144A == null) {
                        }
                        str = c3879e.f4144A != null ? c3879e.f4144A.toStr(1) : null;
                        c3879e.f4171u = false;
                        if (c3879e.f4144A != null) {
                            c3879e.f4171u = true;
                            str = m3113a.toStr(1);
                        }
                        m3073a(messenger, str, i, c3879e.f4171u);
                        C3873cu.m2930a(c3879e.f4151a, c3872ct);
                        i2 = i;
                        c3879e.m3080b(messenger);
                        if (c3879e.f4158h.m2716d()) {
                        }
                        if (C3869cq.m2871a(c3879e.f4145B)) {
                            c3879e.f4145B = C3876cx.m2985b();
                            c3879e.f4158h.m2713b();
                        }
                        c3879e.m3083c();
                    }
                    c3872ct.m2925b(C3876cx.m2985b());
                    if (c3879e.f4144A != null && c3879e.f4144A.getErrorCode() == 0) {
                        c3879e.f4176z = C3876cx.m2985b();
                    }
                    if (c3879e.f4144A == null) {
                        c3879e.f4144A = m3065a(8, "loc is null");
                    }
                    str = c3879e.f4144A != null ? c3879e.f4144A.toStr(1) : null;
                    c3879e.f4171u = false;
                    if (c3879e.f4144A != null && c3879e.f4144A.getErrorCode() != 0 && c3879e.f4170t && c3879e.f4148E != null && c3879e.f4144A.getErrorCode() != 7 && (m3113a = c3879e.f4148E.m3113a(c3879e.f4144A.m560j())) != null) {
                        c3879e.f4171u = true;
                        str = m3113a.toStr(1);
                    }
                    m3073a(messenger, str, i, c3879e.f4171u);
                    C3873cu.m2930a(c3879e.f4151a, c3872ct);
                    i2 = i;
                } else {
                    m3073a(messenger, c3879e.f4144A.toStr(1), 4, false);
                }
                c3879e.m3080b(messenger);
                if (c3879e.f4158h.m2716d()) {
                    c3879e.m3070a(messenger);
                }
                if (C3869cq.m2871a(c3879e.f4145B) && c3879e.f4144A != null && (i2 == 2 || i2 == 4)) {
                    c3879e.f4145B = C3876cx.m2985b();
                    c3879e.f4158h.m2713b();
                }
                c3879e.m3083c();
            } catch (Throwable th3) {
                C3880f.m3097a(th3, "APSServiceCore", "doLocation");
            }
        }
    }

    /* renamed from: c */
    private void m3083c() {
        try {
            if (this.f4158h != null) {
                this.f4158h.m2718f();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "startColl");
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m3084c(C3879e c3879e) {
        C3846bu c3846bu;
        try {
            if (!C3869cq.m2884e()) {
                if (!C3876cx.m3004d(c3879e.f4151a)) {
                    c3846bu = c3879e.f4158h;
                }
                c3879e.f4168r.sendEmptyMessageDelayed(5, C3869cq.m2881d() * 1000);
            }
            c3846bu = c3879e.f4158h;
            c3846bu.m2713b();
            c3879e.f4168r.sendEmptyMessageDelayed(5, C3869cq.m2881d() * 1000);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "doOffFusion");
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m3085d(C3879e c3879e) {
        try {
            if (C3869cq.m2873a(c3879e.f4151a, c3879e.f4174x)) {
                c3879e.f4174x = C3876cx.m2969a();
                c3879e.f4158h.m2713b();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "doNGps");
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m3086e(C3879e c3879e) {
        b bVar;
        try {
            if (c3879e.f4158h != null) {
                c3879e.f4158h.m2714b(c3879e.f4151a);
            }
            if (c3879e.f4168r != null) {
                c3879e.f4168r.removeCallbacksAndMessages(null);
            }
            if (c3879e.f4156f != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    try {
                        C3871cs.m2916a(c3879e.f4156f, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                    } catch (Throwable unused) {
                        bVar = c3879e.f4156f;
                    }
                } else {
                    bVar = c3879e.f4156f;
                }
                bVar.quit();
            }
            c3879e.f4156f = null;
            c3879e.f4168r = null;
            if (c3879e.f4147D != null) {
                c3879e.f4147D.m2740f();
            }
            if (c3879e.f4148E != null) {
                c3879e.f4148E.m3115b();
            }
            c3879e.m3089b();
            c3879e.f4172v = false;
            c3879e.f4173w = false;
            c3879e.f4158h.m2715c();
            if (c3879e.f4162l != null) {
                long m2985b = C3876cx.m2985b() - c3879e.f4160j;
                C3873cu.m2928a(c3879e.f4151a, c3879e.f4162l.m2941c(c3879e.f4151a), c3879e.f4162l.m2942d(c3879e.f4151a), c3879e.f4161k, m2985b);
                c3879e.f4162l.m2943e(c3879e.f4151a);
            }
            C3873cu.m2927a(c3879e.f4151a);
            C3900z.m3264a();
            if (c3879e.f4152b) {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "threadDestroy");
        }
    }

    /* renamed from: a */
    public final void m3087a() {
        try {
            m3069a((Bundle) null);
            if (!this.f4165o) {
                this.f4165o = true;
                this.f4164n = new ServerSocket(43689);
            }
            while (this.f4165o) {
                this.f4166p = this.f4164n.accept();
                m3078a(this.f4166p);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "startSocket");
        }
    }

    /* renamed from: a */
    final void m3088a(Messenger messenger, Bundle bundle) {
        float f;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && C3869cq.m2908y()) {
                    double d = bundle.getDouble("lat");
                    double d2 = bundle.getDouble("lon");
                    if (this.f4157g != null) {
                        f = C3876cx.m2964a(new double[]{d, d2, this.f4157g.getLatitude(), this.f4157g.getLongitude()});
                        if (f < C3869cq.m2909z() * 3) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("lMaxGeoDis", C3869cq.m2909z() * 3);
                            bundle2.putInt("lMinGeoDis", C3869cq.m2909z());
                            bundle2.putString("locationJson", this.f4157g.toStr(1));
                            m3071a(messenger, 6, bundle2);
                        }
                    } else {
                        f = -1.0f;
                    }
                    if (f == -1.0f || f > C3869cq.m2909z()) {
                        m3069a((Bundle) null);
                        this.f4157g = this.f4158h.m2704a(d, d2);
                    }
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "APSServiceCore", "doLocationGeo");
            }
        }
    }

    /* renamed from: b */
    public final void m3089b() {
        try {
            if (this.f4164n != null) {
                this.f4164n.close();
            }
            if (this.f4166p != null) {
                this.f4166p.close();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "stopScocket");
        }
        this.f4164n = null;
        this.f4167q = false;
        this.f4165o = false;
    }

    @Override // com.amap.api.location.APSServiceBase
    public IBinder onBind(Intent intent) {
        a aVar;
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            C3886l.m3132a(stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("b");
        this.f4159i = "true".equals(intent.getStringExtra("as"));
        if (this.f4159i && (aVar = this.f4168r) != null) {
            aVar.sendEmptyMessageDelayed(9, 100L);
        }
        C3885k.m3121a(stringExtra2);
        String stringExtra3 = intent.getStringExtra("c");
        String m3102c = C3880f.m3102c(this.f4151a);
        if (TextUtils.isEmpty(stringExtra3) || !stringExtra3.equals(m3102c)) {
            return null;
        }
        this.f4155e = new Messenger(this.f4168r);
        return this.f4155e.getBinder();
    }

    @Override // com.amap.api.location.APSServiceBase
    public void onCreate() {
        try {
            this.f4154d = this.f4151a.getApplicationContext().getPackageName();
            this.f4156f = new b("amapLocCoreThread");
            this.f4156f.setPriority(5);
            this.f4156f.start();
            this.f4168r = new a(this.f4156f.getLooper());
            this.f4160j = C3876cx.m2985b();
            this.f4161k = C3876cx.m2969a();
            this.f4162l = new C3873cu();
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "onCreate");
        }
    }

    @Override // com.amap.api.location.APSServiceBase
    public void onDestroy() {
        try {
            if (this.f4168r != null) {
                this.f4168r.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APSServiceCore", "onDestroy");
        }
    }

    @Override // com.amap.api.location.APSServiceBase
    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
