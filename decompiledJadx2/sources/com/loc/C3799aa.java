package com.loc;

import android.content.Context;
import android.os.Looper;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* compiled from: AnrLogProcessor.java */
/* renamed from: com.loc.aa */
/* loaded from: classes4.dex */
public final class C3799aa extends AbstractC3802ad {

    /* renamed from: b */
    private static boolean f3547b = true;

    /* renamed from: c */
    private String[] f3548c;

    /* renamed from: d */
    private int f3549d;

    /* renamed from: e */
    private boolean f3550e;

    /* renamed from: f */
    private int f3551f;

    /* JADX INFO: Access modifiers changed from: protected */
    public C3799aa(int i) {
        super(i);
        this.f3548c = new String[10];
        this.f3549d = 0;
        this.f3550e = false;
        this.f3551f = 0;
    }

    /* renamed from: d */
    private String m2387d() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = this.f3549d; i < 10 && i <= 9; i++) {
                sb.append(this.f3548c[i]);
            }
            for (int i2 = 0; i2 < this.f3549d; i2++) {
                sb.append(this.f3548c[i2]);
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "ANRWriter", "getLogInfo");
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0164 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:134:0x00d4 -> B:29:0x015b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:136:0x00cd -> B:29:0x015b). Please report as a decompilation issue!!! */
    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final String mo2388a(List<C3893s> list) {
        FileInputStream fileInputStream;
        C3832bg c3832bg;
        File file;
        boolean z;
        try {
        } catch (IOException e) {
            C3897w.m3249a(e, "ANRWriter", "initLog3");
        } catch (Throwable th) {
            C3897w.m3249a(th, "ANRWriter", "initLog4");
        }
        try {
            try {
                file = new File("/data/anr/traces.txt");
            } catch (FileNotFoundException unused) {
                fileInputStream = null;
                c3832bg = null;
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
                c3832bg = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                c3832bg = null;
            }
            if (!file.exists()) {
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                if (fileInputStream.available() > 1024000) {
                    fileInputStream.skip(r0 - 1024000);
                }
                c3832bg = new C3832bg(fileInputStream, C3833bh.f3676a);
                boolean z2 = false;
                while (true) {
                    try {
                        String trim = c3832bg.m2597a().trim();
                        if (trim.contains("pid")) {
                            while (!trim.startsWith("\"main\"")) {
                                trim = c3832bg.m2597a();
                            }
                            z = true;
                        } else {
                            z = z2;
                        }
                        if (!trim.equals("") || !z) {
                            if (z) {
                                try {
                                    if (this.f3549d > 9) {
                                        this.f3549d = 0;
                                    }
                                    this.f3548c[this.f3549d] = trim;
                                    this.f3549d++;
                                } catch (Throwable th3) {
                                    C3897w.m3249a(th3, "ANRWriter", "addData");
                                }
                                if (this.f3551f == 5) {
                                    break;
                                }
                                if (this.f3550e) {
                                    this.f3551f++;
                                } else {
                                    Iterator<C3893s> it = list.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            C3893s next = it.next();
                                            this.f3550e = m2398a(next.m3212f(), trim);
                                            if (this.f3550e) {
                                                m2408a(next);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            z2 = z;
                        }
                    } catch (EOFException unused2) {
                    } catch (FileNotFoundException unused3) {
                        if (c3832bg != null) {
                            try {
                                c3832bg.close();
                            } catch (IOException e3) {
                                C3897w.m3249a(e3, "ANRWriter", "initLog1");
                            } catch (Throwable th4) {
                                C3897w.m3249a(th4, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (this.f3550e) {
                        }
                    } catch (IOException e4) {
                        e = e4;
                        C3897w.m3249a(e, "ANRWriter", "initLog");
                        if (c3832bg != null) {
                            try {
                                c3832bg.close();
                            } catch (IOException e5) {
                                C3897w.m3249a(e5, "ANRWriter", "initLog1");
                            } catch (Throwable th5) {
                                C3897w.m3249a(th5, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (this.f3550e) {
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        C3897w.m3249a(th, "ANRWriter", "initLog");
                        if (c3832bg != null) {
                            try {
                                c3832bg.close();
                            } catch (IOException e6) {
                                C3897w.m3249a(e6, "ANRWriter", "initLog1");
                            } catch (Throwable th7) {
                                C3897w.m3249a(th7, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (this.f3550e) {
                        }
                    }
                }
            } catch (FileNotFoundException unused4) {
                c3832bg = null;
            } catch (IOException e7) {
                e = e7;
                c3832bg = null;
            } catch (Throwable th8) {
                th = th8;
                c3832bg = null;
            }
            if (this.f3550e) {
                return null;
            }
            return m2387d();
            fileInputStream.close();
            if (this.f3550e) {
            }
            try {
                c3832bg.close();
            } catch (IOException e8) {
                C3897w.m3249a(e8, "ANRWriter", "initLog1");
            } catch (Throwable th9) {
                C3897w.m3249a(th9, "ANRWriter", "initLog2");
            }
            fileInputStream.close();
            if (this.f3550e) {
            }
        } catch (Throwable th10) {
            if (c3832bg != null) {
                try {
                    c3832bg.close();
                } catch (IOException e9) {
                    C3897w.m3249a(e9, "ANRWriter", "initLog1");
                } catch (Throwable th11) {
                    C3897w.m3249a(th11, "ANRWriter", "initLog2");
                }
            }
            if (fileInputStream == null) {
                throw th10;
            }
            try {
                fileInputStream.close();
                throw th10;
            } catch (IOException e10) {
                C3897w.m3249a(e10, "ANRWriter", "initLog3");
                throw th10;
            } catch (Throwable th12) {
                C3897w.m3249a(th12, "ANRWriter", "initLog4");
                throw th10;
            }
        }
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final boolean mo2389a(Context context) {
        if (C3888n.m3164m(context) != 1 || !f3547b) {
            return false;
        }
        f3547b = false;
        synchronized (Looper.getMainLooper()) {
            C3815aq c3815aq = new C3815aq(context);
            C3816ar m2458a = c3815aq.m2458a();
            if (m2458a == null) {
                return true;
            }
            if (!m2458a.m2465c()) {
                return false;
            }
            m2458a.m2464c(false);
            c3815aq.m2459a(m2458a);
            return true;
        }
    }
}
