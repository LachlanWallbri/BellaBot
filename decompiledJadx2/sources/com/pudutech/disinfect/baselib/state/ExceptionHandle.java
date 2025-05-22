package com.pudutech.disinfect.baselib.state;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.loc.C3898x;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import retrofit2.HttpException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ExceptionHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ExceptionHandle;", "", "()V", "handleException", "Lcom/pudutech/disinfect/baselib/state/AppException;", C3898x.f4338g, "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ExceptionHandle {
    public static final ExceptionHandle INSTANCE = new ExceptionHandle();

    private ExceptionHandle() {
    }

    public final AppException handleException(Throwable e) {
        if (e != null) {
            if (e instanceof HttpException) {
                return new AppException(Error.NETWORK_ERROR, e);
            }
            if ((e instanceof JsonParseException) || (e instanceof JSONException) || (e instanceof ParseException) || (e instanceof MalformedJsonException)) {
                return new AppException(Error.PARSE_ERROR, e);
            }
            if (e instanceof ConnectException) {
                return new AppException(Error.NETWORK_ERROR, e);
            }
            if (e instanceof SSLException) {
                return new AppException(Error.SSL_ERROR, e);
            }
            if (e instanceof ConnectTimeoutException) {
                return new AppException(Error.TIMEOUT_ERROR, e);
            }
            if (e instanceof SocketTimeoutException) {
                return new AppException(Error.TIMEOUT_ERROR, e);
            }
            if (e instanceof UnknownHostException) {
                return new AppException(Error.TIMEOUT_ERROR, e);
            }
            return e instanceof AppException ? (AppException) e : new AppException(Error.UNKNOWN, e);
        }
        return new AppException(Error.UNKNOWN, e);
    }
}
