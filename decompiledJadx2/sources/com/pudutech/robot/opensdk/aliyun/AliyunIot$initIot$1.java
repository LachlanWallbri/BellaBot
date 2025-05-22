package com.pudutech.robot.opensdk.aliyun;

import com.aliyun.alink.linkkit.api.ILinkKit;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linksdk.tools.AError;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.interf.ICallback;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AliyunIot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/robot/opensdk/aliyun/AliyunIot$initIot$1", "Lcom/aliyun/alink/linkkit/api/ILinkKitConnectListener;", "onError", "", "p0", "Lcom/aliyun/alink/linksdk/tools/AError;", "onInitDone", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AliyunIot$initIot$1 implements ILinkKitConnectListener {
    final /* synthetic */ ICallback $callBack;
    final /* synthetic */ AliyunIot this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AliyunIot$initIot$1(AliyunIot aliyunIot, ICallback iCallback) {
        this.this$0 = aliyunIot;
        this.$callBack = iCallback;
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKitConnectListener
    public void onInitDone(Object p0) {
        String str;
        boolean z;
        AliyunIot$iotNotifyListener$1 aliyunIot$iotNotifyListener$1;
        String str2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onInitDone : p0 = " + p0 + "; ");
        z = this.this$0.isRelease;
        if (z) {
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "onInitDone : isRelease; ");
            LinkKit.getInstance().deinit();
        } else {
            ILinkKit linkKit = LinkKit.getInstance();
            aliyunIot$iotNotifyListener$1 = this.this$0.iotNotifyListener;
            linkKit.registerOnPushListener(aliyunIot$iotNotifyListener$1);
            this.this$0.subscribeTopic();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AliyunIot$initIot$1$onInitDone$1(this, null), 2, null);
        }
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKitConnectListener
    public void onError(AError p0) {
        String str;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onError : p0 = ");
        sb.append(p0 != null ? p0.getMsg() : null);
        sb.append(";code = ");
        sb.append(p0 != null ? Integer.valueOf(p0.getCode()) : null);
        sb.append(' ');
        objArr[0] = sb.toString();
        Pdlog.m3274e(str, objArr);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AliyunIot$initIot$1$onError$1(this, p0, null), 3, null);
    }
}
