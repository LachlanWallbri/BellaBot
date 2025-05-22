package org.nanohttpd.protocols.http.response;

import org.apache.http.HttpStatus;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public enum Status implements IStatus {
    SWITCH_PROTOCOL(101, "Switching Protocols"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    PARTIAL_CONTENT(HttpStatus.SC_PARTIAL_CONTENT, "Partial Content"),
    MULTI_STATUS(HttpStatus.SC_MULTI_STATUS, "Multi-Status"),
    REDIRECT(301, "Moved Permanently"),
    FOUND(302, "Found"),
    REDIRECT_SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable"),
    REQUEST_TIMEOUT(HttpStatus.SC_REQUEST_TIMEOUT, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(HttpStatus.SC_GONE, "Gone"),
    LENGTH_REQUIRED(HttpStatus.SC_LENGTH_REQUIRED, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(HttpStatus.SC_REQUEST_TOO_LONG, "Payload Too Large"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type"),
    RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED(HttpStatus.SC_EXPECTATION_FAILED, "Expectation Failed"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    UNSUPPORTED_HTTP_VERSION(505, "HTTP Version Not Supported");

    private final String description;
    private final int requestStatus;

    Status(int i, String str) {
        this.requestStatus = i;
        this.description = str;
    }

    public static Status lookup(int i) {
        for (Status status : values()) {
            if (status.getRequestStatus() == i) {
                return status;
            }
        }
        return null;
    }

    @Override // org.nanohttpd.protocols.http.response.IStatus
    public String getDescription() {
        return "" + this.requestStatus + " " + this.description;
    }

    @Override // org.nanohttpd.protocols.http.response.IStatus
    public int getRequestStatus() {
        return this.requestStatus;
    }
}
