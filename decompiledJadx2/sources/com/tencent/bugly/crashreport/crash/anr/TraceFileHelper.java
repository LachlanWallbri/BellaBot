package com.tencent.bugly.crashreport.crash.anr;

import com.pudutech.shared.lib_syntime.SystemTimeSetting;
import com.tencent.bugly.proguard.C5940x;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class TraceFileHelper {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    /* loaded from: classes7.dex */
    public static class C5882a {

        /* renamed from: a */
        public long f7885a;

        /* renamed from: b */
        public String f7886b;

        /* renamed from: c */
        public long f7887c;

        /* renamed from: d */
        public Map<String, String[]> f7888d;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    /* loaded from: classes7.dex */
    public interface InterfaceC5883b {
        /* renamed from: a */
        boolean mo3505a(long j);

        /* renamed from: a */
        boolean mo3506a(long j, long j2, String str);

        /* renamed from: a */
        boolean mo3507a(String str, int i, String str2, String str3);
    }

    public static C5882a readTargetDumpInfo(final String str, String str2, final boolean z) {
        if (str != null && str2 != null) {
            final C5882a c5882a = new C5882a();
            readTraceFile(str2, new InterfaceC5883b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.1
                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
                /* renamed from: a */
                public final boolean mo3507a(String str3, int i, String str4, String str5) {
                    C5940x.m3823c("new thread %s", str3);
                    if (C5882a.this.f7885a > 0 && C5882a.this.f7887c > 0 && C5882a.this.f7886b != null) {
                        if (C5882a.this.f7888d == null) {
                            C5882a.this.f7888d = new HashMap();
                        }
                        Map<String, String[]> map = C5882a.this.f7888d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        map.put(str3, new String[]{str4, str5, sb.toString()});
                    }
                    return true;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
                /* renamed from: a */
                public final boolean mo3506a(long j, long j2, String str3) {
                    C5940x.m3823c("new process %s", str3);
                    if (!str3.equals(str)) {
                        return true;
                    }
                    C5882a c5882a2 = C5882a.this;
                    c5882a2.f7885a = j;
                    c5882a2.f7886b = str3;
                    c5882a2.f7887c = j2;
                    return z;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
                /* renamed from: a */
                public final boolean mo3505a(long j) {
                    C5940x.m3823c("process end %d", Long.valueOf(j));
                    return C5882a.this.f7885a <= 0 || C5882a.this.f7887c <= 0 || C5882a.this.f7886b == null;
                }
            });
            if (c5882a.f7885a > 0 && c5882a.f7887c > 0 && c5882a.f7886b != null) {
                return c5882a;
            }
        }
        return null;
    }

    public static C5882a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            C5940x.m3825e("path:%s", str);
            return null;
        }
        final C5882a c5882a = new C5882a();
        readTraceFile(str, new InterfaceC5883b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.2
            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
            /* renamed from: a */
            public final boolean mo3507a(String str2, int i, String str3, String str4) {
                C5940x.m3823c("new thread %s", str2);
                if (C5882a.this.f7888d == null) {
                    C5882a.this.f7888d = new HashMap();
                }
                Map<String, String[]> map = C5882a.this.f7888d;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                map.put(str2, new String[]{str3, str4, sb.toString()});
                return true;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
            /* renamed from: a */
            public final boolean mo3506a(long j, long j2, String str2) {
                C5940x.m3823c("new process %s", str2);
                C5882a c5882a2 = C5882a.this;
                c5882a2.f7885a = j;
                c5882a2.f7886b = str2;
                c5882a2.f7887c = j2;
                return z;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC5883b
            /* renamed from: a */
            public final boolean mo3505a(long j) {
                C5940x.m3823c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (c5882a.f7885a > 0 && c5882a.f7887c > 0 && c5882a.f7886b != null) {
            return c5882a;
        }
        C5940x.m3825e("first dump error %s", c5882a.f7885a + " " + c5882a.f7887c + " " + c5882a.f7886b);
        return null;
    }

    public static void readTraceFile(String str, InterfaceC5883b interfaceC5883b) {
        Throwable th;
        BufferedReader bufferedReader;
        if (str == null || interfaceC5883b == null) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        file.lastModified();
        file.length();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    Pattern compile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                    Pattern compile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                    Pattern compile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                    Pattern compile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SystemTimeSetting.TIME_FORMAT, Locale.US);
                    while (true) {
                        Object[] m3503a = m3503a(bufferedReader, compile);
                        if (m3503a == null) {
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e) {
                                if (C5940x.m3819a(e)) {
                                    return;
                                }
                                e.printStackTrace();
                                return;
                            }
                        }
                        String[] split = m3503a[1].toString().split("\\s");
                        long parseLong = Long.parseLong(split[2]);
                        long time = simpleDateFormat.parse(split[4] + " " + split[5]).getTime();
                        Object[] m3503a2 = m3503a(bufferedReader, compile3);
                        if (m3503a2 == null) {
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e2) {
                                if (C5940x.m3819a(e2)) {
                                    return;
                                }
                                e2.printStackTrace();
                                return;
                            }
                        }
                        Matcher matcher = compile3.matcher(m3503a2[1].toString());
                        matcher.find();
                        matcher.group(1);
                        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                        if (!interfaceC5883b.mo3506a(parseLong, time, matcher.group(1))) {
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e3) {
                                if (C5940x.m3819a(e3)) {
                                    return;
                                }
                                e3.printStackTrace();
                                return;
                            }
                        }
                        while (true) {
                            Object[] m3503a3 = m3503a(bufferedReader, compile4, compile2);
                            if (m3503a3 == null) {
                                break;
                            }
                            if (m3503a3[0] == compile4) {
                                String obj = m3503a3[1].toString();
                                Matcher matcher2 = Pattern.compile("\".+\"").matcher(obj);
                                matcher2.find();
                                String group = matcher2.group();
                                String substring = group.substring(1, group.length() - 1);
                                obj.contains("NATIVE");
                                Matcher matcher3 = Pattern.compile("tid=\\d+").matcher(obj);
                                matcher3.find();
                                String group2 = matcher3.group();
                                interfaceC5883b.mo3507a(substring, Integer.parseInt(group2.substring(group2.indexOf("=") + 1)), m3502a(bufferedReader), m3504b(bufferedReader));
                            } else if (!interfaceC5883b.mo3505a(Long.parseLong(m3503a3[1].toString().split("\\s")[2]))) {
                                try {
                                    bufferedReader.close();
                                    return;
                                } catch (IOException e4) {
                                    if (C5940x.m3819a(e4)) {
                                        return;
                                    }
                                    e4.printStackTrace();
                                    return;
                                }
                            }
                        }
                        simpleDateFormat = simpleDateFormat2;
                    }
                } catch (Exception e5) {
                    e = e5;
                    bufferedReader2 = bufferedReader;
                    if (!C5940x.m3819a(e)) {
                        e.printStackTrace();
                    }
                    C5940x.m3824d("trace open fail:%s : %s", e.getClass().getName(), e.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e6) {
                            if (C5940x.m3819a(e6)) {
                                return;
                            }
                            e6.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (IOException e7) {
                        if (C5940x.m3819a(e7)) {
                            throw th;
                        }
                        e7.printStackTrace();
                        throw th;
                    }
                }
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
    
        if (r5.matcher(r1).matches() == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        return new java.lang.Object[]{r5, r1};
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0003, code lost:
    
        if (r8 != null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
    
        r1 = r7.readLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000a, code lost:
    
        if (r1 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        r2 = r8.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000f, code lost:
    
        if (r4 >= r2) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0011, code lost:
    
        r5 = r8[r4];
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object[] m3503a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader != null) {
        }
        return null;
    }

    /* renamed from: a */
    private static String m3502a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m3504b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().length() <= 0) {
                break;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }
}
