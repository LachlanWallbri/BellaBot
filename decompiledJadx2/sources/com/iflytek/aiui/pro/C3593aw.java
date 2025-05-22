package com.iflytek.aiui.pro;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.aw */
/* loaded from: classes.dex */
public class C3593aw {

    /* renamed from: d */
    private static final boolean f2392d = new File("/dev/cpuctl/tasks").exists();

    /* renamed from: e */
    private static final Pattern f2393e = Pattern.compile("^([A-Za-z]{1}[A-Za-z0-9_]*[\\.|:])*[A-Za-z][A-Za-z0-9_]*$");

    /* renamed from: a */
    public final int f2394a;

    /* renamed from: b */
    public final String f2395b;

    /* renamed from: c */
    public final int f2396c;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* renamed from: com.iflytek.aiui.pro.aw$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public final int f2397a;

        /* renamed from: b */
        public final String f2398b;

        /* renamed from: c */
        public final String f2399c;

        protected a(String str) throws NumberFormatException, IndexOutOfBoundsException {
            String[] split = str.split(":");
            this.f2397a = Integer.parseInt(split[0]);
            this.f2398b = split[1];
            this.f2399c = split[2];
        }

        public String toString() {
            return String.format(Locale.ENGLISH, "%d:%s:%s", Integer.valueOf(this.f2397a), this.f2398b, this.f2399c);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.aw$b */
    /* loaded from: classes.dex */
    public static final class b extends Exception {
        public b(int i) {
            super(String.format("The process %d does not belong to any application", Integer.valueOf(i)));
        }
    }

    public C3593aw(int i) throws IOException, b {
        int m1104a;
        String substring;
        this.f2396c = i;
        String m1101a = m1101a(i);
        this.f2395b = m1101a;
        if (m1101a == null || !f2393e.matcher(m1101a).matches() || !new File("/data/data", m1105b()).exists()) {
            throw new b(i);
        }
        ArrayList<a> m1103c = m1103c();
        if (f2392d) {
            a m1100a = m1100a(m1103c, "cpuacct");
            a m1100a2 = m1100a(m1103c, "cpu");
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (m1100a2 == null || m1100a == null || !m1100a.f2399c.contains("pid_")) {
                        throw new b(i);
                    }
                    substring = m1100a.f2399c.split("/")[1].replace("uid_", "");
                } else {
                    if (m1100a2 == null || m1100a == null || !m1100a2.f2399c.contains("apps")) {
                        throw new b(i);
                    }
                    substring = m1100a.f2399c.substring(m1100a.f2399c.lastIndexOf("/") + 1);
                }
                m1104a = Integer.parseInt(substring);
            } catch (Exception unused) {
            }
            this.f2394a = m1104a;
        }
        m1104a = m1104a();
        this.f2394a = m1104a;
    }

    /* renamed from: a */
    private a m1100a(ArrayList<a> arrayList, String str) {
        Iterator<a> it = arrayList.iterator();
        while (it.hasNext()) {
            a next = it.next();
            for (String str2 : next.f2398b.split(",")) {
                if (str2.equals(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private String m1101a(int i) throws IOException {
        String str;
        try {
            str = m1102a(String.format(Locale.ENGLISH, "/proc/%d/cmdline", Integer.valueOf(i))).trim();
        } catch (IOException unused) {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return m1102a(String.format(Locale.ENGLISH, "/proc/%d/stat", Integer.valueOf(i))).split("\\s+")[1].replace("(", "").replace(")", "");
        } catch (Exception unused2) {
            throw new IOException(String.format(Locale.ENGLISH, "Error reading /proc/%d/stat", Integer.valueOf(i)));
        }
    }

    /* renamed from: a */
    static String m1102a(String str) throws IOException {
        BufferedReader bufferedReader;
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            bufferedReader = new BufferedReader(new FileReader(str));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            String str2 = "";
            while (readLine != null) {
                sb.append(str2);
                sb.append(readLine);
                readLine = bufferedReader.readLine();
                str2 = "\n";
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (IOException unused) {
            }
            return sb2;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    /* renamed from: c */
    private ArrayList<a> m1103c() {
        ArrayList<a> arrayList = new ArrayList<>();
        try {
            for (String str : m1102a(String.format(Locale.ENGLISH, "/proc/%d/cgroup", Integer.valueOf(this.f2396c))).split("\n")) {
                try {
                    arrayList.add(new a(str));
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
        }
        return arrayList;
    }

    /* renamed from: a */
    public int m1104a() {
        try {
            for (String str : m1102a(String.format(Locale.ENGLISH, "/proc/%d/status", Integer.valueOf(this.f2396c))).split("\n")) {
                if (str.startsWith("Uid:")) {
                    return Integer.parseInt(str.split("Uid:")[1].trim().split("\\s+")[0]);
                }
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public String m1105b() {
        return this.f2395b.split(":")[0];
    }
}
