package com.google.zxing.client.result;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
abstract class AbstractDoCoMoResultParser extends ResultParser {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] matchDoCoMoPrefixedField(String str, String str2, boolean z) {
        return matchPrefixedField(str, str2, ';', z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String matchSingleDoCoMoPrefixedField(String str, String str2, boolean z) {
        return matchSinglePrefixedField(str, str2, ';', z);
    }
}
