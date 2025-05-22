package retrofit2.adapter.rxjava2;

import javax.annotation.Nullable;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public final class Result<T> {

    @Nullable
    private final Throwable error;

    @Nullable
    private final Response<T> response;

    public static <T> Result<T> error(Throwable th) {
        if (th == null) {
            throw new NullPointerException("error == null");
        }
        return new Result<>(null, th);
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response == null) {
            throw new NullPointerException("response == null");
        }
        return new Result<>(response, null);
    }

    private Result(@Nullable Response<T> response, @Nullable Throwable th) {
        this.response = response;
        this.error = th;
    }

    @Nullable
    public Response<T> response() {
        return this.response;
    }

    @Nullable
    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }
}
