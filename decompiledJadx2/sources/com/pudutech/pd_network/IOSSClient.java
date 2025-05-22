package com.pudutech.pd_network;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/pd_network/IOSSClient;", "Lcom/pudutech/pd_network/IOssTaskController;", RequestParameters.SUBRESOURCE_DELETE, "", "objectKey", "", "callback", "Lcom/pudutech/pd_network/OssCallback;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IOSSClient extends IOssTaskController {
    void delete(String objectKey, OssCallback callback);

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static void delete(IOSSClient iOSSClient, String objectKey, OssCallback callback) {
            Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
            Intrinsics.checkParameterIsNotNull(callback, "callback");
            throw new Exception();
        }
    }
}
