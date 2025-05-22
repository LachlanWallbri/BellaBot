package io.minio.errors;

/* loaded from: classes7.dex */
public class XmlParserException extends MinioException {
    private static final long serialVersionUID = -3877568719271880309L;
    Exception exception;

    public XmlParserException(Exception exc) {
        this.exception = exc;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return this.exception.toString();
    }
}
