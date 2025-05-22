package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a;

import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0942c;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CloudChannelProxy.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.a.b */
/* loaded from: classes.dex */
public class C0922b implements IDataDownListener, IThingCloudChannel {

    /* renamed from: a */
    private static final String f721a = "[AlcsLPBS]CloudChannelProxy";

    /* renamed from: b */
    private IThingCloudChannel f722b;

    public C0922b(IThingCloudChannel iThingCloudChannel, IDataDownListener iDataDownListener) {
        this.f722b = iThingCloudChannel;
        if (iThingCloudChannel != null) {
            iThingCloudChannel.addDownDataListener(iDataDownListener);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener
    public void onDataDown(String str, byte[] bArr) {
        ALog.m479d(f721a, "onDataDown topic:" + str + " payload hex:" + C0942c.m366a(bArr) + " mChannel:" + this.f722b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public IThingCloudChannel m344a() {
        return this.f722b;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel
    public void reportData(String str, byte[] bArr) {
        if (this.f722b != null || bArr != null) {
            ALog.m479d(f721a, "reportData topic:" + str + " payload hex:" + C0942c.m366a(bArr) + " mChannel:" + this.f722b);
            this.f722b.reportData(str, bArr);
            return;
        }
        ALog.m480e(f721a, "reportData topic:" + str + " payload:" + bArr + " mChannel :" + this.f722b);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel
    public void reportData(String str, Object obj, IThingCloudChannel.IChannelActionListener iChannelActionListener) {
        if (this.f722b != null || obj != null) {
            ALog.m479d(f721a, "reportData topic:" + str + " payload " + obj + " mChannel:" + this.f722b + " listener:" + iChannelActionListener);
            this.f722b.reportData(str, obj, iChannelActionListener);
            return;
        }
        ALog.m480e(f721a, "reportData topic:" + str + " payload:" + obj + " mChannel :" + this.f722b + " listener:" + iChannelActionListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel
    public void addDownDataListener(IDataDownListener iDataDownListener) {
        ALog.m479d(f721a, "addDownDataListener listener:" + iDataDownListener + " mChannel:" + this.f722b);
        IThingCloudChannel iThingCloudChannel = this.f722b;
        if (iThingCloudChannel != null) {
            iThingCloudChannel.addDownDataListener(iDataDownListener);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel
    public void removeDownDataListener(IDataDownListener iDataDownListener) {
        ALog.m479d(f721a, "removeDownDataListener listener:" + iDataDownListener + " mChannel:" + this.f722b);
        IThingCloudChannel iThingCloudChannel = this.f722b;
        if (iThingCloudChannel != null) {
            iThingCloudChannel.removeDownDataListener(iDataDownListener);
        }
    }
}
