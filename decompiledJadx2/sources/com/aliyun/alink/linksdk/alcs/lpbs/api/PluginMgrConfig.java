package com.aliyun.alink.linksdk.alcs.lpbs.api;

import android.content.Context;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PluginMgrConfig {
    public ICloudChannelFactory cloudChannelFactory;
    public Context context;
    public IDevInfoTrans devInfoTrans;
    public PalInitData initData;
    public IJSEngine jsEngine;
    public IJsProvider jsProvider;
}
