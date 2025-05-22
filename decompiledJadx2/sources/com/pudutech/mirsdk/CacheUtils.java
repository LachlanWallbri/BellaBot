package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: CacheUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdk/CacheUtils;", "", "()V", "clearCache", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CacheUtils {
    public static final CacheUtils INSTANCE = new CacheUtils();

    private CacheUtils() {
    }

    public final void clearCache() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new CacheUtils$clearCache$1(null), 3, null);
    }
}
