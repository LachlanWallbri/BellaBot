package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OSSRetryHandler {
    private int maxRetryCount = 2;

    public void setMaxRetryCount(int i) {
        this.maxRetryCount = i;
    }

    public OSSRetryHandler(int i) {
        setMaxRetryCount(i);
    }

    public OSSRetryType shouldRetry(Exception exc, int i) {
        if (i >= this.maxRetryCount) {
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        if (exc instanceof ClientException) {
            if (((ClientException) exc).isCanceledException().booleanValue()) {
                return OSSRetryType.OSSRetryTypeShouldNotRetry;
            }
            Exception exc2 = (Exception) exc.getCause();
            if ((exc2 instanceof InterruptedIOException) && !(exc2 instanceof SocketTimeoutException)) {
                OSSLog.logError("[shouldRetry] - is interrupted!");
                return OSSRetryType.OSSRetryTypeShouldNotRetry;
            }
            if (exc2 instanceof IllegalArgumentException) {
                return OSSRetryType.OSSRetryTypeShouldNotRetry;
            }
            OSSLog.logDebug("shouldRetry - " + exc.toString());
            exc.getCause().printStackTrace();
            return OSSRetryType.OSSRetryTypeShouldRetry;
        }
        if (exc instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) exc;
            if (serviceException.getErrorCode() != null && serviceException.getErrorCode().equalsIgnoreCase("RequestTimeTooSkewed")) {
                return OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry;
            }
            if (serviceException.getStatusCode() >= 500) {
                return OSSRetryType.OSSRetryTypeShouldRetry;
            }
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        return OSSRetryType.OSSRetryTypeShouldNotRetry;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.alibaba.sdk.android.oss.internal.OSSRetryHandler$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C08261 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$sdk$android$oss$internal$OSSRetryType = new int[OSSRetryType.values().length];

        static {
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$internal$OSSRetryType[OSSRetryType.OSSRetryTypeShouldRetry.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
