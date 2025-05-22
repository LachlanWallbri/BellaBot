package com.pudutech.mirsdk.map;

import java.io.File;
import java.io.FilenameFilter;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* compiled from: Atlas.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/io/File;", "kotlin.jvm.PlatformType", "name", "", "accept"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class Atlas$checkAllTopoMap$mapFiles$1 implements FilenameFilter {
    public static final Atlas$checkAllTopoMap$mapFiles$1 INSTANCE = new Atlas$checkAllTopoMap$mapFiles$1();

    Atlas$checkAllTopoMap$mapFiles$1() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "pdmap", false, 2, (Object) null);
    }
}
