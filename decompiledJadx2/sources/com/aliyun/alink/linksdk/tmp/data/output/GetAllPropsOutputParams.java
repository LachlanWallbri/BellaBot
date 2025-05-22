package com.aliyun.alink.linksdk.tmp.data.output;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetAllPropsOutputParams extends OutputParams {
    public static final String PARAMS_GETALLPROPS_RESPONSE = "params_getallprops_response";

    public GetAllPropsOutputParams(TmpCommonResponse tmpCommonResponse) {
        super(PARAMS_GETALLPROPS_RESPONSE, new ValueWrapper(tmpCommonResponse));
    }
}
