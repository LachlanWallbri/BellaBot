package org.apache.http;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface Header extends NameValuePair {
    HeaderElement[] getElements() throws ParseException;
}
