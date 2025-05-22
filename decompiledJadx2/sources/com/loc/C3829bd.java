package com.loc;

import com.loc.C3823ay;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/* compiled from: Utils.java */
/* renamed from: com.loc.bd */
/* loaded from: classes4.dex */
public final class C3829bd {
    /* renamed from: a */
    public static int m2537a(String str, String str2) {
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            int i = 0;
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0 || (i = split[i2].compareTo(split2[i2])) != 0) {
                    break;
                }
            }
            return i != 0 ? i : split.length - split2.length;
        } catch (Exception e) {
            C3897w.m3249a(e, "Utils", "compareVersion");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static PublicKey m2538a() {
        ByteArrayInputStream byteArrayInputStream;
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            byteArrayInputStream = new ByteArrayInputStream(C3889o.m3182b("MIIDRzCCAi+gAwIBAgIEeuDbsDANBgkqhkiG9w0BAQsFADBTMQswCQYDVQQGEwJjbjELMAkGA1UECBMCYmoxCzAJBgNVBAcTAmJqMQ0wCwYDVQQKEwRvcGVuMQ4wDAYDVQQLEwVnYW9kZTELMAkGA1UEAxMCUWkwIBcNMTYwODAxMDE0ODMwWhgPMjA3MTA1MDUwMTQ4MzBaMFMxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJiajELMAkGA1UEBxMCYmoxDTALBgNVBAoTBG9wZW4xDjAMBgNVBAsTBWdhb2RlMQswCQYDVQQDEwJRaTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKpL13mZm4q6AFP5csQE7130Lwq8m+HICy3rBARd9vbw5Cb1wFF96KdhC5P/aASlrPb+6MSyP1nE97p3ygKJWsgxExyvVuOvh1KUqOFuK15oY7JKTk6L4eLCbkBJZV2DLffpW0HGiRpmFG8LJR0sjNOoubSd5R/6XoBwyRglsyVHprjrK2qDRvT3Edgtfvxp4HnUzMsDD3CJRtgsaDw6ECyF7fhYKEz9I6OEEVsPlpbgzRmhSeFDL77/k1mhPve1ZyKGlPcxvSSdLSAlV0hzr5NKlujHll7BbouwDnr6l/0O44AzZ0V/ieft1iBkSLirnlm56uI/8jdh8ANrD1fW4ZUCAwEAAaMhMB8wHQYDVR0OBBYEFBzudtI5UKRvHGDV+VQRzItIj3PqMA0GCSqGSIb3DQEBCwUAA4IBAQBS2EGndgvIBnf7Ce4IhDbm7F5h4L+6TYGmT9acnQbEFY8oUoFblMDgg+cETT44jU/elwbJJVmKhj/WRQl+AdSALBAgDvxq1AcjlGg+c8H3pa2BWlrxNJP9MFLIEI5bA8m5og/Epjut50uemZ9ggoNmJeW0N/a6D8euhYJKOYngUQqDu6cwLj1Ec0ptwrNRbvRXXgzjfJMPE/ii4K/b8JZ+QN2d/bl7QEvKWBSzVueZifV659qAbMh6C9TCVstWWfV53Z3Vyt+duDNU5ed7aWao42Ppw4VHslrJW0V6BXDUhhzgXx28UWY78W7LmYGCtC8PfDId2+k4tPoTNPM6HHP5"));
        } catch (Throwable th) {
            th = th;
            byteArrayInputStream = null;
        }
        try {
            return ((X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream)).getPublicKey();
        } catch (Throwable th2) {
            th = th2;
            try {
                C3897w.m3249a(th, "DyLoader", "init");
                try {
                    m2539a(byteArrayInputStream);
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                return null;
            } finally {
                try {
                    m2539a(byteArrayInputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2539a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2540a(List<C3828bc> list) {
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                C3828bc c3828bc = list.get(i);
                C3828bc c3828bc2 = list.get(i3);
                if (m2537a(c3828bc2.m2527e(), c3828bc.m2527e()) > 0) {
                    list.set(i, c3828bc2);
                    list.set(i3, c3828bc);
                }
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2541a(C3804af c3804af, String str, String str2, C3893s c3893s) {
        C3828bc m2504a = C3823ay.a.m2504a(c3804af, str);
        return m2504a != null && c3893s.m3208b().equals(m2504a.m2526d()) && m2542b(str2, m2504a.m2523b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m2542b(String str, String str2) {
        String m3185a = C3890p.m3185a(str);
        return m3185a != null && m3185a.equalsIgnoreCase(str2);
    }
}
