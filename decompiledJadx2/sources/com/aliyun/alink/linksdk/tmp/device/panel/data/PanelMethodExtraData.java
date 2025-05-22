package com.aliyun.alink.linksdk.tmp.device.panel.data;

import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PanelMethodExtraData {
    public TmpEnum.ChannelStrategy channelStrategy;

    public PanelMethodExtraData(TmpEnum.ChannelStrategy channelStrategy) {
        this.channelStrategy = channelStrategy;
    }

    public String toString() {
        return String.valueOf(this.channelStrategy);
    }
}
