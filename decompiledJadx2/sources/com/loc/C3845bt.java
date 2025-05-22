package com.loc;

import android.content.Context;
import com.loc.C3830be;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

/* compiled from: Utils.java */
/* renamed from: com.loc.bt */
/* loaded from: classes4.dex */
public class C3845bt {
    /* renamed from: a */
    public static String m2646a() {
        return C3894t.m3223a(new Date().getTime());
    }

    /* renamed from: a */
    public static String m2647a(Context context, C3893s c3893s) {
        StringBuilder sb = new StringBuilder();
        try {
            String m3156e = C3888n.m3156e(context);
            sb.append("\"sim\":\"");
            sb.append(m3156e);
            sb.append("\",\"sdkversion\":\"");
            sb.append(c3893s.m3209c());
            sb.append("\",\"product\":\"");
            sb.append(c3893s.m3206a());
            sb.append("\",\"ed\":\"");
            sb.append(c3893s.m3210d());
            sb.append("\",\"nt\":\"");
            sb.append(C3888n.m3153c(context));
            sb.append("\",\"np\":\"");
            sb.append(C3888n.m3145a(context));
            sb.append("\",\"mnc\":\"");
            sb.append(C3888n.m3151b(context));
            sb.append("\",\"ant\":\"");
            sb.append(C3888n.m3155d(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m2648a(String str, String str2, int i, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(",\"timestamp\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m2649a(Context context, long j, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        File file = new File(C3898x.m3254a(context, str));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e3) {
                fileOutputStream = null;
                e2 = e3;
            } catch (IOException e4) {
                fileOutputStream = null;
                e = e4;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                }
                throw th;
            }
            try {
                fileOutputStream.write(C3894t.m3232a(String.valueOf(j)));
                try {
                    fileOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            } catch (IOException e6) {
                e = e6;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
            if (0 != 0) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable th7) {
                    th7.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m2650a(Context context, String str, byte[] bArr) {
        C3830be c3830be;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        synchronized (C3845bt.class) {
            Random random = new Random();
            OutputStream outputStream = null;
            try {
                c3830be = C3830be.m2543a(new File(C3898x.m3254a(context, str)), 307200L);
            } catch (Throwable th) {
                th = th;
                c3830be = null;
            }
            try {
                C3830be.a m2569b = c3830be.m2569b(Integer.toString(random.nextInt(100)) + Long.toString(System.nanoTime()));
                outputStream = m2569b.m2578a();
                outputStream.write(bArr);
                m2569b.m2579b();
                c3830be.m2571c();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (c3830be != null) {
                    try {
                        c3830be.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    C3897w.m3249a(th, "Statistics.Utils", "writeToDiskLruCache");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    if (c3830be != null) {
                        try {
                            c3830be.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                        }
                    }
                } finally {
                }
            }
        }
    }

    /* renamed from: a */
    public static byte[] m2651a(Context context, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String m3254a = C3898x.m3254a(context, str);
        C3830be c3830be = null;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            try {
                c3830be = C3830be.m2543a(new File(m3254a), 307200L);
                File file = new File(m3254a);
                if (file.exists()) {
                    for (String str2 : file.list()) {
                        if (str2.contains(".0")) {
                            byteArrayOutputStream.write(m2652a(c3830be, str2.split("\\.")[0], true));
                        }
                    }
                }
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (c3830be != null) {
                    c3830be.close();
                }
            } catch (IOException e2) {
                C3897w.m3249a(e2, "Statistics.Utils", "getContent");
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                if (c3830be != null) {
                    c3830be.close();
                }
                return bArr;
            } catch (Throwable th2) {
                C3897w.m3249a(th2, "Statistics.Utils", "getContent");
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                if (c3830be != null) {
                    c3830be.close();
                }
                return bArr;
            }
            return bArr;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m2652a(C3830be c3830be, String str, boolean z) {
        C3830be.b bVar;
        byte[] bArr = new byte[0];
        InputStream inputStream = null;
        try {
            bVar = c3830be.m2566a(str);
            if (bVar == null) {
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                return bArr;
            }
            try {
                InputStream m2581a = bVar.m2581a();
                if (m2581a == null) {
                    if (m2581a != null) {
                        try {
                            m2581a.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    return bArr;
                }
                bArr = new byte[m2581a.available()];
                m2581a.read(bArr);
                if (z) {
                    c3830be.m2572c(str);
                }
                if (m2581a != null) {
                    try {
                        m2581a.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                return bArr;
            } catch (Throwable th6) {
                th = th6;
                try {
                    C3897w.m3249a(th, "Utils", "readSingleLog");
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    return bArr;
                } finally {
                }
            }
        } catch (Throwable th9) {
            th = th9;
            bVar = null;
        }
    }

    /* renamed from: b */
    public static int m2653b(Context context, String str) {
        try {
            File file = new File(C3898x.m3254a(context, str));
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            C3897w.m3249a(th, "Statistics.Utils", "getFileNum");
            return 0;
        }
    }

    /* renamed from: c */
    public static long m2654c(Context context, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        IOException e;
        FileNotFoundException e2;
        File file = new File(C3898x.m3254a(context, str));
        if (!file.exists()) {
            return 0L;
        }
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e3) {
                    fileInputStream = null;
                    e2 = e3;
                } catch (IOException e4) {
                    fileInputStream = null;
                    e = e4;
                } catch (Throwable th2) {
                    fileInputStream = null;
                    th = th2;
                }
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    long parseLong = Long.parseLong(C3894t.m3226a(bArr));
                    try {
                        fileInputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    return parseLong;
                } catch (FileNotFoundException e5) {
                    e2 = e5;
                    C3897w.m3249a(e2, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return 0L;
                } catch (IOException e6) {
                    e = e6;
                    C3897w.m3249a(e, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return 0L;
                } catch (Throwable th4) {
                    th = th4;
                    C3897w.m3249a(th, "StatisticsManager", "getUpdateTime");
                    try {
                        if (file.exists()) {
                            file.delete();
                        }
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return 0L;
                }
            } catch (Throwable th6) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
                throw th6;
            }
        } catch (Throwable th8) {
            th8.printStackTrace();
        }
    }
}
