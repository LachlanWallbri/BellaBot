package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ResourceTranscoder<Z, R> {
    Resource<R> transcode(Resource<Z> resource, Options options);
}
