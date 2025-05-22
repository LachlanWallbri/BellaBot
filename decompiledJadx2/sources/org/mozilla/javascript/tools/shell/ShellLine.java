package org.mozilla.javascript.tools.shell;

import java.io.InputStream;
import java.nio.charset.Charset;
import org.mozilla.javascript.Scriptable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class ShellLine {
    @Deprecated
    public static InputStream getStream(Scriptable scriptable) {
        ShellConsole console = ShellConsole.getConsole(scriptable, Charset.defaultCharset());
        if (console != null) {
            return console.getIn();
        }
        return null;
    }
}
