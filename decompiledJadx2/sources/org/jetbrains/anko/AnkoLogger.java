package org.jetbrains.anko;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Logging.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoLogger;", "", "loggerTag", "", "getLoggerTag", "()Ljava/lang/String;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public interface AnkoLogger {
    String getLoggerTag();

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: Logging.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3962k = 3, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static String getLoggerTag(AnkoLogger ankoLogger) {
            String tag;
            tag = Logging.getTag(ankoLogger.getClass());
            return tag;
        }
    }
}
