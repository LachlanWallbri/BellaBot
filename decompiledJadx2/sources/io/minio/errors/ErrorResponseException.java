package io.minio.errors;

import io.minio.messages.ErrorResponse;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes7.dex */
public class ErrorResponseException extends MinioException {
    private static final long serialVersionUID = -2933211538346902928L;
    private final ErrorResponse errorResponse;
    private final Response response;

    public ErrorResponseException(ErrorResponse errorResponse, Response response, String str) {
        super(errorResponse.message(), str);
        this.errorResponse = errorResponse;
        this.response = response;
    }

    public ErrorResponse errorResponse() {
        return this.errorResponse;
    }

    public Response response() {
        return this.response;
    }

    @Override // java.lang.Throwable
    public String toString() {
        Request request = this.response.request();
        return "error occurred\n" + this.errorResponse.toString() + "\nrequest={method=" + request.method() + ", url=" + request.url() + ", headers=" + request.headers().toString().replaceAll("Signature=([0-9a-f]+)", "Signature=*REDACTED*").replaceAll("Credential=([^/]+)", "Credential=*REDACTED*") + "}\nresponse={code=" + this.response.code() + ", headers=" + this.response.headers() + "}\n";
    }
}
