package com.pudutech.robot.module.voice;

import com.pudutech.robot.module.voice.data.BaseVoice;
import com.pudutech.robot.module.voice.data.CombineVoices;
import com.pudutech.robot.module.voice.data.SingleVoice;
import com.pudutech.robot.module.voice.data.VoicePlayMode;
import com.pudutech.robot.module.voice.data.VoicePlayTask;
import com.pudutech.robot.module.voice.data.VoicePlayType;
import com.pudutech.robot.module.voice.data.VoiceSubItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceTaskFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00112\b\b\u0002\u0010\u000f\u001a\u00020\u0010J3\u0010\r\u001a\u00020\u000e2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0006\"\u00020\u00132\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoiceTaskFactory;", "", "()V", "genCombineVoices", "Lcom/pudutech/robot/module/voice/data/CombineVoices;", "v", "", "Lcom/pudutech/robot/module/voice/data/VoiceSubItem;", "delayPlay", "", "playCount", "", "([Lcom/pudutech/robot/module/voice/data/VoiceSubItem;JI)Lcom/pudutech/robot/module/voice/data/CombineVoices;", "genTask", "Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "mode", "Lcom/pudutech/robot/module/voice/data/VoicePlayMode;", "Lcom/pudutech/robot/module/voice/data/SingleVoice;", "vs", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "type", "Lcom/pudutech/robot/module/voice/data/VoicePlayType;", "([Lcom/pudutech/robot/module/voice/data/BaseVoice;Lcom/pudutech/robot/module/voice/data/VoicePlayMode;Lcom/pudutech/robot/module/voice/data/VoicePlayType;)Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceTaskFactory {
    public static final VoiceTaskFactory INSTANCE = new VoiceTaskFactory();

    private VoiceTaskFactory() {
    }

    public static /* synthetic */ VoicePlayTask genTask$default(VoiceTaskFactory voiceTaskFactory, SingleVoice singleVoice, VoicePlayMode voicePlayMode, int i, Object obj) {
        if ((i & 2) != 0) {
            voicePlayMode = VoicePlayMode.Mode;
        }
        return voiceTaskFactory.genTask(singleVoice, voicePlayMode);
    }

    public final VoicePlayTask genTask(SingleVoice v, VoicePlayMode mode) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        return new VoicePlayTask(CollectionsKt.arrayListOf(v), mode, null, null, 12, null);
    }

    public static /* synthetic */ VoicePlayTask genTask$default(VoiceTaskFactory voiceTaskFactory, CombineVoices combineVoices, VoicePlayMode voicePlayMode, int i, Object obj) {
        if ((i & 2) != 0) {
            voicePlayMode = VoicePlayMode.Mode;
        }
        return voiceTaskFactory.genTask(combineVoices, voicePlayMode);
    }

    public final VoicePlayTask genTask(CombineVoices v, VoicePlayMode mode) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        return new VoicePlayTask(CollectionsKt.arrayListOf(v), mode, null, null, 12, null);
    }

    public static /* synthetic */ VoicePlayTask genTask$default(VoiceTaskFactory voiceTaskFactory, BaseVoice[] baseVoiceArr, VoicePlayMode voicePlayMode, VoicePlayType voicePlayType, int i, Object obj) {
        if ((i & 2) != 0) {
            voicePlayMode = VoicePlayMode.Mode;
        }
        if ((i & 4) != 0) {
            voicePlayType = VoicePlayType.Order;
        }
        return voiceTaskFactory.genTask(baseVoiceArr, voicePlayMode, voicePlayType);
    }

    public final VoicePlayTask genTask(BaseVoice[] vs, VoicePlayMode mode, VoicePlayType type) {
        Intrinsics.checkParameterIsNotNull(vs, "vs");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(type, "type");
        List mutableList = ArraysKt.toMutableList(vs);
        if (mutableList != null) {
            return new VoicePlayTask((ArrayList) mutableList, mode, type, null, 8, null);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.module.voice.data.BaseVoice> /* = java.util.ArrayList<com.pudutech.robot.module.voice.data.BaseVoice> */");
    }

    public static /* synthetic */ CombineVoices genCombineVoices$default(VoiceTaskFactory voiceTaskFactory, VoiceSubItem[] voiceSubItemArr, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = 0;
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        return voiceTaskFactory.genCombineVoices(voiceSubItemArr, j, i);
    }

    public final CombineVoices genCombineVoices(VoiceSubItem[] v, long delayPlay, int playCount) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        List mutableList = ArraysKt.toMutableList(v);
        if (mutableList != null) {
            return new CombineVoices((ArrayList) mutableList, delayPlay, playCount);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.module.voice.data.VoiceSubItem> /* = java.util.ArrayList<com.pudutech.robot.module.voice.data.VoiceSubItem> */");
    }
}
