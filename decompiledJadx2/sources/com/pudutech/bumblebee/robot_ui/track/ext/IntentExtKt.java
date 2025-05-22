package com.pudutech.bumblebee.robot_ui.track.ext;

import android.content.Intent;
import com.pudutech.bumblebee.robot_ui.track.TrackScene;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IntentExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001¨\u0006\t"}, m3961d2 = {"getSceneId", "", "Landroid/content/Intent;", "trackType", "Lcom/pudutech/robot/module/report/track2/TrackType;", "saveSceneId", "isCreate", "", "sceneId", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class IntentExtKt {
    public static /* synthetic */ Intent saveSceneId$default(Intent intent, TrackType trackType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return saveSceneId(intent, trackType, z);
    }

    public static final Intent saveSceneId(Intent saveSceneId, TrackType trackType, boolean z) {
        Intrinsics.checkParameterIsNotNull(saveSceneId, "$this$saveSceneId");
        Intrinsics.checkParameterIsNotNull(trackType, "trackType");
        Intent putExtra = saveSceneId.putExtra(TrackKey.SCENE_ID, TrackScene.INSTANCE.createOrGetSession(trackType, z));
        Intrinsics.checkExpressionValueIsNotNull(putExtra, "this.putExtra(TrackKey.S…ion(trackType, isCreate))");
        return putExtra;
    }

    public static final Intent saveSceneId(Intent saveSceneId, String sceneId) {
        Intrinsics.checkParameterIsNotNull(saveSceneId, "$this$saveSceneId");
        Intrinsics.checkParameterIsNotNull(sceneId, "sceneId");
        Intent putExtra = saveSceneId.putExtra(TrackKey.SCENE_ID, sceneId);
        Intrinsics.checkExpressionValueIsNotNull(putExtra, "this.putExtra(TrackKey.SCENE_ID, sceneId)");
        return putExtra;
    }

    public static /* synthetic */ String getSceneId$default(Intent intent, TrackType trackType, int i, Object obj) {
        if ((i & 1) != 0) {
            trackType = (TrackType) null;
        }
        return getSceneId(intent, trackType);
    }

    public static final String getSceneId(Intent getSceneId, TrackType trackType) {
        String createOrGetSession;
        Intrinsics.checkParameterIsNotNull(getSceneId, "$this$getSceneId");
        String stringExtra = getSceneId.getStringExtra(TrackKey.SCENE_ID);
        return stringExtra != null ? stringExtra : (trackType == null || (createOrGetSession = TrackScene.INSTANCE.createOrGetSession(trackType, true)) == null) ? "" : createOrGetSession;
    }
}
