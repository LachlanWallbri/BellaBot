package com.loc;

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

/* compiled from: DiskLruCache.java */
/* renamed from: com.loc.be */
/* loaded from: classes4.dex */
public final class C3830be implements Closeable {

    /* renamed from: c */
    private final File f3636c;

    /* renamed from: d */
    private final File f3637d;

    /* renamed from: e */
    private final File f3638e;

    /* renamed from: f */
    private final File f3639f;

    /* renamed from: h */
    private long f3641h;

    /* renamed from: k */
    private Writer f3644k;

    /* renamed from: n */
    private int f3647n;

    /* renamed from: o */
    private InterfaceC3831bf f3648o;

    /* renamed from: a */
    static final Pattern f3632a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* renamed from: q */
    private static final ThreadFactory f3634q = new ThreadFactory() { // from class: com.loc.be.1

        /* renamed from: a */
        private final AtomicInteger f3651a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f3651a.getAndIncrement());
        }
    };

    /* renamed from: b */
    static ThreadPoolExecutor f3633b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), f3634q);

    /* renamed from: s */
    private static final OutputStream f3635s = new OutputStream() { // from class: com.loc.be.3
        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
        }
    };

    /* renamed from: j */
    private long f3643j = 0;

    /* renamed from: l */
    private int f3645l = 1000;

    /* renamed from: m */
    private final LinkedHashMap<String, c> f3646m = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: p */
    private long f3649p = 0;

    /* renamed from: r */
    private final Callable<Void> f3650r = new Callable<Void>() { // from class: com.loc.be.2
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (C3830be.this) {
                if (C3830be.this.f3644k == null) {
                    return null;
                }
                C3830be.this.m2565l();
                if (C3830be.this.m2563j()) {
                    C3830be.this.m2562i();
                    C3830be.m2554e(C3830be.this);
                }
                return null;
            }
        }
    };

    /* renamed from: g */
    private final int f3640g = 1;

    /* renamed from: i */
    private final int f3642i = 1;

    /* compiled from: DiskLruCache.java */
    /* renamed from: com.loc.be$a */
    /* loaded from: classes4.dex */
    public final class a {

        /* renamed from: b */
        private final c f3654b;

        /* renamed from: c */
        private final boolean[] f3655c;

        /* renamed from: d */
        private boolean f3656d;

        /* renamed from: e */
        private boolean f3657e;

        /* compiled from: DiskLruCache.java */
        /* renamed from: com.loc.be$a$a, reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        private class C9031a extends FilterOutputStream {
            private C9031a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ C9031a(a aVar, OutputStream outputStream, byte b) {
                this(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.m2577c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.m2577c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.m2577c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.m2577c(a.this);
                }
            }
        }

        private a(c cVar) {
            this.f3654b = cVar;
            this.f3655c = cVar.f3667d ? null : new boolean[C3830be.this.f3642i];
        }

        /* synthetic */ a(C3830be c3830be, c cVar, byte b) {
            this(cVar);
        }

        /* renamed from: c */
        static /* synthetic */ boolean m2577c(a aVar) {
            aVar.f3656d = true;
            return true;
        }

        /* renamed from: a */
        public final OutputStream m2578a() throws IOException {
            FileOutputStream fileOutputStream;
            C9031a c9031a;
            if (C3830be.this.f3642i <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + C3830be.this.f3642i);
            }
            synchronized (C3830be.this) {
                if (this.f3654b.f3668e != this) {
                    throw new IllegalStateException();
                }
                byte b = 0;
                if (!this.f3654b.f3667d) {
                    this.f3655c[0] = true;
                }
                File m2594b = this.f3654b.m2594b(0);
                try {
                    fileOutputStream = new FileOutputStream(m2594b);
                } catch (FileNotFoundException unused) {
                    C3830be.this.f3636c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(m2594b);
                    } catch (FileNotFoundException unused2) {
                        return C3830be.f3635s;
                    }
                }
                c9031a = new C9031a(this, fileOutputStream, b);
            }
            return c9031a;
        }

        /* renamed from: b */
        public final void m2579b() throws IOException {
            if (this.f3656d) {
                C3830be.this.m2546a(this, false);
                C3830be.this.m2572c(this.f3654b.f3665b);
            } else {
                C3830be.this.m2546a(this, true);
            }
            this.f3657e = true;
        }

        /* renamed from: c */
        public final void m2580c() throws IOException {
            C3830be.this.m2546a(this, false);
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: com.loc.be$b */
    /* loaded from: classes4.dex */
    public final class b implements Closeable {

        /* renamed from: b */
        private final String f3660b;

        /* renamed from: c */
        private final long f3661c;

        /* renamed from: d */
        private final InputStream[] f3662d;

        /* renamed from: e */
        private final long[] f3663e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f3660b = str;
            this.f3661c = j;
            this.f3662d = inputStreamArr;
            this.f3663e = jArr;
        }

        /* synthetic */ b(C3830be c3830be, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(str, j, inputStreamArr, jArr);
        }

        /* renamed from: a */
        public final InputStream m2581a() {
            return this.f3662d[0];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            for (InputStream inputStream : this.f3662d) {
                C3833bh.m2598a(inputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskLruCache.java */
    /* renamed from: com.loc.be$c */
    /* loaded from: classes4.dex */
    public final class c {

        /* renamed from: b */
        private final String f3665b;

        /* renamed from: c */
        private final long[] f3666c;

        /* renamed from: d */
        private boolean f3667d;

        /* renamed from: e */
        private a f3668e;

        /* renamed from: f */
        private long f3669f;

        private c(String str) {
            this.f3665b = str;
            this.f3666c = new long[C3830be.this.f3642i];
        }

        /* synthetic */ c(C3830be c3830be, String str, byte b) {
            this(str);
        }

        /* renamed from: a */
        private static IOException m2584a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        static /* synthetic */ void m2585a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != C3830be.this.f3642i) {
                throw m2584a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    cVar.f3666c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw m2584a(strArr);
                }
            }
        }

        /* renamed from: a */
        static /* synthetic */ boolean m2586a(c cVar) {
            cVar.f3667d = true;
            return true;
        }

        /* renamed from: a */
        public final File m2592a(int i) {
            return new File(C3830be.this.f3636c, this.f3665b + "." + i);
        }

        /* renamed from: a */
        public final String m2593a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.f3666c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* renamed from: b */
        public final File m2594b(int i) {
            return new File(C3830be.this.f3636c, this.f3665b + "." + i + ".tmp");
        }
    }

    private C3830be(File file, long j) {
        this.f3636c = file;
        this.f3637d = new File(file, "journal");
        this.f3638e = new File(file, "journal.tmp");
        this.f3639f = new File(file, "journal.bkp");
        this.f3641h = j;
    }

    /* renamed from: a */
    public static C3830be m2543a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                m2549a(file2, file3, false);
            }
        }
        C3830be c3830be = new C3830be(file, j);
        if (c3830be.f3637d.exists()) {
            try {
                c3830be.m2560g();
                c3830be.m2561h();
                c3830be.f3644k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c3830be.f3637d, true), C3833bh.f3676a));
                return c3830be;
            } catch (Throwable unused) {
                c3830be.m2573d();
            }
        }
        file.mkdirs();
        C3830be c3830be2 = new C3830be(file, j);
        c3830be2.m2562i();
        return c3830be2;
    }

    /* renamed from: a */
    public static void m2545a() {
        ThreadPoolExecutor threadPoolExecutor = f3633b;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        f3633b.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m2546a(a aVar, boolean z) throws IOException {
        c cVar = aVar.f3654b;
        if (cVar.f3668e != aVar) {
            throw new IllegalStateException();
        }
        if (z && !cVar.f3667d) {
            for (int i = 0; i < this.f3642i; i++) {
                if (!aVar.f3655c[i]) {
                    aVar.m2580c();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!cVar.m2594b(i).exists()) {
                    aVar.m2580c();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.f3642i; i2++) {
            File m2594b = cVar.m2594b(i2);
            if (!z) {
                m2548a(m2594b);
            } else if (m2594b.exists()) {
                File m2592a = cVar.m2592a(i2);
                m2594b.renameTo(m2592a);
                long j = cVar.f3666c[i2];
                long length = m2592a.length();
                cVar.f3666c[i2] = length;
                this.f3643j = (this.f3643j - j) + length;
            }
        }
        this.f3647n++;
        cVar.f3668e = null;
        if (cVar.f3667d || z) {
            c.m2586a(cVar);
            this.f3644k.write("CLEAN " + cVar.f3665b + cVar.m2593a() + '\n');
            if (z) {
                long j2 = this.f3649p;
                this.f3649p = 1 + j2;
                cVar.f3669f = j2;
            }
        } else {
            this.f3646m.remove(cVar.f3665b);
            this.f3644k.write("REMOVE " + cVar.f3665b + '\n');
        }
        this.f3644k.flush();
        if (this.f3643j > this.f3641h || m2563j()) {
            m2558f().submit(this.f3650r);
        }
    }

    /* renamed from: a */
    private static void m2548a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m2549a(File file, File file2, boolean z) throws IOException {
        if (z) {
            m2548a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: d */
    private synchronized a m2552d(String str) throws IOException {
        m2564k();
        m2556e(str);
        c cVar = this.f3646m.get(str);
        byte b2 = 0;
        if (cVar == null) {
            cVar = new c(this, str, b2);
            this.f3646m.put(str, cVar);
        } else if (cVar.f3668e != null) {
            return null;
        }
        a aVar = new a(this, cVar, b2);
        cVar.f3668e = aVar;
        this.f3644k.write("DIRTY " + str + '\n');
        this.f3644k.flush();
        return aVar;
    }

    /* renamed from: e */
    static /* synthetic */ int m2554e(C3830be c3830be) {
        c3830be.f3647n = 0;
        return 0;
    }

    /* renamed from: e */
    private static void m2556e(String str) {
        if (f3632a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    /* renamed from: f */
    private static ThreadPoolExecutor m2558f() {
        try {
            if (f3633b == null || f3633b.isShutdown()) {
                f3633b = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f3634q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f3633b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f5, code lost:
    
        throw new java.io.IOException("unexpected journal line: " + r3);
     */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m2560g() throws IOException {
        String substring;
        C3832bg c3832bg = new C3832bg(new FileInputStream(this.f3637d), C3833bh.f3676a);
        try {
            String m2597a = c3832bg.m2597a();
            String m2597a2 = c3832bg.m2597a();
            String m2597a3 = c3832bg.m2597a();
            String m2597a4 = c3832bg.m2597a();
            String m2597a5 = c3832bg.m2597a();
            if (!"libcore.io.DiskLruCache".equals(m2597a) || !"1".equals(m2597a2) || !Integer.toString(this.f3640g).equals(m2597a3) || !Integer.toString(this.f3642i).equals(m2597a4) || !"".equals(m2597a5)) {
                throw new IOException("unexpected journal header: [" + m2597a + ", " + m2597a2 + ", " + m2597a4 + ", " + m2597a5 + "]");
            }
            byte b2 = 0;
            int i = 0;
            while (true) {
                try {
                    String m2597a6 = c3832bg.m2597a();
                    int indexOf = m2597a6.indexOf(32);
                    if (indexOf == -1) {
                        throw new IOException("unexpected journal line: " + m2597a6);
                    }
                    int i2 = indexOf + 1;
                    int indexOf2 = m2597a6.indexOf(32, i2);
                    if (indexOf2 == -1) {
                        substring = m2597a6.substring(i2);
                        if (indexOf == 6 && m2597a6.startsWith("REMOVE")) {
                            this.f3646m.remove(substring);
                            i++;
                        }
                    } else {
                        substring = m2597a6.substring(i2, indexOf2);
                    }
                    c cVar = this.f3646m.get(substring);
                    if (cVar == null) {
                        cVar = new c(this, substring, b2);
                        this.f3646m.put(substring, cVar);
                    }
                    if (indexOf2 != -1 && indexOf == 5 && m2597a6.startsWith("CLEAN")) {
                        String[] split = m2597a6.substring(indexOf2 + 1).split(" ");
                        c.m2586a(cVar);
                        cVar.f3668e = null;
                        c.m2585a(cVar, split);
                    } else if (indexOf2 == -1 && indexOf == 5 && m2597a6.startsWith("DIRTY")) {
                        cVar.f3668e = new a(this, cVar, b2);
                    } else if (indexOf2 != -1 || indexOf != 4 || !m2597a6.startsWith("READ")) {
                        break;
                    }
                    i++;
                } catch (EOFException unused) {
                    this.f3647n = i - this.f3646m.size();
                    C3833bh.m2598a(c3832bg);
                    return;
                }
            }
        } catch (Throwable th) {
            C3833bh.m2598a(c3832bg);
            throw th;
        }
    }

    /* renamed from: h */
    private void m2561h() throws IOException {
        m2548a(this.f3638e);
        Iterator<c> it = this.f3646m.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f3668e == null) {
                while (i < this.f3642i) {
                    this.f3643j += next.f3666c[i];
                    i++;
                }
            } else {
                next.f3668e = null;
                while (i < this.f3642i) {
                    m2548a(next.m2592a(i));
                    m2548a(next.m2594b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public synchronized void m2562i() throws IOException {
        if (this.f3644k != null) {
            this.f3644k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3638e), C3833bh.f3676a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3640g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3642i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (c cVar : this.f3646m.values()) {
                bufferedWriter.write(cVar.f3668e != null ? "DIRTY " + cVar.f3665b + '\n' : "CLEAN " + cVar.f3665b + cVar.m2593a() + '\n');
            }
            bufferedWriter.close();
            if (this.f3637d.exists()) {
                m2549a(this.f3637d, this.f3639f, true);
            }
            m2549a(this.f3638e, this.f3637d, false);
            this.f3639f.delete();
            this.f3644k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3637d, true), C3833bh.f3676a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public boolean m2563j() {
        int i = this.f3647n;
        return i >= 2000 && i >= this.f3646m.size();
    }

    /* renamed from: k */
    private void m2564k() {
        if (this.f3644k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m2565l() throws IOException {
        while (true) {
            if (this.f3643j <= this.f3641h && this.f3646m.size() <= this.f3645l) {
                return;
            }
            String key = this.f3646m.entrySet().iterator().next().getKey();
            m2572c(key);
            InterfaceC3831bf interfaceC3831bf = this.f3648o;
            if (interfaceC3831bf != null) {
                interfaceC3831bf.mo2415a(key);
            }
        }
    }

    /* renamed from: a */
    public final synchronized b m2566a(String str) throws IOException {
        m2564k();
        m2556e(str);
        c cVar = this.f3646m.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.f3667d) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.f3642i];
        for (int i = 0; i < this.f3642i; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(cVar.m2592a(i));
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.f3642i && inputStreamArr[i2] != null; i2++) {
                    C3833bh.m2598a(inputStreamArr[i2]);
                }
                return null;
            }
        }
        this.f3647n++;
        this.f3644k.append((CharSequence) ("READ " + str + '\n'));
        if (m2563j()) {
            m2558f().submit(this.f3650r);
        }
        return new b(this, str, cVar.f3669f, inputStreamArr, cVar.f3666c, (byte) 0);
    }

    /* renamed from: a */
    public final void m2567a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.f3645l = i;
    }

    /* renamed from: a */
    public final void m2568a(InterfaceC3831bf interfaceC3831bf) {
        this.f3648o = interfaceC3831bf;
    }

    /* renamed from: b */
    public final a m2569b(String str) throws IOException {
        return m2552d(str);
    }

    /* renamed from: b */
    public final synchronized boolean m2570b() {
        return this.f3644k == null;
    }

    /* renamed from: c */
    public final synchronized void m2571c() throws IOException {
        m2564k();
        m2565l();
        this.f3644k.flush();
    }

    /* renamed from: c */
    public final synchronized boolean m2572c(String str) throws IOException {
        m2564k();
        m2556e(str);
        c cVar = this.f3646m.get(str);
        if (cVar != null && cVar.f3668e == null) {
            for (int i = 0; i < this.f3642i; i++) {
                File m2592a = cVar.m2592a(i);
                if (m2592a.exists() && !m2592a.delete()) {
                    throw new IOException("failed to delete " + m2592a);
                }
                this.f3643j -= cVar.f3666c[i];
                cVar.f3666c[i] = 0;
            }
            this.f3647n++;
            this.f3644k.append((CharSequence) ("REMOVE " + str + '\n'));
            this.f3646m.remove(str);
            if (m2563j()) {
                m2558f().submit(this.f3650r);
            }
            return true;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.f3644k == null) {
            return;
        }
        Iterator it = new ArrayList(this.f3646m.values()).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.f3668e != null) {
                cVar.f3668e.m2580c();
            }
        }
        m2565l();
        this.f3644k.close();
        this.f3644k = null;
    }

    /* renamed from: d */
    public final void m2573d() throws IOException {
        close();
        C3833bh.m2599a(this.f3636c);
    }
}
