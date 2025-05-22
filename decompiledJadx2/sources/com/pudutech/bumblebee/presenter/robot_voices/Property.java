package com.pudutech.bumblebee.presenter.robot_voices;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Property.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/Property;", "", "timeLag_ms", "", "(J)V", "getTimeLag_ms", "()J", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceProperty;", "Lcom/pudutech/bumblebee/presenter/robot_voices/GeneralVoiceProperty;", "Lcom/pudutech/bumblebee/presenter/robot_voices/TtsVoiceProperty;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class Property {
    private final long timeLag_ms;

    private Property(long j) {
        this.timeLag_ms = j;
    }

    public /* synthetic */ Property(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    public final long getTimeLag_ms() {
        return this.timeLag_ms;
    }
}
