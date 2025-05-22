package com.pudutech.pd_network.storage.aws;

import android.util.Log;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"TAG", "", ExtKt.TAG, "Lorg/json/JSONObject;", OSSConfig.PARAM_REGION, OSSConfig.PARAM_BUCKET, "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ExtKt {
    private static final String TAG = "newAWSConfiguration";

    public static final JSONObject newAWSConfiguration(String region, String bucket) {
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(bucket, "bucket");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.putOpt("Default", jSONObject2);
        jSONObject.putOpt("S3TransferUtility", jSONObject3);
        jSONObject2.put("Bucket", bucket);
        jSONObject2.put("Region", region);
        Log.i(TAG, "newAWSConfiguration > " + jSONObject);
        return jSONObject;
    }
}
