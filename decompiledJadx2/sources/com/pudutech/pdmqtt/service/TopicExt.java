package com.pudutech.pdmqtt.service;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: topic_ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/TopicExt;", "", "uniqueKey", "", "(Ljava/lang/String;)V", "topicSet", "", "getTopicSet", "()Ljava/util/Set;", "getUniqueKey", "()Ljava/lang/String;", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class TopicExt {
    private final Set<String> topicSet;
    private final String uniqueKey;

    public TopicExt(String uniqueKey) {
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        this.uniqueKey = uniqueKey;
        this.topicSet = new LinkedHashSet();
    }

    public final String getUniqueKey() {
        return this.uniqueKey;
    }

    public final Set<String> getTopicSet() {
        return this.topicSet;
    }
}
