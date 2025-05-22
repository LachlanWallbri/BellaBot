package com.pudutech.light_network;

import android.content.Context;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.light_network.intercepter.HeaderInterceptor;
import com.pudutech.light_network.intercepter.HttpLogInterceptor;
import com.pudutech.light_network.intercepter.RequestInterceptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class OKHttpClient {
    private static final String TAG = "OKHttpClient";
    public static final int TIME_OUT_SECONDS = 60;
    private static boolean sDebug = true;
    private static HeaderInterceptor sHeaderInterceptor;
    private static InputStream sInputStream;
    private static RequestInterceptor sRequestInterceptor;

    public static OkHttpClient getInstance(Context context, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z) {
        if (headerInterceptor != null) {
            sHeaderInterceptor = headerInterceptor;
        }
        if (requestInterceptor != null) {
            sRequestInterceptor = requestInterceptor;
        }
        sInputStream = null;
        sDebug = z;
        return getHttpsOkHttpClient(context, false);
    }

    public static OkHttpClient getInstance4Https(Context context, HttpsServiceType httpsServiceType, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z) {
        return getInstance4Https(context, httpsServiceType, headerInterceptor, requestInterceptor, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.pudutech.light_network.OKHttpClient$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C47502 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$light_network$HttpsServiceType;

        static {
            int[] iArr = new int[HttpsServiceType.values().length];
            $SwitchMap$com$pudutech$light_network$HttpsServiceType = iArr;
            try {
                iArr[HttpsServiceType.Upgrade.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$light_network$HttpsServiceType[HttpsServiceType.Cloud.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$light_network$HttpsServiceType[HttpsServiceType.IOT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$light_network$HttpsServiceType[HttpsServiceType.IOTSR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$light_network$HttpsServiceType[HttpsServiceType.DEBUGSRV.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$light_network$HttpsServiceType[HttpsServiceType.HTTP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static OkHttpClient getInstance4Https(Context context, HttpsServiceType httpsServiceType, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z, InputStream inputStream) {
        int i = C47502.$SwitchMap$com$pudutech$light_network$HttpsServiceType[httpsServiceType.ordinal()];
        if (i == 1) {
            try {
                sInputStream = context.getAssets().open(BuildConfig.UpgradeCA_FILE);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "open upgrade ca file exception", e.getLocalizedMessage());
                sInputStream = null;
            }
        } else if (i == 2) {
            try {
                sInputStream = context.getAssets().open(BuildConfig.CloudCA_FILE);
            } catch (Exception e2) {
                Pdlog.m3274e(TAG, "open cloud ca file exception", e2.getLocalizedMessage());
                sInputStream = null;
            }
        } else if (i == 3) {
            try {
                sInputStream = context.getAssets().open(BuildConfig.IotCA_FILE);
            } catch (Exception e3) {
                Pdlog.m3274e(TAG, "open iot ca file exception", e3.getLocalizedMessage());
                sInputStream = null;
            }
        } else if (i == 4) {
            try {
                sInputStream = context.getAssets().open(BuildConfig.IotSR_FILE);
            } catch (Exception e4) {
                Pdlog.m3274e(TAG, "open iot secret ca file exception", e4.getLocalizedMessage());
                sInputStream = null;
            }
        } else if (i == 5) {
            try {
                sInputStream = inputStream;
            } catch (Exception e5) {
                Pdlog.m3274e(TAG, "open iot secret ca file exception", e5.getLocalizedMessage());
                sInputStream = null;
            }
        } else {
            sInputStream = null;
        }
        if (headerInterceptor != null) {
            sHeaderInterceptor = headerInterceptor;
        }
        if (requestInterceptor != null) {
            sRequestInterceptor = requestInterceptor;
        }
        sDebug = z;
        return getHttpsOkHttpClient(context, Boolean.valueOf(httpsServiceType != HttpsServiceType.DEBUGSRV));
    }

    private static OkHttpClient getHttpsOkHttpClient(Context context, Boolean bool) {
        try {
            try {
                Pdlog.m3273d(TAG, "start certificate");
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (!HttpConfig.INSTANCE.isDebug()) {
                    try {
                        builder = HttpsUtils.INSTANCE.setSuportHttpsParams(context, builder, sInputStream, bool.booleanValue());
                    } catch (Exception e) {
                        Pdlog.m3274e(TAG, Log.getStackTraceString(e));
                    }
                }
                builder.hostnameVerifier(new HostnameVerifier() { // from class: com.pudutech.light_network.OKHttpClient.1
                    @Override // javax.net.ssl.HostnameVerifier
                    public boolean verify(String str, SSLSession sSLSession) {
                        Log.d("===", "hostname: " + str + " session protocol: " + sSLSession.getProtocol());
                        return true;
                    }
                });
                if (sRequestInterceptor != null) {
                    builder.addInterceptor(sRequestInterceptor);
                }
                if (sHeaderInterceptor != null) {
                    builder.addInterceptor(sHeaderInterceptor);
                }
                builder.addInterceptor(new HttpLogInterceptor(sDebug)).connectTimeout(HttpConfig.INSTANCE.getConnectTimeout(), TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS);
                return builder.build();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } finally {
            InputStream inputStream = sInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
}
