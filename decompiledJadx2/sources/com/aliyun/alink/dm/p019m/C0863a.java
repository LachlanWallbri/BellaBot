package com.aliyun.alink.dm.p019m;

import com.aliyun.alink.linksdk.tools.ALog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Downloader.java */
/* renamed from: com.aliyun.alink.dm.m.a */
/* loaded from: classes.dex */
public class C0863a {

    /* renamed from: a */
    public static final char[] f432a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: c */
    private static boolean f433c = false;

    /* renamed from: d */
    private a f435d;

    /* renamed from: e */
    private b f436e;

    /* renamed from: b */
    private final int f434b = 1024;

    /* renamed from: f */
    private AtomicBoolean f437f = new AtomicBoolean(false);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: Downloader.java */
    /* renamed from: com.aliyun.alink.dm.m.a$b */
    /* loaded from: classes.dex */
    public interface b {
        /* renamed from: a */
        void mo163a(int i, int i2);

        /* renamed from: a */
        void mo164a(int i, String str);
    }

    public C0863a(a aVar, b bVar) {
        this.f435d = aVar;
        this.f436e = bVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.aliyun.alink.dm.m.a$1] */
    /* renamed from: a */
    public void m160a() {
        new Thread() { // from class: com.aliyun.alink.dm.m.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                if (C0863a.this.f435d == null) {
                    ALog.m484w("Downloader", "null download info.");
                    return;
                }
                ALog.m479d("Downloader", "try to download info:" + C0863a.this.f435d);
                File file = new File(C0863a.this.f435d.f442d);
                if (!file.exists()) {
                    m162a(C0863a.this.f435d.f440b);
                    return;
                }
                String m156a = C0863a.m156a(file);
                ALog.m479d("Downloader", "server md5:" + C0863a.this.f435d.f439a);
                ALog.m479d("Downloader", "local  md5:" + m156a);
                if (m156a.toUpperCase().equals(C0863a.this.f435d.f439a.toUpperCase())) {
                    ALog.m479d("Downloader", "file has already downloaded successfully, file:" + file);
                    C0863a.this.f436e.mo163a(0, (int) file.length());
                    C0863a.this.f436e.mo163a((int) file.length(), (int) file.length());
                    C0863a.this.f436e.mo164a(0, "");
                    return;
                }
                ALog.m484w("Downloader", "file content md5 unmatch, delete it. file:" + file);
                file.delete();
                m162a(C0863a.this.f435d.f440b);
            }

            /* renamed from: a */
            private void m162a(String str) {
                int read;
                ALog.m479d("Downloader", "download url:" + str);
                File file = new File(C0863a.this.f435d.f442d);
                file.getParentFile().mkdirs();
                file.delete();
                try {
                    URLConnection openConnection = new URL(str).openConnection();
                    openConnection.connect();
                    InputStream inputStream = openConnection.getInputStream();
                    File file2 = new File(C0863a.this.f435d.f442d + ".tmp");
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    int i = 0;
                    while (!C0863a.this.f437f.get() && (read = inputStream.read(bArr)) != -1) {
                        fileOutputStream.write(bArr, 0, read);
                        i += read;
                        C0863a.this.f436e.mo163a(i, C0863a.this.f435d.f441c);
                        ALog.m479d("Downloader", "download: " + i + "/" + C0863a.this.f435d.f441c);
                    }
                    inputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (C0863a.this.f437f.get()) {
                        ALog.m479d("Downloader", "download has cancled.");
                        C0863a.this.f436e.mo164a(-1, "download has canceled.");
                        file2.delete();
                        return;
                    }
                    file2.renameTo(file);
                    ALog.m479d("Downloader", "rename file:" + file + " exist?" + file.exists());
                    StringBuilder sb = new StringBuilder();
                    sb.append("ota file is ready. file:");
                    sb.append(file);
                    ALog.m479d("Downloader", sb.toString());
                    String m156a = C0863a.m156a(file);
                    if (!C0863a.f433c && !C0863a.this.f435d.f439a.equalsIgnoreCase(m156a)) {
                        ALog.m484w("Downloader", "md5 check unmatch. expected:" + C0863a.this.f435d.f439a + " but actual:" + m156a);
                        C0863a.this.f436e.mo164a(-1, "ota md5 don't match.");
                        return;
                    }
                    ALog.m479d("Downloader", "md5 check match.");
                    C0863a.this.f436e.mo164a(0, "");
                } catch (IOException e) {
                    e.printStackTrace();
                    C0863a.this.f436e.mo164a(-1, e.getMessage());
                } catch (Error e2) {
                    e2.printStackTrace();
                    C0863a.this.f436e.mo164a(-1, e2.getMessage());
                }
            }
        }.start();
    }

    /* renamed from: b */
    public void m161b() {
        this.f437f.set(true);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: Downloader.java */
    /* renamed from: com.aliyun.alink.dm.m.a$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public String f439a;

        /* renamed from: b */
        public String f440b;

        /* renamed from: c */
        public int f441c;

        /* renamed from: d */
        public String f442d;

        public String toString() {
            return "md5:" + this.f439a + " url:" + this.f440b + " size:" + this.f441c + " filepath:" + this.f442d;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0047: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:31:0x0047 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m156a(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        MessageDigest messageDigest;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                fileInputStream = new FileInputStream(file);
            } catch (Exception e) {
                e = e;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream3 != null) {
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                String str = new String(Hex.encodeHex(messageDigest.digest()));
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return str;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream3 = fileInputStream2;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }
}
