package com.pudutech.light_network.utils;

import com.pudutech.base.Pdlog;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: PuduTrustManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J#\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J%\u0010\u0014\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/light_network/utils/PuduTrustManager;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/X509TrustManager;)V", "TAG", "", "getTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "checkClientTrusted", "", "chain", "", "Ljava/security/cert/X509Certificate;", "authType", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;)V", "checkExpiredException", "", MqttServiceConstants.TRACE_EXCEPTION, "Ljava/security/cert/CertificateException;", "checkNotYetValidException", "checkServerTrusted", "getAcceptedIssuers", "()[Ljava/security/cert/X509Certificate;", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class PuduTrustManager implements X509TrustManager {
    private final String TAG = "Certificate";
    private final X509TrustManager trustManager;

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public PuduTrustManager(X509TrustManager x509TrustManager) {
        this.trustManager = x509TrustManager;
    }

    public final X509TrustManager getTrustManager() {
        return this.trustManager;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Intrinsics.checkParameterIsNotNull(authType, "authType");
        Pdlog.m3273d(this.TAG, "checkClientTrusted");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        Throwable cause;
        Throwable cause2;
        Intrinsics.checkParameterIsNotNull(authType, "authType");
        if (chain != null) {
            try {
                if (this.trustManager != null) {
                    X509TrustManager x509TrustManager = this.trustManager;
                    if (x509TrustManager != null) {
                        x509TrustManager.checkServerTrusted(chain, authType);
                    }
                } else {
                    for (X509Certificate x509Certificate : chain) {
                        x509Certificate.checkValidity();
                    }
                }
                Pdlog.m3273d(this.TAG, "check Server Trusted");
            } catch (CertificateException e) {
                Throwable cause3 = e.getCause();
                Class<?> cls = null;
                Class<?> cls2 = (cause3 == null || (cause2 = cause3.getCause()) == null) ? null : cause2.getClass();
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("catch certificate exception: ");
                sb.append(e.getClass());
                sb.append("'s cause  ");
                Throwable cause4 = e.getCause();
                sb.append(cause4 != null ? cause4.getClass() : null);
                sb.append("'s cause ");
                sb.append(cls2 != null ? cls2.getName() : null);
                objArr[0] = sb.toString();
                Pdlog.m3274e(str, objArr);
                if (checkExpiredException(e) || checkNotYetValidException(e)) {
                    String str2 = this.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("catch time certificate exception: ");
                    sb2.append(e.getClass());
                    sb2.append("'s cause  ");
                    Throwable cause5 = e.getCause();
                    sb2.append(cause5 != null ? cause5.getClass() : null);
                    sb2.append("'s cause ");
                    Throwable cause6 = e.getCause();
                    if (cause6 != null && (cause = cause6.getCause()) != null) {
                        cls = cause.getClass();
                    }
                    sb2.append(cls);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3274e(str2, objArr2);
                    return;
                }
                throw new CertificateException(e);
            }
        }
    }

    private final boolean checkExpiredException(CertificateException exception) {
        for (CertificateException certificateException = exception; certificateException != null; certificateException = certificateException.getCause()) {
            Class<?> cls = certificateException.getClass();
            String name = cls != null ? cls.getName() : null;
            Intrinsics.checkExpressionValueIsNotNull(name, "throwable?.javaClass?.name");
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) "CertificateExpiredException", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final boolean checkNotYetValidException(CertificateException exception) {
        for (CertificateException certificateException = exception; certificateException != null; certificateException = certificateException.getCause()) {
            Class<?> cls = certificateException.getClass();
            String name = cls != null ? cls.getName() : null;
            Intrinsics.checkExpressionValueIsNotNull(name, "throwable?.javaClass?.name");
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) "CertificateNotYetValidException", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
