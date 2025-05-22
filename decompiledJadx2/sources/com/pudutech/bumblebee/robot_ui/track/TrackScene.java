package com.pudutech.bumblebee.robot_ui.track;

import com.pudutech.robot.module.report.track2.TrackType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TrackScene.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/TrackScene;", "", "()V", "sceneMap", "", "", "createOrGetSession", "type", "Lcom/pudutech/robot/module/report/track2/TrackType;", "isCreate", "", "createSceneId", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TrackScene {
    public static final TrackScene INSTANCE = new TrackScene();
    private static final Map<String, String> sceneMap = new LinkedHashMap();

    private TrackScene() {
    }

    public final synchronized String createOrGetSession(TrackType type, boolean isCreate) {
        String createSceneId;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (isCreate) {
            createSceneId = createSceneId();
            sceneMap.put(type.name(), createSceneId);
        } else if (sceneMap.containsKey(type.name())) {
            createSceneId = sceneMap.get(type.name());
            if (createSceneId == null) {
                createSceneId = createSceneId();
            }
        } else {
            createSceneId = createSceneId();
            sceneMap.put(type.name(), createSceneId);
        }
        return createSceneId;
    }

    private final String createSceneId() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        return StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }
}
