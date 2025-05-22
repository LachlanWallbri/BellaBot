package com.pudutech.mirsdkwrap.lib.interf;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestinationStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\b2\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/MoveByDestinationStateListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "onArrive", "", LinkFormat.DOMAIN, "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "onCancel", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "hasLeft", "", "onDone", "onPause", "onStart", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MoveByDestinationStateListener extends BaseRobotMoveStateListener {
    void onArrive(Destination d);

    void onCancel(ArrayList<Destination> d, boolean hasLeft);

    void onDone(Destination d);

    void onPause(Destination d);

    void onStart(Destination d);
}
