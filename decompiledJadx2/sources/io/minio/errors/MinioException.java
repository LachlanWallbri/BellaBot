package io.minio.errors;

/* loaded from: classes7.dex */
public class MinioException extends Exception {
    private static final long serialVersionUID = -7241010318779326306L;
    String httpTrace;

    public MinioException() {
        this.httpTrace = null;
    }

    public MinioException(String str) {
        super(str);
        this.httpTrace = null;
    }

    public MinioException(String str, String str2) {
        super(str);
        this.httpTrace = null;
        this.httpTrace = str2;
    }

    public String httpTrace() {
        return this.httpTrace;
    }
}
