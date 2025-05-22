package com.iflytek.cloud.record;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.iflytek.cloud.record.d */
/* loaded from: classes3.dex */
public class C3684d {

    /* renamed from: a */
    private RandomAccessFile f2931a;

    /* renamed from: b */
    private short f2932b;

    /* renamed from: c */
    private int f2933c;

    /* renamed from: d */
    private short f2934d;

    public C3684d(File file, int i) throws IOException {
        m1751a(file, (short) 1, i, (short) 16);
    }

    /* renamed from: a */
    private boolean m1751a(File file, short s, int i, short s2) throws IOException {
        if (file == null) {
            return false;
        }
        this.f2931a = new RandomAccessFile(file, "rw");
        RandomAccessFile randomAccessFile = this.f2931a;
        if (randomAccessFile == null) {
            return false;
        }
        this.f2932b = s;
        this.f2933c = i;
        this.f2934d = s2;
        randomAccessFile.write(new byte[44]);
        return true;
    }

    /* renamed from: a */
    public void m1754a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.f2931a.write(str.charAt(i));
        }
    }

    /* renamed from: a */
    public void m1753a(int i) throws IOException {
        this.f2931a.write(i >> 0);
        this.f2931a.write(i >> 8);
        this.f2931a.write(i >> 16);
        this.f2931a.write(i >> 24);
    }

    /* renamed from: a */
    public void m1755a(short s) throws IOException {
        this.f2931a.write(s >> 0);
        this.f2931a.write(s >> 8);
    }

    /* renamed from: a */
    public int m1752a() throws IOException {
        return (int) (this.f2931a.length() - 44);
    }

    /* renamed from: b */
    public void m1756b() throws IOException {
        this.f2931a.seek(0L);
        m1754a("RIFF");
        m1753a(m1752a() + 36);
        m1754a("WAVE");
        m1754a("fmt ");
        m1753a(16);
        m1755a((short) 1);
        m1755a(this.f2932b);
        m1753a(this.f2933c);
        m1753a(((this.f2932b * this.f2933c) * this.f2934d) / 8);
        m1755a((short) ((this.f2932b * this.f2934d) / 8));
        m1755a(this.f2934d);
        m1754a("data");
        m1753a(m1752a());
    }

    /* renamed from: c */
    public void m1757c() throws IOException {
        RandomAccessFile randomAccessFile = this.f2931a;
        if (randomAccessFile != null) {
            randomAccessFile.close();
            this.f2931a = null;
        }
    }
}
