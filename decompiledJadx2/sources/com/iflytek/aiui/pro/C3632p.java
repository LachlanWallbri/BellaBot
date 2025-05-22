package com.iflytek.aiui.pro;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.p */
/* loaded from: classes.dex */
public class C3632p {

    /* renamed from: a */
    public String f2597a;

    /* renamed from: b */
    public String f2598b;

    /* renamed from: c */
    public String f2599c;

    /* renamed from: d */
    String f2600d;

    /* renamed from: e */
    String f2601e;

    /* renamed from: f */
    String f2602f;

    /* renamed from: g */
    String f2603g;

    /* renamed from: h */
    int f2604h;

    /* renamed from: i */
    int f2605i;

    /* renamed from: j */
    private RandomAccessFile f2606j;

    public C3632p(String str, String str2, String str3) {
        this.f2597a = str;
        this.f2598b = str2;
        this.f2599c = str3;
        String[] split = str.split("/");
        String str4 = split[split.length - 1];
        this.f2600d = str4;
        String[] split2 = str4.split("_");
        this.f2601e = split2[0];
        this.f2602f = split2[2];
        this.f2603g = split2[1];
        this.f2604h = Integer.parseInt(split2[3]);
        this.f2605i = Integer.parseInt(split2[4].split("\\.")[0]);
    }

    /* renamed from: a */
    public void m1469a(Context context, C3626m c3626m) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.f2606j == null) {
            return;
        }
        try {
            this.f2606j.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = this.f2598b;
        if (!C3636r.m1510a(C3636r.m1513c(context, this.f2600d), str)) {
            try {
                new File(C3636r.m1513c(context, this.f2600d)).delete();
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                return;
            }
        }
        try {
            C3642u c3642u = new C3642u(str, this.f2599c, this.f2603g);
            c3642u.f2663d = "usedex";
            C3636r.m1507a(context, c3626m, c3642u, C3636r.m1513c(context, this.f2600d));
            C3638s.m1523a(context, c3626m, C3636r.m1504a(context, this.f2601e, c3626m.f2580b), C3636r.m1515d(context), null, context.getClassLoader(), this.f2603g);
            File file = new File(C3636r.m1513c(context, this.f2600d));
            if (file.exists()) {
                file.delete();
                return;
            }
            return;
        } catch (Throwable th3) {
            th3.printStackTrace();
            return;
        }
        th.printStackTrace();
    }

    /* renamed from: a */
    public void m1470a(Context context, byte[] bArr, long j) {
        try {
            if (this.f2606j == null) {
                File file = new File(C3636r.m1513c(context, this.f2600d));
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    this.f2606j = new RandomAccessFile(file, "rw");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try {
                this.f2606j.seek(j);
                this.f2606j.write(bArr);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m1471a(Throwable th) {
        try {
            if (this.f2606j == null) {
                return;
            }
            try {
                this.f2606j.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
