package com.danikula.videocache;

import java.io.File;

/* loaded from: classes.dex */
public interface CacheListener {
    void onCacheAvailable(File file, String str, int i);
}
