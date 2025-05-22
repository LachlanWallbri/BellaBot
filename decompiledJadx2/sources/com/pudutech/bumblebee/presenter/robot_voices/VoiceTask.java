package com.pudutech.bumblebee.presenter.robot_voices;

import com.pudutech.resources.voice.VoiceItem;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B?\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012.\u0010\b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\t0\u0005\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\t¢\u0006\u0002\u0010\nB#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0005\"\u00020\u000b¢\u0006\u0002\u0010\fB#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0005\"\u00020\r¢\u0006\u0002\u0010\u000eB#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u0005\"\u00020\u000f¢\u0006\u0002\u0010\u0010B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u000f¢\u0006\u0002\u0010\u0012B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001cR!\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0017j\b\u0012\u0004\u0012\u00020\u000b`\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "", "loopTime_ms", "", "items", "", "Lcom/pudutech/resources/voice/VoiceItem;", "(J[Lcom/pudutech/resources/voice/VoiceItem;)V", "delayItems", "Lkotlin/Pair;", "(J[Lkotlin/Pair;)V", "Lcom/pudutech/bumblebee/presenter/robot_voices/Property;", "(J[Lcom/pudutech/bumblebee/presenter/robot_voices/Property;)V", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceProperty;", "(J[Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceProperty;)V", "Lcom/pudutech/bumblebee/presenter/robot_voices/GeneralVoiceProperty;", "(J[Lcom/pudutech/bumblebee/presenter/robot_voices/GeneralVoiceProperty;)V", "item", "(JLcom/pudutech/bumblebee/presenter/robot_voices/GeneralVoiceProperty;)V", "ttsVoiceProperty", "Lcom/pudutech/bumblebee/presenter/robot_voices/TtsVoiceProperty;", "(JLcom/pudutech/bumblebee/presenter/robot_voices/TtsVoiceProperty;)V", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "listener", "Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "getListener$module_bumblebee_presenter_robotRelease", "()Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "setListener$module_bumblebee_presenter_robotRelease", "(Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;)V", "getLoopTime_ms", "()J", "setLoopTime_ms", "(J)V", "type", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceType;", "getType$module_bumblebee_presenter_robotRelease", "()Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceType;", "setType$module_bumblebee_presenter_robotRelease", "(Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceType;)V", "withListener", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceTask {
    private final ArrayList<Property> list;
    private Listener listener;
    private long loopTime_ms;
    private VoiceType type;

    public final ArrayList<Property> getList() {
        return this.list;
    }

    /* renamed from: getType$module_bumblebee_presenter_robotRelease, reason: from getter */
    public final VoiceType getType() {
        return this.type;
    }

    public final void setType$module_bumblebee_presenter_robotRelease(VoiceType voiceType) {
        Intrinsics.checkParameterIsNotNull(voiceType, "<set-?>");
        this.type = voiceType;
    }

    public final long getLoopTime_ms() {
        return this.loopTime_ms;
    }

    public final void setLoopTime_ms(long j) {
        this.loopTime_ms = j;
    }

    public VoiceTask(long j, VoiceItem... items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        for (VoiceItem voiceItem : items) {
            this.list.add(new VoiceProperty(0L, voiceItem, -1));
        }
    }

    public VoiceTask(long j, Pair<Long, ? extends VoiceItem>... delayItems) {
        Intrinsics.checkParameterIsNotNull(delayItems, "delayItems");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        this.type = this.type;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        for (Pair<Long, ? extends VoiceItem> pair : delayItems) {
            this.list.add(new VoiceProperty(pair.getFirst().longValue(), pair.getSecond(), -1));
        }
    }

    public VoiceTask(long j, Property... items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        this.type = this.type;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        CollectionsKt.addAll(this.list, items);
    }

    public VoiceTask(long j, VoiceProperty... items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        this.type = this.type;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        CollectionsKt.addAll(this.list, items);
    }

    public VoiceTask(long j, GeneralVoiceProperty... items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        for (GeneralVoiceProperty generalVoiceProperty : items) {
            this.list.add(generalVoiceProperty);
        }
    }

    public VoiceTask(long j, GeneralVoiceProperty item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        if (j < 0) {
            this.type = VoiceType.TEMP;
        } else {
            this.type = VoiceType.MODE;
        }
        this.loopTime_ms = j;
        this.list.add(item);
    }

    public VoiceTask(long j, TtsVoiceProperty ttsVoiceProperty) {
        VoiceType voiceType;
        Intrinsics.checkParameterIsNotNull(ttsVoiceProperty, "ttsVoiceProperty");
        this.list = new ArrayList<>();
        this.type = VoiceType.TEMP;
        this.type = this.type;
        if (j < 0) {
            voiceType = VoiceType.TEMP;
        } else {
            voiceType = VoiceType.MODE;
        }
        this.type = voiceType;
        this.loopTime_ms = j;
        this.list.add(ttsVoiceProperty);
    }

    public final VoiceTask withListener(Listener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
        return this;
    }

    /* renamed from: getListener$module_bumblebee_presenter_robotRelease, reason: from getter */
    public final Listener getListener() {
        return this.listener;
    }

    public final void setListener$module_bumblebee_presenter_robotRelease(Listener listener) {
        this.listener = listener;
    }
}
