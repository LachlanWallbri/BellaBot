package com.pudutech.robot.module.openapi.handler;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.robot.module.openapi.remoteapi.BaseRemoteApiProvider;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B?\u00128\u0010\u0004\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00070\u0005j\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u0007j\b\u0012\u0004\u0012\u00020\u0002`\t`\b¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\u0006H&R@\u0010\u0004\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00070\u0005j\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u0007j\b\u0012\u0004\u0012\u00020\u0002`\t`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/handler/BaseMsgHandler;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/module/openapi/remoteapi/BaseRemoteApiProvider;", "", "providers", "Ljava/util/HashMap;", "", "Ljava/util/ArrayList;", "Lkotlin/collections/HashMap;", "Lkotlin/collections/ArrayList;", "(Ljava/util/HashMap;)V", "getMsgType", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class BaseMsgHandler<T extends BaseRemoteApiProvider> {
    private final HashMap<String, ArrayList<BaseRemoteApiProvider>> providers;

    public abstract String getMsgType();

    public BaseMsgHandler(HashMap<String, ArrayList<BaseRemoteApiProvider>> providers) {
        Intrinsics.checkParameterIsNotNull(providers, "providers");
        this.providers = providers;
    }
}
