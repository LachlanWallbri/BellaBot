package io.netty.handler.codec.smtp;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface SmtpRequest {
    SmtpCommand command();

    List<CharSequence> parameters();
}
