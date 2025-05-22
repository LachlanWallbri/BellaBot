package com.iflytek.aiui.pro;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.v */
/* loaded from: classes.dex */
public final class C3644v implements Closeable {

    /* renamed from: c */
    private final File f2668c;

    /* renamed from: d */
    private final File f2669d;

    /* renamed from: e */
    private final File f2670e;

    /* renamed from: f */
    private final File f2671f;

    /* renamed from: g */
    private final int f2672g;

    /* renamed from: h */
    private long f2673h;

    /* renamed from: i */
    private final int f2674i;

    /* renamed from: k */
    private Writer f2676k;

    /* renamed from: n */
    private int f2679n;

    /* renamed from: o */
    private InterfaceC3646w f2680o;

    /* renamed from: a */
    static final Pattern f2664a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* renamed from: q */
    private static final ThreadFactory f2666q = new d();

    /* renamed from: b */
    static ThreadPoolExecutor f2665b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), f2666q);

    /* renamed from: s */
    private static final OutputStream f2667s = new f();

    /* renamed from: j */
    private long f2675j = 0;

    /* renamed from: l */
    private int f2677l = 1000;

    /* renamed from: m */
    private final LinkedHashMap<String, c> f2678m = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: p */
    private long f2681p = 0;

    /* renamed from: r */
    private final Callable<Void> f2682r = new e();

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$a */
    /* loaded from: classes.dex */
    public final class a {

        /* renamed from: b */
        private final c f2684b;

        /* renamed from: c */
        private final boolean[] f2685c;

        /* renamed from: d */
        private boolean f2686d;

        /* renamed from: e */
        private boolean f2687e;

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
          classes4.dex
         */
        /* renamed from: com.iflytek.aiui.pro.v$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private class C9030a extends FilterOutputStream {
            private C9030a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ C9030a(a aVar, OutputStream outputStream, d dVar) {
                this(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.this.f2686d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.this.f2686d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.this.f2686d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.this.f2686d = true;
                }
            }
        }

        private a(c cVar) {
            this.f2684b = cVar;
            this.f2685c = cVar.f2697d ? null : new boolean[C3644v.this.f2674i];
        }

        /* synthetic */ a(C3644v c3644v, c cVar, d dVar) {
            this(cVar);
        }

        /* renamed from: a */
        public OutputStream m1583a(int i) throws IOException {
            FileOutputStream fileOutputStream;
            C9030a c9030a;
            if (i < 0 || i >= C3644v.this.f2674i) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + C3644v.this.f2674i);
            }
            synchronized (C3644v.this) {
                if (this.f2684b.f2698e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f2684b.f2697d) {
                    this.f2685c[i] = true;
                }
                File m1600b = this.f2684b.m1600b(i);
                try {
                    fileOutputStream = new FileOutputStream(m1600b);
                } catch (FileNotFoundException unused) {
                    C3644v.this.f2668c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(m1600b);
                    } catch (FileNotFoundException unused2) {
                        return C3644v.f2667s;
                    }
                }
                c9030a = new C9030a(this, fileOutputStream, null);
            }
            return c9030a;
        }

        /* renamed from: a */
        public void m1584a() throws IOException {
            if (this.f2686d) {
                C3644v.this.m1556a(this, false);
                C3644v.this.m1579c(this.f2684b.f2695b);
            } else {
                C3644v.this.m1556a(this, true);
            }
            this.f2687e = true;
        }

        /* renamed from: b */
        public void m1585b() throws IOException {
            C3644v.this.m1556a(this, false);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$b */
    /* loaded from: classes.dex */
    public final class b implements Closeable {

        /* renamed from: b */
        private final String f2690b;

        /* renamed from: c */
        private final long f2691c;

        /* renamed from: d */
        private final InputStream[] f2692d;

        /* renamed from: e */
        private final long[] f2693e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f2690b = str;
            this.f2691c = j;
            this.f2692d = inputStreamArr;
            this.f2693e = jArr;
        }

        /* synthetic */ b(C3644v c3644v, String str, long j, InputStream[] inputStreamArr, long[] jArr, d dVar) {
            this(str, j, inputStreamArr, jArr);
        }

        /* renamed from: a */
        public InputStream m1586a(int i) {
            return this.f2692d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.f2692d) {
                C3650y.m1617a(inputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$c */
    /* loaded from: classes.dex */
    public final class c {

        /* renamed from: b */
        private final String f2695b;

        /* renamed from: c */
        private final long[] f2696c;

        /* renamed from: d */
        private boolean f2697d;

        /* renamed from: e */
        private a f2698e;

        /* renamed from: f */
        private long f2699f;

        private c(String str) {
            this.f2695b = str;
            this.f2696c = new long[C3644v.this.f2674i];
        }

        /* synthetic */ c(C3644v c3644v, String str, d dVar) {
            this(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1591a(String[] strArr) throws IOException {
            if (strArr.length != C3644v.this.f2674i) {
                throw m1593b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.f2696c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw m1593b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m1593b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File m1598a(int i) {
            return new File(C3644v.this.f2668c, this.f2695b + "." + i);
        }

        /* renamed from: a */
        public String m1599a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.f2696c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* renamed from: b */
        public File m1600b(int i) {
            return new File(C3644v.this.f2668c, this.f2695b + "." + i + ".tmp");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$d */
    /* loaded from: classes.dex */
    public static final class d implements ThreadFactory {

        /* renamed from: a */
        private final AtomicInteger f2700a = new AtomicInteger(1);

        d() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f2700a.getAndIncrement());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$e */
    /* loaded from: classes.dex */
    class e implements Callable<Void> {
        e() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (C3644v.this) {
                if (C3644v.this.f2676k == null) {
                    return null;
                }
                C3644v.this.m1573j();
                if (C3644v.this.m1571h()) {
                    C3644v.this.m1570g();
                    C3644v.this.f2679n = 0;
                }
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.v$f */
    /* loaded from: classes.dex */
    static final class f extends OutputStream {
        f() {
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    }

    private C3644v(File file, int i, int i2, long j) {
        this.f2668c = file;
        this.f2672g = i;
        this.f2669d = new File(file, "journal");
        this.f2670e = new File(file, "journal.tmp");
        this.f2671f = new File(file, "journal.bkp");
        this.f2674i = i2;
        this.f2673h = j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.iflytek.aiui.pro.v$d] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* renamed from: a */
    private a m1552a(String str, long j) throws IOException {
        a aVar;
        synchronized (this) {
            m1572i();
            m1567e(str);
            c cVar = this.f2678m.get(str);
            aVar = 0;
            aVar = 0;
            aVar = 0;
            if (j == -1 || (cVar != null && cVar.f2699f == j)) {
                if (cVar == null) {
                    cVar = new c(this, str, aVar);
                    this.f2678m.put(str, cVar);
                } else if (cVar.f2698e != null) {
                }
                a aVar2 = new a(this, cVar, aVar);
                cVar.f2698e = aVar2;
                this.f2676k.write("DIRTY " + str + '\n');
                this.f2676k.flush();
                aVar = aVar2;
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public static C3644v m1553a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                m1559a(file2, file3, false);
            }
        }
        C3644v c3644v = new C3644v(file, i, i2, j);
        if (c3644v.f2669d.exists()) {
            try {
                c3644v.m1566e();
                c3644v.m1569f();
                c3644v.f2676k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c3644v.f2669d, true), C3650y.f2726a));
                return c3644v;
            } catch (Throwable unused) {
                c3644v.m1578c();
            }
        }
        file.mkdirs();
        C3644v c3644v2 = new C3644v(file, i, i2, j);
        c3644v2.m1570g();
        return c3644v2;
    }

    /* renamed from: a */
    public static ThreadPoolExecutor m1555a() {
        try {
            if (f2665b == null || f2665b.isShutdown()) {
                f2665b = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f2666q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f2665b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1556a(a aVar, boolean z) throws IOException {
        synchronized (this) {
            c cVar = aVar.f2684b;
            if (cVar.f2698e != aVar) {
                throw new IllegalStateException();
            }
            if (z && !cVar.f2697d) {
                for (int i = 0; i < this.f2674i; i++) {
                    if (!aVar.f2685c[i]) {
                        aVar.m1585b();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                    if (!cVar.m1600b(i).exists()) {
                        aVar.m1585b();
                        break;
                    }
                }
            }
            for (int i2 = 0; i2 < this.f2674i; i2++) {
                File m1600b = cVar.m1600b(i2);
                if (!z) {
                    m1558a(m1600b);
                } else if (m1600b.exists()) {
                    File m1598a = cVar.m1598a(i2);
                    m1600b.renameTo(m1598a);
                    long j = cVar.f2696c[i2];
                    long length = m1598a.length();
                    cVar.f2696c[i2] = length;
                    this.f2675j = (this.f2675j - j) + length;
                }
            }
            this.f2679n++;
            cVar.f2698e = null;
            if (cVar.f2697d || z) {
                cVar.f2697d = true;
                this.f2676k.write("CLEAN " + cVar.f2695b + cVar.m1599a() + '\n');
                if (z) {
                    long j2 = this.f2681p;
                    this.f2681p = 1 + j2;
                    cVar.f2699f = j2;
                }
            } else {
                this.f2678m.remove(cVar.f2695b);
                this.f2676k.write("REMOVE " + cVar.f2695b + '\n');
            }
            this.f2676k.flush();
            if (this.f2675j > this.f2673h || m1571h()) {
                m1555a().submit(this.f2682r);
            }
        }
    }

    /* renamed from: a */
    private static void m1558a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m1559a(File file, File file2, boolean z) throws IOException {
        if (z) {
            m1558a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: d */
    private void m1564d(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == 6 && str.startsWith("REMOVE")) {
                this.f2678m.remove(substring);
                return;
            }
        } else {
            substring = str.substring(i, indexOf2);
        }
        c cVar = this.f2678m.get(substring);
        d dVar = null;
        if (cVar == null) {
            cVar = new c(this, substring, dVar);
            this.f2678m.put(substring, cVar);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            cVar.f2697d = true;
            cVar.f2698e = null;
            cVar.m1591a(split);
            return;
        }
        if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
            cVar.f2698e = new a(this, cVar, dVar);
            return;
        }
        if (indexOf2 == -1 && indexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    /* renamed from: e */
    private void m1566e() throws IOException {
        C3648x c3648x = new C3648x(new FileInputStream(this.f2669d), C3650y.f2726a);
        try {
            String m1609a = c3648x.m1609a();
            String m1609a2 = c3648x.m1609a();
            String m1609a3 = c3648x.m1609a();
            String m1609a4 = c3648x.m1609a();
            String m1609a5 = c3648x.m1609a();
            if (!"libcore.io.DiskLruCache".equals(m1609a) || !"1".equals(m1609a2) || !Integer.toString(this.f2672g).equals(m1609a3) || !Integer.toString(this.f2674i).equals(m1609a4) || !"".equals(m1609a5)) {
                throw new IOException("unexpected journal header: [" + m1609a + ", " + m1609a2 + ", " + m1609a4 + ", " + m1609a5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    m1564d(c3648x.m1609a());
                    i++;
                } catch (EOFException unused) {
                    this.f2679n = i - this.f2678m.size();
                    C3650y.m1617a(c3648x);
                    return;
                }
            }
        } catch (Throwable th) {
            C3650y.m1617a(c3648x);
            throw th;
        }
    }

    /* renamed from: e */
    private void m1567e(String str) {
        if (f2664a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    /* renamed from: f */
    private void m1569f() throws IOException {
        m1558a(this.f2670e);
        Iterator<c> it = this.f2678m.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f2698e == null) {
                while (i < this.f2674i) {
                    this.f2675j += next.f2696c[i];
                    i++;
                }
            } else {
                next.f2698e = null;
                while (i < this.f2674i) {
                    m1558a(next.m1598a(i));
                    m1558a(next.m1600b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m1570g() throws IOException {
        synchronized (this) {
            if (this.f2676k != null) {
                this.f2676k.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2670e), C3650y.f2726a));
            try {
                bufferedWriter.write("libcore.io.DiskLruCache");
                bufferedWriter.write("\n");
                bufferedWriter.write("1");
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.f2672g));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.f2674i));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (c cVar : this.f2678m.values()) {
                    bufferedWriter.write(cVar.f2698e != null ? "DIRTY " + cVar.f2695b + '\n' : "CLEAN " + cVar.f2695b + cVar.m1599a() + '\n');
                }
                bufferedWriter.close();
                if (this.f2669d.exists()) {
                    m1559a(this.f2669d, this.f2671f, true);
                }
                m1559a(this.f2670e, this.f2669d, false);
                this.f2671f.delete();
                this.f2676k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2669d, true), C3650y.f2726a));
            } catch (Throwable th) {
                bufferedWriter.close();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public boolean m1571h() {
        int i = this.f2679n;
        return i >= 2000 && i >= this.f2678m.size();
    }

    /* renamed from: i */
    private void m1572i() {
        if (this.f2676k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m1573j() throws IOException {
        while (true) {
            if (this.f2675j <= this.f2673h && this.f2678m.size() <= this.f2677l) {
                return;
            }
            String key = this.f2678m.entrySet().iterator().next().getKey();
            m1579c(key);
            InterfaceC3646w interfaceC3646w = this.f2680o;
            if (interfaceC3646w != null) {
                interfaceC3646w.m1604a(key);
            }
        }
    }

    /* renamed from: a */
    public b m1574a(String str) throws IOException {
        b bVar;
        synchronized (this) {
            m1572i();
            m1567e(str);
            c cVar = this.f2678m.get(str);
            if (cVar != null && cVar.f2697d) {
                InputStream[] inputStreamArr = new InputStream[this.f2674i];
                for (int i = 0; i < this.f2674i; i++) {
                    try {
                        inputStreamArr[i] = new FileInputStream(cVar.m1598a(i));
                    } catch (FileNotFoundException unused) {
                        for (int i2 = 0; i2 < this.f2674i && inputStreamArr[i2] != null; i2++) {
                            C3650y.m1617a(inputStreamArr[i2]);
                        }
                    }
                }
                this.f2679n++;
                this.f2676k.append((CharSequence) ("READ " + str + '\n'));
                if (m1571h()) {
                    m1555a().submit(this.f2682r);
                }
                bVar = new b(this, str, cVar.f2699f, inputStreamArr, cVar.f2696c, null);
            }
            bVar = null;
        }
        return bVar;
    }

    /* renamed from: a */
    public void m1575a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.f2677l = i;
    }

    /* renamed from: b */
    public a m1576b(String str) throws IOException {
        return m1552a(str, -1L);
    }

    /* renamed from: b */
    public void m1577b() throws IOException {
        synchronized (this) {
            m1572i();
            m1573j();
            this.f2676k.flush();
        }
    }

    /* renamed from: c */
    public void m1578c() throws IOException {
        close();
        C3650y.m1618a(this.f2668c);
    }

    /* renamed from: c */
    public boolean m1579c(String str) throws IOException {
        boolean z;
        synchronized (this) {
            m1572i();
            m1567e(str);
            c cVar = this.f2678m.get(str);
            z = false;
            z = false;
            if (cVar != null && cVar.f2698e == null) {
                for (int i = 0; i < this.f2674i; i++) {
                    File m1598a = cVar.m1598a(i);
                    if (m1598a.exists() && !m1598a.delete()) {
                        throw new IOException("failed to delete " + m1598a);
                    }
                    this.f2675j -= cVar.f2696c[i];
                    cVar.f2696c[i] = 0;
                }
                this.f2679n++;
                this.f2676k.append((CharSequence) ("REMOVE " + str + '\n'));
                this.f2678m.remove(str);
                if (m1571h()) {
                    m1555a().submit(this.f2682r);
                }
                z = true;
            }
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            if (this.f2676k != null) {
                Iterator it = new ArrayList(this.f2678m.values()).iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.f2698e != null) {
                        cVar.f2698e.m1585b();
                    }
                }
                m1573j();
                this.f2676k.close();
                this.f2676k = null;
            }
        }
    }
}
