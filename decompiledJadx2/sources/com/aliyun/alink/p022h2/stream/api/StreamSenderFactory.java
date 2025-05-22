package com.aliyun.alink.p022h2.stream.api;

import com.aliyun.alink.p022h2.api.Profile;
import com.aliyun.alink.p022h2.stream.biz.C0903c;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StreamSenderFactory {
    public static IStreamSender streamSender(Profile profile) {
        return new C0903c(profile);
    }
}
