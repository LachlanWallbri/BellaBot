package io.netty.handler.codec.smtp;

import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultSmtpResponse implements SmtpResponse {
    private final int code;
    private final List<CharSequence> details;

    public DefaultSmtpResponse(int i) {
        this(i, (List<CharSequence>) null);
    }

    public DefaultSmtpResponse(int i, CharSequence... charSequenceArr) {
        this(i, SmtpUtils.toUnmodifiableList(charSequenceArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSmtpResponse(int i, List<CharSequence> list) {
        if (i < 100 || i > 599) {
            throw new IllegalArgumentException("code must be 100 <= code <= 599");
        }
        this.code = i;
        if (list == null) {
            this.details = Collections.emptyList();
        } else {
            this.details = Collections.unmodifiableList(list);
        }
    }

    @Override // io.netty.handler.codec.smtp.SmtpResponse
    public int code() {
        return this.code;
    }

    @Override // io.netty.handler.codec.smtp.SmtpResponse
    public List<CharSequence> details() {
        return this.details;
    }

    public int hashCode() {
        return (this.code * 31) + this.details.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultSmtpResponse)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        DefaultSmtpResponse defaultSmtpResponse = (DefaultSmtpResponse) obj;
        return code() == defaultSmtpResponse.code() && details().equals(defaultSmtpResponse.details());
    }

    public String toString() {
        return "DefaultSmtpResponse{code=" + this.code + ", details=" + this.details + '}';
    }
}
