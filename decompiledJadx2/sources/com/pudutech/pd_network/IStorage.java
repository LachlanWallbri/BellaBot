package com.pudutech.pd_network;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/pd_network/IStorage;", "", RequestParameters.SUBRESOURCE_DELETE, "", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "objectKey", "", "callback", "Lcom/pudutech/pd_network/OssCallback;", "download", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "downloadFile", "Ljava/io/File;", "upload", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IStorage {
    void delete(StorageBucketType bucketType, String objectKey, OssCallback callback);

    IOssTaskController download(String url, File downloadFile);

    IOssTaskController upload(PdUploadConfig config);

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static IOssTaskController download(IStorage iStorage, String url, File downloadFile) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
            throw new Exception();
        }
    }
}
