package io.netty.handler.codec.smtp;

import io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultSmtpRequest implements SmtpRequest {
    private final SmtpCommand command;
    private final List<CharSequence> parameters;

    public DefaultSmtpRequest(SmtpCommand smtpCommand) {
        this.command = (SmtpCommand) ObjectUtil.checkNotNull(smtpCommand, "command");
        this.parameters = Collections.emptyList();
    }

    public DefaultSmtpRequest(SmtpCommand smtpCommand, CharSequence... charSequenceArr) {
        this.command = (SmtpCommand) ObjectUtil.checkNotNull(smtpCommand, "command");
        this.parameters = SmtpUtils.toUnmodifiableList(charSequenceArr);
    }

    public DefaultSmtpRequest(CharSequence charSequence, CharSequence... charSequenceArr) {
        this(SmtpCommand.valueOf(charSequence), charSequenceArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSmtpRequest(SmtpCommand smtpCommand, List<CharSequence> list) {
        this.command = (SmtpCommand) ObjectUtil.checkNotNull(smtpCommand, "command");
        this.parameters = list != null ? Collections.unmodifiableList(list) : Collections.emptyList();
    }

    @Override // io.netty.handler.codec.smtp.SmtpRequest
    public SmtpCommand command() {
        return this.command;
    }

    @Override // io.netty.handler.codec.smtp.SmtpRequest
    public List<CharSequence> parameters() {
        return this.parameters;
    }

    public int hashCode() {
        return (this.command.hashCode() * 31) + this.parameters.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultSmtpRequest)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        DefaultSmtpRequest defaultSmtpRequest = (DefaultSmtpRequest) obj;
        return command().equals(defaultSmtpRequest.command()) && parameters().equals(defaultSmtpRequest.parameters());
    }

    public String toString() {
        return "DefaultSmtpRequest{command=" + this.command + ", parameters=" + this.parameters + '}';
    }
}
