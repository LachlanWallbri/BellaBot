package io.netty.handler.codec.smtp;

import io.netty.util.AsciiString;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SmtpRequests {
    private static final SmtpRequest DATA = new DefaultSmtpRequest(SmtpCommand.DATA);
    private static final SmtpRequest NOOP = new DefaultSmtpRequest(SmtpCommand.NOOP);
    private static final SmtpRequest RSET = new DefaultSmtpRequest(SmtpCommand.RSET);
    private static final SmtpRequest HELP_NO_ARG = new DefaultSmtpRequest(SmtpCommand.HELP);
    private static final SmtpRequest QUIT = new DefaultSmtpRequest(SmtpCommand.QUIT);
    private static final AsciiString FROM_NULL_SENDER = AsciiString.cached("FROM:<>");

    public static SmtpRequest helo(CharSequence charSequence) {
        return new DefaultSmtpRequest(SmtpCommand.HELO, charSequence);
    }

    public static SmtpRequest ehlo(CharSequence charSequence) {
        return new DefaultSmtpRequest(SmtpCommand.EHLO, charSequence);
    }

    public static SmtpRequest noop() {
        return NOOP;
    }

    public static SmtpRequest data() {
        return DATA;
    }

    public static SmtpRequest rset() {
        return RSET;
    }

    public static SmtpRequest help(String str) {
        return str == null ? HELP_NO_ARG : new DefaultSmtpRequest(SmtpCommand.HELP, str);
    }

    public static SmtpRequest quit() {
        return QUIT;
    }

    public static SmtpRequest mail(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (charSequenceArr == null || charSequenceArr.length == 0) {
            SmtpCommand smtpCommand = SmtpCommand.MAIL;
            CharSequence[] charSequenceArr2 = new CharSequence[1];
            charSequenceArr2[0] = charSequence != null ? "FROM:<" + ((Object) charSequence) + Typography.greater : FROM_NULL_SENDER;
            return new DefaultSmtpRequest(smtpCommand, charSequenceArr2);
        }
        ArrayList arrayList = new ArrayList(charSequenceArr.length + 1);
        arrayList.add(charSequence != null ? "FROM:<" + ((Object) charSequence) + Typography.greater : FROM_NULL_SENDER);
        for (CharSequence charSequence2 : charSequenceArr) {
            arrayList.add(charSequence2);
        }
        return new DefaultSmtpRequest(SmtpCommand.MAIL, arrayList);
    }

    public static SmtpRequest rcpt(CharSequence charSequence, CharSequence... charSequenceArr) {
        ObjectUtil.checkNotNull(charSequence, "recipient");
        if (charSequenceArr == null || charSequenceArr.length == 0) {
            return new DefaultSmtpRequest(SmtpCommand.RCPT, "TO:<" + ((Object) charSequence) + Typography.greater);
        }
        ArrayList arrayList = new ArrayList(charSequenceArr.length + 1);
        arrayList.add("TO:<" + ((Object) charSequence) + Typography.greater);
        for (CharSequence charSequence2 : charSequenceArr) {
            arrayList.add(charSequence2);
        }
        return new DefaultSmtpRequest(SmtpCommand.RCPT, arrayList);
    }

    public static SmtpRequest expn(CharSequence charSequence) {
        return new DefaultSmtpRequest(SmtpCommand.EXPN, (CharSequence) ObjectUtil.checkNotNull(charSequence, "mailingList"));
    }

    public static SmtpRequest vrfy(CharSequence charSequence) {
        return new DefaultSmtpRequest(SmtpCommand.VRFY, (CharSequence) ObjectUtil.checkNotNull(charSequence, "user"));
    }

    private SmtpRequests() {
    }
}
