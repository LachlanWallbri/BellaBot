package org.apache.http.impl.client;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class LaxRedirectStrategy extends DefaultRedirectStrategy {
    public static final LaxRedirectStrategy INSTANCE = new LaxRedirectStrategy();

    public LaxRedirectStrategy() {
        super(new String[]{"GET", "POST", "HEAD", "DELETE"});
    }
}
