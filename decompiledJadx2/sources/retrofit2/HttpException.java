package retrofit2;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    private static String getMessage(Response<?> response) {
        Utils.checkNotNull(response, "response == null");
        return "HTTP " + response.code() + " " + response.message();
    }

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public Response<?> response() {
        return this.response;
    }
}
